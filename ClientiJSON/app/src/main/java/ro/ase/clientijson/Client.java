package ro.ase.clientijson;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "clienti")
public class Client implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nume;
    private Date dataContract;
    private String tipAbonament;
    private float pret;
    private boolean extraoptiuni;

    public Client(Date dataContract, boolean extraoptiuni, String nume, float pret, String tipAbonament) {
        this.dataContract = dataContract;
        this.extraoptiuni = extraoptiuni;
        this.nume = nume;
        this.pret = pret;
        this.tipAbonament = tipAbonament;
    }

    @Ignore
    public Client() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataContract() {
        return dataContract;
    }

    public void setDataContract(Date dataContract) {
        this.dataContract = dataContract;
    }

    public boolean isExtraoptiuni() {
        return extraoptiuni;
    }

    public void setExtraoptiuni(boolean extraoptiuni) {
        this.extraoptiuni = extraoptiuni;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public String getTipAbonament() {
        return tipAbonament;
    }

    public void setTipAbonament(String tipAbonament) {
        this.tipAbonament = tipAbonament;
    }

    @NonNull
    @Override
    public String toString() {
        return "Client{" +
                "dataContract=" + dataContract +
                ", nume='" + nume + '\'' +
                ", tipAbonament='" + tipAbonament + '\'' +
                ", pret=" + pret +
                ", extraoptiuni=" + (extraoptiuni ? "DA" : "NU") +
                '}';
    }
}
