package de.dhbwka.stadt_land_fluss.classes;

public enum ColumnTypes {


    CITY("Stadt"),
    COUNTRY("Land"),
    RIVER("Fluss"),
    PROFESSION("Beruf"),
    ANIMAL("Tier"),
    NAME("Vorname"),
    SPORT("Sportart"),
    FOOD("Lebensmittel"),
    BEVERAGE("Getränk"),
    GAME("Spiel");

    private final String titel;

    ColumnTypes(String titel) {
        this.titel = titel;
    }

    public String getTitel() {
        return titel;
    }
}
