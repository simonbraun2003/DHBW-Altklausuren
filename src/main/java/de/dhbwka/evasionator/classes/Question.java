package de.dhbwka.evasionator.classes;

public class Question {

    private String text;
    private boolean start;
    QuestionType type;

    public Question(String text, boolean start, QuestionType type) {
        this.text = text;
        this.start = start;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }
}
