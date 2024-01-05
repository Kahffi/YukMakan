/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Objects;
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
    Image fotoResep;
    String datePosted;
    Admin uploader;
    
    //Arraylist untuk menyimpan ulasan
    ArrayList <Ulasan> ulasan = new ArrayList <>();
    //constructor lengkap
    public Resep(UUID id, String judul, String deskripsi, String langkah, String bahan, String kandunganGizi, Image fotoResep, String datePosted, Admin uploader, ArrayList<Ulasan> ulasan) {
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.langkah = langkah;
        this.bahan = bahan;
        this.kandunganGizi = kandunganGizi;
        this.fotoResep = fotoResep;
        this.datePosted = datePosted;
        this.uploader = uploader;
        this.ulasan = ulasan;
    }

    // constructor tanpa ulasan
    public Resep(UUID id, String judul, String deskripsi, String langkah, String bahan, String kandunganGizi, Image fotoResep, String datePosted, Admin uploader) {
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.langkah = langkah;
        this.bahan = bahan;
        this.kandunganGizi = kandunganGizi;
        this.fotoResep = fotoResep;
        this.datePosted = datePosted;
        this.uploader = uploader;
    }
    // constructor tanpa foto
    public Resep(UUID id, String judul, String deskripsi, String langkah, String bahan, String kandunganGizi, String datePosted, Admin uploader, ArrayList<Ulasan> ulasan) {
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.langkah = langkah;
        this.bahan = bahan;
        this.kandunganGizi = kandunganGizi;
        this.datePosted = datePosted;
        this.uploader = uploader;
        this.ulasan = ulasan;
    }

    // constructor tanpa ulasan dan foto
    public Resep(UUID id, String judul, String deskripsi, String langkah, String bahan, String kandunganGizi, String datePosted, Admin uploader) {
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.langkah = langkah;
        this.bahan = bahan;
        this.kandunganGizi = kandunganGizi;
        this.datePosted = datePosted;
        this.uploader = uploader;
    }
    public void printResep(){
        System.out.println("======= " + getJudul() + " =======");
        System.out.println(getFotoResep());
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
    // Di kelas Resep
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Resep otherResep = (Resep) obj;
        return Objects.equals(id, otherResep.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    public Image getFotoResep() {
        return fotoResep;
    }

    public void setFotoResep(Image fotoResep) {
        this.fotoResep = fotoResep;
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
