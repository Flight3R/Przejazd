public class Przejazd extends ElementInfrastruktury {

    private final PasRuchu pasLewy;
    private final PasRuchu pasPrawy;
    private final Tor torGorny;
    private final Tor torDolny;
    private final Rozklad rozkladGorny;
    private final Rozklad rozkladDolny;
    private final Rozklad pociagiObecne = new Rozklad();
    private double czas;

    public Przejazd(Polozenie polozenie, String nazwa, PasRuchu pasLewy, PasRuchu pasPrawy, Tor torGorny, Tor torDolny, Rozklad rozkladGorny, Rozklad rozkladDolny, double czas) {
        super(polozenie, nazwa);
        this.pasLewy = pasLewy;
        this.pasPrawy = pasPrawy;
        this.torGorny = torGorny;
        this.torDolny = torDolny;
        this.rozkladGorny = rozkladGorny;
        this.rozkladDolny = rozkladDolny;
        this.czas = czas;
    }

    @Override
    public String toString() {
        return "Przejazd: " + nazwa + "\tX= " + getPolozenie().getX() + "\tY= " + getPolozenie().getY() + "\tT= " + Math.round(czas*100.0)/100.0;
    }

    public PasRuchu getPasLewy() { return pasLewy; }

    public PasRuchu getPasPrawy() { return pasPrawy; }

    public Tor getTorGorny() { return torGorny; }

    public Tor getTorDolny() { return torDolny; }

    public Rozklad getRozkladGorny() { return rozkladGorny; }

    public Rozklad getRozkladDolny() { return rozkladDolny; }

    public double getCzas() { return czas; }

    public boolean isRogatkiOtwarte() {
        return pasLewy.getRogatka().isOtwarta() || pasPrawy.getRogatka().isOtwarta();
    }

    public void sterowanieAutomatyczne() {
        boolean zajetoscOdcinkaGornego = (torGorny.getCzujnikiNajazdoweSBL().get(1).getAktywacje() + torGorny.getCzujnikiZjazdoweSBL().get(1).getAktywacje()) % 2 != 0;
        boolean zajetoscOdcinkaDolnego = (torDolny.getCzujnikiNajazdoweSBL().get(1).getAktywacje() + torDolny.getCzujnikiZjazdoweSBL().get(1).getAktywacje()) % 2 != 0;

        if(zajetoscOdcinkaGornego || zajetoscOdcinkaDolnego) {
            if (!pasLewy.getSygnalizacja().isStop() || !pasPrawy.getSygnalizacja().isStop() ) {
                pasLewy.getSygnalizacja().wyswietlSTOP();
                pasPrawy.getSygnalizacja().wyswietlSTOP();
                pasLewy.getRogatka().zamknij();
                pasPrawy.getRogatka().zamknij();
            }
        } else {
            if (!pasLewy.getRogatka().isOtwarta() || !pasPrawy.getRogatka().isOtwarta()) {
                pasLewy.getRogatka().otworz();
                pasPrawy.getRogatka().otworz();
                pasLewy.getSygnalizacja().wyswietlJEDZ();
                pasPrawy.getSygnalizacja().wyswietlJEDZ();

            }
        }
    }

    // SSP - Samoczynna Sygnalizacja Przejazdowa (info dla maszynisty czy rogatki działają)
    public void sterowanieSSP() {
        boolean zajetoscOdcinkaGornego = torGorny.getCzujnikSSP1().getAktywacje() != torGorny.getCzujnikSSP2().getAktywacje();
        boolean zajetoscOdcinkaDolnego = torDolny.getCzujnikSSP1().getAktywacje() != torDolny.getCzujnikSSP2().getAktywacje();

        if (zajetoscOdcinkaGornego || zajetoscOdcinkaDolnego) {
            if (!pasPrawy.getSygnalizacja().isStop() || !pasLewy.getSygnalizacja().isStop()) {
                if (!torGorny.getSemaforSSP().isStop() || !torDolny.getSemaforSSP().isStop()) {
                    torGorny.getSemaforSSP().wyswietlSTOP();
                    torDolny.getSemaforSSP().wyswietlSTOP();
                }
            } else if (torGorny.getSemaforSSP().isStop() || torDolny.getSemaforSSP().isStop()) {
                torGorny.getSemaforSSP().wyswietlJEDZ();
                torDolny.getSemaforSSP().wyswietlJEDZ();
            }
        } else if (torGorny.getSemaforSSP().isStop() || torDolny.getSemaforSSP().isStop()) {
            torGorny.getSemaforSSP().wyswietlJEDZ();
            torDolny.getSemaforSSP().wyswietlJEDZ();
        }
    }

    public void sterowanieRuchem() {
        if (!torGorny.getSemaforySBL().get(0).isStop() && rozkladGorny.ilePociagow() != 0) {
            Pociag najblizszyPrzed = rozkladGorny.najblizszyPociag();
            double czasDojazdu = 2500 / najblizszyPrzed.getMaxPredkosc();
            if (najblizszyPrzed.getCzasPrzyjazdu() - czasDojazdu < czas) {
                najblizszyPrzed.start();
                najblizszyPrzed.setSpoznienie((czasDojazdu+najblizszyPrzed.getCzasPrzyjazdu() - czas));
                pociagiObecne.dodaj(najblizszyPrzed);
                rozkladGorny.usunPierwszy();
            }
        }

        if (!torDolny.getSemaforySBL().get(0).isStop() && rozkladDolny.ilePociagow() != 0) {
            Pociag najblizszyPrzed = rozkladDolny.najblizszyPociag();
            double czasDojazdu = 2500 / najblizszyPrzed.getMaxPredkosc();
            if (najblizszyPrzed.getCzasPrzyjazdu() - czasDojazdu < czas) {
                najblizszyPrzed.start();
                pociagiObecne.dodaj(najblizszyPrzed);
                rozkladDolny.usunPierwszy();
            }
        }

        if (pociagiObecne.ilePociagow() != 0) {
            Pociag najblizszyZa = pociagiObecne.najblizszyPociag();
            if (Math.abs(najblizszyZa.getPolozenie().getX()) > 3200) {
                pociagiObecne.usunPierwszy();
                najblizszyZa.interrupt();
            }
        }
    }

    public void sterowanieSemaforami() {
        for (int i=0; i<torGorny.getIloscSemaforow(); i++) {
            if (torGorny.getCzujnikiNajazdoweSBL().get(i).isAktywowany()) {
                torGorny.getCzujnikiNajazdoweSBL().get(i).setAktywowany(false);
                torGorny.getSemaforySBL().get(i).wyswietlSTOP();
            }
            if (torGorny.getCzujnikiZjazdoweSBL().get(i).isAktywowany()) {
                torGorny.getCzujnikiZjazdoweSBL().get(i).setAktywowany(false);
                torGorny.getSemaforySBL().get(i).wyswietlJEDZ();
            }
        }
        for (int i=0; i<torDolny.getIloscSemaforow(); i++) {
            if (torDolny.getCzujnikiNajazdoweSBL().get(i).isAktywowany()) {
                torDolny.getCzujnikiNajazdoweSBL().get(i).setAktywowany(false);
                torDolny.getSemaforySBL().get(i).wyswietlSTOP();
            }
            if (torDolny.getCzujnikiZjazdoweSBL().get(i).isAktywowany()) {
                torDolny.getCzujnikiZjazdoweSBL().get(i).setAktywowany(false);
                torDolny.getSemaforySBL().get(i).wyswietlJEDZ();
            }
        }
    }

    @Override
    public void run() {
        System.out.println(this + "\tROZPOCZYNAM DYŻUR!");
        double deltaT = 200.0/1000;
        while (true) {

            sterowanieRuchem();
            sterowanieSemaforami();
//            sterowanieAutomatyczne();
            sterowanieSSP();

//            System.out.println(this);
            czas = czas + deltaT;
            try { sleep((long) (deltaT*1000)); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
