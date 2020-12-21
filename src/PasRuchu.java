public class PasRuchu extends Droga {
    private Rogatka rogatka;

    public PasRuchu(Polozenie polozenie, Swiatlo swiatlo, String zwrot, double dlugosc, Rogatka rogatka) {
        super(polozenie, swiatlo, zwrot, dlugosc);
        this.rogatka = rogatka;
    }

    public Rogatka getRogatka() { return rogatka; }
}
