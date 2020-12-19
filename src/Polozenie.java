public class Polozenie {
    private double x;
    private double y;

    public void przenies(double predkosc, double deltaT, String kierunek, Polozenie cel){
        double deltaX;
        double deltaY;
        switch (kierunek) {
            case "prawo":
                deltaX = predkosc * deltaT;
                if (deltaX < Math.abs(cel.getX() - x ))
                    x = x + deltaX;
                else
                    x = cel.getX();
                break;
            case "lewo":
                deltaX = predkosc * deltaT;
                x = x - deltaX;
                break;
            case "gora":
                deltaY = predkosc * deltaT;
                y = y + deltaY;
                break;
            case "dol":
                deltaY = predkosc * deltaT;
                y = y - deltaY;
                break;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
