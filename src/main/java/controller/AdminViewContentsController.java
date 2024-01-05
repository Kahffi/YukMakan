package controller;

import dao.CampaignDAO;
import dao.ResepDAO;
import dao.KontenEduDAO;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Campaign;
import model.KontenEdukasi;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Resep;
import model.KontenEdukasi;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminViewContentsController implements Initializable {
    private String session;
    @FXML
    private GridPane cardsLayout;
    @FXML
    private Button btnBackToDash;
    @FXML
    private BorderPane pane = new BorderPane();

    public AdminViewContentsController(String session) {
        this.session = session;
    }

    //untuk mengontrol tampilan kartu agar file fxml dapat digunakan lebih dari sekali
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (session.equals("resep")){
            ArrayList <Resep> resepList = ResepDAO.getAllResep();
            initResepCards(resepList);
            System.out.println("ini view resep");
        }
        else if (session.equals("kontenedukasi")){
            ArrayList<KontenEdukasi> kontenEduList = KontenEduDAO.getAllKonten();
            initKontenCard(kontenEduList);
            System.out.println("ini konten edukasi");

        }
        else if (session.equals("campaign")){
            ArrayList<Campaign> campaignList = CampaignDAO.getAllCampaign();
            initCampaignCards(campaignList);

        }
    }

    public void initResepCards(ArrayList <Resep> resepList){
        int row = 1;
        int column = 0;
        for (Resep resep : resepList){
            if (column == 4){
                column = 1;
                row++;
            }
            else{
                column++;
            }
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/CardResep.fxml"));
            try {
                VBox card = fxmlLoader.load();
                CardResepController cardController = fxmlLoader.getController();
                cardController.setData(resep);
                cardsLayout.add(card, column, row);
                GridPane.setMargin(card, new Insets(30));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
    
    public void initKontenCard(ArrayList <KontenEdukasi> kontenEduList){
        int row = 1;     
        int column = 0;
        for (KontenEdukasi konten : kontenEduList){
            if (column == 4){
                column = 1;
                row++;
            }
            else{
                column++;
            }
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/CardKontenEdu.fxml"));
            try {
                VBox card = fxmlLoader.load();
                CardKontenEduController cardController = fxmlLoader.getController();
                cardController.setData(konten);
                cardsLayout.add(card, column, row);
                GridPane.setMargin(card, new Insets(30));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        
    }

    public void initCampaignCards(ArrayList<Campaign> campaigns){
        cardsLayout.getChildren().clear();
        try {
            int row = 1;
            int column = 0;
            for (Campaign campaign : campaigns){
                if (column == 4){
                    column = 1;
                    row++;
                }
                else{
                    column++;
                }
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/CardCampaign.fxml"));
                VBox card = loader.load();
                CardCampaignController cardController = loader.getController();
                cardController.setData(campaign);
                cardsLayout.add(card, column, row);
                GridPane.setMargin(card, new Insets(30));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    
    public void setSession(String session){
        this.session = session;
    }
    @FXML
    void backToDashboard(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) btnBackToDash.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
