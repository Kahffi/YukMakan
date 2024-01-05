/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import javafx.scene.image.Image;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;


import java.util.Scanner;

/**
 *
 * @author Kahffi
 */
public class Campaign {
    private Scanner scanner = new Scanner(System.in);
 
    private UUID id;
    private Image campaignImage;
    private String judul;
    private Admin creator;
    private String deskripsi;
    private String tanggal;
    private ArrayList <DonationLog> donationLogs = new ArrayList<>();
    private ArrayList <MetodePembayaran> listMetodePembayaran;
    private String endDate;
    private int targetDonasi;
    private int currentDonasi;
    
    //main constructor
    public Campaign (String judul, Admin creator, String deskripsi, String tanggal, String endDate, Image campaignImage, UUID id, int targetDonasi, int currentDonasi,
                     ArrayList <MetodePembayaran> listMetodePembayaran, ArrayList<DonationLog> riwayatDonasi){
        this.id = id;
        this.judul = judul;
        this.creator = creator;
        this.tanggal = tanggal;
        this.endDate = endDate;
        this.deskripsi = deskripsi;
        this.campaignImage = campaignImage;
        this.targetDonasi = targetDonasi;
        this.currentDonasi = currentDonasi;
        this.listMetodePembayaran = listMetodePembayaran;
        this.donationLogs = riwayatDonasi;
    }
    
    public Campaign (UUID id, String judul, String deskripsi, String tanggal, Image campaignImage, int targetDonasi){
        this.id = id;
        this.judul = judul;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
        this.campaignImage = campaignImage;
        this.targetDonasi = targetDonasi;
        this.currentDonasi = 0;
    }
    
    public Campaign(Admin Creator){
        this.creator = creator;
    }

    public void printCampaign(){
        System.out.println("ID : " + getId().toString());
        System.out.println(getCampaignImage());
        System.out.println(getJudul());
        System.out.println(getDeskripsi());
        System.out.println(getTanggal());
        System.out.println(getTargetDonasi());
        System.out.println(getCurrentDonasi());
        System.out.println(creator.getUsername());
    }
    
    public void addDonationLog(DonationLog donationLog) {
        if (donationLogs == null) {
            donationLogs = new ArrayList<>();
        }
        donationLogs.add(donationLog);
    }
    
    public int getCurrentDonasi() {
        return currentDonasi;
    }   
    //setter getter


    public ArrayList<DonationLog> getDonationLogs() {
        return donationLogs;
    }

    public void setDonationLogs(ArrayList<DonationLog> donationLogs) {
        this.donationLogs = donationLogs;
    }

    public void setListMetodePembayaran(ArrayList<MetodePembayaran> listMetodePembayaran) {
        this.listMetodePembayaran = listMetodePembayaran;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

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

    public Image getCampaignImage() {
        return campaignImage;
    }

    public void setCampaignImage(Image campaignImage) {
        this.campaignImage = campaignImage;
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
        return donationLogs;
    }

    public void setDonationLog(ArrayList<DonationLog> donatur) {
        this.donationLogs = donatur;
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


    public ArrayList<MetodePembayaran> getListMetodePembayaran() {
        return this.listMetodePembayaran;
    }

    public String currencyFormatter(int i){
        NumberFormat currencyFormat = DecimalFormat.getCurrencyInstance(Locale.forLanguageTag("id-ID"));
        return currencyFormat.format(i);
    }
    public String getFormattedTargetDonasi(){
        return currencyFormatter(targetDonasi);
    }
    public String getFormattedCurrentDonasi(){
        return currencyFormatter(currentDonasi);
    }

    public double getDonationProgress(){
        return (double) currentDonasi / targetDonasi;
    }
}
