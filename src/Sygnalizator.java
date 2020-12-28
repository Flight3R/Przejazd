public class Sygnalizator extends Swiatlo {

    private boolean polecenieZgaszenia = false;

    public Sygnalizator(Polozenie polozenie, String nazwa) {
        super(polozenie, nazwa);
    }

    public void podajJEDZ() {
        polecenieZgaszenia = true;
    }

    private void podajJEDZprivate() throws InterruptedException {
        sleep(10000);
        System.out.println("Światło: " + nazwa + " świeci JEDŹ!");
        setStop(false);
    }

    @Override
    public void run() {
        while (true) {
            if (polecenieZgaszenia) {
                try {
                    podajJEDZprivate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                polecenieZgaszenia = false;
            }
            try { sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
