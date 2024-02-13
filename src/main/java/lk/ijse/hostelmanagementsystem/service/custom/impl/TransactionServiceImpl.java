package lk.ijse.hostelmanagementsystem.service.custom.impl;

import lk.ijse.hostelmanagementsystem.dto.custom.StudentRoomDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.Room;
import lk.ijse.hostelmanagementsystem.entity.custom.Student;
import lk.ijse.hostelmanagementsystem.entity.custom.StudentRoom;
import lk.ijse.hostelmanagementsystem.repo.custom.RoomRepo;
import lk.ijse.hostelmanagementsystem.repo.custom.StudentRoomRepo;
import lk.ijse.hostelmanagementsystem.repo.custom.impl.RoomRepoImpl;
import lk.ijse.hostelmanagementsystem.repo.custom.impl.StudentRoomRepoImpl;
import lk.ijse.hostelmanagementsystem.service.custom.TransactionService;
import lk.ijse.hostelmanagementsystem.util.Converter;
import lk.ijse.hostelmanagementsystem.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransactionServiceImpl implements TransactionService {

    private final Converter converter;
    private final StudentRoomRepo studentRoomRepo;
    private final RoomRepo roomRepo;

    public TransactionServiceImpl(){
        converter=Converter.getInstance();
        studentRoomRepo= new StudentRoomRepoImpl();
        roomRepo =new RoomRepoImpl();
    }
    @Override
    public boolean reserveRooms(StudentRoomDTO studentRoomDTO) {
        Student student = converter.toOnlyStudent(studentRoomDTO.getStudent());
        Room room = converter.toOnlyRoom(studentRoomDTO.getRoom());

        StudentRoom studentRoom = converter.toOnlyStudentRoom(studentRoomDTO);
        studentRoom.setStudent(student);
        studentRoom.setRoom(room);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentRoomRepo.save(studentRoom,session);
            boolean isUpdated = roomRepo.updateAvailability(room,session);
            if (isUpdated){
                transaction.commit();
                return true;
            }
            transaction.rollback();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean makeLeave(StudentRoomDTO studentRoomDTO){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        StudentRoom studentRoom = converter.toOnlyStudentRoom(studentRoomDTO);
        Room room = converter.toOnlyRoom(studentRoomDTO.getRoom());

        try {
            boolean b = studentRoomRepo.updateToDate(session, studentRoom);
            if (b){
                boolean b1 = roomRepo.updateAvailability(room, session);
                if (b1){
                    transaction.commit();
                    return true;
                }
            }

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }
}
