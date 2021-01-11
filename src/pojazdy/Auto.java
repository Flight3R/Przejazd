package pojazdy;

import klasyAbstrakcyjne.Pojazd;
import lokacja.Polozenie;
import podlozaTransportowe.PasRuchu;

import javax.swing.*;

public class Auto extends Pojazd {

    private final PasRuchu pas;
    private final Auto autoPrzed;

    public Auto(String nazwa, int dlugosc, Integer masa, double maxPredkosc, PasRuchu pas, Auto autoPrzed, Icon ikona) {
        super(new Polozenie(pas.getPolozenie().getX(), -pas.getKoniec().getY()/2), nazwa, "Auto", pas.getZwrot(), dlugosc, masa, maxPredkosc, ikona);
        this.pas = pas;
        this.autoPrzed = autoPrzed;
        copyCel(pas.getKoniec());
    }

    @Override
    public String toString() {
        return "Auto: " + getNazwa() + "\tV= " + Math.round(getPredkosc()*100.0)/100.0 + "\tX= " + getPolozenie().getX() +
                "\tY= " + Math.round(getPolozenie().getY()*100.0)/100.0 + "\tCEL= " + getCel().getY();
    }

    public void copyCel(Polozenie cel) {
        getCel().setY(cel.getY() - getOdstep());
    }

    public boolean sprawdzSwiatla() {
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
            if (getCel().getY() == pas.getSygnalizacja().getPolozenie().getY() - getOdstep() || mozliwoscWyhamowania) {
                copyCel(pas.getSygnalizacja().getPolozenie());
                return true;
            }
        }
        return false;
    }

    public boolean sprawdzAutoPrzed() {

        if (pas.getZwrot() == "gora") {
            if (Math.abs(autoPrzed.getPolozenie().getY() - autoPrzed.getDlugosc() - getOdstep() - getPolozenie().getY()) <= getDrogaHamowania()) {
                getCel().setY(autoPrzed.getPolozenie().getY() - autoPrzed.getDlugosc() - getOdstep());
                return true;
            }
        } else {
            if (Math.abs(autoPrzed.getPolozenie().getY() + autoPrzed.getDlugosc() - getOdstep() - getPolozenie().getY()) <= getDrogaHamowania()) {
                getCel().setY(autoPrzed.getPolozenie().getY() + autoPrzed.getDlugosc() - getOdstep());
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        double deltaT = 200.0/1000;
        while(true) {
            boolean swiatla = sprawdzSwiatla();         // Aby zawsze sie wykonalo

            boolean autoPrzed;
            if (this.autoPrzed != null)
                autoPrzed = sprawdzAutoPrzed();     // Aby zawsze sie wykonalo i nadpisało jak cos
            else
                autoPrzed = false;

            if ( !swiatla && !autoPrzed && getCel().getY() != pas.getKoniec().getY() - getOdstep())
               copyCel(pas.getKoniec());

            if (getCel().getY() != getPolozenie().getY()) {

                System.out.println(this + "   " + getDrogaHamowania());

                if (Math.abs(getCel().getY() - getPolozenie().getY()) < getDrogaHamowania()*2)
                    hamuj(deltaT);
                else
                    przyspiesz(deltaT);

                getPolozenie().przenies(getPredkosc(), deltaT, pas.getZwrot(), getCel());
            } else if (getPredkosc() != 0){
                setPredkosc(0);
            }

            try { sleep(200); } catch (InterruptedException interruptedException) { stop(); }
        }
    }
}

