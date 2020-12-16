public abstract class Pojazd {
    protected double dlugosc;
    private double masa;
    private double predkosc;
    private double maxPredkosc;
    protected Polozenie polozenie;

    public void zatrzymaj(){
        predkosc = 0;
    }

    public void jedz(){
        predkosc = maxPredkosc;
    }
}
