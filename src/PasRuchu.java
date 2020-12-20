public class PasRuchu extends ElementInfrastruktury {
    private String zwrot; // gora lub dol
    private double dlugosc;
    private Polozenie polozenie;
    private Polozenie koniec;

    public PasRuchu(String zwrot, double dlugosc, Polozenie polozenie) {
        this.zwrot = zwrot;
        this.dlugosc = dlugosc;
        this.polozenie = polozenie;

         if (zwrot == "gora")
             koniec = new Polozenie(polozenie.getX(), polozenie.getY() + dlugosc);
         else
             koniec = new Polozenie(polozenie.getX(), polozenie.getY() - dlugosc);
    }

    public Polozenie getKoniec() { return koniec; }

    public String getZwrot() { return zwrot; }
}
