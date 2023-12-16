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
import model.Ulasan;
import model.User;

/**
 *
 * @author Fauzan
 */
public class UlasanDAO{
    private static Connection conn;
    private static PreparedStatement stmt;
    private static ResultSet rs;
    
    public static void saveUlasan(Ulasan u){
        try {
            String query = "insert into ulasan values ('%s', '%s', '%s', '%s')";
            query = String.format(query, u.getId().toString(),
                        u.getUlasanUsername(), u.getUlasan(), u.getTanggalUlasan());
            conn = BaseDAO.getConn();
            System.out.println("ini query : " + query);
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UlasanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Ulasan getUlasan(String id){
        Ulasan ul;
        String query = "Select * from ulasan where id = '%s'";
        query = String.format(query, id);
        try {
            conn = BaseDAO.getConn();
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            
            if (!rs.next()){
                UUID uuid;
                User usr;
                String ulasan, tanggalUlasan, user;
   
                
                uuid = UUID.fromString(rs.getString(1));
                user= rs.getString(2);
                ulasan = rs.getString(3);
                tanggalUlasan = rs.getString(4);
                
                ul = new Ulasan(uuid, ulasan, tanggalUlasan);
                usr = AkunDAO.getUser(user);
                conn.close();
                return ul;
            }
            else{
                System.out.println("gagal mengambil data konten edukasi");
                return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UlasanDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("gagal mengambil data konten edukasi");
            return null;
        }
    }
    
    public static void delUlasan(Ulasan u){
        String query = "DELETE FROM yukmakan.ulasan WHERE id = '%s';";
        query = String.format(query, u.getId().toString());
        conn = BaseDAO.getConn();
        try{
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(UlasanDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Hapus data gagal");
        }
    }
    
    public static void editUlasan(Ulasan u){
         String query = "update ulasan set ulasan = '%s', tanggalulasan = '%s' where id  = '%s'";
         query = String.format(query, u.getUlasan(), u.getTanggalUlasan(), u.getId().toString());
         conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UlasanDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("update gagal");
        }
    }
    
    
    
    
        
    
    
    
}
