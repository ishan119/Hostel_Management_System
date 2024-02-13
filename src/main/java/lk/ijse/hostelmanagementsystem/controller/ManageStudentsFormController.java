package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.service.custom.StudentService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.StudentServiceImpl;
import lk.ijse.hostelmanagementsystem.tm.ReservedOrAvailableRoomTM;
import lk.ijse.hostelmanagementsystem.tm.RoomTM;
import lk.ijse.hostelmanagementsystem.tm.StudentTM;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ManageStudentsFormController {
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public AnchorPane addStudentPane;
    public JFXTextField txtStudentId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXTextField txtCity;
    public JFXTextField txtGmail;
    public TableView<StudentTM> tblStudent;
    public TableColumn<StudentTM , String> colId;
    public TableColumn<StudentTM , String> colName;
    public TableColumn<StudentTM , String> colAddress;
    public TableColumn<StudentTM , String> colCity;
    public TableColumn<StudentTM , String> colContactNo;
    public TableColumn<StudentTM , String> colGmail;
    public JFXButton btnSearch;
    public JFXButton btnClear;

    private StudentService studentService;

    public void initialize() throws IOException {
        addStudentPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/AddStudentForm.fxml")));
        studentService =new StudentServiceImpl();
        getDataToTable();
        visualize();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean b = studentService.update(collectData());
        if (b){
            new Alert(Alert.AlertType.INFORMATION,"Student Updated").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Student updated Fail").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            StudentDTO studentDTO = studentService.search(txtStudentId.getText());
            if (studentDTO==null){
                new Alert(Alert.AlertType.INFORMATION,"Student Not Found").show();
                return;
            }
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.isPresent()){
                if (buttonType.get().equals(ButtonType.YES)){
                    boolean b = studentService.delete(studentDTO);
                    if (b){
                        new Alert(Alert.AlertType.INFORMATION,"Student Deleted ").show();
                    }else {
                        new Alert(Alert.AlertType.INFORMATION,"Student Deleted Fail").show();

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StudentDTO collectData(){
        String id = txtStudentId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String contact = txtContactNo.getText();
        String gmail = txtGmail.getText();

        StudentDTO student =new StudentDTO(id,name,address,city,contact,gmail,null);
        return student;
    }



    public void visualize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colGmail.setCellValueFactory(new PropertyValueFactory<>("gmail"));
    }

    public void getDataToTable(){
        List<StudentTM> data = studentService.getStudentDataToTable();
        ObservableList<StudentTM> tblData = FXCollections.observableArrayList(data);
        tblStudent.setItems(tblData);
    }

    public void btnSearchOnAction(ActionEvent actionEvent){
        String id = txtStudentId.getText();
        try {
            StudentTM studentTM = studentService.searchStudent(id);
            String name = studentTM.getName();
            txtName.setText(name);
            String address = studentTM.getAddress();
            txtAddress.setText(address);
            String city = studentTM.getCity();
            txtCity.setText(city);
            String contact = studentTM.getContact();
            txtContactNo.setText(contact);
            String gmail = studentTM.getGmail();
            txtGmail.setText(gmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void btnClearOnAction(ActionEvent actionEvent) {
        txtStudentId.clear();
        txtName.clear();
        txtAddress.clear();
        txtCity.clear();
        txtContactNo.clear();
        txtGmail.clear();
    }

    public void tblStudentRefreshOnAction(MouseEvent mouseEvent) {
        StudentTM detail =tblStudent.getSelectionModel().getSelectedItem();
        if (detail !=null)setData(detail);
    }

    public  void setData(StudentTM obj){
        StudentDTO student =obj.getStudentDTO();
        txtStudentId.setText(student.getId());
        txtName.setText(student.getName());
        txtAddress.setText(student.getAddress());
        txtCity.setText(student.getCity());
        txtContactNo.setText(student.getContact());
        txtGmail.setText(student.getGmail());

    }


}
