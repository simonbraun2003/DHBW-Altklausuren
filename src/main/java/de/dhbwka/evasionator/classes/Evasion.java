package de.dhbwka.evasionator.classes;

import java.util.List;

public class Evasion {

    private String text;
    private List<QuestionType> types;

    public Evasion(String text, List<QuestionType> types) {
        this.text = text;
        this.types = types;
    }

    public boolean isEvation(Question q) {
        for (QuestionType t : types) {
            return t.equals(q.getType());
        }
        return false;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<QuestionType> getTypes() {
        return types;
    }

    public void setTypes(List<QuestionType> types) {
        this.types = types;
    }
}
