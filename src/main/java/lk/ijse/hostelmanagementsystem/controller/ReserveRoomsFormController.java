package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomTypeDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentRoomDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.Room;
import lk.ijse.hostelmanagementsystem.entity.custom.Student;
import lk.ijse.hostelmanagementsystem.entity.custom.StudentRoom;
import lk.ijse.hostelmanagementsystem.service.custom.RoomService;
import lk.ijse.hostelmanagementsystem.service.custom.RoomTypeService;
import lk.ijse.hostelmanagementsystem.service.custom.StudentService;
import lk.ijse.hostelmanagementsystem.service.custom.TransactionService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.RoomServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.RoomTypeServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.StudentServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.TransactionServiceImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ReserveRoomsFormController {
    public JFXTextField txtReservationNo;
    public JFXComboBox<StudentDTO> cmbStudentId;
    public JFXTextField txtStudentName;
    public JFXComboBox<RoomTypeDTO> cmbRoomTypeId;
    public JFXComboBox<RoomDTO> cmbRoom;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtPayingAmount;
    public DatePicker datePicker1;
    public DatePicker datePicker2;
    public JFXButton btnAdd;
    public TableView table;
    public TableColumn colStudentId;
    public TableColumn colRoomTypeId;
    public TableColumn colRoom;
    public TableColumn colKeyMoney;
    public TableColumn colPayingAmount;
    public TableColumn colDateFrom;
    public TableColumn colDateTo;
    public JFXButton btnRegister;
    public JFXButton btnClear;


    private StudentService studentService;
    private RoomService roomService;
    private RoomTypeService roomTypeService;
    private TransactionService transactionService;

    public void initialize(){
        studentService= new StudentServiceImpl();
        roomService =new RoomServiceImpl();
        roomTypeService =new RoomTypeServiceImpl();
        transactionService=new TransactionServiceImpl();
        setConverters();
        setStudentData();
        setRoomTypeData();


    }

    public void setConverters(){
        cmbStudentId.setConverter(new StringConverter<StudentDTO>() {
            @Override
            public String toString(StudentDTO student) {
                if (student!=null)return student.getId();
                return null;
            }

            @Override
            public StudentDTO fromString(String string) {
                return null;
            }
        });

        cmbRoom.setConverter(new StringConverter<RoomDTO>() {
            @Override
            public String toString(RoomDTO room) {
                if (room!=null)return room.getId();
                return null;
            }

            @Override
            public RoomDTO fromString(String string) {
                return null;
            }
        });

        cmbRoomTypeId.setConverter(new StringConverter<RoomTypeDTO>() {
            @Override
            public String toString(RoomTypeDTO roomType) {
                if (roomType!=null)return roomType.getId();
                return null;
            }

            @Override
            public RoomTypeDTO fromString(String string) {
                return null;
            }
        });
    }

    public void setStudentData(){
        List<StudentDTO> all = studentService.getAll();
        cmbStudentId.setItems(FXCollections.observableArrayList(all));
    }

    public void setRoomTypeData(){
        List<RoomTypeDTO> all = roomTypeService.getAll();
        cmbRoomTypeId.setItems(FXCollections.observableArrayList(all));
    }

    public void setRoomData(RoomTypeDTO type){
        List<RoomDTO> availableRooms = roomService.getAvailableRooms(type);
        cmbRoom.setItems(FXCollections.observableArrayList(availableRooms));
    }



    public void btnRegisterOnAction(ActionEvent actionEvent) {

    }

    public void cmbRoomTypeIdOnAction(ActionEvent actionEvent) {
        RoomTypeDTO type = cmbRoomTypeId.getSelectionModel().getSelectedItem();
        if (type!=null){
            setRoomData(type);
            txtKeyMoney.setText(String.valueOf(type.getKey_money()));
        }
    }

    public void cmbStudentIdOnAction(ActionEvent actionEvent) {
        StudentDTO st = cmbStudentId.getSelectionModel().getSelectedItem();
        if (st!=null){
            txtStudentName.setText(st.getName());
        }
    }

    public void cmbRoomOnAction(ActionEvent actionEvent) {
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String reservationNo = txtReservationNo.getText();
        StudentDTO student = cmbStudentId.getSelectionModel().getSelectedItem();
        RoomDTO room = cmbRoom.getSelectionModel().getSelectedItem();
        double payment = Double.parseDouble(txtPayingAmount.getText());

        room.setAvailability("Not Available");

        StudentRoomDTO studentRoom = new StudentRoomDTO(reservationNo,payment,
                LocalDate.now(),datePicker1.getValue(),datePicker2.getValue(),student,room);
        boolean isAdded = transactionService.reserveRooms(studentRoom);
        if(isAdded){
            new Alert(Alert.AlertType.INFORMATION,"Operation Success").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Operation Failed").show();
        }


    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtReservationNo.clear();
        txtStudentName.clear();
        txtPayingAmount.clear();
        txtKeyMoney.clear();
        cmbRoom.getItems().clear();
        cmbStudentId.getItems().clear();
        cmbRoomTypeId.getItems().clear();
        datePicker1.getEditor().clear();
        datePicker2.getEditor().clear();
    }
}
