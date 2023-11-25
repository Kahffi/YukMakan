/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Akun;
import model.User;
import model.Admin;

/**
 *
 * @author Kahffi
 */
public class AkunDAO {
    private static Connection conn;
    private static PreparedStatement stmt;
    private static ResultSet rs;
    
    // register admin dan user belum dilakukan verifikasi username;
    
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
    
    public static Admin getAdmin(String usn){
        String query = "Select * from akun where username = '%s'";
        query = String.format(query, usn);
        try {
            conn = BaseDAO.getConn();
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            
            if (!rs.next()){
                String username, password, nama, phoneNum, email, role;
                
                username = rs.getString(1);
                password = rs.getString(2);
                nama = rs.getString(3);
                phoneNum = rs.getString(4);
                email = rs.getString(5);
                role = rs.getString(6);
                conn.close();
                Admin admin = new Admin (username, password, nama, phoneNum, email, role);
                return admin;
                
            }
            else{
                System.out.println("data tidak ditemukan");
                return null;
            }
            
        } catch (SQLException ex) {
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
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(KontenEduDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("update gagal");
            return false;
        } 
    }
    
    
    private static void registerAccount(String query){
        try {
            System.out.println(query);
            conn = BaseDAO.getConn();
            stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AkunDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
}
