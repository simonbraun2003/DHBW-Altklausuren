package de.dhbwka.jBay;

public class Bieter {

    private String vorname;
    private String nachname;

    public Bieter(String vorname, String nachname) {
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public String getFullName(){
        return vorname + " " + nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }
}
