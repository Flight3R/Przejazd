package pojazdy;

import klasyAbstrakcyjne.Pojazd;
import lokacja.Polozenie;
import obslugaPrzejazdu.Przejazd;
import podlozaTransportowe.Tor;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class Pociag extends Pojazd {

    private final Integer czasPrzyjazdu;
    private double spoznienie = 0;
    private final Tor tor;
    private final Przejazd przejazd;

    public Pociag(String nazwa, int dlugosc, Integer masa, double maxPredkosc, Integer czasPrzyjazdu, Tor tor, Przejazd przejazd, ImageIcon ikona) {
        super(new Polozenie(-tor.getKoniec().getX()/2,tor.getPolozenie().getY()), nazwa, "Pociag" , tor.getZwrot(), dlugosc, masa, maxPredkosc, ikona);
        this.czasPrzyjazdu = czasPrzyjazdu;
        this.tor = tor;
        this.przejazd = przejazd;
        ustawCel(tor.getKoniec());
    }

    @Override
    public String toString() {
        return "Pociag: " + getNazwa() + "\tV= " + Math.round(getPredkosc()*100.0)/100.0 + "\tX= " + Math.round(getPolozenie().getX()*100.0)/100.0 +
                "\tY= " + getPolozenie().getY() + "\tETA= " + czasPrzyjazdu + "\tSP= " + Math.round(spoznienie) + "\tCEL=" + getCel().getX();
    }

// ------------------ gettery ------------------
    public Integer getCzasPrzyjazdu() {
        return czasPrzyjazdu;
    }

    public double getSpoznienie() {
        return spoznienie;
    }

    public Tor getTor() {
        return tor;
    }

// ------------------ settery ------------------
    public void setSpoznienie(double spoznienie) {
        this.spoznienie = spoznienie;
    }

// ------------------ metody ------------------
    public void ustawCel(Polozenie cel) {
        getCel().setX(cel.getX() - getOdstep());
    }

    public void sprawdzCzujniki() {
        boolean pociagNadCzujnikiem;
        boolean pociagZaCzujnikiem;

        if (tor.getZwrot().equals("prawo")) {
            for (int i = 0; i<tor.getIloscSemaforowSBL(); i++) {
                pociagNadCzujnikiem = (getPolozenie().getX() - getDlugosc()) < tor.getCzujnikiNajazdoweSBL().get(i).getPolozenie().getX() && tor.getCzujnikiNajazdoweSBL().get(i).getPolozenie().getX() < getPolozenie().getX();
                if (pociagNadCzujnikiem)
                    tor.getCzujnikiNajazdoweSBL().get(i).aktywuj(getNazwa());

                pociagZaCzujnikiem = (getPolozenie().getX() - getDlugosc() * 2) < tor.getCzujnikiZjazdoweSBL().get(i).getPolozenie().getX() && tor.getCzujnikiZjazdoweSBL().get(i).getPolozenie().getX() < (getPolozenie().getX() - getDlugosc());
                if (pociagZaCzujnikiem)
                    tor.getCzujnikiZjazdoweSBL().get(i).aktywuj(getNazwa());
            }
        } else {
            for (int i = 0; i<tor.getIloscSemaforowSBL(); i++) {
                pociagNadCzujnikiem = getPolozenie().getX() < tor.getCzujnikiNajazdoweSBL().get(i).getPolozenie().getX() && tor.getCzujnikiNajazdoweSBL().get(i).getPolozenie().getX() < (getPolozenie().getX() + getDlugosc());
                if (pociagNadCzujnikiem)
                    tor.getCzujnikiNajazdoweSBL().get(i).aktywuj(getNazwa());

                pociagZaCzujnikiem = (getPolozenie().getX() + getDlugosc()) < tor.getCzujnikiZjazdoweSBL().get(i).getPolozenie().getX() && tor.getCzujnikiZjazdoweSBL().get(i).getPolozenie().getX() < (getPolozenie().getX() + getDlugosc() * 2);
                if (pociagZaCzujnikiem)
                    tor.getCzujnikiZjazdoweSBL().get(i).aktywuj(getNazwa());
            }
        }
    }

    public Polozenie sprawdzSemaforySBL() {
        if (tor.getZwrot().equals("prawo")) {
            for (int i = 0; i < tor.getIloscSemaforowSBL(); i++) {
                if (getPolozenie().getX() <= tor.getSemaforySBL().get(i).getPolozenie().getX() && tor.getSemaforySBL().get(i).isStop())
                    return tor.getSemaforySBL().get(i).getPolozenie();
            }
        } else {
            for (int i = 0; i < tor.getIloscSemaforowSBL(); i++) {
                if (tor.getSemaforySBL().get(i).getPolozenie().getX() <= getPolozenie().getX() && tor.getSemaforySBL().get(i).isStop())
                    return tor.getSemaforySBL().get(i).getPolozenie();
            }
        }
        return tor.getKoniec();
    }

    public Polozenie sprawdzSSP() {
        boolean czujnikNpodPociagiem;
        boolean czujnikZpodPociagiem;

        if (tor.getZwrot().equals("prawo")) {
            czujnikNpodPociagiem = (getPolozenie().getX() - getDlugosc()) < tor.getCzujnikNajazdowySSP().getPolozenie().getX() && tor.getCzujnikNajazdowySSP().getPolozenie().getX() < getPolozenie().getX();
            czujnikZpodPociagiem = (getPolozenie().getX() - getDlugosc()*2) < tor.getCzujnikZjazdowySSP().getPolozenie().getX() && tor.getCzujnikZjazdowySSP().getPolozenie().getX() < (getPolozenie().getX() - getDlugosc());

            if (czujnikNpodPociagiem)
                tor.getCzujnikNajazdowySSP().aktywuj(getNazwa());

            else if (czujnikZpodPociagiem)
                    tor.getCzujnikZjazdowySSP().aktywuj(getNazwa());

            if ((getPolozenie().getX() < tor.getTarczaSSP().getPolozenie().getX() && tor.getTarczaSSP().isStop()) || (getPolozenie().getX() < przejazd.getPolozenie().getX() && getCel().getX() == przejazd.getPolozenie().getX() - getOdstep()))
                    return przejazd.getPolozenie();

        } else {
            czujnikNpodPociagiem = getPolozenie().getX() < tor.getCzujnikNajazdowySSP().getPolozenie().getX() && tor.getCzujnikNajazdowySSP().getPolozenie().getX() < (getPolozenie().getX() + getDlugosc());
            czujnikZpodPociagiem = (getPolozenie().getX() + getDlugosc()) < tor.getCzujnikZjazdowySSP().getPolozenie().getX() && tor.getCzujnikZjazdowySSP().getPolozenie().getX() < (getPolozenie().getX() + getDlugosc()*2);

            if (czujnikNpodPociagiem)
                tor.getCzujnikNajazdowySSP().aktywuj(getNazwa());

            else if (czujnikZpodPociagiem)
                tor.getCzujnikZjazdowySSP().aktywuj(getNazwa());

            if ((getPolozenie().getX() > tor.getTarczaSSP().getPolozenie().getX() && tor.getTarczaSSP().isStop()) || (getPolozenie().getX() > przejazd.getPolozenie().getX() && getCel().getX() == przejazd.getPolozenie().getX() - getOdstep()))
                return przejazd.getPolozenie();
         }
        return tor.getKoniec();
    }

    @Override
    public void run() {
        System.out.println(this + "\tZGŁASZAM SIĘ!");
        double deltaT = 40.0/1000;
        while(true) {

            sprawdzCzujniki();

            if (tor.getZwrot().equals("prawo"))
                ustawCel(Arrays.stream(new Polozenie[]{sprawdzSemaforySBL(), sprawdzSSP()}).reduce((p1, p2) -> p1.getX() < p2.getX() ? p1 : p2).orElse(tor.getKoniec()));
            else
                ustawCel(Arrays.stream(new Polozenie[]{sprawdzSemaforySBL(), sprawdzSSP()}).reduce((p1, p2) -> p1.getX() < p2.getX() ? p2 : p1).orElse(tor.getKoniec()));

            if (getCel().getX() == przejazd.getPolozenie().getX() - getOdstep()) {
                if (Math.abs(getPolozenie().getX() - (przejazd.getPolozenie().getX() - getOdstep())) < 20 && !przejazd.isRogatkaOtwarta())
                    ustawCel(tor.getKoniec());
            }

            if (getCel().getX() != getPolozenie().getX()) {

//                System.out.println(this); // >>>>>>>>>>>>>>>>>>>>>PRINT

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
                return;
            }
        }
    }
}
