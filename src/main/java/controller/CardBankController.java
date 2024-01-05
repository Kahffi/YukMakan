package controller;

import dao.BankDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Bank;
import utils.ImageUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CardBankController implements Initializable {

    @FXML
    private ImageView logoBank;

    @FXML
    private Label txtNamaBank;

    @FXML
    private Label urutanCard;
    @FXML
    private Button btnCancelEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSaveEdit;

    @FXML
    private Button btnStartEdit;
    @FXML
    private TextField txtInBankName;

    private Image oldImage;
    private Bank bank;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setEditVisibilty(false);
    }

    private void setEditVisibilty(boolean visibilty){
        btnCancelEdit.setVisible(visibilty); btnCancelEdit.managedProperty().bind(btnCancelEdit.visibleProperty());
        btnSaveEdit.setVisible(visibilty); btnSaveEdit.managedProperty().bind(btnSaveEdit.visibleProperty());
        txtInBankName.setVisible(visibilty); txtInBankName.managedProperty().bind(txtInBankName.visibleProperty());
        logoBank.setMouseTransparent(!visibilty);

        txtNamaBank.setVisible(!visibilty); txtNamaBank.managedProperty().bind(txtNamaBank.visibleProperty());
        btnDelete.setVisible(!visibilty); btnDelete.managedProperty().bind(btnDelete.visibleProperty());
        btnStartEdit.setVisible(!visibilty); btnStartEdit.managedProperty().bind(btnStartEdit.visibleProperty());
    }

    public void setData(Bank bank, int urutan){
        logoBank.setImage(bank.getLogoBank());
        txtNamaBank.setText(bank.getNamaBank());
        urutanCard.setText(String.valueOf(urutan));
        oldImage = bank.getLogoBank();
        this.bank = bank;
    }

    @FXML
    private void cancelEdit(ActionEvent event) {
        setEditVisibilty(false);
        logoBank.setImage(oldImage);
    }

    @FXML
    private void deleteBank(ActionEvent event) {
        BankDAO.deleteBank(this.bank);
        refreshPage();
    }

    @FXML
    private void saveEdit(ActionEvent event) {
        oldImage = logoBank.getImage();
        txtNamaBank.setText(txtInBankName.getText());
        setEditVisibilty(false);

    }

    @FXML
    private void startEdit(ActionEvent event) {
        txtInBankName.setText(txtNamaBank.getText());
        setEditVisibilty(true);
    }
    @FXML
    void editBankLogo(MouseEvent event) {
        Image image = ImageUtils.imageFileChooser();
        if (image != null){
            logoBank.setImage(image);
        }
    }

    private void refreshPage(){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/MetodePembayaran.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) btnStartEdit.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}