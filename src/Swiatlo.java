public class Swiatlo extends ElementInfrastruktury {
    private boolean zapalone;

    public Swiatlo(Polozenie polozenie, String nazwa, boolean zapalone) {
        super(polozenie, nazwa);
        this.zapalone = zapalone;
    }

    public boolean isZapalone() { return zapalone; }

    public void zapal(){
        zapalone=true;
    }
    public void zgas(){
        zapalone=false;
    }
}
