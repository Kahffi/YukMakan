package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Resep;

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

    }
}
