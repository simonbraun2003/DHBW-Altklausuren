package de.dhbwka.dbvasion.classes;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ConnectionSelectionTerm{

    List<TrainConnection> connections = new ArrayList<>();

    public ConnectionSelectionTerm(List<TrainConnection> connections) {
        this.connections = connections;

        JFrame frame = new JFrame();
        frame.setTitle("Connections");

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(30);
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        frame.add(namePanel, BorderLayout.NORTH);

        JPanel connectionPanel = new JPanel();
        connectionPanel.setLayout(new GridLayout(connections.size(),2,5,5));
        for (int i = 0; i < connections.size(); i++){
            JButton connectionButton = new JButton(connections.get(i).getName());
            if(connections.get(i).isRegional()){
                connectionButton.setIcon(DBVasion.createDLTicketIcon());
            }
            connectionPanel.add(connectionButton);
            int finalI1 = i;
            connectionButton.addActionListener(e -> {
                Random rand = new Random();
                int r = rand.nextInt(100);
                if(r <= 70){
                    JOptionPane.showMessageDialog(null,"This connection was cancelled");
                }else{
                    //JOptionPane.showMessageDialog(null,"Hier entseht eine Liste");
                    try {
                        ConnectionTerm connTerm = new ConnectionTerm(connections, finalI1);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            JButton buyBtn = new JButton("Buy Ticket");
            connectionPanel.add(buyBtn);
            int finalI = i;
            buyBtn.addActionListener(e -> {
                String[] opts = { "Yes", "No"};
                int n = JOptionPane.showOptionDialog(null,
                        "Do you want 'Super-Sparpreis' Ticket?",
                        "Discount?",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opts, opts[0]);
                if ( n == JOptionPane.YES_OPTION ) {
                    JOptionPane.showMessageDialog(null,
                            "Ticket for " + nameField.getText() + " (EUR " +
                                    connections.get(finalI).getPrice() * 0.2 + "-Super-Sparpreis)");
                    try(BufferedWriter bw = new BufferedWriter(new FileWriter(
                            "C:\\Users\\simon\\Documents\\GitHub\\DHBW-Altklausuren\\src\\main\\java\\de\\dhbwka\\dbvasion\\tickets.txt", true
                    ))){
                        bw.write("Ticket for " + nameField.getText() + " (EUR " +
                                connections.get(finalI).getPrice() * 0.2 + "-Super-Sparpreis)");
                        bw.newLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }else {
                    JOptionPane.showMessageDialog(null,
                            "Ticket for " + nameField.getText() + " (EUR " +
                                    connections.get(finalI).getPrice() +")");
                    try(BufferedWriter bw = new BufferedWriter(new FileWriter(
                            "C:\\Users\\simon\\Documents\\GitHub\\DHBW-Altklausuren\\src\\main\\java\\de\\dhbwka\\dbvasion\\tickets.txt", true
                    ))){
                        bw.write("Ticket for " + nameField.getText() + " (EUR " +
                                connections.get(finalI).getPrice() + ")");
                        bw.newLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        }
        frame.add(connectionPanel, BorderLayout.CENTER);

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
