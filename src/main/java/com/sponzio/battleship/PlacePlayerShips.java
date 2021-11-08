package src.main.java.com.sponzio.battleship;

import javax.swing.*;

public class PlacePlayerShips {
    public static void placePlayerShips(JFrame inputFrame){
        SpinnerNumberModel shipRowModel = new SpinnerNumberModel(1, 1, GraphicsBattleship.numRows,1);
        SpinnerNumberModel shipColModel = new SpinnerNumberModel(1, 1, GraphicsBattleship.numCols,1);

        for (int i = 0; i < GraphicsBattleship.numShips; i++){
            JSpinner shipRowChooser = new JSpinner(shipRowModel);
            JSpinner shipColChooser = new JSpinner(shipColModel);
            shipRowChooser.setEditor(new JSpinner.DefaultEditor(shipRowChooser));
            shipColChooser.setEditor(new JSpinner.DefaultEditor(shipColChooser));

            Object[] shipPlacementsMessage = {
                    "   Ship Row (1-" + GraphicsBattleship.numRows + "):", shipRowChooser,
                    "   Number of Columns (1-" + GraphicsBattleship.numCols + "):", shipColChooser,
            };

            JOptionPane.showMessageDialog(inputFrame, shipPlacementsMessage, "Place Ship #" + (i + 1),
                    JOptionPane.PLAIN_MESSAGE, null);
            //GraphicsBattleship.playerShips[(int) rowChooser.getValue()][(int) colChooser.getValue()] = 1;
            int shipRow = (int) shipRowChooser.getValue();
            int shipCol = (int) shipColChooser.getValue();
            System.out.println("Placed player ship #" + (i + 1) + " at " + shipRow + ", " + shipCol);
        }
    }
}
