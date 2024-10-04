package de.dhbwka.stadt_land_fluss.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private String anfangsbuschstabe;
    private int minSpalten;
    private int maxSpalten;
    private int restSek;
    private boolean running;
    private List<ColumnTypes> columns;
    private int numberOfColumns;

    public Game(int minSpalten, int maxSpalten, int restSek) {
        this.setMaxSpalten(maxSpalten, minSpalten);
        this.setMinSpalten(minSpalten);
        this.restSek = restSek;
    }

    public char createFirstCharacter() {
        Random random = new Random();
        return (char) (random.nextInt(26)+'a');
    }

    public List<ColumnTypes> createColumns(){
        List<ColumnTypes> columns = new ArrayList<>();
        Random random = new Random();
        this.numberOfColumns = random.nextInt(minSpalten, maxSpalten);
        columns.add(ColumnTypes.CITY);
        columns.add(ColumnTypes.COUNTRY);
        columns.add(ColumnTypes.RIVER);
        while(columns.size() != numberOfColumns){
            ColumnTypes randColumn = ColumnTypes.values()[random.nextInt(ColumnTypes.values().length)];
            if(!columns.contains(randColumn)){
                columns.add(randColumn);
            }
        }
        return columns;
    }

    public String getAnfangsbuschstabe() {
        return anfangsbuschstabe;
    }

    public void setAnfangsbuschstabe(String anfangsbuschstabe) {
        this.anfangsbuschstabe = anfangsbuschstabe;
    }

    public int getMinSpalten() {
        return minSpalten;
    }

    public void setMinSpalten(int minSpalten) {
        if(minSpalten >= 3){
            this.minSpalten = minSpalten;
        }else{
            this.minSpalten = 3;
        }
    }

    public int getMaxSpalten() {
        return maxSpalten;
    }

    public void setMaxSpalten(int maxSpalten, int minSpalten) {
        if(maxSpalten >= minSpalten){
            this.maxSpalten = maxSpalten;
        }else {
            this.maxSpalten = minSpalten;
        }
    }

    public int getRestSek() {
        return restSek;
    }

    public void setRestSek(int restSek) {
        this.restSek = restSek;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
