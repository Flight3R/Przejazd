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
            czujnik1PodPociagiem = (getPolozenie().getX() - getDlugosc()) < tor.getCzujnik_przed().getPolozenie().getX() && tor.getCzujnik_przed().getPolozenie().getX() < getPolozenie().getX() ;
            czujnik2PodPociagiem = (getPolozenie().getX() - getDlugosc()) < tor.getCzujnik_za().getPolozenie().getX() && tor.getCzujnik_za().getPolozenie().getX() < getPolozenie().getX();

           zaPrzejazdem = getPrzejazd().getPolozenie().getX() < (getPolozenie().getX() - getDlugosc());

        } else { // zwrot == "lewo"
            czujnik1PodPociagiem = getPolozenie().getX() < tor.getCzujnik_przed().getPolozenie().getX() && tor.getCzujnik_przed().getPolozenie().getX() < (getPolozenie().getX() + getDlugosc());
            czujnik2PodPociagiem = getPolozenie().getX() < tor.getCzujnik_za().getPolozenie().getX() && tor.getCzujnik_za().getPolozenie().getX() < (getPolozenie().getX() + getDlugosc());

            zaPrzejazdem = (getPolozenie().getX() + getDlugosc()) < getPrzejazd().getPolozenie().getX();
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
            if (getCel().getX() != getPolozenie().getX()) {
                if (Math.abs(getCel().getX() - getPolozenie().getX()) < getDrogaHamowania()) {
                    hamuj(deltaT);
                }
                getPolozenie().przenies(getPredkosc(), deltaT, tor.getZwrot(), getCel());
            }


            try { sleep((long) deltaT*1000); } catch (InterruptedException interruptedException) { interruptedException.printStackTrace(); }
        }
    }
}
