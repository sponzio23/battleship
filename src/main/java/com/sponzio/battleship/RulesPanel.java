package src.main.java.com.sponzio.battleship;

import javax.swing.*;

public class RulesPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        boolean rulesDone = false;

        while(!rulesDone){
            int rulesChoice = JOptionPane.showOptionDialog(frame, "Would you like to hear the rules?", null,
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if(rulesChoice == 0 || rulesChoice == 1){
                rulesDone = true;
            }
            else if (rulesChoice == -1){
                JOptionPane.showMessageDialog(frame,
                        "Please choose yes or no.", null,
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}
