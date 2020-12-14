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

    public void sprawdz(){
        if (czas + 30 < "czas przyjazdu najblizszego"){
            swiatloGorne.zapal();
            swiatlDolne.zapal();
            sleep(5000);
            rogatkaGorna.zamknij();
            rogatkaDolna.zamknij();

            sleep((czas - czasprzyjazdu)*1000 + 2000);
            rogatkaGorna.otworz();
            rogatkaDolna.otworz();
            sleep(2000);
            swiatloGorne.zgas();
            swiatlDolne.zgas();
        }
    }

}
