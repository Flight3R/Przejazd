package urzadzeniaKolejowe;

import klasyAbstrakcyjne.obiektSymulacji;
import klasyAbstrakcyjne.Swiatlo;
import lokacja.Polozenie;

import javax.swing.*;

public class Semafor extends obiektSymulacji implements Swiatlo {
    private boolean stop = false;
    private final ImageIcon ikona2;

    public Semafor(Polozenie polozenie, String nazwa, ImageIcon ikona, ImageIcon ikona2) {
        super(polozenie, nazwa, ikona);
        this.ikona2 = ikona2;
        start();
    }

// ------------------ gettery ------------------
    public boolean isStop() {
        return stop;
    }

// ------------------ metody ------------------
    public void podajSTOP() {
        stop = true;
        System.out.println("Semafor: " + nazwa + " podaje STOP!");
        getLabel().setIcon(ikona2);
    }

    public void podajJEDZ() {
        stop = false;
        System.out.println("Semafor: " + nazwa + " podaje JEDŹ!");
        getLabel().setIcon(getIkona());
    }
}
