package controller;

import com.sun.security.jgss.GSSUtil;
import dao.AkunDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Resep;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResepDetailController implements Initializable {
    @FXML
    private Button btnAddFav;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnHapus;

    @FXML
    private ImageView imgResep;

    @FXML
    private Label lblJudul;

    @FXML
    private Text txtBahan;

    @FXML
    private Text txtDeskripsi;

    @FXML
    private Text txtGizi;

    @FXML
    private Text txtLangkah;
    @FXML
    private Button btnBack;

    private Resep resep;

    public ResepDetailController (Resep resep){
        this.resep = resep;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblJudul.setText(resep.getJudul());
        txtDeskripsi.setText(resep.getDeskripsi());
        txtBahan.setText(resep.getBahan());
        txtGizi.setText(resep.getKandunganGizi());
        txtLangkah.setText(resep.getLangkah());
        imgResep.setImage(resep.getFotoResep());
        imgResep.setFitWidth(600);
        imgResep.setFitHeight(400);

        if (SignUpController.isAdmin){
            btnAddFav.setVisible(false); btnAddFav.managedProperty().bind(btnAddFav.visibleProperty());
        }
        else{
            btnEdit.setVisible(false); btnEdit.managedProperty().bind(btnEdit.visibleProperty());
            btnHapus.setVisible(false); btnHapus.managedProperty().bind(btnHapus.visibleProperty());
        }
    }

    @FXML
    void addToFav(ActionEvent event) {
        int i = AkunDAO.addFavorite(this.resep.getId().toString(), SignUpController.user.getUsername());
        if (i>0){
            System.out.println("sukses menambahkan ke daftar favorit");
        }
        else {
            System.out.println("Resep sudah ada di daftar favorit");
        }
        SignUpController.user.addToFav(this.resep);
        for(Resep resep: SignUpController.user.getDaftarFavorit()){
            System.out.println(resep.getJudul());
        }
    }

    @FXML
    void deleteResep(ActionEvent event) {

    }

    @FXML
    void startEditResep(ActionEvent event) {

    }
    @FXML
    public void backToDash(ActionEvent event){
        Stage stage = (Stage) btnBack.getScene().getWindow();
        Parent parent = null;
        try {
            parent = new FXMLLoader().load(getClass().getResource("../view/Dashboard.fxml"));
            Scene scene = new Scene(parent);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
