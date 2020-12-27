import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Swiatlo sygnalizacjaP = new Swiatlo(new Polozenie(1, -4), "sygnalizacja_pas_prawy");
        Rogatka rogatkaP = new Rogatka(new Polozenie(1, -3), "rogatka_pas_prawy", 5);
        PasRuchu pasP = new PasRuchu(new Polozenie(1, 0), "pas_prawy", sygnalizacjaP, "gora", 1000, rogatkaP);

        Semafor semaforSBL1D  = new Semafor(new Polozenie(-2500,-2), "semafor_SBL1_tor_dolny");
        Czujnik czujnikSBL1Dn = new Czujnik(new Polozenie(-2500,-2), "czujnik_SBL1n_tor_dolny");

        Semafor semaforSBL2D  = new Semafor(new Polozenie(-1300,-2), "semafor_SBL2_tor_dolny");
        Czujnik czujnikSBL2Dn = new Czujnik(new Polozenie(-1300,-2), "czujnik_SBL2n_tor_dolny");
        Czujnik czujnikSBL1Dz = new Czujnik(new Polozenie(-1300,-2), "czujnik_SBL1z_tor_dolny");

        Semafor semaforSSPD   = new Semafor(new Polozenie(-1000,-2), "semafor_SSP_tor_dolny");

        Semafor semaforSBL3D  = new Semafor(new Polozenie(-10,  -2), "semafor_SBL3_tor_dolny");
        Czujnik czujnikSBL3Dn = new Czujnik(new Polozenie(-10,  -2), "czujnik_SBL2n_tor_dolny");
        Czujnik czujnikSBL2Dz = new Czujnik(new Polozenie(-10,  -2), "czujnik_SBL3z_tor_dolny");

        Semafor semaforSBL4D  = new Semafor(new Polozenie(1700, -2), "semafor_SBL3_tor_dolny");
        Czujnik czujnikSBL4Dn = new Czujnik(new Polozenie(1700, -2), "czujnik_SBL2n_tor_dolny");
        Czujnik czujnikSBL3Dz = new Czujnik(new Polozenie(1700, -2), "czujnik_SBL3z_tor_dolny");

        Czujnik czujnikSBL4Dz = new Czujnik(new Polozenie(2500, -2), "czujnik_SBL3z_tor_dolny");

        ArrayList<Czujnik> czujnikiSBLnD = new ArrayList<>(Arrays.asList(czujnikSBL1Dn, czujnikSBL2Dn, czujnikSBL3Dn, czujnikSBL4Dn));
        ArrayList<Czujnik> czujnikiSBLzD = new ArrayList<>(Arrays.asList(czujnikSBL1Dz, czujnikSBL2Dz, czujnikSBL3Dz, czujnikSBL4Dz));
        ArrayList<Semafor> semaforySBLD = new ArrayList<>(Arrays.asList(semaforSBL1D, semaforSBL2D, semaforSBL3D, semaforSBL4D));

        Tor torD = new Tor(new Polozenie(0, -2),"tor_dolny", "prawo", 5000, semaforSSPD, czujnikiSBLnD, czujnikiSBLzD, semaforySBLD);

        Swiatlo sygnalizacjaL = new Swiatlo(new Polozenie(-1, 4), "sygnalizacja_pas_lewy");
        Rogatka rogatkaL = new Rogatka(new Polozenie(-1, 3), "rogatka_pas_lewy", 5);
        PasRuchu pasL = new PasRuchu(new Polozenie(-1, 0), "pas_lewy", sygnalizacjaL, "dol", 1000, rogatkaL);


        Semafor semaforSBL1G  = new Semafor(new Polozenie(2500, 2), "semafor_SBL1_tor_gorny");
        Czujnik czujnikSBL1Gn = new Czujnik(new Polozenie(2500, 2), "czujnik_SBL1n_tor_gorny");

        Semafor semaforSBL2G  = new Semafor(new Polozenie(1300, 2), "semafor_SBL2_tor_gorny");
        Czujnik czujnikSBL2Gn = new Czujnik(new Polozenie(1300, 2), "czujnik_SBL2n_tor_gorny");
        Czujnik czujnikSBL1Gz = new Czujnik(new Polozenie(1300, 2), "czujnik_SBL1z_tor_gorny");

        Semafor semaforSSPG  = new Semafor(new Polozenie(1000,  2), "semafor_SSP_tor_gorny");

        Semafor semaforSBL3G  = new Semafor(new Polozenie(10,   2), "semafor_SBL3_tor_gorny");
        Czujnik czujnikSBL3Gn = new Czujnik(new Polozenie(10,   2), "czujnik_SBL2n_tor_gorny");
        Czujnik czujnikSBL2Gz = new Czujnik(new Polozenie(10,   2), "czujnik_SBL3z_tor_gorny");

        Semafor semaforSBL4G  = new Semafor(new Polozenie(-1700,2), "semafor_SBL3_tor_gorny");
        Czujnik czujnikSBL4Gn = new Czujnik(new Polozenie(-1700,2), "czujnik_SBL2n_tor_gorny");
        Czujnik czujnikSBL3Gz = new Czujnik(new Polozenie(-1700,2), "czujnik_SBL3z_tor_gorny");

        Czujnik czujnikSBL4Gz = new Czujnik(new Polozenie(-2500,2), "czujnik_SBL3z_tor_gorny");

        ArrayList<Czujnik> czujnikiSBLnG = new ArrayList<>(Arrays.asList(czujnikSBL1Gn, czujnikSBL2Gn, czujnikSBL3Gn, czujnikSBL4Gn));
        ArrayList<Czujnik> czujnikiSBLzG = new ArrayList<>(Arrays.asList(czujnikSBL1Gz, czujnikSBL2Gz, czujnikSBL3Gz, czujnikSBL4Gz));
        ArrayList<Semafor> semaforySBLG = new ArrayList<>(Arrays.asList(semaforSBL1G, semaforSBL2G, semaforSBL3G, semaforSBL4G));

        Tor torG = new Tor(new Polozenie(0, 2),"tor_gorny", "lewo", 5000, semaforSSPG, czujnikiSBLnG, czujnikiSBLzG, semaforySBLG);

        Rozklad rozkladTestowyGorny = new Rozklad();
        Rozklad rozkladTestowyDolny = new Rozklad();
        Przejazd przejazd= new Przejazd(new Polozenie(0,0), "przejazd",pasL, pasP,torG, torD, rozkladTestowyGorny, rozkladTestowyDolny,0);

