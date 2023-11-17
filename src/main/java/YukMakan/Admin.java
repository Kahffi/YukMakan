package YukMakan;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Akun{
    
    ArrayList <KontenEdukasi> createdKontenEdukasi = new ArrayList <>();
    ArrayList <Resep> createdResep = new ArrayList <>();
    ArrayList <Ulasan> ulasanList = new ArrayList <>();
    Scanner resep = new Scanner(System.in);

    public Admin(String username, String password, String phoneNum, String nama, String email) {
        super(username, password, phoneNum, nama, email);
    }
    
    public Admin(){}
   
    // method untuk admin membuat konten
    public void createKontenEdukasi () {
        System.out.println("Masukkan judul konten edukasi: ");
        String judul = resep.nextLine();

        System.out.println("konten: ");
        String content = resep.nextLine();

        System.out.println("gambar: ");
        String imagePath = resep.nextLine();

        System.out.println("deskripsi : ");
        String deskripsi = resep.nextLine();

        System.out.println("langkah-langkah: ");
        String langkah = resep.nextLine();

        System.out.println("bahan: ");
        String bahan = resep.nextLine();

        System.out.println("kandungan gizi: ");
        String kandunganGizi = resep.nextLine();

        String tanggal = java.time.LocalDate.now().toString();

        Resep newResep = new Resep(judul, content, tanggal, this, imagePath, deskripsi, langkah, bahan, kandunganGizi);
        createdResep.add(newResep);
        
        
    }
    
    // create campaign
    
    
    public void addCreatedKontenEdukasi (KontenEdukasi kontenEdukasi){
        this.createdKontenEdukasi.add(kontenEdukasi);
    }
    public void addCreatedResep (Resep resep){
        this.createdResep.add(resep);
    }
    
   
    

}