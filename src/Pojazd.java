public abstract class Pojazd extends Thread {

    private Polozenie polozenie; // POLOZENIE PRZODU POJAZDU
    private double dlugosc;
    private double predkosc;
    private double maxPredkosc;
    private double opoznienie;
    private double przyspieszenie;
    private double drogaHamowania;
    private Polozenie cel;

    public Pojazd(double dlugosc, double masa, double maxPredkosc, Polozenie polozenie) {
        this.dlugosc = dlugosc;
        this.maxPredkosc = maxPredkosc;
        this.polozenie = polozenie;

        predkosc = maxPredkosc;

        opoznienie = -36000 / masa;
        przyspieszenie = 20000 / masa;
        drogaHamowania = Math.pow(maxPredkosc, 2) / (2 * -opoznienie);
    }

    public double getDlugosc() { return dlugosc; }
    public double getMaxPredkosc() { return maxPredkosc; }
    public Polozenie getPolozenie() { return polozenie; }
    public Polozenie getCel() { return cel; }
    public void setCel(Polozenie cel) { this.cel = cel; }

    public double getPredkosc() { return predkosc; }
    public double getOpoznienie() { return opoznienie; }
    public double getPrzyspieszenie() { return przyspieszenie; }
    public double getDrogaHamowania() { return drogaHamowania; }

    public void zatrzymaj() { predkosc = 0; }
    public void jedz() { predkosc = maxPredkosc; }

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
