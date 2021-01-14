package urzadzeniaKolejowe;

import klasyAbstrakcyjne.obiektSymulacji;
import lokacja.Polozenie;

import javax.swing.*;
import java.util.ArrayList;

public class Czujnik extends obiektSymulacji {
    private Integer aktywacje = 0;
    private final ArrayList<String> listaAktywacji = new ArrayList<>();
    private boolean aktywowany = false;

    public Czujnik(Polozenie polozenie, String nazwa, ImageIcon ikona) {
        super(polozenie, nazwa, ikona);
    }

// ------------------ gettery ------------------
    public Integer getAktywacje() {
        return aktywacje;
    }

    public boolean isAktywowany() {
        return aktywowany;
    }

// ------------------ metody ------------------
    public void deaktywuj() {
        this.aktywowany = false;
    }

    public void aktywuj(String nazwa){
        if (!listaAktywacji.contains(nazwa)) {
            aktywacje = aktywacje + 1;
            listaAktywacji.add(nazwa);
            aktywowany = true;
            System.out.println("Czujnik: " + this.nazwa + " włączony przez pociąg: " + nazwa);
        }
    }
}
