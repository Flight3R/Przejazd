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
        // ZAKLADAMY POLOZENIE POCIAGU W JEGO SRODKU bo latwiej okreslic dla czujnikow czy pociag jest na nich

        boolean czujnik1PodPociagiem = (this.polozenie.getX() - this.dlugosc/2) < tor.getCzujnik_przed().getPolozenie().getX() && tor.getCzujnik_przed().getPolozenie().getX() < (this.polozenie.getX() + this.dlugosc/2); // OK
        boolean czujnik2PodPociagiem = (this.polozenie.getX() - this.dlugosc/2) < tor.getCzujnik_za().getPolozenie().getX() && tor.getCzujnik_za().getPolozenie().getX() < (this.polozenie.getX() + this.dlugosc/2); // OK

        if (tor.getKierunek() == "prawo") {
            if (czujnik1PodPociagiem)
                tor.getCzujnik_przed().aktywuj(this.nazwa);

            if (czujnik2PodPociagiem)
                tor.getCzujnik_za().aktywuj(this.nazwa);

        } else if (tor.getKierunek() == "lewo") {
            if (czujnik1PodPociagiem)
                tor.getCzujnik_przed().aktywuj(this.nazwa);

             if (czujnik2PodPociagiem)
                tor.getCzujnik_za().aktywuj(this.nazwa);
        }
    }
}
