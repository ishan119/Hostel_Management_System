package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXButton btnLogin;
    public AnchorPane LoginFormContext;
    public Label lblWrong;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnLogin) {
            String username = txtUserName.getText();
            String password = txtPassword.getText();
            if (username.equalsIgnoreCase("Ishan") && password.equalsIgnoreCase("1234")) {
                System.out.println("login success");
                Stage window = (Stage) LoginFormContext.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"))));
                window.centerOnScreen();
            } else if
            (txtUserName.getText().isEmpty() && txtPassword.getText().isEmpty()) {
                lblWrong.setText("Please enter your data.");
            } else {
                lblWrong.setText("Wrong username or password!");
            }
        }
    }
}
