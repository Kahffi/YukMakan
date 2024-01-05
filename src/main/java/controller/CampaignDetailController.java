package controller;

import dao.CampaignDAO;
import dao.MetodePembayaranDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Campaign;
import model.DonationLog;
import model.MetodePembayaran;
import utils.ImageUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class CampaignDetailController{


    @FXML
    private ImageView btnBackToDash;

    @FXML
    private Button btnCancelEdit;

    @FXML
    private Button btnConfirmDonation;

    @FXML
    private Button btnConfirmEdit;

    @FXML
    private Button btnHapusCampaign;

    @FXML
    private Button btnStartEdit;

    @FXML
    private ProgressBar donationProgress;

    @FXML
    private VBox editDeskripsiContainer;

    @FXML
    private ImageView imgCampaign;

    @FXML
    private MenuButton menuMetodePembayaran;

    @FXML
    private Label txtCurrentDonasi;

    @FXML
    private Text txtDeskripsiCampaign;

    @FXML
    private TextArea txtEditDeskripsi;

    @FXML
    private Label txtJudulCampaign;

    @FXML
    private TextField txtNominalDonasi;

    @FXML
    private Label txtTargetDonasi;

    @FXML
    private VBox donationContainer;
    @FXML
    private Button btnViewLog;
    @FXML
    private TextField txtEditJudul;

    private MetodePembayaran selectedMetodePembayaran;
    private Campaign campaign;
    private Image tempImage;

    public void initialize(Campaign campaign){
        this.campaign = campaign;
        imgCampaign.setImage(campaign.getCampaignImage());
        txtJudulCampaign.setText(campaign.getJudul());
        txtTargetDonasi.setText(campaign.getFormattedTargetDonasi());
        txtCurrentDonasi.setText(campaign.getFormattedCurrentDonasi() + " Terkumpul");
        txtDeskripsiCampaign.setText(campaign.getDeskripsi());
        donationProgress.setProgress(campaign.getDonationProgress());
        initPilihanMetodePembayaran();
        setEditVisibility(false);
        txtNominalDonasi.addEventFilter(KeyEvent.KEY_TYPED, e -> {
            String character = e.getCharacter();
            if (!character.matches("[0-9]")) {
                e.consume(); // Consume the event to prevent the character from being entered
            }
        });
        if (!SignUpController.isAdmin){
            btnStartEdit.setVisible(false); btnStartEdit.managedProperty().bind(btnStartEdit.visibleProperty());
            btnHapusCampaign.setVisible(false); btnHapusCampaign.managedProperty().bind(btnHapusCampaign.visibleProperty());
            btnViewLog.setVisible(false); btnViewLog.managedProperty().bind(btnViewLog.visibleProperty());
        }
        else {
            donationContainer.setVisible(false); donationContainer.managedProperty().bind(donationContainer.visibleProperty());
        }

    }

    private void initPilihanMetodePembayaran(){
        menuMetodePembayaran.getItems().clear();
        ArrayList<MetodePembayaran> metodePembayaranList = MetodePembayaranDAO.getAllAvailablePayment(this.campaign.getId());
        System.out.println(metodePembayaranList.size());
        for (MetodePembayaran m : metodePembayaranList){
            ImageView bankLogo = new ImageView(m.getBank().getLogoBank());
            bankLogo.setFitWidth(50);
            bankLogo.setFitHeight(50);
            MenuItem metodePembayaran = new MenuItem();
            metodePembayaran.setGraphic(bankLogo);
            metodePembayaran.setText(m.getBank().getNamaBank() + " - " + m.getNomorRekening() + " (" + m.getAtasNama() + ")");
            metodePembayaran.setOnAction(e -> {
                selectedMetodePembayaran = m;
                ImageView image = new ImageView(m.getBank().getLogoBank());
                image.setFitWidth(50);
                image.setFitHeight(50);
                menuMetodePembayaran.setText(metodePembayaran.getText());
                menuMetodePembayaran.setGraphic(image);
            });
            menuMetodePembayaran.getItems().add(metodePembayaran);
        }
    }

    @FXML
    void backToDash(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) txtNominalDonasi.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void confirmDonation(ActionEvent event) {
        if (selectedMetodePembayaran != null && Integer.parseInt(txtNominalDonasi.getText()) >= 0
                &&  Integer.parseInt(txtNominalDonasi.getText()) <= (campaign.getTargetDonasi() - campaign.getCurrentDonasi())){
            DonationLog donasi;
            int nominal = Integer.parseInt(txtNominalDonasi.getText());
            donasi = new DonationLog(SignUpController.user, nominal, SignUpController.user.getDate(), UUID.randomUUID().toString(),
                    this.selectedMetodePembayaran, this.campaign.getId());
            CampaignDAO.saveDonation(donasi);
            return;
        }
        System.out.println("gagal melakukan transaksi");


    }

    @FXML
    void editImageCampaign(MouseEvent event) {
        Image selectedImage = ImageUtils.imageFileChooser();
        tempImage = imgCampaign.getImage();
        setEditVisibility(true);
        imgCampaign.setImage(selectedImage);
    }

    @FXML
    void cancelEdit(ActionEvent event) {
        imgCampaign.setImage(tempImage);
        txtEditDeskripsi.clear();
        setEditVisibility(false);

    }
    @FXML
    void deleteCampaign(ActionEvent event) {
        CampaignDAO.delCampaign(this.campaign);
        backToDash(event);
    }
    @FXML
    void saveEdit(ActionEvent event) {
        this.campaign.setCampaignImage(imgCampaign.getImage());
        this.campaign.setDeskripsi(txtEditDeskripsi.getText());
        this.campaign.setJudul(txtEditJudul.getText());
        CampaignDAO.updateCampaign(this.campaign);
//       Untuk merefreseh halaman
        initialize(CampaignDAO.getCampaign(this.campaign.getId()));
    }

    @FXML
    void startEdit(ActionEvent event) {
        txtEditJudul.setText(this.campaign.getJudul());
        txtEditDeskripsi.setText(this.campaign.getDeskripsi());
        tempImage = imgCampaign.getImage();
        setEditVisibility(true);
        txtEditDeskripsi.setText(this.campaign.getDeskripsi());
    }
    private void setEditVisibility(boolean visibility){
        editDeskripsiContainer.setVisible(visibility); editDeskripsiContainer.managedProperty().bind(editDeskripsiContainer.visibleProperty());
        btnCancelEdit.setVisible(visibility); btnCancelEdit.managedProperty().bind(btnCancelEdit.visibleProperty());
        btnConfirmEdit.setVisible(visibility); btnConfirmEdit.managedProperty().bind(btnConfirmEdit.visibleProperty());
        imgCampaign.setMouseTransparent(!visibility);
        txtEditJudul.setVisible(visibility); txtEditJudul.managedProperty().bind(txtEditJudul.visibleProperty());

        txtJudulCampaign.setVisible(!visibility); txtJudulCampaign.managedProperty().bind(txtJudulCampaign.visibleProperty());
        txtDeskripsiCampaign.setVisible(!visibility); txtDeskripsiCampaign.managedProperty().bind(txtDeskripsiCampaign.visibleProperty());
        btnStartEdit.setVisible(!visibility); btnStartEdit.managedProperty().bind(btnStartEdit.visibleProperty());
        btnHapusCampaign.setVisible(!visibility); btnHapusCampaign.managedProperty().bind(btnHapusCampaign.visibleProperty());
    }

    @FXML
    private void showDonationLog(ActionEvent e){
        VBox donationContainer = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxWidth(800);
        scrollPane.setMaxHeight(600);
        scrollPane.setContent(donationContainer);
        Scene scene = new Scene(scrollPane);
        Stage logStage = new Stage();
        logStage.setScene(scene);
        logStage.setTitle("Riwayat Donasi - " + this.campaign.getId().toString());
        logStage.setHeight(600);
        logStage.setWidth(800);
        logStage.show();

        for (DonationLog donation : this.campaign.getDonationLogs()){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/DonationCard.fxml"));
            try {
                HBox box = loader.load();
                DonationCardController controller = loader.getController();
                controller.initialize(donation);
                donationContainer.getChildren().add(box);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
