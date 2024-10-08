package de.dhbwka.evasionator.classes;

import java.util.List;
import java.util.Random;

public class ListAnswerGenerator implements AnswerGenerator {

    private List<Evasion> evasions;


    public ListAnswerGenerator(List<Evasion> e) {
        this.evasions = e;
    }

    @Override
    public String generateAnswer(Question q, String topic) {
        List<QuestionType> currentTypes;
        int count = 0;
        for(Evasion e : evasions) {
            currentTypes = e.getTypes();
            for (QuestionType cT : currentTypes) {
                count ++;
                while (cT.equals(q.getType())) {
                    return Evasionator.replaceTopic(e.getText(), topic);
                }
                if(count == 10){
                    return "Keine Antwort ist auch eine Antwort";
                }
            }
        }
        return "´Fehler";
    }

    @Override
    public Evasion getRandomEvasion() {
        Random r = new Random();
        return evasions.get(r.nextInt(evasions.size()));
    }

    public List<Evasion> getEvasions() {
        return evasions;
    }

    public void setEvasions(List<Evasion> evasions) {
        this.evasions = evasions;
    }
}
