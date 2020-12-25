public class KontrolaRuchu extends Thread {
    private Przejazd przejazd;

    public KontrolaRuchu(Przejazd przejazd) {
        this.przejazd = przejazd;
        start();
    }

    @Override
    public void run() {
        while (true) {
            boolean zajetoscToruGornego = (przejazd.getTorGorny().getSblPrzed().getAktywacje() + przejazd.getTorGorny().getSblZa().getAktywacje()) % 2 != 0;
            boolean zajetoscToruDolnego = (przejazd.getTorDolny().getSblPrzed().getAktywacje() + przejazd.getTorDolny().getSblZa().getAktywacje()) % 2 != 0;

            if (!zajetoscToruGornego && przejazd.getRozkladGorny().ilePociagow() != 0) {
                Pociag najblizszyPrzed = przejazd.getRozkladGorny().najblizszyPociag();
                double czasDojazdu = 2500 / najblizszyPrzed.getMaxPredkosc();
                if (najblizszyPrzed.getCzasPrzyjazdu() - czasDojazdu < przejazd.getCzas()) {
                    najblizszyPrzed.start();
                    przejazd.getLista().dodaj(najblizszyPrzed);
                    przejazd.getRozkladGorny().usunPierwszy();
                }
            }

            if (!zajetoscToruDolnego && przejazd.getRozkladDolny().ilePociagow() != 0) {
                Pociag najblizszyPrzed = przejazd.getRozkladDolny().najblizszyPociag();
                double czasDojazdu = 2500 / najblizszyPrzed.getMaxPredkosc();
                if (najblizszyPrzed.getCzasPrzyjazdu() - czasDojazdu < przejazd.getCzas()) {
                    najblizszyPrzed.start();
                    przejazd.getLista().dodaj(najblizszyPrzed);
                    przejazd.getRozkladDolny().usunPierwszy();
                }
            }

            if (przejazd.getLista().ilePociagow() != 0) {
                Pociag najblizszyZa = przejazd.getLista().najblizszyPociag();
                if (Math.abs(najblizszyZa.getPolozenie().getX()) > 2500) {
                    przejazd.getLista().usunPierwszy();
                    najblizszyZa.interrupt();
                }
            }

            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            };
        }
    }
}
