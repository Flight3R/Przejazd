import static java.lang.Thread.sleep;

public class Przejazd extends Thread {
    private String nazwa;
    private Integer iloscTorow;
    private Integer iloscUlic;
    private Rogatka rogatkaGorna;
    private Rogatka rogatkaDolna;
    private Integer czas;
    private Rozklad rozklad;
    //private Swiatlo swiatloGorne;
    //private Swiatlo swiatloDolne;
    private Tor torGorny;
    private Tor torDolny;
    private Polozenie polozenie;
    private Ulica ulica;

    public Polozenie getPolozenie() { return polozenie; }

    public void sprawdz() throws InterruptedException {
        if (czas + 30 < rozklad.najblizszyPociag().getCzasPrzyjazdu()){
            ulica.getSwiatloGorne().zapal();
            ulica.getSwiatloDolne().zapal();
            sleep(5000);
            rogatkaGorna.zamknij();
            rogatkaDolna.zamknij();

//powinno być: gdy przejedzie (bo mogą z dwóch stron naraz)
            sleep((czas - rozklad.najblizszyPociag().getCzasPrzyjazdu())*1000 + 2000);
            rogatkaGorna.otworz();
            rogatkaDolna.otworz();
            sleep(2000);
            ulica.getSwiatloGorne().zgas();
            ulica.getSwiatloDolne().zgas();
        }
    }
    public void sterowanieAutomatyczne() {
        boolean zajetoscOdcinkaGornego = (torGorny.getCzujnik_przed().getAktywacje() + torGorny.getCzujnik_za().getAktywacje()) %2 != 0;
        boolean zajetoscOdcinkaDolnego = (torDolny.getCzujnik_przed().getAktywacje() + torDolny.getCzujnik_za().getAktywacje()) %2 != 0;

        if(zajetoscOdcinkaGornego || zajetoscOdcinkaDolnego) {
            if (rogatkaGorna.isOtwarta() || rogatkaDolna.isOtwarta()) {
                ulica.getSwiatloGorne().zapal();
                ulica.getSwiatloDolne().zapal();
                try { sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
                rogatkaGorna.zamknij();
                rogatkaDolna.zamknij();
            }
        } else {
            if (!rogatkaGorna.isOtwarta() || !rogatkaDolna.isOtwarta()) {
                rogatkaGorna.otworz();
                rogatkaDolna.otworz();
                try { sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
                ulica.getSwiatloGorne().zgas();
                ulica.getSwiatloDolne().zgas();
            }
        }
    }

    // SSP - Samoczynna Sygnalizacja Przejazdowa (info dla maszynisty czy rogatki działają)
    public void kontrolaSSP() {
        boolean zajetoscOdcinkaGornego = (torGorny.getCzujnik_przed().getAktywacje() + torGorny.getCzujnik_za().getAktywacje()) %2 != 0;
        boolean zajetoscOdcinkaDolnego = (torDolny.getCzujnik_przed().getAktywacje() + torDolny.getCzujnik_za().getAktywacje()) %2 != 0;

        if(zajetoscOdcinkaGornego || zajetoscOdcinkaDolnego) {
            if (rogatkaGorna.isOtwarta() || rogatkaDolna.isOtwarta()) {
                torGorny.getSSP().zapal();
                torDolny.getSSP().zapal();
            }
        } else {
            if (torGorny.getSSP().isZapalone() || torDolny.getSSP().isZapalone()) {
                torGorny.getSSP().zgas();
                torDolny.getSSP().zgas();
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
