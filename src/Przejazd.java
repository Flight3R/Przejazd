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

    public Tor getTorGorny() {
        return torGorny;
    }

    public Tor getTorDolny() {
        return torDolny;
    }

    public Rozklad getRozkladDolny() {
        return rozkladDolny;
    }

    public Rozklad getRozkladGorny() {
        return rozkladGorny;
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
        boolean zajetoscOdcinkaGornego = (torGorny.getCzujnikPrzed().getAktywacje() + torGorny.getCzujnikZa().getAktywacje()) %2 != 0;
        boolean zajetoscOdcinkaDolnego = (torDolny.getCzujnikPrzed().getAktywacje() + torDolny.getCzujnikZa().getAktywacje()) %2 != 0;

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
    public void kontrolaSSP() {
        boolean zajetoscOdcinkaGornego = (torGorny.getCzujnikPrzed().getAktywacje() + torGorny.getCzujnikZa().getAktywacje()) % 2 != 0;
        boolean zajetoscOdcinkaDolnego = (torDolny.getCzujnikPrzed().getAktywacje() + torDolny.getCzujnikZa().getAktywacje()) % 2 != 0;

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

    public void obslugaRozkladu() {

    }

    @Override
    public void run() {
        System.out.println(this + "\tROZPOCZYNAM SŁUŻBĘ!");
        double deltaT = 200.0/1000;
        while (true) {
            sterowanieAutomatyczne();
            kontrolaSSP();
            obslugaRozkladu();

            czas = czas + deltaT;
            try { sleep((long) (deltaT*1000)); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
