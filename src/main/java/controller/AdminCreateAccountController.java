package controller;

import dao.AkunDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.Admin;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static dao.AkunDAO.getAdmin;
import static dao.AkunDAO.registerAccount;

public class AdminCreateAccountController implements Initializable {

    @FXML
    private ImageView btnBackToDash;

    @FXML
    private Button btnDaftar;

    @FXML
    private HBox formEmail;

    @FXML
    private HBox formName;

    @FXML
    private HBox formPass;

    @FXML
    private HBox formPhone;

    @FXML
    private HBox formUsn;

    @FXML
    private Label lblSuccess;

    @FXML
    private HBox toDaftarContainer;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPass;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtUsn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblSuccess.setVisible(false); lblSuccess.managedProperty().bind(lblSuccess.visibleProperty());

    }

    @FXML
    void proceedDaftar(ActionEvent event) {
        Admin admin;
        String username = txtUsn.getText();
        String pass = txtPass.getText();
        String phoneNum = txtPhone.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String role = "admin";
        if (!username.isBlank() && !pass.isBlank() && !name.isBlank() && !email.isBlank() && !phoneNum.isBlank()) {
            if (getAdmin(username) == null){
                registerAccount(username, pass, name, phoneNum, email, role);
                lblSuccess.setVisible(true);
                lblSuccess.setText("Akun Berhasil Didaftarkan");
                lblSuccess.setTextFill(Paint.valueOf("black"));
            }
            else{
                lblSuccess.setVisible(true);
                lblSuccess.setText("Username sudah digunakan");
                lblSuccess.setTextFill(Paint.valueOf("red"));
            }
        }
        else {
            lblSuccess.setVisible(true);
            lblSuccess.setText("Tidak boleh ada yang kosong");
            lblSuccess.setTextFill(Paint.valueOf("red"));
        }
    }

    @FXML
    public void backToDash(ActionEvent event){
        Stage stage = (Stage) txtEmail.getScene().getWindow();
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
