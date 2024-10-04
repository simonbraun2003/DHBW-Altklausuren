package de.dhbwka.stadt_land_fluss.classes;

public class Player {

    private String name;
    private int points;

    public Player(String name, int points) {
        this.name = name;
        this.points = 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
