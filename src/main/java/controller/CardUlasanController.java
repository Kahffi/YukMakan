package controller;

import dao.UlasanDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Resep;
import model.Ulasan;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class CardUlasanController{
    @FXML
    private TextArea txtEditUlasan;
    @FXML
    private Button btnCancelEdit;

    @FXML
    private Button btnDeleteUlasan;

    @FXML
    private Button btnSaveEdit;

    @FXML
    private Button btnStartEdit;


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

    private UUID id;
    private Ulasan ulasan;
    private Resep resep;
    public void setData(Ulasan ulasan, Resep resep) {
        this.resep = resep;
        this.ulasan = ulasan;
        System.out.println("ini rating" + ulasan.getRating());
        System.out.println(ulasan.getTanggalUlasan());
        ratingUlasan.setRating(ulasan.getRating());
        txtUlasan.setText(ulasan.getUlasan());
        username.setText(ulasan.getUser().getUsername());
        tanggalUlasan.setText(ulasan.getTanggalUlasan());
        id = ulasan.getId();
        if (ulasan.getUser().getProfilePict() != null) {
            imgUser.setFill(new ImagePattern(ulasan.getUser().getProfilePict()));
        }
    }

    @FXML
    void deleteUlasan(ActionEvent event) {
        try {
            UlasanDAO.delUlasan(this.ulasan);
            FXMLLoader fxmlLoader = new FXMLLoader();
            ResepDetailController resepDetailController = new ResepDetailController(this.resep);
            fxmlLoader.setLocation(getClass().getResource("/view/ResepDetail.fxml"));
            fxmlLoader.setController(resepDetailController);
            Parent parent = fxmlLoader.load();
            Stage stage = (Stage)this.txtEditUlasan.getScene().getWindow();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void startEdit(ActionEvent event) {
        txtEditUlasan.setText(txtUlasan.getText());
        setEditVisibility(true);
    }

    @FXML
    void cancelEdit(ActionEvent event) {
        txtEditUlasan.clear();
        setEditVisibility(false);
    }

    @FXML
    void confirmUpdate(ActionEvent event) {
        this.ulasan.setUlasan(txtEditUlasan.getText());
        this.ulasan.setRating((int)ratingUlasan.getRating());
        txtUlasan.setText(txtEditUlasan.getText());
        UlasanDAO.editUlasan(this.ulasan);
        setEditVisibility(false);
    }

    public void setEditVisibility(boolean visibility){
        btnCancelEdit.setVisible(visibility); btnCancelEdit.managedProperty().bind(btnCancelEdit.visibleProperty());
        btnSaveEdit.setVisible(visibility); btnSaveEdit.managedProperty().bind(btnSaveEdit.visibleProperty());
        txtEditUlasan.setVisible(visibility); txtEditUlasan.managedProperty().bind(txtEditUlasan.visibleProperty());
        ratingUlasan.setMouseTransparent(visibility);

        btnStartEdit.setVisible(!visibility); btnStartEdit.managedProperty().bind(btnStartEdit.visibleProperty());
        btnDeleteUlasan.setVisible(!visibility); btnDeleteUlasan.managedProperty().bind(btnDeleteUlasan.visibleProperty());
    }

    public void setEditBtnVisibility(boolean visibility){
        btnStartEdit.setVisible(visibility); btnStartEdit.managedProperty().bind(btnStartEdit.visibleProperty());
        btnDeleteUlasan.setVisible(visibility); btnDeleteUlasan.managedProperty().bind(btnDeleteUlasan.visibleProperty());
    }
}
