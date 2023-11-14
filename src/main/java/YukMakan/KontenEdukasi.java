package YukMakan;

public class KontenEdukasi {

    private String imagePath;
    private String judul;
    private String content;
    private String tanggal;
    private Admin uploader;
    private String deskripsi;
    private String langkah;
    private String bahan;
    private String kandunganGizi;

    public KontenEdukasi(String judul, String content, String tanggal, Admin uploader, String imagePath, 
            String deskripsi, String langkah, String bahan, String kandunganGizi){
        this.judul = judul;
        this.content = content;
        this.tanggal = tanggal;
        this.uploader = uploader;
        this.imagePath = imagePath;
        this.deskripsi = deskripsi;
        this.langkah = langkah;
        this.bahan = bahan;
        this.bahan = kandunganGizi;
    }
    
    // setter dan getter
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

    public String getKandunganGizi() {
        return kandunganGizi;
    }
    public void setKandunganGizi(String kandunganGizi) {
        this.kandunganGizi = kandunganGizi;
    }
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
