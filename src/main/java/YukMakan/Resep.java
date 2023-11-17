package YukMakan;

import java.util.ArrayList;
// child class dari class konten edukasi
public class Resep extends KontenEdukasi{
    private ArrayList <Ulasan> ulasan = new ArrayList<>();
    private String kandunganGizi;
    private String langkah;
    private String bahan;
    private String deskripsi;

    public Resep(String judul, String tanggal, Admin uploader,String imagePath,
                 String kandunganGizi, String langkah, String bahan, String deskripsi) {
        super(judul, tanggal, uploader, imagePath);
        this.kandunganGizi = kandunganGizi;
        this.langkah = langkah;
        this.bahan = bahan;
        this.deskripsi = deskripsi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    public String getLangkah() {
        return langkah;
    }
    public void setLangkah(String langkah) {
        this.langkah = langkah;
    }
    public String getBahan() {
        return bahan;
    }
    public void setBahan(String bahan) {
        this.bahan = bahan;
    }
    public void addUlasan(Ulasan ulasan){
        this.ulasan.add(ulasan);
    } 
    public void printUlasan(int index){
        ulasan.get(index).printUlasan();
    }   
    public ArrayList <Ulasan> getUlasan(){
        return this.ulasan;
    }
    public String getKandunganGizi() {
        return kandunganGizi;
    }
    public void setKandunganGizi(String kandunganGizi) {
        this.kandunganGizi = kandunganGizi;
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