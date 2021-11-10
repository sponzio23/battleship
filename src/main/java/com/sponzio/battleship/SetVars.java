package src.main.java.com.sponzio.battleship;

import javax.swing.*;

public class SetVars {
    public static void setVars() {
        Icon questionIcon = GraphicsBattleship.createImageIcon("/src/main/resources/question.png", "A question mark");

        boolean varsDone = false;
        while (!varsDone) {
            int defaultVarsChoice = JOptionPane.showOptionDialog(GraphicsBattleship.frame,
                    "Would you like to use the default variables?",
                    null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    questionIcon, null, null);
            if (defaultVarsChoice == 0) {
                GraphicsBattleship.numRows = 3;
                GraphicsBattleship.numCols = 3;
                GraphicsBattleship.numShips = 3;
                GraphicsBattleship.numShots = 3;
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

                JOptionPane.showMessageDialog(GraphicsBattleship.frame, firstVarsChoiceMessage,
                        "Choose Variables (Part 1/2)", JOptionPane.PLAIN_MESSAGE, null);
                GraphicsBattleship.numRows = (int) rowsChooser.getValue();
                GraphicsBattleship.numCols = (int) colsChooser.getValue();

                JOptionPane.showMessageDialog(GraphicsBattleship.frame,
                        "Number of rows set to " + GraphicsBattleship.numRows +
                                "\n Number of columns set to " + GraphicsBattleship.numCols,
                        null, JOptionPane.PLAIN_MESSAGE);

                int numSlots = GraphicsBattleship.numRows * GraphicsBattleship.numCols;

                SpinnerNumberModel shipsSpinnerModel = new SpinnerNumberModel(1, 1, numSlots,1);
                JSpinner shipsChooser = new JSpinner(shipsSpinnerModel);
                shipsChooser.setEditor(new JSpinner.DefaultEditor(shipsChooser));

                SpinnerNumberModel shotsSpinnerModel = new SpinnerNumberModel(1, 1, numSlots,1);
                JSpinner shotsChooser = new JSpinner(shotsSpinnerModel);
                shotsChooser.setEditor(new JSpinner.DefaultEditor(shotsChooser));

                Object[] secondVarsChoiceMessage = {
                        "   Number of Ships (1-" + numSlots + "):", shipsChooser,
                        "   Number of Shots (1-" + numSlots + "):", shotsChooser,
                };

                JOptionPane.showMessageDialog(GraphicsBattleship.frame, secondVarsChoiceMessage,
                        "Choose Variables (Part 2/2)", JOptionPane.PLAIN_MESSAGE, null);
                GraphicsBattleship.numShips = (int) shipsChooser.getValue();
                GraphicsBattleship.numShots = (int) shotsChooser.getValue();

                JOptionPane.showMessageDialog(GraphicsBattleship.frame,
                        "Number of ships set to " + GraphicsBattleship.numShips +
                                "\n Number of shots set to " + GraphicsBattleship.numShots,
                        null, JOptionPane.PLAIN_MESSAGE);

                varsDone = true;
                }

            else if (defaultVarsChoice == -1) {
                JOptionPane.showMessageDialog(GraphicsBattleship.frame,
                        "Please choose yes or no.", null,
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        GraphicsBattleship.playerBoard = new int[GraphicsBattleship.numRows][GraphicsBattleship.numCols];
        GraphicsBattleship.playerShips = new int[GraphicsBattleship.numRows][GraphicsBattleship.numCols];
        GraphicsBattleship.compBoard = new int[GraphicsBattleship.numRows][GraphicsBattleship.numCols];
        GraphicsBattleship.compShips = new int[GraphicsBattleship.numRows][GraphicsBattleship.numCols];
    }
}
