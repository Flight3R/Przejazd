public class Auto extends Pojazd implements Runnable {

    private PasRuchu pas;
    private Ulica ulica;
    private Auto autoPrzed;

    public Auto(double dlugosc, double masa, double maxPredkosc, Polozenie polozenie, Przejazd przejazd, PasRuchu pas, Ulica ulica) {
        super(dlugosc, masa, maxPredkosc, polozenie, przejazd);
        this.pas = pas;
        this.ulica = ulica;
    }

    public boolean sprawdzSwiatla() {
        boolean przedPrzejazdem;
        boolean mozliwoscWyhamowania;
        Swiatlo swiatlo;

        if (pas.getZwrot() == "dol") {
            przedPrzejazdem = ulica.getSwiatloGorne().getPolozenie().getY() < polozenie.getY();
            mozliwoscWyhamowania = drogaHamowania <= (polozenie.getY() - ulica.getSwiatloGorne().getPolozenie().getY());
            swiatlo = ulica.getSwiatloGorne();

        } else {
            przedPrzejazdem = polozenie.getY() < ulica.getSwiatloDolne().getPolozenie().getY();
            mozliwoscWyhamowania = drogaHamowania <= (ulica.getSwiatloDolne().getPolozenie().getY() - polozenie.getY());
            swiatlo = ulica.getSwiatloDolne();
        }

        if (przedPrzejazdem && swiatlo.isZapalone() && mozliwoscWyhamowania) {
            cel = swiatlo.getPolozenie();
            return true;
        }
    }



    public boolean sprawdzAutoPrzed() {

        if (pas.getZwrot() == "dol") {
            if (Math.abs(autoPrzed.getPolozenie().getY() + autoPrzed.getDlugosc() - polozenie.getY()) < drogaHamowania) {
                cel.setY(autoPrzed.getPolozenie().getY() + autoPrzed.getDlugosc());
                return true;
        } else {
            if (Math.abs(autoPrzed.getPolozenie().getY() - autoPrzed.getDlugosc() - polozenie.getX()) < drogaHamowania) {
                cel.setY(autoPrzed.getPolozenie().getY() - autoPrzed.getDlugosc());
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        super.run();
        double deltaT = 200/1000;
        while(true) {
            if ( !(sprawdzSwiatla() || sprawdzAutoPrzed()) && cel != pas.getKoniec())
                cel = pas.getKoniec();

            if (cel.getY() != polozenie.getY()) {
                if (Math.abs(cel.getY() - polozenie.getY()) < drogaHamowania)
                    hamuj(deltaT);
                else
                    przyspiesz(deltaT);

                polozenie.przenies(predkosc, deltaT, pas.getZwrot(), cel);
            }

            try { sleep((long) deltaT*1000); } catch (InterruptedException interruptedException) { interruptedException.printStackTrace(); }
        }
    }

}

