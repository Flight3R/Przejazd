public abstract class Droga extends ElementInfrastruktury {
    private Polozenie polozenie;
    private Swiatlo swiatlo;
    private String zwrot;
    private double dlugosc;
    private Polozenie koniec;

    public Droga(Polozenie polozenie, Swiatlo swiatlo, String zwrot, double dlugosc) {
        this.polozenie = polozenie;
        this.swiatlo = swiatlo;

        this.zwrot = zwrot;
        this.dlugosc = dlugosc;

        if (zwrot == "gora")
            koniec = new Polozenie(polozenie.getX(), polozenie.getY() + dlugosc);
        else if (zwrot == "dol")
            koniec = new Polozenie(polozenie.getX(), polozenie.getY() - dlugosc);
        else if (zwrot == "lewo")
            koniec = new Polozenie(polozenie.getX() - dlugosc, polozenie.getY());
        else if (zwrot == "prawo")
            koniec = new Polozenie(polozenie.getX() + dlugosc, polozenie.getY());

    }

    public Polozenie getPolozenie() { return polozenie; }
    public Swiatlo getSwiatlo() { return swiatlo; }
    public String getZwrot() { return zwrot; }
    public double getDlugosc() { return dlugosc; }
    public Polozenie getKoniec() { return koniec; }

}
