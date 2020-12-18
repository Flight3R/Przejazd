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
        // --->>>---<czujnik1>-----|przejazd|-----<czujnik2>-->>>---
        if (tor.getKierunek() == "prawo") {
            boolean zaPrzejazdem = przejazd.getPolozenie().getX() < (this.polozenie.getX() - this.dlugosc/2);
            if (czujnik1PodPociagiem) {
                tor.getCzujnik_przed().aktywuj(this.nazwa);

            } else if (czujnik2PodPociagiem && zaPrzejazdem)
                tor.getCzujnik_za().aktywuj(this.nazwa);

        } else if (tor.getKierunek() == "lewo") {
            boolean zaPrzejazdem = (this.polozenie.getX() + this.dlugosc/2) < przejazd.getPolozenie().getX();
            if (czujnik1PodPociagiem) {
                tor.getCzujnik_przed().aktywuj(this.nazwa);

            } else if (czujnik2PodPociagiem && zaPrzejazdem)
                tor.getCzujnik_za().aktywuj(this.nazwa);
        }
    }
}
