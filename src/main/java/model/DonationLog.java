/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.UUID;
/**
 *
 * @author Kahffi
 */
public class DonationLog {
    private UUID id;
    private User donor; 
    private int amount;
    private String tanggal;

    // Constructor
    public DonationLog(User donor, int amount, String tanggal, String strID) {
        this.id = UUID.fromString(strID);
        this.donor = donor;
        this.amount = amount;
        this.tanggal = tanggal;
    }

    public DonationLog(Campaign campaign, User aThis, int amount) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getter methods
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