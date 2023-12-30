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
import javafx.stage.Stage;

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
    private Button btnAddFav;
    @FXML
    private Button btnHapus;
    private KontenEdukasi konten;
    @FXML
    private ImageView imgKonten;
    @FXML
    private Button btnBack;

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
        if (SignUpController.isAdmin){
            btnAddFav.setVisible(false); btnAddFav.managedProperty().bind(btnAddFav.visibleProperty());
        }
        else{
            btnEdit.setVisible(false); btnEdit.managedProperty().bind(btnEdit.visibleProperty());
            btnHapus.setVisible(false); btnHapus.managedProperty().bind(btnHapus.visibleProperty());
        }
    }
   
    


   

    @FXML
    private void EditKonten(ActionEvent event){
        
    }

    @FXML
    private void deleteKonten(ActionEvent event){
    }

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
   }
    
