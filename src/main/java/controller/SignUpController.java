/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import static dao.AkunDAO.getAccRole;
import static dao.AkunDAO.getAdmin;
import static dao.AkunDAO.getUser;
import static dao.AkunDAO.registerAccount;
import static dao.AkunDAO.validateAcc;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import model.Akun;
import model.User;
import model.Admin;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Kahffi
 */
public class SignUpController implements Initializable {
	public static boolean isAdmin;
	public static User user;
	public static Admin admin;

	@FXML
    private Button btnDaftar;

    @FXML
    private Button btnToDaftar;

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
    private Label lblErr;

    @FXML
    private HBox toDaftarContainer;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPass;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtUsn;
    
    @FXML
    private Button btnBackToLogin;
    

  
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		btnDaftar.setText("LOGIN");
		formEmail.setVisible(false); formEmail.managedProperty().bind(formEmail.visibleProperty());
		formName.setVisible(false); formName.managedProperty().bind(formEmail.visibleProperty());
		formPhone.setVisible(false); formPhone.managedProperty().bind(formPhone.visibleProperty());
		lblErr.setVisible(false); lblErr.managedProperty().bind(lblErr.visibleProperty());
		btnBackToLogin.setVisible(false); btnBackToLogin.managedProperty().bind(btnBackToLogin.visibleProperty());
	}
    
    @FXML
    void backToLogin(ActionEvent event) {
    	btnDaftar.setText("LOGIN");
    	emptyAllTxtField();
		formEmail.setVisible(false);lblErr.setVisible(false); formName.setVisible(false); formPhone.setVisible(false);
		toDaftarContainer.setVisible(true);
		btnBackToLogin.setVisible(false);
    }
    
    @FXML
    void proceedLogin(ActionEvent event) {
    	
    	// kondisi untuk login
    	if (btnDaftar.getText().equals("LOGIN")) {
    		String usn, pass;
    		usn = txtUsn.getText(); 
    		pass = txtPass.getText();
    		
    		// login sebagai admin
    		if (getAccRole(usn).equals("admin")) {
    			if (validateAcc(usn,pass)) {
    				admin = getAdmin(usn);
    				isAdmin = true;
    				openDashboard();
    				
    			}
    			else {
    				lblErr.setVisible(true);
    				lblErr.setText("username/password salah");
    			}
    		}

			//login sebagai user
    		else if (getAccRole(usn).equals("user")) {
    			if (validateAcc(usn,pass)) {
    				user = getUser(usn);
    				isAdmin = false;
    				openDashboard();
    			}
    			else {
    				lblErr.setVisible(true);
    				lblErr.setText("username/password salah");
    			}
    		}
    		else {
    			lblErr.setVisible(true);
    			lblErr.setText("Akun belum ada");
    		}
    	}
    	
    	//kondisi untuk daftar
    	else {
    		String usn, phone, pass, name, role, email;
    		usn = txtUsn.getText(); pass = txtPass.getText(); name = txtName.getText(); 
    		role = "user"; email = txtEmail.getText(); phone = txtPhone.getText();
    		if (!usn.isBlank() && !pass.isBlank() && !name.isBlank() && !email.isBlank() && !phone.isBlank()) {
    			
    			registerAccount(usn, pass, name, phone, email, role);
    			user = new User(name, pass, name, phone, email, role);
    			isAdmin = false;
    			openDashboard();
    		}
    		else {
    			lblErr.setVisible(true);
    			lblErr.setText("Tidak boleh ada yang kosong");
    		}
    	}
    }
    

    @FXML
    void toDaftar(ActionEvent event) {
    	emptyAllTxtField();
    	// memunculkan kembali item yang di hide
    	formEmail.setVisible(true); formName.setVisible(true); formPhone.setVisible(true);
    	// hide item yang tidak diperlukan
    	lblErr.setVisible(false);
    	toDaftarContainer.setVisible(false); toDaftarContainer.managedProperty().bind(toDaftarContainer.visibleProperty());
    	btnDaftar.setText("DAFTAR");
    	btnBackToLogin.setVisible(true);
    }
    
    // mengosongkan text field yang terisi saat user berpindah ke login/daftar
    private void emptyAllTxtField() {
    	txtUsn.setText(null); txtPass.setText(null); txtPhone.setText(null); txtEmail.setText(null); txtName.setText(null);
    }

    private void openDashboard(){
    	try {
			Parent usrDashboardPage = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
			Scene scene = new Scene(usrDashboardPage);
			Stage thisStage = (Stage) txtUsn.getScene().getWindow();
			thisStage.setScene(scene);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

}
