package work;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Worktime {
    private JButton buttonOblicz;
    private JPanel panel1;
    private JComboBox comboHstart;
    private JComboBox comboMstart;
    private JComboBox comboHend;
    private JComboBox comboMend;
    private JButton buttonDodaj;
    private JLabel dodano;
    private long suma;
    private long czas;

    public Worktime() {


        buttonOblicz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // JOptionPane.showMessageDialog(null, "Naciśnięto przycisk Oblicz");
                dodano.setText(DateFormat.getTimeInstance(DateFormat.MEDIUM).format(suma - 3600000));
                czas=0;
            }
        });
        buttonDodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // dodano.setText(String.valueOf(comboHstart.getSelectedItem()));
                String start = String.valueOf(comboHstart.getSelectedItem()) + ":" + String.valueOf(comboMstart.getSelectedItem());
                String koniec = String.valueOf(comboHend.getSelectedItem()) + ":" + String.valueOf(comboMend.getSelectedItem());

                try {
                    czas = timeStringToMillis(koniec)-timeStringToMillis(start);
                    if (czas<0) {
                        JOptionPane.showMessageDialog(null, "Niepoprawne dane");
                        return;
                    }
                    suma += czas;
                    JOptionPane.showMessageDialog(null, "Dodano");
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }


               // dodano.setText(start);
            }
        });
    }

    private void createUIComponents() {

    }

    public static long timeStringToMillis(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        //sdf.setTimeZone(TimeZone.getTimeZone("Europe/Warsaw"));
        Date date = sdf.parse("1970-01-01 " + time);
        return date.getTime();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Work Time Calc  *wersja 1.0*");
        frame.setContentPane(new Worktime().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
