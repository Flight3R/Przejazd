import static java.lang.Thread.sleep;

public class Przejazd extends ElementInfrastruktury {
   // private Ulica ulica;
    private Droga pasLewy;
    private Droga pasPrawy;
    private Tor torGorny;
    private Tor torDolny;

    private Rozklad rozklad;
    private Integer czas;

    public void sterowanieAutomatyczne() {
        boolean zajetoscOdcinkaGornego = (torGorny.getCzujnikPrzed().getAktywacje() + torGorny.getCzujnikZa().getAktywacje()) %2 != 0;
        boolean zajetoscOdcinkaDolnego = (torDolny.getCzujnikPrzed().getAktywacje() + torDolny.getCzujnikZa().getAktywacje()) %2 != 0;

        if(zajetoscOdcinkaGornego || zajetoscOdcinkaDolnego) {
            if (pasPrawy.getRogatka().isOtwarta() || pasLewy.getRogatka().isOtwarta()) {
                pasPrawy.getSwiatlo().zapal();
                pasLewy.getSwiatlo().zapal();
                try { sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
                pasPrawy.getRogatka().zamknij();
                pasLewy.getRogatka().zamknij();
            }
        } else {
            if (!pasPrawy.getRogatka().isOtwarta() || !pasLewy.getRogatka().isOtwarta()) {
                pasPrawy.getRogatka().otworz();
                pasLewy.getRogatka().otworz();
                try { sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
                pasPrawy.getSwiatlo().zgas();
                pasLewy.getSwiatlo().zgas();
            }
        }
    }

    // SSP - Samoczynna Sygnalizacja Przejazdowa (info dla maszynisty czy rogatki działają)
    public void kontrolaSSP() {
        boolean zajetoscOdcinkaGornego = (torGorny.getCzujnikPrzed().getAktywacje() + torGorny.getCzujnikZa().getAktywacje()) %2 != 0;
        boolean zajetoscOdcinkaDolnego = (torDolny.getCzujnikPrzed().getAktywacje() + torDolny.getCzujnikZa().getAktywacje()) %2 != 0;

        if(zajetoscOdcinkaGornego || zajetoscOdcinkaDolnego) {
            if (pasPrawy.getRogatka().isOtwarta() || pasLewy.getRogatka().isOtwarta()) {
                torGorny.getSwiatlo().zapal();
                torDolny.getSwiatlo().zapal();
            }
        } else {
            if (torGorny.getSwiatlo().isZapalone() || torDolny.getSwiatlo().isZapalone()) {
                torGorny.getSwiatlo().zgas();
                torDolny.getSwiatlo().zgas();
            }
        }

    }

    @Override
    public void run() {
        super.run();

        sterowanieAutomatyczne();

        try {
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
