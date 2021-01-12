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
                    torBierzacy.getSemaforySBL().get(j).getLabel().setIcon(new ImageIcon(torBierzacy.getZwrot().equals("prawo")?"src/grafiki/s1_prawo.png":"src/grafiki/s1_lewo.png"));

                }
                if (torBierzacy.getCzujnikiZjazdoweSBL().get(j).isAktywowany()) {
                    torBierzacy.getCzujnikiZjazdoweSBL().get(j).deaktywuj();
                    torBierzacy.getSemaforySBL().get(j).podajJEDZ();
                    torBierzacy.getSemaforySBL().get(j).getLabel().setIcon(new ImageIcon(torBierzacy.getZwrot().equals("prawo")?"src/grafiki/s2_prawo.png":"src/grafiki/s2_lewo.png"));

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
                    pasBierzacy.getSygnalizacja().getLabel().setIcon(new ImageIcon(pasBierzacy.getZwrot().equals("gora") ?"src/grafiki/sygnalizacja1_gora.png":"src/grafiki/sygnalizacja1_dol.png"));
                    pasBierzacy.getRogatka().zamknij();

                }
            } else {
                if (!pasBierzacy.getRogatka().isOtwarta()) {
                    pasBierzacy.getSygnalizacja().podajJEDZ();
                    pasBierzacy.getSygnalizacja().getLabel().setIcon(new ImageIcon(pasBierzacy.getZwrot().equals("gora") ?"src/grafiki/sygnalizacja_gora.png":"src/grafiki/sygnalizacja_dol.png"));
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
                    if (!torBierzacy.getTarczaSSP().isStop()) {
                        torBierzacy.getTarczaSSP().podajSTOP();
                        torBierzacy.getTarczaSSP().getLabel().setIcon(new ImageIcon(torBierzacy.getZwrot().equals("prawo")?"src/grafiki/osp1_prawo.png":"src/grafiki/osp1_lewo.png"));
                    }
                } else {
                    if (torBierzacy.getTarczaSSP().isStop()) {
                        torBierzacy.getTarczaSSP().podajJEDZ();
                        torBierzacy.getTarczaSSP().getLabel().setIcon(new ImageIcon(torBierzacy.getZwrot().equals("prawo")?"src/grafiki/osp2_prawo.png":"src/grafiki/osp2_lewo.png"));
                    }
                }
            } else {
                if (torBierzacy.getTarczaSSP().isStop()) {
                    torBierzacy.getTarczaSSP().podajJEDZ();
                    torBierzacy.getTarczaSSP().getLabel().setIcon(new ImageIcon(torBierzacy.getZwrot().equals("prawo")?"src/grafiki/osp2_prawo.png":"src/grafiki/osp2_lewo.png"));
                }
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
