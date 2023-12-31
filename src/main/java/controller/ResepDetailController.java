package controller;

import dao.AkunDAO;
import dao.ResepDAO;
import dao.UlasanDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Resep;
import model.Ulasan;
import model.User;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

public class ResepDetailController implements Initializable {
    @FXML
    private VBox ulasanCardsLayout;
    @FXML
    private Rating ratingUlasan;
    @FXML
    private TextField txtInUlasan;
    @FXML
    private Button btnCancelUlasan;
    @FXML
    private Button btnSaveUlasan;
    @FXML
    private TextArea txtEditBahan;

    @FXML
    private TextArea txtEditDeskripsi;

    @FXML
    private TextArea txtEditGizi;

    @FXML
    private TextField txtEditJudul;

    @FXML
    private TextArea txtEditLangkah;

    @FXML
    private Button btnCancel;
    @FXML
    private Button btnAddFav;
    @FXML
    private Button btnSaveUpdate;

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
    private ArrayList <Ulasan> ulasanList = new ArrayList<>();

    public ResepDetailController (Resep resep){
        this.resep = resep;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setEditVisibility(false);
        initUlasanCards();
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
        setEditVisibility(true);
        txtEditBahan.setText(txtBahan.getText());
        txtEditDeskripsi.setText(txtDeskripsi.getText());
        txtEditGizi.setText(txtGizi.getText());
        txtEditJudul.setText(lblJudul.getText());
        txtEditLangkah.setText(txtLangkah.getText());
    }
    @FXML
    public void saveUpdate(ActionEvent event){
        String updatedBahan, updatedDeskripsi, updatedGizi, updatedJudul, updatedLangkah;
        updatedBahan = txtEditBahan.getText(); updatedDeskripsi = txtEditDeskripsi.getText(); updatedGizi = txtEditGizi.getText();
        updatedJudul = txtEditJudul.getText();updatedLangkah = txtEditLangkah.getText();

        if (updatedDeskripsi.isEmpty() || updatedBahan.isEmpty() || updatedGizi.isEmpty() || updatedJudul.isEmpty() || updatedLangkah.isEmpty()){
            System.out.println("data tidak boleh ada yang kosong");
        }
        else {
            resep.setBahan(updatedBahan);
            resep.setDeskripsi(updatedDeskripsi);
            resep.setKandunganGizi(updatedGizi);
            resep.setJudul(updatedJudul);
            resep.setLangkah(updatedLangkah);
            ResepDAO.updateResep(resep);
            updateResepProperties(resep);
        }
        setEditVisibility(false);
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

    @FXML
    public void cancelEdit(ActionEvent event){
        setEditVisibility(false);
    }
    @FXML
    void cancelUlasan(ActionEvent event) {
        txtInUlasan.clear();
        ratingUlasan.setRating(0);
    }
    @FXML
    void saveUlasan(ActionEvent event) {
        User currentUser = SignUpController.user;
        String isi = txtInUlasan.getText();
        int rating = (int) ratingUlasan.getRating();
        UUID id = UUID.randomUUID();
        Ulasan ulasan = new Ulasan(currentUser, isi, SignUpController.user.getDate(),rating, id, this.resep.getId());
        UlasanDAO.saveUlasan(ulasan);
        initUlasanCards();


    }


    // method untuk mengatur visibilitas saat berinteraksi dengan tombol edit
    private void setEditVisibility(Boolean visibility){
        btnCancelUlasan.setVisible(!visibility); btnCancelUlasan.managedProperty().bind(btnCancelUlasan.visibleProperty());
        btnSaveUlasan.setVisible(!visibility); btnSaveUlasan.managedProperty().bind(btnSaveUlasan.visibleProperty());
        txtBahan.setVisible(!visibility); txtBahan.managedProperty().bind(txtBahan.visibleProperty());
        txtDeskripsi.setVisible(!visibility); txtDeskripsi.managedProperty().bind(txtDeskripsi.visibleProperty());
        txtGizi.setVisible(!visibility); txtGizi.managedProperty().bind(txtGizi.visibleProperty());
        txtLangkah.setVisible(!visibility); txtLangkah.managedProperty().bind(txtLangkah.visibleProperty());
        lblJudul.setVisible(!visibility); lblJudul.managedProperty().bind(lblJudul.visibleProperty());

        btnSaveUpdate.setVisible(visibility); btnSaveUpdate.managedProperty().bind(btnSaveUpdate.visibleProperty());
        btnCancel.setVisible(visibility); btnCancel.managedProperty().bind(btnCancel.visibleProperty());
        txtEditBahan.setVisible(visibility); txtEditBahan.managedProperty().bind(txtEditBahan.visibleProperty());
        txtEditDeskripsi.setVisible(visibility); txtEditDeskripsi.managedProperty().bind(txtEditDeskripsi.visibleProperty());
        txtEditGizi.setVisible(visibility); txtEditGizi.managedProperty().bind(txtEditGizi.visibleProperty());
        txtEditLangkah.setVisible(visibility); txtEditLangkah.managedProperty().bind(txtEditLangkah.visibleProperty());
        txtEditJudul.setVisible(visibility); txtEditJudul.managedProperty().bind(txtEditJudul.visibleProperty());
    }

    private void initUlasanCards(){
        this.ulasanList = UlasanDAO.getAllUlasan(resep.getId().toString());
        for (Ulasan ulasan : ulasanList) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/CardUlasan.fxml"));
                VBox ulasanBox = fxmlLoader.load();
                CardUlasanController cardUlasanController = fxmlLoader.getController();
                cardUlasanController.setData(ulasan);
                fxmlLoader.setController(cardUlasanController);
                ulasanCardsLayout.getChildren().add(ulasanBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    private void updateResepProperties(Resep resep){
        txtBahan.setText(resep.getBahan());
        txtDeskripsi.setText(resep.getDeskripsi());
        txtGizi.setText(resep.getKandunganGizi());
        txtLangkah.setText(resep.getLangkah());
        lblJudul.setText(resep.getJudul());
    }
}
