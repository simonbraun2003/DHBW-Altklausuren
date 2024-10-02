package de.dhbwka.speedy;

import java.awt.*;

public enum Status {
    ACTIVE(Color.ORANGE, 0, "aktuell gestellte Frage"),
    CORRECT(Color.GREEN, +1, "richtig beantwortete Frage"),
    WRONG(Color.RED, -1, "falsch beantwortete Frage"),
    PENDING(Color.WHITE, 0, "noch nicht gespielte/ausstehende Frage"),
    NO_ANSWER(Color.GRAY, 0, "Frage auf die ein anderer Spieler geantwortet hat");

    private final String label;
    private final int points;
    private final Color color;


    Status(Color color, int points, String label) {
        this.label = label;
        this.points = points;
        this.color = color;

    }

    public String getLabel() {
        return label;
    }

    public int getPoints() {
        return points;
    }

    public Color getColor() {
        return color;
    }
}
