package de.dhbwka.speedy;

public interface GameClient {

    String getPlayerName();
    int getPoints();
    void setQuestion(int questionIndex, Question question);
    void setRemainingSeconds(int seconds);
    void gameIsOver();
    void setAnswerState(int questionIndex, Status status);
}
