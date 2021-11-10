package src.main.java.com.sponzio.battleship;

import javax.swing.*;

public class rules {
    // import icons
    static Icon questionIcon = GraphicsBattleship.createImageIcon("/src/main/resources/question.png",
            "A question mark");

    // method to create the rules dialogue
    public static void rulesDialogue() {
        boolean rulesDone = false;
        String rulesText =
                """
                        WELCOME TO BATTLESHIP!

                        OBJECT OF THE GAME
                        To sink your opponents ships before they can sink yours. You will be playing against a computer
                        that operates fully randomly.

                        PREPARE FOR BATTLE
                        You will be prompted if you would like to use the default values. If you choose yes, you will play
                        with a 3x3 board and 3 ships. You will have 3 shots each round. If you choose no, you can set these
                        values as you desire. Currently each ship only takes up a 1x1 space. This might be changed in
                        future versions. Once you have set all these values you will be prompted to place each of your
                        ships by entering coordinates. Values that are not integers or that don’t exist on the board will
                        not be accepted. Once you have placed your ships the computer will place its ships.

                        BATTLE
                        You and the computer will take turns shooting. You will go first. Before each time it becomes your
                        turn you will see two grids. One is labeled Player Board and the other is labeled Player Ships.
                        Player Ships shows your ships. A 0 means there is nothing there, a 1 means one of your ships is
                        there, a 2 means the computer has fired at that location and missed, and a 3 means that location
                        had a ship that the computer shot at and hit. Player board shows your previous shots. A 0 means
                        you have not yet shot there, a 2 means you missed, and a 3 means you hit something. When it is your
                        turn to shoot you will be prompted to enter coordinates similar to how you placed your ships.
                        Yet again, values that are not integers or that don’t exist on the board will not be accepted.
                        In addition, locations you have already shot at cannot be shot at again. Once you have taken your
                        shot you will be informed if it is a hit or miss. This process will repeat for as many shots as
                        you have. After you have shot, the computer will shoot. The computer randomly selects and announces
                        its shots and then informs you if they are a hit or a miss.

                        WINNING/LOSING THE GAME
                        If at any point you or the computer has no ships remaining, the game will end and the winner will
                        be announced. If you would like to play again, simply click the run button again.

                        These rules are based on Hasbro's official rules for Battleship.
                        (https://www.hasbro.com/common/instruct/Battleship.PDF)

                        ENJOY!
                        """;

        while (!rulesDone) {
            int rulesChoice = JOptionPane.showOptionDialog(GraphicsBattleship.frame,
                    "Would you like to hear the rules?", null, JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, questionIcon, null, null);
            if (rulesChoice == 0) {
                JOptionPane.showMessageDialog(GraphicsBattleship.frame, rulesText, "Battleship Rules",
                        JOptionPane.PLAIN_MESSAGE);
                rulesDone = true;
            } else if (rulesChoice == 1) {
                rulesDone = true;
            } else if (rulesChoice == -1) {
                JOptionPane.showMessageDialog(GraphicsBattleship.frame, "Please choose yes or no.",
                        null, JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
