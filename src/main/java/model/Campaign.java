/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.ArrayList;
import java.util.UUID;


import java.util.Scanner;

/**
 *
 * @author Kahffi
 */
public class Campaign {
    private Scanner scanner = new Scanner(System.in);
 
    private UUID id;
    private String imagePath;
    private String judul;
    private Admin creator;
    private String deskripsi;
    private String tanggal;
    private ArrayList <DonationLog> donatur = new ArrayList<>();
    private int targetDonasi;
    private int currentDonasi;
    
    //main constructor
    public Campaign (String judul, Admin creator, String deskripsi, String tanggal, String imagePath, String strID, int targetDonasi){
        this.id = UUID.fromString(strID);
        this.judul = judul;
        this.creator = creator;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
        this.imagePath = imagePath;
        this.targetDonasi = targetDonasi;
        this.currentDonasi = 0;
    }
    
    public Campaign (UUID id, String judul, String deskripsi, String tanggal, String imagePath, int targetDonasi){
        this.id = id;
        this.judul = judul;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
        this.imagePath = imagePath;
        this.targetDonasi = targetDonasi;
        this.currentDonasi = 0;
    }
    
    public Campaign(Admin Creator){
        this.creator = creator;
    }

    public void printCampaign(){
        System.out.println("ID : " + getId().toString());
        System.out.println(getImagePath());
        System.out.println(getJudul());
        System.out.println(getDeskripsi());
        System.out.println(getTanggal());
        System.out.println(getTargetDonasi());
        System.out.println(getCurrentDonasi());
        System.out.println(creator.getUsername());
    }
    
    public void addDonationLog(DonationLog donationLog) {
        if (donatur == null) {
            donatur = new ArrayList<>();
        }
        donatur.add(donationLog);
    }
    
    public int getCurrentDonasi() {
        int totalDonasi = 0;

        // Iterate through the list of DonationLog and sum up the donation amounts
        for (DonationLog donationLog : donatur) {
            totalDonasi += donationLog.getAmount();
        }

        return totalDonasi;
    }   
    //setter getter
    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Admin getCreator() {
        return creator;
    }

    public void setCreator(Admin creator) {
        this.creator = creator;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public ArrayList<DonationLog> getDonationLog() {
        return donatur;
    }

    public void setDonationLog(ArrayList<DonationLog> donatur) {
        this.donatur = donatur;
    }

    public int getTargetDonasi() {
        return targetDonasi;
    }

    public void setTargetDonasi(int targetDonasi) {
        this.targetDonasi = targetDonasi;
    }

    public void setCurrentDonasi(int currentDonasi) {
        this.currentDonasi = currentDonasi;
    }
    
    
}
