package obslugaPrzejazdu;

import lokacja.Polozenie;
import podlozaTransportowe.PasRuchu;
import podlozaTransportowe.Tor;
import pojazdy.Auto;
import pojazdy.Pociag;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class KontrolaRuchu extends Thread {
    private final Przejazd przejazd;
    int maxIloscNaPas;
    private int numerPorzadkowy = 0;

    public KontrolaRuchu(Przejazd przejazd, int maxIloscNaPas) {
        this.przejazd = przejazd;
        this.maxIloscNaPas = maxIloscNaPas;
        start();
    }

    public void obslugaAut() {
        Random generator = new Random();
        boolean miejsceNaAuto;
        Auto poprzednieAuto;
        ImageIcon ikona = new ImageIcon();

        for (PasRuchu pasBierzacy : przejazd.getListaPasow()) {

            if (pasBierzacy.getListaAut().size() == 0) {
                poprzednieAuto = null;
                miejsceNaAuto = true;

            } else {
                poprzednieAuto = pasBierzacy.getListaAut().get(pasBierzacy.getListaAut().size() - 1);

                if (pasBierzacy.getZwrot() == "gora") {
                    miejsceNaAuto = (pasBierzacy.getPolozenie().getY() - pasBierzacy.getDlugosc()/2) < (poprzednieAuto.getPolozenie().getY() - poprzednieAuto.getDlugosc() - 20);
                    ikona = new ImageIcon("src/grafiki/auto_gora.png");
                }
                else {
                    miejsceNaAuto = (poprzednieAuto.getPolozenie().getY() + poprzednieAuto.getDlugosc() + 20) < (pasBierzacy.getPolozenie().getY() + pasBierzacy.getDlugosc()/2);
                    ikona = new ImageIcon("src/grafiki/auto_dol.png");
                }
            }

            if (pasBierzacy.getListaAut().size() < maxIloscNaPas && miejsceNaAuto) {
                int masa = generator.nextInt(1500) + 500;
                int Vmax = generator.nextInt(10) + 100;

                Auto nowe = new Auto(Integer.toString(numerPorzadkowy), 40, masa, Vmax, pasBierzacy, poprzednieAuto, ikona);
                pasBierzacy.getListaAut().add(nowe);
                nowe.start();
                System.out.println("auto myyyyyk");
                numerPorzadkowy = numerPorzadkowy + 1;
            }
            double cybant = Math.abs(pasBierzacy.getPolozenie().getY() - pasBierzacy.getListaAut().get(0).getPolozenie().getY());
            double dlug = pasBierzacy.getDlugosc()/2;
            if (dlug < cybant ) {
                pasBierzacy.getListaAut().get(0).getLabel().setIcon(new ImageIcon("src/grafiki/rogatka_otwarta.png"));
                pasBierzacy.getListaAut().get(1).setAutoPrzed(null);
                pasBierzacy.getListaAut().get(0).interrupt();
                pasBierzacy.getListaAut().get(0).getLabel().remove(0);
                pasBierzacy.getListaAut().remove(0);

            }
        }
    }

    public void obslugaPociagow() {
        for (Tor torBierzacy : przejazd.getListaTorow()) {
            if (!torBierzacy.getSemaforySBL().get(0).isStop() && torBierzacy.getRozkladPociagow().ilePociagow() != 0) {
                Pociag najblizszyPrzed = torBierzacy.getRozkladPociagow().najblizszyPociag();
                double czasDojazdu = 2500 / najblizszyPrzed.getMaxPredkosc();
                if (najblizszyPrzed.getCzasPrzyjazdu() - czasDojazdu < przejazd.getCzas()) {
                    najblizszyPrzed.start();
                    najblizszyPrzed.setSpoznienie(przejazd.getCzas() + czasDojazdu - najblizszyPrzed.getCzasPrzyjazdu());
                    przejazd.getPociagiObecne().dodaj(najblizszyPrzed);
                    torBierzacy.getRozkladPociagow().usunPierwszy();
                }
            }
        }
        if (przejazd.getPociagiObecne().ilePociagow() != 0) {
            Pociag najblizszyZa = przejazd.getPociagiObecne().najblizszyPociag();
            if (Math.abs(najblizszyZa.getPolozenie().getX()) > 3200) {
                przejazd.getPociagiObecne().usunPierwszy();
                najblizszyZa.interrupt();
            }
        }
    }

    @Override
    public void run() {
        Random losowyCzas = new Random();
        double czasKolejnegoAuta = 0;

        while (true) {

            if (czasKolejnegoAuta <= przejazd.getCzas()) {
                obslugaAut();
                czasKolejnegoAuta = przejazd.getCzas() + losowyCzas.nextInt(2)+2;
            }

            obslugaPociagow();

            try { sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
