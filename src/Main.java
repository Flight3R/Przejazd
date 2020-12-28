import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Sygnalizator sygnalizacjaP = new Sygnalizator(new Polozenie(1, -4), "sygnalizacja_pas_prawy");
        Rogatka rogatkaP = new Rogatka(new Polozenie(1, -3), "rogatka_pas_prawy", 5);
        PasRuchu pasP = new PasRuchu(new Polozenie(1, 0), "pas_prawy", sygnalizacjaP, "gora", 1000, rogatkaP);

        Swiatlo semaforSBL1D  = new Swiatlo(new Polozenie(-2501,-2), "semafor_SBL1_tor_dolny");
        Czujnik czujnikSBL1Dn = new Czujnik(new Polozenie(-2501,-2), "czujnik_SBL1n_tor_dolny");

        Swiatlo semaforSBL2D  = new Swiatlo(new Polozenie(-1300,-2), "semafor_SBL2_tor_dolny");
        Czujnik czujnikSBL2Dn = new Czujnik(new Polozenie(-1300,-2), "czujnik_SBL2n_tor_dolny");
        Czujnik czujnikSBL1Dz = new Czujnik(new Polozenie(-1300,-2), "czujnik_SBL1z_tor_dolny");

        Czujnik czujnikSSPDn  = new Czujnik(new Polozenie(-1300,-2), "czujnik_SSPn_tor_dolny");

        Swiatlo semaforSSPD   = new Swiatlo(new Polozenie(-1000,-2), "semafor_SSP_tor_dolny");

        Czujnik czujnikSSPDz  = new Czujnik(new Polozenie(10,   -2), "czujnik_SSPz_tor_dolny");

        Swiatlo semaforSBL3D  = new Swiatlo(new Polozenie(1000, -2), "semafor_SBL3_tor_dolny");
        Czujnik czujnikSBL3Dn = new Czujnik(new Polozenie(1000, -2), "czujnik_SBL3n_tor_dolny");
        Czujnik czujnikSBL2Dz = new Czujnik(new Polozenie(1000, -2), "czujnik_SBL2z_tor_dolny");

        Czujnik czujnikSBL3Dz = new Czujnik(new Polozenie(2500, -2), "czujnik_SBL3z_tor_dolny");

        ArrayList<Czujnik> czujnikiSBLnD = new ArrayList<>(Arrays.asList(czujnikSBL1Dn, czujnikSBL2Dn, czujnikSBL3Dn));
        ArrayList<Czujnik> czujnikiSBLzD = new ArrayList<>(Arrays.asList(czujnikSBL1Dz, czujnikSBL2Dz, czujnikSBL3Dz));
        ArrayList<Swiatlo> semaforySBLD = new ArrayList<>(Arrays.asList(semaforSBL1D, semaforSBL2D, semaforSBL3D));

        Tor torD = new Tor(new Polozenie(0, -2),"tor_dolny", "prawo", 5000, semaforSSPD, czujnikSSPDn, czujnikSSPDz, czujnikiSBLnD, czujnikiSBLzD, semaforySBLD);

        Sygnalizator sygnalizacjaL = new Sygnalizator(new Polozenie(-1, 4), "sygnalizacja_pas_lewy");
        Rogatka rogatkaL = new Rogatka(new Polozenie(-1, 3), "rogatka_pas_lewy", 5);
        PasRuchu pasL = new PasRuchu(new Polozenie(-1, 0), "pas_lewy", sygnalizacjaL, "dol", 1000, rogatkaL);


        Swiatlo semaforSBL1G  = new Swiatlo(new Polozenie(2501, 2), "semafor_SBL1_tor_gorny");
        Czujnik czujnikSBL1Gn = new Czujnik(new Polozenie(2501, 2), "czujnik_SBL1n_tor_gorny");

        Swiatlo semaforSBL2G  = new Swiatlo(new Polozenie(1300, 2), "semafor_SBL2_tor_gorny");
        Czujnik czujnikSBL2Gn = new Czujnik(new Polozenie(1300, 2), "czujnik_SBL2n_tor_gorny");
        Czujnik czujnikSBL1Gz = new Czujnik(new Polozenie(1300, 2), "czujnik_SBL1z_tor_gorny");

        Czujnik czujnikSSPGn  = new Czujnik(new Polozenie(1300, 2), "czujnik_SSPn_tor_gorny");

        Swiatlo semaforSSPG   = new Swiatlo(new Polozenie(1000, 2), "semafor_SSP_tor_gorny");

        Czujnik czujnikSSPGz  = new Czujnik(new Polozenie(-10,  2), "czujnik_SSPz_tor_gorny");

        Swiatlo semaforSBL3G  = new Swiatlo(new Polozenie(-1000,2), "semafor_SBL3_tor_gorny");
        Czujnik czujnikSBL3Gn = new Czujnik(new Polozenie(-1000,2), "czujnik_SBL3n_tor_gorny");
        Czujnik czujnikSBL2Gz = new Czujnik(new Polozenie(-1000,2), "czujnik_SBL2z_tor_gorny");

        Czujnik czujnikSBL3Gz = new Czujnik(new Polozenie(-2500,2), "czujnik_SBL3z_tor_gorny");

        ArrayList<Czujnik> czujnikiSBLnG = new ArrayList<>(Arrays.asList(czujnikSBL1Gn, czujnikSBL2Gn, czujnikSBL3Gn));
        ArrayList<Czujnik> czujnikiSBLzG = new ArrayList<>(Arrays.asList(czujnikSBL1Gz, czujnikSBL2Gz, czujnikSBL3Gz));
        ArrayList<Swiatlo> semaforySBLG = new ArrayList<>(Arrays.asList(semaforSBL1G, semaforSBL2G, semaforSBL3G));

        Tor torG = new Tor(new Polozenie(0, 2),"tor_gorny", "lewo", 5000, semaforSSPG, czujnikSSPGn, czujnikSSPGz, czujnikiSBLnG, czujnikiSBLzG, semaforySBLG);

        Rozklad rozkladTestowyGorny = new Rozklad();
        Rozklad rozkladTestowyDolny = new Rozklad();
        Przejazd przejazd= new Przejazd(new Polozenie(0,0), "przejazd",pasL, pasP,torG, torD, rozkladTestowyGorny, rozkladTestowyDolny,0);

        Pociag pociagTestowy1 = new Pociag(120,50000,27,"444D",60, torD, przejazd);
