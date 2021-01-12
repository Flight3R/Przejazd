package klasyAbstrakcyjne;

import lokacja.Polozenie;

import javax.swing.*;

public abstract class obiektSymulacji extends Thread {

    protected Polozenie polozenie;
    protected String nazwa;
    private ImageIcon ikona;
    private JLabel label;

    public obiektSymulacji(Polozenie polozenie, String nazwa, ImageIcon ikona) {
        this.polozenie = polozenie;
        this.nazwa = nazwa;
        this.ikona = ikona;
        label = new JLabel(ikona);
    }

    public Polozenie getPolozenie() {
        return polozenie;
    }

    public String getNazwa() { return nazwa; }

    public ImageIcon getIkona() {
        return ikona;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

}
