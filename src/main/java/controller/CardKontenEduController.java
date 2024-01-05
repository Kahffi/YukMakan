/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.KontenEdukasi;


/**
 * FXML Controller class
 *
 * @author Asus
 */
public class CardKontenEduController{

    @FXML
    private VBox cardContainer;
    @FXML
    private ImageView fotoKonten;
    @FXML
    private Label judulKonten;
    @FXML
    private Label namaAdmin;
    @FXML
    private Label tanggal;
    @FXML
    private Label descKonten;
    private KontenEdukasi konten;

    
    public void setData(KontenEdukasi konten){
            this.konten = konten;
        judulKonten.setText(konten.getJudul());
        descKonten.setText(konten.getContent());
        namaAdmin.setText(konten.getUploader().getUsername());
        tanggal.setText(konten.getTanggal());
        fotoKonten.setImage(konten.getImagePath());
    }

    @FXML
    void toKontenEduDetail(){
        try {
            System.out.println("judul: " + konten.getJudul());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/KontenEduDetail.fxml"));
            loader.setController(new KontenEduDetailController(konten));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            
            Stage stage = (Stage) namaAdmin.getScene().getWindow();
            stage.setTitle("YukMakan - Konten Edukasi Detail");
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();        
        }
    }
    
}
