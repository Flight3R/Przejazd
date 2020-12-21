public class Tor extends ElementInfrastruktury {
    private double dlugosc;
    private String zwrot; // prawo lub lewo (ważne do metody przenies)

    private Czujnik czujnik_przed;
    private Czujnik czujnik_za;

    public double getDlugosc() {
        return dlugosc;
    }

    public String getZwrot() {
        return zwrot;
    }

    public Czujnik getCzujnik_przed() {
        return czujnik_przed;
    }

    public Czujnik getCzujnik_za() {
        return czujnik_za;
    }
}
