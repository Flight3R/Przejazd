public class Tor extends Droga {
   // private double dlugosc;
   // private String zwrot; // prawo lub lewo (ważne do metody przenies)
    private Czujnik czujnikPrzed;
    private Czujnik czujnikZa;
    //private Swiatlo ssp; // SSP - Samoczynna sygnalizacja przejazdowa (info dla maszynisty czy rogatki działają)

    /*public double getDlugosc() {
        return dlugosc;
    }

    public String getZwrot() {
        return zwrot;
    }*/

    public Czujnik getCzujnikPrzed() {
        return czujnikPrzed;
    }

    public Czujnik getCzujnikZa() {
        return czujnikZa;
    }

   // public Swiatlo getSwiatlo() { return ssp; }
}
