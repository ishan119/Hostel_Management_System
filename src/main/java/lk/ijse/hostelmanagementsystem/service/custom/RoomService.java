package lk.ijse.hostelmanagementsystem.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomTypeDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.Room;
import lk.ijse.hostelmanagementsystem.entity.custom.RoomType;
import lk.ijse.hostelmanagementsystem.service.CrudService;
import lk.ijse.hostelmanagementsystem.tm.ReservedOrAvailableRoomTM;
import lk.ijse.hostelmanagementsystem.tm.RoomTM;
import lk.ijse.hostelmanagementsystem.tm.StudentTM;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public interface RoomService extends CrudService<RoomDTO , String> {
    List<RoomDTO> getAvailableRooms(RoomTypeDTO roomType);
    List<RoomTM> getRoomDataToTable();
    List<ReservedOrAvailableRoomTM> getAvailableRooms();
   // RoomTM searchRoomType(String id)throws Exception;
}
