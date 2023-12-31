package controller;

import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import model.Ulasan;
import org.controlsfx.control.Rating;

import java.net.URL;
import java.util.ResourceBundle;

public class CardUlasanController{

    @FXML
    private VBox cardContainer;

    @FXML
    private Circle imgUser;

    @FXML
    private Rating ratingUlasan;

    @FXML
    private Label tanggalUlasan;

    @FXML
    private Text txtUlasan;

    @FXML
    private Label username;

    public void setData(Ulasan ulasan) {
        System.out.println("ini rating" + ulasan.getRating());
        System.out.println(ulasan.getTanggalUlasan());
        ratingUlasan.setRating(ulasan.getRating());
        txtUlasan.setText(ulasan.getUlasan());
        username.setText(ulasan.getUser().getUsername());
        tanggalUlasan.setText(ulasan.getTanggalUlasan());
        if (ulasan.getUser().getProfilePict() != null) {
            imgUser.setFill(new ImagePattern(ulasan.getUser().getProfilePict()));
        }
    }
}
