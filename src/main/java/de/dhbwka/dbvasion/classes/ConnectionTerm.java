package de.dhbwka.dbvasion.classes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConnectionTerm {

    private List<TrainConnection> connections = new ArrayList<TrainConnection>();
    private int index;

    public ConnectionTerm(List<TrainConnection> connections, int index) throws InterruptedException {
        this.connections = connections;

        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(connections.size(), 1,5,5));
        int delayBefore = 0;
        for(int i=0; i<connections.size(); i++) {
            if(i == index){
                frame.setTitle(connections.get(i).getName());
            }
            Stop stop = connections.get(i).getStops().get(i);
            StopComponent stopComponent = new StopComponent(stop);
            if(i!= 0){
                Random rand = new Random();
                int delay = rand.nextInt(10);
                if(delay == 0){
                    delay = -rand.nextInt(5);
                }
                stopComponent.setDelay(delayBefore + delay);
                delayBefore = delayBefore + delay;
                if(delayBefore >= 10){
                    stopComponent.setReason(StopComponent.DELAY_REASONS[rand.nextInt(StopComponent.DELAY_REASONS.length)]);
                }
            }
            frame.add(stopComponent);

            //Todo: Thread Implementieren Aufgabe g)

        }

        frame.setVisible(true);
    }

    public List<TrainConnection> getConnections() {
        return connections;
    }

    public void setConnections(List<TrainConnection> connections) {
        this.connections = connections;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
