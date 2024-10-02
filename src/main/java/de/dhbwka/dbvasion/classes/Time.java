package de.dhbwka.dbvasion.classes;

public class Time {
    private int hour;
    private int minute;

    public Time(int hour, int minute) {
        this.setHour(hour);
        this.setMinute(minute);
    }

    public String toString(){
        return String.format("%02d:%02d", hour, minute);
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        if(hour >= 0 && hour < 24){
            this.hour = hour;
        }else {
            throw new DBException("Invalid hour");
        }
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        if(minute >= 0 && minute < 60){
            this.minute = minute;
        }else {
            throw new DBException("Invalid minute");
        }
    }
}
