package podlozaTransportowe;

import klasyAbstrakcyjne.Droga;
import lokacja.Polozenie;
import pojazdy.Auto;
import urzadzeniaUliczne.Rogatka;
import urzadzeniaUliczne.Sygnalizator;

import java.util.ArrayList;

public class PasRuchu extends Droga {
    private final Rogatka rogatka;
    private final Sygnalizator sygnalizacja;
    private final ArrayList<Auto> listaAut = new ArrayList<>();

    public PasRuchu(Polozenie polozenie, String nazwa, Sygnalizator sygnalizacja, String zwrot, double dlugosc, Rogatka rogatka) {
        super(polozenie, nazwa, zwrot, dlugosc);
        this.rogatka = rogatka;
        this.sygnalizacja = sygnalizacja;
    }

    public Rogatka getRogatka() { return rogatka; }

    public Sygnalizator getSygnalizacja() { return sygnalizacja; }

    public ArrayList<Auto> getListaAut() { return listaAut; }
}
