package controller;

import javafx.fxml.Initializable;
import model.Resep;

import java.net.URL;
import java.util.ResourceBundle;

public class ResepDetailController implements Initializable {
    private Resep resep;
    public ResepDetailController (Resep resep){
        this.resep = resep;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
