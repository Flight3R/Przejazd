package zadania;

import klasyAbstrakcyjne.obiektSymulacji;
import lokacja.Polozenie;
import obslugaPrzejazdu.KontrolaRuchu;
import obslugaPrzejazdu.Przejazd;
import podlozaTransportowe.PasRuchu;
import podlozaTransportowe.Tor;
import pojazdy.Auto;
import pojazdy.Pociag;
import urzadzeniaKolejowe.Czujnik;
import urzadzeniaKolejowe.Semafor;
import urzadzeniaUliczne.Rogatka;
import urzadzeniaUliczne.Sygnalizator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Thread.sleep;

public class PanelSytuacji extends JFrame implements Runnable {

    int deltaX=2500;
    int deltaY=-500;

    ArrayList<obiektSymulacji> listaStatyczna = new ArrayList<>();
    ArrayList<obiektSymulacji> listaDynamiczna = new ArrayList<>();

    private final Przejazd przejazd;

    private final JLabel innerPanel = new JLabel();
    private final JScrollPane scrollPane = new JScrollPane(innerPanel);

    public PanelSytuacji(Przejazd przejazd) {
        this.przejazd = przejazd;
        listaStatyczna.add(przejazd);
        for (Tor tor : przejazd.getListaTorow()) {
            for (Semafor semafor : tor.getSemaforySBL())
                listaStatyczna.add(semafor);
            for (Czujnik czujnik : tor.getCzujnikiNajazdoweSBL())
                listaStatyczna.add(czujnik);
            for (Czujnik czujnik : tor.getCzujnikiZjazdoweSBL())
                listaStatyczna.add(czujnik);
            listaStatyczna.add(tor);
            listaStatyczna.add(tor.getCzujnikNajazdowySSP());
            listaStatyczna.add(tor.getCzujnikZjazdowySSP());
            listaStatyczna.add(tor.getTarczaSSP());
        }
        for (PasRuchu pas : przejazd.getListaPasow()) {
            listaStatyczna.add(pas);
            listaStatyczna.add(pas.getSygnalizacja());
            listaStatyczna.add(pas.getRogatka());
        }

        setPreferredSize(new Dimension(1300,800));
        setBounds(0,0,1280,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("test");

        getContentPane().setLayout(new FlowLayout());

        innerPanel.setPreferredSize(new Dimension(5000,1000));

        scrollPane.setPreferredSize(new Dimension(1280,720));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollPane);

        scrollPane.getViewport().setViewPosition(new Point(scrollPane.getViewport().getViewSize().width/2-640, scrollPane.getViewport().getViewSize().height/2-360));

        for (obiektSymulacji obiekt : listaStatyczna) {
            obiekt.getLabel().setBounds((int)obiekt.getPolozenie().getX()+deltaX-obiekt.getLabel().getIcon().getIconWidth()/2, -(int)(obiekt.getPolozenie().getY()+deltaY+obiekt.getLabel().getIcon().getIconHeight()/2), obiekt.getLabel().getPreferredSize().width, obiekt.getLabel().getPreferredSize().height);
            innerPanel.add(obiekt.getLabel());
        }

        for (Tor tor : przejazd.getListaTorow()) {
            for (Pociag pociag : tor.getRozkladPociagow().getTabelaPociagow()) {
                listaDynamiczna.add(pociag);
                innerPanel.add(pociag.getLabel(),0);
            }
        }
    }
    public void odswierz() {
        for (PasRuchu pas : przejazd.getListaPasow()) {
            for (Auto auto : pas.getListaAut()) {
                if (!listaDynamiczna.contains(auto)) {
                    listaDynamiczna.add(auto);
                    auto.getLabel().setText(auto.getNazwa());
                    innerPanel.add(auto.getLabel(),0);
                }
            }
        }

        for (Pociag pociag : przejazd.getPociagiObecne().getTabelaPociagow()) {
            if (!listaDynamiczna.contains(pociag)) {
                listaDynamiczna.add(pociag);
                pociag.getLabel().setText(pociag.getNazwa());
                innerPanel.add(pociag.getLabel(), 0);
            }
        }

        for (obiektSymulacji obiekt : listaDynamiczna) {
            if (obiekt.getLabel().getIcon() == null) {

            } else {
                if (obiekt instanceof Pociag) {
                    if (((Pociag) obiekt).getTor().getZwrot().equals("prawo")) {
                        obiekt.getLabel().setBounds((int) obiekt.getPolozenie().getX() + deltaX - ((Pociag) obiekt).getDlugosc(), -(int) (obiekt.getPolozenie().getY() + deltaY + obiekt.getLabel().getIcon().getIconHeight() / 2), obiekt.getLabel().getPreferredSize().width, obiekt.getLabel().getPreferredSize().height);
                    } else {
                        obiekt.getLabel().setBounds((int) obiekt.getPolozenie().getX() + deltaX, -(int) (obiekt.getPolozenie().getY() + deltaY + obiekt.getLabel().getIcon().getIconHeight() / 2), obiekt.getLabel().getPreferredSize().width, obiekt.getLabel().getPreferredSize().height);
                    }
                } else if (obiekt instanceof Auto) {
                    if (((Auto) obiekt).getPas().getZwrot().equals("gora")) {
                        obiekt.getLabel().setBounds((int) obiekt.getPolozenie().getX() + deltaX - obiekt.getLabel().getIcon().getIconWidth() / 2, -(int) (obiekt.getPolozenie().getY() + deltaY), obiekt.getLabel().getPreferredSize().width, obiekt.getLabel().getPreferredSize().height);
                    } else {
                        obiekt.getLabel().setBounds((int) obiekt.getPolozenie().getX() + deltaX - obiekt.getLabel().getIcon().getIconWidth() / 2, -(int) (obiekt.getPolozenie().getY() + deltaY + ((Auto) obiekt).getDlugosc()), obiekt.getLabel().getPreferredSize().width, obiekt.getLabel().getPreferredSize().height);
                    }
                } else {
                    obiekt.getLabel().setBounds((int) obiekt.getPolozenie().getX() + deltaX - obiekt.getLabel().getIcon().getIconWidth() / 2, -(int) (obiekt.getPolozenie().getY() + deltaY + obiekt.getLabel().getIcon().getIconHeight() / 2), obiekt.getLabel().getIcon().getIconWidth(), obiekt.getLabel().getIcon().getIconHeight());
                }
            }
        }
    }

    public void run() {
        while (true) {
            odswierz();
            try {
                sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    public static void main(String[] args) {
//
//
//    }
}
