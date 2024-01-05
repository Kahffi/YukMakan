package controller;

import dao.BaseDAO;
import dao.CampaignDAO;
import dao.MetodePembayaranDAO;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Campaign;
import model.MetodePembayaran;
import utils.ImageUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

import static controller.SignUpController.admin;

public class AdminCreateCampaignController implements Initializable {

    @FXML
    public TextField txtTargetDonasi;
    @FXML
    private Button btnAddPhoto;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCreateResep;

    @FXML
    private Button btnLogout;

    @FXML
    private VBox containerDeskripsi;

    @FXML
    private VBox containerFoto;

    @FXML
    private VBox containerFoto1;

    @FXML
    private VBox containerJudul;

    @FXML
    private ImageView imageIn;

    @FXML
    private Label metodePembayaranBankName;

    @FXML
    private HBox metodePembayaranContainer;

    @FXML
    private ImageView metodePembayaranImageLayout;

    @FXML
    private Label metodePembayaranNama;

    @FXML
    private Label metodePembayaranRekening;

    @FXML
    private TextArea txtDeskripsi;

    @FXML
    private TextField txtJudul;
    @FXML
    private VBox cardContainer;
    @FXML
    private DatePicker campaignEndDate;
    private final ArrayList <MetodePembayaran>selectedMetodePembayaran = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtTargetDonasi.addEventFilter(KeyEvent.KEY_TYPED, e -> {
            String character = e.getCharacter();
            if (!character.matches("[0-9]")) {
                e.consume(); // Consume the event to prevent the character from being entered
            }
        });
        ArrayList<MetodePembayaran> listMetodePembayaran = MetodePembayaranDAO.getAllMetodePembayaran();
        initMetodePembayaran(listMetodePembayaran);

    }

    private void initMetodePembayaran(ArrayList <MetodePembayaran> listMetodePembayaran){
        cardContainer.getChildren().clear();
        for (MetodePembayaran m : listMetodePembayaran){
            HBox hbox = setMetodePembayaranCardData(m);
            cardContainer.getChildren().add(hbox);
        }
    }
    private HBox setMetodePembayaranCardData(MetodePembayaran m){
        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: white;" +
                "-fx-border-color: black;" + "-fx-alignment: center-left;" +"-fx-spacing: 10");

        ImageView bankLogo = new ImageView();
        bankLogo.setFitWidth(80);
        bankLogo.setFitHeight(80);
        bankLogo.setImage(m.getBank().getLogoBank());
        hbox.getChildren().add(bankLogo);

        Label bankName = new Label();
        bankName.setText(m.getBank().getNamaBank());
        bankName.setPrefWidth(122);
        bankName.setPrefHeight(50);
        bankName.setWrapText(true);
        hbox.getChildren().add(bankName);

        Label rekening = new Label();
        rekening.setText(m.getNomorRekening());
        rekening.setPrefWidth(122);
        rekening.setPrefHeight(50);
        rekening.setWrapText(true);
        hbox.getChildren().add(rekening);

        Label atasNama = new Label();
        atasNama.setText(m.getAtasNama());
        atasNama.setPrefWidth(122);
        atasNama.setPrefHeight(50);
        atasNama.setWrapText(true);
        hbox.getChildren().add(atasNama);

        CheckBox checkBox = new CheckBox();
        checkBox.setMouseTransparent(false);
        checkBox.setOnAction(e -> {
            if (checkBox.isSelected()) {
                selectedMetodePembayaran.add(m);
            } else {
                selectedMetodePembayaran.remove(m);
            }
            System.out.println(selectedMetodePembayaran);
        });
        hbox.getChildren().add(checkBox);

        return hbox;
    }

    @FXML
    void backToDash(ActionEvent event) {
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
    void logout(ActionEvent event) {
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
    void pilihFoto(ActionEvent event) {
        Image image = ImageUtils.imageFileChooser();
        if (image != null) imageIn.setImage(image);
    }

    @FXML
    public void saveCampaign(ActionEvent event) {
        //TODO: benerin constructor ini tanggal selesainya salah
        Campaign campaign = new Campaign(txtJudul.getText(), admin, txtDeskripsi.getText(), admin.getDate(),campaignEndDate.getValue().toString(),imageIn.getImage(),
                UUID.randomUUID(),Integer.parseInt(txtTargetDonasi.getText()),0 ,selectedMetodePembayaran, null);
        CampaignDAO.saveCampaign(campaign);
    }
}
