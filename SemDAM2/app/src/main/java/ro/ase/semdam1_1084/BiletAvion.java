package ro.ase.semdam1_1084;

import java.util.Date;

public class BiletAvion {
    private String destinatie;
    private Date dataZbor;
    private float pret;
    private String companie; // Tarom, Ryanair, WizzAir, HiSky
    private String categorie_Bilet; // ECONOMY, BUSINESS

    public BiletAvion(String destinatie, Date dataZbor, float pret, String companie, String categorie_Bilet) {
        this.destinatie = destinatie;
        this.dataZbor = dataZbor;
        this.pret = pret;
        this.companie = companie;
        this.categorie_Bilet = categorie_Bilet;
    }

    public String getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public Date getDataZbor() {
        return dataZbor;
    }

    public void setDataZbor(Date dataZbor) {
        this.dataZbor = dataZbor;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public String getCompanie() {
        return companie;
    }

    public void setCompanie(String companie) {
        this.companie = companie;
    }

    public String getCategorie_Bilet() {
        return categorie_Bilet;
    }

    public void setCategorie_Bilet(String categorie_Bilet) {
        this.categorie_Bilet = categorie_Bilet;
    }

    @Override
    public String toString() {
        return "BiletAvion{" +
                "destinatie='" + destinatie + '\'' +
                ", dataZbor=" + dataZbor +
                ", pret=" + pret +
                ", companie='" + companie + '\'' +
                ", categorie_Bilet='" + categorie_Bilet + '\'' +
                '}';
    }
}
