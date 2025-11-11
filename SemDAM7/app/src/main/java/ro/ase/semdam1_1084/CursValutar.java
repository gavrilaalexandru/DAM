package ro.ase.semdam1_1084;

import androidx.annotation.NonNull;

public class CursValutar {
    private String dataCurs;
    private String cursEUR;
    private String cursUSD;
    private String cursGBP;
    private String cursXAU;

    public CursValutar(String dataCurs, String cursEUR, String cursUSD, String cursGBP, String cursXAU) {
        this.dataCurs = dataCurs;
        this.cursEUR = cursEUR;
        this.cursUSD = cursUSD;
        this.cursGBP = cursGBP;
        this.cursXAU = cursXAU;
    }

    public CursValutar() {}

    public String getDataCurs() {
        return dataCurs;
    }

    public void setDataCurs(String dataCurs) {
        this.dataCurs = dataCurs;
    }

    public String getCursEUR() {
        return cursEUR;
    }

    public void setCursEUR(String cursEUR) {
        this.cursEUR = cursEUR;
    }

    public String getCursUSD() {
        return cursUSD;
    }

    public void setCursUSD(String cursUSD) {
        this.cursUSD = cursUSD;
    }

    public String getCursGBP() {
        return cursGBP;
    }

    public void setCursGBP(String cursGBP) {
        this.cursGBP = cursGBP;
    }

    public String getCursXAU() {
        return cursXAU;
    }

    public void setCursXAU(String cursXAU) {
        this.cursXAU = cursXAU;
    }

    @NonNull
    @Override
    public String toString() {
        return "CursValutar{" +
                "dataCurs='" + dataCurs + '\'' +
                ", cursEUR='" + cursEUR + '\'' +
                ", cursUSD='" + cursUSD + '\'' +
                ", cursGBP='" + cursGBP + '\'' +
                ", cursXAU='" + cursXAU + '\'' +
                '}';
    }
}
