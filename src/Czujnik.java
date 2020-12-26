import java.util.ArrayList;

public class Czujnik extends ElementInfrastruktury{
    private Integer aktywacje = 0;
    private ArrayList<String> listaAktywacji = new ArrayList<>();

    public Czujnik(Polozenie polozenie, String nazwa) {
        super(polozenie, nazwa);
    }

    public Integer getAktywacje() { return aktywacje; }

    public void aktywuj(String nazwa){
        if (!listaAktywacji.contains(nazwa)) {
            aktywacje = aktywacje + 1;
            listaAktywacji.add(nazwa);
            System.out.println("Czujnik: " + this.nazwa + " włączony przez pociąg: " + nazwa);
        }
    }
}
