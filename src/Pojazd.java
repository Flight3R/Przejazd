public abstract class Pojazd {
    private double dlugosc;
    private double masa;
    private double predkosc;
    private double maxPredkosc;
    private Polozenie polozenie;

    public void zatrzymaj(){
        predkosc = 0;
    }

    public void jedz(){
        predkosc = maxPredkosc;
    }
}
