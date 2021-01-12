//import com.sun.source.doctree.ThrowsTree;
//import klasyAbstrakcyjne.obiektSymulacji;
//import lokacja.Polozenie;
//import obslugaPrzejazdu.KontrolaRuchu;
//import obslugaPrzejazdu.Przejazd;
//import podlozaTransportowe.PasRuchu;
//import podlozaTransportowe.Tor;
//import pojazdy.Pociag;
//import urzadzeniaKolejowe.Czujnik;
//import urzadzeniaKolejowe.Semafor;
//import urzadzeniaUliczne.Rogatka;
//import urzadzeniaUliczne.Sygnalizator;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//public class GUI extends Thread {
//    private JPanel panel1;
//    private JScrollPane obszar;
//    private JPanel rozklad;
//    private JPanel systemy;
//    private JPanel lewy;
//    private JScrollBar scrollBarPionowy;
//    private JScrollBar scrollBarPoziomy;
//    private JPanel mapa;
//    private JTextField textField1;
//    private JTextField textField2;
//    private JTextField textField3;
//    private JTextField textField4;
//    private JTextField textField5;
//    private JTextField textField6;
//    private JTextField textField7;
//    private JTextField textField8;
//    private JTextField textField9;
//    private JTextField textField10;
//    private JTextField textField11;
//    private JTextField textField12;
//    private JTextField textField13;
//    private JTextField textField14;
//    private JTextField textField15;
//    private JTextField textField16;
//    private JTextField textField17;
//    private JTextField textField18;
//    private JButton button1;
//    private JButton button2;
//    private JRadioButton radioButton1;
//    private JRadioButton radioButton2;
//    private static final JFrame frame = new JFrame();
//    int deltaX=2500;
//    int deltaY=-500;
//
//    ImageIcon s1P = new ImageIcon("src/grafiki/s1_prawo.png");
//    ImageIcon s2P = new ImageIcon("src/grafiki/s2_prawo.png");
//    ImageIcon s1L = new ImageIcon("src/grafiki/s1_lewo.png");
//    ImageIcon s2L = new ImageIcon("src/grafiki/s2_lewo.png");
//    ImageIcon osp1P = new ImageIcon("src/grafiki/osp1_prawo.png");
//    ImageIcon osp2P = new ImageIcon("src/grafiki/osp2_prawo.png");
//    ImageIcon osp1L = new ImageIcon("src/grafiki/osp1_lewo.png");
//    ImageIcon osp2L = new ImageIcon("src/grafiki/osp2_lewo.png");
//    ImageIcon sygnG = new ImageIcon("src/grafiki/sygnalizacja_gora.png");
//    ImageIcon sygnD = new ImageIcon("src/grafiki/sygnalizacja_dol.png");
//    ImageIcon sygnG1 = new ImageIcon("src/grafiki/sygnalizacja1_gora.png");
//    ImageIcon sygnD1 = new ImageIcon("src/grafiki/sygnalizacja1_dol.png");
//    ImageIcon sygnG2 = new ImageIcon("src/grafiki/sygnalizacja2_gora.png");
//    ImageIcon sygnD2 = new ImageIcon("src/grafiki/sygnalizacja2_dol.png");
//
//    ImageIcon czujnik = new ImageIcon("src/grafiki/czujnik.png");
//
//    ImageIcon rogatka1 = new ImageIcon("src/grafiki/rogatka_zamknieta.png");
//    ImageIcon rogatka2 = new ImageIcon("src/grafiki/rogatka_otwarta.png");
//
//    ImageIcon tor = new ImageIcon("src/grafiki/tor.png");
//    ImageIcon pas = new ImageIcon("src/grafiki/pas.png");
//
//    ImageIcon pociag300P = new ImageIcon("src/grafiki/pociag_300_prawo.png");
//    ImageIcon pociag300L = new ImageIcon("src/grafiki/pociag_300_lewo.png");
//
//    ImageIcon autoG = new ImageIcon("src/grafiki/auto_gora.png");
//    ImageIcon autoD = new ImageIcon("src/grafiki/auto_dol.png");
//
//    ImageIcon przej = new ImageIcon("src/grafiki/przejazd.png");
//
//    ArrayList<obiektSymulacji> lista;
//
//    public GUI() { ;
//
//        // GENEROWANIE CZUJNIKOW I SEMAFORÓW - TOR GORNY
//        Semafor semaforSBL1G  = new Semafor(new Polozenie(2501, 2), "semafor_SBL1_tor_gorny", s2L);
//        Czujnik czujnikSBL1Gn = new Czujnik(new Polozenie(2501, 2), "czujnik_SBL1n_tor_gorny", czujnik);
//
//        Semafor semaforSBL2G  = new Semafor(new Polozenie(1300, 2), "semafor_SBL2_tor_gorny", s2L);
//        Czujnik czujnikSBL2Gn = new Czujnik(new Polozenie(1300, 2), "czujnik_SBL2n_tor_gorny", czujnik);
//        Czujnik czujnikSBL1Gz = new Czujnik(new Polozenie(1300, 2), "czujnik_SBL1z_tor_gorny", czujnik);
//
//        Czujnik czujnikSSPGn  = new Czujnik(new Polozenie(1300, 2), "czujnik_SSPn_tor_gorny", czujnik);
//
//        Semafor tarczaSSPG   = new Semafor(new Polozenie(1000, 2), "tarcza_SSP_tor_gorny", osp2L);
//
//        Czujnik czujnikSSPGz  = new Czujnik(new Polozenie(-10,  2), "czujnik_SSPz_tor_gorny", czujnik);
//
//        Semafor semaforSBL3G  = new Semafor(new Polozenie(-1000,2), "semafor_SBL3_tor_gorny", s2L);
//        Czujnik czujnikSBL3Gn = new Czujnik(new Polozenie(-1000,2), "czujnik_SBL3n_tor_gorny", czujnik);
//        Czujnik czujnikSBL2Gz = new Czujnik(new Polozenie(-1000,2), "czujnik_SBL2z_tor_gorny", czujnik);
//
//        Czujnik czujnikSBL3Gz = new Czujnik(new Polozenie(-2500,2), "czujnik_SBL3z_tor_gorny", czujnik);
//
//        ArrayList<Czujnik> czujnikiSBLnG = new ArrayList<>(Arrays.asList(czujnikSBL1Gn, czujnikSBL2Gn, czujnikSBL3Gn));
//        ArrayList<Czujnik> czujnikiSBLzG = new ArrayList<>(Arrays.asList(czujnikSBL1Gz, czujnikSBL2Gz, czujnikSBL3Gz));
//        ArrayList<Semafor> semaforySBLG = new ArrayList<>(Arrays.asList(semaforSBL1G, semaforSBL2G, semaforSBL3G));
//
//        Tor torG = new Tor(new Polozenie(0, 2),"tor_gorny", "lewo", 5010, tarczaSSPG, czujnikSSPGn, czujnikSSPGz, czujnikiSBLnG, czujnikiSBLzG, semaforySBLG, tor);
//
//        // GENEROWANIE CZUJNIKOW I SEMAFORÓW - TOR DOLNY
//        Semafor semaforSBL1D  = new Semafor(new Polozenie(-2501,-2), "semafor_SBL1_tor_dolny", s2P);
//        Czujnik czujnikSBL1Dn = new Czujnik(new Polozenie(-2501,-2), "czujnik_SBL1n_tor_dolny", czujnik);
//
//        Semafor semaforSBL2D  = new Semafor(new Polozenie(-1300,-2), "semafor_SBL2_tor_dolny", s2P);
//        Czujnik czujnikSBL2Dn = new Czujnik(new Polozenie(-1300,-2), "czujnik_SBL2n_tor_dolny", czujnik);
//        Czujnik czujnikSBL1Dz = new Czujnik(new Polozenie(-1300,-2), "czujnik_SBL1z_tor_dolny", czujnik);
//
//        Czujnik czujnikSSPDn  = new Czujnik(new Polozenie(-1300,-2), "czujnik_SSPn_tor_dolny", czujnik);
//
//        Semafor tarczaSSPD   = new Semafor(new Polozenie(-1000,-2), "tarcza_SSP_tor_dolny", osp2P);
//
//        Czujnik czujnikSSPDz  = new Czujnik(new Polozenie(10,   -2), "czujnik_SSPz_tor_dolny", czujnik);
//
//        Semafor semaforSBL3D  = new Semafor(new Polozenie(1000, -2), "semafor_SBL3_tor_dolny", s2P);
//        Czujnik czujnikSBL3Dn = new Czujnik(new Polozenie(1000, -2), "czujnik_SBL3n_tor_dolny", czujnik);
//        Czujnik czujnikSBL2Dz = new Czujnik(new Polozenie(1000, -2), "czujnik_SBL2z_tor_dolny", czujnik);
//
//        Czujnik czujnikSBL3Dz = new Czujnik(new Polozenie(2500, -2), "czujnik_SBL3z_tor_dolny", czujnik);
//
//        ArrayList<Czujnik> czujnikiSBLnD = new ArrayList<>(Arrays.asList(czujnikSBL1Dn, czujnikSBL2Dn, czujnikSBL3Dn));
//        ArrayList<Czujnik> czujnikiSBLzD = new ArrayList<>(Arrays.asList(czujnikSBL1Dz, czujnikSBL2Dz, czujnikSBL3Dz));
//        ArrayList<Semafor> semaforySBLD = new ArrayList<>(Arrays.asList(semaforSBL1D, semaforSBL2D, semaforSBL3D));
//
//        Tor torD = new Tor(new Polozenie(0, -2),"tor_dolny", "prawo", 5010, tarczaSSPD, czujnikSSPDn, czujnikSSPDz, czujnikiSBLnD, czujnikiSBLzD, semaforySBLD, tor);
//
//        ArrayList<Tor> listaTorow = new ArrayList<>(Arrays.asList(torG,torD));
//
//        // GENEROWANIE SYGNALIZATORÓW I ROGATEK - PAS PRAWY I LEWY
//        Sygnalizator sygnalizacjaP = new Sygnalizator(new Polozenie(1, -4), "sygnalizacja_pas_prawy", sygnD);
//        Rogatka rogatkaP = new Rogatka(new Polozenie(1, -3), "rogatka_pas_prawy", 5, rogatka2);
//        PasRuchu pasP = new PasRuchu(new Polozenie(1, 0), "pas_prawy", sygnalizacjaP, "gora", 501, rogatkaP, pas);
//
//        Sygnalizator sygnalizacjaL = new Sygnalizator(new Polozenie(-1, 4), "sygnalizacja_pas_lewy", sygnG);
//        Rogatka rogatkaL = new Rogatka(new Polozenie(-1, 3), "rogatka_pas_lewy", 5, rogatka2);
//        PasRuchu pasL = new PasRuchu(new Polozenie(-1, 0), "pas_lewy", sygnalizacjaL, "dol", 501, rogatkaL, pas);
//
//        ArrayList<PasRuchu> listaPasow = new ArrayList<>(Arrays.asList(pasL, pasP));
//
//        // GENEROWANIE PRZEJAZDU
//        Przejazd przejazd= new Przejazd(new Polozenie(0,0), "przejazd", listaPasow, listaTorow, 0, przej);
//
//        // GENEROWANIE POCIĄGÓW
////    Pociag pociagTestowy1 = new Pociag("1111-G",200,30000,25,95, torG, przejazd);
////    Pociag pociagTestowy2 = new Pociag("2222-G",100,20000,27, 120, torG, przejazd);
////    Pociag pociagTestowy3 = new Pociag("3333-D",120,50000,27,60, torD, przejazd);
//        Pociag pociagTestowy4 = new Pociag("4444-D", 300,20000,38.89,50, torD, przejazd, pociag300P);
//
//        KontrolaRuchu kontrola = new KontrolaRuchu(przejazd, 3);
//
//        lista = new ArrayList(Arrays.asList(
//                semaforSBL1G,
//                czujnikSBL1Gn,
//                semaforSBL2G ,
//                czujnikSBL2Gn,
//                czujnikSBL1Gz,
//                czujnikSSPGn,
//                tarczaSSPG ,
//                czujnikSSPGz,
//                semaforSBL3G,
//                czujnikSBL3Gn,
//                czujnikSBL2Gz,
//                czujnikSBL3Gz,
//                torG ,
//                semaforSBL1D,
//                czujnikSBL1Dn,
//                semaforSBL2D,
//                czujnikSBL2Dn,
//                czujnikSBL1Dz,
//                czujnikSSPDn,
//                tarczaSSPD,
//                czujnikSSPDz,
//                semaforSBL3D,
//                czujnikSBL3Dn,
//                czujnikSBL2Dz,
//                czujnikSBL3Dz,
//                torD ,
//                sygnalizacjaP ,
//                rogatkaP,
//                pasP,
//                sygnalizacjaL,
//                rogatkaL,
//                pasL,
//                przejazd,
//                pociagTestowy4));
//
////        obszar.getViewport().setViewPosition(new Point(obszar.getViewport().getViewSize().width/2-640, obszar.getViewport().getViewSize().height/2-360));
////        torG.getRozkladPociagow().dodaj(pociagTestowy1);
////        torG.getRozkladPociagow().dodaj(pociagTestowy2);
////        torD.getRozkladPociagow().dodaj(pociagTestowy3);
//        torD.getRozkladPociagow().dodaj(pociagTestowy4);
//
//        for (obiektSymulacji obiekt : lista) {
//            obiekt.getLabel().setLocation(100, 100);
//        }
//    }
//
//    public void odswiez() {
//        for (obiektSymulacji obiekt : lista) {
//            obiekt.getLabel().setLocation(100, 100);
//        }
//    }
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("GUI");
//        frame.setContentPane(new GUI().panel1);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }
//
//    private void createUIComponents() {
//        // TODO: place custom component creation code here
//    }
//}
