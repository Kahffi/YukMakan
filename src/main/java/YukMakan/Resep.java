package YukMakan;

import java.util.ArrayList;
// child class dari class konten edukasi
public class Resep extends KontenEdukasi{
    private ArrayList <Ulasan> ulasan = new ArrayList<>();
    
    
    public Resep(String judul, String content, String tanggal, Admin uploader, String imagePath, 
            String deskripsi, String langkah, String bahan, String kandunganGizi){
        super(judul, content, tanggal, uploader, imagePath, deskripsi, langkah, bahan, kandunganGizi);
        
    }
    
    
    public void addUlasan(Ulasan ulasan){
        this.ulasan.add(ulasan);
    }
    
    public String printUlasan(int index){
        return ulasan.get(index).printUlasan();
    }
    
    public ArrayList <Ulasan> getUlasan(){
        return this.ulasan;
    }
    
    public void printResep(){
        System.out.println("Judul : " + getJudul());
        System.out.println("Deskripsi : \n" + getDeskripsi());
        System.out.println("Bahan-bahan :\n" + getBahan());
        System.out.println("Langkah pembuatan :\n" + getLangkah());
        System.out.println("Kandungan gizi : \n" + getKandunganGizi());
        System.out.println("Diupload oleh : \n" + getUploader() + ", pada tanggal : " + getTanggal());
        System.out.println("Ulasan : \n");
        if(ulasan.size() > 0){
            ulasan.forEach((Ulasan u) -> System.out.println(u.getUlasanUsername() + " : " + u.getUlasan()));
        }
        else{
            System.out.println("belum ada ulasan");
        }
    }


}
