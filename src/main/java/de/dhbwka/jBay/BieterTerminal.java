package de.dhbwka.jBay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class BieterTerminal {
    private Bieter bieter;
    private Auktionshaus ah;
    private Calendar calendar = Calendar.getInstance();

    public BieterTerminal(Bieter bieter, Auktionshaus ah) {
        this.bieter = bieter;
        this.ah = ah;
        this.ah.registerAuktionshaus(this);

        try {
            createWindow();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void createWindow() throws InterruptedException {
        JFrame frame = new JFrame();

        frame.setTitle(this.bieter.getFullName() + " Terminal");

        JPanel timePanel = new JPanel();
        JLabel timeLabel = new JLabel();

        timeLabel.setText(calendar.getTime().toString());
        timePanel.add(timeLabel);
        frame.add(timePanel, BorderLayout.NORTH);

        Runnable run = new Runnable() {
            public void run() {
                while (true) {
                    Calendar calendar = Calendar.getInstance();
                    timeLabel.setText(calendar.getTime().toString());
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(run).start();


        JPanel auktionPanel = new JPanel();
        auktionPanel.setLayout(new GridLayout(ah.getAktiveAuktionen().size(), 5));
        for (int i = 0; i < ah.getAktiveAuktionen().size(); i++) {
            JLabel warelb = new JLabel();
            warelb.setText(ah.getAktiveAuktionen().get(i).getWare().getTitel());
            auktionPanel.add(warelb);

            JLabel preislb = new JLabel();
            preislb.setText("" + ah.getAktiveAuktionen().get(i).getPreis());
            auktionPanel.add(preislb);

            JLabel bieterlb = new JLabel();
            if (ah.getAktiveAuktionen().get(i).getGebot() == null) {
                bieterlb.setText("----");
            } else {
                bieterlb.setText(ah.getAktiveAuktionen().get(i).getGebot().getBieter().getFullName());
            }
            auktionPanel.add(bieterlb);

            JLabel aktionsEndelb = new JLabel(ah.getAktiveAuktionen().get(i).getAuktionsEnde().getTime().toString());
            auktionPanel.add(aktionsEndelb);

            JButton gebotBtn = new JButton("Gebot");
            int finalI = i;
            gebotBtn.addActionListener(e -> {
                if (java.util.Calendar.getInstance().after(ah.getAktiveAuktionen().get(finalI).getAuktionsEnde())) {
                    System.out.println(ah.getAktiveAuktionen().get(finalI).getAuktionsEnde());
                    JOptionPane.showMessageDialog(null, "Die Auktion ist leider schon vorbei...");
                } else {
                    String input =
                            (String) JOptionPane.showInputDialog("Bitte neues Gebot eingeben. \n Mindestens " +
                                    (ah.getAktiveAuktionen().get(finalI).getPreis() + 1.0)); //Todo: Increment verknüpfen
                    if (input.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ungültige Eingabe");
                    } else if (ah.getAktiveAuktionen().get(finalI).gebotAbgegeben(new Gebot(Integer.parseInt(input), bieter))) {
                        JOptionPane.showMessageDialog(null, "Sie sind der Höchstbietende");
                        writeToFile(new Gebot(Integer.parseInt(input), bieter), ah, finalI );
                        ah.updateTerminals(bieterlb);
                    } else {
                        JOptionPane.showMessageDialog(null, "Gebot zu gering!");
                    }
                }
            });
            auktionPanel.add(gebotBtn);
        }
        frame.add(auktionPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setSize(1000, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void writeToFile(Gebot gebot, Auktionshaus ah, int index) {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime().toString() + " Gebot von " + gebot.getBieter().getFullName() + " für " +
                ah.getAktiveAuktionen().get(index).getWare().getTitel() + ": " + gebot.getHoechstBetrag() );

            try(BufferedWriter bw = new BufferedWriter(new FileWriter(
                    "C:\\Users\\simon\\Documents\\GitHub\\DHBW-Altklausuren\\src\\main\\java\\de\\dhbwka\\jBay\\auktionen.txt", true))){
                bw.write(calendar.getTime().toString() + " Gebot von " + gebot.getBieter().getFullName() + " für " +
                        ah.getAktiveAuktionen().get(index).getWare().getTitel() + ": " + gebot.getHoechstBetrag() );
                bw.newLine();
        } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public void update(JLabel bieterlb){
        for(int i = 0; i < ah.getAktiveAuktionen().size(); i++){
            if (ah.getAktiveAuktionen().get(i).getGebot() == null) {
                bieterlb.setText("----");
                System.out.println("test");
            } else {
                System.out.println(ah.getAktiveAuktionen().get(i).getGebot().getBieter().getFullName());
                bieterlb.setText(ah.getAktiveAuktionen().get(i).getGebot().getBieter().getFullName());
            }
        }
    }
}
