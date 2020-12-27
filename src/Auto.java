public class Auto extends Pojazd {

    private final PasRuchu pas;
    private final Auto autoPrzed;

    public Auto(double dlugosc, Integer masa, double maxPredkosc, Polozenie polozenie, Przejazd przejazd, PasRuchu pas, Auto autoPrzed) {
        super(dlugosc, masa, maxPredkosc, polozenie);
        this.pas = pas;
        this.autoPrzed = autoPrzed;
        copyCel(pas.getKoniec());
        start();
    }

    public boolean sprawdzSwiatla() {
        boolean przedPrzejazdem;
        boolean mozliwoscWyhamowania;
        Swiatlo swiatlo;

        if (pas.getZwrot() == "gora") {
            przedPrzejazdem = getPolozenie().getY() < pas.getSygnalizacja().getPolozenie().getY();
            mozliwoscWyhamowania = getDrogaHamowania() <= (pas.getSygnalizacja().getPolozenie().getY() - getPolozenie().getY());
            swiatlo = pas.getSygnalizacja();
        } else { // zwrot == "dol"
            przedPrzejazdem = pas.getSygnalizacja().getPolozenie().getY() < getPolozenie().getY();
            mozliwoscWyhamowania = getDrogaHamowania() <= (getPolozenie().getY() - pas.getSygnalizacja().getPolozenie().getY());
            swiatlo = pas.getSygnalizacja();
        }

        if (przedPrzejazdem && swiatlo.isStop() && mozliwoscWyhamowania) {
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
        double deltaT = 200.0/1000;
        while(true) {
            boolean swiatla = sprawdzSwiatla();         // Aby zawsze sie wykonalo
            boolean autoPrzed = sprawdzAutoPrzed();     // Aby zawsze sie wykonalo i nadpisało jak cos

            if ( !swiatla && !autoPrzed && getCel() != pas.getKoniec())
               copyCel(pas.getKoniec());

            if (getCel().getY() != getPolozenie().getY()) {
                if (Math.abs(getCel().getY() - getPolozenie().getY()) < getDrogaHamowania())
                    hamuj(deltaT);
                else
                    przyspiesz(deltaT);

                getPolozenie().przenies(getPredkosc(), deltaT, pas.getZwrot(), getCel());
            }

            try { sleep(200); } catch (InterruptedException interruptedException) { interruptedException.printStackTrace(); }
        }
    }
}

