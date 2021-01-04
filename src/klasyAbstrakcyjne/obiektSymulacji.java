package klasyAbstrakcyjne;

import lokacja.Polozenie;

public abstract class obiektSymulacji extends Thread {

    protected Polozenie polozenie;
    protected String nazwa;

    public obiektSymulacji(Polozenie polozenie, String nazwa) {
        this.polozenie = polozenie;
        this.nazwa = nazwa;
    }

    public Polozenie getPolozenie() {
        return polozenie;
    }
}
