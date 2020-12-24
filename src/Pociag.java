public class Pociag extends Pojazd {

    private String nazwa;
    private Integer czasPrzyjazdu;
    private Tor tor;
    private Przejazd przejazd;
    private boolean utrzymujPredkosc = false;

    public Pociag(double dlugosc, double masa, double maxPredkosc, Polozenie polozenie, String nazwa, Integer czasPrzyjazdu, Tor tor, Przejazd przejazd) {
        super(dlugosc, masa, maxPredkosc, polozenie);
        this.nazwa = nazwa;
        this.czasPrzyjazdu = czasPrzyjazdu;
        this.tor = tor;
        this.przejazd = przejazd;
        setCel(tor.getKoniec());
        start();
    }

    public String getNazwa() {
        return nazwa;
    }
    public Integer getCzasPrzyjazdu() {
        return czasPrzyjazdu;
    }
    public Tor getTor() { return tor; }


    public void sprawdzCzujniki() {

        boolean pociagNadCzujnikiem1;
        boolean pociagNadCzujnikiem2;
        boolean tylZaPrzejazdem;

        if ( tor.getZwrot() == "prawo") {
            pociagNadCzujnikiem1 = (getPolozenie().getX() - getDlugosc()) < tor.getCzujnikPrzed().getPolozenie().getX() && tor.getCzujnikPrzed().getPolozenie().getX() < getPolozenie().getX() ;
            pociagNadCzujnikiem2 = (getPolozenie().getX() - getDlugosc()) < tor.getCzujnikZa().getPolozenie().getX() && tor.getCzujnikZa().getPolozenie().getX() < getPolozenie().getX();
            tylZaPrzejazdem = przejazd.getPolozenie().getX() < (getPolozenie().getX() - getDlugosc());

        } else { // zwrot == "lewo"
            pociagNadCzujnikiem1 = getPolozenie().getX() < tor.getCzujnikPrzed().getPolozenie().getX() && tor.getCzujnikPrzed().getPolozenie().getX() < (getPolozenie().getX() + getDlugosc());
            pociagNadCzujnikiem2 = getPolozenie().getX() < tor.getCzujnikZa().getPolozenie().getX() && tor.getCzujnikZa().getPolozenie().getX() < (getPolozenie().getX() + getDlugosc());
            tylZaPrzejazdem = (getPolozenie().getX() + getDlugosc()) < przejazd.getPolozenie().getX();

        }

        if (pociagNadCzujnikiem1) {
            tor.getCzujnikPrzed().aktywuj(nazwa);
            System.out.println("nad1!");

        } else if (pociagNadCzujnikiem2 && tylZaPrzejazdem)
            tor.getCzujnikZa().aktywuj(nazwa);

//        System.out.println("sprawdzczujniki");
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
            System.out.println(getPolozenie().getX());
            sprawdzCzujniki();
            sprawdzSSP();

            if (getCel().getX() != getPolozenie().getX()) {
                if (Math.abs(getCel().getX() - getPolozenie().getX()) < getDrogaHamowania()) {
                    if (getPredkosc() <= 5.56 && !przejazd.isRogatkiOtwarte()) { // 5.56m/s ~= 20km/h
                        utrzymujPredkosc = true;
                        setCel(tor.getKoniec());
                    } else if (!utrzymujPredkosc)
                        hamuj(deltaT);
                } else if (getPredkosc() < getMaxPredkosc() && !utrzymujPredkosc)
                    przyspiesz(deltaT);

                if (utrzymujPredkosc) {
                    boolean przodZaPrzejazdem;
                    if (tor.getZwrot() == "prawo")
                        przodZaPrzejazdem = getPolozenie().getX() < przejazd.getPolozenie().getX();
                    else // zwrot == "lewo"
                        przodZaPrzejazdem = przejazd.getPolozenie().getX() < getPolozenie().getX();

                    if (przodZaPrzejazdem)
                        utrzymujPredkosc = false;
                }

                getPolozenie().przenies(getPredkosc(), deltaT, tor.getZwrot(), getCel());
            }

            try { sleep((long) deltaT*1000); } catch (InterruptedException interruptedException) { interruptedException.printStackTrace(); }
        }
    }
}
