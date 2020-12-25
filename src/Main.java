import java.util.ArrayList;

import static java.lang.Thread.activeCount;
import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Swiatlo sygnalizacjaTestowaP = new Swiatlo(new Polozenie(1, -4), "sygnalizacja_pas_prawy");
        Rogatka rogatkaTestowaP = new Rogatka(new Polozenie(1, -3), "rogatka_pas_prawy", 5);
        PasRuchu pasTestowyP = new PasRuchu(new Polozenie(1, 0), "pas_prawy", sygnalizacjaTestowaP, "gora", 1000, rogatkaTestowaP);
        Swiatlo sspTestoweD = new Swiatlo(new Polozenie(-1500, -2), "SSP_tor_dolny");
        Czujnik czujnikTestowy1D = new Czujnik(new Polozenie(-1800, -2), "czujnik_przed_tor_dolny");
        Czujnik czujnikTestowy2D = new Czujnik(new Polozenie(10, -2), "czujnik_za_tor_dolny");
        Czujnik czujnikTestowySBL1D = new Czujnik(new Polozenie(-2500, -2), "czujnik_sbl_przed_tor_dolny");
        Czujnik czujnikTestowySBL2D = new Czujnik(new Polozenie(2500, -2), "czujnik_sbl_za_tor_dolny");
        Tor torTestowyD = new Tor(new Polozenie(0, -2), "tor_dolny", sspTestoweD, "prawo", 5000, czujnikTestowy1D, czujnikTestowy2D,czujnikTestowySBL1D,czujnikTestowySBL2D);

        Swiatlo sygnalizacjaTestowaL = new Swiatlo(new Polozenie(-1, 4), "sygnalizacja_pas_lewy");
        Rogatka rogatkaTestowaL = new Rogatka(new Polozenie(-1, 3), "rogatka_pas_lewy", 5);
        PasRuchu pasTestowyL = new PasRuchu(new Polozenie(-1, 0), "pas_lewy", sygnalizacjaTestowaL, "dol", 1000, rogatkaTestowaL);
        Swiatlo sspTestoweG = new Swiatlo(new Polozenie(1500, 2), "SSP_tor_gorny");
        Czujnik czujnikTestowy1G = new Czujnik(new Polozenie(1800, 2), "czujnik_przed_tor_gorny");
        Czujnik czujnikTestowy2G = new Czujnik(new Polozenie(-10, 2), "czujnik_za_tor_gorny");
        Czujnik czujnikTestowySBL1G = new Czujnik(new Polozenie(2500, 2), "czujnik_sbl_przed_tor_gorny");
        Czujnik czujnikTestowySBL2G = new Czujnik(new Polozenie(-2500, 2), "czujnik_sbl_za_tor_gorny");
        Tor torTestowyG = new Tor(new Polozenie(0, 2), "tor_dolny", sspTestoweG, "lewo", 5000, czujnikTestowy1G, czujnikTestowy2G, czujnikTestowySBL1G, czujnikTestowySBL2G);

        Rozklad rozkladTestowyGorny = new Rozklad();
        Rozklad rozkladTestowyDolny = new Rozklad();
        Przejazd przejazdTestowy = new Przejazd(new Polozenie(0,0), "przejazd",pasTestowyL, pasTestowyP,torTestowyG, torTestowyD, rozkladTestowyGorny, rozkladTestowyDolny,0);

        Pociag pociagTestowy1 = new Pociag(120,650000,26,"444",200, torTestowyD, przejazdTestowy);
        Pociag pociagTestowy2 = new Pociag(200,80000,25,"555",200, torTestowyG, przejazdTestowy);
        Pociag pociagTestowy3 = new Pociag(30,20000,38.89,"222",120, torTestowyD, przejazdTestowy);
        Pociag pociagTestowy4 = new Pociag(80,40000,27.67,"600",250, torTestowyD, przejazdTestowy);
        Pociag pociagTestowy5 = new Pociag(300,100000,22.22,"777", 250, torTestowyG, przejazdTestowy);
        Pociag pociagTestowy6 = new Pociag(600,200000,16.67,"333",150, torTestowyG, przejazdTestowy);
        Pociag pociagTestowy7 = new Pociag(10,15000,44.44,"111",60, torTestowyD, przejazdTestowy);

        rozkladTestowyDolny.dodaj(pociagTestowy1);
        rozkladTestowyGorny.dodaj(pociagTestowy2);
        rozkladTestowyDolny.dodaj(pociagTestowy3);
        rozkladTestowyDolny.dodaj(pociagTestowy4);
        rozkladTestowyGorny.dodaj(pociagTestowy5);
        rozkladTestowyGorny.dodaj(pociagTestowy6);
        rozkladTestowyDolny.dodaj(pociagTestowy7);

//        przejazdTestowy.start();

        KontrolaRuchu kontrolaTestowa = new KontrolaRuchu(przejazdTestowy);
    }
}
