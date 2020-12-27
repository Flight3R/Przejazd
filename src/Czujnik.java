import java.util.ArrayList;

public class Czujnik extends ElementInfrastruktury{
    private Integer aktywacje = 0;
    private final ArrayList<String> listaAktywacji = new ArrayList<>();

    private boolean aktywowany = false;

    public Czujnik(Polozenie polozenie, String nazwa) {
        super(polozenie, nazwa);
    }

    public Integer getAktywacje() { return aktywacje; }

    public boolean isAktywowany() { return aktywowany; }

    public void setAktywowany(boolean aktywowany) { this.aktywowany = aktywowany; }

    public void aktywuj(String nazwa){
        if (!listaAktywacji.contains(nazwa)) {
            aktywacje = aktywacje + 1;
            listaAktywacji.add(nazwa);
            aktywowany = true;
            System.out.println("Czujnik: " + this.nazwa + " włączony przez pociąg: " + nazwa);
        }
    }
}
