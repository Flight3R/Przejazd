public class Przejazd extends ElementInfrastruktury {

    private final PasRuchu pasLewy;
    private final PasRuchu pasPrawy;
    private final Tor torGorny;
    private final Tor torDolny;

    private final Rozklad rozkladGorny;
    private final Rozklad rozkladDolny;
    private final Rozklad lista = new Rozklad();

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
        boolean zajetoscOdcinkaGornego = (torGorny.getCzujnikSSP1().getAktywacje() + torGorny.getCzujnikSSP2().getAktywacje()) %2 != 0;
        boolean zajetoscOdcinkaDolnego = (torDolny.getCzujnikSSP1().getAktywacje() + torDolny.getCzujnikSSP2().getAktywacje()) %2 != 0;

        if(zajetoscOdcinkaGornego || zajetoscOdcinkaDolnego) {
            if (!pasLewy.getSwiatlo().isStop() || !pasPrawy.getSwiatlo().isStop() ) {
                pasLewy.getSwiatlo().wyswietlSTOP();
                pasPrawy.getSwiatlo().wyswietlSTOP();
                pasLewy.getRogatka().zamknij();
                pasPrawy.getRogatka().zamknij();
            }
        } else {
            if (!pasLewy.getRogatka().isOtwarta() || !pasPrawy.getRogatka().isOtwarta()) {
                pasLewy.getRogatka().otworz();
                pasPrawy.getRogatka().otworz();
                pasLewy.getSwiatlo().wyswietlJEDZ();
                pasPrawy.getSwiatlo().wyswietlJEDZ();

            }
        }
    }

    // SSP - Samoczynna Sygnalizacja Przejazdowa (info dla maszynisty czy rogatki działają)
    public void sterowanieSSP() {
        boolean zajetoscOdcinkaGornego = (torGorny.getCzujnikSSP1().getAktywacje() + torGorny.getCzujnikSSP2().getAktywacje()) % 2 != 0;
        boolean zajetoscOdcinkaDolnego = (torDolny.getCzujnikSSP1().getAktywacje() + torDolny.getCzujnikSSP2().getAktywacje()) % 2 != 0;

        if (zajetoscOdcinkaGornego || zajetoscOdcinkaDolnego) {
            if (!pasPrawy.getSwiatlo().isStop() || !pasLewy.getSwiatlo().isStop()) {
                if (!torGorny.getSwiatlo().isStop() || !torDolny.getSwiatlo().isStop()) {
                    torGorny.getSwiatlo().wyswietlSTOP();
                    torDolny.getSwiatlo().wyswietlSTOP();
                }
            } else if (torGorny.getSwiatlo().isStop() || torDolny.getSwiatlo().isStop()) {
                torGorny.getSwiatlo().wyswietlJEDZ();
                torDolny.getSwiatlo().wyswietlJEDZ();
            }
        } else if (torGorny.getSwiatlo().isStop() || torDolny.getSwiatlo().isStop()) {
            torGorny.getSwiatlo().wyswietlJEDZ();
            torDolny.getSwiatlo().wyswietlJEDZ();
        }
    }

    public void sterowanieSBL() {
        boolean zajetoscOdc1ToruGornego = (torGorny.getCzujnikSBL1().getAktywacje() + torGorny.getCzujnikSBL2().getAktywacje()) %2 != 0;
        boolean zajetoscOdc2ToruGornego = (torGorny.getCzujnikSBL2().getAktywacje() + torGorny.getCzujnikSBL3().getAktywacje()) %2 != 0;
        boolean zajetoscOdc3ToruGornego = (torGorny.getCzujnikSBL3().getAktywacje() + torGorny.getCzujnikSBL4().getAktywacje()) %2 != 0;
        boolean zajetoscOdc1ToruDolnego = (torDolny.getCzujnikSBL1().getAktywacje() + torDolny.getCzujnikSBL2().getAktywacje()) %2 != 0;
        boolean zajetoscOdc2ToruDolnego = (torDolny.getCzujnikSBL2().getAktywacje() + torDolny.getCzujnikSBL3().getAktywacje()) %2 != 0;
        boolean zajetoscOdc3ToruDolnego = (torDolny.getCzujnikSBL3().getAktywacje() + torDolny.getCzujnikSBL4().getAktywacje()) %2 != 0;

        if (zajetoscOdc1ToruGornego) {
            if (!torGorny.getSemaforSBL1().isStop())
                torGorny.getSemaforSBL1().wyswietlSTOP();
        } else if (torGorny.getSemaforSBL1().isStop())
            torGorny.getSemaforSBL1().wyswietlJEDZ();

        if (zajetoscOdc2ToruGornego) {
            if (!torGorny.getSemaforSBL2().isStop())
                torGorny.getSemaforSBL2().wyswietlSTOP();
        } else if (torGorny.getSemaforSBL2().isStop())
            torGorny.getSemaforSBL2().wyswietlJEDZ();

        if (zajetoscOdc3ToruGornego) {
            if (!torGorny.getSemaforSBL3().isStop())
                torGorny.getSemaforSBL3().wyswietlSTOP();
        } else if (torGorny.getSemaforSBL3().isStop())
            torGorny.getSemaforSBL3().wyswietlJEDZ();

        //tor dolny
        if (zajetoscOdc1ToruDolnego) {
            if (!torDolny.getSemaforSBL1().isStop())
                torDolny.getSemaforSBL1().wyswietlSTOP();
        } else if (torDolny.getSemaforSBL1().isStop())
            torDolny.getSemaforSBL1().wyswietlJEDZ();

        if (zajetoscOdc2ToruDolnego) {
            if (!torDolny.getSemaforSBL2().isStop())
                torDolny.getSemaforSBL2().wyswietlSTOP();
        } else if (torDolny.getSemaforSBL2().isStop())
            torDolny.getSemaforSBL2().wyswietlJEDZ();

        if (zajetoscOdc3ToruDolnego) {
            if (!torDolny.getSemaforSBL3().isStop())
                torDolny.getSemaforSBL3().wyswietlSTOP();
        } else if (torDolny.getSemaforSBL3().isStop())
            torDolny.getSemaforSBL3().wyswietlJEDZ();
    }

    public void sterowanieRuchem() {
        if (!torGorny.getSemaforSBL1().isStop() && rozkladGorny.ilePociagow() != 0) {
            Pociag najblizszyPrzed = rozkladGorny.najblizszyPociag();
            double czasDojazdu = 2500 / najblizszyPrzed.getMaxPredkosc();
            if (najblizszyPrzed.getCzasPrzyjazdu() - czasDojazdu < czas) {
                najblizszyPrzed.start();
                najblizszyPrzed.setOpoznienie((int) (czasDojazdu+najblizszyPrzed.getCzasPrzyjazdu() - czas));
                lista.dodaj(najblizszyPrzed);
                rozkladGorny.usunPierwszy();
            }
        }

        if (!torDolny.getSemaforSBL1().isStop() && rozkladDolny.ilePociagow() != 0) {
            Pociag najblizszyPrzed = rozkladDolny.najblizszyPociag();
            double czasDojazdu = 2500 / najblizszyPrzed.getMaxPredkosc();
            if (najblizszyPrzed.getCzasPrzyjazdu() - czasDojazdu < czas) {
                najblizszyPrzed.start();
                lista.dodaj(najblizszyPrzed);
                rozkladDolny.usunPierwszy();
            }
        }

        if (lista.ilePociagow() != 0) {
            Pociag najblizszyZa = lista.najblizszyPociag();
            if (Math.abs(najblizszyZa.getPolozenie().getX()) > 2500) {
                lista.usunPierwszy();
                najblizszyZa.interrupt();
            }
        }
    }

    @Override
    public void run() {
        System.out.println(this + "\tROZPOCZYNAM SŁUŻBĘ!");
        double deltaT = 200.0/1000;
        while (true) {

            sterowanieSBL();
            sterowanieRuchem();
            sterowanieAutomatyczne();
            sterowanieSSP();

//            System.out.println(this);
            czas = czas + deltaT;
            try { sleep((long) (deltaT*1000)); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
