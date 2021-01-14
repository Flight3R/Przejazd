package urzadzeniaUliczne;

import klasyAbstrakcyjne.obiektSymulacji;
import klasyAbstrakcyjne.Swiatlo;
import lokacja.Polozenie;

import javax.swing.*;

public class Sygnalizator extends obiektSymulacji implements Swiatlo {

    boolean stop = false;
    private final ImageIcon ikona2;
    private final ImageIcon ikona3;

    public Sygnalizator(Polozenie polozenie, String nazwa, ImageIcon ikona, ImageIcon ikona2, ImageIcon ikona3) {
        super(polozenie, nazwa, ikona);
        this.ikona2 = ikona2;
        this.ikona3 = ikona3;
        start();
    }

// ------------------ gettery ------------------
    public boolean isStop() {
        return stop;
    }

// ------------------ metody ------------------
    public void podajSTOP() {
        stop = true;
        System.out.println("Sygnalizator: " + nazwa + " świeci STOP!");
    }

    public void podajJEDZ() {
        stop = false;
        System.out.println("Sygnalizator: " + nazwa + " świeci JEDŹ!");
    }

    @Override
    public void run() {
        while (true) {
            if (stop) {
                getLabel().setIcon(ikona2);
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getLabel().setIcon(ikona3);
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getLabel().setIcon(getIkona());
            } else {
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
