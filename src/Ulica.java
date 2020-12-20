public class Ulica extends ElementInfrastruktury {
    private double dlugosc;
    private Integer iloscPasow;
    private Swiatlo swiatloGorne;
    private Swiatlo swiatloDolne;
    private PasRuchu pas1;
    private PasRuchu pas2;


    public Swiatlo getSwiatloGorne() { return swiatloGorne; }
    public Swiatlo getSwiatloDolne() { return swiatloDolne; }

    public PasRuchu getPas1() { return pas1; }
    public PasRuchu getPas2() { return pas2; }
}
