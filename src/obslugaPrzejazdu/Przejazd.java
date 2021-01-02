package obslugaPrzejazdu;

import klasyAbstrakcyjne.ElementInfrastruktury;
import lokacja.Polozenie;
import podlozaTransportowe.PasRuchu;
import podlozaTransportowe.Tor;

import java.util.ArrayList;
import java.util.TreeMap;

public class Przejazd extends ElementInfrastruktury {
    
    private final ArrayList<PasRuchu> listaPasow;
    private final ArrayList<Tor> listaTorow;

    private final Rozklad pociagiObecne = new Rozklad();
    private double czas;

    public Przejazd(Polozenie polozenie, String nazwa, ArrayList<PasRuchu> listaPasow, ArrayList<Tor> listaTorow, double czas) {
        super(polozenie, nazwa);
        this.listaPasow = listaPasow;
        this.listaTorow = listaTorow;
        this.czas = czas;
        start();
    }

    @Override
    public String toString() {
        return "Przejazd: " + nazwa + "\tX= " + getPolozenie().getX() + "\tY= " + getPolozenie().getY() + "\tT= " + Math.round(czas*100.0)/100.0;
    }

    public ArrayList<PasRuchu> getListaPasow() { return listaPasow; }

    public ArrayList<Tor> getListaTorow() { return listaTorow; }

    public Rozklad getPociagiObecne() { return pociagiObecne; }

    public double getCzas() { return czas; }

    public boolean isRogatkaOtwarta() {
        return listaPasow.stream().anyMatch(pasRuchu -> pasRuchu.getRogatka().isOtwarta());
    }

    public void sterowanieSBL() {
        for (Tor torBierzacy : listaTorow) {
            for (int j = 0; j < torBierzacy.getIloscSemaforowSBL(); j++) {
                if (torBierzacy.getCzujnikiNajazdoweSBL().get(j).isAktywowany()) {
                    torBierzacy.getCzujnikiNajazdoweSBL().get(j).setAktywowany(false);
                    torBierzacy.getSemaforySBL().get(j).podajSTOP();
                }
                if (torBierzacy.getCzujnikiZjazdoweSBL().get(j).isAktywowany()) {
                    torBierzacy.getCzujnikiZjazdoweSBL().get(j).setAktywowany(false);
                    torBierzacy.getSemaforySBL().get(j).podajJEDZ();
                }
            }
        }
    }

    public void sterowanieAutomatyczne() {
        boolean przejazdZajety = listaTorow.stream().anyMatch(tor -> tor.getCzujnikNajazdowySSP().getAktywacje().equals(tor.getCzujnikZjazdowySSP().getAktywacje()));

        if(przejazdZajety) {
            if (listaPasow.stream().anyMatch(pasRuchu -> !pasRuchu.getSygnalizacja().isStop())) {
                for (PasRuchu pasRuchu : listaPasow) {
                    pasRuchu.getSygnalizacja().podajSTOP();
                    pasRuchu.getRogatka().zamknij();
                }
            }
        } else {
            if (listaPasow.stream().anyMatch(pasRuchu -> !pasRuchu.getRogatka().isOtwarta())) {
                for (PasRuchu pasRuchu : listaPasow) {
                    pasRuchu.getSygnalizacja().podajJEDZ();
                    pasRuchu.getRogatka().otworz();
                }
            }
        }
    }

    public void sterowanieSSP() {
        boolean przejazdZajety = listaTorow.stream().anyMatch(tor -> tor.getCzujnikNajazdowySSP().getAktywacje().equals(tor.getCzujnikZjazdowySSP().getAktywacje()));

        if (przejazdZajety) {
            if (listaPasow.stream().anyMatch(pasRuchu -> !pasRuchu.getSygnalizacja().isStop())) {
                for (Tor torBierzacy : listaTorow) {
                    if (!torBierzacy.getTarczaSSP().isStop())
                        torBierzacy.getTarczaSSP().podajSTOP();
                }
            } else {
                for (Tor torBierzacy : listaTorow) {
                    if (torBierzacy.getTarczaSSP().isStop())
                        torBierzacy.getTarczaSSP().podajJEDZ();
                }
            }
        } else {
            for (Tor torBierzacy : listaTorow) {
                if (torBierzacy.getTarczaSSP().isStop())
                    torBierzacy.getTarczaSSP().podajJEDZ();
            }
        }
    }

    @Override
    public void run() {
        System.out.println(this + "\tROZPOCZYNAM DYŻUR!");
        double deltaT = 200.0/1000;
        while (true) {

            sterowanieSBL();

            if (1==0) // >>>>>>>>>>>>>>>>>> STAN PRZEŁĄCZNIKA NA PULPICIE
                sterowanieAutomatyczne();
            else
                sterowanieSSP();

            czas = czas + deltaT;
            try { sleep((long) (deltaT*1000)); } catch (InterruptedException e) { e.printStackTrace(); }

        }
    }
}
