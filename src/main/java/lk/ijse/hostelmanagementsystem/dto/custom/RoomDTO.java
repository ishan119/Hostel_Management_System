package lk.ijse.hostelmanagementsystem.dto.custom;

import lk.ijse.hostelmanagementsystem.dto.SuperDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.RoomType;
import lk.ijse.hostelmanagementsystem.entity.custom.StudentRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO implements SuperDTO {
    private String id;
    private String availability;


    private RoomTypeDTO roomType;

    @ToString.Exclude

    private List<StudentRoomDTO> studentRoomList;
}
