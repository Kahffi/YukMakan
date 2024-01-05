package controller;

import dao.BankDAO;
import dao.MetodePembayaranDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Bank;
import model.MetodePembayaran;
import utils.ImageUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

public class MetodePembayaranController implements Initializable {

    @FXML
    private Button btnAddMetodePembayaran;

    @FXML
    private Button btnChooseBankImg;

    @FXML
    private Button btnSaveBank;

    @FXML
    private VBox cardBankContainer;

    @FXML
    private ImageView imgBank;

    @FXML
    private MenuButton menuPilihanBank;

    @FXML
    private TextField txtInAtasNama;

    @FXML
    private TextField txtInRekening;

    @FXML
    private TextField txtNamaBankIn;
    @FXML
    private VBox cardMetodePembayaranContainer;
    @FXML
    private Button btnBackToDash;

    private ArrayList<Bank> bankList = new ArrayList<>();
    private Bank selectedBank;
    private ArrayList<MetodePembayaran> metodePembayaranList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bankList = BankDAO.getAllBanks();
        metodePembayaranList = MetodePembayaranDAO.getAllMetodePembayaran();
        initMetodePembayaranCards(metodePembayaranList);
        initBankCards(bankList);
        initMenuPilihanBank(bankList);

    }

    @FXML
    void chooseBankImg(ActionEvent event) {
        imgBank.setImage(ImageUtils.imageFileChooser());
    }

    @FXML
    void saveBank(ActionEvent event) {
        Bank newBank = new Bank(UUID.randomUUID(), txtNamaBankIn.getText(), imgBank.getImage());
        BankDAO.saveBank(newBank);
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/MetodePembayaran.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) btnSaveBank.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initBankCards(ArrayList<Bank> bankList) {
        cardBankContainer.getChildren().clear();
        for (Bank bank : bankList){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/CardBank.fxml"));
                HBox cardBank = loader.load();
                cardBank.setId(bank.getIdBank().toString());
                CardBankController controller = loader.getController();
                controller.setData(bank, bankList.indexOf(bank)+1);
                cardBankContainer.getChildren().add(cardBank);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
    private void initMetodePembayaranCards(ArrayList<MetodePembayaran> metodePembayaranList) {
        cardMetodePembayaranContainer.getChildren().clear();
        for (MetodePembayaran metodePembayaran : metodePembayaranList) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/CardMetodePembayaran.fxml"));
                HBox cardMetodePembayaran = loader.load();
                CardMetodePembayaranController controller = loader.getController();
                controller.setData(metodePembayaran, bankList, metodePembayaranList.indexOf(metodePembayaran) + 1);
                cardMetodePembayaranContainer.getChildren().add(cardMetodePembayaran);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void initMenuPilihanBank(ArrayList<Bank> bankList) {
        menuPilihanBank.getItems().clear();
        for (Bank bank : bankList){
            ImageView imgBank = new ImageView();
            imgBank.setImage(bank.getLogoBank());
            imgBank.setFitHeight(85);
            imgBank.setFitWidth(85);
            MenuItem bankSelection = new MenuItem(bank.getNamaBank(),imgBank);
            bankSelection.setId(bank.getIdBank().toString());
            bankSelection.setOnAction(event -> {
                ImageView image = new ImageView(bank.getLogoBank());
                image.setFitWidth(70);
                image.setFitHeight(70);
                this.selectedBank = bank;
                this.menuPilihanBank.setGraphic(image);
            });
            menuPilihanBank.getItems().add(bankSelection);
        }

    }

    @FXML
    void addMetodePembayaran(ActionEvent event) {
        if (selectedBank != null){
            MetodePembayaran m = new MetodePembayaran(UUID.randomUUID(), txtInRekening.getText(), txtInAtasNama.getText(), selectedBank);
            MetodePembayaranDAO.saveMetodePembayaran(m);
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/MetodePembayaran.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) btnAddMetodePembayaran.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    @FXML
    void backToDash(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) btnBackToDash.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
