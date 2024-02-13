package lk.ijse.hostelmanagementsystem.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Navigation {

    public static void navigate(Route route, AnchorPane pane) throws IOException {
        switch (route){
            case HOME:setStage("HomeForm",pane);break;
            case MANAGAESTUDENTS:setStage("ManageStudents",pane);break;

        }
    }

    private static void setStage(String url, AnchorPane pane) throws IOException {
        String u = "/view/"+url+"form.fxml";
        Parent load = FXMLLoader.load(Navigation.class.getResource(u));
        pane.getChildren().clear();
        pane.getChildren().add(load);
    }
}
