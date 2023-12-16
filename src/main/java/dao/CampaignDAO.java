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
import model.Campaign;
import model.KontenEdukasi;
/**
 *
 * @author rahma
 */
public class CampaignDAO {
    private static Connection conn;
    private static PreparedStatement stmt;
    private static ResultSet rs;

    // method to save campaign data to the database
    public static void saveCampaign(Campaign c) {
        try {
            String query = "insert into campaign values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', %d, %d)";
            query = String.format(query, c.getId().toString(), c.getJudul(), c.getCreator().getUsername(),
                    c.getDeskripsi(), c.getTanggal(), c.getImagePath(), c.getTargetDonasi(), c.getCurrentDonasi());
            conn = BaseDAO.getConn();
            System.out.println("ini query : " + query);
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CampaignDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public static void updateJudul(Campaign c) {
        String query = "update campaign set judul = '%s' where id  = '%s'";
        query = String.format(query, c.getJudul(), c.getId().toString());
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CampaignDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("update gagal");
        }
    }

    public static void updateGambar(Campaign c){
        String query = "update campaign set imagePath = '%s' where id  = '%s'";
        query = String.format(query, c.getImagePath(), c.getId().toString());
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CampaignDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("update gagal");
        }
    }
    
    public static void delCampaign(Campaign c) {
        String query = "DELETE FROM yukmakan.campaign WHERE id = '%s';";
        query = String.format(query, c.getId().toString());
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CampaignDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Hapus data gagal");
        }
    }

    public static Campaign getCampaign(String id) {
        Campaign c;
        String query = "Select * from campaign where id = '%s'";
        query = String.format(query, id);
        try {
            conn = BaseDAO.getConn();
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            if (rs.next()) {
                UUID uuid = UUID.fromString(rs.getString(1));
                String judul = rs.getString(2);
                String creator = rs.getString(3);
                String deskripsi = rs.getString(4);
                String tanggal = rs.getString(5);
                String imagePath = rs.getString(6);
                int targetDonasi = rs.getInt(7);
                int currentDonasi = rs.getInt(8);

                // Create Admin instance assuming you have an Admin class
                Admin admin = AkunDAO.getAdmin(creator);

                c = new Campaign(uuid, judul, deskripsi, tanggal, imagePath, targetDonasi);
                c.setCreator(admin);
                c.setCurrentDonasi(currentDonasi);

                conn.close();
                return c;
            } else {
                System.out.println("gagal mengambil data campaign");
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CampaignDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("gagal mengambil data campaign");
            return null;
        }
    }
}
