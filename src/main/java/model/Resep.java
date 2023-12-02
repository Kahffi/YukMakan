/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Kahffi
 */
public class Resep {
    UUID id;
    String judul;
    String deskripsi;
    String langkah;
    String bahan;
    String kandunganGizi;
    String imagePath;
    String datePosted;
    Admin uploader;
    
    //Arraylist untuk menyimpan ulasan
    ArrayList <Ulasan> ulasan = new ArrayList <>();

    // constructor
    public Resep(UUID id, String judul, String deskripsi, String langkah, String bahan, String kandunganGizi, String imagePath, String datePosted, Admin uploader) {
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.langkah = langkah;
        this.bahan = bahan;
        this.kandunganGizi = kandunganGizi;
        this.imagePath = imagePath;
        this.datePosted = datePosted;
        this.uploader = uploader;
    }
    
    
    public void printResep(){
        System.out.println("======= " + getJudul() + " =======");
        System.out.println(getImagePath());
        System.out.println("Deskripsi: ");
        System.out.println(getDeskripsi());
        System.out.println("Bahan: ");
        System.out.println(getBahan());
        System.out.println("Langkah: ");
        System.out.println(getLangkah());
        System.out.println("Kandungan gizi: ");
        System.out.println(getKandunganGizi());
        System.out.println("Diupload oleh: " + getUploader().getUsername() + ", Pada tanggal: " + getDatePosted());
    }
    
    //menambahkan ulasan kedalam resep
    public void addUlasan(Ulasan u){
        this.ulasan.add(u);
    }
    
    //setter & getter 

    public ArrayList<Ulasan> getUlasan() {
        return ulasan;
    }

    public void setUlasan(ArrayList<Ulasan> ulasan) {
        this.ulasan = ulasan;
    }
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getLangkah() {
        return langkah;
    }

    public void setLangkah(String langkah) {
        this.langkah = langkah;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public String getKandunganGizi() {
        return kandunganGizi;
    }

    public void setKandunganGizi(String kandunganGizi) {
        this.kandunganGizi = kandunganGizi;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public Admin getUploader() {
        return uploader;
    }

    public void setUploader(Admin uploader) {
        this.uploader = uploader;
    }
    
    
    
}