package klasyAbstrakcyjne;

import lokacja.Polozenie;

import javax.swing.*;

public abstract class obiektSymulacji extends Thread {

    protected Polozenie polozenie;
    protected String nazwa;
    private JLabel label;

    public obiektSymulacji(Polozenie polozenie, String nazwa, Icon ikona) {
        this.polozenie = polozenie;
        this.nazwa = nazwa;
        label = new JLabel(ikona);
    }

    public Polozenie getPolozenie() {
        return polozenie;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
}
