package klasyAbstrakcyjne;
import lokacja.Polozenie;

import javax.swing.*;

public abstract class Pojazd extends obiektSymulacji {

    private final int dlugosc;
    private final double maxPredkosc;
    private double predkosc;
    private final double opoznienie;
    private final double przyspieszenie;
    private double drogaHamowania;
    private final double odstep;
    private Polozenie cel = new Polozenie(0,0);

    public Pojazd(Polozenie polozenie, String nazwa, String typ, String zwrot, int dlugosc, Integer masa, double maxPredkosc, Icon ikona) {
        super(polozenie, nazwa, ikona);
        this.dlugosc = dlugosc;
        this.maxPredkosc = maxPredkosc;
        predkosc = maxPredkosc;

        if (typ.equals("Pociag")) {
            opoznienie = -36000.0 / masa;
            przyspieszenie = 20000.0 / masa;
            if (zwrot.equals("prawo"))
                odstep = 10;
            else
                odstep = -10;
        } else {
            opoznienie = -5000.0 / masa;
            przyspieszenie = 5000.0 / masa;
            if (zwrot.equals("gora"))
                odstep = 1;
            else
                odstep = -1;
        }

        drogaHamowania = Math.pow(maxPredkosc, 2) / (2 * -opoznienie);
    }

    public String getNazwa() {
        return nazwa;
    }
    public Polozenie getPolozenie() {
        return polozenie;
    }
    public int getDlugosc() {
        return dlugosc;
    }
    public double getPredkosc() {
        return predkosc;
    }
    public double getMaxPredkosc() {
        return maxPredkosc;
    }
    public double getOpoznienie() {
        return opoznienie;
    }
    public double getPrzyspieszenie() {
        return przyspieszenie;
    }
    public double getDrogaHamowania() {
        return drogaHamowania;
    }
    public double getOdstep() {
        return odstep;
    }
    public Polozenie getCel() {
        return cel;
    }
    public void setPredkosc(double predkosc) {
        this.predkosc = predkosc;
    }

    public void hamuj(double deltaT) {
        predkosc = predkosc + opoznienie * deltaT;
        if (predkosc < 0)
            predkosc = 0;

        drogaHamowania = Math.pow(predkosc, 2) / (2 * -opoznienie);
    }

    public void przyspiesz(double deltaT) {
        predkosc = predkosc + przyspieszenie * deltaT;
        if (predkosc > maxPredkosc)
            predkosc = maxPredkosc;

        drogaHamowania = Math.pow(predkosc, 2) / (2 * -opoznienie);
    }
}
