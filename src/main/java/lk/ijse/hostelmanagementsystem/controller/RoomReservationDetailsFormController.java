package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentRoomDTO;
import lk.ijse.hostelmanagementsystem.service.custom.RoomService;
import lk.ijse.hostelmanagementsystem.service.custom.StudentRoomService;
import lk.ijse.hostelmanagementsystem.service.custom.TransactionService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.RoomServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.StudentRoomServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.TransactionServiceImpl;
import lk.ijse.hostelmanagementsystem.tm.ReservedOrAvailableRoomTM;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RoomReservationDetailsFormController {

    public TableView<ReservedOrAvailableRoomTM> tblReservedRoom;
    public TableColumn<ReservedOrAvailableRoomTM,String> colRoomId;
    public TableColumn<ReservedOrAvailableRoomTM,String> colDescription;
    public TableColumn<ReservedOrAvailableRoomTM,String> colType;
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXTextField txtStartDate;
    public JFXTextField txtEndDate;
    public JFXTextField txtKeyMoney;
    public TableView<ReservedOrAvailableRoomTM> tblAvailableRooms;
    public TableColumn<ReservedOrAvailableRoomTM,String > colRoomId2;
    public TableColumn<ReservedOrAvailableRoomTM,String > colDescription2;
    public TableColumn<ReservedOrAvailableRoomTM,String > colType2;

    public StudentRoomService service = new StudentRoomServiceImpl();

    private TransactionService transactionService =new TransactionServiceImpl();
    private RoomService roomService =new RoomServiceImpl();
    public JFXButton btnLeave;

    public void initialize(){
        visualize();
        getData();
        getAvailableRooms();
    }

    public void visualize(){
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));

        colRoomId2.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colDescription2.setCellValueFactory(new PropertyValueFactory<>("description"));
        colType2.setCellValueFactory(new PropertyValueFactory<>("type"));
    }

    public void getData(){
        List<ReservedOrAvailableRoomTM> details = service.getReservedRoomDetails();
        ObservableList<ReservedOrAvailableRoomTM> all = FXCollections.observableArrayList(details);
        tblReservedRoom.setItems(all);
    }

    public  void setData(ReservedOrAvailableRoomTM obj){
        StudentDTO student =obj.getStudentRoom().getStudent();
        txtStudentId.setText(student.getId());
        txtAddress.setText(student.getAddress());
        txtStudentName.setText(student.getName());
        txtContactNo.setText(student.getContact());
        txtStartDate.setText(obj.getStudentRoom().getFrom().toString());
        txtEndDate.setText(obj.getStudentRoom().getTo().toString());
        txtKeyMoney.setText(String.valueOf(obj.getStudentRoom().getAdvance()));
    }
    public void tblReservedRoomOnClickAction(MouseEvent mouseEvent) {
        ReservedOrAvailableRoomTM detail =tblReservedRoom.getSelectionModel().getSelectedItem();
        if (detail !=null)setData(detail);
    }

    public void btnLeaveOnAction(ActionEvent actionEvent) {
        ReservedOrAvailableRoomTM selectedItem = tblReservedRoom.getSelectionModel().getSelectedItem();
        Optional<ButtonType> al = new Alert(Alert.AlertType.WARNING,"Are You Sure?",
                ButtonType.YES,ButtonType.NO).showAndWait();
        if ((al.isPresent() && (selectedItem != null))) {
            if (al.get().equals(ButtonType.NO)){
                return;
            }
        }
        StudentRoomDTO studentRoom = selectedItem.getStudentRoom();
        studentRoom.setTo(LocalDate.now());
        RoomDTO room =new RoomDTO();
        room.setId(selectedItem.getRoomId());
        room.setAvailability("Available");
        studentRoom.setRoom(room);

        boolean b = transactionService.makeLeave(studentRoom);
        if (b){
            new Alert(Alert.AlertType.INFORMATION,"Operation Success").show();
            getData();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Operation Failed").show();
        }

    }

    public void getAvailableRooms(){
        List<ReservedOrAvailableRoomTM> availableRooms = roomService.getAvailableRooms();
        ObservableList<ReservedOrAvailableRoomTM> all = FXCollections.observableArrayList(availableRooms);
        tblAvailableRooms.setItems(all);
    }
}
