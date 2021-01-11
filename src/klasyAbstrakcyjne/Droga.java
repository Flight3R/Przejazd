package klasyAbstrakcyjne;

import lokacja.Polozenie;

import javax.swing.*;

public abstract class Droga extends obiektSymulacji {

    private final String zwrot;
    private final double dlugosc;
    private Polozenie koniec = null;

    public Droga(Polozenie polozenie, String nazwa, String zwrot, double dlugosc, Icon ikona) {
        super(polozenie, nazwa, ikona);
        this.zwrot = zwrot;
        this.dlugosc = dlugosc;

        if (zwrot == "gora")
            this.koniec = new Polozenie(polozenie.getX(), polozenie.getY() + dlugosc);
        else if (zwrot == "dol")
            this.koniec = new Polozenie(polozenie.getX(), polozenie.getY() - dlugosc);
        else if (zwrot == "lewo")
            this.koniec = new Polozenie(polozenie.getX() - dlugosc, polozenie.getY());
        else if (zwrot == "prawo")
            this.koniec = new Polozenie(polozenie.getX() + dlugosc, polozenie.getY());

    }

    public String getZwrot() {
        return zwrot;
    }
    public double getDlugosc() {
        return dlugosc;
    }
    public Polozenie getKoniec() {
        return koniec;
    }

}
