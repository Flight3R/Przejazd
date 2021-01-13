import lokacja.Polozenie;
import obslugaPrzejazdu.KontrolaRuchu;
import obslugaPrzejazdu.Przejazd;
import podlozaTransportowe.PasRuchu;
import podlozaTransportowe.Tor;
import pojazdy.Pociag;
import urzadzeniaKolejowe.Czujnik;
import urzadzeniaKolejowe.Semafor;
import urzadzeniaUliczne.Rogatka;
import urzadzeniaUliczne.Sygnalizator;
import zadania.PanelSterowania;
import zadania.PanelSytuacji;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ImageIcon s1P = new ImageIcon("src/grafiki/s1_prawo.png");
        ImageIcon s2P = new ImageIcon("src/grafiki/s2_prawo.png");
        ImageIcon s1L = new ImageIcon("src/grafiki/s1_lewo.png");
        ImageIcon s2L = new ImageIcon("src/grafiki/s2_lewo.png");
        ImageIcon osp1P = new ImageIcon("src/grafiki/osp1_prawo.png");
        ImageIcon osp2P = new ImageIcon("src/grafiki/osp2_prawo.png");
        ImageIcon osp1L = new ImageIcon("src/grafiki/osp1_lewo.png");
        ImageIcon osp2L = new ImageIcon("src/grafiki/osp2_lewo.png");
        ImageIcon sygnG = new ImageIcon("src/grafiki/sygnalizacja_gora.png");
        ImageIcon sygnD = new ImageIcon("src/grafiki/sygnalizacja_dol.png");
        ImageIcon sygnG1 = new ImageIcon("src/grafiki/sygnalizacja1_gora.png");
        ImageIcon sygnD1 = new ImageIcon("src/grafiki/sygnalizacja1_dol.png");
        ImageIcon sygnG2 = new ImageIcon("src/grafiki/sygnalizacja2_gora.png");
        ImageIcon sygnD2 = new ImageIcon("src/grafiki/sygnalizacja2_dol.png");

        ImageIcon czujnik = new ImageIcon("src/grafiki/czujnik.png");

        ImageIcon rogatka1 = new ImageIcon("src/grafiki/rogatka_zamknieta.png");
        ImageIcon rogatka2 = new ImageIcon("src/grafiki/rogatka_otwarta.png");

        ImageIcon tor = new ImageIcon("src/grafiki/tor.png");
        ImageIcon pas = new ImageIcon("src/grafiki/pas.png");

        ImageIcon pociag300P = new ImageIcon("src/grafiki/pociag_300_prawo.png");
        ImageIcon pociag300L = new ImageIcon("src/grafiki/pociag_300_lewo.png");

        ImageIcon przej = new ImageIcon("src/grafiki/przejazd.png");

        // GENEROWANIE CZUJNIKOW I SEMAFORÓW - TOR GORNY
        Semafor semaforSBL1G  = new Semafor(new Polozenie(2490, 40+15), "semafor_SBL1_tor_gorny", s2L, s1L);
        Czujnik czujnikSBL1Gn = new Czujnik(new Polozenie(2490, 40), "czujnik_SBL1n_tor_gorny", czujnik);

        Semafor semaforSBL2G  = new Semafor(new Polozenie(1300, 40+15), "semafor_SBL2_tor_gorny", s2L, s1L);
        Czujnik czujnikSBL2Gn = new Czujnik(new Polozenie(1300, 40), "czujnik_SBL2n_tor_gorny", czujnik);
        Czujnik czujnikSBL1Gz = new Czujnik(new Polozenie(1290, 40), "czujnik_SBL1z_tor_gorny", czujnik);

        Czujnik czujnikSSPGn  = new Czujnik(new Polozenie(1280, 40), "czujnik_SSPn_tor_gorny", czujnik);

        Semafor tarczaSSPG   = new Semafor(new Polozenie(1000, 40+15), "tarcza_SSP_tor_gorny", osp2L, osp1L);

        Czujnik czujnikSSPGz  = new Czujnik(new Polozenie(-40,  40), "czujnik_SSPz_tor_gorny", czujnik);

        Semafor semaforSBL3G  = new Semafor(new Polozenie(-1000,40+15), "semafor_SBL3_tor_gorny", s2L, s1L);
        Czujnik czujnikSBL3Gn = new Czujnik(new Polozenie(-1000,40), "czujnik_SBL3n_tor_gorny", czujnik);
        Czujnik czujnikSBL2Gz = new Czujnik(new Polozenie(-1010,40), "czujnik_SBL2z_tor_gorny", czujnik);

        Czujnik czujnikSBL3Gz = new Czujnik(new Polozenie(-2500,40), "czujnik_SBL3z_tor_gorny", czujnik);

        ArrayList<Czujnik> czujnikiSBLnG = new ArrayList<>(Arrays.asList(czujnikSBL1Gn, czujnikSBL2Gn, czujnikSBL3Gn));
        ArrayList<Czujnik> czujnikiSBLzG = new ArrayList<>(Arrays.asList(czujnikSBL1Gz, czujnikSBL2Gz, czujnikSBL3Gz));
        ArrayList<Semafor> semaforySBLG = new ArrayList<>(Arrays.asList(semaforSBL1G, semaforSBL2G, semaforSBL3G));

        Tor torG = new Tor(new Polozenie(0, 40),"tor_gorny", "lewo", 5010, tarczaSSPG, czujnikSSPGn, czujnikSSPGz, czujnikiSBLnG, czujnikiSBLzG, semaforySBLG, tor);

        // GENEROWANIE CZUJNIKOW I SEMAFORÓW - TOR DOLNY
        Semafor semaforSBL1D  = new Semafor(new Polozenie(-2490,-40-15), "semafor_SBL1_tor_dolny", s2P, s1P);
        Czujnik czujnikSBL1Dn = new Czujnik(new Polozenie(-2490,-40), "czujnik_SBL1n_tor_dolny", czujnik);

        Semafor semaforSBL2D  = new Semafor(new Polozenie(-1300,-40-15), "semafor_SBL2_tor_dolny", s2P, s1P);
        Czujnik czujnikSBL2Dn = new Czujnik(new Polozenie(-1300,-40), "czujnik_SBL2n_tor_dolny", czujnik);
        Czujnik czujnikSBL1Dz = new Czujnik(new Polozenie(-1290,-40), "czujnik_SBL1z_tor_dolny", czujnik);

        Czujnik czujnikSSPDn  = new Czujnik(new Polozenie(-1280,-40), "czujnik_SSPn_tor_dolny", czujnik);

        Semafor tarczaSSPD   = new Semafor(new Polozenie(-1000,-40-15), "tarcza_SSP_tor_dolny", osp2P, osp1P);

        Czujnik czujnikSSPDz  = new Czujnik(new Polozenie(40,   -40), "czujnik_SSPz_tor_dolny", czujnik);

        Semafor semaforSBL3D  = new Semafor(new Polozenie(1000, -40-15), "semafor_SBL3_tor_dolny", s2P, s1P);
        Czujnik czujnikSBL3Dn = new Czujnik(new Polozenie(1000, -40), "czujnik_SBL3n_tor_dolny", czujnik);
        Czujnik czujnikSBL2Dz = new Czujnik(new Polozenie(1010, -40), "czujnik_SBL2z_tor_dolny", czujnik);

        Czujnik czujnikSBL3Dz = new Czujnik(new Polozenie(2500, -40), "czujnik_SBL3z_tor_dolny", czujnik);

        ArrayList<Czujnik> czujnikiSBLnD = new ArrayList<>(Arrays.asList(czujnikSBL1Dn, czujnikSBL2Dn, czujnikSBL3Dn));
        ArrayList<Czujnik> czujnikiSBLzD = new ArrayList<>(Arrays.asList(czujnikSBL1Dz, czujnikSBL2Dz, czujnikSBL3Dz));
        ArrayList<Semafor> semaforySBLD = new ArrayList<>(Arrays.asList(semaforSBL1D, semaforSBL2D, semaforSBL3D));

        Tor torD = new Tor(new Polozenie(0, -40),"tor_dolny", "prawo", 5010, tarczaSSPD, czujnikSSPDn, czujnikSSPDz, czujnikiSBLnD, czujnikiSBLzD, semaforySBLD, tor);

        ArrayList<Tor> listaTorow = new ArrayList<>(Arrays.asList(torG,torD));

        // GENEROWANIE SYGNALIZATORÓW I ROGATEK - PAS PRAWY I LEWY
        Sygnalizator sygnalizacjaP = new Sygnalizator(new Polozenie(30+20, -80), "sygnalizacja_pas_prawy", sygnG, sygnG1, sygnG2);
        Rogatka rogatkaP = new Rogatka(new Polozenie(30+20, -80), "rogatka_pas_prawy", 5, rogatka2, rogatka1);
        PasRuchu pasP = new PasRuchu(new Polozenie(30, 0), "pas_prawy", sygnalizacjaP, "gora", 1020, rogatkaP, pas);

        Sygnalizator sygnalizacjaL = new Sygnalizator(new Polozenie(-30-20, 80), "sygnalizacja_pas_lewy", sygnD, sygnD1, sygnD2);
        Rogatka rogatkaL = new Rogatka(new Polozenie(-30-20, 80), "rogatka_pas_lewy", 5, rogatka2, rogatka1);
        PasRuchu pasL = new PasRuchu(new Polozenie(-30, 0), "pas_lewy", sygnalizacjaL, "dol", 1020, rogatkaL, pas);

        ArrayList<PasRuchu> listaPasow = new ArrayList<>(Arrays.asList(pasL, pasP));

        // GENEROWANIE PRZEJAZDU
        Przejazd przejazd = new Przejazd(new Polozenie(0,0), "przejazd", listaPasow, listaTorow, 0, przej);

        // GENEROWANIE POCIĄGÓW
        Pociag pociagTestowy1 = new Pociag("1111-G",300,30000,50,20, torG, przejazd, pociag300L);
        Pociag pociagTestowy2 = new Pociag("2222-G",300,20000,50, 20, torG, przejazd, pociag300L);
        Pociag pociagTestowy3 = new Pociag("3333-D",320,50000,40,60, torD, przejazd, pociag300P);
        Pociag pociagTestowy4 = new Pociag("4444-D", 300,20000,70,30, torD, przejazd, pociag300P);

//        torD.getRozkladPociagow().dodaj(pociagTestowy3);
//        torD.getRozkladPociagow().dodaj(pociagTestowy4);
//        torG.getRozkladPociagow().dodaj(pociagTestowy1);
//        torG.getRozkladPociagow().dodaj(pociagTestowy2);
        przejazd.dodajDoRozkladu(pociagTestowy1);
        przejazd.dodajDoRozkladu(pociagTestowy2);
        przejazd.dodajDoRozkladu(pociagTestowy3);
        przejazd.dodajDoRozkladu(pociagTestowy4);


        KontrolaRuchu kontrolaRuchu = new KontrolaRuchu(przejazd, 8);

        JFrame panelSterowania = new PanelSterowania(przejazd);
        Thread panelSterowaniaThread = new Thread((Runnable) panelSterowania);
        panelSterowaniaThread.start();
        panelSterowania.pack();
        panelSterowania.setVisible(true);

        JFrame panelSytuacji = new PanelSytuacji(przejazd);
        Thread panelSytuacjiThread = new Thread((Runnable) panelSytuacji);
        panelSytuacjiThread.start();
        panelSytuacji.pack();
        panelSytuacji.setVisible(true);
    }
}
