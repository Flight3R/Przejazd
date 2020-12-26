public class Swiatlo extends ElementInfrastruktury {
    private boolean stop = false;
    private boolean polecenieZgaszenia = false;

    public Swiatlo(Polozenie polozenie, String nazwa) {
        super(polozenie, nazwa);
        start();
    }

    public boolean isStop() { return stop; }

    public void wyswietlSTOP() {
        System.out.println("Światło: " + nazwa + " świeci STOP!");
        stop = true;
    }

    public void wyswietlJEDZ() {
        polecenieZgaszenia = true;
    }

    private void wyswietlJEDZprivate() throws InterruptedException {
        sleep(10000);
        System.out.println("Światło: " + nazwa + " świeci JEDŹ!");
        stop = false;
    }

    @Override
    public void run() {
        while (true) {
            if (polecenieZgaszenia) {
                try {
                    wyswietlJEDZprivate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                polecenieZgaszenia = false;
            }

            try { sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
