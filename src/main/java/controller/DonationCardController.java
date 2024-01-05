package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import model.DonationLog;


public class DonationCardController {

    @FXML
    private Text txtIdCampaign;

    @FXML
    private Text txtIdTransaksi;

    @FXML
    private Text txtNominalDonasi;

    @FXML
    private Label txtUsername;
    @FXML
    private Circle userProfilePict;
    @FXML
    private ImageView imgPayment;
    public void initialize(DonationLog donationLog){
        userProfilePict.setFill(new ImagePattern(donationLog.getDonor().getProfilePict()));
        txtUsername.setText(donationLog.getDonor().getUsername());
        txtIdCampaign.setText("ID Campaign:\n" + donationLog.getCampaignID().toString());
        txtIdTransaksi.setText("ID Transaksi:\n" + donationLog.getId().toString());
        txtNominalDonasi.setText("Nominal Donasi:\n" + donationLog.getFormattedAmount());
        imgPayment.setImage(donationLog.getMetodePembayaran().getBank().getLogoBank());
    }

}
