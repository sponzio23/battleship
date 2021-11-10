package src.main.java.com.sponzio.battleship;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerShot extends  JPanel implements  ActionListener{
    protected static JButton displayBoardButton;

    public static void playerShot() {
        SpinnerNumberModel shotRowModel = new SpinnerNumberModel(1, 1,
                GraphicsBattleship.numRows, 1);
        SpinnerNumberModel shotColModel = new SpinnerNumberModel(1, 1,
                GraphicsBattleship.numCols, 1);

        displayBoardButton = new JButton("Display your board");
        displayBoardButton.setVerticalTextPosition(AbstractButton.CENTER);
        displayBoardButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        displayBoardButton.setActionCommand("displayBoard");
        displayBoardButton.addActionListener(new PlayerShot());

        for (int i = 0; i < GraphicsBattleship.numShips; i++) {
            JSpinner shotRowChooser = new JSpinner(shotRowModel);
            JSpinner shotColChooser = new JSpinner(shotColModel);
            shotRowChooser.setEditor(new JSpinner.DefaultEditor(shotRowChooser));
            shotColChooser.setEditor(new JSpinner.DefaultEditor(shotColChooser));

            Object[] playerShotMessage = {
                    "   Shot Row (1-" + GraphicsBattleship.numRows + "):", shotRowChooser,
                    "   Shot Column (1-" + GraphicsBattleship.numCols + "):", shotColChooser,
                    displayBoardButton
            };

            JOptionPane.showMessageDialog(GraphicsBattleship.frame, playerShotMessage, "Take shot " + (i + 1) +
                            " of " + GraphicsBattleship.numShots, JOptionPane.PLAIN_MESSAGE, null);

            int shotRow = (int) shotRowChooser.getValue();
            int shotCol = (int) shotColChooser.getValue();

            if (GraphicsBattleship.playerBoard[shotCol - 1][shotRow - 1] == 2 ||
                    GraphicsBattleship.playerBoard[shotCol - 1][shotRow - 1] == 3) {
                JOptionPane.showMessageDialog(GraphicsBattleship.frame,
                        "That location has already been shot at, please choose a different location.",
                        null, JOptionPane.ERROR_MESSAGE);
                i--;
            } else {
                JOptionPane.showMessageDialog(GraphicsBattleship.frame,
                        "Fired shot #" + (i + 1) + " at (" + shotRow + ", " + shotCol + ")",
                        null, JOptionPane.PLAIN_MESSAGE);

                if (GraphicsBattleship.compShips[shotRow - 1][shotCol - 1] == 1) {
                    JOptionPane.showMessageDialog(GraphicsBattleship.frame,
                            "HIT!!!",
                            null, JOptionPane.PLAIN_MESSAGE);
                    GraphicsBattleship.playerHits++;
                    GraphicsBattleship.compShips[shotRow - 1][shotCol - 1] = 3;
                    GraphicsBattleship.playerBoard[shotRow - 1][shotCol - 1] = 3;

                } else {
                    JOptionPane.showMessageDialog(GraphicsBattleship.frame,
                            "You missed, sorry.",
                            null, JOptionPane.PLAIN_MESSAGE);
                    GraphicsBattleship.compShips[shotRow - 1][shotCol - 1] = 2;
                    GraphicsBattleship.playerBoard[shotRow - 1][shotCol - 1] = 2;
                }
                System.out.println("Fired shot #" + (i + 1) + " at " + shotRow + ", " + shotCol);
            }
        }
    }
    public void actionPerformed(ActionEvent e) {
        if ("displayBoard".equals(e.getActionCommand())) {
            PrintArray.printArray(GraphicsBattleship.playerBoard);
        }
    }
}
