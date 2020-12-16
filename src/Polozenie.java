public class Polozenie {
    private double x;
    private double y;

    public void przenies(double predkosc, double deltaT, String kierunek){
        double deltaX;
        double deltaY;
        switch (kierunek) {
            case "prawo":
                deltaX = predkosc * deltaT;
                this.x = this.x + deltaX;
                break;
            case "lewo":
                deltaX = predkosc * deltaT;
                this.x = this.x - deltaX;
                break;
            case "gora":
                deltaY = predkosc * deltaT;
                this.y = this.y + deltaY;
                break;
            case "dol":
                deltaY = predkosc * deltaT;
                this.y = this.y - deltaY;
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
