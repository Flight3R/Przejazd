public class PasRuchu extends Droga {
    private final Rogatka rogatka;
    private final Swiatlo sygnalizacja;

    public PasRuchu(Polozenie polozenie, String nazwa, Swiatlo sygnalizacja, String zwrot, double dlugosc, Rogatka rogatka) {
        super(polozenie, nazwa, zwrot, dlugosc);
        this.rogatka = rogatka;
        this.sygnalizacja = sygnalizacja;
    }

    public Rogatka getRogatka() { return rogatka; }

    public Swiatlo getSygnalizacja() { return sygnalizacja; }
}
