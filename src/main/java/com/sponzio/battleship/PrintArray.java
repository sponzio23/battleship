package src.main.java.com.sponzio.battleship;

import javax.swing.*;
import java.util.Arrays;

public class PrintArray {
    public static void printArray(int[][] input) {
        // this loops through each row and uses the java.util.Arrays package that was imported at the start
        // to convert it to a string and add it to the output string
        String output = "\n";
        for (int[] row : input) {
            output = output.concat(Arrays.toString(row)) ;
            output = output.concat("\n");
        }
        JOptionPane.showMessageDialog(Battleship.frame, output, null, JOptionPane.PLAIN_MESSAGE);
        // TODO: make title not null and instead the name of the inputted array
    }
}
