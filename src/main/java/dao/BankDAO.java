package dao;

import model.Bank;
import utils.ImageUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class BankDAO {
    private static Connection conn;
    private static PreparedStatement stmt;
    private static ResultSet rs;

    public static void saveBank(Bank b){
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("insert into bank values (?,?,?)");
            stmt.setString(1, b.getIdBank().toString());
            stmt.setString(2, b.getNamaBank());
            stmt.setBlob(3, ImageUtils.imageToInputStream(b.getLogoBank()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDAO.closeConn(conn);
        }

    }

    public static void updateBank(Bank b){
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("update bank set nama_bank = ?, logo_bank = ? where id = ?");
            stmt.setString(1, b.getNamaBank());
            stmt.setBlob(2, ImageUtils.imageToInputStream(b.getLogoBank()));
            stmt.setString(3, b.getIdBank().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDAO.closeConn(conn);
        }
    }

    public static void deleteBank(Bank b){
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("delete from bank where id = ?");
            stmt.setString(1, b.getIdBank().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDAO.closeConn(conn);
        }
    }

    public static ArrayList<Bank> getAllBanks(){
        conn = BaseDAO.getConn();
        ArrayList<Bank> banks = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("select * from bank");
            rs = stmt.executeQuery();
            while (rs.next()){
                banks.add(new Bank(UUID.fromString(rs.getString("id")), rs.getString("nama_bank"), ImageUtils.inputStreamToImage(rs.getBlob("logo_bank").getBinaryStream())));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDAO.closeConn(conn);
        }
        return banks;
    }

    public static Bank getBank(UUID id){
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("select * from bank where id = ?");
            stmt.setString(1, id.toString());
            rs = stmt.executeQuery();
            if(rs.next()){
                return new Bank(UUID.fromString(rs.getString("id")), rs.getString("nama_bank"), ImageUtils.inputStreamToImage(rs.getBlob("logo_bank").getBinaryStream()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            BaseDAO.closeConn(conn);
        }
        return null;
    }
}
