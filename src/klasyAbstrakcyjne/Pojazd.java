package klasyAbstrakcyjne;
import lokacja.Polozenie;

public abstract class Pojazd extends Thread {
    private final String nazwa;
    private final Polozenie polozenie; // POLOZENIE PRZODU POJAZDU
    private final double dlugosc;
    private double predkosc;
    private final double maxPredkosc;
    private final double opoznienie;
    private final double przyspieszenie;
    private double drogaHamowania;
    private double odstep;
    private Polozenie cel = new Polozenie(0,0);

    public Pojazd(String typ, String zwrot, String nazwa, double dlugosc, Integer masa, double maxPredkosc, Polozenie polozenie) {
        this.nazwa = nazwa;
        this.dlugosc = dlugosc;
        this.maxPredkosc = maxPredkosc;
        this.polozenie = polozenie;
        predkosc = maxPredkosc;

        if (typ.equals("Pociag")) {
            opoznienie = -36000.0 / masa;
            przyspieszenie = 20000.0 / masa;
            if (zwrot.equals("prawo"))
                odstep = 10;
            else
                odstep = -10;
        } else {
            opoznienie = -5000.0 / masa;
            przyspieszenie = 5000.0 / masa;
            if (zwrot.equals("gora"))
                odstep = 1;
            else
                odstep = -1;
        }

        drogaHamowania = Math.pow(maxPredkosc, 2) / (2 * -opoznienie);
    }

    public String getNazwa() { return nazwa; }

    public Polozenie getPolozenie() { return polozenie; }

    public double getDlugosc() { return dlugosc; }

    public double getPredkosc() { return predkosc; }

    public double getMaxPredkosc() { return maxPredkosc; }

    public double getOpoznienie() { return opoznienie; }

    public double getPrzyspieszenie() { return przyspieszenie; }

    public double getDrogaHamowania() { return drogaHamowania; }

    public double getOdstep() { return odstep; }

    public Polozenie getCel() { return cel; }

    public void setPredkosc(double predkosc) { this.predkosc = predkosc; }

    public void hamuj(double deltaT) {
        // F = a*m => a=F/m przyspieszenie odwrotnie proporcjonalne do masy F=36kN
        predkosc = predkosc + opoznienie * deltaT;
        if (predkosc < 0)
            predkosc = 0;

        drogaHamowania = Math.pow(predkosc, 2) / (2 * -opoznienie);
    }

    public void przyspiesz(double deltaT) {
        // F = a*m => a=F/m przyspieszenie odwrotnie proporcjonalne do masy F=20kN
        predkosc = predkosc + przyspieszenie * deltaT;
        if (predkosc > maxPredkosc)
            predkosc = maxPredkosc;

        drogaHamowania = Math.pow(predkosc, 2) / (2 * -opoznienie);
    }
}
