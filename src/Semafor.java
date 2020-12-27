public class Semafor extends Swiatlo {

    public Semafor(Polozenie polozenie, String nazwa) {
        super(polozenie, nazwa);
    }

    public void wyswietlJEDZ() {
        System.out.println("Światło: " + nazwa + " świeci JEDŹ!");
        setStop(false);
    }
}

