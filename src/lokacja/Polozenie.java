package lokacja;

public class Polozenie {
    private double x;
    private double y;

    public Polozenie(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    public void przenies(double predkosc, double deltaT, String zwrot, Polozenie cel){
        double deltaX;
        double deltaY;
        switch (zwrot) {
            case "prawo":
                deltaX = predkosc * deltaT;
                if (deltaX < Math.abs(cel.getX() - x))
                    x = x + deltaX;
                else
                    x = cel.getX();
                break;
            case "lewo":
                deltaX = predkosc * deltaT;
                if (deltaX < Math.abs(cel.getX() - x))
                    x = x - deltaX;
                else
                    x = cel.getX();
                break;
            case "gora":
                deltaY = predkosc * deltaT;
                if (deltaY < Math.abs(cel.getY() - y))
                    y = y + deltaY;
                else
                    y = cel.getY();
                break;
            case "dol":
                deltaY = predkosc * deltaT;
                if (deltaY < Math.abs(cel.getY() - y))
                    y = y - deltaY;
                else
                    y = cel.getY();
                break;
        }
    }

}
