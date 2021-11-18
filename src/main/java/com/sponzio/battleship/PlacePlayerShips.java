package src.main.java.com.sponzio.battleship;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PlacePlayerShips extends  JPanel implements  ActionListener{
    protected static JButton displayShipsButton;

    public static void placePlayerShips() {
        SpinnerNumberModel shipRowModel = new SpinnerNumberModel(1, 1,
                Battleship.numRows, 1);
        SpinnerNumberModel shipColModel = new SpinnerNumberModel(1, 1,
                Battleship.numCols, 1);

        displayShipsButton = new JButton("Display your ships");
        displayShipsButton.setVerticalTextPosition(AbstractButton.CENTER);
        displayShipsButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        displayShipsButton.setActionCommand("displayShips");
        displayShipsButton.addActionListener(new PlacePlayerShips());

        for (int i = 0; i < Battleship.numShips; i++) {
            JSpinner shipRowChooser = new JSpinner(shipRowModel);
            JSpinner shipColChooser = new JSpinner(shipColModel);
            shipRowChooser.setEditor(new JSpinner.DefaultEditor(shipRowChooser));
            shipColChooser.setEditor(new JSpinner.DefaultEditor(shipColChooser));

            Object[] shipPlacementsMessage = {
                    "   Ship Row (1-" + Battleship.numRows + "):", shipRowChooser,
                    "   Ship Column (1-" + Battleship.numCols + "):", shipColChooser,
                    displayShipsButton
            };

            JOptionPane.showMessageDialog(Battleship.frame, shipPlacementsMessage, "Place ship " + (i + 1) +
                    " of " + Battleship.numShips, JOptionPane.PLAIN_MESSAGE, null);

            int shipRow = (int) shipRowChooser.getValue();
            int shipCol = (int) shipColChooser.getValue();

            if (Battleship.playerShips[shipCol - 1][shipRow - 1] == 1) {
                JOptionPane.showMessageDialog(Battleship.frame,
                        "That location already contains a ship, please choose a different location.",
                        null, JOptionPane.ERROR_MESSAGE);
                i--;
            } else {
                Battleship.playerShips[shipCol - 1][shipRow - 1] = 1;
                JOptionPane.showMessageDialog(Battleship.frame,
                        "Placed ship #" + (i + 1) + " at (" + shipRow + ", " + shipCol + ")",
                        null, JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
    public void actionPerformed(ActionEvent e) {
        if ("displayShips".equals(e.getActionCommand())) {
            PrintArray.printArray(Battleship.playerShips);
        }
    }
}