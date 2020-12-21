public class Auto extends Pojazd implements Runnable {

    private PasRuchu pas;
    private Ulica ulica;
    private Auto autoPrzed;

    public Auto(double dlugosc, double masa, double maxPredkosc, Polozenie polozenie, Przejazd przejazd, PasRuchu pas, Ulica ulica, Auto autoPrzed) {
        super(dlugosc, masa, maxPredkosc, polozenie, przejazd);
        this.pas = pas;
        this.ulica = ulica;
        this.autoPrzed = autoPrzed;
    }

    public boolean sprawdzSwiatla() {
        boolean przedPrzejazdem;
        boolean mozliwoscWyhamowania;
        Swiatlo swiatlo;

        if (pas.getZwrot() == "gora") {
            przedPrzejazdem = getPolozenie().getY() < ulica.getSwiatloDolne().getPolozenie().getY();
            mozliwoscWyhamowania = getDrogaHamowania() <= (ulica.getSwiatloDolne().getPolozenie().getY() - getPolozenie().getY());
            swiatlo = ulica.getSwiatloDolne();
        } else { // zwrot == "dol"
            przedPrzejazdem = ulica.getSwiatloGorne().getPolozenie().getY() < getPolozenie().getY();
            mozliwoscWyhamowania = getDrogaHamowania() <= (getPolozenie().getY() - ulica.getSwiatloGorne().getPolozenie().getY());
            swiatlo = ulica.getSwiatloGorne();
        }

        if (przedPrzejazdem && swiatlo.isZapalone() && mozliwoscWyhamowania) {
            getCel().setY(swiatlo.getPolozenie().getX());
            return true;
        }
        return false;
    }

    public boolean sprawdzAutoPrzed() {

        if (pas.getZwrot() == "gora") {
            if (Math.abs(autoPrzed.getPolozenie().getY() - autoPrzed.getDlugosc() - getPolozenie().getX()) < getDrogaHamowania()) {
                getCel().setY(autoPrzed.getPolozenie().getY() - autoPrzed.getDlugosc());
                return true;
            }
        } else { // zwrot == "dol"
            if (Math.abs(autoPrzed.getPolozenie().getY() + autoPrzed.getDlugosc() - getPolozenie().getY()) < getDrogaHamowania()) {
                getCel().setY(autoPrzed.getPolozenie().getY() + autoPrzed.getDlugosc());
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
            boolean swiatla = sprawdzSwiatla();         // Aby zawsze sie wykonalo
            boolean autoPrzed = sprawdzAutoPrzed();     // Aby zawsze sie wykonalo i nadpisało jak cos

            if ( !swiatla && !autoPrzed && getCel() != pas.getKoniec())
               setCel(pas.getKoniec());

            if (getCel().getY() != getPolozenie().getY()) {
                if (Math.abs(getCel().getY() - getPolozenie().getY()) < getDrogaHamowania())
                    hamuj(deltaT);
                else
                    przyspiesz(deltaT);

                getPolozenie().przenies(getPredkosc(), deltaT, pas.getZwrot(), getCel());
            }

            try { sleep((long) deltaT*1000); } catch (InterruptedException interruptedException) { interruptedException.printStackTrace(); }
        }
    }

}

