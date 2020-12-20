public class Auto extends Pojazd implements Runnable {

    private PasRuchu pas;
    private Ulica ulica;
    private Auto autoPrzed;

    public Auto(double dlugosc, double masa, double maxPredkosc, Polozenie polozenie, Przejazd przejazd, PasRuchu pas, Ulica ulica) {
        super(dlugosc, masa, maxPredkosc, polozenie, przejazd);
        this.pas = pas;
        this.ulica = ulica;
    }

    public String sprawdzSwiatla() {
        if (pas.getZwrot() == "dol") {
            boolean przedPrzejazdem = ulica.getSwiatloGorne().getPolozenie().getY() < (polozenie.getY() - dlugosc/2);
           // boolean mozliwoscWyhamowania = 2*Math.abs(ulica.getSwiatloGorne().getPolozenie().getY() - polozenie.getY()) < Math.pow(predkosc,2)/opoznienie;
            boolean mozliwoscWyhamowania = drogaHamowania <= (polozenie.getY() - ulica.getSwiatloGorne().getPolozenie().getY());

            if (przedPrzejazdem && ulica.getSwiatloGorne().isZapalone() && mozliwoscWyhamowania)
                return "gorne";

        } else if (pas.getZwrot() == "gora") {
            boolean przedPrzejazdem = (polozenie.getY() + dlugosc/2) < ulica.getSwiatloGorne().getPolozenie().getY();
           // boolean mozliwoscWyhamowania = 2*Math.abs(ulica.getSwiatloDolne().getPolozenie().getY() - polozenie.getY()) < Math.pow(predkosc,2)/opoznienie;
            boolean mozliwoscWyhamowania = drogaHamowania <= (ulica.getSwiatloDolne().getPolozenie().getY() - polozenie.getY());

            if (przedPrzejazdem && ulica.getSwiatloDolne().isZapalone() && mozliwoscWyhamowania)
                return "dolne";
        }
        return "ok";
    }

    public void sprawdzPrzeszkode() {

        String swiatla = sprawdzSwiatla();

        if (Math.abs(autoPrzed.getPolozenie().getX() - polozenie.getX()) < drogaHamowania * 1.2)
            cel = autoPrzed.getPolozenie();
        else if (swiatla == "gorne")
            cel = ulica.getSwiatloGorne().polozenie;
        else if (swiatla == "dolne")
            cel = ulica.getSwiatloDolne().polozenie;
        else
            cel = pas.getKoniec();
    }


    @Override
    public void run() {
        super.run();
        double deltaT = 200/1000;
        while(true) {
            sprawdzPrzeszkode();
            if (cel.getY() != polozenie.getY()) {
                if (Math.abs(cel.getY() - polozenie.getY()) < drogaHamowania * 1.2) {
                    hamuj(deltaT);
                }
                polozenie.przenies(predkosc, deltaT, pas.getZwrot(), cel);
            }

            try { sleep((long) deltaT*1000); } catch (InterruptedException interruptedException) { interruptedException.printStackTrace(); }
        }
    }

}

