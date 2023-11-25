/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

/**
 *
 * @author Kahffi
 */
public class Admin extends Akun{
    
    private ArrayList <KontenEdukasi> createdKontenEdu = new ArrayList <>();
    private ArrayList <Campaign> createdCampaign = new ArrayList <>();
    private ArrayList <Resep> createdResep = new ArrayList <>();
    
    
    //constructor
    public Admin(String username, String password, String nama, String phoneNum, String email, String role) {
        super(username, password, nama, phoneNum, email, role);
    }
    public Admin(ArrayList<KontenEdukasi> createdKontenEdu, ArrayList<Campaign> createdCampaign, ArrayList<Resep> createdResep, String username, String password, String nama, String phoneNum, String email, String role) {
        super(username, password, nama, phoneNum, email, role);
        this.createdKontenEdu = createdKontenEdu;
        this.createdCampaign = createdCampaign;
        this.createdResep = createdResep;
    }
    public Admin(ArrayList<KontenEdukasi> createdKontenEdu, ArrayList<Campaign> createdCampaign, ArrayList<Resep> createdResep, ArrayList<Resep> resepList, ArrayList<KontenEdukasi> kontenEduList, ArrayList<Campaign> campaignList, String username, String password, String nama, String phoneNum, String email, String role) {
        super(resepList, kontenEduList, campaignList, username, password, nama, phoneNum, email, role);
        this.createdKontenEdu = createdKontenEdu;
        this.createdCampaign = createdCampaign;
        this.createdResep = createdResep;
    }
    
    
    // menu 
    // belakangan aja dikerjainnya
    public void adminMenu(){
        int start = 1;
        int menu;
        while(start != 0){
            System.out.println("======== Admin Main Menu ========");
            System.out.println("1. Lihat Profile\n2. Buat Resep/Konten Edukasi/Campaign\n3. Edit Resep/Konten Edukasi/Campaign");
            System.out.println("4. Lihat Resep/Konten Edukasi/Campaign Yang Telah Saya Buat\n 5. Daftarkan admin baru");
            menu = Integer.parseInt(scanner.nextLine());
            if (menu == 1){
                
            }
            
        }
    }
    
    private void menuProfile(){
        int start = 1;
        int menu;
        while(start != 0){
            System.out.println("======== Profile ========");
            System.out.println("Username: " + getUsername());
            
            menu = Integer.parseInt(scanner.nextLine());
            if (menu == 1){
                
            }
            
        }
    }










//create konten edukasi
    public KontenEdukasi createKontenEdukasi () {
        KontenEdukasi k;
        k = new KontenEdukasi (this);
        k.setId(generateUUID());
        System.out.println("Judul konten edukasi: ");
        k.setJudul(scanner.nextLine());
        System.out.println("Konten edukasi : ");
        k.setContent(super.createParagraph());
        System.out.println("Sumber gambar : ");
        k.setImagePath(scanner.nextLine());
        k.setTanggal(super.getDate());
        super.addToKontenEduList(k);
        return k;
    }
   
    // method update kontenEdukasi
    public KontenEdukasi updateKontenEduDesc(KontenEdukasi k){
        k.printKontenEdu();
        System.out.println("Masukkan isi konten edukasi yang baru : ");
        String newContent = super.createParagraph();
        k.setContent(newContent);
        System.out.println("Isi konten edukasi berhasil diubah");
        k.printKontenEdu();
        return k;
    }
    public KontenEdukasi updateKontenEduJudul(KontenEdukasi k){
        k.printKontenEdu();
        System.out.println("Masukkan judul konten edukasi yang baru : ");
        String newContent = super.createParagraph();
        k.setContent(newContent);
        System.out.println("Judul Berhasil diubah");
        k.printKontenEdu();
        return k;
    }    
    // ini entah bener atau engga naronya begini
    public KontenEdukasi delKontenEdukasi (KontenEdukasi k){
        return k = null;
    }
    
//create 
    
    
    // setter & getter
    
    public void addCreatedKontenEdu(KontenEdukasi k){
        createdKontenEdu.add(k);
    }
    
    public ArrayList<KontenEdukasi> getCreatedKontenEdu() {
        return createdKontenEdu;
    }

    public void setCreatedKontenEdu(ArrayList<KontenEdukasi> createdKontenEdu) {
        this.createdKontenEdu = createdKontenEdu;
    }

    public ArrayList<Campaign> getCreatedCampaign() {
        return createdCampaign;
    }

    public void setCreatedCampaign(ArrayList<Campaign> createdCampaign) {
        this.createdCampaign = createdCampaign;
    }

    public ArrayList<Resep> getCreatedResep() {
        return createdResep;
    }

    public void setCreatedResep(ArrayList<Resep> createdResep) {
        this.createdResep = createdResep;
    }
    
    //misc method
    
    // method generate UUID yang digunakan untuk create konten, campaign, resep
    public UUID generateUUID(){
        return UUID.randomUUID();
    }
}
