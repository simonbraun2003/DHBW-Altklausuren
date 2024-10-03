package de.dhbwka.jBay;

import java.util.HashMap;

public class Gebot {

    private double hoechstBetrag;
    private Bieter bieter;

    public Gebot(double hoechstBetrag, Bieter bieter) {
        this.hoechstBetrag = hoechstBetrag;
        this.bieter = bieter;
    }

    public double getHoechstBetrag() {
        return hoechstBetrag;
    }

    public void setHoechstBetrag(double hoechstBetrag) {
        this.hoechstBetrag = hoechstBetrag;
    }

    public Bieter getBieter() {
        return bieter;
    }

    public void setBieter(Bieter bieter) {
        this.bieter = bieter;
    }
}
