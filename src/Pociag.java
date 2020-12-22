public class Pociag extends Pojazd {
    private String nazwa;
    private Integer czasPrzyjazdu;
    private Tor tor;
    private Przejazd przejazd;

    public Pociag(double dlugosc, double masa, double maxPredkosc, Polozenie polozenie, String nazwa, Integer czasPrzyjazdu, Tor tor) {
        super(dlugosc, masa, maxPredkosc, polozenie);
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
            pociagNadCzujnikiem1 = (getPolozenie().getX() - getDlugosc()) < tor.getCzujnikPrzed().getPolozenie().getX() && tor.getCzujnikPrzed().getPolozenie().getX() < getPolozenie().getX() ;
            pociagNadCzujnikiem2 = (getPolozenie().getX() - getDlugosc()) < tor.getCzujnikZa().getPolozenie().getX() && tor.getCzujnikZa().getPolozenie().getX() < getPolozenie().getX();

           zaPrzejazdem = przejazd.getPolozenie().getX() < (getPolozenie().getX() - getDlugosc());

        } else { // zwrot == "lewo"
            pociagNadCzujnikiem1 = getPolozenie().getX() < tor.getCzujnikPrzed().getPolozenie().getX() && tor.getCzujnikPrzed().getPolozenie().getX() < (getPolozenie().getX() + getDlugosc());
            pociagNadCzujnikiem2 = getPolozenie().getX() < tor.getCzujnikZa().getPolozenie().getX() && tor.getCzujnikZa().getPolozenie().getX() < (getPolozenie().getX() + getDlugosc());

            zaPrzejazdem = (getPolozenie().getX() + getDlugosc()) < przejazd.getPolozenie().getX();
        }

        if (pociagNadCzujnikiem1) {
            tor.getCzujnikPrzed().aktywuj(nazwa);

        } else if (pociagNadCzujnikiem2 && zaPrzejazdem)
            tor.getCzujnikZa().aktywuj(nazwa);
    }

    public void sprawdzSSP() {
        if ( tor.getZwrot() == "prawo") {
            if (getPolozenie().getX() < tor.getSwiatlo().getPolozenie().getX() && tor.getSwiatlo().isZapalone())
                getCel().setX(przejazd.getPolozenie().getX());

        } else { // zwrot == "lewo"
            if (tor.getSwiatlo().getPolozenie().getX() < getPolozenie().getX() && tor.getSwiatlo().isZapalone())
                getCel().setX(przejazd.getPolozenie().getX());
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
                    if (getPredkosc() <= 5.56){ //5.56m/s ~= 20km/h
                        if (!przejazd.isRogatkiOtwarte())
                            setCel(tor.getKoniec());
                    } else
                        hamuj(deltaT);
                else
                    przyspiesz(deltaT);

                getPolozenie().przenies(getPredkosc(), deltaT, tor.getZwrot(), getCel());
            }

            try { sleep((long) deltaT*1000); } catch (InterruptedException interruptedException) { interruptedException.printStackTrace(); }
        }
    }
}
