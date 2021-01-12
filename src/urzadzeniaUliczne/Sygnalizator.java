package urzadzeniaUliczne;

import klasyAbstrakcyjne.obiektSymulacji;
import klasyAbstrakcyjne.Swiatlo;
import lokacja.Polozenie;

import javax.swing.*;

public class Sygnalizator extends obiektSymulacji implements Swiatlo {

    boolean stop = false;
    private boolean polecenieJedz = false;

    public Sygnalizator(Polozenie polozenie, String nazwa, ImageIcon ikona) {
        super(polozenie, nazwa, ikona);
        start();
    }

    public boolean isStop() {
        return stop;
    }

    public void podajSTOP() {
        stop = true;
        System.out.println("Sygnalizator: " + nazwa + " świeci STOP!");
    }

    public void podajJEDZ() {
        polecenieJedz = true;
    }

    private void podajJEDZprivate() throws InterruptedException {
        sleep(5000);
        stop = false;
        System.out.println("Sygnalizator: " + nazwa + " świeci JEDŹ!");

    }

    @Override
    public void run() {
        while (true) {
            if (polecenieJedz) {
                try {
                    podajJEDZprivate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                polecenieJedz = false;


            }
            try { sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
