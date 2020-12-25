public class Swiatlo extends ElementInfrastruktury {
    private boolean zapalone = false;

    public Swiatlo(Polozenie polozenie, String nazwa) {
        super(polozenie, nazwa);
    }

    public boolean isZapalone() { return zapalone; }

    public void zapal(){
        System.out.println(nazwa + " zapalony!");
        zapalone = true;
    }
    public void zgas(){
        System.out.println(nazwa + " zgaszony!");
        zapalone = false;
    }
}
