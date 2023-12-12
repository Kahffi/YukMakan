/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

import java.util.UUID;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Kahffi
 */
public class User extends Akun{
    
    private ArrayList <Resep> daftarFavorit;
    private ArrayList <DonationLog> riwayatDonasi;
    private ArrayList <Ulasan> createdUlasan = new ArrayList <>();
    

    public User(String username, String password, String nama, String phoneNum, String email, String role) {
        super(username, password, nama, phoneNum, email, role);
    }

    public User(ArrayList<Resep> daftarFavorit, ArrayList <Ulasan> createdUlasan, String username, String password, String nama,
            String phoneNum, String email, String role, ArrayList<Resep> resepList, ArrayList<KontenEdukasi> kontenEduList,
            ArrayList<Campaign> campaignList, ArrayList <DonationLog> riwayatDonasi) {
        super(resepList, kontenEduList, campaignList, username, password, nama, phoneNum, email, role);
        this.daftarFavorit = daftarFavorit;
        this.riwayatDonasi = riwayatDonasi;
        this.createdUlasan = createdUlasan;
    }
    
    public User(ArrayList<Resep> daftarFavorit, String username, String password, String nama, String phoneNum, String email, String role) {
        super(username, password, nama, phoneNum, email, role);
        this.daftarFavorit = daftarFavorit;
    }
    
    public Ulasan createUlasan(){
        Ulasan u;
        u = new Ulasan(this);
        u.setId(generateUUID());
        System.out.println("masukkan ulasan:");
        String newUlasan = super.createParagraph();
        u.setUlasan(newUlasan);
        System.out.println("ulasan berhasil diubah");
        u.printUlasan();
        return u;
    }
    
    private String getCurrentTimestampAsString() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return currentDateTime.format(formatter);
    }
    
    public void donateToCampaign(Campaign campaign, int amount) {
        String currentTimestamp = getCurrentTimestampAsString();
        DonationLog donationLog = new DonationLog(campaign, this, amount);
        riwayatDonasi.add(donationLog);
        campaign.addDonationLog(donationLog);
        campaign.setCurrentDonasi(campaign.getCurrentDonasi() + amount);
        System.out.println("Donation successful!");
    }
    
    //method untuk menambahkan resep kedalam daftar favorit
    public void addToFav(Resep resep){
        //menambahkan resep ke dalam daftar favorit
        daftarFavorit.add(resep);
    }
    public void delFav(int index){
        daftarFavorit.remove(index);
    }
    
    public void setCreatedUlasan(ArrayList <Ulasan> createdUlasan){    
        this.createdUlasan = createdUlasan;
    }
    
    public ArrayList<Ulasan> getCreatedUlasan() {    
        return createdUlasan;
    }

    //setter & getter
    public void addCreatedUlasan(Ulasan u) {    
        createdUlasan.add(u);
    }
    

    public ArrayList<Resep> getDaftarFavorit() {
        return daftarFavorit;
    }

    public ArrayList<DonationLog> getRiwayatDonasi() {
        return riwayatDonasi;
    }

    public void setRiwayatDonasi(ArrayList<DonationLog> riwayatDonasi) {
        this.riwayatDonasi = riwayatDonasi;
    }
    
    public void setDaftarFavorit(ArrayList<Resep> daftarFavorit) {
        this.daftarFavorit = daftarFavorit;
    }
    public UUID generateUUID(){
        return UUID.randomUUID();}
}