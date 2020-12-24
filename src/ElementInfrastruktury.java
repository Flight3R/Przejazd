public abstract class ElementInfrastruktury extends Thread {

    protected Polozenie polozenie;
    protected String nazwa;

    public ElementInfrastruktury(Polozenie polozenie, String nazwa) {
        this.polozenie = polozenie;
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }
    public Polozenie getPolozenie() {
        return polozenie;
    }

}
