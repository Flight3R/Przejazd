
import klasyAbstrakcyjne.Pojazd;
import klasyAbstrakcyjne.obiektSymulacji;
import lokacja.Polozenie;
import obslugaPrzejazdu.KontrolaRuchu;
import obslugaPrzejazdu.Przejazd;
import podlozaTransportowe.PasRuchu;
import podlozaTransportowe.Tor;
import pojazdy.Auto;
import pojazdy.Pociag;
import urzadzeniaKolejowe.Czujnik;
import urzadzeniaKolejowe.Semafor;
import urzadzeniaUliczne.Rogatka;
import urzadzeniaUliczne.Sygnalizator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Panel extends Thread {

    int deltaX=2500;
    int deltaY=-500;

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

    ArrayList<obiektSymulacji> listaStatyczna;
    ArrayList<obiektSymulacji> listaDynamiczna = new ArrayList<>();

    Przejazd przejazd;

    private final JFrame frame = new JFrame();
    private final JLabel innerPanel = new JLabel();
    private final JScrollPane scrollPane = new JScrollPane(innerPanel);

    public Panel() {
        // GENEROWANIE CZUJNIKOW I SEMAFORÓW - TOR GORNY
        Semafor semaforSBL1G  = new Semafor(new Polozenie(2501, 40+15), "semafor_SBL1_tor_gorny", s2L, s1L);
        Czujnik czujnikSBL1Gn = new Czujnik(new Polozenie(2501, 40), "czujnik_SBL1n_tor_gorny", czujnik);

        Semafor semaforSBL2G  = new Semafor(new Polozenie(1300, 40+15), "semafor_SBL2_tor_gorny", s2L, s1L);
        Czujnik czujnikSBL2Gn = new Czujnik(new Polozenie(1300, 40), "czujnik_SBL2n_tor_gorny", czujnik);
        Czujnik czujnikSBL1Gz = new Czujnik(new Polozenie(1300, 40), "czujnik_SBL1z_tor_gorny", czujnik);

        Czujnik czujnikSSPGn  = new Czujnik(new Polozenie(1300, 40), "czujnik_SSPn_tor_gorny", czujnik);

        Semafor tarczaSSPG   = new Semafor(new Polozenie(1000, 40+15), "tarcza_SSP_tor_gorny", osp2L, osp1L);

        Czujnik czujnikSSPGz  = new Czujnik(new Polozenie(-40,  40), "czujnik_SSPz_tor_gorny", czujnik);

        Semafor semaforSBL3G  = new Semafor(new Polozenie(-1000,40+15), "semafor_SBL3_tor_gorny", s2L, s1L);
        Czujnik czujnikSBL3Gn = new Czujnik(new Polozenie(-1000,40), "czujnik_SBL3n_tor_gorny", czujnik);
        Czujnik czujnikSBL2Gz = new Czujnik(new Polozenie(-1000,40), "czujnik_SBL2z_tor_gorny", czujnik);

        Czujnik czujnikSBL3Gz = new Czujnik(new Polozenie(-2500,40), "czujnik_SBL3z_tor_gorny", czujnik);

        ArrayList<Czujnik> czujnikiSBLnG = new ArrayList<>(Arrays.asList(czujnikSBL1Gn, czujnikSBL2Gn, czujnikSBL3Gn));
        ArrayList<Czujnik> czujnikiSBLzG = new ArrayList<>(Arrays.asList(czujnikSBL1Gz, czujnikSBL2Gz, czujnikSBL3Gz));
        ArrayList<Semafor> semaforySBLG = new ArrayList<>(Arrays.asList(semaforSBL1G, semaforSBL2G, semaforSBL3G));

        Tor torG = new Tor(new Polozenie(0, 40),"tor_gorny", "lewo", 5010, tarczaSSPG, czujnikSSPGn, czujnikSSPGz, czujnikiSBLnG, czujnikiSBLzG, semaforySBLG, tor);

        // GENEROWANIE CZUJNIKOW I SEMAFORÓW - TOR DOLNY
        Semafor semaforSBL1D  = new Semafor(new Polozenie(-2501,-40-15), "semafor_SBL1_tor_dolny", s2P, s1P);
        Czujnik czujnikSBL1Dn = new Czujnik(new Polozenie(-2501,-40), "czujnik_SBL1n_tor_dolny", czujnik);

        Semafor semaforSBL2D  = new Semafor(new Polozenie(-1300,-40-15), "semafor_SBL2_tor_dolny", s2P, s1P);
        Czujnik czujnikSBL2Dn = new Czujnik(new Polozenie(-1300,-40), "czujnik_SBL2n_tor_dolny", czujnik);
        Czujnik czujnikSBL1Dz = new Czujnik(new Polozenie(-1300,-40), "czujnik_SBL1z_tor_dolny", czujnik);

        Czujnik czujnikSSPDn  = new Czujnik(new Polozenie(-1300,-40), "czujnik_SSPn_tor_dolny", czujnik);

        Semafor tarczaSSPD   = new Semafor(new Polozenie(-1000,-40-15), "tarcza_SSP_tor_dolny", osp2P, osp1P);

        Czujnik czujnikSSPDz  = new Czujnik(new Polozenie(40,   -40), "czujnik_SSPz_tor_dolny", czujnik);

        Semafor semaforSBL3D  = new Semafor(new Polozenie(1000, -40-15), "semafor_SBL3_tor_dolny", s2P, s1P);
        Czujnik czujnikSBL3Dn = new Czujnik(new Polozenie(1000, -40), "czujnik_SBL3n_tor_dolny", czujnik);
        Czujnik czujnikSBL2Dz = new Czujnik(new Polozenie(1000, -40), "czujnik_SBL2z_tor_dolny", czujnik);

        Czujnik czujnikSBL3Dz = new Czujnik(new Polozenie(2500, -40), "czujnik_SBL3z_tor_dolny", czujnik);

        ArrayList<Czujnik> czujnikiSBLnD = new ArrayList<>(Arrays.asList(czujnikSBL1Dn, czujnikSBL2Dn, czujnikSBL3Dn));
        ArrayList<Czujnik> czujnikiSBLzD = new ArrayList<>(Arrays.asList(czujnikSBL1Dz, czujnikSBL2Dz, czujnikSBL3Dz));
        ArrayList<Semafor> semaforySBLD = new ArrayList<>(Arrays.asList(semaforSBL1D, semaforSBL2D, semaforSBL3D));

        Tor torD = new Tor(new Polozenie(0, -40),"tor_dolny", "prawo", 5010, tarczaSSPD, czujnikSSPDn, czujnikSSPDz, czujnikiSBLnD, czujnikiSBLzD, semaforySBLD, tor);

        ArrayList<Tor> listaTorow = new ArrayList<>(Arrays.asList(torG,torD));

        // GENEROWANIE SYGNALIZATORÓW I ROGATEK - PAS PRAWY I LEWY
        Sygnalizator sygnalizacjaP = new Sygnalizator(new Polozenie(30+20, -80), "sygnalizacja_pas_prawy", sygnG, sygnG1, sygnG2);
        Rogatka rogatkaP = new Rogatka(new Polozenie(30+20, -80), "rogatka_pas_prawy", 5, rogatka2, rogatka1);
        PasRuchu pasP = new PasRuchu(new Polozenie(30, 0), "pas_prawy", sygnalizacjaP, "gora", 520, rogatkaP, pas);

        Sygnalizator sygnalizacjaL = new Sygnalizator(new Polozenie(-30-20, 80), "sygnalizacja_pas_lewy", sygnD, sygnD1, sygnD2);
        Rogatka rogatkaL = new Rogatka(new Polozenie(-30-20, 80), "rogatka_pas_lewy", 5, rogatka2, rogatka1);
        PasRuchu pasL = new PasRuchu(new Polozenie(-30, 0), "pas_lewy", sygnalizacjaL, "dol", 520, rogatkaL, pas);

        ArrayList<PasRuchu> listaPasow = new ArrayList<>(Arrays.asList(pasL, pasP));

        // GENEROWANIE PRZEJAZDU
        przejazd = new Przejazd(new Polozenie(0,0), "przejazd", listaPasow, listaTorow, 0, przej);

        // GENEROWANIE POCIĄGÓW
        Pociag pociagTestowy1 = new Pociag("1111-G",300,30000,50,95, torG, przejazd, pociag300L);
        Pociag pociagTestowy2 = new Pociag("2222-G",300,20000,60, 120, torG, przejazd, pociag300L);
        Pociag pociagTestowy3 = new Pociag("3333-D",320,50000,40,60, torD, przejazd, pociag300P);
        Pociag pociagTestowy4 = new Pociag("4444-D", 300,20000,70,30, torD, przejazd, pociag300P);

        torD.getRozkladPociagow().dodaj(pociagTestowy3);
        torD.getRozkladPociagow().dodaj(pociagTestowy4);
        torG.getRozkladPociagow().dodaj(pociagTestowy1);
        torG.getRozkladPociagow().dodaj(pociagTestowy2);

        KontrolaRuchu kontrola = new KontrolaRuchu(przejazd, 7);

        listaStatyczna = new ArrayList(Arrays.asList(
                semaforSBL1G,
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
                przejazd
                ));

        listaDynamiczna.add(rogatkaL);
        listaDynamiczna.add(rogatkaP);

        frame.setPreferredSize(new Dimension(1300,800));
        frame.setBounds(0,0,1280,720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("test");
        frame.pack();
        frame.setVisible(true);

        frame.getContentPane().setLayout(new FlowLayout());

        innerPanel.setPreferredSize(new Dimension(5000,1000));

        scrollPane.setPreferredSize(new Dimension(1280,720));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().add(scrollPane);

        scrollPane.setViewport(new JViewport());

        for (obiektSymulacji obiekt : listaStatyczna) {
            obiekt.getLabel().setBounds((int)obiekt.getPolozenie().getX()+deltaX-obiekt.getLabel().getIcon().getIconWidth()/2, -(int)(obiekt.getPolozenie().getY()+deltaY+obiekt.getLabel().getIcon().getIconHeight()/2), obiekt.getLabel().getPreferredSize().width, obiekt.getLabel().getPreferredSize().height);
            innerPanel.add(obiekt.getLabel());
        }

        for (Tor tor : przejazd.getListaTorow()) {
            for (Pociag pociag : tor.getRozkladPociagow().getTabelaPociagow()) {
                listaDynamiczna.add(pociag);
                innerPanel.add(pociag.getLabel(),0);
            }
        }

        start();
    }
    public void odswierz() {
        System.out.println("refresh");
        for (PasRuchu pas : przejazd.getListaPasow()) {
            for (Auto auto : pas.getListaAut()) {
                if (!listaDynamiczna.contains(auto)) {
                    System.out.println("auto dodane");
                    listaDynamiczna.add(auto);
                    innerPanel.add(auto.getLabel(),0);
                }
            }
        }

        for (Pociag pociag : przejazd.getPociagiObecne().getTabelaPociagow()) {
            System.out.println(pociag.getNazwa());
            if (!listaDynamiczna.contains(pociag)) {
                listaDynamiczna.add(pociag);
                innerPanel.add(pociag.getLabel(), 0);
            }
        }

        for (obiektSymulacji obiekt : listaDynamiczna) {
            if (obiekt instanceof Pociag) {
                if (((Pociag) obiekt).getTor().getZwrot().equals("prawo")) {
                    obiekt.getLabel().setBounds((int)obiekt.getPolozenie().getX()+deltaX-((Pociag) obiekt).getDlugosc(), -(int)(obiekt.getPolozenie().getY()+deltaY+obiekt.getLabel().getIcon().getIconHeight()/2), obiekt.getLabel().getPreferredSize().width, obiekt.getLabel().getPreferredSize().height);
                } else {
                    obiekt.getLabel().setBounds((int)obiekt.getPolozenie().getX()+deltaX, -(int)(obiekt.getPolozenie().getY()+deltaY+obiekt.getLabel().getIcon().getIconHeight()/2), obiekt.getLabel().getPreferredSize().width, obiekt.getLabel().getPreferredSize().height);
                }
            } else if (obiekt instanceof Auto) {
                if (((Auto) obiekt).getPas().getZwrot().equals("gora")) {
                    obiekt.getLabel().setBounds((int)obiekt.getPolozenie().getX()+deltaX-obiekt.getLabel().getIcon().getIconWidth()/2, -(int)(obiekt.getPolozenie().getY()+deltaY), obiekt.getLabel().getPreferredSize().width, obiekt.getLabel().getPreferredSize().height);
                    System.out.println("autogora");
                } else {
                    obiekt.getLabel().setBounds((int)obiekt.getPolozenie().getX()+deltaX-obiekt.getLabel().getIcon().getIconWidth()/2, -(int)(obiekt.getPolozenie().getY()+deltaY+((Auto) obiekt).getDlugosc()), obiekt.getLabel().getPreferredSize().width, obiekt.getLabel().getPreferredSize().height);
                    System.out.println("autodol");
                }
            } else {
                obiekt.getLabel().setBounds((int)obiekt.getPolozenie().getX()+deltaX-obiekt.getLabel().getIcon().getIconWidth()/2, -(int)(obiekt.getPolozenie().getY()+deltaY+obiekt.getLabel().getIcon().getIconHeight()/2), obiekt.getLabel().getIcon().getIconWidth(), obiekt.getLabel().getIcon().getIconHeight());
            }
        }
    }

    public void run() {
        while (true) {
            odswierz();
            try {
                sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Panel();
    }
}
