package de.dhbwka.speedy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private List<Question> questionPool = new ArrayList<Question>();
    private List<Question> gameQuestions = new ArrayList<Question>();
    private int numberOfQuestions;

    public Game(List<Question> questionPool, int numberOfQuestions) {
        this.questionPool = questionPool;
        this.numberOfQuestions = numberOfQuestions;

        Random rand = new Random();
        try{
            gameQuestions.add(questionPool.get(rand.nextInt(11)));//Todo: Duplikate vermeiden.
        }catch(Exception e){
            //Todo: GameException schreiben und werfen.
            throw new GameException("Too few questions available");
        }
    }

    public void registerClient(GameClient client) {

    }

    //public int getQuestionsCount() {

    //}

    public void startGame(){

    }

    public void answerSelect(GameClient client, int index){

    }

    public List<Question> getQuestionPool() {
        return questionPool;
    }

    public void setQuestionPool(List<Question> questionPool) {
        this.questionPool = questionPool;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }
}
