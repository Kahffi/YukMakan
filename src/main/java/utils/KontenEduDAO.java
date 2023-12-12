/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.KontenEdukasi;
import model.Admin;

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
        try {
            String query = "insert into kontenedukasi values ('%s', '%s', '%s', '%s', '%s', '%s')";
            query = String.format(query, k.getId().toString(),
                        k.getUploader().getUsername(), k.getJudul(), k.getContent(), k.getTanggal(),
                        k.getImagePath());
            conn = BaseDAO.getConn();
            System.out.println("ini query : " + query);
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(KontenEduDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    // method-method untuk update data dalam database
    public static void updateJudul(KontenEdukasi k){
        String query = "update kontenedukasi set judul = '%s' where id  = '%s'";
        query = String.format(query, k.getJudul(), k.getId().toString());
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KontenEduDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("update gagal");
        }
    }
    public static void updateKonten(KontenEdukasi k){
        String query = "update kontenedukasi set konten = '%s' where id  = '%s'";
        query = String.format(query, k.getContent(), k.getId().toString());
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KontenEduDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("update gagal");
        }
    }
    public static void updateGambar(KontenEdukasi k){
        String query = "update kontenedukasi set imagePath = '%s' where id  = '%s'";
        query = String.format(query, k.getImagePath(), k.getId().toString());
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement(query);
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
                String imagePath, judul, content, tanggal, uploader;
                
                uuid = UUID.fromString(rs.getString(1));
                uploader = rs.getString(2);
                judul = rs.getString(3);
                content = rs.getString(4);
                tanggal = rs.getString(5);
                imagePath = rs.getString(6);
                
                k = new KontenEdukasi (uuid, imagePath, judul, content, tanggal);
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
        }
        
    } 
  
    
    
}
