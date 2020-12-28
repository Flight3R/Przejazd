import java.util.ArrayList;

public class Tor extends Droga {

    private final Semafor semaforSSP;
    private final Czujnik czujnikSSP1;
    private final Czujnik czujnikSSP2;

    private final ArrayList<Czujnik> CzujnikiNajazdoweSBL;
    private final ArrayList<Czujnik> CzujnikiZjazdoweSBL;
    private final ArrayList<Semafor> SemaforySBL;

    public Tor(Polozenie polozenie, String nazwa, String zwrot, double dlugosc, Semafor semaforSSP, Czujnik czujnikSSP1,
               Czujnik czujnikSSP2, ArrayList<Czujnik> czujnikiNajazdoweSBL, ArrayList<Czujnik> czujnikiZjazdoweSBL,
               ArrayList<Semafor> semaforySBL) {
        super(polozenie, nazwa, zwrot, dlugosc);
        this.semaforSSP = semaforSSP;
        this.czujnikSSP1 = czujnikSSP1;
        this.czujnikSSP2 = czujnikSSP2;
        CzujnikiNajazdoweSBL = czujnikiNajazdoweSBL;
        CzujnikiZjazdoweSBL = czujnikiZjazdoweSBL;
        SemaforySBL = semaforySBL;
    }

    public Semafor getSemaforSSP() { return semaforSSP; }

    public Czujnik getCzujnikSSP1() { return czujnikSSP1; }

    public Czujnik getCzujnikSSP2() { return czujnikSSP2; }

    public ArrayList<Czujnik> getCzujnikiNajazdoweSBL() { return CzujnikiNajazdoweSBL; }

    public ArrayList<Czujnik> getCzujnikiZjazdoweSBL() { return CzujnikiZjazdoweSBL; }

    public ArrayList<Semafor> getSemaforySBL() { return SemaforySBL; }

    public Integer getIloscSemaforow() {
        return SemaforySBL.size();
    }
}
