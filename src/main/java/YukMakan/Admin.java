package YukMakan;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Akun{
    
    ArrayList <KontenEdukasi> createdKontenEdukasi = new ArrayList <>();
    ArrayList <Resep> createdResep = new ArrayList <>();
    Scanner scanner = new Scanner(System.in);

    public Admin(String username, String password, String phoneNum, String nama, String email) {
        super(username, password, phoneNum, nama, email);
    }
    
    public Admin(){}
   
    
    public void createKontenEdukasi () {
        System.out.println("Masukkan judul konten edukasi: ");
        
    }
    
    public void addCreatedKontenEdukasi (KontenEdukasi kontenEdukasi){
        this.createdKontenEdukasi.add(kontenEdukasi);
    }
    public void addCreatedResep (Resep resep){
        this.createdResep.add(resep);
    }

}
