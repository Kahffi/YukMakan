/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Resep;
import dao.AkunDAO;

import javafx.scene.image.Image;
import utils.ImageUtils;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class PictureController implements Initializable {
	private InputStream input;

    private FileChooser fileChooser;
    @FXML
    private Button btnSave;
    @FXML
    private TextArea txtPict;
    @FXML
    private Button btnGet;
    @FXML
    private ImageView accountPicture;
    public static Resep resep;

    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayPicture(AkunDAO.getProfilePict("kahffi"));
        // TODO        
        
    }

    @FXML
    private void handleButton(ActionEvent event){
        if (event.getSource() == btnGet) {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image files", "*.png", "*.jpg", "*.gif")
        );

        // Show open file dialog
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Display the file path in the TextArea
            txtPict.setText(selectedFile.getAbsolutePath());
            try {
    			input = new FileInputStream(selectedFile.getAbsolutePath());
                InputStream input2 = new FileInputStream(selectedFile.getAbsolutePath());
                Image inmage2 = new Image(input2);
                InputStream newIs = ImageUtils.imageToInputStream(inmage2);
    			AkunDAO.setPicture("kahffi", inmage2);
                displayPicture(input);
                image2.setImage(new Image(newIs));
                image3.setImage(inmage2);
                displayPicture(AkunDAO.getProfilePict("kahffi"));
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} finally {
    			try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
        } else {
            // User canceled the file selection
            System.out.println("File selection canceled.");
        }
    }
    }
    
    public void displayPicture (InputStream img){
    	Image image = new Image(img);
    	accountPicture.setImage(image);
    	try {
			img.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
}
