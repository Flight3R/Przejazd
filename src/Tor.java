public class Tor extends Droga {

    private Czujnik czujnikPrzed;
    private Czujnik czujnikZa;
    private Czujnik sblPrzed;
    private Czujnik sblZa;

    public Tor(Polozenie polozenie, String nazwa, Swiatlo swiatlo, String zwrot, double dlugosc, Czujnik czujnikPrzed, Czujnik czujnikZa, Czujnik sblPrzed, Czujnik sblZa) {
        super(polozenie, nazwa, swiatlo, zwrot, dlugosc);
        this.czujnikPrzed = czujnikPrzed;
        this.czujnikZa = czujnikZa;
        this.sblPrzed = sblPrzed;
        this.sblZa = sblZa;
    }

    public Czujnik getCzujnikPrzed() { return czujnikPrzed; }
    public Czujnik getCzujnikZa() { return czujnikZa; }
    public Czujnik getSblPrzed() { return sblPrzed; }
    public Czujnik getSblZa() { return sblZa; }
}
