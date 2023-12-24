/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.BufferedInputStream;
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
import model.Admin;

import model.Resep;
import model.Ulasan;
import utils.ImageUtils;


/**
 *
 * @author Kahffi
 */
public class ResepDAO {
    private static Connection conn;
    private static ResultSet rs;
    private static PreparedStatement stmt;
    

    public static void saveResep(Resep r){
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("insert into resep values (?, ?, ?, ?, ?, ?, ?,?,?)");
            stmt.setString(1, r.getId().toString());
            stmt.setString(2, r.getUploader().getUsername());
            stmt.setString(3, r.getJudul());
            stmt.setString(4, r.getDatePosted());
            stmt.setString(5, r.getDeskripsi());
            stmt.setString(6, r.getLangkah());
            stmt.setString(7, r.getBahan());
            stmt.setString(8, r.getKandunganGizi());
            stmt.setBlob(9, ImageUtils.imageToInputStream(r.getFotoResep()));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KontenEduDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("update gagal");
        }
    }
    
    // update resep 
    public static void updateResep(Resep r){
        String query = "update resep set judul = ?, deskripsi = ?, langkah = ?, bahan = ?, kandunganGizi = ?, imageResep = ?  where id  = ?";

        query = String.format(query, r.getJudul(), r.getDeskripsi(),
                r.getLangkah(), r.getBahan(), r.getKandunganGizi(), r.getFotoResep());
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, r.getJudul());
            stmt.setString(2, r.getDeskripsi());
            stmt.setString(3, r.getLangkah());
            stmt.setString(4, r.getBahan());
            stmt.setString(5, r.getKandunganGizi());
            stmt.setBlob(6, ImageUtils.imageToInputStream(r.getFotoResep()));
            stmt.setString(7, r.getId().toString());
            System.out.println("ini query : " + query);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KontenEduDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("update gagal");
        }
    }
    
    //hapus resep (dan juga ulasan yang berkaitan)
    public static void delResep(Resep r){
        String query = "DELETE FROM yukmakan.resep WHERE id = '%s';";
        query = String.format(query, r.getId().toString());
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            
            //sambungkan dengan method pada ulasanDAO untuk menghapus ulasan
            //ulasanDAO.delUlasan(r.getId().toString());
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ResepDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Hapus data gagal");
        }
    }
    
    // mengambil data dari database berdasarkan id resep
    public static Resep getResep (String id){
        Resep r;
        String query = "Select * from resep where id = '%s'";
        query = String.format(query, id);
        try {
            conn = BaseDAO.getConn();
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            
            if (!rs.next()){
                Admin admin;
                UUID uuid;
                ArrayList <Ulasan> ulasan = new ArrayList <>();
                InputStream is;
                Image imageResep;
                String judul;
                String tanggal;
                String uploader;
                String deskripsi;
                String bahan;
                String kandunganGizi;
                String langkah;

                uuid = UUID.fromString(rs.getString(1));
                uploader = rs.getString(2);
                judul = rs.getString(3);
                tanggal = rs.getString(4);
                deskripsi = rs.getString(5);
                langkah = rs.getString(6);
                bahan = rs.getString(7);
                kandunganGizi = rs.getString(8);
                is = rs.getBinaryStream(9);
                admin = AkunDAO.getAdmin(uploader);

                if (is!=null){
                    imageResep = new Image(is);
                    is.close();
                    r = new Resep (uuid, judul, deskripsi, langkah, bahan, kandunganGizi, imageResep, tanggal,admin);
                }

                r = new Resep(uuid, judul, deskripsi, langkah, bahan, kandunganGizi, tanggal, admin, ulasan);

                // mengambil data admin untuk construct class resep
                //ulasan = UlasanDAO.getUlasan(uuid.toString());

                conn.close();
                return r;
            }
            else{
                System.out.println("gagal mengambil data konten edukasi");
                return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(KontenEduDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("gagal mengambil data konten edukasi");
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    
    public static ArrayList<Resep> getAllResep(){
    	ArrayList <Resep> ls = new ArrayList<>();
    	conn = BaseDAO.getConn();
        System.out.println("Halo, dibawah ini sus");
    	try {
            System.out.println("sus");
			stmt = conn.prepareStatement("select * from resep");
			rs = stmt.executeQuery();
			while(rs.next()) {
                ArrayList <Ulasan> ulasan = new ArrayList <>();
                Resep r;
                InputStream is;
                Image imageResep;
				UUID id;
		    	String judul;
                String deskripsi;
                String langkah;
                String bahan;
                String kandunganGizi;
                String datePosted;
                Admin admin;
				id = UUID.fromString(rs.getString("id"));
				admin = AkunDAO.getAdmin(rs.getString("admin_username"));
				judul = rs.getString("judul");
				datePosted = rs.getString("datePosted");
				deskripsi = rs.getString("deskripsi");
				langkah = rs.getString("langkah");
				bahan = rs.getString("bahan");
				kandunganGizi = rs.getString("kandunganGizi");
				imageResep = ImageUtils.inputStreamToImage(rs.getBinaryStream("imageResep"));
                //todo mengambil ulasan dari database
                r = new Resep(id, judul, deskripsi, langkah, bahan, kandunganGizi, imageResep,datePosted, admin);
                System.out.println("Berhasil ditambahkan?");
                r.printResep();
                ls.add(r);
			}
			return ls;

		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan saat mengambil data resep dari database");
			e.printStackTrace();
			return null;
		}
    }
}
