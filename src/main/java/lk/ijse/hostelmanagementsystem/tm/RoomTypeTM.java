package lk.ijse.hostelmanagementsystem.tm;

import lk.ijse.hostelmanagementsystem.dto.custom.RoomDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypeTM {
    private String id;
    private Double key_money;
    private String description;

    RoomTypeDTO roomTypeDTO;
    RoomDTO roomDTO;
}
