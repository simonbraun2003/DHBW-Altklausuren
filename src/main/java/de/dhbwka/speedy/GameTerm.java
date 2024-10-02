package de.dhbwka.speedy;

public class GameTerm implements GameClient{

    String name;
    Game game;
    int points;

    @Override
    public String getPlayerName() {
        return this.name;
    }

    @Override
    public int getPoints() {
        if(this.points > 0){
            return 0;
        }else {
            return this.points;
        }
    }

    @Override
    public void setQuestion(int questionIndex, Question question) {
       // question.set
    }

    @Override
    public void setRemainingSeconds(int seconds) {

    }

    @Override
    public void gameIsOver() {

    }

    @Override
    public void setAnswerState(int questionIndex, Status status) {

    }
}
