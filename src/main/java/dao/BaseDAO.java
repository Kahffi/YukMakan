/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kahffi
 */
public class BaseDAO {
    protected static final String DB_NAME = "yukmakan";
    protected static final String DB_HOST = "localhost";
    protected static final String DB_USER = "PBO";
    protected static final String DB_PASS = "1234";
    
    public static Connection getConn(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + ":3306/" + DB_NAME, DB_USER, DB_PASS);
            System.out.println("Database connected");
            return conn;
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    public static void closeConn (Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static InputStream imageToInputStream(Image image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            javax.imageio.ImageIO.write(bufferedImage, "png", outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    public static String getImageType(InputStream is) {
        try {
            javax.imageio.ImageReader reader = ImageIO.getImageReaders(is).next();
            String formatName = reader.getFormatName();
            return formatName.toLowerCase();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    
}
