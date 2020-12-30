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
        start();
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

    public Rozklad getPociagiObecne() { return pociagiObecne; }

    public double getCzas() { return czas; }

    public boolean isRogatkiOtwarte() {
        return pasLewy.getRogatka().isOtwarta() || pasPrawy.getRogatka().isOtwarta();
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
        boolean zajetoscOdcinkaGornego = torGorny.getCzujnikNajazdowySSP().getAktywacje() != torGorny.getCzujnikZjazdowySSP().getAktywacje();
        boolean zajetoscOdcinkaDolnego = torDolny.getCzujnikNajazdowySSP().getAktywacje() != torDolny.getCzujnikZjazdowySSP().getAktywacje();

        if(zajetoscOdcinkaGornego || zajetoscOdcinkaDolnego) {
            if (!pasLewy.getSygnalizacja().isStop() || !pasPrawy.getSygnalizacja().isStop() ) {
                pasLewy.getSygnalizacja().podajSTOP();
                pasPrawy.getSygnalizacja().podajSTOP();
                pasLewy.getRogatka().zamknij();
                pasPrawy.getRogatka().zamknij();
            }
        } else {
            if (!pasLewy.getRogatka().isOtwarta() || !pasPrawy.getRogatka().isOtwarta()) {
                pasLewy.getRogatka().otworz();
                pasPrawy.getRogatka().otworz();
                pasLewy.getSygnalizacja().podajJEDZ();
                pasPrawy.getSygnalizacja().podajJEDZ();

            }
        }
    }

    public void sterowanieSSP() {
        boolean zajetoscOdcinkaGornego = torGorny.getCzujnikNajazdowySSP().getAktywacje() != torGorny.getCzujnikZjazdowySSP().getAktywacje();
        boolean zajetoscOdcinkaDolnego = torDolny.getCzujnikNajazdowySSP().getAktywacje() != torDolny.getCzujnikZjazdowySSP().getAktywacje();

        if (zajetoscOdcinkaGornego || zajetoscOdcinkaDolnego) {
            if (!pasPrawy.getSygnalizacja().isStop() || !pasLewy.getSygnalizacja().isStop()) {
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
