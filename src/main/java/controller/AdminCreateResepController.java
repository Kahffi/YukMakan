package controller;

import dao.ResepDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Resep;
import utils.ImageUtils;

import static controller.SignUpController.admin;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.UUID;

public class AdminCreateResepController implements Initializable {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogout;

    @FXML
    private ImageView imageIn;
    @FXML
    private Button btnCreateResep;

    @FXML
    private VBox containerBahan;

    @FXML
    private VBox containerLangkah;

    @FXML
    private VBox containerDeskripsi;

    @FXML
    private VBox containerGizi;

    @FXML
    private VBox containerJudul;

    @FXML
    private TextArea txtBahan;

    @FXML
    private TextArea txtDeskripsi;

    @FXML
    private TextArea txtGizi;

    @FXML
    private TextField txtJudul;

    @FXML
    private TextArea txtLangkah;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void backToDash(){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void saveResep(ActionEvent event) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime current = LocalDateTime.now();
        Resep resep;
        String bahan, langkah, deskripsi, gizi, judul, date;
        date = dtf.format(current);
        bahan = txtBahan.getText();
        langkah = txtLangkah.getText();
        deskripsi = txtDeskripsi.getText();
        gizi = txtGizi.getText();
        judul = txtJudul.getText();
        resep = new Resep(UUID.randomUUID(), judul, deskripsi, langkah, bahan, gizi,imageIn.getImage(),date, admin);
        imageIn.setImage(resep.getFotoResep());
        ResepDAO.saveResep(resep);

    }
    @FXML
    public void pilihFoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                InputStream input = new FileInputStream(selectedFile.getAbsolutePath());
                imageIn.setImage(new Image(input));
                input.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    public void logout(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/SignUp.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            admin = null;
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
