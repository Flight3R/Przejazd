import static java.lang.Thread.sleep;

public class Przejazd extends Thread {
    private String nazwa;
    private Integer iloscTorow;
    private Integer iloscUlic;
    private Rogatka rogatkaGorna;
    private Rogatka rogatkaDolna;
    private Integer czas;
    private Rozklad rozklad;
    private Swiatlo swiatloGorne;
    private Swiatlo swiatlDolne;
    private Tor torGorny;
    private Tor torDolny;
    private Polozenie polozenie;

    public Polozenie getPolozenie() { return polozenie; }

    public void sprawdz() throws InterruptedException {
        if (czas + 30 < rozklad.najblizszyPociag().getCzasPrzyjazdu()){
            swiatloGorne.zapal();
            swiatlDolne.zapal();
            sleep(5000);
            rogatkaGorna.zamknij();
            rogatkaDolna.zamknij();



//powinno być: gdy przejedzie (bo mogą z dwóch stron naraz)
            sleep((czas - rozklad.najblizszyPociag().getCzasPrzyjazdu())*1000 + 2000);
            rogatkaGorna.otworz();
            rogatkaDolna.otworz();
            sleep(2000);
            swiatloGorne.zgas();
            swiatlDolne.zgas();
        }
    }
    public void sprawdzCzujniki() {
        boolean stanCzujnikowStabilny = (torDolny.getCzujnik_przed().getZadzialania() + torDolny.getCzujnik_za().getZadzialania()) %2 == 0;
        // jak tak to znaczy ze pociagu nie ma między czujnikami
        if(stanCzujnikowStabilny) {
            if (!rogatkaGorna.isOtwarta() && !rogatkaDolna.isOtwarta()) {
                rogatkaGorna.otworz();
                rogatkaDolna.otworz();
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                swiatloGorne.zgas();
                swiatlDolne.zgas();
            }
        } else {
            if (rogatkaGorna.isOtwarta() || rogatkaDolna.isOtwarta()) {
                swiatloGorne.zapal();
                swiatlDolne.zapal();
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                rogatkaGorna.zamknij();
                rogatkaDolna.zamknij();
            }
        }
    }


    @Override
    public void run() {
        super.run();

        sprawdzCzujniki();

        try {
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
