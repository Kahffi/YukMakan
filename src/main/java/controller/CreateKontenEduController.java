package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import static controller.SignUpController.admin;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import dao.KontenEduDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.KontenEdukasi;
import static controller.SignUpController.admin;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class CreateKontenEduController implements Initializable {

    @FXML
    private Button btnLogout;
    @FXML
    private Button btnBack;
    @FXML
    private VBox containerJudul;
    @FXML
    private TextField txtJudul;
    @FXML
    private VBox containerGizi;
    @FXML
    private VBox containerFoto;
    @FXML
    private ImageView imageIn;
    @FXML
    private Button btnAddPhoto;
    @FXML
    private TextArea txtKonten;
    @FXML
    private VBox containerKonten;
    @FXML
    private Button btnCreateKonten;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
    }    

    @FXML
    public void logout(ActionEvent event){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/SignUp.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            admin = null;
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void backToDash(ActionEvent event){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void pilihFoto(ActionEvent event){
         FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                InputStream input = new FileInputStream(selectedFile.getAbsolutePath());
                imageIn.setImage(new Image(input));
                input.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void saveKonten(ActionEvent event){
        KontenEdukasi konten;
        String judul, tanggal, kontenEdukasi;
        tanggal = admin.getDate();
        judul = txtJudul.getText();
        kontenEdukasi = txtKonten.getText();
        konten = new KontenEdukasi(UUID.randomUUID(), imageIn.getImage(), judul, kontenEdukasi, tanggal, admin);
        imageIn.setImage(konten.getImagePath());
        KontenEduDAO.saveKontenEdu(konten);
    }
    
}
