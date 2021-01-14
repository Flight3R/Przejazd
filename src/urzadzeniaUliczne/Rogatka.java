package urzadzeniaUliczne;

import klasyAbstrakcyjne.obiektSymulacji;
import lokacja.Polozenie;

import javax.swing.*;

public class Rogatka extends obiektSymulacji {

    private boolean otwarta = true;
    private boolean polecenieZamkniecia = false;
    private final ImageIcon ikona2;

    public Rogatka(Polozenie polozenie, String nazwa, ImageIcon ikona, ImageIcon ikona2) {
        super(polozenie, nazwa, ikona);
        this.ikona2 = ikona2;
        start();
    }

// ------------------ gettery ------------------
    public boolean isOtwarta() {
        return otwarta;
    }

// ------------------ metody ------------------
    public void otworz() {
        System.out.println("Rogatka: " + nazwa + " otwarta");
        getLabel().setIcon(getIkona());
        otwarta = true;
    }

    public void zamknij() {
        this.polecenieZamkniecia = true;
    }

    private void zamknijPrivate() throws InterruptedException {
        sleep(6000);
        System.out.println("Rogatka: " + nazwa + " zamknieta");
        getLabel().setIcon(ikona2);
        otwarta = false;
    }

    @Override
    public void run() {
        while (true) {
            if (polecenieZamkniecia) {
                try {
                    zamknijPrivate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                polecenieZamkniecia = false;
            }
            try { sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
