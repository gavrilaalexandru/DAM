package ro.ase.semdam1_1084;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "bilete")
public class BiletAvion implements Serializable {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String destinatie;
    private Date dataZbor;
    private float pret;
    private String companie; // Tarom, Ryanair, WizzAir, HiSky
    private String categorie_Bilet; // ECONOMY, BUSINESS

    @Ignore
    private String uid;

    public BiletAvion(String destinatie, Date dataZbor, float pret, String companie, String categorie_Bilet) {
        this.destinatie = destinatie;
        this.dataZbor = dataZbor;
        this.pret = pret;
        this.companie = companie;
        this.categorie_Bilet = categorie_Bilet;
    }

    @Ignore
    public BiletAvion() {}

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @NonNull
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
