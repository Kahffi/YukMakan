package model;

import java.util.UUID;

public class MetodePembayaran {
    private UUID id;
    private String nomorRekening;
    private String atasNama;
    private Bank bank;

    public MetodePembayaran(UUID id, String nomorRekening, String atasNama, Bank bank) {
        this.id = id;
        this.nomorRekening = nomorRekening;
        this.atasNama = atasNama;
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomorRekening() {
        return nomorRekening;
    }

    public void setNomorRekening(String nomorRekening) {
        this.nomorRekening = nomorRekening;
    }

    public String getAtasNama() {
        return atasNama;
    }

    public void setAtasNama(String atasNama) {
        this.atasNama = atasNama;
    }

    public void setNamaBank(UUID idBank) {
    }
}
