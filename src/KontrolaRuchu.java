public class KontrolaRuchu extends Thread {
    private final Przejazd przejazd;
    private final Rozklad lista = new Rozklad();

    public KontrolaRuchu(Przejazd przejazd) {
        this.przejazd = przejazd;
        start();
    }

    /*@Override
    public void run() {
        while (true) {
            if (!przejazd.getTorGorny().getSemaforSBL1().isStop() && przejazd.getRozkladGorny().ilePociagow() != 0) {
                Pociag najblizszyPrzed = przejazd.getRozkladGorny().najblizszyPociag();
                double czasDojazdu = 2500 / najblizszyPrzed.getMaxPredkosc();
                if (najblizszyPrzed.getCzasPrzyjazdu() - czasDojazdu < przejazd.getCzas()) {
                    najblizszyPrzed.start();
                    najblizszyPrzed.setOpoznienie((int) (czasDojazdu+najblizszyPrzed.getCzasPrzyjazdu() - przejazd.getCzas()));
                    lista.dodaj(najblizszyPrzed);
                    przejazd.getRozkladGorny().usunPierwszy();
                }
            }

            if (!przejazd.getTorDolny().getSemaforSBL1().isStop() && przejazd.getRozkladDolny().ilePociagow() != 0) {
                Pociag najblizszyPrzed = przejazd.getRozkladDolny().najblizszyPociag();
                double czasDojazdu = 2500 / najblizszyPrzed.getMaxPredkosc();
                if (najblizszyPrzed.getCzasPrzyjazdu() - czasDojazdu < przejazd.getCzas()) {
                    najblizszyPrzed.start();
                    lista.dodaj(najblizszyPrzed);
                    przejazd.getRozkladDolny().usunPierwszy();
                }
            }

            if (lista.ilePociagow() != 0) {
                Pociag najblizszyZa = lista.najblizszyPociag();
                if (Math.abs(najblizszyZa.getPolozenie().getX()) > 2500) {
                    lista.usunPierwszy();
                    najblizszyZa.interrupt();
                }
            }

            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            };
        }
    }*/
}
