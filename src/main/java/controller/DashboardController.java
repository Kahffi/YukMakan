package controller;

import dao.CampaignDAO;
import dao.AkunDAO;
import dao.KontenEduDAO;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.*;

public class DashboardController implements Initializable {
	@FXML
	private Circle profilePict;
	
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
	private Button navbarHistoriDonasi;

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
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// jika yang login user
		if (!SignUpController.isAdmin) {

			lblUsername.setText(SignUpController.user.getUsername());
			//setup tampilan profile picture untuk user
			if (SignUpController.user.getProfilePict()!=null) {
				System.out.println(SignUpController.user.getProfilePict() != null);
				profilePict.setFill(new ImagePattern(SignUpController.user.getProfilePict()));
			}
			else{
				profilePict.setFill(new ImagePattern(new Image("/images/account_circle.png")));
			}
			setResepCards(ResepDAO.getAllResep());
		}
		// jika yang login admin
		else {
			try {
				mainSection.setContent(FXMLLoader.load(getClass().getResource("/view/AdminMenu.fxml")));
				// hide beberapa navbar karena admin hanya memiliki 1 navbar
				navbarHistoriDonasi.setVisible(false); navbarHistoriDonasi.managedProperty().bind(navbarHistoriDonasi.visibleProperty());
				navbarKontenEdu.setVisible(false); navbarKontenEdu.managedProperty().bind(navbarKontenEdu.visibleProperty());
				navbarFavorit.setVisible(false); navbarFavorit.managedProperty().bind(navbarFavorit.visibleProperty());
				navbarCampaign.setVisible(false); navbarCampaign.managedProperty().bind(navbarCampaign.visibleProperty());
				navbarResep.setText("Admin Menu");
				ImageView adminIcon = new ImageView(new Image("/images/Admin Settings Male.png"));
				adminIcon.setFitWidth(35);
				adminIcon.setFitHeight(35);
				adminIcon.setPreserveRatio(false);
				navbarResep.setGraphic(adminIcon);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			lblUsername.setText(SignUpController.admin.getUsername());
			//setup tampilan profile picture untuk admin
			if (SignUpController.admin.getProfilePict()!=null) {
				System.out.println(SignUpController.admin.getProfilePict().toString());
				profilePict.setFill(new ImagePattern(SignUpController.admin.getProfilePict()));
			};
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

	public void setResepCards(ArrayList<Resep> resepList) {
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
				cardContainer.add(cardBox, column, row);
				GridPane.setMargin(cardBox, new Insets(30));
                                System.out.println("Resep card");
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
			setCampaignCards(CampaignDAO.getAllCampaign());
		}

	}
	private void setCampaignCards(ArrayList<Campaign> campaignList){
		cardContainer.getChildren().clear();
		int row = 1;
		int column = 0;
		int size = campaignList.size();
		try {
			for (Campaign element : campaignList) {
				if (column == 3) {
					column = 1;
					row++;
				} else {
					column++;
				}
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/view/CardCampaign.fxml"));
				VBox cardBox = fxmlLoader.load();
				CardCampaignController cardController = fxmlLoader.getController();
				cardController.setData(element);
				cardContainer.add(cardBox, column, row);
				GridPane.setMargin(cardBox, new Insets(30));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	public void toKontenEduMenu(ActionEvent event) {
		cardContainer.getChildren().clear();
		resetNavbarPropery();
		setNavbarAffectedStyle(2);
		navbarKontenEdu.getStyleClass().add("navbarBtnSelected");

		if (SignUpController.isAdmin){

		}
		else {
			setKontenEduCards(KontenEduDAO.getAllKonten());
		}
	}
	public void setKontenEduCards(ArrayList<KontenEdukasi> kontenEduList){
		cardContainer.getChildren().clear();
		int row = 1;
		int column = 0;
		int size = kontenEduList.size();
		try {
			for (KontenEdukasi element : kontenEduList) {
				if (column == 3) {
					column = 1;
					row++;
				} else {
					column++;
				}
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/view/CardKontenEdu.fxml"));
				VBox cardBox = fxmlLoader.load();
				CardKontenEduController cardController = fxmlLoader.getController();
				cardController.setData(element);
				cardContainer.add(cardBox, column, row);
				GridPane.setMargin(cardBox, new Insets(30));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@FXML
	public void toDaftarFavMenu(ActionEvent event) {
		cardContainer.getChildren().clear();
		resetNavbarPropery();
		setNavbarAffectedStyle(3);
		navbarFavorit.getStyleClass().add("navbarBtnSelected");
                
                if (!SignUpController.isAdmin) {
                    ArrayList <Resep> daftarFav = new ArrayList<>();
                    daftarFav = AkunDAO.getDaftarFav(SignUpController.user.getUsername());
                    setResepCards(daftarFav);
                    System.out.println("dibawah ini jalan");
                }
	}

	@FXML
	public void toHistoriDonasi (ActionEvent event){
		resetNavbarPropery();
		navbarHistoriDonasi.getStyleClass().add("navbarBtnSelected");
		setNavbarAffectedStyle(5);
		setDonationCards(CampaignDAO.getDonationLogsByUser(SignUpController.user.getUsername()));
	}
	public void setDonationCards(ArrayList<DonationLog> donationLogs) {
		cardContainer.getChildren().clear();
		int column = 0;
		try {
			for (DonationLog donation : donationLogs) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/view/DonationCard.fxml"));
				HBox cardBox = null;
				cardBox = fxmlLoader.load();
				DonationCardController cardController = fxmlLoader.getController();
				cardController.initialize(donation);
				cardContainer.add(cardBox, 0, column);
				column++;
				GridPane.setMargin(cardBox, new Insets(20, 0, 0, 100));
				if (column > 1) {
					GridPane.setMargin(cardBox, new Insets(0, 0, 0, 100));
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	// method untuk reset css dari navbar
	public void resetNavbarPropery() {
		navbarHistoriDonasi.getStyleClass().remove("navbarTopAffected");
		navbarHistoriDonasi.getStyleClass().remove("navbarBottomAffected");
		navbarHistoriDonasi.getStyleClass().remove("navbarBtnSelected");

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
		// 1 = resep, 2 = konten edukasi, 3 = daftar favorit, 4 = campaign 5 = Histori Donasi

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
			navbarHistoriDonasi.getStyleClass().add("navbarBottomAffected");
		}
		else if (i==5) {
			navbarCampaign.getStyleClass().add("navbarTopAffected");
		}
	}

}
