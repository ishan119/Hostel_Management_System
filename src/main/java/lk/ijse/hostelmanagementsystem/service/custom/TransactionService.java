package lk.ijse.hostelmanagementsystem.service.custom;

import lk.ijse.hostelmanagementsystem.dto.custom.StudentRoomDTO;


public interface TransactionService  {
    boolean reserveRooms(StudentRoomDTO studentRoomDTO);
    boolean makeLeave(StudentRoomDTO studentRoomDTO);
}
