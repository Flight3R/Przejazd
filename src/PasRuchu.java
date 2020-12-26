public class PasRuchu extends Droga {
    private final Rogatka rogatka;

    public PasRuchu(Polozenie polozenie, String nazwa, Swiatlo swiatlo, String zwrot, double dlugosc, Rogatka rogatka) {
        super(polozenie, nazwa, swiatlo, zwrot, dlugosc);
        this.rogatka = rogatka;
    }

    public Rogatka getRogatka() { return rogatka; }
}
