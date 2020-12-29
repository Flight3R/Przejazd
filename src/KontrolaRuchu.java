import java.util.ArrayList;
import java.util.Random;

public class KontrolaRuchu extends Thread {
    private Przejazd przejazd;
    private ArrayList<Auto> autaPrawy = new ArrayList<>();
    private ArrayList<Auto> autaLewy = new ArrayList<>();
    private int maxIloscNaPas = 5;
    private int numerPorzadkowy = 0;

    public KontrolaRuchu(Przejazd przejazd) {
        this.przejazd = przejazd;
        start();
    }

    public void obslugaAut() {
        Random generator = new Random();
        boolean miejsceNaAuto;
        Auto poprzednieAuto;

        // PAS LEWY
        if (autaLewy.size() == 0) {
            miejsceNaAuto = true;
            poprzednieAuto = null;
        } else {
            miejsceNaAuto = autaLewy.get(autaLewy.size() - 1).getPolozenie().getY() < 1000 - 3;
            poprzednieAuto = autaLewy.get(autaLewy.size() - 1);
        }

        if (autaLewy.size() < maxIloscNaPas && miejsceNaAuto) {
            int masa = generator.nextInt(1500)+500;
            int Vmax = generator.nextInt(4)+12;
            Auto nowe = new Auto(Integer.toString(numerPorzadkowy),3, masa, Vmax, new Polozenie(-1,1000), przejazd.getPasLewy(), poprzednieAuto);
            autaLewy.add(nowe);
            nowe.start();
            numerPorzadkowy = numerPorzadkowy + 1;
        }

        if (autaLewy.get(0).getPolozenie().getY() < -1000) {
            autaLewy.get(0).interrupt();
            autaLewy.remove(0);
        }

        // PAS PRAWY
        if (autaPrawy.size() == 0) {
            miejsceNaAuto = true;
            poprzednieAuto = null;
        }
        else {
            miejsceNaAuto = -1000 + 3 < autaPrawy.get(autaPrawy.size() - 1).getPolozenie().getY();
            poprzednieAuto = autaPrawy.get(autaPrawy.size() - 1);
        }

        if (autaPrawy.size() < maxIloscNaPas && miejsceNaAuto) {
            int masa = generator.nextInt(1500)+500;
            int Vmax = generator.nextInt(4)+12;
            Auto nowe = new Auto(Integer.toString(numerPorzadkowy), 3, masa, Vmax, new Polozenie(1,-1000), przejazd.getPasPrawy(), poprzednieAuto);
            autaPrawy.add(nowe);
            nowe.start();
            numerPorzadkowy = numerPorzadkowy + 1;
        }

        if (1000 < autaPrawy.get(0).getPolozenie().getY()) {
            autaPrawy.get(0).interrupt();
            autaPrawy.remove(0);
        }
    }

    public void obslugaPociagow() {
        if (!przejazd.getTorGorny().getSemaforySBL().get(0).isStop() && przejazd.getRozkladGorny().ilePociagow() != 0) {
            Pociag najblizszyPrzed = przejazd.getRozkladGorny().najblizszyPociag();
            double czasDojazdu = 2500 / najblizszyPrzed.getMaxPredkosc();
            if (najblizszyPrzed.getCzasPrzyjazdu() - czasDojazdu < przejazd.getCzas()) {
                najblizszyPrzed.start();
                najblizszyPrzed.setSpoznienie((czasDojazdu+najblizszyPrzed.getCzasPrzyjazdu() - przejazd.getCzas()));
                przejazd.getPociagiObecne().dodaj(najblizszyPrzed);
                przejazd.getRozkladGorny().usunPierwszy();
            }
        }

        if (!przejazd.getTorDolny().getSemaforySBL().get(0).isStop() && przejazd.getRozkladDolny().ilePociagow() != 0) {
            Pociag najblizszyPrzed = przejazd.getRozkladDolny().najblizszyPociag();
            double czasDojazdu = 2500 / najblizszyPrzed.getMaxPredkosc();
            if (najblizszyPrzed.getCzasPrzyjazdu() - czasDojazdu < przejazd.getCzas()) {
                najblizszyPrzed.start();
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

//        super.run();
        while (true) {

            if (czasKolejnegoAuta <= przejazd.getCzas()) {
                obslugaAut();
                czasKolejnegoAuta = przejazd.getCzas() + losowyCzas.nextInt(2)+4;
            }

            obslugaPociagow();

            try { sleep(400); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
