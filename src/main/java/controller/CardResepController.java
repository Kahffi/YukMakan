package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Resep;

import java.io.IOException;

public class CardResepController {

		private Resep resep;

	  	@FXML
	    private VBox cardContainer;

	    private Label deskripsiResep;

	    private ImageView fotoResep;

	    private Label judulResep;

	    @FXML
	    private Label namaAdmin;

	    @FXML
	    private Label tanggal;
    

	    @FXML
	    void toResepDetail(MouseEvent event) throws IOException {
			System.out.println("judul: " + resep.getJudul());
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ResepDetail.fxml"));
			loader.setController(new ResepDetailController(resep));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);

			Stage stage = (Stage) namaAdmin.getScene().getWindow();
			stage.setTitle("YukMakan - Resep Detail");
			stage.setScene(scene);
	    }
    
    public void setData(Resep resep) {
		this.resep = resep;
    	judulResep.setText(resep.getJudul());
    	deskripsiResep.setText(resep.getDeskripsi());
    	namaAdmin.setText(resep.getUploader().getUsername());
    	tanggal.setText(resep.getDatePosted());
		fotoResep.setImage(resep.getFotoResep());
    }

}

