package de.dhbwka.dbvasion.classes;

import java.awt.*;

public enum StopStatus {

    ON_SCHEDULE(Color.BLACK, new Color(0, 150, 0)),
    REASONABLE(Color.BLACK, new Color(150, 150, 255)),
    DELAYED(Color.WHITE, Color.RED);

    private final Color textColor;
    private final Color backgroundColor;

    StopStatus(Color textColor, Color backgroundColor) {
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public static StopStatus get(int delay) {
        if (delay <= 0){
            return ON_SCHEDULE;
        }else if (delay < 6){
            return REASONABLE;
        }else {
            return DELAYED;
        }
    }
}
