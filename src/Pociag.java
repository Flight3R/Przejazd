public class Pociag extends Pojazd {

    private final String nazwa;
    private final Integer czasPrzyjazdu;
    private double spoznienie = 0;
    private final Tor tor;
    private final Przejazd przejazd;

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
        return "Pociag: " + nazwa + "\tV= " + Math.round(getPredkosc()*100.0)/100.0 + "\tX= " + Math.round(getPolozenie().getX()*100.0)/100.0 +
                "\tY= " + getPolozenie().getY() + "\tETA= " + czasPrzyjazdu + "\tOP= " + spoznienie + "\tCEL=" + getCel().getX();
    }

    public Integer getCzasPrzyjazdu() { return czasPrzyjazdu; }

    public double getSpoznienie() { return spoznienie; }

    public void setSpoznienie(double spoznienie) { this.spoznienie = spoznienie; }

    public void sprawdzCzujniki() {
        boolean pociagNadCzujnikiem;
        boolean pociagZaCzujnikiem;

        if (tor.getZwrot().equals("prawo")) {
            for (int i = 0; i<tor.getIloscSemaforowSBL(); i++) {
                pociagNadCzujnikiem = (getPolozenie().getX() - getDlugosc()) < tor.getCzujnikiNajazdoweSBL().get(i).getPolozenie().getX() && tor.getCzujnikiNajazdoweSBL().get(i).getPolozenie().getX() < getPolozenie().getX();
                if (pociagNadCzujnikiem)
                    tor.getCzujnikiNajazdoweSBL().get(i).aktywuj(nazwa);

                pociagZaCzujnikiem = (getPolozenie().getX() - getDlugosc() * 2) < tor.getCzujnikiZjazdoweSBL().get(i).getPolozenie().getX() && tor.getCzujnikiZjazdoweSBL().get(i).getPolozenie().getX() < (getPolozenie().getX() - getDlugosc());
                if (pociagZaCzujnikiem)
                    tor.getCzujnikiZjazdoweSBL().get(i).aktywuj(nazwa);
            }
        } else {
            for (int i = 0; i<tor.getIloscSemaforowSBL(); i++) {
                pociagNadCzujnikiem = tor.getCzujnikiNajazdoweSBL().get(i).getPolozenie().getX() < getPolozenie().getX() && tor.getCzujnikiNajazdoweSBL().get(i).getPolozenie().getX() < (getPolozenie().getX() + getDlugosc());
                if (pociagNadCzujnikiem)
                    tor.getCzujnikiNajazdoweSBL().get(i).aktywuj(nazwa);

                pociagZaCzujnikiem = tor.getCzujnikiZjazdoweSBL().get(i).getPolozenie().getX() < (getPolozenie().getX() + getDlugosc()) && tor.getCzujnikiZjazdoweSBL().get(i).getPolozenie().getX() < (getPolozenie().getX() + getDlugosc() * 2);
                if (pociagZaCzujnikiem)
                    tor.getCzujnikiZjazdoweSBL().get(i).aktywuj(nazwa);
            }
        }
    }

    public boolean sprawdzSemaforySBL() {
        if (tor.getZwrot().equals("prawo")) {
            for (int i = 0; i < tor.getIloscSemaforowSBL(); i++) {
                if (getPolozenie().getX() <= tor.getSemaforySBL().get(i).getPolozenie().getX()) {
                    if (tor.getSemaforySBL().get(i).isStop()) {
                        copyCel(tor.getSemaforySBL().get(i).getPolozenie());
                        return true;
                    }
                }
            }
        } else {
            for (int i = 0; i < tor.getIloscSemaforowSBL(); i++) {
                if (tor.getSemaforySBL().get(i).getPolozenie().getX() <= getPolozenie().getX()) {
                    if (tor.getSemaforySBL().get(i).isStop()) {
                        copyCel(tor.getSemaforySBL().get(i).getPolozenie());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean sprawdzSSP() {
        if (tor.getZwrot().equals("prawo")) {
            if ((getPolozenie().getX() - getDlugosc()) < tor.getCzujnikSSPn().getPolozenie().getX() && tor.getCzujnikSSPn().getPolozenie().getX() < getPolozenie().getX())
                tor.getCzujnikSSPn().aktywuj(nazwa);    // nadSSP

            else if ((getPolozenie().getX() - getDlugosc()*2) < tor.getCzujnikSSPz().getPolozenie().getX() && tor.getCzujnikSSPz().getPolozenie().getX() < (getPolozenie().getX() - getDlugosc()))
                tor.getCzujnikSSPz().aktywuj(nazwa);    // zaSSP

            if (getPolozenie().getX() < tor.getTarczaSSP().getPolozenie().getX() && przejazd.getPolozenie().getX() < getCel().getX()) {
                if (tor.getTarczaSSP().isStop()) {
                    copyCel(przejazd.getPolozenie());
                    return true;
                }
            }

        } else { // zwrot == "lewo"
            if (getPolozenie().getX() < tor.getCzujnikSSPn().getPolozenie().getX() && tor.getCzujnikSSPn().getPolozenie().getX() < (getPolozenie().getX() + getDlugosc()))
                tor.getCzujnikSSPn().aktywuj(nazwa);    // nadSSP

            else if ((getPolozenie().getX() + getDlugosc()) < tor.getCzujnikSSPz().getPolozenie().getX() && tor.getCzujnikSSPz().getPolozenie().getX() < (getPolozenie().getX() + getDlugosc()*2))
                tor.getCzujnikSSPz().aktywuj(nazwa);    // zaSSP

            if (tor.getTarczaSSP().getPolozenie().getX() < getPolozenie().getX() && getCel().getX() < przejazd.getPolozenie().getX()) {
                if (tor.getTarczaSSP().isStop()) {
                    copyCel(przejazd.getPolozenie());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void run() {
        System.out.println(this + "\tZGŁASZAM SIĘ!");
        double deltaT = 200.0/1000;
        while(true) {

            sprawdzCzujniki();

            boolean sbl = sprawdzSemaforySBL();
            boolean ssp = sprawdzSSP();

            if (getCel().getX() == przejazd.getPolozenie().getX()) {
                if (getPredkosc() <= 5.56 && !przejazd.isRogatkiOtwarte())  // 5.56m/s ~= 20km/h
                    copyCel(tor.getKoniec());
            } else if (!sbl && !ssp)
                copyCel(tor.getKoniec());


            if (getCel().getX() != getPolozenie().getX()) {

                System.out.println(this); // >>>>>>>>>>>>>>>>>>>>>PRINT

                if (Math.abs(getCel().getX() - getPolozenie().getX()) < getDrogaHamowania()*1.2) {
                    hamuj(deltaT);
                } else
                    przyspiesz(deltaT);

                getPolozenie().przenies(getPredkosc(), deltaT, tor.getZwrot(), getCel());

            } else if (getPredkosc() != 0){
                setPredkosc(0);
            }
            
            try {
                sleep((long) (deltaT*1000));
            } catch (InterruptedException e) {
                System.out.println(this + "\tDO WIDZENIA!");
                stop();
            }
        }
    }
}
