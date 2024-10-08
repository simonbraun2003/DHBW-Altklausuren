package de.dhbwka.evasionator.classes;

public enum QuestionType {

    YES_NO("Yes/No Question"), WITNESS("Witness may be involved"), GENERAL("General question");

    final String label;

    QuestionType(String label) {
        this.label = label;
    }
}
