public abstract class Pojazd {
    protected double dlugosc;
    private double masa;
    protected double predkosc;
    private double maxPredkosc;
    protected double opoznienie = -36000/masa;
    protected double przyspieszenie =  20000/masa;;
    protected Polozenie polozenie;
    protected Przejazd przejazd;
    protected Polozenie cel;


    public void zatrzymaj(){
        predkosc = 0;
    }

    public void jedz(){
        predkosc = maxPredkosc;
    }

    public void hamuj(double deltaT) {
        // F = a*m => a=F/m przyspieszenie odwrotnie proporcjonalne do masy F=36kN
        predkosc = predkosc + opoznienie*deltaT;
    }

    public void przyspiesz(double deltaT) {
        // F = a*m => a=F/m przyspieszenie odwrotnie proporcjonalne do masy F=20kN
        predkosc = predkosc + przyspieszenie*deltaT;
    }

}
