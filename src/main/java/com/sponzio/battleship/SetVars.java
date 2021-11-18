package src.main.java.com.sponzio.battleship;

import javax.swing.*;

public class SetVars {
    public static void setVars() {
        Icon questionIcon = createImageIcon();

        boolean varsDone = false;
        while (!varsDone) {
            int defaultVarsChoice = JOptionPane.showOptionDialog(Battleship.frame,
                    "Would you like to use the default variables?",
                    null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    questionIcon, null, null);
            if (defaultVarsChoice == 0) {
                Battleship.numRows = 3;
                Battleship.numCols = 3;
                Battleship.numShips = 3;
                Battleship.numShots = 3;
                varsDone = true;
            }
            else if (defaultVarsChoice == 1) {
                SpinnerNumberModel rowsSpinnerModel = new SpinnerNumberModel(1, 1, 20,1);
                JSpinner rowsChooser = new JSpinner(rowsSpinnerModel);
                rowsChooser.setEditor(new JSpinner.DefaultEditor(rowsChooser));

                SpinnerNumberModel colsSpinnerModel = new SpinnerNumberModel(1, 1, 20,1);
                JSpinner colsChooser = new JSpinner(colsSpinnerModel);
                colsChooser.setEditor(new JSpinner.DefaultEditor(colsChooser));

                Object[] firstVarsChoiceMessage = {
                        "   Number of Rows (1-20):", rowsChooser,
                        "   Number of Columns (1-20):", colsChooser,
                };

                JOptionPane.showMessageDialog(Battleship.frame, firstVarsChoiceMessage,
                        "Choose Variables (Part 1/2)", JOptionPane.PLAIN_MESSAGE, null);
                Battleship.numRows = (int) rowsChooser.getValue();
                Battleship.numCols = (int) colsChooser.getValue();

                JOptionPane.showMessageDialog(Battleship.frame,
                        "Number of rows set to " + Battleship.numRows +
                                "\nNumber of columns set to " + Battleship.numCols,
                        null, JOptionPane.PLAIN_MESSAGE);

                int numSlots = Battleship.numRows * Battleship.numCols;

                SpinnerNumberModel shipsSpinnerModel = new SpinnerNumberModel(1, 1, numSlots,1);
                JSpinner shipsChooser = new JSpinner(shipsSpinnerModel);
                shipsChooser.setEditor(new JSpinner.DefaultEditor(shipsChooser));

                SpinnerNumberModel shotsSpinnerModel = new SpinnerNumberModel(1, 1, numSlots,1);
                JSpinner shotsChooser = new JSpinner(shotsSpinnerModel);
                shotsChooser.setEditor(new JSpinner.DefaultEditor(shotsChooser));

                Object[] secondVarsChoiceMessage = {
                        "   You set the number of Ships (1-" + numSlots + "):", shipsChooser,
                        "   You set the number of Shots (1-" + numSlots + "):", shotsChooser,
                };

                JOptionPane.showMessageDialog(Battleship.frame, secondVarsChoiceMessage,
                        "Choose Variables (Part 2/2)", JOptionPane.PLAIN_MESSAGE, null);
                Battleship.numShips = (int) shipsChooser.getValue();
                Battleship.numShots = (int) shotsChooser.getValue();

                JOptionPane.showMessageDialog(Battleship.frame,
                        "You set the number of ships set to " + Battleship.numShips +
                                "\nYou set the number of shots set to " + Battleship.numShots,
                        null, JOptionPane.PLAIN_MESSAGE);

                varsDone = true;
                }

            else if (defaultVarsChoice == -1) {
                JOptionPane.showMessageDialog(Battleship.frame,
                        "Please choose yes or no.", null,
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        Battleship.playerBoard = new int[Battleship.numRows][Battleship.numCols];
        Battleship.playerShips = new int[Battleship.numRows][Battleship.numCols];
        Battleship.compBoard = new int[Battleship.numRows][Battleship.numCols];
        Battleship.compShips = new int[Battleship.numRows][Battleship.numCols];
    }
    // method to import images as icons
    protected static ImageIcon createImageIcon() {
        java.net.URL imgURL = Rules.class.getResource("/src/main/resources/question.png");
        if (imgURL != null) {
            return new ImageIcon(imgURL, "A question mark");
        } else {
            System.err.println("Couldn't find file: " + "/src/main/resources/question.png");
            return null;
        }
    }
}
