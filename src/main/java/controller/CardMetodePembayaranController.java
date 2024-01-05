package controller;

import dao.BankDAO;
import dao.MetodePembayaranDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Bank;
import model.MetodePembayaran;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CardMetodePembayaranController implements Initializable {

    @FXML
    private Button btnCancelEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSaveEdit;

    @FXML
    private Button btnStartEdit;

    @FXML
    private ImageView logoBank;

    @FXML
    private TextField txtInAtasNama;

    @FXML
    private TextField txtInNoRek;

    @FXML
    private Label urutanCard;
    @FXML
    private MenuButton menuEditBank;
    @FXML
    private MetodePembayaran metodePembayaran;

    private Bank selectedBank;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setEditVisibilty(false);
    }

    public void setData(MetodePembayaran metodePembayaran, ArrayList<Bank> bankList, int urutan) {
        this.metodePembayaran = metodePembayaran;
        menuEditBank.setText(metodePembayaran.getBank().getNamaBank());
        urutanCard.setText(String.valueOf(urutan));
        txtInAtasNama.setText(metodePembayaran.getAtasNama());
        txtInNoRek.setText(metodePembayaran.getNomorRekening());
        logoBank.setImage(metodePembayaran.getBank().getLogoBank());
        initMenuEditBank(bankList);
    }

    private void initMenuEditBank(ArrayList<Bank> bankList){
        menuEditBank.getItems().clear();
        for (Bank bank : bankList) {
            ImageView itemImage = new ImageView(bank.getLogoBank());
            itemImage.setFitHeight(85);
            itemImage.setFitWidth(85);
            MenuItem menuItem = new MenuItem(bank.getNamaBank(), itemImage);
            menuItem.setOnAction(event -> {
                selectedBank = bank;
                ImageView logoBank = new ImageView(bank.getLogoBank());
                logoBank.setFitWidth(85);
                logoBank.setFitHeight(85);
                menuEditBank.setText(bank.getNamaBank());
                menuEditBank.setGraphic(logoBank);
            });
            menuEditBank.getItems().add(menuItem);
        }
    }

    @FXML
    void cancelEdit(ActionEvent event) {
        selectedBank = null;
        menuEditBank.setGraphic(logoBank);
        menuEditBank.setText(metodePembayaran.getBank().getNamaBank());
        setEditVisibilty(false);

    }

    @FXML
    void deleteBank(ActionEvent event) {

    }

    @FXML
    void saveEdit(ActionEvent event) {
        if (selectedBank!= null){
            metodePembayaran.setBank(selectedBank);
            MetodePembayaranDAO.updateMetodePembayaran(metodePembayaran);
            setEditVisibilty(false);
        }
        else {
            MetodePembayaranDAO.updateMetodePembayaran(metodePembayaran);
            setEditVisibilty(false);
        }

    }
    @FXML
    void startEdit(ActionEvent event) {
        setEditVisibilty(true);
    }

    private void setEditVisibilty(boolean visibilty){
        btnCancelEdit.setVisible(visibilty); btnCancelEdit.managedProperty().bind(btnCancelEdit.visibleProperty());
        btnSaveEdit.setVisible(visibilty); btnSaveEdit.managedProperty().bind(btnSaveEdit.visibleProperty());
        txtInAtasNama.setMouseTransparent(!visibilty);
        menuEditBank.setMouseTransparent(!visibilty);
        txtInNoRek.setMouseTransparent(!visibilty);

        btnDelete.setVisible(!visibilty); btnDelete.managedProperty().bind(btnDelete.visibleProperty());
        btnStartEdit.setVisible(!visibilty); btnStartEdit.managedProperty().bind(btnStartEdit.visibleProperty());
    }
}
