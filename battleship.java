// key for values in ship arrays: 0 is empty, 1 is ship, 2 is shot, 3 is

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class battleship {
    public static int numRows;
    public static int numCols;
    public static int numShips;
    public static int numShots;

    public static Scanner varScanner = new Scanner(System.in);
    public static Scanner rulesScanner = new Scanner(System.in);
    public static Scanner placeScanner = new Scanner(System.in);
    public static Scanner shotScanner = new Scanner(System.in);

    public static int[][] playerBoard;
    public static int[][] playerShips;
    public static int[][] compBoard;
    public static int[][] compShips;

    public static void main(String[] args) {
        setVars();
        newline();

        rules();
        newline();

        placeCompShips();
        newline();

        placePlayerShips();
        newline();

        playerShot();
        newline();

        compShot();
        newline();

        printArray(playerShips);
    }

    static void newline() {
        System.out.print("\n");
    }

    static void printArray(int[][] input){
        for (int[] row : input) {
            System.out.println(Arrays.toString(row));
        }
    }

    static void setVars(){
        System.out.println("How many rows would you like the board to have?");
        while (!varScanner.hasNextInt()) {
            System.out.println("Please enter a number");
            varScanner.next();
        }
        numRows = Math.abs(varScanner.nextInt());

        System.out.println("How many columns would you like the board to have?");
        while (!varScanner.hasNextInt()){
            System.out.println("Please enter a number");
            varScanner.next();
        }
        numCols = Math.abs(varScanner.nextInt());

        System.out.println("How many ships would you like each player to have?");
        while (!varScanner.hasNextInt()) { // add something along the lines of
            // && varScanner.nextInt() > (numCols * numRows)
            System.out.println("Please enter a number less than the number of spaces on the board");
            varScanner.next();
        }
        numShips = Math.abs(varScanner.nextInt());

        System.out.println("How many shots would you like each player to get each round?");
        while (!varScanner.hasNextInt()) { // add something along the lines of
            // && varScanner.nextInt() > (numCols * numRows)
            System.out.println("Please enter a number less than the number of spaces on the board");
            varScanner.next();
        }
        numShots = Math.abs(varScanner.nextInt());


        playerBoard = new int[numRows][numCols];
        playerShips = new int[numRows][numCols];
        compBoard = new int[numRows][numCols];
        compShips = new int[numRows][numCols];
    }

    static void rules() {
        boolean rulesDone = false;
        System.out.println("Would you like to hear the rules? y/n"); // prompt for input
        while (!rulesDone) {
            String printRules = rulesScanner.nextLine(); // reads user input
            if (printRules.equals("y")) {
                System.out.println("RULES PLACEHOLDER");
                rulesDone = true;
            } else if (printRules.equals("n")) {
                rulesDone = true;
            } else {
                System.out.println("Invalid input, please try again. Respond with y or n ONLY.");
            }
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
            while (!placeScanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                placeScanner.next();
            }
            int shipRow = Math.abs(placeScanner.nextInt());

            System.out.println("Place ship #" + (i + 1) + " by entering a column. This value must be between 0 and "
                    + (numCols - 1) + ".");
            while (!placeScanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                placeScanner.next();
            }
            int shipCol = Math.abs(placeScanner.nextInt());
            newline();
            if (shipCol > (numCols - 1) || shipRow > (numRows - 1)) {
                System.out.println("Failed to place ship. One or both of your values is/are either too large " +
                        "or small. Please try again.");
                i--;
            } else if (playerShips[shipRow][shipCol] == 1) {
                System.out.println("Failed to place ship. That location already has a ship. Please try again.");
                i--;
            } else {
                playerShips[shipRow][shipCol] = 1;
                System.out.println("Successfully placed ship #" + (i + 1) + " at (" + shipRow + ", " + shipCol + ")");
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
            int shotRow = Math.abs(shotScanner.nextInt());

            System.out.println("Take shot #" + (i + 1) + " by entering a column. This value must be between 0 and "
                    + (numCols - 1) + ".");
            while (!shotScanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                shotScanner.next();
            }
            int shotCol = Math.abs(shotScanner.nextInt());
            newline();
            if (shotCol > (numCols - 1) || shotRow > (numRows - 1)) {
                System.out.println("Failed to take shot. One or both of your values is/are either too large " +
                        "or small. Please try again.");
                newline();
                i--;
            } else if (playerShips[shotRow][shotCol] == 2 || playerShips[shotRow][shotCol] == 3) {
                System.out.println("Failed to take shot. That location has already been shot at. Please try again.");
                i--;
                newline();
            } else {
                System.out.println("Fired shot #" + (i + 1) + " at (" + shotRow + ", " + shotCol + ")");
                if (compShips[shotRow][shotCol] == 1) {
                    System.out.println("HIT!!!");
                    compShips[shotRow][shotCol] = 3;
                    playerBoard[shotRow][shotCol] = 3;
                } else {
                    compShips[shotRow][shotCol] = 2;
                    playerBoard[shotRow][shotCol] = 2;
                    System.out.println("You missed, sorry.");
                }
            }
        }
    }

    static void compShot() {
        System.out.println("It's the computer's turn to shoot!");
        for (int i = 0; i < numShots; i++) {
            int shotRow = ThreadLocalRandom.current().nextInt(0, numRows);
            int shotCol = ThreadLocalRandom.current().nextInt(0, numCols);
            if (playerShips[shotRow][shotCol] == 2 || playerShips[shotRow][shotCol] == 3) {
                System.out.println("Shot failed.");
                i--;
                newline();
            } else {
                System.out.println("The computer fired shot #" + (i + 1) + " at (" + shotRow + ", " + shotCol + ")");
                if (playerShips[shotRow][shotCol] == 1) {
                    System.out.println("HIT!!!");
                    playerShips[shotRow][shotCol] = 3;
                    compBoard[shotRow][shotCol] = 3;
                } else {
                    playerShips[shotRow][shotCol] = 2;
                    compBoard[shotRow][shotCol] = 2;
                    System.out.println("The computer missed!");
                }
            }
        }
    }
}
