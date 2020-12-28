import java.util.ArrayList;

public class Tor extends Droga {

    private final Swiatlo tarczaSSP;
    private final Czujnik czujnikSSPn;
    private final Czujnik czujnikSSPz;

    private final ArrayList<Czujnik> czujnikiNajazdoweSBL;
    private final ArrayList<Czujnik> czujnikiZjazdoweSBL;
    private final ArrayList<Swiatlo> semaforySBL;

    public Tor(Polozenie polozenie, String nazwa, String zwrot, double dlugosc, Swiatlo tarczaSSP, Czujnik czujnikSSPn,
               Czujnik czujnikSSPz, ArrayList<Czujnik> czujnikiNajazdoweSBL, ArrayList<Czujnik> czujnikiZjazdoweSBL,
               ArrayList<Swiatlo> semaforySBL) {
        super(polozenie, nazwa, zwrot, dlugosc);
        this.tarczaSSP = tarczaSSP;
        this.czujnikSSPn = czujnikSSPn;
        this.czujnikSSPz = czujnikSSPz;
        this.czujnikiNajazdoweSBL = czujnikiNajazdoweSBL;
        this.czujnikiZjazdoweSBL = czujnikiZjazdoweSBL;
        this.semaforySBL = semaforySBL;
    }

    public Swiatlo getTarczaSSP() { return tarczaSSP; }

    public Czujnik getCzujnikSSPn() { return czujnikSSPn; }

    public Czujnik getCzujnikSSPz() { return czujnikSSPz; }

    public ArrayList<Czujnik> getCzujnikiNajazdoweSBL() { return czujnikiNajazdoweSBL; }

    public ArrayList<Czujnik> getCzujnikiZjazdoweSBL() { return czujnikiZjazdoweSBL; }

    public ArrayList<Swiatlo> getSemaforySBL() { return semaforySBL; }

    public Integer getIloscSemaforowSBL() {
        return semaforySBL.size();
    }
}
