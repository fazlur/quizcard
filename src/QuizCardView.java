import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizCardView {

    private JFrame mainViewFrame = new JFrame();
    private JPanel cardPlayerPanel = new QuizCardPlayer().cardPlayerTest();
    private JPanel cardBuilderPanel = new QuizCardBuilder().cardBuilderTest();

    private void execute(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem createCard = new JMenuItem("Create");
        JMenuItem loadCard = new JMenuItem("Load");

        createCard.addActionListener(new CreateCard());
        loadCard.addActionListener(new LoadCard());

        menu.add(createCard);
        menu.add(loadCard);
        menuBar.add(menu);

        mainViewFrame.setJMenuBar(menuBar);
        mainViewFrame.setSize(300,300);
        mainViewFrame.setVisible(true);
        mainViewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class LoadCard implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(cardBuilderPanel.isDisplayable()) {

                mainViewFrame.remove(cardBuilderPanel);
            }

            mainViewFrame.add(cardPlayerPanel);
            mainViewFrame.setSize(500,550);
            mainViewFrame.revalidate();
            mainViewFrame.repaint();
            System.out.println("I am on load mode");
        }
    }

    private class CreateCard implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(cardPlayerPanel.isDisplayable()) {
                mainViewFrame.remove(cardPlayerPanel);
            }

            mainViewFrame.setSize(500,550);
            mainViewFrame.add(cardBuilderPanel);
            mainViewFrame.revalidate();
            mainViewFrame.repaint();
            System.out.println("I am on create mode");
        }
    }

    public static void main(String[] args) {

       QuizCardView play = new QuizCardView();
       play.execute();


    }


}
