package urzadzeniaKolejowe;

import klasyAbstrakcyjne.ElementInfrastruktury;
import klasyAbstrakcyjne.Swiatlo;
import lokacja.Polozenie;

public class Semafor extends ElementInfrastruktury implements Swiatlo {
    private boolean stop = false;

    public Semafor(Polozenie polozenie, String nazwa) {
        super(polozenie, nazwa);
        start();
    }

    public boolean isStop() { return stop; }

    public void podajSTOP() {
        stop = true;
        System.out.println("Semafor: " + nazwa + " podaje STOP!");
    }

    public void podajJEDZ() {
        stop = false;
        System.out.println("Semafor: " + nazwa + " podaje JEDŹ!");
    }
}
