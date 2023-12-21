package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ResepAdminController implements Initializable {

    public VBox btnCreateResep;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void toCreateResep(MouseEvent mouseEvent) {
        System.out.println("Clicked");
    }
}
