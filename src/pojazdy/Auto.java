package pojazdy;

import klasyAbstrakcyjne.Pojazd;
import lokacja.Polozenie;
import podlozaTransportowe.PasRuchu;

import javax.swing.*;
import java.util.Arrays;

public class Auto extends Pojazd {

    private final PasRuchu pas;
    private Auto autoPrzed;

    public Auto(String nazwa, int dlugosc, Integer masa, double maxPredkosc, PasRuchu pas, Auto autoPrzed, ImageIcon ikona) {
        super(new Polozenie(pas.getPolozenie().getX(), -pas.getKoniec().getY()/2), nazwa, "Auto", pas.getZwrot(), dlugosc, masa, maxPredkosc, ikona);
        this.pas = pas;
        this.autoPrzed = autoPrzed;
        ustawCel(pas.getKoniec());
    }

    @Override
    public String toString() {
        return "Auto: " + getNazwa() + "\tV= " + Math.round(getPredkosc()*100.0)/100.0 + "\tX= " + getPolozenie().getX() +
                "\tY= " + Math.round(getPolozenie().getY()*100.0)/100.0 + "\tCEL= " + getCel().getY();
    }

// ------------------ gettery ------------------
    public PasRuchu getPas() {
        return pas;
    }

// ------------------ settery ------------------
    public void setAutoPrzed(Auto autoPrzed) {
        this.autoPrzed = autoPrzed;
    }

// ------------------ metody ------------------
    public void ustawCel(Polozenie cel) {
        getCel().setY(cel.getY() - getOdstep());
    }

    public Polozenie sprawdzSwiatla() {
        boolean przedSygnalizatorem;
        boolean mozliwoscWyhamowania;

        if (pas.getZwrot() == "gora") {
            przedSygnalizatorem = getPolozenie().getY() <= pas.getSygnalizacja().getPolozenie().getY();
            mozliwoscWyhamowania = getDrogaHamowania() <= (pas.getSygnalizacja().getPolozenie().getY() - getOdstep() - getPolozenie().getY());

        } else {
            przedSygnalizatorem = pas.getSygnalizacja().getPolozenie().getY() <= getPolozenie().getY();
            mozliwoscWyhamowania = getDrogaHamowania() <= (getPolozenie().getY() - (pas.getSygnalizacja().getPolozenie().getY() - getOdstep()));

        }

        if (przedSygnalizatorem && pas.getSygnalizacja().isStop()) {
            if (getCel().getY() == pas.getSygnalizacja().getPolozenie().getY() - getOdstep() || mozliwoscWyhamowania)
                return pas.getSygnalizacja().getPolozenie();
        }
        return pas.getKoniec();
    }

    public Polozenie sprawdzAutoPrzed() {
        if (autoPrzed != null) {
            if (pas.getZwrot() == "gora") {
//                if (Math.abs(autoPrzed.getPolozenie().getY() - autoPrzed.getDlugosc() - getOdstep() - getPolozenie().getY()) <= getDrogaHamowania())
                    return new Polozenie(0, autoPrzed.getPolozenie().getY() - autoPrzed.getDlugosc());

            } else {
//                if (Math.abs(autoPrzed.getPolozenie().getY() + autoPrzed.getDlugosc() - getOdstep() - getPolozenie().getY()) <= getDrogaHamowania())
                    return new Polozenie(0, autoPrzed.getPolozenie().getY() + autoPrzed.getDlugosc());
            }
        }
        return pas.getKoniec();
    }

    @Override
    public void run() {
        double deltaT = 40.0/1000;
        while(true) {

            if (pas.getZwrot().equals("gora"))
                ustawCel(Arrays.stream(new Polozenie[]{sprawdzAutoPrzed(),  sprawdzSwiatla()}).reduce((p1, p2) -> p1.getY() < p2.getY() ? p1 : p2).orElse(pas.getKoniec()));
            else
                ustawCel(Arrays.stream(new Polozenie[]{sprawdzAutoPrzed(),  sprawdzSwiatla()}).reduce((p1, p2) -> p1.getY() < p2.getY() ? p2 : p1).orElse(pas.getKoniec()));


            if (getCel().getY() != getPolozenie().getY()) {

//                System.out.println(this + "   " + getDrogaHamowania());

                if (Math.abs(getCel().getY() - getPolozenie().getY()) < getDrogaHamowania()*2)
                    hamuj(deltaT);
                else
                    przyspiesz(deltaT);

                getPolozenie().przenies(getPredkosc(), deltaT, pas.getZwrot(), getCel());
            } else if (getPredkosc() != 0){
                setPredkosc(0);
            }

            try { sleep((long) (deltaT*1000)); } catch (InterruptedException interruptedException) { return; }
        }
    }
}

