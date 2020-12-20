public abstract class Pojazd extends Thread {
    protected double dlugosc;
    private double masa;
    protected double predkosc;
    private double maxPredkosc;
    protected double opoznienie = -36000/masa;
    protected double przyspieszenie =  20000/masa;
    protected double drogaHamowania = Math.pow(maxPredkosc,2)/(2 * - opoznienie);
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
    public void run() {
        double deltaT = 200/1000;
        while(true) {
            if (cel.getY() != polozenie.getY()) {
                if (Math.abs(cel.getY() - polozenie.getY()) < drogaHamowania * 1.2) {
                    hamuj(deltaT);

                }
            }
            try { sleep((long) (deltaT*1000)); } catch (InterruptedException e) { e.printStackTrace(); }
        }

    }

}
