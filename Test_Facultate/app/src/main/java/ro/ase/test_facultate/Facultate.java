package ro.ase.test_facultate;

import java.io.Serializable;
import java.util.ArrayList;

public class Facultate implements Serializable {
    private String nume;
    private String adresa;
    private ArrayList<String> facilitati; // compatibil
    private int nrStud;
    private int anInfiintare;

    public Facultate(String nume, String adresa, ArrayList<String> facilitati, int nrStud, int anInfiintare) {
        this.nume = nume;
        this.adresa = adresa;
        this.facilitati = new ArrayList<>(facilitati);
        this.nrStud = nrStud;
        this.anInfiintare = anInfiintare;
    }

    public Facultate() {
        facilitati = new ArrayList<>();
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

    public ArrayList<String> getFacilitati() {
        return new ArrayList<>(facilitati);
    }

    public void setFacilitati(ArrayList<String> facilitati) {
        this.facilitati = new ArrayList<>(facilitati);
    }

    public int getNrStud() {
        return nrStud;
    }

    public void setNrStud(int nrStud) {
        this.nrStud = nrStud;
    }

    public int getAnInfiintare() {
        return anInfiintare;
    }

    public void setAnInfiintare(int anInfiintare) {
        this.anInfiintare = anInfiintare;
    }

    @Override
    public String toString() {
        return "Facultate{" +
                "nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", facilitati=" + facilitati +
                ", nrStud=" + nrStud +
                ", anInfiintare=" + anInfiintare +
                '}';
    }
}
