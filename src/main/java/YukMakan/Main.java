package YukMakan;


public class Main {

    public static void main(String... args) {
        
        Admin admin1 = new Admin("rahamatMegumi", "megumiRahmat", "Rahmat Hidayat", "0848486", "megumirahmat@upi.edun");
        User user1 = new User("Fauzan47", "fauzanrossi", "05487548", "Fauzan", "fauzanRossi@upi.edun");
        User user2 = new User("Nanda07", "nandasiuu", "50487548", "Nanda", "nanadaSiuu@upi.edun");
        Resep resep1 = new Resep("Capcay Balado", admin1, "17 Agustus 1945", "Lezat nikmat bermanfaat",
                "1. Masukan ini\n2. Tambahkan itu\n3. Inikan itunya\n4. Capcay Balado siap disajikan",
                "1. Cabe\n2. Wortel\n3. Jagung\n4. DLL", "../someImage.png", "12mg Vitamin C\n5g Vitamin A");
        Ulasan ulasan1 = new Ulasan(user1, "Mantap gan", "17 Agustus 1945");
        Ulasan ulasan2 = new Ulasan(user2, "B aja gan", "17 Agustus 1945");
       
        
        resep1.setUlasan(ulasan1);
        resep1.setUlasan(ulasan2);
        System.out.println(resep1.printUlasan(0));
        System.out.println(resep1.printUlasan(1));
        System.out.println(admin1.getNama());
        System.out.println(admin1.login("rahmatMegumi", "megumiRahmat"));



    }

}