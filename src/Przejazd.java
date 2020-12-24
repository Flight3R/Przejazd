import static java.lang.Thread.sleep;

public class Przejazd extends ElementInfrastruktury {

    private PasRuchu pasLewy;
    private PasRuchu pasPrawy;
    private Tor torGorny;
    private Tor torDolny;

    private Rozklad rozklad;
    private Integer czas;

    public Przejazd(Polozenie polozenie, String nazwa, PasRuchu pasLewy, PasRuchu pasPrawy, Tor torGorny, Tor torDolny, Rozklad rozklad, Integer czas) {
        super(polozenie, nazwa);
        this.pasLewy = pasLewy;
        this.pasPrawy = pasPrawy;
        this.torGorny = torGorny;
        this.torDolny = torDolny;
        this.rozklad = rozklad;
        this.czas = czas;
        start();
    }

    public boolean isRogatkiOtwarte() {
        if (pasLewy.getRogatka().isOtwarta() || pasPrawy.getRogatka().isOtwarta())
            return true;
        return false;
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
        boolean zajetoscOdcinkaGornego = (torGorny.getCzujnikPrzed().getAktywacje() + torGorny.getCzujnikZa().getAktywacje()) %2 != 0;
        boolean zajetoscOdcinkaDolnego = (torDolny.getCzujnikPrzed().getAktywacje() + torDolny.getCzujnikZa().getAktywacje()) %2 != 0;

        if(zajetoscOdcinkaGornego || zajetoscOdcinkaDolnego) {
            if (pasPrawy.getRogatka().isOtwarta() || pasLewy.getRogatka().isOtwarta()) {
                torGorny.getSwiatlo().zapal();
                torDolny.getSwiatlo().zapal();
                System.out.println("zapalam SSP!");
            }
        } else if (torGorny.getSwiatlo().isZapalone() || torDolny.getSwiatlo().isZapalone()) {
            torGorny.getSwiatlo().zgas();
            torDolny.getSwiatlo().zgas();
            System.out.println("gasze SSP!");
        }
    }


    @Override
    public void run() {
        double deltaT = 200.0/1000;
//        super.run();
        while (true) {
//            System.out.println("przejazd dziala!");
//            sterowanieAutomatyczne();
            kontrolaSSP();

            try { sleep((long) (deltaT*1000)); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
