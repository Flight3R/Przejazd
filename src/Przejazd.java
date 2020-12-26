public class Przejazd extends ElementInfrastruktury {

    private final PasRuchu pasLewy;
    private final PasRuchu pasPrawy;
    private final Tor torGorny;
    private final Tor torDolny;

    private Rozklad rozkladGorny;
    private Rozklad rozkladDolny;
    private Rozklad lista = new Rozklad();
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
        return "Przejazd: " + nazwa + "\tX= " + getPolozenie().getX() + "\tY= " + getPolozenie().getY();
    }

    public PasRuchu getPasLewy() {
        return pasLewy;
    }

    public PasRuchu getPasPrawy() {
        return pasPrawy;
    }

    public Tor getTorGorny() {
        return torGorny;
    }

    public Tor getTorDolny() {
        return torDolny;
    }

    public Rozklad getRozkladGorny() {
        return rozkladGorny;
    }

    public Rozklad getRozkladDolny() {
        return rozkladDolny;
    }

    public Rozklad getLista() {
        return lista;
    }

    public double getCzas() {
        return czas;
    }

    public boolean isRogatkiOtwarte() {
        return pasLewy.getRogatka().isOtwarta() || pasPrawy.getRogatka().isOtwarta();
    }

    public void sterowanieAutomatyczne() {
        boolean zajetoscOdcinkaGornego = (torGorny.getCzujnikSSP1().getAktywacje() + torGorny.getCzujnikSSP2().getAktywacje()) %2 != 0;
        boolean zajetoscOdcinkaDolnego = (torDolny.getCzujnikSSP1().getAktywacje() + torDolny.getCzujnikSSP2().getAktywacje()) %2 != 0;

        if(zajetoscOdcinkaGornego || zajetoscOdcinkaDolnego) {
            if (pasLewy.getRogatka().isOtwarta() || pasPrawy.getRogatka().isOtwarta() ) {
                pasLewy.getSwiatlo().zapal();
                pasPrawy.getSwiatlo().zapal();
                System.out.println("zapalam lampy!");
                try { sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
                pasLewy.getRogatka().zamknij();
                pasPrawy.getRogatka().zamknij();
                System.out.println("zamykam rogatki!");
            }
        } else {
            if (!pasLewy.getRogatka().isOtwarta() || !pasPrawy.getRogatka().isOtwarta()) {
                pasLewy.getRogatka().otworz();
                pasPrawy.getRogatka().otworz();
                System.out.println("otwieram rogatki!");
                try { sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
                pasLewy.getSwiatlo().zgas();
                pasPrawy.getSwiatlo().zgas();
                System.out.println("gasze lampy!");

            }
        }
//        System.out.println("sterowanie auto!");
    }

    // SSP - Samoczynna Sygnalizacja Przejazdowa (info dla maszynisty czy rogatki działają)
    public void sterowanieSSP() {
//        >>>>>>>>>>>>>>>>>>> DO REFAKTORYZACJI !!! >>>>>>>>>>>>>>>>
        boolean zajetoscOdcinkaGornego = (torGorny.getCzujnikSSP1().getAktywacje() + torGorny.getCzujnikSSP2().getAktywacje()) % 2 != 0;
        boolean zajetoscOdcinkaDolnego = (torDolny.getCzujnikSSP1().getAktywacje() + torDolny.getCzujnikSSP2().getAktywacje()) % 2 != 0;

        if (zajetoscOdcinkaGornego || zajetoscOdcinkaDolnego) {
            if (pasPrawy.getRogatka().isOtwarta() || pasLewy.getRogatka().isOtwarta()) {
                if (!torGorny.getSwiatlo().isZapalone() || !torDolny.getSwiatlo().isZapalone()) {
                    torGorny.getSwiatlo().zapal();
                    torDolny.getSwiatlo().zapal();
                    System.out.println("zapalam SSP!");
                }
            } else if (torGorny.getSwiatlo().isZapalone() || torDolny.getSwiatlo().isZapalone()) {
                torGorny.getSwiatlo().zgas();
                torDolny.getSwiatlo().zgas();
                System.out.println("gasze SSP!");
            }
        } else if (torGorny.getSwiatlo().isZapalone() || torDolny.getSwiatlo().isZapalone()) {
            torGorny.getSwiatlo().zgas();
            torDolny.getSwiatlo().zgas();
            System.out.println("gasze SSP!");
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
            if (!torGorny.getSemaforSBL1().isZapalone())
                torGorny.getSemaforSBL1().zapal();
        } else if (torGorny.getSemaforSBL1().isZapalone())
            torGorny.getSemaforSBL1().zgas();

        if (zajetoscOdc2ToruGornego) {
            if (!torGorny.getSemaforSBL2().isZapalone())
                torGorny.getSemaforSBL2().zapal();
        } else if (torGorny.getSemaforSBL2().isZapalone())
            torGorny.getSemaforSBL2().zgas();

        if (zajetoscOdc3ToruGornego) {
            if (!torGorny.getSemaforSBL3().isZapalone())
                torGorny.getSemaforSBL3().zapal();
        } else if (torGorny.getSemaforSBL3().isZapalone())
            torGorny.getSemaforSBL3().zgas();
//tor dolny

        if (zajetoscOdc1ToruDolnego) {
            if (!torDolny.getSemaforSBL1().isZapalone())
                torDolny.getSemaforSBL1().zapal();
        } else if (torDolny.getSemaforSBL1().isZapalone())
            torDolny.getSemaforSBL1().zgas();

        if (zajetoscOdc2ToruDolnego) {
            if (!torDolny.getSemaforSBL2().isZapalone())
                torDolny.getSemaforSBL2().zapal();
        } else if (torDolny.getSemaforSBL2().isZapalone())
            torDolny.getSemaforSBL2().zgas();

        if (zajetoscOdc3ToruDolnego) {
            if (!torDolny.getSemaforSBL3().isZapalone())
                torDolny.getSemaforSBL3().zapal();
        } else if (torDolny.getSemaforSBL3().isZapalone())
            torDolny.getSemaforSBL3().zgas();
    }

    @Override
    public void run() {
        System.out.println(this + "\tROZPOCZYNAM SŁUŻBĘ!");
        double deltaT = 200.0/1000;
        while (true) {
            sterowanieAutomatyczne();
            sterowanieSSP();
            sterowanieSBL();

            czas = czas + deltaT;
            try { sleep((long) (deltaT*1000)); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
