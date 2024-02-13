package lk.ijse.hostelmanagementsystem.util;

import lk.ijse.hostelmanagementsystem.dto.custom.RoomDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomTypeDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentRoomDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.Room;
import lk.ijse.hostelmanagementsystem.entity.custom.RoomType;
import lk.ijse.hostelmanagementsystem.entity.custom.Student;
import lk.ijse.hostelmanagementsystem.entity.custom.StudentRoom;
import lk.ijse.hostelmanagementsystem.tm.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class Converter {
    private static Converter converter;
    private Converter(){}
    public static Converter getInstance(){
        if (converter==null)converter=new Converter();
        return converter;
    }

    public Student toOnlyStudent(StudentDTO student){
        return new Student(student.getId(),student.getName(),student.getAddress(),student.getCity(),student.getContact(),student.getGmail(),null);
    }

    public StudentDTO toOnlyStudentDTO(Student student){
        return new StudentDTO(student.getId(),student.getName(),student.getAddress(),student.getCity(),student.getContact(),student.getGmail(),null);
    }

    public Room toOnlyRoom(RoomDTO room){
        return new Room(room.getId(),room.getAvailability(),null,null);
    }

    public RoomDTO toOnlyRoomDTO(Room room){
        return new RoomDTO(room.getId(),room.getAvailability(),null,null);
    }

    public RoomType toOnlyRoomType(RoomTypeDTO roomType){
        return  new RoomType(roomType.getId(),roomType.getKey_money(),roomType.getDescription(),null);
    }

    public RoomTypeDTO toOnlyRoomTypeDTO(RoomType roomType){
        return new RoomTypeDTO(roomType.getId(), roomType.getKey_money(), roomType.getDescription(),null);
    }

    public StudentRoom toOnlyStudentRoom(StudentRoomDTO studentRoom){
        return new StudentRoom(studentRoom.getId(),studentRoom.getAdvance(),Date.valueOf(studentRoom.getPaymentDate()),Date.valueOf(studentRoom.getFrom()),Date.valueOf(studentRoom.getTo()),null,null);
    }

    public StudentRoomDTO toOnlyStudentRoomDTO(StudentRoom studentRoom){
        return new StudentRoomDTO(studentRoom.getId(),studentRoom.getAdvance(),studentRoom.getPaymentDate().toLocalDate(),studentRoom.getFromDate().toLocalDate(),studentRoom.getToDate().toLocalDate(),null,null);
    }

    public List<RoomTM> getRoomDataToTable(List<Room> room){
        ArrayList<RoomTM> list =new ArrayList<>();
        for (Room ob: room){
            RoomTM roomTM = new RoomTM(ob.getId(),ob.getRoomType().getId(),
                    ob.getRoomType().getKey_money(),
                    toOnlyRoomDTO(ob),toOnlyRoomTypeDTO(ob.getRoomType()));
            list.add(roomTM);
        }
        return list;
    }

    public  List<ReservedOrAvailableRoomTM> convertToReserveRoomTM(List<StudentRoom>studentRoomList){
        ArrayList<ReservedOrAvailableRoomTM> list =new ArrayList<>();
        for (StudentRoom obj:studentRoomList) {
            StudentRoomDTO studentRoomDTO = toOnlyStudentRoomDTO(obj);
            StudentDTO studentDTO =toOnlyStudentDTO(obj.getStudent());
            studentRoomDTO.setStudent(studentDTO);
            list.add(new ReservedOrAvailableRoomTM(obj.getRoom().getId(),obj.getRoom().getRoomType().getDescription(),
                    obj.getRoom().getRoomType().getId(),studentRoomDTO));
        }
        return list;
    }

    public List<RemainingKeyMoneyTM> getRemainingKeyMoneyList(List<StudentRoom> studentRoomList){
        ArrayList<RemainingKeyMoneyTM> list =new ArrayList<>();
        for (StudentRoom ob:studentRoomList) {
            Student student = ob.getStudent();
            StudentDTO studentDTO =toOnlyStudentDTO(student);
            toOnlyStudentDTO(student);
            StudentRoomDTO studentRoomDTO = toOnlyStudentRoomDTO(ob);
            studentRoomDTO.setStudent(studentDTO);
            RemainingKeyMoneyTM remaining = new RemainingKeyMoneyTM(ob.getRoom().getId(), ob.getRoom().getRoomType().getId(), student.getName(), ob.getRoom().getRoomType().getKey_money(), ob.getAdvance(), studentRoomDTO);
            list.add(remaining);
        }
        return list;
    }

    public List<ReservedOrAvailableRoomTM> getAvailableRoomList(List<Room> rooms){
        ArrayList<ReservedOrAvailableRoomTM> list =new ArrayList<>();
        for (Room room:rooms){
            list.add(new ReservedOrAvailableRoomTM(room.getId(),room.getRoomType().getDescription(),
                    room.getRoomType().getId(),null));
        }
        return list;
    }

    public List<StudentTM> getStudentDataToTable(List<Student> student){
        ArrayList<StudentTM> list =new ArrayList<>();
        for (Student ob: student){
            StudentTM studentTM = new StudentTM(ob.getId(),ob.getName(),ob.getAddress(),
                    ob.getCity(),ob.getContact(),ob.getGmail(),toOnlyStudentDTO(ob));
            list.add(studentTM);
        }
        return list;
    }

    public StudentTM fromOnlyStudent(Student student){
        return new StudentTM(student.getId(),student.getName(),student.getAddress(),student.getCity(),student.getContact(),student.getGmail(),null);
    }

    public RoomTypeTM fromOnlyRoomType(RoomType roomType){
        return new RoomTypeTM(roomType.getId(),roomType.getKey_money(),roomType.getDescription(),null,null);
    }
}
