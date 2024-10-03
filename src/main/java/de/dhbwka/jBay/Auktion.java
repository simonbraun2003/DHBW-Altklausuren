package de.dhbwka.jBay;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Auktion {
    private Ware ware;
    private Gebot gebot;
    private double preis;
    private Calendar auktionsEnde = Calendar.getInstance();
    private static double increment;
    private int anzahlGebote;

    public Auktion(Ware ware){
        this.ware = ware;
        this.preis = 0.0;
        this.increment = 1.0;
        this.anzahlGebote = 0;
    }

    public Auktion(Ware ware, int dauer){
        this.ware = ware;
        this.preis = 0.0;
        this.anzahlGebote = 0;
        this.gebot = null;
        this.auktionsEnde.setTimeInMillis(System.currentTimeMillis() + 60000L * dauer);
    }

    public boolean gebotAbgegeben(Gebot gebot){
        if(gebot.getHoechstBetrag() < this.preis + this.increment){
            return false;
        } else if (this.anzahlGebote == 0) {
            this.anzahlGebote++;
            this.preis = increment;
            this.gebot = new Gebot(gebot.getHoechstBetrag(), gebot.getBieter());
            //this.gebot.setHoechstBetrag(gebot.getHoechstBetrag());
        }
        if(this.gebot.getBieter().equals(gebot.getBieter())){
            this.gebot.setHoechstBetrag(gebot.getHoechstBetrag());
        }
        if(gebot.getHoechstBetrag() >= (this.preis+this.increment) && gebot.getHoechstBetrag() <= this.gebot.getHoechstBetrag()){
            if(gebot.getHoechstBetrag()+this.increment >= this.gebot.getHoechstBetrag()){
                this.preis = gebot.getHoechstBetrag()+this.increment;
            }
        }
        if(gebot.getHoechstBetrag() >= (this.preis+this.increment) && gebot.getHoechstBetrag() > this.gebot.getHoechstBetrag()){
            if(gebot.getHoechstBetrag() <= this.gebot.getHoechstBetrag()+ increment){
                this.preis = this.gebot.getHoechstBetrag()+this.increment;
            }else{
                this.preis = gebot.getHoechstBetrag();
            }
            this.gebot.setHoechstBetrag(gebot.getHoechstBetrag());
        }
        return true;
    }

    public String calenderToString(){
        return auktionsEnde.getTime().toString();
    }

    public Ware getWare() {
        return ware;
    }

    public void setWare(Ware ware) {
        this.ware = ware;
    }

    public Gebot getGebot() {
        return gebot;
    }

    public void setGebot(Gebot gebot) {
        this.gebot = gebot;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public Calendar getAuktionsEnde() {
        return auktionsEnde;
    }

    public void setAuktionsEnde(Calendar auktionsEnde) {
        this.auktionsEnde = auktionsEnde;
    }

    public static double getIncrement() {
        return increment;
    }

    public static void setIncrement(double increment) {
        Auktion.increment = increment;
    }

    public int getAnzahlGebote() {
        return anzahlGebote;
    }

    public void setAnzahlGebote(int anzahlGebote) {
        this.anzahlGebote = anzahlGebote;
    }
}
