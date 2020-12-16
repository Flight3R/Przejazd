public class Rogatka extends ElementInfrastruktury {

    private Integer czasZamykania;
    private boolean stan;

    public void setCzasZamykania(Integer czasZamykania) {
        this.czasZamykania = czasZamykania;
    }

    public Integer getCzasZamykania() {
        return czasZamykania;
    }

    public void setStan(boolean stan) {
        this.stan = stan;
    }

    public boolean isOtwarta() { return stan; }

    public void otworz(){

    }
    public void zamknij(){

    }
}
