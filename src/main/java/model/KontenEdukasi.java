/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.ArrayList;
import java.util.UUID;


import java.util.Scanner;
import javafx.scene.image.Image;
/**
 *
 * @author Kahffi
 */
public class KontenEdukasi {
    
    
    
    UUID id;
    Image imagePath;
    String judul;
    String content;
    String tanggal;
    Admin uploader;
    ArrayList <Ulasan> ulasan = new ArrayList <>();
    
    //main constructor
    public KontenEdukasi(UUID id, String judul, String content, String tanggal, Image imagePath, Admin uploader, ArrayList <Ulasan> ulasan) {
        this.id = id;
        this.judul = judul;
        this.content = content;
        this.tanggal = tanggal;
        this.uploader = uploader;
        this.imagePath = imagePath;
        this.ulasan = ulasan;
    }
    // without ulasan
    public KontenEdukasi(UUID id, Image imagePath, String judul, String content, String tanggal, Admin uploader) {
        this.id = id;
        this.judul = judul;
        this.content = content;
        this.tanggal = tanggal;
        this.uploader = uploader;
        this.imagePath = imagePath;
    }
    //tanpa foto
     public KontenEdukasi(UUID id, String judul, String content, String tanggal, Admin uploader, ArrayList <Ulasan> ulasan) {
        this.id = id;
        this.judul = judul;
        this.content = content;
        this.tanggal = tanggal;
        this.uploader = uploader;
        this.ulasan = ulasan;
    }
    // tanpa foto dan ulasan
    public KontenEdukasi(UUID id, String judul, String content, String tanggal, Admin uploader) {
        this.id = id;
        this.judul = judul;
        this.content = content;
        this.tanggal = tanggal;
        this.uploader = uploader;
    }

    public KontenEdukasi (Admin uploader){
        this.uploader = uploader;
    }
    
    
    public void addUlasan(Ulasan u){
        this.ulasan.add(u);
    }

    public ArrayList<Ulasan> getUlasan() {
        return ulasan;
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

    public Image getImagePath() {
        return imagePath;
    }

    public void setImagePath(Image imagePath) {
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
