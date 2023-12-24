package controller;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

import dao.ResepDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Admin;
import model.Resep;

public class DashboardController implements Initializable {

	private final ArrayList <Button> buttonList = new ArrayList <>();

	@FXML
	private ImageView profilePict;
	
	@FXML
	private Label lblUsername;
		
	@FXML
	private GridPane cardContainer;
    @FXML
    private ScrollPane mainSection;

    @FXML
    private VBox navbar;

    @FXML
    private Button navbarCampaign;

    @FXML
    private Button navbarFavorit;

    @FXML
    private Button navbarKontenEdu;

    @FXML
    private HBox navbarProfile;

    @FXML
    private Button navbarResep;

    @FXML
    void toProfile(MouseEvent event) {
    	try {
			Parent profilePage = FXMLLoader.load(getClass().getResource("/view/Profile.fxml"));
			Scene scene = new Scene(profilePage);
			Stage stage = (Stage) navbar.getScene().getWindow();
			stage.setScene(scene);
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	@FXML
	void toResepMenu (ActionEvent event) {
		resetNavbarPropery();
		navbarResep.getStyleClass().add("navbarBtnSelected");
		setNavbarAffectedStyle(1);
		if (SignUpController.isAdmin) {

		}
		else {
			setResepCards(ResepDAO.getAllResep());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// jika yang login user
		if (!SignUpController.isAdmin) {

			lblUsername.setText(SignUpController.user.getUsername());
			//setup tampilan profile picture untuk user
			if (SignUpController.user.getProfilePict()!=null) {
				System.out.println(SignUpController.user.getProfilePict() != null);
				profilePict.setImage(SignUpController.user.getProfilePict());
				profilePict.setFitHeight(60);
				profilePict.setFitWidth(60);
			};
			setResepCards(ResepDAO.getAllResep());
		}
		// jika yang login admin
		else {
			try {
				mainSection.setContent(FXMLLoader.load(getClass().getResource("/view/ResepAdmin.fxml")));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			lblUsername.setText(SignUpController.admin.getUsername());
			//setup tampilan profile picture untuk admin
			if (SignUpController.admin.getProfilePict()!=null) {
				System.out.println(SignUpController.admin.getProfilePict().toString());
				profilePict.setImage(SignUpController.admin.getProfilePict());
				profilePict.setFitHeight(60);
				profilePict.setFitWidth(60);
			};
		}

	}
	private void setResepCards(ArrayList<Resep> resepList) {
		cardContainer.getChildren().clear();
		int row = 1;
		int column = 0;
		System.out.println("ResepList size ->"+resepList.size());
		int size = resepList.size();
		try {
			for (Resep element : resepList) {
				if (column == 3) {
					column = 1;
					row++;
				} else {
					column++;
				}
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/view/CardResep.fxml"));

				VBox cardBox = fxmlLoader.load();
				CardResepController cardController = fxmlLoader.getController();
				cardController.setData(element);
				System.out.println(column + " " + row);
				cardContainer.add(cardBox, column, row);
				GridPane.setMargin(cardBox, new Insets(30));
				System.out.println("limapuluh");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@FXML
	public void toCampaignMenu(ActionEvent event) {
		cardContainer.getChildren().clear();
		resetNavbarPropery();
		setNavbarAffectedStyle(4);
		navbarCampaign.getStyleClass().add("navbarBtnSelected");

		if (SignUpController.isAdmin) {

		}
		else {

		}

	}
	@FXML
	public void toKontenEduMenu(ActionEvent event) {
		cardContainer.getChildren().clear();
		resetNavbarPropery();
		setNavbarAffectedStyle(2);
		navbarKontenEdu.getStyleClass().add("navbarBtnSelected");


	}
	@FXML
	public void toDaftarFavMenu(ActionEvent event) {
		cardContainer.getChildren().clear();
		resetNavbarPropery();
		setNavbarAffectedStyle(3);
		navbarFavorit.getStyleClass().add("navbarBtnSelected");

	}

	// method untuk reset css dari navbar
	public void resetNavbarPropery() {
		navbarCampaign.getStyleClass().remove("navbarTopAffected");
		navbarCampaign.getStyleClass().remove("navbarBottomAffected");
		navbarCampaign.getStyleClass().remove("navbarBtnSelected");

		navbarFavorit.getStyleClass().remove("navbarTopAffected");
		navbarFavorit.getStyleClass().remove("navbarBottomAffected");
		navbarFavorit.getStyleClass().remove("navbarBtnSelected");

		navbarKontenEdu.getStyleClass().remove("navbarTopAffected");
		navbarKontenEdu.getStyleClass().remove("navbarBottomAffected");
		navbarKontenEdu.getStyleClass().remove("navbarBtnSelected");

		navbarResep.getStyleClass().remove("navbarTopAffected");
		navbarResep.getStyleClass().remove("navbarBottomAffected");
		navbarResep.getStyleClass().remove("navbarBtnSelected");

		navbarProfile.getStyleClass().remove("navbarTopAffected");
		navbarProfile.getStyleClass().remove("navbarBottomAffected");
		navbarProfile.getStyleClass().remove("navbarBtnSelected");
	}
	//method untuk mengatur style dari navbar yang berada di atas dan atau di bawah navbar yang sedang dipilih
	public void setNavbarAffectedStyle(int i){
		// 1 = resep, 2 = konten edukasi, 3 = daftar favorit, 4 = campaign

		if(i==1) {
			navbarKontenEdu.getStyleClass().add("navbarBottomAffected");
		}
		else if(i==2) {
			navbarResep.getStyleClass().add("navbarTopAffected");
			navbarFavorit.getStyleClass().add("navbarBottomAffected");
		}
		else if(i==3) {
			navbarKontenEdu.getStyleClass().add("navbarTopAffected");
			navbarCampaign.getStyleClass().add("navbarBottomAffected");
		}
		else if(i==4) {
			navbarFavorit.getStyleClass().add("navbarTopAffected");
		};

	}

}