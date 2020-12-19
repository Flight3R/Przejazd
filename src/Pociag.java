public class Pociag extends Pojazd {
    private String nazwa;
    private Integer czasPrzyjazdu;
    private Tor tor;


    public String getNazwa() {
        return nazwa;
    }
    public Integer getCzasPrzyjazdu() {
        return czasPrzyjazdu;
    }
    public Tor getTor() { return tor; }

    public void sprawdzCzujniki() {
        // ZAKLADAMY POLOZENIE POCIAGU W JEGO SRODKU bo latwiej okreslic dla czujnikow czy pociag jest na nich

        boolean czujnik1PodPociagiem = (polozenie.getX() -dlugosc/2) < tor.getCzujnik_przed().getPolozenie().getX() && tor.getCzujnik_przed().getPolozenie().getX() < (polozenie.getX() + dlugosc/2);
        boolean czujnik2PodPociagiem = (polozenie.getX() - dlugosc/2) < tor.getCzujnik_za().getPolozenie().getX() && tor.getCzujnik_za().getPolozenie().getX() < (polozenie.getX() + dlugosc/2);
        // --->>>---<czujnik1>-----|przejazd|-----<czujnik2>-->>>---
        if (tor.getKierunek() == "prawo") {
            boolean zaPrzejazdem = przejazd.getPolozenie().getX() < (polozenie.getX() - dlugosc/2);
            if (czujnik1PodPociagiem) {
                tor.getCzujnik_przed().aktywuj(nazwa);

            } else if (czujnik2PodPociagiem && zaPrzejazdem)
                tor.getCzujnik_za().aktywuj(nazwa);

        } else if (tor.getKierunek() == "lewo") {
            boolean zaPrzejazdem = (polozenie.getX() + dlugosc/2) < przejazd.getPolozenie().getX();
            if (czujnik1PodPociagiem) {
                tor.getCzujnik_przed().aktywuj(nazwa);

            } else if (czujnik2PodPociagiem && zaPrzejazdem)
                tor.getCzujnik_za().aktywuj(nazwa);
        }
    }
}
