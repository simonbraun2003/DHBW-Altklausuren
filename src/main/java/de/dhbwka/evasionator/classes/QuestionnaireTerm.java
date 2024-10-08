package de.dhbwka.evasionator.classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class QuestionnaireTerm extends JFrame {

    private JTextArea textArea;

    public QuestionnaireTerm() {
        this.setTitle("Questionnaire");

        textArea = new JTextArea();
        textArea.setText("Test \n Test \n Test \n Test \n Test \n Test");
        textArea.setEditable(false);
        this.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        JButton saveBtn = new JButton("Save");
        btnPanel.setBackground(Color.GRAY);
        btnPanel.add(saveBtn);
        this.add(btnPanel, BorderLayout.SOUTH);

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textArea.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Leerer Katalog kann nicht gespeichert werden!");
                }else{
                    try(BufferedWriter bw = new BufferedWriter(new FileWriter(
                            "C:\\Users\\simon\\Documents\\GitHub\\DHBW-Altklausuren\\src\\main\\java\\de\\dhbwka\\evasionator\\questionnaires.txt", true))){
                        bw.write(textArea.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
    }

    public void setQuestionnaire(String text) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                int count = 10;
                textArea.setText(text);
                while(count > 0){
                    System.out.println(count--);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                textArea.setText("");
            }
        });
        thread.start();
    }
}
