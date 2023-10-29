package YukMakan;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Akun{
    
    ArrayList <KontenEdukasi> createdKontenEdukasi = new ArrayList <KontenEdukasi>();
    Scanner scanner = new Scanner(System.in);

    public Admin(String username, String password, String phoneNum, String nama, String email) {
        super(username, password, phoneNum, nama, email);
    }
   
    
    public void createKontenEdukasi () {
        System.out.println("Masukkan judul konten edukasi: ");
        
    }

}
