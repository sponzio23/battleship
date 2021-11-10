package src.main.java.com.sponzio.battleship;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PlacePlayerShips extends  JPanel implements  ActionListener{
    protected static JButton displayShipsButton;

    public static void placePlayerShips() {
        SpinnerNumberModel shipRowModel = new SpinnerNumberModel(1, 1,
                GraphicsBattleship.numRows, 1);
        SpinnerNumberModel shipColModel = new SpinnerNumberModel(1, 1,
                GraphicsBattleship.numCols, 1);

        displayShipsButton = new JButton("Display your ships");
        displayShipsButton.setVerticalTextPosition(AbstractButton.CENTER);
        displayShipsButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        displayShipsButton.setActionCommand("displayShips");
        displayShipsButton.addActionListener(new PlacePlayerShips());

        for (int i = 0; i < GraphicsBattleship.numShips; i++) {
            JSpinner shipRowChooser = new JSpinner(shipRowModel);
            JSpinner shipColChooser = new JSpinner(shipColModel);
            shipRowChooser.setEditor(new JSpinner.DefaultEditor(shipRowChooser));
            shipColChooser.setEditor(new JSpinner.DefaultEditor(shipColChooser));

            Object[] shipPlacementsMessage = {
                    "   Ship Row (1-" + GraphicsBattleship.numRows + "):", shipRowChooser,
                    "   Ship Column (1-" + GraphicsBattleship.numCols + "):", shipColChooser,
                    displayShipsButton
            };

            JOptionPane.showMessageDialog(GraphicsBattleship.frame, shipPlacementsMessage, "Place ship " + (i + 1) +
                    " of " + GraphicsBattleship.numShips, JOptionPane.PLAIN_MESSAGE, null);

            int shipRow = (int) shipRowChooser.getValue();
            int shipCol = (int) shipColChooser.getValue();

            if (GraphicsBattleship.playerShips[shipCol - 1][shipRow - 1] == 1) {
                JOptionPane.showMessageDialog(GraphicsBattleship.frame,
                        "That location already contains a ship, please choose a different location.",
                        null, JOptionPane.ERROR_MESSAGE);
                i--;
            } else {
                GraphicsBattleship.playerShips[shipCol - 1][shipRow - 1] = 1;
                JOptionPane.showMessageDialog(GraphicsBattleship.frame,
                        "Placed ship #" + (i + 1) + " at (" + shipRow + ", " + shipCol + ")",
                        null, JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
    public void actionPerformed(ActionEvent e) {
        if ("displayShips".equals(e.getActionCommand())) {
            PrintArray.printArray(GraphicsBattleship.playerShips);
        }
    }
}