package de.dhbwka.speedy;

public class Question {

    private String questiontext;
    private String[] answers = new String[4];
    private int correctIndex;

    public Question(String questiontext, String[] answers, int correctIndex) {
        this.questiontext = questiontext;
        this.answers = answers;
        this.correctIndex = correctIndex;
    }

    public String getQuestiontext() {
        return questiontext;
    }

    public void setQuestiontext(String questiontext) {
        this.questiontext = questiontext;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public void setCorrectIndex(int correctIndex) {
        this.correctIndex = correctIndex;
    }
}
