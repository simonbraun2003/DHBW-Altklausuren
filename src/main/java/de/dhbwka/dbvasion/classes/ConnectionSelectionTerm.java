package de.dhbwka.dbvasion.classes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConnectionSelectionTerm extends JFrame{

    List<TrainConnection> connections = new LinkedList<>();

    public ConnectionSelectionTerm(List<TrainConnection> connections) {
        this.connections = connections;

        this.setSize(100,100);
        this.add(new JPanel(), BorderLayout.CENTER);
        this.setVisible(true);
    }
}
