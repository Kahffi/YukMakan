package controller;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import dao.AkunDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import javafx.scene.image.ImageView;

import static controller.SignUpController.isAdmin;
import static controller.SignUpController.user;
import static controller.SignUpController.admin;

public class ProfileController implements Initializable{

    InputStream pictureInput;
    private FileChooser fileChooser;
	@FXML
	private ImageView profilePicture;
	@FXML
	private Text pictureStatus;
    @FXML
    private Button btnChangePicture;

	@FXML
	private Button btnSaveEditProfile;

    @FXML
    private Button btnEditProfile;

	@FXML
    private Button btnBackToDashboard;
    @FXML
    private Button btnLogout;

    @FXML
    private Text lblEmail;

    @FXML
    private Text lblNama;

    @FXML
    private Text lblPhone;

    @FXML
    private Text lblUsername;

    @FXML
    private TextField txtEditNama;

    @FXML
    private TextField txtEditPhone;
    @FXML
    private Text editStatus;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initAccProfile();
		btnSaveEditProfile.setVisible(false); btnSaveEditProfile.managedProperty().bind(btnSaveEditProfile.visibleProperty());
		pictureStatus.setVisible(false); pictureStatus.managedProperty().bind(pictureStatus.visibleProperty());
		editStatus.setVisible(false); editStatus.managedProperty().bind(editStatus.visibleProperty());;
		txtEditNama.setVisible(false); txtEditNama.managedProperty().bind(txtEditNama.visibleProperty());
		txtEditPhone.setVisible(false); txtEditPhone.managedProperty().bind(txtEditPhone.visibleProperty());
	}

	@FXML
	void startEditProfile(ActionEvent event) {
		if (btnEditProfile.getText().equals("Edit")) {
			btnEditProfile.setText("Batal");
			btnSaveEditProfile.setVisible(true);
			txtEditNama.setVisible(true);
			txtEditPhone.setVisible(true);
		}
		
		else if (btnEditProfile.getText().equals("Batal")) {
			initAccProfile();
			btnEditProfile.setText("Edit");
			btnSaveEditProfile.setVisible(false);
		}
	}
	
	@FXML
	void saveEditProfile() {
		if (isAdmin){
			admin.setNama(txtEditNama.getText());
			admin.setPhoneNum(txtEditPhone.getText());
		}
		else{
			user.setNama(txtEditNama.getText());
			user.setPhoneNum(txtEditPhone.getText());
		}
		initAccProfile();
		txtEditNama.setVisible(false);
		txtEditPhone.setVisible(false);
		AkunDAO.updateAccount(txtEditNama.getText(), txtEditPhone.getText(), lblUsername.getText());
		btnSaveEditProfile.setVisible(false);
		btnEditProfile.setText("Edit");
	}
	
	@FXML
	void logout(ActionEvent event) {
		try {
			Parent parent;
			parent = FXMLLoader.load(getClass().getResource("/view/SignUp.fxml"));
			Scene scene = new Scene(parent);
			user = null;
			admin = null;
			Stage stage = (Stage) txtEditNama.getScene().getWindow();
			stage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void initAccProfile() {
		if (isAdmin) {
			profilePicture.setImage(admin.getProfilePict());
			lblUsername.setText(admin.getUsername());
			lblNama.setText(admin.getNama());
			lblEmail.setText(admin.getEmail());
			lblPhone.setText(admin.getPhoneNum());
		}
		else {
			profilePicture.setImage(user.getProfilePict());
			lblUsername.setText(user.getUsername());
			lblNama.setText(user.getNama());
			lblEmail.setText(user.getEmail());
			lblPhone.setText(user.getPhoneNum());			
		};
		txtEditNama.setText(lblNama.getText());
		txtEditPhone.setText(lblPhone.getText());
        profilePicture.setFitHeight(60);
        profilePicture.setFitWidth(60);
	}
	
	public void backToDashboard (ActionEvent event) {
		try {	
			Parent parent = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = (Stage) txtEditNama.getScene().getWindow();
			stage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public void changePicture(ActionEvent event){
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.gif")
        );

        File selectedImg = fileChooser.showOpenDialog(null);
        if (selectedImg != null){
            try {
				pictureInput = new BufferedInputStream(new FileInputStream(selectedImg.getAbsolutePath()));
                if (isAdmin){
					System.out.println(pictureInput.toString());
					AkunDAO.setPicture(admin.getUsername(), pictureInput);
					if(pictureInput.markSupported()){
						pictureInput.reset();
						System.out.println("Resek");
					}
					System.out.println(pictureInput.toString());
					admin.setProfilePict(new Image(pictureInput));
                }
                else {
					if(pictureInput.markSupported()){
						pictureInput.reset();
						System.out.println("Resek");
					}
					AkunDAO.setPicture(user.getUsername(), pictureInput);
					user.setProfilePict(new Image(pictureInput));
                };

                initAccProfile();
                pictureInput.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
