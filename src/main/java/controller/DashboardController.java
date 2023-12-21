package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

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
	
	
	private final Admin admin = new Admin("Kahffi", "123", "kahffi", "08907", "kahffi@gmail.com", "admin");

	private final ArrayList <Button> buttonList = new ArrayList <>();
	
	private final ArrayList <Resep> resepList = new ArrayList<>();
	
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (!SignUpController.isAdmin) {

			lblUsername.setText(SignUpController.user.getUsername());
			//setup tampilan profile picture untuk user
			if (SignUpController.user.getProfilePict()!=null) {
				System.out.println(SignUpController.user.getProfilePict() != null);
				profilePict.setImage(SignUpController.user.getProfilePict());
				profilePict.setFitHeight(60);
				profilePict.setFitWidth(60);
			}
		}

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
			}
		}
		resepList.add(new Resep(UUID.randomUUID(),"Judul", "Telur makanan yang praktis dibuat namun menghasilkan rasa yang lezat. Perpaduan rasa gurih telur dengan kecap manis memang menjadikannya menu favorit bagi sebagian orang, terutama anak kos yang ingin serba cepat dan murah.",
				"langkah", "bahan", "kandungan gizi", "image path", "2024-07-4", admin));



		int size = resepList.size();
		try {
		for (Resep element : resepList) {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/view/CardResep.fxml"));

				VBox cardBox = fxmlLoader.load();
				CardResepController cardController = fxmlLoader.getController();
				cardController.setData(element);

				cardContainer.getChildren().add(cardBox);
				GridPane.setMargin(cardBox, new Insets(30));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
