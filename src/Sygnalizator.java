public class Sygnalizator extends Swiatlo {

    private boolean polecenieJedz = false;

    public Sygnalizator(Polozenie polozenie, String nazwa) {
        super(polozenie, nazwa);
    }

    public void podajJEDZ() {
        polecenieJedz = true;
    }

    private void podajJEDZprivate() throws InterruptedException {
        sleep(10000);
        System.out.println("Światło: " + nazwa + " świeci JEDŹ!");
        setStop(false);
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
