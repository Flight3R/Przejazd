import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Przejazd1GUI {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JPanel mainpannel;
    private JPanel przejazd;
    private JButton dodajPociągButton;
    private JButton usuńPociągButton;
    private JPanel panel;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Przejazd1GUI");
        frame.setContentPane(new Przejazd1GUI().mainpannel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
