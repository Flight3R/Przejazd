public abstract class Pojazd extends Thread {
    protected double dlugosc;

    private double maxPredkosc;
    protected Polozenie polozenie; // POLOZENIE PRZODU POJAZDU
    protected Przejazd przejazd;

    protected Polozenie cel;
    protected double predkosc;

    protected double opoznienie;
    protected double przyspieszenie;
    protected double drogaHamowania;


    public Pojazd(double dlugosc, double masa, double maxPredkosc, Polozenie polozenie, Przejazd przejazd) {
        this.dlugosc = dlugosc;
        this.maxPredkosc = maxPredkosc;
        this.polozenie = polozenie;
        this.przejazd = przejazd;

        predkosc = maxPredkosc;

        opoznienie = -36000 / masa;
        przyspieszenie = 20000 / masa;
        drogaHamowania = Math.pow(maxPredkosc, 2) / (2 * -opoznienie);
    }

    public Polozenie getPolozenie() { return polozenie; }
    public double getPredkosc() { return predkosc; }
    public double getDlugosc() { return dlugosc; }

    public void zatrzymaj() { predkosc = 0; }
    public void jedz() { predkosc = maxPredkosc; }

    public void hamuj(double deltaT) {
        // F = a*m => a=F/m przyspieszenie odwrotnie proporcjonalne do masy F=36kN
        predkosc = predkosc + opoznienie * deltaT;
        if (predkosc < 0)
            predkosc = 0;
    }

    public void przyspiesz(double deltaT) {
        // F = a*m => a=F/m przyspieszenie odwrotnie proporcjonalne do masy F=20kN
        predkosc = predkosc + przyspieszenie * deltaT;
        if (predkosc > maxPredkosc)
            predkosc = maxPredkosc;
    }
}