//        Pociag pociagTestowy1 = new Pociag(120,650000,26,"444D",200, torTestowyD, przejazdTestowy);
//        Pociag pociagTestowy2 = new Pociag(200,80000,25,"555G",200, torTestowyG, przejazdTestowy);
        Pociag pociagTestowy3 = new Pociag(30,20000,38.89,"111D",60, torD, przejazd);
//        Pociag pociagTestowy4 = new Pociag(80,40000,27.67,"600D",250, torTestowyD, przejazdTestowy);
//        Pociag pociagTestowy5 = new Pociag(300,100000,22.22,"777G", 250, torTestowyG, przejazdTestowy);
//        Pociag pociagTestowy6 = new Pociag(600,200000,16.67,"333G",170, torTestowyG, przejazdTestowy);
        Pociag pociagTestowy7 = new Pociag(10,15000,44.44,"222D",80, torD, przejazd);
//
//        rozkladTestowyDolny.dodaj(pociagTestowy1);
//        rozkladTestowyGorny.dodaj(pociagTestowy2);
        rozkladTestowyDolny.dodaj(pociagTestowy3);
//        rozkladTestowyDolny.dodaj(pociagTestowy4);
//        rozkladTestowyGorny.dodaj(pociagTestowy5);
//        rozkladTestowyGorny.dodaj(pociagTestowy6);
        rozkladTestowyDolny.dodaj(pociagTestowy7);

        przejazd.start();

        sleep(1000 * 50);
        rogatkaL.zamknij();
        rogatkaP.zamknij();

//        KontrolaRuchu kontrolaTestowa = new KontrolaRuchu(przejazdTestowy);
    }
}
