package YukMakan;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Asus
 */
public class Akun {
    private Scanner scanner = new Scanner(System.in);
    
    public String username;
    private String password;
    private String nama;
    private String phoneNum;
    private String email;
    private ArrayList <KontenEdukasi> kontenEdukasi = new ArrayList <KontenEdukasi>();
    private ArrayList <Resep> resep = new ArrayList <Resep>();
    
    public Akun(String username, String password, String phoneNum, String nama, String email){
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.phoneNum = phoneNum;
        this.email = email;
    }
    
    public Akun(){
        
    }
    
    public void daftarAkun(){
        System.out.println("Daftar akun : ");
        System.out.println("Masukkan Username : ");
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

    public ArrayList<KontenEdukasi> getKontenEdukasi() {
        return kontenEdukasi;
    }

    public void setKontenEdukasi(ArrayList<KontenEdukasi> kontenEdukasi) {
        this.kontenEdukasi = kontenEdukasi;
    }

    public ArrayList<Resep> getResep() {
        return resep;
    }

    public void setResep(ArrayList<Resep> resep) {
        this.resep = resep;
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
    
    public int login(String username, String password){
        if (this.username == username && this.password == password){
            return 1;
        } 
        return 0;
    }
    
    // method untuk konfirmasi usn dan pass saat login
    public boolean loginVer(String username, String password){
        return this.username.equals(username) && this.password.equals(password);
    }
}
