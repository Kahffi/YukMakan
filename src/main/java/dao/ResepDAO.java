/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Admin;

import model.Resep;
import model.Ulasan;
        

/**
 *
 * @author Kahffi
 */
public class ResepDAO {
    private static Connection conn;
    private static ResultSet rs;
    private static PreparedStatement stmt;
    
    
    public static void saveResep(Resep r){
        try {
            String query = "insert into resep values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";
            query = String.format(query, r.getId().toString(), r.getUploader().getUsername(),
                    r.getJudul(), r.getDatePosted(), r.getDeskripsi(),r.getLangkah(),
                    r.getBahan(), r.getKandunganGizi(), r.getImagePath());
            conn = BaseDAO.getConn();
            System.out.println("ini query : " + query);
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Gagal memasukkan data kedalam database");
            Logger.getLogger(ResepDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // update resep 
    public static void updateResep(Resep r){
        String query = "update resep set judul = '%s', deskripsi = '%s', langkah = '%s', bahan = '%s', kandunganGizi = '%s', imagePath = '%s'  where id  = '%s'";
        query = String.format(query, r.getJudul(), r.getDeskripsi(),
                r.getLangkah(), r.getBahan(), r.getKandunganGizi(), r.getImagePath());
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement(query);
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
    public Resep getResep (String id){
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
                String imagePath, judul, tanggal, uploader, deskripsi, bahan, kandunganGizi, langkah;
                
                uuid = UUID.fromString(rs.getString(1));
                uploader = rs.getString(2);
                judul = rs.getString(3);
                tanggal = rs.getString(4);
                deskripsi = rs.getString(5);
                langkah = rs.getString(6);
                bahan = rs.getString(7);
                kandunganGizi = rs.getString(8);
                imagePath = rs.getString(9);
                
                // mengambil data admin untuk construct class resep
                admin = AkunDAO.getAdmin(uploader);
                //ulasan = UlasanDAO.getUlasan(uuid.toString());
                r = new Resep (uuid, judul, deskripsi, langkah, bahan, kandunganGizi, imagePath, tanggal,admin);
                
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
        }
        
    }
    
    public ArrayList<Resep> getAllResep(){
    	ArrayList <Resep> ls = new ArrayList<>();
    	conn = BaseDAO.getConn();
    	try {
			stmt = conn.prepareStatement("select * from akun");
			rs = stmt.executeQuery();
			while(!rs.next()) {
				UUID id;
		    	String judul, deskripsi, langkah, bahan, kandunganGizi, imagePath, datePosted;
		    	Admin admin;
				id = UUID.fromString(rs.getString("id"));
				admin = AkunDAO.getAdmin(rs.getString("admin_username"));
				judul = rs.getString("judul");
				datePosted = rs.getString("datePosted");
				deskripsi = rs.getString("deskripsi");
				langkah = rs.getString("langkah");
				bahan = rs.getString("bahan");
				kandunganGizi = rs.getString("kandunganGizi");
				imagePath = rs.getString("imagePath");
				
				ls.add(new Resep(id, judul, deskripsi, langkah, bahan, kandunganGizi, imagePath, datePosted, admin));
			}
			return ls;
			
		} catch (SQLException e) {
			System.out.println("Terjadi kesalahan saat mengambil data resep dari database");
			e.printStackTrace();
			return null;
		}
    }
}
