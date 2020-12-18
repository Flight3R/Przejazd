import java.util.ArrayList;

public class Czujnik extends ElementInfrastruktury{
    private Integer aktywacje = 0;
    private ArrayList<String> listaAktywacji;

    public void aktywuj(String nazwa){
        if (!listaAktywacji.contains(nazwa)) {
            aktywacje = aktywacje + 1;
            listaAktywacji.add(nazwa);
        }
    }

    public Integer getAktywacje() { return aktywacje; }
}