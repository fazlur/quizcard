package quizApp.view;


import quizApp.model.QuizCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;


public class QuizCardBuilder {
    private JTextArea question;
    private JTextArea answer;
    private ArrayList<QuizCard> cardList;

    public JPanel cardBuilderTest(){
        JPanel mainPanel = new JPanel();
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

        JButton saveButton = new JButton("Save Card");
        saveButton.addActionListener(new SaveMenuListener());
        JButton nextButton = new JButton("New Card");
        nextButton.addActionListener(new NextCardListener());

        cardList = new ArrayList<QuizCard>();

        JLabel qLabel = new JLabel("Question:");
        JLabel aLabel = new JLabel("Answer:");

        mainPanel.add(qLabel);
        mainPanel.add(qScroller);
        mainPanel.add(aLabel);
        mainPanel.add(aScroller);
        mainPanel.add(saveButton);
        mainPanel.add(nextButton);



        return mainPanel;
    }


    public class NextCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);
            clearCard();
        }
    }

    public class SaveMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);

            JFileChooser fileSave = new JFileChooser(
                    "C:\\Users\\fazlu\\Google Drive\\JavaStudy\\Portfolio\\quizcard\\data");

            fileSave.showSaveDialog(null);

            // Brings up dialog box for user to choose and save file
            saveFile(fileSave.getSelectedFile());

        }
    }

    public class NewMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cardList.clear();
            clearCard();

        }
    }

    // Clearing the text in question and answer text areas, and reseting the focus
    // to the question area
    private void clearCard() {
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }

    // Method to save files
    private void saveFile(File file) {
        // try-with-resources which close methods that are closable without the need of
        // a finally, note the conditions inside the () of the try
        try (Writer source = new FileWriter(file); BufferedWriter data = new BufferedWriter(source)) {
            for (QuizCard card : cardList) {
                // Go through ArrayList and save questions and answers separated by an / and
                // new line at end of answer
                data.write(card.getQuestion() + "/");
                data.write(card.getAnswer() + "\n");
            }
        } catch (Exception e) {
            System.err.println("Couldn't write cardList out");
            e.printStackTrace();
        }
    }


}
