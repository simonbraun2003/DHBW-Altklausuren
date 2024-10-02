package de.dhbwka.dbvasion.classes;

import java.util.LinkedList;
import java.util.List;

public class TrainConnection {
    String name;
    String start; //Startbahnhof
    String end;
    boolean regional; //Regionalverkehr??
    double price;
    List<String> stops = new LinkedList<String>();

    public TrainConnection(String name, String start, String end, boolean regional, double price) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.regional = regional;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public boolean isRegional() {
        return regional;
    }

    public void setRegional(boolean regional) {
        this.regional = regional;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getStops() {
        return stops;
    }

    public void setStops(List<String> stops) {
        this.stops = stops;
    }
}
