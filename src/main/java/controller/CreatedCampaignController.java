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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class CreatedCampaignController implements Initializable {

    @FXML
    private VBox containerJudul;
    @FXML
    private TextField txtJudul;
    @FXML
    private TextArea txtDesc;
    @FXML
    private TextArea txtTanggal;
    @FXML
    private VBox containerGizi1;
    @FXML
    private TextArea txtTargetDonasi;
    @FXML
    private VBox containerGizi;
    @FXML
    private TextArea txtDonasi;
    @FXML
    private VBox containerFoto;
    @FXML
    private ImageView imageIn;
    @FXML
    private Button btnAddPhoto;
    @FXML
    private Button btnCreateCampaign;
    @FXML
    private Button btnLogout;
    @FXML
    private VBox containerDesc;
    @FXML
    private VBox containerTanggal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void pilihFoto(ActionEvent event) {
    }

    @FXML
    private void saveCampaign(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }
    
}
