package quizApp.view;


import quizApp.model.QuizCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class QuestionAnswerPanel  {
    private JTextArea question;
    private JTextArea answer;
    private ArrayList<QuizCard> cardList;

    public JPanel cardText(){
        JPanel textPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);
        question = new JTextArea(6, 20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(bigFont);

        JScrollPane qScroller = new JScrollPane(question);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        answer = new JTextArea(6, 20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(bigFont);

        JScrollPane aScroller = new JScrollPane(answer);
        aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        cardList = new ArrayList<QuizCard>();
        JLabel qLabel = new JLabel("Question:");
        JLabel aLabel = new JLabel("Answer:");

        textPanel.add(qLabel);
        textPanel.add(qScroller);
        textPanel.add(aLabel);
        textPanel.add(aScroller);

        return textPanel;
    }
}
