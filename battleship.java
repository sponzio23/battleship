// key for values in ship arrays: 0 is empty, 1 is ship, 2 is shot, 3 is

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class battleship {
    // these three variables can all be customized to any value
    public static int numRows = 5;
    public static int numCols = 5;
    public static int numShips = 3;
    public static int numShots = 2;

    // create fields for visible boards and hidden boards with ships
    public static int[][] playerBoard = new int[numCols][numRows];
    public static int[][] playerShips = new int[numCols][numRows];
    public static int[][] compBoard = new int[numCols][numRows];
    public static int[][] compShips = new int[numCols][numRows];

    // create scanners to detect input for rules and ship placements
    public static Scanner rulesScanner = new Scanner(System.in);
    public static Scanner placeScanner = new Scanner(System.in);
    public static Scanner shotScanner = new Scanner(System.in);

    public static void main(String[] args) {
        rules();
        newline();

        placeCompShips();
        placePlayerShips();
        newline();

        printPlayerShips();
        printCompShips();
        newline();

        printCompBoard();
        printPlayerBoard();
        newline();

        playerShot();
        compShot();
    }

    static void newline(){
        System.out.print("\n");
    }

    static void rules(){
        boolean rulesDone = false;
        System.out.println("Would you like to hear the rules? y/n"); // prompt for input
        while (!rulesDone){
            String printRules = rulesScanner.nextLine(); // reads user input
            if (printRules.equals("y")) {
                System.out.println("RULES PLACEHOLDER");
                rulesDone = true;
            }
            else if (printRules.equals("n")){
                rulesDone = true;
            }
            else{
                System.out.println("Invalid input, please try again. Respond with y or n ONLY.");
            }
        }
    }

    static void printPlayerBoard(){
        System.out.println("Player Board:");
        for (int[] row : playerBoard) {
            System.out.println(Arrays.toString(row));
        }
    }

    static void printCompBoard(){
        System.out.println("Computer Board:");
        for (int[] row : compBoard) {
            System.out.println(Arrays.toString(row));
        }
    }

    static void printPlayerShips(){
        System.out.println("Player ships:");
        for (int[] row : playerShips) {
            System.out.println(Arrays.toString(row));
        }
    }

    static void printCompShips(){
        System.out.println("Computer ships:");
        for (int[] row : compShips) {
            System.out.println(Arrays.toString(row));
        }
    }

    static void placeCompShips() {
        for (int i = 0; i < numShips; i++) {
            int shipRow = ThreadLocalRandom.current().nextInt(0, numRows);
            int shipCol = ThreadLocalRandom.current().nextInt(0, numCols);
            if (compShips[shipRow][shipCol] == 0) {
                compShips[shipRow][shipCol] = 1;
                System.out.println("Placed a ship at (" + shipCol + ", " + shipRow + ")");
            } else {
                System.out.println("Failed to place ship.");
                i--;
            }
        }
    }

    static void placePlayerShips() {
        System.out.println("Placing " + numShips + " ships.");
        for (int i = 0; i < numShips; i++) {
            newline();
            System.out.println("Place ship #" + (i + 1) + " by entering a row. This value must be between 0 and "
                    + (numRows - 1) + ".");
            while (!placeScanner.hasNextInt()){
                System.out.println("Please enter a number.");
                placeScanner.next();
            }
            int shipRow = placeScanner.nextInt();

            System.out.println("Place ship #" + (i + 1) + " by entering a column. This value must be between 0 and "
                    + (numCols - 1) + ".");
            while (!placeScanner.hasNextInt()){
                System.out.println("Please enter a number.");
                placeScanner.next();
            }
            int shipCol = placeScanner.nextInt();
            newline();
            if (shipCol > (numCols - 1) || shipRow > (numRows - 1)) {
                System.out.println("Failed to place ship. One or both of your values is/are either too large " +
                        "or small. Please try again.");
                i--;
            }
            else if (playerShips[shipRow][shipCol] == 1){
                System.out.println("Failed to place ship. That location already has a ship. Please try again.");
                i--;
            }
            else {
                playerShips[shipRow][shipCol] = 1;
                System.out.println("Successfully placed ship #" + (i+1) + " at (" + shipRow + ", " + shipCol + ")");
            }
        }
    }

    static void playerShot() {
        System.out.println("It is your turn to shoot! You have " + numShots + " shots.");
        for (int i = 0; i < numShots; i++) {
            newline();
            System.out.println("Take shot #" + (i + 1) + " by entering a row. This value must be between 0 and "
                    + (numRows - 1) + ".");
            while (!shotScanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                shotScanner.next();
            }
            int shotRow = shotScanner.nextInt();

            System.out.println("Take shot #" + (i + 1) + " by entering a column. This value must be between 0 and "
                    + (numCols - 1) + ".");
            while (!shotScanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                shotScanner.next();
            }
            int shotCol = shotScanner.nextInt();
            newline();
            if (shotCol > (numCols - 1) || shotRow > (numRows - 1)) {
                System.out.println("Failed to take shot. One or both of your values is/are either too large " +
                        "or small. Please try again.");
                newline();
                i--;
            } else if (compShips[shotRow][shotCol] == 2) {
                System.out.println("Failed to take shot. That location has already been shot at. Please try again.");
                i--;
                newline();
            } else {
                System.out.println("Fired shot #" + (i + 1) + " at (" + shotRow + ", " + shotCol + ")");
                if (compShips[shotRow][shotCol] == 1) {
                    System.out.println("HIT!!!");
                    compShips[shotRow][shotCol] = 3;
                    playerBoard[shotRow][shotCol] = 3;
                }
                else {
                    compShips[shotRow][shotCol] = 2;
                    playerBoard[shotRow][shotCol] = 2;
                    System.out.println("You missed, sorry.");
                }
            }
        }
    }

    // comp shot is wip
    static void compShot(){
        System.out.println("pew pew");
    }
}
