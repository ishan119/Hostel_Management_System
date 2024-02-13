package lk.ijse.hostelmanagementsystem.dto.custom;

import lk.ijse.hostelmanagementsystem.dto.SuperDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomTypeDTO implements SuperDTO {
    private String id;
    private Double key_money;
    private String description;

    @ToString.Exclude

    private List<RoomDTO> rooms;
}
