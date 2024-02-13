package lk.ijse.hostelmanagementsystem.service.custom.impl;

import lk.ijse.hostelmanagementsystem.dto.custom.RoomDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomTypeDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.Room;
import lk.ijse.hostelmanagementsystem.entity.custom.RoomType;
import lk.ijse.hostelmanagementsystem.repo.custom.RoomRepo;
import lk.ijse.hostelmanagementsystem.repo.custom.impl.RoomRepoImpl;
import lk.ijse.hostelmanagementsystem.service.custom.RoomService;
import lk.ijse.hostelmanagementsystem.tm.ReservedOrAvailableRoomTM;
import lk.ijse.hostelmanagementsystem.tm.RoomTM;
import lk.ijse.hostelmanagementsystem.util.Converter;
import lk.ijse.hostelmanagementsystem.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoomServiceImpl implements RoomService {

    RoomRepo roomRepo;
    Converter converter;
    FactoryConfiguration factory;

    public RoomServiceImpl(){
        factory =FactoryConfiguration.getInstance();
        roomRepo =new RoomRepoImpl();
        converter = Converter.getInstance();
    }
    @Override
    public RoomDTO search(String s){
        Session session =factory.getSession();
        try {
            Room search = roomRepo.search(s,session);
            return converter.toOnlyRoomDTO(search);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean delete(RoomDTO roomDTO){
        Session session = factory.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Room room = converter.toOnlyRoom(roomDTO);
            roomRepo.delete(room,session);
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
    public boolean update(RoomDTO roomDTO) {
        Session session = factory.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Room room = converter.toOnlyRoom(roomDTO);
            //we have to set the foreign value manually because the converter remove that value and keep null value
            RoomType roomType = converter.toOnlyRoomType(roomDTO.getRoomType());
            room.setRoomType(roomType);
            roomRepo.update(room,session);
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
    public RoomDTO save(RoomDTO roomDTO) {
        Session session = factory.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Room room = converter.toOnlyRoom(roomDTO);
            //we have to set the foreign value manually because the converter remove that value and keep null value
            RoomType roomType = converter.toOnlyRoomType(roomDTO.getRoomType());
            room.setRoomType(roomType);
            Room save =roomRepo.save(room,session);
            transaction.commit();
            return converter.toOnlyRoomDTO(save);
        }catch (Exception ex){
            ex.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<RoomDTO> getAll(){
        Session session = factory.getSession();
        List<RoomDTO> list =new ArrayList<>();
        try{
            List<Room> all =roomRepo.getAll(session);
            for (Room room :all){
                list.add(converter.toOnlyRoomDTO(room));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<RoomDTO> getAvailableRooms(RoomTypeDTO roomType) {
        List<RoomDTO> list =new ArrayList<>();
        try {
            Session session = factory.getSession();
            RoomType roomTypeEntity = converter.toOnlyRoomType(roomType);
            List<Room> availableRooms = roomRepo.getAvailableRooms(roomTypeEntity, session);
            for (Room ob:availableRooms) {
                list.add(converter.toOnlyRoomDTO(ob));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<RoomTM> getRoomDataToTable() {
        Session session = factory.getSession();
        try {
            List<Room> all = roomRepo.getAll(session);
            return converter.getRoomDataToTable(all);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }


    public List<ReservedOrAvailableRoomTM> getAvailableRooms(){
        Session session = factory.getSession();
        try {
            List<Room> availableRooms = roomRepo.getAvailableRooms(session);
            return converter.getAvailableRoomList(availableRooms);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

   /* @Override
    public RoomTM searchRoomType(String id) throws Exception {
        Session session =FactoryConfiguration.getInstance().getSession();
        try {
            return Converter.getInstance().fromOnlyRoom(roomRepo.search(id,session));
        }catch (Exception e){
            throw new Exception();
        }
    }*/
}
