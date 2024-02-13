package lk.ijse.hostelmanagementsystem.service.custom;

import lk.ijse.hostelmanagementsystem.dto.custom.StudentRoomDTO;
import lk.ijse.hostelmanagementsystem.service.CrudService;
import lk.ijse.hostelmanagementsystem.tm.ReservedOrAvailableRoomTM;

import java.util.List;

public interface StudentRoomService extends CrudService<StudentRoomDTO , String> {
    List<ReservedOrAvailableRoomTM> getReservedRoomDetails();
    boolean updateKeyMoney(StudentRoomDTO studentRoomDTO);
}
