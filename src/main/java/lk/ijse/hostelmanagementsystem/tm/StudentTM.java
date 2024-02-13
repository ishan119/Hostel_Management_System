package lk.ijse.hostelmanagementsystem.tm;

import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentTM {
    private String id;
    private String name;
    private String address;
    private String city;
    private String contact;
    private String gmail;


    private StudentDTO studentDTO;
}
