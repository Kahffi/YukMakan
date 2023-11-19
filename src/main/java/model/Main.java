package model;

import java.util.ArrayList;
import java.util.Scanner;
import model.Akun;


public class Main {
        static Scanner scanner = new Scanner(System.in);
        static ArrayList <Admin> admins = new ArrayList <>();
        static ArrayList <User> users = new ArrayList <>();
        static ArrayList <Resep> listResep = new ArrayList <>();
        static ArrayList <KontenEdukasi> ListKontenEdukasi = new ArrayList <>();
        static ArrayList <Ulasan> listUlasan = new ArrayList <>();

    public static void main(String... args) {
        // index akun untuk menentukan akun di dalam arrayList
        int akunIndex = -1;
        // menambahkan akun admins
        admins.add(new Admin("rahamatMegumi", "megumiRahmat", "Rahmat Hidayat", "0848486", "megumirahmat@upi.edun"));
        
        // menambahkan akun users
        users.add(new User("Fauzan47", "fauzanrossi", "05487548", "Fauzan", "fauzanRossi@upi.edun"));
        users.add(new User("Nanda07", "nandasiuu", "50487548", "Nanda", "nanadaSiuu@upi.edun"));
        
        // menambahkan listResep
        listResep.add(new Resep("Capcay Balado",  "17 Agustus 1945", "Documents/something.png", admins.get(0),
                " 12mg Vitamin C\n5g Vitamin A", "1. Masukan ini\n2. Tambahkan itu\n3. Inikan itunya\n4. Capcay Balado siap disajikan",
                "1. Cabe\n2. Wortel\n3. Jagung\n4. DLL", "Nikmat nikmat mantap", " 12mg Vitamin C\n5g Vitamin A"));
        
        // menambahkan listUlasan listResep
        listResep.get(0).addUlasan(new Ulasan(users.get(0), "Mantap gan", "17 Agustus 1945"));
        listResep.get(0).addUlasan(new Ulasan(users.get(1), "B aja gan", "17 Agustus 1945"));
        // main menu
        int start = 1;
        
        while (start != 0){
            int menu = 0;
            Akun akun = new Akun(); 
            System.out.println("~~~~~ YukMakan ~~~~~");
            System.out.println("1. Daftar Akun\n2. Login User\n3. Login Admin");
            menu = Integer.parseInt(scanner.nextLine());
            if (menu == 1){
                akun.createAkun();
            }
            else if (menu == 2){
                akunIndex = akun.login("user");
                if (akunIndex != -1){
                    menuUser(akunIndex);
                }
                else{
                    System.out.println("Login gagal");
                }
            }
            else if (menu == 3){
                akun.login("admin");
            }
            else{
                printInputError();
            }
            users.forEach((User user) -> System.out.println(user.getUsername()));
        }

    }
    
    public static void menuUser(int akunIndex){
        int start = 1;
        
        while (start != 0){
            int menu = -1;
            System.out.println("~~~~~ Menu User ~~~~~");
            System.out.println("1. Lihat Resep\n2. Lihat daftar favorit\n3. Edit profile\n4. Lihat konten edukasi\n0. Keluar");
            menu = Integer.parseInt(scanner.nextLine());
            if (menu == 1 && listResep.size() > 0){
                menuResepUser(akunIndex);
            }
            
        }
  
    }
    
    // method menu resep
    public static void menuResepUser(int akunIndex){
        int start = 1;
        int i = 0;
        while(start != 0){
            int menu;
            listResep.get(i).printResep();
            System.out.println("1. Sebelumnya\n2. Selanjutnya\n3. Tambahkan ke daftar favorit\n4. Berikan ulasan\n0. Keluar");
            menu = Integer.parseInt(scanner.nextLine());
            // menggeser index arrayList resep 1 kali kekiri, jika i < 1 maka tidak akan digeser
            if (menu == 1 && i > 0){
                i--;
            }
            // menggeser index ke kanan dengan batas i mencapai jumlah index listResep
            else if (menu == 2 && i < listResep.size()){
                i++;
            }
            else if (menu == 3){
                users.get(akunIndex).addToFav(listResep.get(i));
            }
            else if (menu == 4){
                System.out.println("Masukkan ulasan : ");
                String newUlasan = scanner.nextLine();
                listResep.get(i).addUlasan(new Ulasan (users.get(akunIndex), newUlasan, "17 Agustus 1945"));
            }
        }
        
    }
    
    
    
   
    
    
    public static void printInputError(){
        System.out.println("Input yang anda masukkan salah");
    }
}   
    
    
    
    