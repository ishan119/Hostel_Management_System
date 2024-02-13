package lk.ijse.hostelmanagementsystem.service.custom.impl;

import lk.ijse.hostelmanagementsystem.dto.custom.RoomTypeDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.RoomType;
import lk.ijse.hostelmanagementsystem.entity.custom.Student;
import lk.ijse.hostelmanagementsystem.repo.custom.RoomRepo;
import lk.ijse.hostelmanagementsystem.repo.custom.RoomTypeRepo;
import lk.ijse.hostelmanagementsystem.repo.custom.impl.RoomRepoImpl;
import lk.ijse.hostelmanagementsystem.repo.custom.impl.RoomTypeRepoImpl;
import lk.ijse.hostelmanagementsystem.service.custom.RoomTypeService;
import lk.ijse.hostelmanagementsystem.tm.RoomTypeTM;
import lk.ijse.hostelmanagementsystem.tm.StudentTM;
import lk.ijse.hostelmanagementsystem.util.Converter;
import lk.ijse.hostelmanagementsystem.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoomTypeServiceImpl implements RoomTypeService {

    RoomTypeRepo roomTypeRepo;
    Converter converter;
    FactoryConfiguration factory;

    public RoomTypeServiceImpl(){
        factory =FactoryConfiguration.getInstance();
        roomTypeRepo =new RoomTypeRepoImpl();
        converter = Converter.getInstance();
    }
    @Override
    public RoomTypeDTO search(String s){
        Session session =factory.getSession();
        try {
            RoomType search = roomTypeRepo.search(s,session);
            return converter.toOnlyRoomTypeDTO(search);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean delete(RoomTypeDTO roomTypeDTO) {
        Session session = factory.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            RoomType roomType = converter.toOnlyRoomType(roomTypeDTO);
            roomTypeRepo.delete(roomType,session);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean update(RoomTypeDTO roomTypeDTO) {
        Session session = factory.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            RoomType roomType = converter.toOnlyRoomType(roomTypeDTO);
            roomTypeRepo.update(roomType,session);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public RoomTypeDTO save(RoomTypeDTO roomTypeDTO){
        Session session = factory.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            RoomType roomType = converter.toOnlyRoomType(roomTypeDTO);
            RoomType save =roomTypeRepo.save(roomType,session);
            transaction.commit();
            return converter.toOnlyRoomTypeDTO(save);
        }catch (Exception ex){
            ex.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return null;    }

    @Override
    public List<RoomTypeDTO> getAll() {
        Session session = factory.getSession();
        List<RoomTypeDTO> list =new ArrayList<>();
        try{
            List<RoomType> all =roomTypeRepo.getAll(session);
            for (RoomType roomType :all){
                list.add(converter.toOnlyRoomTypeDTO(roomType));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return list;
    }

    public RoomTypeTM searchRoomType(String id)throws Exception{
        Session session =FactoryConfiguration.getInstance().getSession();
        try {
            return Converter.getInstance().fromOnlyRoomType(roomTypeRepo.search(id,session));
        }catch (Exception e){
            throw new Exception();
        }
    }
}
