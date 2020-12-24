public class Swiatlo extends ElementInfrastruktury {
    private boolean zapalone = false;

    public Swiatlo(Polozenie polozenie, String nazwa) {
        super(polozenie, nazwa);
    }

    public boolean isZapalone() { return zapalone; }

    public void zapal(){
        zapalone = true;
    }
    public void zgas(){
        zapalone = false;
    }
}
