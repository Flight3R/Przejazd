public class Pociag extends Pojazd {

    private final String nazwa;
    private final Integer czasPrzyjazdu;
    private double spoznienie = 0;
    private final Tor tor;
    private final Przejazd przejazd;

    private boolean uwazajNaPrzejezdzie = false;

    public Pociag(double dlugosc, Integer masa, double maxPredkosc, String nazwa, Integer czasPrzyjazdu,Tor tor, Przejazd przejazd) {
        super(dlugosc, masa, maxPredkosc, new Polozenie(-tor.getKoniec().getX()/2,tor.getKoniec().getY()));
        this.nazwa = nazwa;
        this.czasPrzyjazdu = czasPrzyjazdu;
        this.tor = tor;
        this.przejazd = przejazd;
        copyCel(tor.getKoniec());
    }

    @Override
    public String toString() {
        return "Pociag: " + nazwa + "\tV= " + getPredkosc() + "\tX= " + getPolozenie().getX() +
                "\tY= " + getPolozenie().getY() + "\tETA= " + czasPrzyjazdu + "\tOP= " + spoznienie;
    }

    public Integer getCzasPrzyjazdu() { return czasPrzyjazdu; }

    public double getSpoznienie() { return spoznienie; }

    public void setSpoznienie(double spoznienie) { this.spoznienie = spoznienie; }

    public void sprawdzCzujniki() {
        boolean pociagNadCzujnikiem;
        boolean pociagZaCzujnikiem;

        if (tor.getZwrot().equals("prawo")) {
            for (int i=0; i<tor.getIloscSemaforow(); i++) {
                pociagNadCzujnikiem = (getPolozenie().getX() - getDlugosc()) < tor.getCzujnikiNajazdoweSBL().get(i).getPolozenie().getX() && tor.getCzujnikiNajazdoweSBL().get(i).getPolozenie().getX() < getPolozenie().getX();
                if (pociagNadCzujnikiem)
                    tor.getCzujnikiNajazdoweSBL().get(i).aktywuj(nazwa);

                pociagZaCzujnikiem = (getPolozenie().getX() - getDlugosc() * 2) < tor.getCzujnikiZjazdoweSBL().get(i).getPolozenie().getX() && tor.getCzujnikiZjazdoweSBL().get(i).getPolozenie().getX() < (getPolozenie().getX() - getDlugosc());
                if (pociagZaCzujnikiem)
                    tor.getCzujnikiZjazdoweSBL().get(i).aktywuj(nazwa);
            }
        } else {
            for (int i=0; i<tor.getIloscSemaforow(); i++) {
                pociagNadCzujnikiem = tor.getCzujnikiNajazdoweSBL().get(i).getPolozenie().getX() < getPolozenie().getX() && tor.getCzujnikiNajazdoweSBL().get(i).getPolozenie().getX() < (getPolozenie().getX() + getDlugosc());
                if (pociagNadCzujnikiem)
                    tor.getCzujnikiNajazdoweSBL().get(i).aktywuj(nazwa);

                pociagZaCzujnikiem = tor.getCzujnikiZjazdoweSBL().get(i).getPolozenie().getX() < (getPolozenie().getX() + getDlugosc()) && tor.getCzujnikiZjazdoweSBL().get(i).getPolozenie().getX() < (getPolozenie().getX() + getDlugosc() * 2);
                if (pociagZaCzujnikiem)
                    tor.getCzujnikiZjazdoweSBL().get(i).aktywuj(nazwa);
            }
        }
    }

    public void sprawdzSemafory() {
        if (tor.getZwrot().equals("prawo")) {
            for (int i = 0; i < tor.getIloscSemaforow(); i++) {
                if (getPolozenie().getX() <= tor.getSemaforySBL().get(i).getPolozenie().getX()) {
                    if (tor.getSemaforySBL().get(i).isStop()) {
                        copyCel(tor.getSemaforySBL().get(i).getPolozenie());
                        break;
                    } else if (getCel().getX() != przejazd.getPolozenie().getX())
                        copyCel(tor.getKoniec());
                }
            }
        } else {
            for (int i = 0; i < tor.getIloscSemaforow(); i++) {
                if (tor.getSemaforySBL().get(i).getPolozenie().getX() <= getPolozenie().getX()) {
                    if (tor.getSemaforySBL().get(i).isStop()) {
                        copyCel(tor.getSemaforySBL().get(i).getPolozenie());
                        break;
                    } else if (getCel().getX() != przejazd.getPolozenie().getX())
                        copyCel(tor.getKoniec());
                }
            }
        }
    }

    public void sprawdzSSP() {
        if (tor.getZwrot().equals("prawo")) {
            if (getPolozenie().getX() < tor.getSemaforSSP().getPolozenie().getX()) {
                if (tor.getSemaforSSP().isStop())
                    uwazajNaPrzejezdzie = true;
                else
                    uwazajNaPrzejezdzie = false;
            }

        } else { // zwrot == "lewo"
            if (tor.getSemaforSSP().getPolozenie().getX() < getPolozenie().getX()) {
                if (tor.getSemaforSSP().isStop())
                    uwazajNaPrzejezdzie = true;
                else
                    uwazajNaPrzejezdzie = false;
            }
        }
    }

    @Override
    public void run() {
//        tor.getCzujnikSBL1().aktywuj(nazwa);
        System.out.println(this + "\tZGŁASZAM SIĘ!");
        double deltaT = 200.0/1000;
        while(true) {

            sprawdzCzujniki();
            sprawdzSemafory();
            sprawdzSSP();

            if (getCel().getX() != getPolozenie().getX()) {

                System.out.println(this);

                if (uwazajNaPrzejezdzie && getCel().getX() == tor.getKoniec().getX()) {
                    copyCel(przejazd.getPolozenie());
                }

                if (getCel().getX() == przejazd.getPolozenie().getX() && (getCel().getX() - getPolozenie().getX()) < getDrogaHamowania()) { //(Math.abs(getCel().getX() - przejazd.getPolozenie().getX()) < 50) {
                    if (getPredkosc() <= 5.56 && !przejazd.isRogatkiOtwarte()) { // 5.56m/s ~= 20km/h
//                        utrzymujPredkosc = true;
                        copyCel(tor.getKoniec());
                        uwazajNaPrzejezdzie = false;
                    }
                }
                if (Math.abs(getCel().getX() - getPolozenie().getX()) < getDrogaHamowania()) {
//                    if (!utrzymujPredkosc)
                        hamuj(deltaT);
                } else // if (getPredkosc() < getMaxPredkosc() && !utrzymujPredkosc)
                    przyspiesz(deltaT);
/*
                if (utrzymujPredkosc) {
                    boolean przodZaPrzejazdem;
                    if (tor.getZwrot().equals("prawo"))
                        przodZaPrzejazdem = getPolozenie().getX() < przejazd.getPolozenie().getX();
                    else // zwrot == "lewo"
                        przodZaPrzejazdem = przejazd.getPolozenie().getX() < getPolozenie().getX();
                    if (przodZaPrzejazdem)
                        utrzymujPredkosc = false;
                }
*/

                getPolozenie().przenies(getPredkosc(), deltaT, tor.getZwrot(), getCel());

            } else if (getPredkosc() != 0){
                setPredkosc(0);
            }
            
            try {
                sleep((long) (deltaT*1000));
            } catch (InterruptedException e) {
//                tor.getCzujnikSBL3().aktywuj(nazwa);
                System.out.println(this + "\tDO WIDZENIA!");
                stop();
            }
        }
    }
}
