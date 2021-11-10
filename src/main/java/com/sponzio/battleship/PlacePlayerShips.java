package src.main.java.com.sponzio.battleship;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PlacePlayerShips extends  JPanel implements  ActionListener{
    protected static JButton displayShipsButton;

    public static void placePlayerShips(JFrame inputFrame) {
        SpinnerNumberModel shipRowModel = new SpinnerNumberModel(1, 1,
                GraphicsBattleship.numRows, 1);
        SpinnerNumberModel shipColModel = new SpinnerNumberModel(1, 1,
                GraphicsBattleship.numCols, 1);

        displayShipsButton = new JButton("Display player ships");
        displayShipsButton.setVerticalTextPosition(AbstractButton.CENTER);
        displayShipsButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        displayShipsButton.setActionCommand("display");
        displayShipsButton.addActionListener(new PlacePlayerShips());

        for (int i = 0; i < GraphicsBattleship.numShips; i++) {
            JSpinner shipRowChooser = new JSpinner(shipRowModel);
            JSpinner shipColChooser = new JSpinner(shipColModel);
            shipRowChooser.setEditor(new JSpinner.DefaultEditor(shipRowChooser));
            shipColChooser.setEditor(new JSpinner.DefaultEditor(shipColChooser));

            Object[] shipPlacementsMessage = {
                    "   Ship Row (1-" + GraphicsBattleship.numRows + "):", shipRowChooser,
                    "   Number of Columns (1-" + GraphicsBattleship.numCols + "):", shipColChooser,
                    displayShipsButton
            };

            JOptionPane.showMessageDialog(inputFrame, shipPlacementsMessage, "Place Ship #" + (i + 1),
                    JOptionPane.PLAIN_MESSAGE, null);

            int shipRow = (int) shipRowChooser.getValue();
            int shipCol = (int) shipColChooser.getValue();

            if (GraphicsBattleship.playerShips[shipCol - 1][shipRow - 1] == 1) {
                JOptionPane.showMessageDialog(inputFrame,
                        "That location already contains a ship, please choose a different location.",
                        null, JOptionPane.ERROR_MESSAGE);
                i--;
            } else {
                GraphicsBattleship.playerShips[shipCol - 1][shipRow - 1] = 1;
                System.out.println("Placed player ship #" + (i + 1) + " at " + shipRow + ", " + shipCol);
            }
        }
    }
    public void actionPerformed(ActionEvent e) {
        if ("display".equals(e.getActionCommand())) {
            PrintArray.printArray(GraphicsBattleship.frame, GraphicsBattleship.playerShips);
        }
    }
}