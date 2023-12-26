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
            
            if (rs.next()){
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

    public static ArrayList<Ulasan> getAllUlasan(String id){
        ArrayList <Ulasan> ulasan = new ArrayList <>();
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("select * from ulasan where id = ?");
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            while (rs.next()){
                Ulasan u;
                String id2 = rs.getString("id");
                User user = AkunDAO.getUser(rs.getString("username"));
                String isi = rs.getString("isi");
                String tanggal = rs.getString("tanggal");
                u = new Ulasan(user, isi, tanggal, id2);
                ulasan.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        BaseDAO.closeConn(conn);
        return ulasan;
    }
    public static ArrayList<Ulasan> getAllUlasan(){
        ArrayList <Ulasan> ulasan = new ArrayList <>();
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("select * from ulasan");
            rs = stmt.executeQuery();
            while (rs.next()){
                Ulasan u;
                String id = rs.getString("id");
                User user = AkunDAO.getUser(rs.getString("user_username"));
                String isi = rs.getString("isi");
                String tanggal = rs.getString("tanggal");
                u = new Ulasan(user, isi, tanggal, id);
                ulasan.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        BaseDAO.closeConn(conn);
        return ulasan;
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
         String query = "update ulasan set isi = '%s', tanggal = '%s' where id  = '%s'";
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
