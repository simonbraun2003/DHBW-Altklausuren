package de.dhbwka.coronawarn.classes;

import java.util.Date;
import java.util.UUID;

public class Token {
    private String value;
    private Date date;

    public Token() {
        this.value = UUID.randomUUID().toString();
        this.date = new Date();
    }

    public Token(String val, Date dat) {
        this.value = val;
        this.date = dat;
    }

    public String toString() {
        return this.value +" @ "+ this.date.toString();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
