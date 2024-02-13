package lk.ijse.hostelmanagementsystem.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.service.CrudService;
import lk.ijse.hostelmanagementsystem.tm.StudentTM;

import java.sql.SQLException;
import java.util.List;

public interface StudentService extends CrudService<StudentDTO ,String> {
    List<StudentTM> getStudentDataToTable();
    StudentTM searchStudent(String id)throws Exception;
}
