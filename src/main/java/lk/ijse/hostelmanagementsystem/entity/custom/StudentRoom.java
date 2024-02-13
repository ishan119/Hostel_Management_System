package lk.ijse.hostelmanagementsystem.entity.custom;

import lk.ijse.hostelmanagementsystem.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class StudentRoom implements SuperEntity {
    @Id
    private String id;
    private Double advance;
    private Date paymentDate;
    private Date fromDate;
    private Date toDate;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "room_id")
    private Room room;
}
