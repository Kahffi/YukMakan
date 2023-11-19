package model;

import java.util.ArrayList;
import java.util.Scanner;

public class User extends Akun {
    // arraylist digunakan untuk mempermudah proses modifikasi array
    ArrayList <Resep> daftarFavorit = new ArrayList<>();
    ArrayList<Campaign> historyDonasi = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    ArrayList<Ulasan> ulasanList = new ArrayList<>();



    public User(String username, String password, String phoneNum, String nama, String email){
        super(username, password, phoneNum, nama, email);
    }

    public User(){}
    
    //method untuk menambahkan resep kedalam daftar favorit
    public void addToFav(Resep resep){
        //menambahkan resep ke dalam daftar favorit
        daftarFavorit.add(resep);
    }
    public void delFav(int index){
        daftarFavorit.remove(index);
    }
    
    // Method untuk berdonasi
    public void donate(Campaign campaign, int nominalDonasi){
        campaign.setDonatur(this);
        campaign.setCurrentDonasi(nominalDonasi);
        addToDonationHistory(campaign);
    }
    
    // Method untuk histori donasi user
    public ArrayList<String> getDonationHistory() {
        ArrayList<String> donationHistory = new ArrayList<>();
        for (Campaign campaign : historyDonasi) {
            donationHistory.add("Campaign: " + campaign.getJudul() + ", Nominal Donasi: " + campaign.getCurrentDonasi());
        }
        return donationHistory;
    }
    
    // method untuk add history donasi
    public void addToDonationHistory(Campaign campaign) {
        historyDonasi.add(campaign);
    }
    
   
    
    // Method untuk melakukan ulasan
    public void createUlasan(String ulasan, String tanggalUlasan){
        System.out.print("Masukkan ulasan Anda: ");
        ulasan = input.nextLine();
        System.out.print("tanggal: ");
        tanggalUlasan = input.nextLine(); 
        Ulasan userUlasan = new Ulasan(this, ulasan, tanggalUlasan);
        ulasanList.add(userUlasan);
    }
    
    
    
    
    
}
