package podlozaTransportowe;

import klasyAbstrakcyjne.Droga;
import lokacja.Polozenie;
import obslugaPrzejazdu.Rozklad;
import pojazdy.Pociag;
import urzadzeniaKolejowe.Czujnik;
import urzadzeniaKolejowe.Semafor;

import java.util.ArrayList;

public class Tor extends Droga {

    private final Semafor tarczaSSP;
    private final Czujnik czujnikNajazdowySSP;
    private final Czujnik czujnikZjazdowySSP;

    private final ArrayList<Czujnik> czujnikiNajazdoweSBL;
    private final ArrayList<Czujnik> czujnikiZjazdoweSBL;
    private final ArrayList<Semafor> semaforySBL;

    private final Rozklad rozkladPociagow = new Rozklad();

    public Tor(Polozenie polozenie, String nazwa, String zwrot, double dlugosc, Semafor tarczaSSP, Czujnik czujnikNajazdowySSP,
               Czujnik czujnikZjazdowySSP, ArrayList<Czujnik> czujnikiNajazdoweSBL, ArrayList<Czujnik> czujnikiZjazdoweSBL,
               ArrayList<Semafor> semaforySBL) {
        super(polozenie, nazwa, zwrot, dlugosc);
        this.tarczaSSP = tarczaSSP;
        this.czujnikNajazdowySSP = czujnikNajazdowySSP;
        this.czujnikZjazdowySSP = czujnikZjazdowySSP;
        this.czujnikiNajazdoweSBL = czujnikiNajazdoweSBL;
        this.czujnikiZjazdoweSBL = czujnikiZjazdoweSBL;
        this.semaforySBL = semaforySBL;
    }

    public Semafor getTarczaSSP() {
        return tarczaSSP;
    }

    public Czujnik getCzujnikNajazdowySSP() {
        return czujnikNajazdowySSP;
    }

    public Czujnik getCzujnikZjazdowySSP() {
        return czujnikZjazdowySSP;
    }

    public ArrayList<Czujnik> getCzujnikiNajazdoweSBL() {
        return czujnikiNajazdoweSBL;
    }

    public ArrayList<Czujnik> getCzujnikiZjazdoweSBL() {
        return czujnikiZjazdoweSBL;
    }

    public ArrayList<Semafor> getSemaforySBL() {
        return semaforySBL;
    }

    public Integer getIloscSemaforowSBL() {
        return semaforySBL.size();
    }

    public Rozklad getRozkladPociagow() { return rozkladPociagow; }

}