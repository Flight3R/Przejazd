public abstract class Pojazd extends Thread {

    private Polozenie polozenie; // POLOZENIE PRZODU POJAZDU
    private final double dlugosc;
    private double predkosc;
    private final double maxPredkosc;
    private final double opoznienie;
    private final double przyspieszenie;
    private double drogaHamowania;
    private Polozenie cel = new Polozenie(0,0);

    public Pojazd(double dlugosc, Integer masa, double maxPredkosc, Polozenie polozenie) {
        this.dlugosc = dlugosc;
        this.maxPredkosc = maxPredkosc;
        this.polozenie = polozenie;
        predkosc = maxPredkosc;
        opoznienie = -36000.0 / masa;
        przyspieszenie = 20000.0 / masa;
        drogaHamowania = Math.pow(maxPredkosc, 2) / (2 * -opoznienie);
    }

    public double getDlugosc() { return dlugosc; }
    public double getMaxPredkosc() { return maxPredkosc; }
    public Polozenie getPolozenie() { return polozenie; }
    public Polozenie getCel() { return cel; }

    public void copyCel(Polozenie cel) { this.cel.setX(cel.getX()); this.cel.setY(cel.getY()); }

    public double getPredkosc() { return predkosc; }
    public double getDrogaHamowania() { return drogaHamowania; }

    public void setPredkosc(double predkosc) {
        this.predkosc = predkosc;
    }

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
