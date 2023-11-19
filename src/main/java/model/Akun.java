package model;


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

    private Scanner input = new Scanner(System.in);
    public String username;
    private String password;
    private String nama;
    private String phoneNum;
    private String email;
    private ArrayList <KontenEdukasi> kontenEdukasi = new ArrayList <KontenEdukasi>();
    private ArrayList <Resep> resep = new ArrayList <Resep>();
    private ArrayList <User> users = new ArrayList <User> ();
    private ArrayList <Admin> admins = new ArrayList <>();

    ArrayList<Ulasan> ulasanList = new ArrayList<>();

    
    public Akun(String username, String password, String phoneNum, String nama, String email){
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.phoneNum = phoneNum;
        this.email = email;
    }
    
    public Akun(){}
    
    public int checkUsername(String username, String role){
        int i = 0;
        if (role.equals("user")){
            while (i != users.size()){
                if (users.get(i).getUsername().equals(username)){
                    return i;
                }
                i++;
            }
            return -1;
        }
        else {
            while (i != users.size()){
                if (users.get(i).getUsername().equals(username)){
                    return i;
                }
                i++;
            }
            return -1;
        }
        
    }   
    
    // method untuk create akun
   public void createAkun(){
        int start = 1;
        int menu;
        while (start != 3){
            System.out.println("Daftar akun :");
            System.out.println("Masukkan username:");

            username = input.nextLine();
            // jika username belum ada (checkUsername me-return -1)
            if (checkUsername(username, "user") == -1){
                System.out.println("Masukkan password : ");
                password = input.nextLine();
                System.out.println("nama : ");
                nama = input.nextLine();
                System.out.println("email : ");
                email = input.nextLine();
                System.out.println("nomor telfon : ");
                phoneNum = input.nextLine();

                System.out.println("Daftar Akun berhasil");
                users.add(new User (username, password, phoneNum, nama, email));
                start = 3;
            }
            else if (checkUsername(username, "user") == 1){
                System.out.println("Username sudah ada, mohon buat username lain");
            }
            else{
                start++;
            }
            
        }
        
    }
   
    // method login
    public int login(String role){
        int start = 1;
        String username, password;
        Akun akun = new Akun();
        int akunIndex;
        
        // metode login untuk user
        if (role.equals("user")){
            while(start != 0){
                System.out.println("Masukkan username : ");
                username = input.nextLine();
                akunIndex = checkUsername(username, "user");
                if(akunIndex >= 0){
                    System.out.println("Masukkan password : ");
                    password = input.nextLine();
                    //verifikasi apakah username dan password benar
                    if (users.get(akunIndex).loginVer(username, password)){
                        System.out.println("Login berhasil");
                        return akunIndex;
                    }
                    else{
                        System.out.println("Login gagal");
                        return -1;
                    }
                }
                
                else{
                    System.out.println("Username belum didaftarkan");
                    return -1;
                }
            }
        }
        // metode login untuk admin
        else{
            while(start != 0){
                System.out.println("Masukkan username : ");
                username = input.nextLine();
                akunIndex = checkUsername(username, "user");
                if(akunIndex >= 0){
                    System.out.println("Masukkan password : ");
                    password = input.nextLine();
                    //verifikasi apakah username dan password benar
                    if (users.get(akunIndex).loginVer(username, password)){
                        System.out.println("Login berhasil");
                        return akunIndex;
                    }
                    else{
                        System.out.println("Login gagal");
                        return -1;
                    }
                }
                
                else {
                    System.out.println("Username belum didaftarkan");
                    return -1;
                }
            }
        }
        return -1;
    }
   
   
    // method setter dan getter
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
    
    
    // method untuk konfirmasi usn dan pass saat login
    public boolean loginVer(String username, String password){
        return this.username.equals(username) && this.password.equals(password);
    }
    
    // lihat konten
    public void viewKontenEdukasi(KontenEdukasi kontenEdukasi) {
        System.out.println("Judul: " + kontenEdukasi.getJudul()+ "Isi: " + kontenEdukasi.getContent()+
                "Tanggal: " + kontenEdukasi.getTanggal()+ "Uploader: " + kontenEdukasi.getUploader());
       // menampilkan gambar (path gambar) jika tersedia
        if (kontenEdukasi.getImagePath() != null && !kontenEdukasi.getImagePath().isEmpty()) {
            System.out.println("Gambar: " + kontenEdukasi.getImagePath());
        }
    }
    
    public void viewUlasan(){
        for (Ulasan ulasan : ulasanList) {
            System.out.println(ulasan.printUlasan());
        }
    }

    
}

