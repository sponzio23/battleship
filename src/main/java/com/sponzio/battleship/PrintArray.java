package src.main.java.com.sponzio.battleship;

import javax.swing.*;
import java.util.Arrays;

public class PrintArray {
    public static String output;
    public static void printArray(JFrame inputFrame, int[][] input) {
        // this loops through each row and uses the java.util.Arrays package that was imported at the start
        // to convert it to a string and add it to the output string
        for (int[] row : input) {
            output = output + Arrays.toString(row);
        }
        JOptionPane.showMessageDialog(inputFrame, output, null, JOptionPane.PLAIN_MESSAGE);
        // TODO: make title not null and instead the name of the inputted array
    }
}
