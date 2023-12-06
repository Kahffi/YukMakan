/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kahffi
 */
public class Akun {
    
    private ArrayList <Resep> resepList = new ArrayList <>();
    private ArrayList <KontenEdukasi> kontenEduList = new ArrayList <>();
    private ArrayList <Campaign> campaignList = new ArrayList <>();
    private ArrayList <Ulasan> ulasanList = new ArrayList<>();
    
    
    private String username;
    private String password;
    private String nama;
    private String phoneNum;
    private String email;
    private String role;

    Scanner scanner = new Scanner (System.in);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public Akun(String username, String password, String nama, String phoneNum, String email, String role) {
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.phoneNum = phoneNum;
        this.email = email;
        this.role = role;
    }

    public Akun(ArrayList<Resep> resepList, ArrayList<KontenEdukasi> kontenEduList, ArrayList<Campaign> campaignList, String username, String password, String nama, String phoneNum, String email, String role) {
        this.resepList = resepList;
        this.kontenEduList = kontenEduList;
        this.campaignList = campaignList;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.phoneNum = phoneNum;
        this.email = email;
        this.role = role;
    }
    
    
    
    
    public void editProfile (){
        int start = 1;
        int menu;
        String newNama, newPhoneNum;
        while(start != 0){
           printProfile();
           System.out.println("1. Ubah nama\n2. Ubah nomor telepon\n3. Kembali");
           
            // Integer.parseInt digunakan untuk merubah String ke integer, ini dilakukan karena bila menggunakan nexInt maka scanner akan di-skip.
           menu = Integer.parseInt(scanner.nextLine()) ;
            switch (menu) {
                case 1:
                    System.out.println("Ubah nama : ");
                    newNama = scanner.nextLine();
                    setNama(newNama);
                    break;
                case 2:
                    System.out.println("Ubah nomor telepon : ");
                    newPhoneNum = scanner.nextLine();
                    setPhoneNum(newPhoneNum);
                    break;
                case 3:
                    start = 0;
                    break;
                default:
                    printInputError();
                    break;
            }
        }
    }
    
    public void printProfile(){
        System.out.println("Username : " + this.username);
        System.out.println("Nama : " + this.nama);
        System.out.println("Nomor telepon : " + this.phoneNum);
        System.out.println("Email : " + this.email);
    }

    // method untuk menambahkan objek kedalam arraylist agar user dan admin dapat melihat dan mengakses
    
    public void addToResepList (Resep resep){
        resepList.add(resep);
    }
    public void addToKontenEduList (KontenEdukasi kontenEd){
        kontenEduList.add(kontenEd);
    }
    public void addToCampaignList (Campaign campaign){
        campaignList.add(campaign);
    }
    public void addToUlasanList(Ulasan ulasan){
        ulasanList.add(ulasan);
    }
    
    // method untuk menghapus objek dari arraylist
    public void rmFromResepList(int index){
        resepList.remove(index);
    }
    public void rmFromKontenEduList(int index){
        kontenEduList.remove(index);
    }
    public void rmFromCampaignList(int index){
        campaignList.remove(index);
    }
    public void rmFromUlasanList(int index){
        ulasanList.remove(index);
    }
    
    
    
    
    // setter & getter
     
    public ArrayList<Resep> getResepList() {
        return resepList;
    }

    public void setResepList(ArrayList<Resep> resepList) {
        this.resepList = resepList;
    }

    public ArrayList<KontenEdukasi> getKontenEduList() {
        return kontenEduList;
    }

    public void setKontenEduList(ArrayList<KontenEdukasi> kontenEduList) {
        this.kontenEduList = kontenEduList;
    }

    public ArrayList<Campaign> getCampaignList() {
        return campaignList;
    }

    public void setCampaignList(ArrayList<Campaign> campaignList) {
        this.campaignList = campaignList;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    // misc method
    
    //method agar memungkinkan copy paste paragraph dan juga memasukan input dengan garis baru
    public String createParagraph(){
        StringBuilder sb = new StringBuilder();
        String str;
        try {
            System.out.println("start");
            while (!(str = br.readLine()).isEmpty()){
                System.out.println("yok");
                sb.append(str).append("\n");
            }
            System.out.println(sb.toString().length());
            return sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(Akun.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void printInputError(){
        System.out.println("Input yang anda masukkan salah");
    }
    // mengambil tanggal dan jam saat ini
    public String getDate(){
        String date;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime current = LocalDateTime.now();
        date = dtf.format(current);
        return date;
    }
}
