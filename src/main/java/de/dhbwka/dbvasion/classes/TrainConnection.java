package de.dhbwka.dbvasion.classes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TrainConnection {
    private String name;
    private String start; //Startbahnhof
    private String end;
    private boolean regional; //Regionalverkehr??
    private double price;
    private List<Stop> stops = new ArrayList<>();

    public TrainConnection(String name, String start, String end, boolean regional, double price) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.regional = regional;
        this.price = price;
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

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

}
