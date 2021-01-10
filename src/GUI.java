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
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Arrays;

public class GUI extends Thread {
    private JPanel panel1;
    private JScrollPane obszar;
    private JPanel rozklad;
    private JPanel systemy;
    private JPanel lewy;
    private JPanel mapa;
    private JLabel sygnalizacja_dol;
    private JLabel sygnalizacja_gora;
    private JLabel pociag;
    private JScrollBar scrollBarPionowy;
    private JScrollBar scrollBarPoziomy;
    private JLabel tor_dolny;
    private JLabel pas_prawy;
    private JLabel pas_lewy;
    private JLabel tor_gorny;
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

    // GENEROWANIE CZUJNIKOW I SEMAFORÓW - TOR GORNY
    Semafor semaforSBL1G  = new Semafor(new Polozenie(2501, 2), "semafor_SBL1_tor_gorny");
    Czujnik czujnikSBL1Gn = new Czujnik(new Polozenie(2501, 2), "czujnik_SBL1n_tor_gorny");

    Semafor semaforSBL2G  = new Semafor(new Polozenie(1300, 2), "semafor_SBL2_tor_gorny");
    Czujnik czujnikSBL2Gn = new Czujnik(new Polozenie(1300, 2), "czujnik_SBL2n_tor_gorny");
    Czujnik czujnikSBL1Gz = new Czujnik(new Polozenie(1300, 2), "czujnik_SBL1z_tor_gorny");

    Czujnik czujnikSSPGn  = new Czujnik(new Polozenie(1300, 2), "czujnik_SSPn_tor_gorny");

    Semafor tarczaSSPG   = new Semafor(new Polozenie(1000, 2), "tarcza_SSP_tor_gorny");

    Czujnik czujnikSSPGz  = new Czujnik(new Polozenie(-10,  2), "czujnik_SSPz_tor_gorny");

    Semafor semaforSBL3G  = new Semafor(new Polozenie(-1000,2), "semafor_SBL3_tor_gorny");
    Czujnik czujnikSBL3Gn = new Czujnik(new Polozenie(-1000,2), "czujnik_SBL3n_tor_gorny");
    Czujnik czujnikSBL2Gz = new Czujnik(new Polozenie(-1000,2), "czujnik_SBL2z_tor_gorny");

    Czujnik czujnikSBL3Gz = new Czujnik(new Polozenie(-2500,2), "czujnik_SBL3z_tor_gorny");

    ArrayList<Czujnik> czujnikiSBLnG = new ArrayList<>(Arrays.asList(czujnikSBL1Gn, czujnikSBL2Gn, czujnikSBL3Gn));
    ArrayList<Czujnik> czujnikiSBLzG = new ArrayList<>(Arrays.asList(czujnikSBL1Gz, czujnikSBL2Gz, czujnikSBL3Gz));
    ArrayList<Semafor> semaforySBLG = new ArrayList<>(Arrays.asList(semaforSBL1G, semaforSBL2G, semaforSBL3G));

    Tor torG = new Tor(new Polozenie(0, 2),"tor_gorny", "lewo", 5010, tarczaSSPG, czujnikSSPGn, czujnikSSPGz, czujnikiSBLnG, czujnikiSBLzG, semaforySBLG);

    // GENEROWANIE CZUJNIKOW I SEMAFORÓW - TOR DOLNY
    Semafor semaforSBL1D  = new Semafor(new Polozenie(-2501,-2), "semafor_SBL1_tor_dolny");
    Czujnik czujnikSBL1Dn = new Czujnik(new Polozenie(-2501,-2), "czujnik_SBL1n_tor_dolny");

    Semafor semaforSBL2D  = new Semafor(new Polozenie(-1300,-2), "semafor_SBL2_tor_dolny");
    Czujnik czujnikSBL2Dn = new Czujnik(new Polozenie(-1300,-2), "czujnik_SBL2n_tor_dolny");
    Czujnik czujnikSBL1Dz = new Czujnik(new Polozenie(-1300,-2), "czujnik_SBL1z_tor_dolny");

    Czujnik czujnikSSPDn  = new Czujnik(new Polozenie(-1300,-2), "czujnik_SSPn_tor_dolny");

    Semafor tarczaSSPD   = new Semafor(new Polozenie(-1000,-2), "tarcza_SSP_tor_dolny");

    Czujnik czujnikSSPDz  = new Czujnik(new Polozenie(10,   -2), "czujnik_SSPz_tor_dolny");

    Semafor semaforSBL3D  = new Semafor(new Polozenie(1000, -2), "semafor_SBL3_tor_dolny");
    Czujnik czujnikSBL3Dn = new Czujnik(new Polozenie(1000, -2), "czujnik_SBL3n_tor_dolny");
    Czujnik czujnikSBL2Dz = new Czujnik(new Polozenie(1000, -2), "czujnik_SBL2z_tor_dolny");

