public class Swiatlo extends ElementInfrastruktury {
    private boolean stop = false;

    public Swiatlo(Polozenie polozenie, String nazwa) {
        super(polozenie, nazwa);
        start();
    }

    public boolean isStop() { return stop; }

    public void setStop(boolean stop) { this.stop = stop; }

    public void podajSTOP() {
        System.out.println("Światło: " + nazwa + " świeci STOP!");
        stop = true;
    }

    public void podajJEDZ() {
        System.out.println("Światło: " + nazwa + " świeci JEDŹ!");
        setStop(false);
    }
}
