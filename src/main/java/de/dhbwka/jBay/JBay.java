package de.dhbwka.jBay;

public class JBay {
    public static void main(String[] args) {
        Auktionshaus jbay = new Auktionshaus();
        jbay.addAuktion(new Auktion(
                new Ware("Turnschuhe","Tolle Turnschuhe, kaum getragen"),2));
        jbay.addAuktion(new Auktion(
                    new Ware("iPad","Nagelneues iPad 3"),4));
        jbay.addAuktion(new Auktion(
                    new Ware("Currywurst","Scharf, ohne Pommes"),5));
                    // An dieser Stelle wird in Teilaufgabe c) erweitert
        //BieterTerminal bieterTerminal = new BieterTerminal(, jbay);
        BieterTerminal b1 = new BieterTerminal(new Bieter("Micky","Maus"),jbay);
        BieterTerminal b2 = new BieterTerminal(new Bieter("Donald","Duck"),jbay);
    }
}
