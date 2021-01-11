import klasyAbstrakcyjne.obiektSymulacji;
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

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GUI extends Thread {
    private JPanel panel1;
    private JScrollPane obszar;
    private JPanel rozklad;
    private JPanel systemy;
    private JPanel lewy;
    private JPanel mapa;
    private JScrollBar scrollBarPionowy;
    private JScrollBar scrollBarPoziomy;
    int deltaX=2500;
    int deltaY=-500;

    {
    /*// GENEROWANIE CZUJNIKOW I SEMAFORÓW - TOR GORNY
        Semafor semaforSBL1G = new Semafor(new Polozenie(2501+deltaX, 2+deltaY), "semafor_SBL1_tor_gorny");
        Czujnik czujnikSBL1Gn = new Czujnik(new Polozenie(2501+deltaX, 2+deltaY), "czujnik_SBL1n_tor_gorny");

        Semafor semaforSBL2G = new Semafor(new Polozenie(1300+deltaX, 2+deltaY), "semafor_SBL2_tor_gorny");
        Czujnik czujnikSBL2Gn = new Czujnik(new Polozenie(1300+deltaX, 2+deltaY), "czujnik_SBL2n_tor_gorny");
        Czujnik czujnikSBL1Gz = new Czujnik(new Polozenie(1300+deltaX, 2+deltaY), "czujnik_SBL1z_tor_gorny");

        Czujnik czujnikSSPGn = new Czujnik(new Polozenie(1300+deltaX, 2+deltaY), "czujnik_SSPn_tor_gorny");

        Semafor tarczaSSPG = new Semafor(new Polozenie(1000+deltaX, 2+deltaY), "tarcza_SSP_tor_gorny");

        Czujnik czujnikSSPGz = new Czujnik(new Polozenie(-10+deltaX, 2+deltaY), "czujnik_SSPz_tor_gorny");

        Semafor semaforSBL3G = new Semafor(new Polozenie(-1000+deltaX, 2+deltaY), "semafor_SBL3_tor_gorny");
        Czujnik czujnikSBL3Gn = new Czujnik(new Polozenie(-1000+deltaX, 2+deltaY), "czujnik_SBL3n_tor_gorny");
        Czujnik czujnikSBL2Gz = new Czujnik(new Polozenie(-1000+deltaX, 2+deltaY), "czujnik_SBL2z_tor_gorny");

        Czujnik czujnikSBL3Gz = new Czujnik(new Polozenie(-2500+deltaX, 2+deltaY), "czujnik_SBL3z_tor_gorny");

        ArrayList<Czujnik> czujnikiSBLnG = new ArrayList<>(Arrays.asList(czujnikSBL1Gn, czujnikSBL2Gn, czujnikSBL3Gn));
        ArrayList<Czujnik> czujnikiSBLzG = new ArrayList<>(Arrays.asList(czujnikSBL1Gz, czujnikSBL2Gz, czujnikSBL3Gz));
        ArrayList<Semafor> semaforySBLG = new ArrayList<>(Arrays.asList(semaforSBL1G, semaforSBL2G, semaforSBL3G));

        Tor torG = new Tor(new Polozenie(0+deltaX, 2+deltaY), "tor_gorny", "lewo", 5010, tarczaSSPG, czujnikSSPGn, czujnikSSPGz, czujnikiSBLnG, czujnikiSBLzG, semaforySBLG);

        // GENEROWANIE CZUJNIKOW I SEMAFORÓW - TOR DOLNY
        Semafor semaforSBL1D = new Semafor(new Polozenie(-2501+deltaX, -2+deltaY), "semafor_SBL1_tor_dolny");
        Czujnik czujnikSBL1Dn = new Czujnik(new Polozenie(-2501+deltaX, -2+deltaY), "czujnik_SBL1n_tor_dolny");

        Semafor semaforSBL2D = new Semafor(new Polozenie(-1300+deltaX, -2+deltaY), "semafor_SBL2_tor_dolny");
        Czujnik czujnikSBL2Dn = new Czujnik(new Polozenie(-1300+deltaX, -2+deltaY), "czujnik_SBL2n_tor_dolny");
        Czujnik czujnikSBL1Dz = new Czujnik(new Polozenie(-1300+deltaX, -2+deltaY), "czujnik_SBL1z_tor_dolny");

        Czujnik czujnikSSPDn = new Czujnik(new Polozenie(-1300+deltaX, -2+deltaY), "czujnik_SSPn_tor_dolny");

        Semafor tarczaSSPD = new Semafor(new Polozenie(-1000+deltaX, -2+deltaY), "tarcza_SSP_tor_dolny");

        Czujnik czujnikSSPDz = new Czujnik(new Polozenie(10+deltaX, -2+deltaY), "czujnik_SSPz_tor_dolny");

        Semafor semaforSBL3D = new Semafor(new Polozenie(1000+deltaX, -2+deltaY), "semafor_SBL3_tor_dolny");
        Czujnik czujnikSBL3Dn = new Czujnik(new Polozenie(1000+deltaX, -2+deltaY), "czujnik_SBL3n_tor_dolny");
        Czujnik czujnikSBL2Dz = new Czujnik(new Polozenie(1000+deltaX, -2+deltaY), "czujnik_SBL2z_tor_dolny");

        Czujnik czujnikSBL3Dz = new Czujnik(new Polozenie(2500+deltaX, -2+deltaY), "czujnik_SBL3z_tor_dolny");

        ArrayList<Czujnik> czujnikiSBLnD = new ArrayList<>(Arrays.asList(czujnikSBL1Dn, czujnikSBL2Dn, czujnikSBL3Dn));
        ArrayList<Czujnik> czujnikiSBLzD = new ArrayList<>(Arrays.asList(czujnikSBL1Dz, czujnikSBL2Dz, czujnikSBL3Dz));
        ArrayList<Semafor> semaforySBLD = new ArrayList<>(Arrays.asList(semaforSBL1D, semaforSBL2D, semaforSBL3D));

        Tor torD = new Tor(new Polozenie(0+deltaX, -2+deltaY), "tor_dolny", "prawo", 5010, tarczaSSPD, czujnikSSPDn, czujnikSSPDz, czujnikiSBLnD, czujnikiSBLzD, semaforySBLD);

        ArrayList<Tor> listaTorow = new ArrayList<>(Arrays.asList(torG, torD));

        // GENEROWANIE SYGNALIZATORÓW I ROGATEK - PAS PRAWY I LEWY
        Sygnalizator sygnalizacjaP = new Sygnalizator(new Polozenie(1+deltaX, -4+deltaY), "sygnalizacja_pas_prawy");
        Rogatka rogatkaP = new Rogatka(new Polozenie(1+deltaX, -3+deltaY), "rogatka_pas_prawy", 5);
        PasRuchu pasP = new PasRuchu(new Polozenie(1+deltaX, 0+deltaY), "pas_prawy", sygnalizacjaP, "gora", 501, rogatkaP);

        Sygnalizator sygnalizacjaL = new Sygnalizator(new Polozenie(-1+deltaX, 4+deltaY), "sygnalizacja_pas_lewy");
        Rogatka rogatkaL = new Rogatka(new Polozenie(-1+deltaX, 3+deltaY), "rogatka_pas_lewy", 5);
        PasRuchu pasL = new PasRuchu(new Polozenie(-1+deltaX, 0+deltaY), "pas_lewy", sygnalizacjaL, "dol", 501, rogatkaL);

        ArrayList<PasRuchu> listaPasow = new ArrayList<>(Arrays.asList(pasL, pasP));

        // GENEROWANIE PRZEJAZDU
        Przejazd przejazd = new Przejazd(new Polozenie(0+deltaX, 0+deltaY), "przejazd", listaPasow, listaTorow, 0);

        // GENEROWANIE POCIĄGÓW
        Pociag pociagTestowy1 = new Pociag("1111-G", 200, 30000, 25, 95, torG, przejazd);
        Pociag pociagTestowy2 = new Pociag("2222-G", 100, 20000, 27, 120, torG, przejazd);
        Pociag pociagTestowy3 = new Pociag("3333-D", 120, 50000, 27, 60, torD, przejazd);
        Pociag pociagTestowy4 = new Pociag("4444-D", 50, 20000, 38.89, 50, torD, przejazd);

        KontrolaRuchu kontrola = new KontrolaRuchu(przejazd, 3);
     // OBIEKTY SYMULACJI*/ }
    Icon s1P = new ImageIcon("src/grafiki/s1_prawo.png");
    Icon s2P = new ImageIcon("src/grafiki/s2_prawo.png");
    Icon s1L = new ImageIcon("src/grafiki/s1_lewo.png");
    Icon s2L = new ImageIcon("src/grafiki/s2_lewo.png");
    Icon osp1P = new ImageIcon("src/grafiki/osp1_prawo.png");
    Icon osp2P = new ImageIcon("src/grafiki/osp2_prawo.png");
    Icon osp1L = new ImageIcon("src/grafiki/osp1_lewo.png");
    Icon osp2L = new ImageIcon("src/grafiki/osp2_lewo.png");
    Icon sygnG = new ImageIcon("src/grafiki/sygnalizacja_gora.png");
    Icon sygnD = new ImageIcon("src/grafiki/sygnalizacja_dol.png");
    Icon sygnG1 = new ImageIcon("src/grafiki/sygnalizacja1_gora.png");
    Icon sygnD1 = new ImageIcon("src/grafiki/sygnalizacja1_dol.png");
    Icon sygnG2 = new ImageIcon("src/grafiki/sygnalizacja2_gora.png");
    Icon sygnD2 = new ImageIcon("src/grafiki/sygnalizacja2_dol.png");//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    Icon czujnik = new ImageIcon("src/grafiki/czujnik.png");

    Icon rogatka1 = new ImageIcon("src/grafiki/rogatka_zamknieta.png");
    Icon rogatka2 = new ImageIcon("src/grafiki/rogatka_otwarta.png");

    Icon tor = new ImageIcon("src/grafiki/tor.png");
    Icon pas = new ImageIcon("src/grafiki/pas.png");

    Icon pociag300P = new ImageIcon("src/grafiki/pociag_300_prawo.png");
    Icon pociag300L = new ImageIcon("src/grafiki/pociag_300_lewo.png");

    Icon autoG = new ImageIcon("src/grafiki/auto_gora.png");
    Icon autoD = new ImageIcon("src/grafiki/auto_dol.png");

    Icon przej = new ImageIcon("src/grafiki/przejazd.png");

    // GENEROWANIE CZUJNIKOW I SEMAFORÓW - TOR GORNY
    Semafor semaforSBL1G  = new Semafor(new Polozenie(2501, 2), "semafor_SBL1_tor_gorny", s2L);
    Czujnik czujnikSBL1Gn = new Czujnik(new Polozenie(2501, 2), "czujnik_SBL1n_tor_gorny", czujnik);

    Semafor semaforSBL2G  = new Semafor(new Polozenie(1300, 2), "semafor_SBL2_tor_gorny", s2L);
    Czujnik czujnikSBL2Gn = new Czujnik(new Polozenie(1300, 2), "czujnik_SBL2n_tor_gorny", czujnik);
    Czujnik czujnikSBL1Gz = new Czujnik(new Polozenie(1300, 2), "czujnik_SBL1z_tor_gorny", czujnik);

    Czujnik czujnikSSPGn  = new Czujnik(new Polozenie(1300, 2), "czujnik_SSPn_tor_gorny", czujnik);

    Semafor tarczaSSPG   = new Semafor(new Polozenie(1000, 2), "tarcza_SSP_tor_gorny", osp2L);

    Czujnik czujnikSSPGz  = new Czujnik(new Polozenie(-10,  2), "czujnik_SSPz_tor_gorny", czujnik);

    Semafor semaforSBL3G  = new Semafor(new Polozenie(-1000,2), "semafor_SBL3_tor_gorny", s2L);
    Czujnik czujnikSBL3Gn = new Czujnik(new Polozenie(-1000,2), "czujnik_SBL3n_tor_gorny", czujnik);
    Czujnik czujnikSBL2Gz = new Czujnik(new Polozenie(-1000,2), "czujnik_SBL2z_tor_gorny", czujnik);

    Czujnik czujnikSBL3Gz = new Czujnik(new Polozenie(-2500,2), "czujnik_SBL3z_tor_gorny", czujnik);

    ArrayList<Czujnik> czujnikiSBLnG = new ArrayList<>(Arrays.asList(czujnikSBL1Gn, czujnikSBL2Gn, czujnikSBL3Gn));
    ArrayList<Czujnik> czujnikiSBLzG = new ArrayList<>(Arrays.asList(czujnikSBL1Gz, czujnikSBL2Gz, czujnikSBL3Gz));
    ArrayList<Semafor> semaforySBLG = new ArrayList<>(Arrays.asList(semaforSBL1G, semaforSBL2G, semaforSBL3G));

    Tor torG = new Tor(new Polozenie(0, 2),"tor_gorny", "lewo", 5010, tarczaSSPG, czujnikSSPGn, czujnikSSPGz, czujnikiSBLnG, czujnikiSBLzG, semaforySBLG, tor);

    // GENEROWANIE CZUJNIKOW I SEMAFORÓW - TOR DOLNY
    Semafor semaforSBL1D  = new Semafor(new Polozenie(-2501,-2), "semafor_SBL1_tor_dolny", s2P);
    Czujnik czujnikSBL1Dn = new Czujnik(new Polozenie(-2501,-2), "czujnik_SBL1n_tor_dolny", czujnik);

    Semafor semaforSBL2D  = new Semafor(new Polozenie(-1300,-2), "semafor_SBL2_tor_dolny", s2P);
    Czujnik czujnikSBL2Dn = new Czujnik(new Polozenie(-1300,-2), "czujnik_SBL2n_tor_dolny", czujnik);
    Czujnik czujnikSBL1Dz = new Czujnik(new Polozenie(-1300,-2), "czujnik_SBL1z_tor_dolny", czujnik);

    Czujnik czujnikSSPDn  = new Czujnik(new Polozenie(-1300,-2), "czujnik_SSPn_tor_dolny", czujnik);

    Semafor tarczaSSPD   = new Semafor(new Polozenie(-1000,-2), "tarcza_SSP_tor_dolny", osp2P);

    Czujnik czujnikSSPDz  = new Czujnik(new Polozenie(10,   -2), "czujnik_SSPz_tor_dolny", czujnik);

    Semafor semaforSBL3D  = new Semafor(new Polozenie(1000, -2), "semafor_SBL3_tor_dolny", s2P);
    Czujnik czujnikSBL3Dn = new Czujnik(new Polozenie(1000, -2), "czujnik_SBL3n_tor_dolny", czujnik);
    Czujnik czujnikSBL2Dz = new Czujnik(new Polozenie(1000, -2), "czujnik_SBL2z_tor_dolny", czujnik);

    Czujnik czujnikSBL3Dz = new Czujnik(new Polozenie(2500, -2), "czujnik_SBL3z_tor_dolny", czujnik);

    ArrayList<Czujnik> czujnikiSBLnD = new ArrayList<>(Arrays.asList(czujnikSBL1Dn, czujnikSBL2Dn, czujnikSBL3Dn));
    ArrayList<Czujnik> czujnikiSBLzD = new ArrayList<>(Arrays.asList(czujnikSBL1Dz, czujnikSBL2Dz, czujnikSBL3Dz));
    ArrayList<Semafor> semaforySBLD = new ArrayList<>(Arrays.asList(semaforSBL1D, semaforSBL2D, semaforSBL3D));

    Tor torD = new Tor(new Polozenie(0, -2),"tor_dolny", "prawo", 5010, tarczaSSPD, czujnikSSPDn, czujnikSSPDz, czujnikiSBLnD, czujnikiSBLzD, semaforySBLD, tor);

    ArrayList<Tor> listaTorow = new ArrayList<>(Arrays.asList(torG,torD));

    // GENEROWANIE SYGNALIZATORÓW I ROGATEK - PAS PRAWY I LEWY
    Sygnalizator sygnalizacjaP = new Sygnalizator(new Polozenie(1, -4), "sygnalizacja_pas_prawy", sygnD);
    Rogatka rogatkaP = new Rogatka(new Polozenie(1, -3), "rogatka_pas_prawy", 5, rogatka2);
    PasRuchu pasP = new PasRuchu(new Polozenie(1, 0), "pas_prawy", sygnalizacjaP, "gora", 501, rogatkaP, pas);

    Sygnalizator sygnalizacjaL = new Sygnalizator(new Polozenie(-1, 4), "sygnalizacja_pas_lewy", sygnG);
    Rogatka rogatkaL = new Rogatka(new Polozenie(-1, 3), "rogatka_pas_lewy", 5, rogatka2);
    PasRuchu pasL = new PasRuchu(new Polozenie(-1, 0), "pas_lewy", sygnalizacjaL, "dol", 501, rogatkaL, pas);

    ArrayList<PasRuchu> listaPasow = new ArrayList<>(Arrays.asList(pasL, pasP));

    // GENEROWANIE PRZEJAZDU
    Przejazd przejazd= new Przejazd(new Polozenie(0,0), "przejazd", listaPasow, listaTorow, 0, przej);

    // GENEROWANIE POCIĄGÓW
//    Pociag pociagTestowy1 = new Pociag("1111-G",200,30000,25,95, torG, przejazd);
//    Pociag pociagTestowy2 = new Pociag("2222-G",100,20000,27, 120, torG, przejazd);
//    Pociag pociagTestowy3 = new Pociag("3333-D",120,50000,27,60, torD, przejazd);
    Pociag pociagTestowy4 = new Pociag("4444-D", 300,20000,38.89,50, torD, przejazd, pociag300P);

    KontrolaRuchu kontrola = new KontrolaRuchu(przejazd, 3);

    ArrayList<obiektSymulacji> lista = new ArrayList(Arrays.asList(semaforSBL1G,
            czujnikSBL1Gn,
            semaforSBL2G ,
            czujnikSBL2Gn,
            czujnikSBL1Gz,
            czujnikSSPGn,
            tarczaSSPG ,
            czujnikSSPGz,
            semaforSBL3G,
            czujnikSBL3Gn,
            czujnikSBL2Gz,
            czujnikSBL3Gz,
            torG ,
            semaforSBL1D,
            czujnikSBL1Dn,
            semaforSBL2D,
            czujnikSBL2Dn,
            czujnikSBL1Dz,
            czujnikSSPDn,
            tarczaSSPD,
            czujnikSSPDz,
            semaforSBL3D,
            czujnikSBL3Dn,
            czujnikSBL2Dz,
            czujnikSBL3Dz,
            torD ,
            sygnalizacjaP ,
            rogatkaP,
            pasP,
            sygnalizacjaL,
            rogatkaL,
            pasL,
            przejazd,
            pociagTestowy4));


    private void przygotuj() {
        for (obiektSymulacji obiekt : lista) {
            obiekt.getLabel().setLocation((int)(obiekt.getPolozenie().getX()+deltaX), -(int)(obiekt.getPolozenie().getY()+deltaY));
            mapa.add(obiekt.getLabel());
        }
    }

    public GUI() {


        obszar.getViewport().setViewPosition(new Point(obszar.getViewport().getViewSize().width/2-640, obszar.getViewport().getViewSize().height/2-360));


//        torG.getRozkladPociagow().dodaj(pociagTestowy1);
//        torG.getRozkladPociagow().dodaj(pociagTestowy2);
//        torD.getRozkladPociagow().dodaj(pociagTestowy3);
        torD.getRozkladPociagow().dodaj(pociagTestowy4);

        start();
    }
    public void odswiez() {
        for (obiektSymulacji obiekt : lista) {
            obiekt.getLabel().setLocation((int)(obiekt.getPolozenie().getX()+deltaX), -(int)(obiekt.getPolozenie().getY()+deltaY));
        }
    }
    @Override
    public void run() {
        przygotuj();
        while (true) {
            odswiez();
//            sygnalizacja_dol.setLocation(((int)sygnalizacjaL.getPolozenie().getX()+deltaX)*10, -((int)sygnalizacjaL.getPolozenie().getY()+deltaY)*10);
//            sygnalizacja_gora.setLocation(((int)sygnalizacjaP.getPolozenie().getX()+deltaX)*10, -((int)sygnalizacjaP.getPolozenie().getY()+deltaY)*10);
//            tor_dolny.setLocation(((int)torD.getPolozenie().getX()+deltaX-2500)*10, -((int)torD.getPolozenie().getY()+deltaY)*10);
//            tor_gorny.setLocation(((int)torG.getPolozenie().getX()+deltaX-2500)*10, -((int)torG.getPolozenie().getY()+deltaY)*10);
//            pas_prawy.setLocation(((int)pasP.getPolozenie().getX()+deltaX)*10, -((int)pasP.getPolozenie().getY()+deltaY+500)*10);
//            pas_lewy.setLocation(((int)pasL.getPolozenie().getX()+deltaX)*10, -((int)pasL.getPolozenie().getY()+deltaY+500)*10);
//            pociag.setLocation(((int)pociagTestowy4.getPolozenie().getX()+deltaX)*10, -((int)pociagTestowy4.getPolozenie().getY()+deltaY)*10);
            try {
                sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
