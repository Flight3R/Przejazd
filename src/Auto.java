public class Auto extends Pojazd {

    private PasRuchu pas;
    private Ulica ulica;

    public void sprawdzSwiatla() {
        if (pas.getKierunek() == "dol") {
            boolean przedPrzejazdem = ulica.getSwiatloGorne().getPolozenie().getY() < (polozenie.getY() - dlugosc/2);
            boolean mozliwoscWyhamowania = 2*Math.abs(ulica.getSwiatloGorne().getPolozenie().getY() - polozenie.getY()) < Math.pow(predkosc,2)/opoznienie;
            if (przedPrzejazdem && ulica.getSwiatloGorne().isZapalone() && mozliwoscWyhamowania) {
                cel = ulica.getSwiatloGorne().polozenie;
            }
        } else if (pas.getKierunek() == "gora") {
            boolean przedPrzejazdem = (polozenie.getY() + dlugosc/2) < ulica.getSwiatloGorne().getPolozenie().getY();
            boolean mozliwoscWyhamowania = 2*Math.abs(ulica.getSwiatloDolne().getPolozenie().getY() - polozenie.getY()) < Math.pow(predkosc,2)/opoznienie;
            if (przedPrzejazdem && ulica.getSwiatloDolne().isZapalone() && mozliwoscWyhamowania) {
                cel = ulica.getSwiatloDolne().polozenie;
            }
        }
    }

}
