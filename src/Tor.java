import java.util.ArrayList;

public class Tor extends Droga {

    private final Semafor semaforSSP;

   /* private final Czujnik czujnikSBL1;
    private final Czujnik czujnikSBL2;
    private final Czujnik czujnikSBL3;
    private final Czujnik czujnikSBL4;
    private final Semafor semaforSBL1;
    private final Semafor semaforSBL2;
    private final Semafor semaforSBL3;*/


    private final ArrayList<Czujnik> CzujnikiNajazdoweSBL;
    private final ArrayList<Czujnik> CzujnikiZjazdoweSBL;
    private final ArrayList<Semafor> SemaforySBL;

    /*public Tor(Polozenie polozenie, String nazwa, String zwrot, double dlugosc, Czujnik czujnikSSP1, Czujnik czujnikSSP2,
               Czujnik czujnikSBL1, Czujnik czujnikSBL2, Czujnik czujnikSBL3, Czujnik czujnikSBL4, Semafor semaforSBL1, Semafor semaforSBL2,
               Semafor semaforSBL3, Semafor semaforSSP) {
        super(polozenie, nazwa, zwrot, dlugosc);
        this.czujnikSSP1 = czujnikSSP1;
        this.czujnikSSP2 = czujnikSSP2;
        this.czujnikSBL1 = czujnikSBL1;
        this.czujnikSBL2 = czujnikSBL2;
        this.czujnikSBL3 = czujnikSBL3;
        this.czujnikSBL4 = czujnikSBL4;
        this.semaforSBL1 = semaforSBL1;
        this.semaforSBL2 = semaforSBL2;
        this.semaforSBL3 = semaforSBL3;
        this.semaforSSP = semaforSSP;
    }*/

    public Tor(Polozenie polozenie, String nazwa, String zwrot, double dlugosc, Semafor semaforSSP, ArrayList<Czujnik> czujnikiNajazdoweSBL,
               ArrayList<Czujnik> czujnikiZjazdoweSBL, ArrayList<Semafor> semaforySBL) {
        super(polozenie, nazwa, zwrot, dlugosc);
        this.semaforSSP = semaforSSP;
        CzujnikiNajazdoweSBL = czujnikiNajazdoweSBL;
        CzujnikiZjazdoweSBL = czujnikiZjazdoweSBL;
        SemaforySBL = semaforySBL;
    }

    /*public Czujnik getCzujnikSSP1() { return czujnikSSP1; }

    public Czujnik getCzujnikSSP2() { return czujnikSSP2; }

    public Czujnik getCzujnikSBL1() { return czujnikSBL1; }

    public Czujnik getCzujnikSBL2() { return czujnikSBL2; }

    public Czujnik getCzujnikSBL3() { return czujnikSBL3; }

    public Czujnik getCzujnikSBL4() { return czujnikSBL4; }

    public Swiatlo getSemaforSBL1() { return semaforSBL1; }

    public Swiatlo getSemaforSBL2() { return semaforSBL2; }

    public Swiatlo getSemaforSBL3() { return semaforSBL3; }*/

    public Semafor getSemaforSSP() { return semaforSSP; }

    public ArrayList<Czujnik> getCzujnikiNajazdoweSBL() { return CzujnikiNajazdoweSBL; }

    public ArrayList<Czujnik> getCzujnikiZjazdoweSBL() { return CzujnikiZjazdoweSBL; }

    public ArrayList<Semafor> getSemaforySBL() { return SemaforySBL; }

    public Integer getIloscSemaforow() {
        return SemaforySBL.size();
    }
}
