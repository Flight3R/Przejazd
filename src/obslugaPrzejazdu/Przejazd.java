package obslugaPrzejazdu;

import klasyAbstrakcyjne.obiektSymulacji;
import lokacja.Polozenie;
import podlozaTransportowe.PasRuchu;
import podlozaTransportowe.Tor;

import javax.swing.*;
import java.util.ArrayList;


public class Przejazd extends obiektSymulacji {
    
    private final ArrayList<PasRuchu> listaPasow;
    private final ArrayList<Tor> listaTorow;

    private final Rozklad pociagiObecne = new Rozklad();
    private double czas;

    public Przejazd(Polozenie polozenie, String nazwa, ArrayList<PasRuchu> listaPasow, ArrayList<Tor> listaTorow, double czas, ImageIcon ikona) {
        super(polozenie, nazwa, ikona);
        this.listaPasow = listaPasow;
        this.listaTorow = listaTorow;
        this.czas = czas;
        start();
    }

    @Override
    public String toString() {
        return "Przejazd: " + nazwa + "\tX= " + getPolozenie().getX() + "\tY= " + getPolozenie().getY() + "\tT= " + Math.round(czas*100.0)/100.0;
    }

    public ArrayList<PasRuchu> getListaPasow() {
        return listaPasow;
    }

    public ArrayList<Tor> getListaTorow() {
        return listaTorow;
    }

    public Rozklad getPociagiObecne() {
        return pociagiObecne;
    }

    public double getCzas() {
        return czas;
    }

    public boolean isRogatkaOtwarta() {
        return listaPasow.stream().anyMatch(pasRuchu -> pasRuchu.getRogatka().isOtwarta());
    }

    public void sterowanieSBL() {
        for (Tor torBierzacy : listaTorow) {
            for (int j = 0; j < torBierzacy.getIloscSemaforowSBL(); j++) {
                if (torBierzacy.getCzujnikiNajazdoweSBL().get(j).isAktywowany()) {
                    torBierzacy.getCzujnikiNajazdoweSBL().get(j).deaktywuj();
                    torBierzacy.getSemaforySBL().get(j).podajSTOP();
                }
                if (torBierzacy.getCzujnikiZjazdoweSBL().get(j).isAktywowany()) {
                    torBierzacy.getCzujnikiZjazdoweSBL().get(j).deaktywuj();
                    torBierzacy.getSemaforySBL().get(j).podajJEDZ();
                }
            }
        }
    }

    public void sterowanieAutomatyczne() {
        boolean przejazdZajety = listaTorow.stream().anyMatch(tor -> !tor.getCzujnikNajazdowySSP().getAktywacje().equals(tor.getCzujnikZjazdowySSP().getAktywacje()));

        for (PasRuchu pasBierzacy : listaPasow) {
            if (przejazdZajety) {
                if (!pasBierzacy.getSygnalizacja().isStop()) {
                    pasBierzacy.getSygnalizacja().podajSTOP();
                    pasBierzacy.getRogatka().zamknij();
                }
            } else {
                if (!pasBierzacy.getRogatka().isOtwarta()) {
                    pasBierzacy.getSygnalizacja().podajJEDZ();
                    pasBierzacy.getRogatka().otworz();
                }
            }
        }
    }

    public void sterowanieSSP() {
        boolean przejazdZajety = listaTorow.stream().anyMatch(tor -> !tor.getCzujnikNajazdowySSP().getAktywacje().equals(tor.getCzujnikZjazdowySSP().getAktywacje()));
        boolean przejazdOtwarty = listaPasow.stream().anyMatch(pasRuchu -> !pasRuchu.getSygnalizacja().isStop());

        for (Tor torBierzacy : listaTorow) {
            if (przejazdZajety) {
                if (przejazdOtwarty) {
                    if (!torBierzacy.getTarczaSSP().isStop())
                        torBierzacy.getTarczaSSP().podajSTOP();
                } else {
                    if (torBierzacy.getTarczaSSP().isStop())
                        torBierzacy.getTarczaSSP().podajJEDZ();
                }
            } else {
                if (torBierzacy.getTarczaSSP().isStop())
                    torBierzacy.getTarczaSSP().podajJEDZ();
            }
        }
    }

    @Override
    public void run() {
        System.out.println(this + "\tROZPOCZYNAM DYŻUR!");
        double deltaT = 40.0/1000;
        while (true) {

            sterowanieSBL();

            if (1==1) // >>>>>>>>>>>>>>>>>> STAN PRZEŁĄCZNIKA NA PULPICIE
                sterowanieAutomatyczne();
            else
                sterowanieSSP();

            czas = czas + deltaT;
            try { sleep((long) (deltaT*1000)); } catch (InterruptedException e) { e.printStackTrace(); }

        }
    }
}