//        Pociag pociagTestowy2 = new Pociag(200,80000,25,"555G",200, torTestowyG, przejazdTestowy);
        Pociag pociagTestowy3 = new Pociag(50,20000,38.89,"111D",50, torD, przejazd);
//        Pociag pociagTestowy4 = new Pociag(80,40000,27.67,"600D",250, torTestowyD, przejazdTestowy);
//        Pociag pociagTestowy5 = new Pociag(300,100000,22.22,"777G", 250, torTestowyG, przejazdTestowy);
//        Pociag pociagTestowy6 = new Pociag(600,200000,16.67,"333G",170, torTestowyG, przejazdTestowy);
        Pociag pociagTestowy7 = new Pociag(15,15000,44.44,"222D",70, torD, przejazd);
//
        rozkladTestowyDolny.dodaj(pociagTestowy1);
//        rozkladTestowyGorny.dodaj(pociagTestowy2);
        rozkladTestowyDolny.dodaj(pociagTestowy3);
//        rozkladTestowyDolny.dodaj(pociagTestowy4);
//        rozkladTestowyGorny.dodaj(pociagTestowy5);
//        rozkladTestowyGorny.dodaj(pociagTestowy6);
        rozkladTestowyDolny.dodaj(pociagTestowy7);

        przejazd.start();

//        rogatkaL.zamknij();
//        rogatkaP.zamknij();
        
    }
}
