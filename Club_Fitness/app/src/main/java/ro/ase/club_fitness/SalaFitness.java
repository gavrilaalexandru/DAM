package ro.ase.club_fitness;

import java.io.Serializable;
import java.util.ArrayList;

public class SalaFitness implements Serializable {
    private float pretAbonament;
    private int capacitateMax;
    private String nume;
    private String adresa;
    private ArrayList<String> aparate;

    public SalaFitness(float pretAbonament, int capacitateMax, String nume, String adresa, ArrayList<String> aparate) {
        this.pretAbonament = pretAbonament;
        this.capacitateMax = capacitateMax;
        this.nume = nume;
        this.adresa = adresa;
        this.aparate = aparate;
    }

    public SalaFitness() {
        this.aparate = new ArrayList<>();
    }

    public float getPretAbonament() {
        return pretAbonament;
    }

    public void setPretAbonament(float pretAbonament) {
        this.pretAbonament = pretAbonament;
    }

    public int getCapacitateMax() {
        return capacitateMax;
    }

    public void setCapacitateMax(int capacitateMax) {
        this.capacitateMax = capacitateMax;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public ArrayList<String> getAparate() {
        return aparate;
    }

    public void setAparate(ArrayList<String> aparate) {
        this.aparate = aparate;
    }

    @Override
    public String toString() {
        return "SalaFitness{" +
                "pretAbonament=" + pretAbonament +
                ", capacitateMax=" + capacitateMax +
                ", nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", aparate=" + aparate +
                '}';
    }
}
