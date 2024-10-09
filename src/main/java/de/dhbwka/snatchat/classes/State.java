package de.dhbwka.snatchat.classes;

public enum State {

    AVAILABLE ("Available"),
    AWAY ("Away"),
    DND ("Do not disturb");

    final String label;

    State(String lb){
        this.label = lb;
    }
}
