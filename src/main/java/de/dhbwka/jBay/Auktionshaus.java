package de.dhbwka.jBay;

import java.util.ArrayList;
import java.util.List;

public class Auktionshaus {

    List<BieterTerminal> btList = new ArrayList<>();

    List<Auktion> aktiveAuktionen = new ArrayList<Auktion>();

    public void addAuktion(Auktion a) {
        aktiveAuktionen.add(a);
    }

    public void removeAuktion(Auktion a) {
        aktiveAuktionen.remove(a);
    }

    public List<Auktion> getAktiveAuktionen() {
        return aktiveAuktionen;
    }

    public void registerAuktionshaus(BieterTerminal bt){
        btList.add(bt);
    }

    public void unregisterAuktionshaus(BieterTerminal bt){
        btList.remove(bt);
    }

    public void updateTerminals(){
        //Todo: Terminals sollen ein update bekommen wenn Eingabe gemacht wurde
        for(BieterTerminal bt : btList){
            bt.createWindow();
        }
    }
}
