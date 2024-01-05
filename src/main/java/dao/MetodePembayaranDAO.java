package dao;

import model.Bank;
import model.MetodePembayaran;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class MetodePembayaranDAO {

    private static Connection conn;
    private static PreparedStatement stmt;
    private static ResultSet rs;

    public static void saveMetodePembayaran(MetodePembayaran m){
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("insert into metode_pembayaran values (?, ?, ?, ?)");
            stmt.setString(1, m.getId().toString());
            stmt.setString(2, m.getBank().getIdBank().toString());
            stmt.setString(3, m.getAtasNama());
            stmt.setString(4, m.getNomorRekening());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDAO.closeConn(conn);
        }
    }

    public static void updateMetodePembayaran(MetodePembayaran m){
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("update metode_pembayaran set bank_id = ?, nomor_rekening = ?, atas_nama = ? where id = ?");
            stmt.setString(1, m.getBank().getIdBank().toString());
            stmt.setString(2, m.getNomorRekening());
            stmt.setString(3, m.getAtasNama());
            stmt.setString(4, m.getId().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDAO.closeConn(conn);
        }
    }

    public static void deleteMetodePembayaran(MetodePembayaran m){
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("delete from metode_pembayaran where id = ?");
            stmt.setString(1, m.getId().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDAO.closeConn(conn);
        }
    }

    public static ArrayList<MetodePembayaran> getAllMetodePembayaran(){
        ArrayList<MetodePembayaran> metodePembayaranList = new ArrayList<>();
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("select * from metode_pembayaran");
            rs = stmt.executeQuery();
            while(rs.next()){
                MetodePembayaran m;
                UUID id = UUID.fromString(rs.getString("id"));
                Bank bank = BankDAO.getBank(UUID.fromString(rs.getString("bank_id")));
                String nomorRekening = rs.getString("nomor_rekening");
                String atasNama = rs.getNString("atas_nama");
                m = new MetodePembayaran(id, nomorRekening, atasNama, bank);
                metodePembayaranList.add(m);
            }
            return metodePembayaranList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static ArrayList<MetodePembayaran> getAllAvailablePayment(UUID id){
        ArrayList<MetodePembayaran> metodePembayaranList = new ArrayList<>();
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("select * from available_metode_pembayaran where campaign_id = ?");
            stmt.setString(1, id.toString());
            ResultSet RS = stmt.executeQuery();
            while(RS.next()){
                MetodePembayaran m = getMetodePembayaran(UUID.fromString(RS.getString("metode_pembayaran_id")));
                metodePembayaranList.add(m);
            }
            return metodePembayaranList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            BaseDAO.closeConn(conn);
        }

    }

    public static MetodePembayaran getMetodePembayaran(UUID id) {
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("select * from metode_pembayaran where id = ?");
            stmt.setString(1, id.toString());
            rs = stmt.executeQuery();
            if (rs.next()){
                return new MetodePembayaran(id, rs.getString("nomor_rekening"), rs.getString("atas_nama"), BankDAO.getBank(UUID.fromString(rs.getString("bank_id"))));
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            BaseDAO.closeConn(conn);
        }

    }
}
