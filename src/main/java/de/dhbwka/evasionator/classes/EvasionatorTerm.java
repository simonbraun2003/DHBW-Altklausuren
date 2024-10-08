package de.dhbwka.evasionator.classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvasionatorTerm extends JFrame {

    private QuestionCatalogue questionCatalogue;
    private AnswerGenerator generator;
    private QuestionnaireTerm questionnaireTerm;
    private JTextField[] uiTextFields = new JTextField[2];
    private String[] labels = {"Topic:", "Question Count:"};

    private Map<String, String> qaMap = new HashMap<String, String>();

    public EvasionatorTerm(QuestionCatalogue qc, AnswerGenerator gen, QuestionnaireTerm questionnaireTerm) {
        this.questionCatalogue = qc;
        this.generator = gen;
        this.questionnaireTerm = questionnaireTerm;

        this.setTitle("Evasionator");

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(2, 2));
        for (int i = 0; i < 2; i++) {
            this.uiTextFields[i] = new JTextField(20);
            textPanel.add(new JLabel(labels[i]));
            textPanel.add(uiTextFields[i]);
        }
        this.add(textPanel);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        JButton generateBtn = new JButton("Generate");
        JButton randomBtn = new JButton("Random Answer");
        btnPanel.add(generateBtn);
        btnPanel.add(randomBtn);
        this.add(btnPanel, BorderLayout.SOUTH);

        generateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Evasionator.containsUnreasonableKeyword(uiTextFields[0].getText())){
                    JOptionPane.showMessageDialog(null, "Sorry, the Topic " + uiTextFields[0].getText() + " is forbidden");
                }else{
                    List<Question> questionList = questionCatalogue.getQuestions(Integer.parseInt(uiTextFields[1].getText()));
                    for(int i = 0; i < questionList.size(); i++){
                        qaMap.put(questionList.get(i).getText(), generator.generateAnswer(
                                questionList.get(i), uiTextFields[0].getText()));
                    }
                    questionnaireTerm.setQuestionnaire(qaMap.toString());
                }
            }
        });

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
    }
}
