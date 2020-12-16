public class Swiatlo extends ElementInfrastruktury {
    private boolean zapalone;

    public boolean isZapalone() { return zapalone; }

    public void zapal(){
        zapalone=true;
    }
    public void zgas(){
        zapalone=false;
    }
}
