/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Kahffi
 */
public class Ulasan{
    // Atribut
    private User user;
    private String ulasan;
    private String tanggalUlasan;
    private Scanner scanner = new Scanner(System.in);
    private UUID id;
    
    // Main Constructor
    public Ulasan(User user, String ulasan, String tanggalUlasan, String strID){
        this.id = UUID.fromString(strID);
        this.user = user;
        this.ulasan = ulasan;
        this.tanggalUlasan = ulasan;
    }  
    public Ulasan(UUID id, String ulasan, String tanggalUlasan){
        this.ulasan = ulasan;
        this.tanggalUlasan = ulasan;
        this.id = id;     
    }
    public Ulasan(User user){
        this.user = user;
    }

    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    } 
    // Getter dan setter
    public String getUlasan(){
        return ulasan;
    }
    public String getTanggalUlasan(){
        return tanggalUlasan;
    }  
    public String getDate(){
        String date;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime current = LocalDateTime.now();
        date = dtf.format(current);
        return date;
    }
    public String getUlasanUsername(){
        return user.getUsername();
    }
    public void setUlasanUser(User user){
        this.user = user;
    }
    public void setUlasan(String ulasan){
        this.ulasan = ulasan;
    }
    public void setTanggalUlasan(String tanggalUlasan){
        this.tanggalUlasan = tanggalUlasan;
    }
    // Method untuk print ulasan
    public void printUlasan(){
        System.out.println("ID : "+getId().toString());
        System.out.println("Username:"+ getUlasanUsername() + "\nUlasan: " + this.ulasan
                +"\nTanggal Ulasan: " + this.tanggalUlasan);
    }
    // method edit ulasan
    public void editUlasan(Akun akn){
        printUlasan();
        System.out.println("Masukkan ulasan yang ingin diedit");
        String newUlasan = akn.createParagraph();
        setUlasan(newUlasan);
        System.out.println("Ulasan berhasil diupdate");
        printUlasan();
    }
}
