public abstract class Droga extends ElementInfrastruktury {
    private Swiatlo swiatlo;
    private String zwrot;
    private double dlugosc;
    private Polozenie koniec = null;

    public Droga(Polozenie polozenie, String nazwa, Swiatlo swiatlo, String zwrot, double dlugosc) {
        super(polozenie, nazwa);
        this.swiatlo = swiatlo;
        this.zwrot = zwrot;
        this.dlugosc = dlugosc;

        if (zwrot == "gora")
            this.koniec = new Polozenie(polozenie.getX(), polozenie.getY() + dlugosc);
        else if (zwrot == "dol")
            this.koniec = new Polozenie(polozenie.getX(), polozenie.getY() - dlugosc);
        else if (zwrot == "lewo")
            this.koniec = new Polozenie(polozenie.getX() - dlugosc, polozenie.getY());
        else // zwrot == "prawo"
            this.koniec = new Polozenie(polozenie.getX() + dlugosc, polozenie.getY());

    }

    public Swiatlo getSwiatlo() { return swiatlo; }

    public String getZwrot() { return zwrot; }

    public double getDlugosc() { return dlugosc; }

    public Polozenie getKoniec() { return koniec; }

}
