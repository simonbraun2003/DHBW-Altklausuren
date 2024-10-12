package de.dhbwka.coronawarn.classes;

import java.awt.*;

public enum WarnStatus {

    UNKNOWN("Unknown", new Color(175, 175, 175)),
    OK("Ok", new Color(100,200,100)),
    ALARM("Possible encounter", new Color(255,100,100)),
    INFECTED("In quarantine", new Color(150,150,255));

    final String text;
    final Color color;

    WarnStatus(String txt, Color col) {
        this.text = txt;
        this.color = col;
    }
}
