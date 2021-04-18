import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class QuizCardPlayer {
    private JTextArea display;
    private ArrayList<QuizCard> cardList;
    private QuizCard currentCard;
    private int currentCardIndex;
    private JButton nextButton;
    private JButton loadButton;
    private boolean isShowAnswer;

    public JPanel cardPlayerTest(){
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);

        display = new JTextArea(10, 20);
        display.setFont(bigFont);

        display.setLineWrap(true);
        display.setEditable(false);

        JScrollPane qScroller = new JScrollPane(display);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        loadButton = new JButton("Load CardSet");
        loadButton.addActionListener(new LoadCardListener());
        nextButton = new JButton("Show Question");
        nextButton.addActionListener(new NextCardListener());

        mainPanel.add(qScroller);
        mainPanel.add(loadButton);
        mainPanel.add(nextButton);

        return mainPanel;
    }


    public class NextCardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isShowAnswer) {
                display.setText(currentCard.getAnswer());
                nextButton.setText("Next Card");
                isShowAnswer = false;
            } else {
                if (currentCardIndex < cardList.size()) {
                    showNextCard();
                } else {
                    display.setText("That was the last card");
                    nextButton.setEnabled(false);
                    currentCardIndex = 0;
                }
            }

        }

    }

    public class LoadCardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileOpen = new JFileChooser(
                    "C:\\Users\\fazlu\\Google Drive\\JavaStudy\\Portfolio\\quizcard\\data");
            fileOpen.showOpenDialog(null);
            loadFile(fileOpen.getSelectedFile());

        }

    }

    public void loadFile(File file) {
        cardList = new ArrayList<QuizCard>();
        // Using Try with resources due to closeable methods
        try (Reader source = new FileReader(file); BufferedReader data = new BufferedReader(source)) {
            String line = null;
            // Reads each line of selected file, passes it to makeCard() who transforms it
            // in a real card
            while ((line = data.readLine()) != null) {
                makeCard(line);
            }

        } catch (Exception e) {
            System.out.println("Couldn't read card file");
            e.printStackTrace();
        }

        showNextCard();
    }

    private void makeCard(String lineToParse) {
        String[] result = lineToParse.split("/");
        QuizCard card = new QuizCard(result[0], result[1]);
        cardList.add(card);
        System.out.println("Made a card");
    }

    private void showNextCard() {
        currentCard = cardList.get(currentCardIndex);
        currentCardIndex++;
        display.setText(currentCard.getQuestion());
        nextButton.setText("Show Answer");
        nextButton.setEnabled(true);
        isShowAnswer = true;

    }
}
