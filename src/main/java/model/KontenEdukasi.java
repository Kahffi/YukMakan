/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.UUID;


import java.util.Scanner;
/**
 *
 * @author Kahffi
 */
public class KontenEdukasi {
    
    private Scanner scanner = new Scanner(System.in);
    
    private UUID id;
    private String imagePath;
    private String judul;
    private String content;
    private String tanggal;
    private Admin uploader;
    
    //main constructor
    public KontenEdukasi(String imagePath, String judul, String content, String tanggal, Admin uploader, String strID) {
        this.id = UUID.fromString(strID);
        this.imagePath = imagePath;
        this.judul = judul;
        this.content = content;
        this.tanggal = tanggal;
        this.uploader = uploader;
    }

    public KontenEdukasi(UUID id, String imagePath, String judul, String content, String tanggal) {
        this.id = id;
        this.imagePath = imagePath;
        this.judul = judul;
        this.content = content;
        this.tanggal = tanggal;
    }

    public KontenEdukasi (Admin uploader){
        this.uploader = uploader;
    }
    
    
    
    
    
    
    // print atribut
    public void printKontenEdu(){
        System.out.println("ID : " + getId().toString());
        System.out.println(getImagePath());
        System.out.println(getJudul());
        System.out.println(getContent());
        System.out.println(getTanggal());
        System.out.println(uploader.getUsername());
    }

    
    

    //setter & getter
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    
    
    // misc method
    public Admin getUploader() {
        return uploader;
    }

    public void setUploader(Admin uploader) {
        this.uploader = uploader;
    }
    
    
}
