public class Pociag extends Pojazd {

    private final String nazwa;
    private final Integer czasPrzyjazdu;
    private final Tor tor;
    private final Przejazd przejazd;
    private boolean utrzymujPredkosc = false;

    public Pociag(double dlugosc, Integer masa, double maxPredkosc, String nazwa, Integer czasPrzyjazdu, Tor tor, Przejazd przejazd) {
        super(dlugosc, masa, maxPredkosc, new Polozenie(-tor.getKoniec().getX()/2,tor.getKoniec().getY()));
        this.nazwa = nazwa;
        this.czasPrzyjazdu = czasPrzyjazdu;
        this.tor = tor;
        this.przejazd = przejazd;
        copyCel(tor.getKoniec());
//        >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ODKOMENTOWAC >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//        start();
    }

    @Override
    public String toString() {
        return "Pociag: " + nazwa + "\tV= " + getPredkosc() + "\tX= " + getPolozenie().getX() + "\tY= " + getPolozenie().getY();
    }

    public Integer getCzasPrzyjazdu() { return czasPrzyjazdu; }

    public void sprawdzCzujniki() {
        boolean pociagNadCzujnikiemSSP1;
        boolean pociagNadCzujnikiemSSP2;
        boolean pociagNadCzujnikiemSBL1;
        boolean pociagNadCzujnikiemSBL2;
        boolean pociagNadCzujnikiemSBL3;
        boolean pociagNadCzujnikiemSBL4;
        boolean tylZaPrzejazdem;

        if (tor.getZwrot().equals("prawo")) {
            pociagNadCzujnikiemSSP1 = (getPolozenie().getX() - getDlugosc()) < tor.getCzujnikSSP1().getPolozenie().getX() && tor.getCzujnikSSP1().getPolozenie().getX() < getPolozenie().getX() ;
            pociagNadCzujnikiemSSP2 = (getPolozenie().getX() - getDlugosc()) < tor.getCzujnikSSP2().getPolozenie().getX() && tor.getCzujnikSSP2().getPolozenie().getX() < getPolozenie().getX();
            pociagNadCzujnikiemSBL1 = (getPolozenie().getX() - getDlugosc()) < tor.getCzujnikSBL1().getPolozenie().getX() && tor.getCzujnikSBL1().getPolozenie().getX() < getPolozenie().getX();
            pociagNadCzujnikiemSBL2 = (getPolozenie().getX() - getDlugosc()) < tor.getCzujnikSBL2().getPolozenie().getX() && tor.getCzujnikSBL2().getPolozenie().getX() < getPolozenie().getX();
            pociagNadCzujnikiemSBL3 = (getPolozenie().getX() - getDlugosc()) < tor.getCzujnikSBL3().getPolozenie().getX() && tor.getCzujnikSBL3().getPolozenie().getX() < getPolozenie().getX();
            pociagNadCzujnikiemSBL4 = (getPolozenie().getX() - getDlugosc()) < tor.getCzujnikSBL4().getPolozenie().getX() && tor.getCzujnikSBL4().getPolozenie().getX() < getPolozenie().getX();
            tylZaPrzejazdem = przejazd.getPolozenie().getX() < (getPolozenie().getX() - getDlugosc());

        } else { // zwrot == "lewo"
            pociagNadCzujnikiemSSP1 = getPolozenie().getX() < tor.getCzujnikSSP1().getPolozenie().getX() && tor.getCzujnikSSP1().getPolozenie().getX() < (getPolozenie().getX() + getDlugosc());
            pociagNadCzujnikiemSSP2 = getPolozenie().getX() < tor.getCzujnikSSP2().getPolozenie().getX() && tor.getCzujnikSSP2().getPolozenie().getX() < (getPolozenie().getX() + getDlugosc());
            pociagNadCzujnikiemSBL1 = getPolozenie().getX() < tor.getCzujnikSBL1().getPolozenie().getX() && tor.getCzujnikSBL1().getPolozenie().getX() < (getPolozenie().getX() + getDlugosc());
            pociagNadCzujnikiemSBL2 = getPolozenie().getX() < tor.getCzujnikSBL2().getPolozenie().getX() && tor.getCzujnikSBL2().getPolozenie().getX() < (getPolozenie().getX() + getDlugosc());
            pociagNadCzujnikiemSBL3 = getPolozenie().getX() < tor.getCzujnikSBL3().getPolozenie().getX() && tor.getCzujnikSBL3().getPolozenie().getX() < (getPolozenie().getX() + getDlugosc());
            pociagNadCzujnikiemSBL4 = getPolozenie().getX() < tor.getCzujnikSBL4().getPolozenie().getX() && tor.getCzujnikSBL4().getPolozenie().getX() < (getPolozenie().getX() + getDlugosc());
            tylZaPrzejazdem = (getPolozenie().getX() + getDlugosc()) < przejazd.getPolozenie().getX();
        }

        if (pociagNadCzujnikiemSSP1)
            tor.getCzujnikSSP1().aktywuj(nazwa);

        if (pociagNadCzujnikiemSBL1)
            tor.getCzujnikSBL1().aktywuj(nazwa);

        if (pociagNadCzujnikiemSBL2)
            tor.getCzujnikSBL2().aktywuj(nazwa);

        if (pociagNadCzujnikiemSBL3)
            tor.getCzujnikSBL3().aktywuj(nazwa);

        if (pociagNadCzujnikiemSBL4)
            tor.getCzujnikSBL4().aktywuj(nazwa);

        if (pociagNadCzujnikiemSSP2 && tylZaPrzejazdem) {
            tor.getCzujnikSSP2().aktywuj(nazwa);
//            System.out.println(this + "\tZA PRZEJAZDEM !!!");
        }
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
    public void sprawdzSBL() {
        if (tor.getZwrot().equals("prawo")) {
            if (getPolozenie().getX() < tor.getSemaforSBL2().getPolozenie().getX()) {
                if (tor.getSemaforSBL2().isZapalone())
                    copyCel(tor.getSemaforSBL2().getPolozenie());
                else
                    copyCel(tor.getKoniec());
            } else if (getPolozenie().getX() < tor.getSemaforSBL3().getPolozenie().getX() && tor.getSemaforSBL3().isZapalone())
                copyCel(tor.getSemaforSBL3().getPolozenie());
        } else { // zwrot == "lewo"
            if (tor.getSemaforSBL2().getPolozenie().getX() < getPolozenie().getX()) {
                if (tor.getSemaforSBL2().isZapalone())
                    copyCel(tor.getSemaforSBL2().getPolozenie());
                else
                    copyCel(tor.getKoniec());
            } else if (tor.getSemaforSBL3().getPolozenie().getX() < getPolozenie().getX() && tor.getSemaforSBL3().isZapalone())
                copyCel(tor.getSemaforSBL3().getPolozenie());
        }
    }

    @Override
    public void run() {
        tor.getCzujnikSBL1().aktywuj(nazwa);
        System.out.println(this + "\tZGŁASZAM SIĘ!");
        double deltaT = 200.0/1000;
        while(true) {
//            System.out.println(this);

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

            try {
                sleep((long) (deltaT*1000));
            } catch (InterruptedException e) {
                tor.getCzujnikSBL3().aktywuj(nazwa);
                System.out.println(this + "\tDO WIDZENIA!");
                stop();
            }
        }
    }
}