    Czujnik czujnikSBL3Dz = new Czujnik(new Polozenie(2500, -2), "czujnik_SBL3z_tor_dolny");

    ArrayList<Czujnik> czujnikiSBLnD = new ArrayList<>(Arrays.asList(czujnikSBL1Dn, czujnikSBL2Dn, czujnikSBL3Dn));
    ArrayList<Czujnik> czujnikiSBLzD = new ArrayList<>(Arrays.asList(czujnikSBL1Dz, czujnikSBL2Dz, czujnikSBL3Dz));
    ArrayList<Semafor> semaforySBLD = new ArrayList<>(Arrays.asList(semaforSBL1D, semaforSBL2D, semaforSBL3D));

    Tor torD = new Tor(new Polozenie(0, -2),"tor_dolny", "prawo", 5010, tarczaSSPD, czujnikSSPDn, czujnikSSPDz, czujnikiSBLnD, czujnikiSBLzD, semaforySBLD);

    ArrayList<Tor> listaTorow = new ArrayList<>(Arrays.asList(torG,torD));

    // GENEROWANIE SYGNALIZATORÓW I ROGATEK - PAS PRAWY I LEWY
    Sygnalizator sygnalizacjaP = new Sygnalizator(new Polozenie(1, -4), "sygnalizacja_pas_prawy");
    Rogatka rogatkaP = new Rogatka(new Polozenie(1, -3), "rogatka_pas_prawy", 5);
    PasRuchu pasP = new PasRuchu(new Polozenie(1, 0), "pas_prawy", sygnalizacjaP, "gora", 501, rogatkaP);

    Sygnalizator sygnalizacjaL = new Sygnalizator(new Polozenie(-1, 4), "sygnalizacja_pas_lewy");
    Rogatka rogatkaL = new Rogatka(new Polozenie(-1, 3), "rogatka_pas_lewy", 5);
    PasRuchu pasL = new PasRuchu(new Polozenie(-1, 0), "pas_lewy", sygnalizacjaL, "dol", 501, rogatkaL);

    ArrayList<PasRuchu> listaPasow = new ArrayList<>(Arrays.asList(pasL, pasP));

    // GENEROWANIE PRZEJAZDU
    Przejazd przejazd= new Przejazd(new Polozenie(0,0), "przejazd", listaPasow, listaTorow, 0);

    // GENEROWANIE POCIĄGÓW
    Pociag pociagTestowy1 = new Pociag("1111-G",200,30000,25,95, torG, przejazd);
    Pociag pociagTestowy2 = new Pociag("2222-G",100,20000,27, 120, torG, przejazd);
    Pociag pociagTestowy3 = new Pociag("3333-D",120,50000,27,60, torD, przejazd);
    Pociag pociagTestowy4 = new Pociag("4444-D", 50,20000,38.89,50, torD, przejazd);

    KontrolaRuchu kontrola = new KontrolaRuchu(przejazd, 3);

    public GUI() {

        obszar.getViewport().setViewPosition(new Point(obszar.getViewport().getViewSize().width/2-640, obszar.getViewport().getViewSize().height/2-360));

        start();
//        torG.getRozkladPociagow().dodaj(pociagTestowy1);
//        torG.getRozkladPociagow().dodaj(pociagTestowy2);
//        torD.getRozkladPociagow().dodaj(pociagTestowy3);
        torD.getRozkladPociagow().dodaj(pociagTestowy4);


    }
    public void odswiezPociagi() {

        for (Tor torBierzacy : listaTorow) {
            for (Pociag pociagBierzacy : torBierzacy.getRozkladPociagow().getTabelaPociagow()) {

            }
        }
    }
    @Override
    public void run() {

        while (true) {

            sygnalizacja_dol.setLocation(((int)sygnalizacjaL.getPolozenie().getX()+deltaX)*10, -((int)sygnalizacjaL.getPolozenie().getY()+deltaY)*10);
            sygnalizacja_gora.setLocation(((int)sygnalizacjaP.getPolozenie().getX()+deltaX)*10, -((int)sygnalizacjaP.getPolozenie().getY()+deltaY)*10);
            tor_dolny.setLocation(((int)torD.getPolozenie().getX()+deltaX-2500)*10, -((int)torD.getPolozenie().getY()+deltaY)*10);
            tor_gorny.setLocation(((int)torG.getPolozenie().getX()+deltaX-2500)*10, -((int)torG.getPolozenie().getY()+deltaY)*10);
            pas_prawy.setLocation(((int)pasP.getPolozenie().getX()+deltaX)*10, -((int)pasP.getPolozenie().getY()+deltaY+500)*10);
            pas_lewy.setLocation(((int)pasL.getPolozenie().getX()+deltaX)*10, -((int)pasL.getPolozenie().getY()+deltaY+500)*10);
            pociag.setLocation(((int)pociagTestowy4.getPolozenie().getX()+deltaX)*10, -((int)pociagTestowy4.getPolozenie().getY()+deltaY)*10);
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
