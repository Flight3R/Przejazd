public class Tor extends ElementInfrastruktury {
    private double dlugosc;
    private String kierunek; // prawo lub lewo

    private Czujnik czujnik_przed;
    private Czujnik czujnik_za;

    public double getDlugosc() {
        return dlugosc;
    }

    public String getKierunek() {
        return kierunek;
    }

    public Czujnik getCzujnik_przed() {
        return czujnik_przed;
    }

    public Czujnik getCzujnik_za() {
        return czujnik_za;
    }
}
