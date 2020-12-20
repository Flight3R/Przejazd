public class Pociag extends Pojazd {
    private String nazwa;
    private Integer czasPrzyjazdu;
    private Tor tor;

    public Pociag(double dlugosc, double masa, double maxPredkosc, Polozenie polozenie, Przejazd przejazd, String nazwa, Integer czasPrzyjazdu, Tor tor) {
        super(dlugosc, masa, maxPredkosc, polozenie, przejazd);
        this.nazwa = nazwa;
        this.czasPrzyjazdu = czasPrzyjazdu;
        this.tor = tor;
    }

    public String getNazwa() {
        return nazwa;
    }
    public Integer getCzasPrzyjazdu() {
        return czasPrzyjazdu;
    }
    public Tor getTor() { return tor; }


    public void sprawdzCzujniki() {
        // --->>>---<czujnik1>-----|przejazd|-----<czujnik2>-->>>---
        boolean czujnik1PodPociagiem;
        boolean czujnik2PodPociagiem;
        boolean zaPrzejazdem;

        if ( tor.getZwrot() == "prawo") {
            czujnik1PodPociagiem = (polozenie.getX() - dlugosc) < tor.getCzujnik_przed().getPolozenie().getX() && tor.getCzujnik_przed().getPolozenie().getX() < polozenie.getX() ;
            czujnik2PodPociagiem = (polozenie.getX() - dlugosc) < tor.getCzujnik_za().getPolozenie().getX() && tor.getCzujnik_za().getPolozenie().getX() < polozenie.getX();

           zaPrzejazdem = przejazd.getPolozenie().getX() < (polozenie.getX() - dlugosc);

        } else { // zwrot == "lewo"
            czujnik1PodPociagiem = polozenie.getX() < tor.getCzujnik_przed().getPolozenie().getX() && tor.getCzujnik_przed().getPolozenie().getX() < (polozenie.getX() + dlugosc);
            czujnik2PodPociagiem = polozenie.getX() < tor.getCzujnik_za().getPolozenie().getX() && tor.getCzujnik_za().getPolozenie().getX() < (polozenie.getX() + dlugosc);

            zaPrzejazdem = (polozenie.getX() + dlugosc) < przejazd.getPolozenie().getX();
        }

        if (czujnik1PodPociagiem) {
            tor.getCzujnik_przed().aktywuj(nazwa);

        } else if (czujnik2PodPociagiem && zaPrzejazdem)
            tor.getCzujnik_za().aktywuj(nazwa);
    }



    @Override
    public void run() {
        super.run();
        double deltaT = 200/1000;
        while(true) {
            // SPRAWDZ SSP
            if (cel.getX() != polozenie.getX()) {
                if (Math.abs(cel.getX() - polozenie.getX()) < drogaHamowania) {
                    hamuj(deltaT);
                }
                polozenie.przenies(predkosc, deltaT, tor.getZwrot(), cel);
            }


            try { sleep((long) deltaT*1000); } catch (InterruptedException interruptedException) { interruptedException.printStackTrace(); }
        }
    }
}
