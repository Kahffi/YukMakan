package model;

public class KontenEdukasi {

    private String imagePath;
    private String judul;
    private String content;
    private String tanggal;
    private Admin uploader;

    public KontenEdukasi(String judul, String content, String tanggal, Admin uploader, String imagePath){
        this.judul = judul;
        this.content = content;
        this.tanggal = tanggal;
        this.uploader = uploader;
        this.imagePath = imagePath;
    }
    // constructor untuk class resep
    public KontenEdukasi(String judul, String tanggal, Admin uploader, String imagePath) {
        this.imagePath = imagePath;
        this.judul = judul;
        this.tanggal = tanggal;
        this.uploader = uploader;
    }

    // setter dan getter
    public String getImagePath(){
        return imagePath;
    }
    public String getJudul(){
        return judul;
    }
    public String getContent(){
        return content;
    }
    public String getUploader(){
        return uploader.getNama();
    }
    public String getTanggal(){
        return tanggal;
    }
    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }
    public void setJudul(String judul){
        this.judul = judul;
    }
    public void setUploader(Admin uploader){
        this.uploader = uploader;
    }
    public void setContent(String content){
        this.content = content;
    }
    public void setTanggal(String tanggal){
        this.tanggal = tanggal;
    }


}