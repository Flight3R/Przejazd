public class Tor extends Droga {

    private Czujnik czujnikPrzed;
    private Czujnik czujnikZa;

    public Tor(Polozenie polozenie, Swiatlo swiatlo, String zwrot, double dlugosc, Czujnik czujnikPrzed, Czujnik czujnikZa) {
        super(polozenie, swiatlo, zwrot, dlugosc);
        this.czujnikPrzed = czujnikPrzed;
        this.czujnikZa = czujnikZa;
    }

    public Czujnik getCzujnikPrzed() { return czujnikPrzed; }
    public Czujnik getCzujnikZa() { return czujnikZa; }

}
