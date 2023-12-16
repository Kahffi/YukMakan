package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Admin;
import model.Resep;

public class UsrDashboardController implements Initializable {
	
	
	private Admin admin = new Admin("Kahffi", "123", "kahffi", "08907", "kahffi@gmail.com", "admin");

	private ArrayList <Resep> resep = new ArrayList<>();
		
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

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		resep.add(new Resep(UUID.randomUUID(),"Judul", "Telur makanan yang praktis dibuat namun menghasilkan rasa yang lezat. Perpaduan rasa gurih telur dengan kecap manis memang menjadikannya menu favorit bagi sebagian orang, terutama anak kos yang ingin serba cepat dan murah.",
				"langkah", "bahan", "kandungan gizi", "image path", "2024-07-4", admin));
	
		int size = resep.size();
		try {
		for (int i = 0; i<size; i++) {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/view/CardResep.fxml"));
			
				VBox cardBox = fxmlLoader.load();
				CardResepController cardController = fxmlLoader.getController();
				cardController.setData(resep.get(i));
				
				cardContainer.getChildren().add(cardBox);
				GridPane.setMargin(cardBox, new Insets(30));
			} 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
