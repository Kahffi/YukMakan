/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.KontenEdukasi;
import dao.AkunDAO;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import dao.KontenEduDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class KontenEduDetailController implements Initializable {

    @FXML
    private Label lblJudul;
    @FXML
    private Text txtContent;
    @FXML
    private Button btnEdit;
    
    @FXML
    private Button btnHapus;
    private KontenEdukasi konten;
    @FXML
    private ImageView imgKonten;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtEditJudul;
    @FXML
    private ImageView imgEditFoto;
    @FXML
    private Button btnPilihFoto;
    @FXML
    private TextArea txtEditKonten;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnBatal;

    /**
     * Initializes the controller class.
     */
    public KontenEduDetailController(KontenEdukasi konten){
        this.konten = konten;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
        lblJudul.setText(konten.getJudul());
        imgKonten.setImage(konten.getImagePath());
        imgKonten.setFitWidth(600);
        imgKonten.setFitHeight(400);
        txtContent.setText(konten.getContent());
        if (!SignUpController.isAdmin){
            btnEdit.setVisible(false); btnEdit.managedProperty().bind(btnEdit.visibleProperty());
            btnHapus.setVisible(false); btnHapus.managedProperty().bind(btnHapus.visibleProperty());
        }       
    }
   
    // method to edit konten edukasi
    @FXML
    void EditKonten(ActionEvent event){
        setVisibility(true);
        txtEditJudul.setText(lblJudul.getText());
        imgEditFoto.setImage(imgKonten.getImage());
        txtEditKonten.setText(txtContent.getText());      
    }
    // to delete konten
    @FXML
    void deleteKonten(ActionEvent event){
        KontenEduDAO.delKontenEdu(this.konten);
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("../view/Dashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // method to back to dashboard
    @FXML
    public void BackToDash(ActionEvent event){
        Stage stage = (Stage) btnBack.getScene().getWindow();
        Parent parent = null;
        try {
            parent = new FXMLLoader().load(getClass().getResource("../view/Dashboard.fxml"));
            Scene scene = new Scene(parent);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void saveEditKonten(ActionEvent event){
        String judulUpdated, kontenUpdated;
        Image pictUpdated;
        judulUpdated = txtEditJudul.getText();
        kontenUpdated = txtEditKonten.getText();
        pictUpdated = imgEditFoto.getImage();
        if (judulUpdated.isEmpty() || kontenUpdated.isEmpty() || pictUpdated == null){
            System.out.println("data tidak boleh ada yang kosong");
        }
        else {
           konten.setJudul(judulUpdated);
           konten.setImagePath(pictUpdated);
           konten.setContent(kontenUpdated);
           KontenEduDAO.updateKontenEdu(konten);
           updateKontenEduProperties(konten);
        }
        setVisibility(false);
    }

    @FXML
    void cancelEdit(ActionEvent event) {
        setVisibility(false);
    }
    // method to set visibility when interact with edit button
    private void setVisibility(Boolean visibility){
        lblJudul.setVisible(!visibility);
        lblJudul.managedProperty().bind(lblJudul.visibleProperty());
        imgKonten.setVisible(!visibility);
        imgKonten.managedProperty().bind(imgKonten.visibleProperty());
        txtContent.setVisible(!visibility);
        txtContent.managedProperty().bind(txtContent.visibleProperty());
        btnSave.setVisible(visibility);
        btnSave.managedProperty().bind(btnSave.visibleProperty());
        btnBatal.setVisible(visibility);
        btnBatal.managedProperty().bind(btnBatal.visibleProperty());
        txtEditJudul.setVisible(visibility);
        txtEditJudul.managedProperty().bind(txtEditJudul.visibleProperty());
        imgEditFoto.setVisible(visibility);
        imgEditFoto.managedProperty().bind(imgEditFoto.visibleProperty());
        txtEditKonten.setVisible(visibility);
        txtEditKonten.managedProperty().bind(txtEditKonten.visibleProperty());      
    }
    
    private void updateKontenEduProperties(KontenEdukasi konten){
        lblJudul.setText(konten.getJudul());
        imgKonten.setImage(konten.getImagePath());
        txtContent.setText(konten.getContent());
    }

    @FXML
    public void pilihFoto(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                InputStream input = new FileInputStream(selectedFile.getAbsolutePath());
                imgEditFoto.setImage(new Image(input));
                input.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
    
