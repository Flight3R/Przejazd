package obslugaPrzejazdu;

import lokacja.Polozenie;
import podlozaTransportowe.PasRuchu;
import pojazdy.Auto;
import pojazdy.Pociag;

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

        for (int i=0; i<przejazd.getListaPasow().size(); i++) {
            PasRuchu pasBierzacy = przejazd.getListaPasow().get(i);

            if (pasBierzacy.getListaAut().size() == 0) {
                poprzednieAuto = null;
                miejsceNaAuto = true;
            } else {
                poprzednieAuto = pasBierzacy.getListaAut().get(pasBierzacy.getListaAut().size() - 1);

                if (pasBierzacy.getZwrot() == "gora")
                    miejsceNaAuto =  pasBierzacy.getDlugosc() < (poprzednieAuto.getPolozenie().getY() - poprzednieAuto.getDlugosc() - 1);
                else
                    miejsceNaAuto = (poprzednieAuto.getPolozenie().getY() + poprzednieAuto.getDlugosc() + 1) < pasBierzacy.getDlugosc();
            }

            if (pasBierzacy.getListaAut().size() < maxIloscNaPas && miejsceNaAuto) {
                int masa = generator.nextInt(1500) + 500;
                int Vmax = generator.nextInt(5) + 12;
                int wspY =  (int) (pasBierzacy.getZwrot() == "gora"? -pasBierzacy.getDlugosc() : pasBierzacy.getDlugosc());

                Auto nowe = new Auto(Integer.toString(numerPorzadkowy), 3, masa, Vmax, new Polozenie(pasBierzacy.getPolozenie().getX(), wspY), pasBierzacy, poprzednieAuto);
                pasBierzacy.getListaAut().add(nowe);
                nowe.start();
                numerPorzadkowy = numerPorzadkowy + 1;
            }

            if (pasBierzacy.getDlugosc() < Math.abs(pasBierzacy.getPolozenie().getY() - pasBierzacy.getListaAut().get(0).getPolozenie().getY())) {
                pasBierzacy.getListaAut().get(0).interrupt();
                pasBierzacy.getListaAut().remove(0);
            }
        }
    }

       /* // PAS PRAWY
        if (autaPrawy.size() == 0) {
            poprzednieAuto = null;
            miejsceNaAuto = true;
        }
        else {
            poprzednieAuto = autaPrawy.get(autaPrawy.size() - 1);
            miejsceNaAuto = -przejazd.getPasLewy().getDlugosc() + 1 < poprzednieAuto.getPolozenie().getY() - poprzednieAuto.getDlugosc();
        }

        if (autaPrawy.size() < maxIloscNaPas && miejsceNaAuto) {
            int masa = generator.nextInt(1500)+500;
            int Vmax = generator.nextInt(5)+12;
            Auto nowe = new Auto(Integer.toString(numerPorzadkowy), 3, masa, Vmax, new Polozenie(przejazd.getPasPrawy().getPolozenie().getX(), -przejazd.getPasPrawy().getDlugosc()), przejazd.getPasPrawy(), poprzednieAuto);
            autaPrawy.add(nowe);
            nowe.start();
            numerPorzadkowy = numerPorzadkowy + 1;
        }

        if (przejazd.getPasPrawy().getDlugosc() < autaPrawy.get(0).getPolozenie().getY()) {
            autaPrawy.get(0).interrupt();
            autaPrawy.remove(0);
        }
    }*/

    public void obslugaPociagow() {
        if (!przejazd.getTorGorny().getSemaforySBL().get(0).isStop() && przejazd.getRozkladGorny().ilePociagow() != 0) {
            Pociag najblizszyPrzed = przejazd.getRozkladGorny().najblizszyPociag();
            double czasDojazdu = 2500 / najblizszyPrzed.getMaxPredkosc();
            if (najblizszyPrzed.getCzasPrzyjazdu() - czasDojazdu < przejazd.getCzas()) {
                najblizszyPrzed.start();
                najblizszyPrzed.setSpoznienie(przejazd.getCzas() + czasDojazdu - najblizszyPrzed.getCzasPrzyjazdu());
                przejazd.getPociagiObecne().dodaj(najblizszyPrzed);
                przejazd.getRozkladGorny().usunPierwszy();
            }
        }

        if (!przejazd.getTorDolny().getSemaforySBL().get(0).isStop() && przejazd.getRozkladDolny().ilePociagow() != 0) {
            Pociag najblizszyPrzed = przejazd.getRozkladDolny().najblizszyPociag();
            double czasDojazdu = 2500 / najblizszyPrzed.getMaxPredkosc();
            if (najblizszyPrzed.getCzasPrzyjazdu() - czasDojazdu < przejazd.getCzas()) {
                najblizszyPrzed.start();
                najblizszyPrzed.setSpoznienie(przejazd.getCzas() + czasDojazdu - najblizszyPrzed.getCzasPrzyjazdu());
                przejazd.getPociagiObecne().dodaj(najblizszyPrzed);
                przejazd.getRozkladDolny().usunPierwszy();
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
        double czasKolejnegoAuta = przejazd.getCzas();

        while (true) {

            if (czasKolejnegoAuta <= przejazd.getCzas()) {
                obslugaAut();
                czasKolejnegoAuta = przejazd.getCzas() + losowyCzas.nextInt(2)+4;
            }

            obslugaPociagow();

            try { sleep(600); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
