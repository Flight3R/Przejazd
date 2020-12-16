public class Pociag extends Pojazd {
    private String nazwa;
    private Integer czasPrzyjazdu;
    private Tor tor;
    private Przejazd przejazd;

    public String getNazwa() {
        return nazwa;
    }
    public Integer getCzasPrzyjazdu() {
        return czasPrzyjazdu;
    }
    public Tor getTor() { return tor; }



    public void sprawdzCzujniki() {
        // ZAKLADAMY POLOZENIE POCIAGU W JEGO SRODKU  !!!!!!!111!!!!!JEDEN!!11 bo latwiej okreslic dla czujnikow czy pociag jest na nich
        // DOROBIC MECHANIKE:  czujnik.aktywacje PRZEJAZD SPRAWDZA CZY czujnik przed i za maja tyle samo aktywacji czyli suma jest = 0(mod 2), na tej podstawie wie czy mozna otworzy szlaban, jak maja rozna to trzeba zamknac szlaban

        if (tor.getKierunek() == "prawo") {
            boolean przedPrzejazdem = (this.polozenie.getX()+dlugosc < przejazd.getPolozenie().getX());
            if(przedPrzejazdem) {
                boolean czujnikPodPociagiem = (tor.getCzujnik_przed().getPolozenie().getX() < (this.polozenie.getX() + this.dlugosc)); // OK
                if (czujnikPodPociagiem)
                    tor.getCzujnik_przed().aktywuj();

            } else {
                boolean czujnikPodPociagiem = (tor.getCzujnik_za().getPolozenie().getX() < (this.polozenie.getX() - this.dlugosc)); //OK
                if (czujnikPodPociagiem)
                    tor.getCzujnik_za().aktywuj();
            }
        } else if (tor.getKierunek() == "lewo") {
            boolean przedPrzejazdem = this.polozenie.getX()-dlugosc > przejazd.getPolozenie().getX();
            if(przedPrzejazdem) {
                boolean czujnikPodPociagiem = (this.polozenie.getX() - this.dlugosc) < tor.getCzujnik_za().getPolozenie().getX(); //OK
                if (czujnikPodPociagiem)
                    tor.getCzujnik_przed().aktywuj();
            } else {
                boolean czujnikPodPociagiem = (this.polozenie.getX() + this.dlugosc) < tor.getCzujnik_przed().getPolozenie().getX(); //OK
                if (czujnikPodPociagiem)
                    tor.getCzujnik_za().aktywuj();
            }
        }
    }
}
