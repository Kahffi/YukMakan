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

import model.Resep;
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
            conn = BaseDAO.getConn();
            stmt = conn.prepareStatement( "insert into ulasan values (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, u.getId().toString());
            stmt.setString(2, u.getResepId().toString());
            stmt.setString(3, u.getUser().getUsername());
            stmt.setString(4, u.getUlasan());
            stmt.setString(5, u.getTanggalUlasan());
            stmt.setInt(6, u.getRating());
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

    public static ArrayList<Ulasan> getAllUlasan(String resepID){
        ArrayList <Ulasan> ulasan = new ArrayList <>();
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("select * from ulasan where resep_id = ?");
            stmt.setString(1, resepID);
            rs = stmt.executeQuery();
            while (rs.next()){
                Ulasan u;
                String id = rs.getString("id");
                User user = AkunDAO.getUser(rs.getString("user_username"));
                int rating = rs.getInt("rating");
                String isi = rs.getString("isi");
                String tanggal = rs.getString("tanggal");
                u = new Ulasan(user, isi, tanggal, rating, UUID.fromString(id), UUID.fromString(resepID));
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

//    hapus ulasan yang spesifik
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

//    hapus ulasan yang terkait dengan resep
    public static void delUlasan(Resep resep){
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("delete from ulasan where resep_id = ?");
            stmt.setString(1, resep.getId().toString());
            stmt.executeUpdate();
            System.out.println("Berhasil hapus seluruh ulasan pada resep");
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
