package src.main.java.com.sponzio.battleship;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerShot extends  JPanel implements  ActionListener{
    protected static JButton displayBoardButton;

    public static void playerShot() {
        SpinnerNumberModel shotRowModel = new SpinnerNumberModel(1, 1,
                Battleship.numRows, 1);
        SpinnerNumberModel shotColModel = new SpinnerNumberModel(1, 1,
                Battleship.numCols, 1);

        displayBoardButton = new JButton("Display your board");
        displayBoardButton.setVerticalTextPosition(AbstractButton.CENTER);
        displayBoardButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        displayBoardButton.setActionCommand("displayBoard");
        displayBoardButton.addActionListener(new PlayerShot());

        for (int i = 0; i < Battleship.numShips; i++) {
            JSpinner shotRowChooser = new JSpinner(shotRowModel);
            JSpinner shotColChooser = new JSpinner(shotColModel);
            shotRowChooser.setEditor(new JSpinner.DefaultEditor(shotRowChooser));
            shotColChooser.setEditor(new JSpinner.DefaultEditor(shotColChooser));

            Object[] playerShotMessage = {
                    "   Shot Row (1-" + Battleship.numRows + "):", shotRowChooser,
                    "   Shot Column (1-" + Battleship.numCols + "):", shotColChooser,
                    displayBoardButton
            };

            JOptionPane.showMessageDialog(Battleship.frame, playerShotMessage, "Take shot " + (i + 1) +
                            " of " + Battleship.numShots, JOptionPane.PLAIN_MESSAGE, null);

            int shotRow = (int) shotRowChooser.getValue();
            int shotCol = (int) shotColChooser.getValue();
            String didPlayerShotHit;

            if (Battleship.playerBoard[shotRow - 1][shotCol - 1] == 2 ||
                    Battleship.playerBoard[shotRow - 1][shotCol - 1] == 3) {
                JOptionPane.showMessageDialog(Battleship.frame,
                        "That location has already been shot at, please choose a different location.",
                        null, JOptionPane.ERROR_MESSAGE);
                i--;
            } else {

                if (Battleship.compShips[shotRow - 1][shotCol - 1] == 1) {
                    didPlayerShotHit = "hit!";
                    Battleship.playerHits++;
                    Battleship.playerBoard[shotRow - 1][shotCol - 1] = 3;

                } else {
                    didPlayerShotHit = "missed.";
                    Battleship.compShips[shotRow - 1][shotCol - 1] = 2;
                    Battleship.playerBoard[shotRow - 1][shotCol - 1] = 2;
                }
                JOptionPane.showMessageDialog(Battleship.frame,
                        "You fired shot #" + (i + 1) + " at (" + shotRow + ", " + shotCol + ") and " +
                                didPlayerShotHit + "\nThe computer has " +
                                (Battleship.numShips - Battleship.playerHits)+ " ships remaining."
                        , null, JOptionPane.PLAIN_MESSAGE);
            }
            Battleship.checkWin();
            if (Battleship.gameDone) {break;}
        }
    }
    public void actionPerformed(ActionEvent e) {
        if ("displayBoard".equals(e.getActionCommand())) {
            PrintArray.printArray(Battleship.playerBoard);
        }
    }
}
