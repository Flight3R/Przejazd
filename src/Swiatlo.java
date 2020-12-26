public class Swiatlo extends ElementInfrastruktury {
    private boolean zapalone = false;
    private boolean polecenieZgaszenia = false;

    public Swiatlo(Polozenie polozenie, String nazwa) {
        super(polozenie, nazwa);
        start();
    }

    public boolean isZapalone() { return zapalone; }

    public void zapal() {
        System.out.println("Światło: " + nazwa + " zapalone!");
        zapalone = true;
    }

    public void zgas() {
        polecenieZgaszenia = true;
    }

    private void zgasPrivate() throws InterruptedException {
        sleep(5000);
        System.out.println("Światło: " + nazwa + " zgaszone!");
        zapalone = false;
    }

    @Override
    public void run() {
        while (true) {
            if (polecenieZgaszenia) {
                try {
                    zgasPrivate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                polecenieZgaszenia = false;
            }

            try { sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
