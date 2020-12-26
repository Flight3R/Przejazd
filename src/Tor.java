public class Tor extends Droga {

    private final Czujnik czujnikSSP1;
    private final Czujnik czujnikSSP2;
    private final Czujnik czujnikSBL1;
    private final Czujnik czujnikSBL2;
    private final Czujnik czujnikSBL3;
    private final Czujnik czujnikSBL4;
    private final Swiatlo semaforSBL1;
    private final Swiatlo semaforSBL2;
    private final Swiatlo semaforSBL3;

    public Tor(Polozenie polozenie, String nazwa, Swiatlo swiatlo, String zwrot, double dlugosc, Czujnik czujnikSSP1, Czujnik czujnikSSP2,
               Czujnik czujnikSBL1, Czujnik czujnikSBL2, Czujnik czujnikSBL3, Czujnik czujnikSBL4, Swiatlo semaforSBL1, Swiatlo semaforSBL2,
               Swiatlo semaforSBL3) {
        super(polozenie, nazwa, swiatlo, zwrot, dlugosc);
        this.czujnikSSP1 = czujnikSSP1;
        this.czujnikSSP2 = czujnikSSP2;
        this.czujnikSBL1 = czujnikSBL1;
        this.czujnikSBL2 = czujnikSBL2;
        this.czujnikSBL3 = czujnikSBL3;
        this.czujnikSBL4 = czujnikSBL4;
        this.semaforSBL1 = semaforSBL1;
        this.semaforSBL2 = semaforSBL2;
        this.semaforSBL3 = semaforSBL3;
    }

    public Czujnik getCzujnikSSP1() { return czujnikSSP1; }

    public Czujnik getCzujnikSSP2() { return czujnikSSP2; }

    public Czujnik getCzujnikSBL1() { return czujnikSBL1; }

    public Czujnik getCzujnikSBL2() { return czujnikSBL2; }

    public Czujnik getCzujnikSBL3() { return czujnikSBL3; }

    public Czujnik getCzujnikSBL4() { return czujnikSBL4; }

    public Swiatlo getSemaforSBL1() { return semaforSBL1; }

    public Swiatlo getSemaforSBL2() { return semaforSBL2; }

    public Swiatlo getSemaforSBL3() { return semaforSBL3; }
}
