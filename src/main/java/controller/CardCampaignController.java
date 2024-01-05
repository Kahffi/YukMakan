package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Campaign;

import java.io.IOException;


public class CardCampaignController {

    @FXML
    private VBox cardContainer;

    @FXML
    private Label currentDonation;

    @FXML
    private Text deskripsiCampaign;

    @FXML
    private ProgressBar donationProgress;

    @FXML
    private Label donationTarget;

    @FXML
    private Label endDate;

    @FXML
    private ImageView fotoCampaign;

    @FXML
    private Text judulCampaign;

    @FXML
    private Label namaAdmin;

    @FXML
    private Label tanggal;

    Campaign campaign;
    public void setData(Campaign campaign) {
        this.campaign = campaign;
        this.deskripsiCampaign.setText(campaign.getDeskripsi());
        this.judulCampaign.setText(campaign.getJudul());
        this.donationTarget.setText(campaign.getFormattedTargetDonasi());
        this.currentDonation.setText(campaign.getFormattedCurrentDonasi());
        this.donationProgress.setProgress(campaign.getDonationProgress());
        System.out.println(this.donationProgress.getProgress());
        this.endDate.setText(campaign.getEndDate());
        this.fotoCampaign.setImage(campaign.getCampaignImage());
        this.namaAdmin.setText(campaign.getCreator().getUsername());
        this.tanggal.setText(campaign.getTanggal());
    }
    @FXML
    void toResepDetail(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/CampaignDetail.fxml"));
        try {
            Parent parent = loader.load();
            CampaignDetailController controller = loader.getController();
            controller.initialize(this.campaign);
            Scene scene = new Scene(parent);
            Stage stage = (Stage) cardContainer.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
