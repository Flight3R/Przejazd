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

            boolean przedPrzejazdem = (this.polozenie.getX() + dlugosc/2) < przejazd.getPolozenie().getX();
            boolean zaPrzejazdem = przejazd.getPolozenie().getX() < (this.polozenie.getX() - dlugosc/2);

            if(przedPrzejazdem) {

                boolean czujnikPodPociagiem = (this.polozenie.getX() - this.dlugosc/2) < tor.getCzujnik_przed().getPolozenie().getX() && tor.getCzujnik_przed().getPolozenie().getX() < (this.polozenie.getX() + this.dlugosc/2); // OK
                if (czujnikPodPociagiem)
                    tor.getCzujnik_przed().aktywuj(this.nazwa);

            } else if (zaPrzejazdem) {

                boolean czujnikPodPociagiem = (this.polozenie.getX() - this.dlugosc/2) < tor.getCzujnik_za().getPolozenie().getX() && tor.getCzujnik_za().getPolozenie().getX() < (this.polozenie.getX() + this.dlugosc/2); // OK
                if (czujnikPodPociagiem)
                    tor.getCzujnik_za().aktywuj(this.nazwa);
            }
        } else if (tor.getKierunek() == "lewo") {

            boolean przedPrzejazdem = przejazd.getPolozenie().getX() < (this.polozenie.getX() - this.dlugosc/2) ;
            boolean zaPrzejazdem = (this.polozenie.getX() + dlugosc/2) < przejazd.getPolozenie().getX();

            if(przedPrzejazdem) {

                boolean czujnikPodPociagiem = (this.polozenie.getX() - this.dlugosc/2) < tor.getCzujnik_przed().getPolozenie().getX() && tor.getCzujnik_przed().getPolozenie().getX() < (this.polozenie.getX() + this.dlugosc/2); // OK
                if (czujnikPodPociagiem)
                    tor.getCzujnik_przed().aktywuj(this.nazwa);
            } else if (zaPrzejazdem) {

                boolean czujnikPodPociagiem = (this.polozenie.getX() - this.dlugosc/2) < tor.getCzujnik_za().getPolozenie().getX() && tor.getCzujnik_za().getPolozenie().getX() < (this.polozenie.getX() + this.dlugosc/2); // OK
                if (czujnikPodPociagiem)
                    tor.getCzujnik_za().aktywuj(this.nazwa);
            }
        }
    }
}
