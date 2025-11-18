package ro.ase.restaurant;

import java.io.Serializable;

public class Restaurant implements Serializable {
    private String name;
    private String adresa;
    private int capacitateMax;
    private int nrProduse;
    private String specific;

    public Restaurant(String adresa, int capacitateMax, String name, String specific, int nrProduse) {
        this.adresa = adresa;
        this.capacitateMax = capacitateMax;
        this.name = name;
        this.specific = specific;
        this.nrProduse = nrProduse;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getCapacitateMax() {
        return capacitateMax;
    }

    public void setCapacitateMax(int capacitateMax) {
        this.capacitateMax = capacitateMax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecific() {
        return specific;
    }

    public void setSpecific(String specific) {
        this.specific = specific;
    }

    public int getNrProduse() {
        return nrProduse;
    }

    public void setNrProduse(int nrProduse) {
        this.nrProduse = nrProduse;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "adresa='" + adresa + '\'' +
                ", nume='" + name + '\'' +
                ", capacitateMax=" + capacitateMax +
                ", nrProduse=" + nrProduse +
                ", specific='" + specific + '\'' +
                '}';
    }
}
