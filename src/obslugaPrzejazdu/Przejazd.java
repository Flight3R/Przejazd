package obslugaPrzejazdu;

import klasyAbstrakcyjne.ElementInfrastruktury;
import lokacja.Polozenie;
import podlozaTransportowe.PasRuchu;
import podlozaTransportowe.Tor;

import java.util.ArrayList;

public class Przejazd extends ElementInfrastruktury {
    
    private final ArrayList<PasRuchu> listaPasow;
    private final Tor torGorny;
    private final Tor torDolny;
    private final Rozklad rozkladGorny;
    private final Rozklad rozkladDolny;
    private final Rozklad pociagiObecne = new Rozklad();
    private double czas;

    public Przejazd(Polozenie polozenie, String nazwa, ArrayList<PasRuchu> listaPasow, Tor torGorny, Tor torDolny, Rozklad rozkladGorny, Rozklad rozkladDolny, double czas) {
        super(polozenie, nazwa);
        this.listaPasow = listaPasow;
        this.torGorny = torGorny;
        this.torDolny = torDolny;
        this.rozkladGorny = rozkladGorny;
        this.rozkladDolny = rozkladDolny;
        this.czas = czas;
        start();
    }

    @Override
    public String toString() {
        return "Przejazd: " + nazwa + "\tX= " + getPolozenie().getX() + "\tY= " + getPolozenie().getY() + "\tT= " + Math.round(czas*100.0)/100.0;
    }

    public ArrayList<PasRuchu> getListaPasow() { return listaPasow; }

    public Tor getTorGorny() { return torGorny; }

    public Tor getTorDolny() { return torDolny; }

    public Rozklad getRozkladGorny() { return rozkladGorny; }

    public Rozklad getRozkladDolny() { return rozkladDolny; }

    public Rozklad getPociagiObecne() { return pociagiObecne; }

    public double getCzas() { return czas; }

    public boolean isRogatkaOtwarta() {
        return listaPasow.stream().anyMatch(pasRuchu -> pasRuchu.getRogatka().isOtwarta());
    }

    public void sterowanieSBL() {
        for (int i = 0; i<torGorny.getIloscSemaforowSBL(); i++) {
            if (torGorny.getCzujnikiNajazdoweSBL().get(i).isAktywowany()) {
                torGorny.getCzujnikiNajazdoweSBL().get(i).setAktywowany(false);
                torGorny.getSemaforySBL().get(i).podajSTOP();
            }
            if (torGorny.getCzujnikiZjazdoweSBL().get(i).isAktywowany()) {
                torGorny.getCzujnikiZjazdoweSBL().get(i).setAktywowany(false);
                torGorny.getSemaforySBL().get(i).podajJEDZ();
            }
        }
        for (int i = 0; i<torDolny.getIloscSemaforowSBL(); i++) {
            if (torDolny.getCzujnikiNajazdoweSBL().get(i).isAktywowany()) {
                torDolny.getCzujnikiNajazdoweSBL().get(i).setAktywowany(false);
                torDolny.getSemaforySBL().get(i).podajSTOP();
            }
            if (torDolny.getCzujnikiZjazdoweSBL().get(i).isAktywowany()) {
                torDolny.getCzujnikiZjazdoweSBL().get(i).setAktywowany(false);
                torDolny.getSemaforySBL().get(i).podajJEDZ();
            }
        }
    }

    public void sterowanieAutomatyczne() {
        boolean zajetoscOdcinkaGornego = !torGorny.getCzujnikNajazdowySSP().getAktywacje().equals(torGorny.getCzujnikZjazdowySSP().getAktywacje());
        boolean zajetoscOdcinkaDolnego = !torDolny.getCzujnikNajazdowySSP().getAktywacje().equals(torDolny.getCzujnikZjazdowySSP().getAktywacje());

        if(zajetoscOdcinkaGornego || zajetoscOdcinkaDolnego) {
            if (listaPasow.stream().anyMatch(pasRuchu -> !pasRuchu.getSygnalizacja().isStop())) {
                for (int i = 0; i<listaPasow.size(); i++) {
                    listaPasow.get(i).getSygnalizacja().podajSTOP();
                    listaPasow.get(i).getRogatka().zamknij();
                }
            }
        } else {
            if (listaPasow.stream().anyMatch(pasRuchu -> !pasRuchu.getRogatka().isOtwarta())) {
                for (int i = 0; i<listaPasow.size(); i++) {
                    listaPasow.get(i).getSygnalizacja().podajJEDZ();
                    listaPasow.get(i).getRogatka().otworz();
                }
            }
        }
    }

    public void sterowanieSSP() {
        boolean zajetoscOdcinkaGornego = !torGorny.getCzujnikNajazdowySSP().getAktywacje().equals(torGorny.getCzujnikZjazdowySSP().getAktywacje());
        boolean zajetoscOdcinkaDolnego = !torDolny.getCzujnikNajazdowySSP().getAktywacje().equals(torDolny.getCzujnikZjazdowySSP().getAktywacje());

        if (zajetoscOdcinkaGornego || zajetoscOdcinkaDolnego) {
            if (listaPasow.stream().anyMatch(pasRuchu -> !pasRuchu.getSygnalizacja().isStop())) {
                if (!torGorny.getTarczaSSP().isStop() || !torDolny.getTarczaSSP().isStop()) {
                    torGorny.getTarczaSSP().podajSTOP();
                    torDolny.getTarczaSSP().podajSTOP();
                }
            } else if (torGorny.getTarczaSSP().isStop() || torDolny.getTarczaSSP().isStop()) {
                torGorny.getTarczaSSP().podajJEDZ();
                torDolny.getTarczaSSP().podajJEDZ();
            }
        } else if (torGorny.getTarczaSSP().isStop() || torDolny.getTarczaSSP().isStop()) {
            torGorny.getTarczaSSP().podajJEDZ();
            torDolny.getTarczaSSP().podajJEDZ();
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
