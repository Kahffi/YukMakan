package YukMakan;

import java.util.ArrayList;

public class Resep {

    private String judul;
    private Admin uploader;
    private String datePosted;
    private String deskripsi;
    private String langkah;
    private String bahan;
    private String kandunganGizi;
    private String imagePath;
    //ArrayList untuk menampung ulasan-ulasan dari user
    private ArrayList <Ulasan> ulasan = new ArrayList<Ulasan>();

    public Resep(String judul, Admin uploader, String datePosted, String deskripsi,
                 String langkah, String bahan, String imagePath, String kandunganGizi){
        this.judul = judul;
        this.uploader = uploader;
        this.datePosted = datePosted;
        this.deskripsi = deskripsi;
        this.langkah = langkah;
        this.bahan = bahan;
        this.imagePath = imagePath;
        this.kandunganGizi = kandunganGizi;
    }
    

    public String getJudul(){
        return judul;
    }
    public Admin getUploader(){
        return uploader;
    }
    public String getDatePosted(){
        return datePosted;
    }
    public String getDeskripsi(){
        return deskripsi;
    }
    public String getLangkah(){
        return langkah;
    }
    public String getBahan(){
        return bahan;
    }
    public String getImagePath(){
        return imagePath;
    }
    public String getKandunganGizi(){
        return kandunganGizi;
    }

    public void setJudul(String judul){
        this.judul = judul;
    }
    public void setUploader(Admin uploader){
        this.uploader = uploader;
    }
    public void setDatePosted(String datePosted){
        this.datePosted = datePosted;
    }
    public void setDeskripsi(String deskripsi){
        this.deskripsi = deskripsi;
    }
    public void setLangkah(String langkah){
        this.langkah = langkah;
    }
    public void setBahan(String bahan){
        this.bahan = bahan;
    }
    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }
    public void setUlasan(Ulasan ulasan){
        this.ulasan.add(ulasan);
    }
    public void setKandunganGizi(String kandunganGizi){
        this.kandunganGizi = kandunganGizi;
    }

    public String printUlasan(int index){
        return ulasan.get(index).printUlasan();
    }



}
