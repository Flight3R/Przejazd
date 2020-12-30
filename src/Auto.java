public class Auto extends Pojazd {

    private final PasRuchu pas;
    private final Auto autoPrzed;

    public Auto(String nazwa, double dlugosc, Integer masa, double maxPredkosc, Polozenie polozenie, PasRuchu pas, Auto autoPrzed) {
        super("Auto", nazwa, dlugosc, masa, maxPredkosc, polozenie);
        this.pas = pas;
        this.autoPrzed = autoPrzed;
        copyCel(pas.getKoniec());
//        start();
    }

    @Override
    public String toString() {
        return "Auto: " + getNazwa() + "\tV= " + Math.round(getPredkosc()*100.0)/100.0 + "\tX= " + Math.round(getPolozenie().getX()*100.0)/100.0 +
                "\tY= " + getPolozenie().getY() + "\tCEL= " + getCel().getY();
    }

    public boolean sprawdzSwiatla() {
        boolean przedSygnalizatorem;
        boolean mozliwoscWyhamowania;
//        Sygnalizator sygnalizator;

        if (pas.getZwrot() == "gora") {
            przedSygnalizatorem = getPolozenie().getY() <= pas.getSygnalizacja().getPolozenie().getY();
            mozliwoscWyhamowania = getDrogaHamowania() <= (pas.getSygnalizacja().getPolozenie().getY() - getPolozenie().getY());
//            sygnalizator = pas.getSygnalizacja();
        } else { // zwrot == "dol"
            przedSygnalizatorem = pas.getSygnalizacja().getPolozenie().getY() <= getPolozenie().getY();
            mozliwoscWyhamowania = getDrogaHamowania() <= (getPolozenie().getY() - pas.getSygnalizacja().getPolozenie().getY());
//            sygnalizator = pas.getSygnalizacja();
        }

        if (przedSygnalizatorem && pas.getSygnalizacja().isStop()) {
            if (getCel().getY() == pas.getSygnalizacja().getPolozenie().getY() || mozliwoscWyhamowania) {
                copyCel(pas.getSygnalizacja().getPolozenie());
                return true;
            }
        }
        return false;
    }

    public boolean sprawdzAutoPrzed() {

        if (pas.getZwrot() == "gora") {
            if (Math.abs(autoPrzed.getPolozenie().getY() - autoPrzed.getDlugosc() - getPolozenie().getX()) <= getDrogaHamowania()) {
                getCel().setY(autoPrzed.getPolozenie().getY() - autoPrzed.getDlugosc());
                return true;
            }
        } else { // zwrot == "dol"
            if (Math.abs(autoPrzed.getPolozenie().getY() + autoPrzed.getDlugosc() - getPolozenie().getY()) <= getDrogaHamowania()) {
                getCel().setY(autoPrzed.getPolozenie().getY() + autoPrzed.getDlugosc());
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        double deltaT = 200.0/1000;
        while(true) {
            boolean swiatla = sprawdzSwiatla();         // Aby zawsze sie wykonalo

            boolean autoPrzed;
            if (this.autoPrzed != null)
                autoPrzed = sprawdzAutoPrzed();     // Aby zawsze sie wykonalo i nadpisało jak cos
            else
                autoPrzed = false;

            if ( !swiatla && !autoPrzed && getCel() != pas.getKoniec())
               copyCel(pas.getKoniec());

            if (getCel().getY() != getPolozenie().getY()) {

                System.out.println(this + "   " + getDrogaHamowania());

                if (Math.abs(getCel().getY() - getPolozenie().getY()) < getDrogaHamowania()*2)
                    hamuj(deltaT);
                else
                    przyspiesz(deltaT);

                getPolozenie().przenies(getPredkosc(), deltaT, pas.getZwrot(), getCel());
            } else if (getPredkosc() != 0){
                setPredkosc(0);
            }

            try { sleep(200); } catch (InterruptedException interruptedException) { stop(); }
        }
    }
}

