/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.image.Image;
import model.*;
import utils.ImageUtils;

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
            conn = BaseDAO.getConn();
            stmt = conn.prepareStatement("insert into campaign values (?, ?, ?, ?, ?, ?, ?, ?,?)");
            stmt.setString(1, c.getId().toString());
            stmt.setString(2, c.getCreator().getUsername());
            stmt.setString(3, c.getJudul());
            stmt.setString(4, c.getDeskripsi());
            stmt.setInt(5, c.getTargetDonasi());
            stmt.setInt(6, c.getCurrentDonasi());
            stmt.setBlob(7, ImageUtils.imageToInputStream(c.getCampaignImage()));
            stmt.setString(8, c.getTanggal());
            stmt.setString(9, c.getEndDate());
            stmt.executeUpdate();
            saveAvailablePayment(c.getListMetodePembayaran(), c.getId());

        } catch (SQLException ex) {
            Logger.getLogger(CampaignDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void saveAvailablePayment(ArrayList<MetodePembayaran> listPayment, UUID campaignID){
        try {
            conn = BaseDAO.getConn();
            stmt = conn.prepareStatement("insert into available_metode_pembayaran values (?,?,?)");
            for (MetodePembayaran payment : listPayment) {
                stmt.setString(1, payment.getId().toString());
                stmt.setString(2, campaignID.toString());
                stmt.setString(3, payment.getId().toString());
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CampaignDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void saveDonation(DonationLog donation) {
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("insert into riwayatdonasi values (?, ?, ?, ?,?,?)");
            stmt.setString(1, donation.getId().toString());
            stmt.setString(2, donation.getCampaignID().toString());
            stmt.setString(3, donation.getDonor().getUsername());
            stmt.setInt(4, donation.getAmount());
            stmt.setString(5, donation.getTanggal());
            stmt.setString(6, donation.getMetodePembayaran().getId().toString());
            stmt.executeUpdate();
            updateCurrentDonasi(donation.getCampaignID(), donation.getAmount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDAO.closeConn(conn);
        }
    }

    public static void updateCurrentDonasi(UUID campaignID, int amount){
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("update campaign set currentDonasi = currentDonasi + ? where id = ?");
            stmt.setInt(1, amount);
            stmt.setString(2, campaignID.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateCampaign(Campaign c){
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("update campaign set judul = ?, deskripsi = ?, targetDonasi = ?, end_date = ? where id = ?");
            stmt.setString(1, c.getJudul());
            stmt.setString(2, c.getDeskripsi());
            stmt.setInt(3, c.getTargetDonasi());
            stmt.setString(4, c.getEndDate());
            stmt.setString(5, c.getId().toString());
            stmt.executeUpdate();
            System.out.println("Id-->>"    + c.getId());
            System.out.println("Judul-->>"    + c.getJudul());
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        query = String.format(query, c.getCampaignImage(), c.getId().toString());
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
        String query = "DELETE FROM yukmakan.campaign WHERE id = '%s' ";
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
    public static Campaign getCampaign(UUID campaignID) {
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("select * from campaign where id = ?");
            stmt.setString(1, campaignID.toString());
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString(1));
                Admin admin = AkunDAO.getAdmin(resultSet.getString(2));
                String judul = resultSet.getString(3);
                String deskripsi = resultSet.getString(4);
                int targetDonasi = resultSet.getInt(5);
                int currentDonasi = resultSet.getInt(6);
                Image image = new Image(resultSet.getBinaryStream(7));
                String tanggal = resultSet.getString(8);
                String endDate = resultSet.getString(9);
                ArrayList<MetodePembayaran> availableMetodePembayaran = getAvailablePayment(id);
                ArrayList<DonationLog> donationLogs = getDonationLogs(id);
                Campaign campaign = new Campaign(judul, admin, deskripsi, tanggal, endDate, image, id, targetDonasi, currentDonasi, availableMetodePembayaran, donationLogs);
                return campaign;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDAO.closeConn(conn);
        }
        return null;
    }

        public static ArrayList<Campaign> getAllCampaign() {
        ArrayList<Campaign> campaignList = new ArrayList<>();
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("select * from campaign");
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                UUID id = UUID.fromString(resultSet.getString(1));
                Admin admin = AkunDAO.getAdmin(resultSet.getString(2));
                String judul = resultSet.getString(3);
                String deskripsi = resultSet.getString(4);
                int targetDonasi = resultSet.getInt(5);
                int currentDonasi = resultSet.getInt(6);
                Image image = new Image(resultSet.getBinaryStream(7));
                String tanggal = resultSet.getString(8);
                String endDate = resultSet.getString(9);
                ArrayList <MetodePembayaran> availableMetodePembayaran = getAvailablePayment(id);
                ArrayList <DonationLog> donationLogs = getDonationLogs(id);
                Campaign campaign = new Campaign(judul, admin, deskripsi, tanggal,endDate ,image, id, targetDonasi, currentDonasi, availableMetodePembayaran, donationLogs);
                campaignList.add(campaign);
            }
            return campaignList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDAO.closeConn(conn);
        }
    }

    public static ArrayList<DonationLog> getDonationLogs(UUID campaignId) {
        ArrayList<DonationLog> donationLogs = new ArrayList<>();
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("select * from riwayatdonasi where campaign_id = ?");
            stmt.setString(1, campaignId.toString());
            rs = stmt.executeQuery();
            while(rs.next()){
                UUID id = UUID.fromString(rs.getString("id"));
                UUID campaignID = UUID.fromString(rs.getString("campaign_id"));
                User donor = AkunDAO.getUser(rs.getString("user_username"));
                int amount = rs.getInt("nominal");
                String date = rs.getString("tanggal");
                MetodePembayaran metodePembayaran = MetodePembayaranDAO.getMetodePembayaran(UUID.fromString(rs.getString("metode_pembayaran_id")));
                DonationLog donation = new DonationLog(donor, amount, date, id.toString(), metodePembayaran, campaignID);
                donationLogs.add(donation);
            }
            return donationLogs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDAO.closeConn(conn);
        }
    }
    public static ArrayList<DonationLog> getDonationLogsByUser(String username) {
        ArrayList<DonationLog> donationLogs = new ArrayList<>();
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("select * from riwayatdonasi where user_username = ?");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while(rs.next()){
                UUID id = UUID.fromString(rs.getString("id"));
                UUID campaignID = UUID.fromString(rs.getString("campaign_id"));
                User donor = AkunDAO.getUser(rs.getString("user_username"));
                int amount = rs.getInt("nominal");
                String date = rs.getString("tanggal");
                MetodePembayaran metodePembayaran = MetodePembayaranDAO.getMetodePembayaran(UUID.fromString(rs.getString("metode_pembayaran_id")));
                DonationLog donation = new DonationLog(donor, amount, date, id.toString(), metodePembayaran, campaignID);
                donationLogs.add(donation);
            }
            return donationLogs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDAO.closeConn(conn);
        }
    }

    private static ArrayList<MetodePembayaran> getAvailablePayment(UUID campaignId) {
        ArrayList<MetodePembayaran> availablePayment = new ArrayList<>();
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("select * from available_metode_pembayaran where campaign_id = ?");
            stmt.setString(1, campaignId.toString());
            rs = stmt.executeQuery();
            while(rs.next()){
                UUID id = UUID.fromString(rs.getString("metode_pembayaran_id"));
                MetodePembayaran payment = MetodePembayaranDAO.getMetodePembayaran(id);
                availablePayment.add(payment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return availablePayment;
    }
/*
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
                Image campaignImage = rs.getString(6);
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
*/
}
