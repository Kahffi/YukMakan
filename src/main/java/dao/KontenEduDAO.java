/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import model.KontenEdukasi;
import model.Admin;
import model.Ulasan;
import utils.ImageUtils;

/**
 *
 * @author Kahffi
 */
public class KontenEduDAO {
    private static Connection conn;
    private static PreparedStatement stmt;
    private static ResultSet rs;
    /*
    public static ArrayList <KontenEdukasi> getKontenEduList(){
        ArrayList <KontenEdukasi> kontenEduList;
        
       return kontenEduList;
    }
    */
    
    // method untuk menyimpan data ke dalam database
    public static void saveKontenEdu(KontenEdukasi k){
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("insert into kontenedukasi values (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, k.getId().toString());
            stmt.setString(2, k.getUploader().getUsername());
            stmt.setString(3, k.getJudul());
            stmt.setString(4, k.getContent());
            stmt.setString(5, k.getTanggal());
            stmt.setBlob(6, ImageUtils.imageToInputStream(k.getImagePath()));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("pek");
            Logger.getLogger(KontenEduDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("update gagal");
        }
       
    }
    
    
    // method-method untuk update data dalam database
    public static void updateKontenEdu(KontenEdukasi k){
       String query = "update kontenedukasi set judul = ?, konten = ?, imagePath = ?  where id  = ?";

        query = String.format(query, k.getJudul(),
                k.getContent(), k.getImagePath());
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, k.getJudul());
            stmt.setString(2, k.getContent());
            stmt.setBlob(3, ImageUtils.imageToInputStream(k.getImagePath()));
            stmt.setString(4, k.getId().toString());
            System.out.println("ini query : " + query);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KontenEduDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("update gagal");
        }
    }
    
    // method untuk menghapus konten edukasi dari database
    public static void delKontenEdu(KontenEdukasi k){
        String query = "DELETE FROM yukmakan.kontenedukasi WHERE id = '%s';";
        query = String.format(query, k.getId().toString());
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KontenEduDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Hapus data gagal");
        }
    }
    
    // method untuk mengambil data dari database berdasarkan id 
    public static KontenEdukasi getKontenEdukasi (String id){
        KontenEdukasi k;
        String query = "Select * from KontenEdukasi where id = '%s'";
        query = String.format(query, id);
        try {
            conn = BaseDAO.getConn();
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            
            if (!rs.next()){
                UUID uuid;
                Admin admin;
                ArrayList <Ulasan> ulasan = new ArrayList <>();
                Image imagePath;
                InputStream is;
                String judul, content, tanggal, uploader;
                
                uuid = UUID.fromString(rs.getString(1));
                uploader = rs.getString(2);
                judul = rs.getString(3);
                content = rs.getString(4);
                tanggal = rs.getString(5);
                is = rs.getBinaryStream(6);
                ulasan = UlasanDAO.getAllUlasan(uuid.toString());
                admin = AkunDAO.getAdmin(uploader);
                if (is!=null){
                    imagePath = new Image(is);
                    is.close();
                    k = new KontenEdukasi(uuid, imagePath, judul, content, tanggal, admin);
                }
                k = new KontenEdukasi (uuid, judul, content, tanggal, admin);
                admin = AkunDAO.getAdmin(uploader);
                conn.close();
                return k;
            }
            else{
                System.out.println("gagal mengambil data konten edukasi");
                return null;
            }   
        } catch (SQLException ex) {
            Logger.getLogger(KontenEduDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("gagal mengambil data konten edukasi");
            return null;
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        
    }
    
     public static ArrayList<KontenEdukasi> getAllKonten(){
    	ArrayList <KontenEdukasi> ls = new ArrayList<>();
    	conn = BaseDAO.getConn();
        System.out.println("Halo, dibawah ini sus");
    	try {
            System.out.println("sus");
			stmt = conn.prepareStatement("select * from kontenedukasi");
			rs = stmt.executeQuery();
			while(rs.next()) {
                ArrayList <Ulasan> ulasan = new ArrayList <>();
                KontenEdukasi k;
                InputStream is;
                Image imagePath;
				UUID id;
		    	String judul;
                String content;
                String tanggal;
                Admin admin;
				id = UUID.fromString(rs.getString("id"));
				admin = AkunDAO.getAdmin(rs.getString("admin_username"));
				judul = rs.getString("judul");
				content = rs.getString("konten");
				tanggal = rs.getString("tanggal");
				imagePath = ImageUtils.inputStreamToImage(rs.getBinaryStream("imagePath"));
                //todo mengambil ulasan dari database
                k = new KontenEdukasi(id, imagePath, judul, content, tanggal, admin);
                System.out.println("Berhasil ditambahkan?");
                k.printKontenEdu();
                ls.add(k);
			}
			return ls;

		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan saat mengambil data resep dari database");
			e.printStackTrace();
			return null;
		}
    }
  
    
    
}
