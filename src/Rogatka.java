public class Rogatka extends ElementInfrastruktury {

    private Integer czasZamykania;
    private boolean otwarta;

    public Rogatka(Polozenie polozenie, String nazwa, Integer czasZamykania, boolean otwarta) {
        super(polozenie, nazwa);
        this.czasZamykania = czasZamykania;
        this.otwarta = otwarta;
    }

    public void setCzasZamykania(Integer czasZamykania) {
        this.czasZamykania = czasZamykania;
    }

    public Integer getCzasZamykania() {
        return czasZamykania;
    }

    public void setOtwarta(boolean otwarta) {
        this.otwarta = otwarta;
    }

    public boolean isOtwarta() { return otwarta; }

    public void otworz(){

    }
    public void zamknij(){

    }
}
