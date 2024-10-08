package de.dhbwka.evasionator.classes;

public interface AnswerGenerator {
    String generateAnswer(Question q, String topic);
    Evasion getRandomEvasion();
}
