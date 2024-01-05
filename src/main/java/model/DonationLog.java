/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.UUID;
/**
 *
 * @author Kahffi
 */
public class DonationLog {
    private UUID id;
    private User donor;
    private UUID campaignID;
    private int amount;
    private MetodePembayaran metodePembayaran;
    private String tanggal;

    // Constructor
    public DonationLog(User donor, int amount, String tanggal, String strID, MetodePembayaran metodePembayaran, UUID campaignID) {
        this.id = UUID.fromString(strID);
        this.metodePembayaran = metodePembayaran;
        this.donor = donor;
        this.amount = amount;
        this.tanggal = tanggal;
        this.campaignID = campaignID;
    }

    DonationLog(Campaign campaign, User aThis, int amount) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String currencyFormatter(int i){
        NumberFormat currencyFormat = DecimalFormat.getCurrencyInstance(Locale.forLanguageTag("id-ID"));
        return currencyFormat.format(i);
    }

    public String getFormattedAmount(){
        return currencyFormatter(this.amount);
    }
    // Getter methods


    public void setId(UUID id) {
        this.id = id;
    }

    public void setDonor(User donor) {
        this.donor = donor;
    }

    public UUID getCampaignID() {
        return campaignID;
    }

    public void setCampaignID(UUID campaignID) {
        this.campaignID = campaignID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public MetodePembayaran getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(MetodePembayaran metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public UUID getId() {
        return id;
    }

    public User getDonor() {
        return donor;
    }

    public int getAmount() {
        return amount;
    }

    public String getTanggal() {
        return tanggal;
    }
}
