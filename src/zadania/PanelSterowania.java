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
                for (PasRuchu pas : przejazd.getListaPasow()) {
                    pas.getSygnalizacja().podajSTOP();
                    pas.getRogatka().zamknij();
                }
            }
        });
        otwieranieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
    if (przejazd.getPociagiObecne().getTabelaPociagow().size() > 0) {
        p1N.setText(przejazd.getPociagiObecne().getTabelaPociagow().get(0).getNazwa());
        p1G.setText(Integer.toString(przejazd.getPociagiObecne().getTabelaPociagow().get(0).getCzasPrzyjazdu()));
        p1S.setText(Double.toString((int)przejazd.getPociagiObecne().getTabelaPociagow().get(0).getSpoznienie()));
    }
    if (przejazd.getPociagiObecne().getTabelaPociagow().size() > 1) {
        p2N.setText(przejazd.getPociagiObecne().getTabelaPociagow().get(1).getNazwa());
        p2G.setText(Integer.toString(przejazd.getPociagiObecne().getTabelaPociagow().get(1).getCzasPrzyjazdu()));
        p2S.setText(Double.toString((int)przejazd.getPociagiObecne().getTabelaPociagow().get(1).getSpoznienie()));
    }
    if (przejazd.getPociagiObecne().getTabelaPociagow().size() > 2) {
        p3N.setText(przejazd.getPociagiObecne().getTabelaPociagow().get(2).getNazwa());
        p3G.setText(Integer.toString(przejazd.getPociagiObecne().getTabelaPociagow().get(2).getCzasPrzyjazdu()));
        p3S.setText(Double.toString((int)przejazd.getPociagiObecne().getTabelaPociagow().get(2).getSpoznienie()));
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
