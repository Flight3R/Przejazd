import java.util.ArrayList;

public class Tor extends Droga {

    private final Semafor semaforSSP;
    private final Czujnik czujnikSSPn;
    private final Czujnik czujnikSSPz;

    private final ArrayList<Czujnik> CzujnikiNajazdoweSBL;
    private final ArrayList<Czujnik> CzujnikiZjazdoweSBL;
    private final ArrayList<Semafor> SemaforySBL;

    public Tor(Polozenie polozenie, String nazwa, String zwrot, double dlugosc, Semafor semaforSSP, Czujnik czujnikSSPn,
               Czujnik czujnikSSPz, ArrayList<Czujnik> czujnikiNajazdoweSBL, ArrayList<Czujnik> czujnikiZjazdoweSBL,
               ArrayList<Semafor> semaforySBL) {
        super(polozenie, nazwa, zwrot, dlugosc);
        this.semaforSSP = semaforSSP;
        this.czujnikSSPn = czujnikSSPn;
        this.czujnikSSPz = czujnikSSPz;
        CzujnikiNajazdoweSBL = czujnikiNajazdoweSBL;
        CzujnikiZjazdoweSBL = czujnikiZjazdoweSBL;
        SemaforySBL = semaforySBL;
    }

    public Semafor getSemaforSSP() { return semaforSSP; }

    public Czujnik getCzujnikSSPn() { return czujnikSSPn; }

    public Czujnik getCzujnikSSPz() { return czujnikSSPz; }

    public ArrayList<Czujnik> getCzujnikiNajazdoweSBL() { return CzujnikiNajazdoweSBL; }

    public ArrayList<Czujnik> getCzujnikiZjazdoweSBL() { return CzujnikiZjazdoweSBL; }

    public ArrayList<Semafor> getSemaforySBL() { return SemaforySBL; }

    public Integer getIloscSemaforow() {
        return SemaforySBL.size();
    }
}
