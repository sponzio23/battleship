package src.main.java.com.sponzio.battleship;

import javax.swing.*;

public class rules {
    // import icons
    static ImageIcon questionIcon = createImageIcon("/src/main/resources/question.png", "A question mark");

    public static void main(String[] args) {
        // all this just creates a window
        JFrame frame = new JFrame();
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        rulesDialogue(frame);

    }

    // method to create the rules dialogue
    public static void rulesDialogue(JFrame inputFrame){
        boolean rulesDone = false;

        while(!rulesDone){
            int rulesChoice = JOptionPane.showOptionDialog(inputFrame, "Would you like to hear the rules?",
                    null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    questionIcon, null, null);
            if(rulesChoice == 0 || rulesChoice == 1){
                rulesDone = true;
            }
            else if (rulesChoice == -1){
                JOptionPane.showMessageDialog(inputFrame,
                        "Please choose yes or no.", null,
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // method to import images as icons
    // this should eventually be moved to a different class
    protected static ImageIcon createImageIcon(String path,
                                        String description) {
        java.net.URL imgURL = rules.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}
