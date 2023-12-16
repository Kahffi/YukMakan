package controller;

import javafx.scene.image.Image;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.Resep;

public class CardResepController {

	  	@FXML
	    private VBox cardContainer;

	    @FXML
	    private Label deskripsiResep;

	    @FXML
	    private ImageView fotoResep;

	    @FXML
	    private Label judulResep;

	    @FXML
	    private Label namaAdmin;

	    @FXML
	    private Label tanggal;

	    @FXML
	    void toResep(MouseEvent event) {

	    }
    
    public void setData(Resep resep) {
    	judulResep.setText(resep.getJudul());
    	deskripsiResep.setText(resep.getJudul());
    	namaAdmin.setText(resep.getUploader().getUsername());
    	tanggal.setText(resep.getDatePosted());
    }
    
    
    
    

}

