package lk.ijse.hostelmanagementsystem.entity.custom;

import lk.ijse.hostelmanagementsystem.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Room implements SuperEntity {
    @Id
    private String id;
    private String availability;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roomTypeId")
    private RoomType roomType;

    @ToString.Exclude
    @OneToMany(targetEntity = StudentRoom.class , mappedBy = "room")
    private List<StudentRoom> studentRoomList;

}
