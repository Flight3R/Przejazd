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
        boolean pociagNadCzujnikiem1;
        boolean pociagNadCzujnikiem2;
        boolean zaPrzejazdem;

        if ( tor.getZwrot() == "prawo") {
            pociagNadCzujnikiem1 = (getPolozenie().getX() - getDlugosc()) < tor.getCzujnik_przed().getPolozenie().getX() && tor.getCzujnik_przed().getPolozenie().getX() < getPolozenie().getX() ;
            pociagNadCzujnikiem2 = (getPolozenie().getX() - getDlugosc()) < tor.getCzujnik_za().getPolozenie().getX() && tor.getCzujnik_za().getPolozenie().getX() < getPolozenie().getX();

           zaPrzejazdem = getPrzejazd().getPolozenie().getX() < (getPolozenie().getX() - getDlugosc());

        } else { // zwrot == "lewo"
            pociagNadCzujnikiem1 = getPolozenie().getX() < tor.getCzujnik_przed().getPolozenie().getX() && tor.getCzujnik_przed().getPolozenie().getX() < (getPolozenie().getX() + getDlugosc());
            pociagNadCzujnikiem2 = getPolozenie().getX() < tor.getCzujnik_za().getPolozenie().getX() && tor.getCzujnik_za().getPolozenie().getX() < (getPolozenie().getX() + getDlugosc());

            zaPrzejazdem = (getPolozenie().getX() + getDlugosc()) < getPrzejazd().getPolozenie().getX();
        }

        if (pociagNadCzujnikiem1) {
            tor.getCzujnik_przed().aktywuj(nazwa);

        } else if (pociagNadCzujnikiem2 && zaPrzejazdem)
            tor.getCzujnik_za().aktywuj(nazwa);
    }

    public void sprawdzSSP() {
        if ( tor.getZwrot() == "prawo") {
            if (getPolozenie().getX() < tor.getSSP().getPolozenie().getX() && tor.getSSP().isZapalone())
                getCel().setX(getPrzejazd().getPolozenie().getX());

        } else { // zwrot == "lewo"
            if (tor.getSSP().getPolozenie().getX() < getPolozenie().getX() && tor.getSSP().isZapalone())
                getCel().setX(getPrzejazd().getPolozenie().getX());
        }
    }


    @Override
    public void run() {
        super.run();
        double deltaT = 200/1000;
        while(true) {

            sprawdzCzujniki();
            sprawdzSSP();

            if (getCel().getX() != getPolozenie().getX()) {
                if (Math.abs(getCel().getX() - getPolozenie().getX()) < getDrogaHamowania())
                    hamuj(deltaT);
                else
                    przyspiesz(deltaT);

                getPolozenie().przenies(getPredkosc(), deltaT, tor.getZwrot(), getCel());
            }

            try { sleep((long) deltaT*1000); } catch (InterruptedException interruptedException) { interruptedException.printStackTrace(); }
        }
    }
}
