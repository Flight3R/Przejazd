public class Sygnalizator extends ElementInfrastruktury implements Swiatlo {

    boolean stop = false;
    private boolean polecenieJedz = false;

    public Sygnalizator(Polozenie polozenie, String nazwa) {
        super(polozenie, nazwa);
        start();
    }

    public void podajSTOP() {
        stop = true;
        System.out.println("Sygnalizator: " + nazwa + " świeci STOP!");
    }

    public void podajJEDZ() {
        polecenieJedz = true;
    }

    public boolean isStop() { return stop; }

    private void podajJEDZprivate() throws InterruptedException {
        sleep(5000);
        stop = false;
        System.out.println("Sygnalizator: " + nazwa + " świeci JEDŹ!");

    }

    @Override
    public void run() {
        while (true) {
            if (polecenieJedz) {
                try {
                    podajJEDZprivate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                polecenieJedz = false;
            }
            try { sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
