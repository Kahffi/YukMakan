package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;

public class ResepAdminController implements Initializable {
    @FXML
    public VBox btnCreateResep;
    @FXML
    private ImageView btnVie;
    @FXML
    private VBox btnCreateKontenEdu;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void toCreateResep(MouseEvent mouseEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/AdminCreate.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) btnCreateResep.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Clicked");
    }

    @FXML
    public void toViewResep(MouseEvent mouseEvent) {
        try {
            // untuk edit/hapus resep
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/AdminViewContents.fxml"));
            fxmlLoader.setController(new AdminViewContentsController("resep"));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = (Stage) btnCreateResep.getScene().getWindow();
            stage.setScene(scene);
            System.out.println("Meluncur");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void toCreateKontenEdu(MouseEvent event) {
       try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/createKontenEdu.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) btnCreateKontenEdu.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Clicked");
    }

    @FXML
    private void viewKontenEdu(MouseEvent event){
         try {
            // untuk edit/hapus resep
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/AdminViewContents.fxml"));
            fxmlLoader.setController(new AdminViewContentsController("kontenedukasi"));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = (Stage) btnCreateKontenEdu.getScene().getWindow();
            stage.setScene(scene);
            System.out.println("Meluncur");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    

}
