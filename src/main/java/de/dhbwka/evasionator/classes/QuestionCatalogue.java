package de.dhbwka.evasionator.classes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class QuestionCatalogue {

    private boolean start;
    private List<Question> startQuestions = new LinkedList<>();
    private List<Question> questions = new LinkedList<>();

    public QuestionCatalogue(){

    }

    public void addQuestion(Question q){
        if (q.isStart() == start){
            startQuestions.add(q);
        }else {
            questions.add(q);
        }
    }

    public List<Question> getStartQuestions(){
        return startQuestions;
    }

    public List<Question> getQuestions(){
        return questions;
    }

    public List<Question> getQuestions(int index){
        Random r = new Random();
        List<Question> returnList = new ArrayList<>();
        if(this.getQuestions().size() < index){
            throw new EvasionException("Nicht genügend Fragen für gewünschte Größe enthalten");
        }
        returnList.add(startQuestions.get(r.nextInt(startQuestions.size())));
        for(int i = 1; i < index -1; i++){
            returnList.add(questions.get(r.nextInt(questions.size())));
        }
        return returnList;
    }

}
