package lk.ijse.hostelmanagementsystem.service.custom;

import lk.ijse.hostelmanagementsystem.dto.custom.RoomTypeDTO;
import lk.ijse.hostelmanagementsystem.service.CrudService;
import lk.ijse.hostelmanagementsystem.tm.RoomTypeTM;

public interface RoomTypeService extends CrudService<RoomTypeDTO , String> {
    RoomTypeTM searchRoomType(String id)throws Exception;
}
