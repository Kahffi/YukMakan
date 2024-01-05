package model;

import javafx.scene.image.Image;

import java.util.UUID;

public class Bank {
    private UUID idBank;
    private String namaBank;

    private Image logoBank;


    public Bank(UUID idBank, String namaBank, Image logoBank) {
        this.idBank = idBank;
        this.namaBank = namaBank;
        this.logoBank = logoBank;
    }

    public UUID getIdBank() {
        return idBank;
    }

    public void setIdBank(UUID idBank) {
        this.idBank = idBank;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    public Image getLogoBank() {
        return logoBank;
    }

    public void setLogoBank(Image logoBank) {
        this.logoBank = logoBank;
    }
}
