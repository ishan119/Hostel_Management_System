package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentRoomDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.StudentRoom;
import lk.ijse.hostelmanagementsystem.service.custom.JoinService;
import lk.ijse.hostelmanagementsystem.service.custom.StudentRoomService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.JoinServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.StudentRoomServiceImpl;
import lk.ijse.hostelmanagementsystem.tm.RemainingKeyMoneyTM;

import java.util.List;

public class RemainingKeyMoneyFormController {
    public TableView<RemainingKeyMoneyTM> tblKeyMoney;
    public TableColumn<RemainingKeyMoneyTM ,String> colRoom;
    public TableColumn<RemainingKeyMoneyTM ,String> colRoomType;
    public TableColumn<RemainingKeyMoneyTM ,String> colStudent;
    public TableColumn<RemainingKeyMoneyTM ,Double> colFullKeyMoney;
    public TableColumn<RemainingKeyMoneyTM ,Double> colPaidAmount;
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXTextField txtEndDate;
    public JFXTextField txtRemaining;
    public JFXTextField txtPayingAmount;
    public JFXButton btnPaid;
    public JFXTextField txtStartDate;

    private JoinService joinService =new JoinServiceImpl();
    private final StudentRoomService studentRoomService =new StudentRoomServiceImpl();

    public void initialize(){
        visualize();
        getData();
    }

    public void visualize(){
        colRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colStudent.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colFullKeyMoney.setCellValueFactory(new PropertyValueFactory<>("fullKeyMoney"));
        colPaidAmount.setCellValueFactory(new PropertyValueFactory<>("paidAmount"));
    }

    public void getData(){
        List<RemainingKeyMoneyTM> details =joinService.getRemainingKeyMoneyDetails();
        ObservableList<RemainingKeyMoneyTM> data = FXCollections.observableArrayList(details);
        tblKeyMoney.setItems(data);
    }


    public void tblRemainingOnMouseClickOnAction(MouseEvent mouseEvent) {
        RemainingKeyMoneyTM selectedItem = tblKeyMoney.getSelectionModel().getSelectedItem();
        if (selectedItem!=null)setData(selectedItem);
    }

    public void setData(RemainingKeyMoneyTM data){
        StudentRoomDTO studentRoom = data.getStudentRoom();
        StudentDTO student = studentRoom.getStudent();

        txtStudentId.setText(student.getId());
        txtStudentName.setText(student.getName());
        txtAddress.setText(student.getAddress());
        txtContactNo.setText(student.getContact());
        txtStartDate.setText(studentRoom.getFrom().toString());
        txtEndDate.setText(studentRoom.getTo().toString());
        txtRemaining.setText(String.valueOf(data.getFullKeyMoney()-data.getPaidAmount()));
    }

    public void btnPaidOnAction(ActionEvent actionEvent) {
        RemainingKeyMoneyTM item = tblKeyMoney.getSelectionModel().getSelectedItem();
        double amount = item.getPaidAmount() + Double.parseDouble(txtPayingAmount.getText());
        item.setPaidAmount(amount);
        item.getStudentRoom().setAdvance(amount);
        boolean b = studentRoomService.updateKeyMoney(item.getStudentRoom());
        if (b){
            new Alert(Alert.AlertType.INFORMATION,"Operation Success").show();
            getData();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Operation Failed").show();
        }

    }

}
