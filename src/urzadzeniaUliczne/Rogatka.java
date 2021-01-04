package urzadzeniaUliczne;

import klasyAbstrakcyjne.obiektSymulacji;
import lokacja.Polozenie;

public class Rogatka extends obiektSymulacji {

    private final Integer czasZamykania;
    private boolean otwarta = true;
    private boolean polecenieZamkniecia = false;

    public Rogatka(Polozenie polozenie, String nazwa, Integer czasZamykania) {
        super(polozenie, nazwa);
        this.czasZamykania = czasZamykania;
        start();
    }

    public Integer getCzasZamykania() {
        return czasZamykania;
    }

    public boolean isOtwarta() {
        return otwarta;
    }

    public void otworz() {
        System.out.println("Rogatka: " + nazwa + " otwarta");
        otwarta = true;
    }

    public void zamknij() {
        this.polecenieZamkniecia = true;
    }


    private void zamknijPrivate() throws InterruptedException {
        sleep(5000);
        System.out.println("Rogatka: " + nazwa + " zamknieta");
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
            try { sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
