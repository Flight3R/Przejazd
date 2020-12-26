import java.util.ArrayList;

import static java.lang.Thread.activeCount;
import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Swiatlo sygnalizacjaTestowaP = new Swiatlo(new Polozenie(1, -4), "sygnalizacja_pas_prawy");
        Rogatka rogatkaTestowaP = new Rogatka(new Polozenie(1, -3), "rogatka_pas_prawy", 5);
        PasRuchu pasTestowyP = new PasRuchu(new Polozenie(1, 0), "pas_prawy", sygnalizacjaTestowaP, "gora",
                1000, rogatkaTestowaP);

        Czujnik czujnikTestowySBL1D = new Czujnik(new Polozenie(-2500,-2), "czujnik_SBL1_tor_dolny");
        Swiatlo semaforTestowySBL1D = new Swiatlo(new Polozenie(-2499,-2), "semafor_SBL1_tor_dolny");

        Czujnik czujnikTestowySBL2D = new Czujnik(new Polozenie(-1900,-2), "czujnik_SBL2_tor_dolny");
        Swiatlo semaforTestowySBL2D = new Swiatlo(new Polozenie(-1901,-2), "semafor_SBL2_tor_dolny");

        Czujnik czujnikTestowySSP1D = new Czujnik(new Polozenie(-1800,-2), "czujnik_SSP1_tor_dolny");
        Swiatlo semaforTestowySSPD = new Swiatlo(new Polozenie(-1500, -2), "semafor_SSP_tor_dolny");

        Czujnik czujnikTestowySSP2D = new Czujnik(new Polozenie(10,   -2), "czujnik_SSP2_tor_dolny");

        Swiatlo semaforTestowySBL3D = new Swiatlo(new Polozenie(1500, -2), "semafor_SBL3_tor_dolny");
        Czujnik czujnikTestowySBL3D = new Czujnik(new Polozenie(1501, -2), "czujnik_SBL3_tor_dolny");

        Czujnik czujnikTestowySBL4D = new Czujnik(new Polozenie(2490, -2), "czujnik_SBL4_tor_dolny");

        Tor torTestowyD = new Tor(new Polozenie(0, -2),"tor_dolny", semaforTestowySSPD, "prawo", 5000,
               czujnikTestowySSP1D, czujnikTestowySSP2D, czujnikTestowySBL1D, czujnikTestowySBL2D, czujnikTestowySBL3D,
               czujnikTestowySBL4D, semaforTestowySBL1D, semaforTestowySBL2D, semaforTestowySBL3D);

        Swiatlo sygnalizacjaTestowaL = new Swiatlo(new Polozenie(-1, 4), "sygnalizacja_pas_lewy");
        Rogatka rogatkaTestowaL = new Rogatka(new Polozenie(-1, 3), "rogatka_pas_lewy", 5);
        PasRuchu pasTestowyL = new PasRuchu(new Polozenie(-1, 0), "pas_lewy", sygnalizacjaTestowaL, "dol", 1000, rogatkaTestowaL);

        Czujnik czujnikTestowySBL1G = new Czujnik(new Polozenie(2499,2), "czujnik_SBL1_tor_gorny");
        Swiatlo semaforTestowySBL1G = new Swiatlo(new Polozenie(2500,2), "semafor_SBL1_tor_gorny");

        Czujnik czujnikTestowySBL2G = new Czujnik(new Polozenie(1900,2), "czujnik_SBL2_tor_gorny");
        Swiatlo semaforTestowySBL2G = new Swiatlo(new Polozenie(1901,2), "semafor_SBL2_tor_gorny");

        Czujnik czujnikTestowySSP1G = new Czujnik(new Polozenie(1800,2), "czujnik_SSP1_tor_gorny");
        Swiatlo semaforTestowySSPG = new Swiatlo(new Polozenie(1500, 2), "semafor_SSP_tor_gorny");

        Czujnik czujnikTestowySSP2G = new Czujnik(new Polozenie(-10,   2), "czujnik_SSP2_tor_gorny");

        Swiatlo semaforTestowySBL3G = new Swiatlo(new Polozenie(-1500, 2), "semafor_SBL3_tor_gorny");
        Czujnik czujnikTestowySBL3G = new Czujnik(new Polozenie(-1501, 2), "czujnik_SBL3_tor_gorny");

        Czujnik czujnikTestowySBL4G = new Czujnik(new Polozenie(-2490, 2), "czujnik_SBL4_tor_gorny");

        Tor torTestowyG = new Tor(new Polozenie(0, 2),"tor_gorny", semaforTestowySSPG, "lewo", 5000,
               czujnikTestowySSP1G, czujnikTestowySSP2G, czujnikTestowySBL1G, czujnikTestowySBL2G, czujnikTestowySBL3G,
               czujnikTestowySBL4G, semaforTestowySBL1G, semaforTestowySBL2G, semaforTestowySBL3G);

        Rozklad rozkladTestowyGorny = new Rozklad();
        Rozklad rozkladTestowyDolny = new Rozklad();
        Przejazd przejazdTestowy = new Przejazd(new Polozenie(0,0), "przejazd",pasTestowyL, pasTestowyP,torTestowyG, torTestowyD, rozkladTestowyGorny, rozkladTestowyDolny,0);

//        Pociag pociagTestowy1 = new Pociag(120,650000,26,"444D",200, torTestowyD, przejazdTestowy);
//        Pociag pociagTestowy2 = new Pociag(200,80000,25,"555G",200, torTestowyG, przejazdTestowy);
        Pociag pociagTestowy3 = new Pociag(30,20000,38.89,"111D",70, torTestowyD, przejazdTestowy);
//        Pociag pociagTestowy4 = new Pociag(80,40000,27.67,"600D",250, torTestowyD, przejazdTestowy);
//        Pociag pociagTestowy5 = new Pociag(300,100000,22.22,"777G", 250, torTestowyG, przejazdTestowy);
//        Pociag pociagTestowy6 = new Pociag(600,200000,16.67,"333G",170, torTestowyG, przejazdTestowy);
        Pociag pociagTestowy7 = new Pociag(10,15000,44.44,"222D",80, torTestowyD, przejazdTestowy);
//
//        rozkladTestowyDolny.dodaj(pociagTestowy1);
//        rozkladTestowyGorny.dodaj(pociagTestowy2);
        rozkladTestowyDolny.dodaj(pociagTestowy3);
//        rozkladTestowyDolny.dodaj(pociagTestowy4);
//        rozkladTestowyGorny.dodaj(pociagTestowy5);
//        rozkladTestowyGorny.dodaj(pociagTestowy6);
        rozkladTestowyDolny.dodaj(pociagTestowy7);

//        przejazdTestowy.start();

        KontrolaRuchu kontrolaTestowa = new KontrolaRuchu(przejazdTestowy);
    }
}
