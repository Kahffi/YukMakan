/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.image.Image;
import model.Resep;
import model.Ulasan;
import model.User;
import model.Admin;
import utils.ImageUtils;

/**
 *
 * @author Kahffi
 */
public class AkunDAO {
    private static Connection conn;
    private static PreparedStatement stmt;
    private static ResultSet rs;
    
    // register admin dan user belum dilakukan verifikasi username;
    
    /*
    
    public static void registerAdmin(Admin admin){
        String query = "insert into akun values ('%s', '%s', '%s', '%s', '%s', '%s')";
        query = String.format(query, admin.getUsername(),
                admin.getPassword(), admin.getNama(), admin.getPhoneNum(),
                admin.getEmail(), admin.getRole()
        );
        registerAccount(query);
    }
    
    public static void registerUser(User user){
        String values = " '%s', '%s', '%s', '%s', '%s', '%s'";
        values = String.format(values, user.getUsername(),
                user.getPassword(), user.getNama(), user.getPhoneNum(),
                user.getEmail(), user.getRole()
        );
        registerAccount(values);
    }
    */
    

    public static int addFavorite(String id_resep, String user_username){
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("insert into daftarfavorit values (?, ?)");
            stmt.setString(1, user_username);
            stmt.setString(2, id_resep);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            return -1;
        } finally {
            BaseDAO.closeConn(conn);
        }
    }
    public static ArrayList <Resep> getDaftarFav(String usn){
        conn = BaseDAO.getConn();
        try {
            stmt = conn.prepareStatement("select * from daftarfavorit where user_username = ?");
            stmt.setString(1, usn);
            rs = stmt.executeQuery();
            ArrayList <Resep> daftarFavorit = new ArrayList <>();
            while (rs.next()){
                daftarFavorit.add(ResepDAO.getResep(rs.getString("id_resep")));
            }
            return daftarFavorit;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getAccRole(String usn) {
    	String query = "Select role from akun where username = '%s'";
    	query = String.format(query, usn);
    	conn = BaseDAO.getConn();
    	try {
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println("Account found");
				return rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "";
    	
    }
    
    public static User getUser(String usn){
        User us;
         String query = "Select * from akun where username = '%s'";
        query = String.format(query, usn);
        try {
            conn = BaseDAO.getConn();
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            
            if (rs.next()){
                String username, password, nama, phoneNum, email, role;
                InputStream profilePict;
                username = rs.getString(1);
                password = rs.getString(2);
                nama = rs.getString(3);
                phoneNum = rs.getString(4);
                email = rs.getString(5);
                role = rs.getString(6);
                profilePict = rs.getBinaryStream(7);
                conn.close();
                if (profilePict != null){
                    us = new User (username, password, nama, phoneNum, email, role, new Image(profilePict));
                    profilePict.close();
                    return us;
                }
                else {
                     us = new User(username, password, nama, phoneNum, email, role);
                     return us;
                }
            }
            else{
                System.out.println("data tidak ditemukan");
                return null;
            }
            
        } catch (SQLException | IOException ex) {
            Logger.getLogger(KontenEduDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("terjadi error");
            return null;
        }
    }
    
    public static Admin getAdmin(String usn){
        Admin admin;
        String query = "Select * from akun where username = '%s'";
        query = String.format(query, usn);
        try {
            conn = BaseDAO.getConn();
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            
            if (rs.next()){
                String username, password, nama, phoneNum, email, role;
                InputStream profilePict;

                username = rs.getString(1);
                password = rs.getString(2);
                nama = rs.getString(3);
                phoneNum = rs.getString(4);
                email = rs.getString(5);
                role = rs.getString(6);
                if (rs.getBinaryStream(7)!= null){
                    profilePict = rs.getBinaryStream(7);
                    admin = new Admin (username, password, nama, phoneNum, email, role, new Image(profilePict));
                    profilePict.close();
                }
                else{
                    admin = new Admin(username, password, nama, phoneNum, email, role);
                }
                conn.close();
                return admin;            
            }
            else{
                System.out.println("data tidak ditemukan");
                return null;
            }
            
        } catch (SQLException | IOException ex) {
            Logger.getLogger(KontenEduDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("terjadi error");
            return null;
        }
    }
    
    public static boolean updateNama(String usn, String newNama){
        String query = "update akun set nama = '%s' where username  = '%s'";
        query = String.format(query, newNama, usn);
        conn = BaseDAO.getConn();
        try {
        	conn = BaseDAO.getConn();
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            System.out.println(query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(KontenEduDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("update gagal");
            return false;
        } 
    }
    
    public static boolean updatePhoneNum(String usn, String newPhone){
        String query = "update akun set phoneNum = '%s' where username  = '%s'";
        query = String.format(query, newPhone, usn);
        conn = BaseDAO.getConn();
        try {
        	conn = BaseDAO.getConn();
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(KontenEduDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("update gagal");
            return false;
        } finally {
			BaseDAO.closeConn(conn);
		}
    }
    
	public static boolean updateAccount(String name, String phone, String username) {
		String query = "update akun set phoneNum = '%s', nama = '%s' where username = '%s'";
		query = String.format(query, phone, name, username);
		try {
			conn = BaseDAO.getConn();
			stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Terjadi kesalahan saat update akun");
			return false;
		}
		
	}
    
    public static void registerAccount(String usn, String pass, String nama, String phone, String email, String role){
        try {
        	String query = "insert into yukmakan.akun (username, password, nama, phoneNum, email, role)values ('%s', '%s', '%s', '%s', '%s', '%s')";
            query = String.format(query, usn, pass, nama, phone, email, role);
            System.out.println(query);
            conn = BaseDAO.getConn();
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AkunDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static boolean validateAcc (String usn, String pass) {
    	try {
    		String query = "select * from akun where username = '%s' AND password = '%s'";
    		query = String.format(query, usn, pass);
    		conn = BaseDAO.getConn();
    		stmt = conn.prepareStatement(query);
    		rs = stmt.executeQuery();
    		if (rs.next()) {
				return true;
			}
    		else {
				return false;
			}
    		
    	} catch(SQLException ex) {
    		Logger.getLogger(AkunDAO.class.getName()).log(Level.SEVERE, null, ex);
    		return false;
    	}
    }
    
    public static void setPicture (String usn, Image img) {

    	conn = BaseDAO.getConn();
    	try {
			stmt = conn.prepareStatement("update akun set profilePicture = ? where username = ?");
			stmt.setBlob(1, ImageUtils.imageToInputStream(img));
            stmt.setString(2, usn);
			stmt.executeUpdate();
			System.out.println(img);
			System.out.println(stmt.toString());
			System.out.println("Foto berhasil ditambahkan");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			BaseDAO.closeConn(conn);
		}
    	
    }
    
    public static InputStream getProfilePict (String usn) {
    	
    	conn = BaseDAO.getConn();
    	try {
			stmt = conn.prepareStatement("Select profilePicture from akun where username = ?");
			stmt.setString(1, usn);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getBinaryStream("profilePicture");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDAO.closeConn(conn);
		}
    	
    	
    	return null;
    	
    }
    
}
