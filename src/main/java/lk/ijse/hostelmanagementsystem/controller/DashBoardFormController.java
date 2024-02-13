package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashBoardFormController {
    public JFXButton btnHome;
    public JFXButton btnManageStudents;
    public JFXButton btnManageRooms;
    public JFXButton btnReserveRooms;
    public JFXButton btnDetails;
    public JFXButton btnRemaining;
    public AnchorPane DashBoardContext;
    public JFXButton btnLogout;
    public AnchorPane DashBoardMainContext;

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        DashBoardMainContext.getChildren().clear();
        DashBoardMainContext.getChildren().add(FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml")));
    }

    public void btnRemainingOnAction(ActionEvent actionEvent) throws IOException {
        DashBoardContext.getChildren().clear();
        DashBoardContext.getChildren().add(FXMLLoader.load(getClass().getResource("/view/RemainingKeyMoneyForm.fxml")));
    }

    public void btnDetailsOnAction(ActionEvent actionEvent) throws IOException {
        DashBoardContext.getChildren().clear();
        DashBoardContext.getChildren().add(FXMLLoader.load(getClass().getResource("/view/RoomReservationDetailsForm.fxml")));
    }

    public void btnReserveRoomsOnAction(ActionEvent actionEvent) throws IOException {
        DashBoardContext.getChildren().clear();
        DashBoardContext.getChildren().add(FXMLLoader.load(getClass().getResource("/view/ReserveRoomsForm.fxml")));
    }

    public void btnManageRoomsOnAction(ActionEvent actionEvent) throws IOException {
        DashBoardContext.getChildren().clear();
        DashBoardContext.getChildren().add(FXMLLoader.load(getClass().getResource("/view/ManageRoomForm.fxml")));
    }

    public void btnManageStudentsOnAction(ActionEvent actionEvent) throws IOException {
        DashBoardContext.getChildren().clear();
        DashBoardContext.getChildren().add(FXMLLoader.load(getClass().getResource("/view/ManageStudentsForm.fxml")));
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        DashBoardContext.getChildren().clear();
        DashBoardContext.getChildren().add(FXMLLoader.load(getClass().getResource("/view/HomeForm.fxml")));
    }
}
