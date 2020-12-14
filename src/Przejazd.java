import static java.lang.Thread.sleep;

public class Przejazd {
    private String nazwa;
    private Integer iloscTorow;
    private Integer iloscUlic;
    private Rogatka rogatkaGorna;
    private Rogatka rogatkaDolna;
    private Integer czas;
    private Rozklad rozklad;
    private Swiatlo swiatloGorne;
    private Swiatlo swiatlDolne;

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

}
