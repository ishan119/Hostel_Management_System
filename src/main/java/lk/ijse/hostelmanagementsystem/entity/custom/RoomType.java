package lk.ijse.hostelmanagementsystem.entity.custom;

import lk.ijse.hostelmanagementsystem.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class RoomType implements SuperEntity {
    @Id
    private String id;
    private Double key_money;
    private String description;

    @ToString.Exclude
    @OneToMany(targetEntity = Room.class ,mappedBy = "roomType")
    private List<Room> rooms;
}
