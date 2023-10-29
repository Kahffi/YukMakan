package YukMakan;

public class Ulasan {
    private User user;
    private String ulasan;
    private String tanggalUlasan;

    public Ulasan(User user, String ulasan, String tanggalUlasan){
        this.user = user;
        this.ulasan = ulasan;
        this.tanggalUlasan = ulasan;
    }

    public String getUlasanUser(){
        return user.getUsername();
    }
    public String getUlasan(){
        return ulasan;
    }
    public String getTanggalUlasan(){
        return tanggalUlasan;
    }
    public String getUlasanUsername(){
        return user.getUsername();
    }
    public String printUlasan(){
        return "Username: " + user.getUsername() + "\nUlasan: " + this.ulasan +
                "\nTanggal Ulasan: " + this.tanggalUlasan;
    }
    // menyimpan objek user yang memberikan ulasan
    public void setUlasanUser(User user){
        this.user = user;
    }
    public void setUlasan(String ulasan){
        this.ulasan = ulasan;
    }
    public void setTanggalUlasan(String tanggalUlasan){
        this.tanggalUlasan = tanggalUlasan;
    }




}
