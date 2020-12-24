public class Rogatka extends ElementInfrastruktury {

    private Integer czasZamykania;
    private boolean otwarta = true;

    public Rogatka(Polozenie polozenie, String nazwa, Integer czasZamykania) {
        super(polozenie, nazwa);
        this.czasZamykania = czasZamykania;
    }

    public void setCzasZamykania(Integer czasZamykania) {
        this.czasZamykania = czasZamykania;
    }

    public Integer getCzasZamykania() {
        return czasZamykania;
    }

    public boolean isOtwarta() { return otwarta; }

    public void otworz(){
        otwarta = true;
    }
    public void zamknij(){
        otwarta = false;
    }
}
