package zadania;

import obslugaPrzejazdu.Przejazd;
import podlozaTransportowe.PasRuchu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

public class PanelSterowania extends JFrame implements Runnable {
    private JPanel panel1;
    private JTextField p1N;
    private JTextField p2N;
    private JTextField p3N;
    private JTextField p1G;
    private JTextField p2G;
    private JTextField p3G;
    private JTextField p1S;
    private JTextField p2S;
    private JTextField p3S;
    private JTextField godzinaTextField;
    private JRadioButton ZALRadioButton;
    private JRadioButton WYLRadioButton;
    private JButton zamykanieButton;
    private JButton otwieranieButton;
    private JTextField p4S;
    private JTextField p4G;
    private JTextField p4N;
    private JButton dodajPociagButton;
    private Przejazd przejazd;

    public PanelSterowania(Przejazd przejazd) {
        this.przejazd = przejazd;
        setContentPane(panel1);
        ZALRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                przejazd.setSterowanieAutomatyczne(true);
            }
        });
        WYLRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                przejazd.setSterowanieAutomatyczne(false);
            }
        });
        zamykanieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WYLRadioButton.setSelected(true);
                przejazd.setSterowanieAutomatyczne(false);
                for (PasRuchu pas : przejazd.getListaPasow()) {
                    pas.getSygnalizacja().podajSTOP();
                    pas.getRogatka().zamknij();
                }
            }
        });
        otwieranieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WYLRadioButton.setSelected(true);
                przejazd.setSterowanieAutomatyczne(false);
                for (PasRuchu pas : przejazd.getListaPasow()) {
                    pas.getSygnalizacja().podajJEDZ();
                    pas.getRogatka().otworz();
                }
            }
        });

        ZALRadioButton.setSelected(true);


    }
public void odswierz() {
    godzinaTextField.setText((int)przejazd.getCzas()/3600 + ":" + (int)przejazd.getCzas()/60 + ":" + (int)przejazd.getCzas()%60);
    if (przejazd.getRozklad().getTabelaPociagow().size() > 0) {
        p1N.setText(przejazd.getRozklad().getTabelaPociagow().get(0).getNazwa());
        p1G.setText(Integer.toString(przejazd.getRozklad().getTabelaPociagow().get(0).getCzasPrzyjazdu()));
        p1S.setText(Double.toString((int)przejazd.getRozklad().getTabelaPociagow().get(0).getSpoznienie()));
    }
    if (przejazd.getRozklad().getTabelaPociagow().size() > 1) {
        p2N.setText(przejazd.getRozklad().getTabelaPociagow().get(1).getNazwa());
        p2G.setText(Integer.toString(przejazd.getRozklad().getTabelaPociagow().get(1).getCzasPrzyjazdu()));
        p2S.setText(Double.toString((int)przejazd.getRozklad().getTabelaPociagow().get(1).getSpoznienie()));
    }
    if (przejazd.getRozklad().getTabelaPociagow().size() > 2) {
        p3N.setText(przejazd.getRozklad().getTabelaPociagow().get(2).getNazwa());
        p3G.setText(Integer.toString(przejazd.getRozklad().getTabelaPociagow().get(2).getCzasPrzyjazdu()));
        p3S.setText(Double.toString((int)przejazd.getRozklad().getTabelaPociagow().get(2).getSpoznienie()));
    }
    if (przejazd.getRozklad().getTabelaPociagow().size() > 3) {
        p4N.setText(przejazd.getRozklad().getTabelaPociagow().get(3).getNazwa());
        p4G.setText(Integer.toString(przejazd.getRozklad().getTabelaPociagow().get(3).getCzasPrzyjazdu()));
        p4S.setText(Double.toString((int)przejazd.getRozklad().getTabelaPociagow().get(3).getSpoznienie()));
    }
}

    @Override
    public void run() {
        while (true) {
            odswierz();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
