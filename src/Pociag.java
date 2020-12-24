public class Pociag extends Pojazd {

    private final String nazwa;
    private final Integer czasPrzyjazdu;
    private final Tor tor;
    private final Przejazd przejazd;
    private boolean utrzymujPredkosc = false;

    public Pociag(double dlugosc, Integer masa, double maxPredkosc, Polozenie polozenie, String nazwa, Integer czasPrzyjazdu, Tor tor, Przejazd przejazd) {
        super(dlugosc, masa, maxPredkosc, polozenie);
        this.nazwa = nazwa;
        this.czasPrzyjazdu = czasPrzyjazdu;
        this.tor = tor;
        this.przejazd = przejazd;
        copyCel(tor.getKoniec());
        start();
    }

    @Override
    public String toString() {
        return "Pociag numer: " + nazwa + "\tV= " + getPredkosc() + "\tX= " + getPolozenie().getX() + "\tY= " + getPolozenie().getY();
    }

    public Integer getCzasPrzyjazdu() { return czasPrzyjazdu; }

    public void sprawdzCzujniki() {
        boolean pociagNadCzujnikiem1;
        boolean pociagNadCzujnikiem2;
        boolean tylZaPrzejazdem;

        if (tor.getZwrot().equals("prawo")) {
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

        } else if (pociagNadCzujnikiem2 && tylZaPrzejazdem)
            tor.getCzujnikZa().aktywuj(nazwa);
    }

    public void sprawdzSSP() {
        if (tor.getZwrot().equals("prawo")) {
            if (getPolozenie().getX() < tor.getSwiatlo().getPolozenie().getX()) {
                if (tor.getSwiatlo().isZapalone())
                    getCel().setX(przejazd.getPolozenie().getX());
                else
                    copyCel(tor.getKoniec());
            }
        } else { // zwrot == "lewo"
            if (tor.getSwiatlo().getPolozenie().getX() < getPolozenie().getX()) {
                if (tor.getSwiatlo().isZapalone())
                    getCel().setX(przejazd.getPolozenie().getX());
                else
                    copyCel(tor.getKoniec());
            }
        }
    }

    @Override
    public void run() {
        double deltaT = 200.0/1000;
        while(true) {
            System.out.println(this);
            System.out.println("cel: "+getCel().getX()+" koniec: "+tor.getKoniec().getX());

            sprawdzCzujniki();
            sprawdzSSP();

            if (getCel().getX() != getPolozenie().getX()) {
                if (Math.abs(getCel().getX() - getPolozenie().getX()) < getDrogaHamowania()*1.2) {
                    if (getPredkosc() <= 5.56 && !przejazd.isRogatkiOtwarte()) { // 5.56m/s ~= 20km/h
                        utrzymujPredkosc = true;
                        copyCel(tor.getKoniec());
                    } else if (!utrzymujPredkosc)
                        hamuj(deltaT);
                } else if (getPredkosc() < getMaxPredkosc() && !utrzymujPredkosc)
                    przyspiesz(deltaT);

                if (utrzymujPredkosc) {
                    boolean przodZaPrzejazdem;
                    if (tor.getZwrot().equals("prawo"))
                        przodZaPrzejazdem = getPolozenie().getX() < przejazd.getPolozenie().getX();
                    else // zwrot == "lewo"
                        przodZaPrzejazdem = przejazd.getPolozenie().getX() < getPolozenie().getX();

                    if (przodZaPrzejazdem)
                        utrzymujPredkosc = false;
                }

                getPolozenie().przenies(getPredkosc(), deltaT, tor.getZwrot(), getCel());
            }

            try { sleep((long) (deltaT*1000)); } catch (InterruptedException interruptedException) { interruptedException.printStackTrace(); }
        }
    }
}
