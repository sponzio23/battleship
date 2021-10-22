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

    public static boolean gameDone = false;
    public static String winner = null;
    public static boolean playerShipsLeft = false;
    public static boolean compShipsLeft = false;

    public static void main(String[] args) {
        rules();
        setVars();
        placePlayerShips();
        placeCompShips();
        while (!gameDone) {
            playerShot();
            checkWin();
            compShot();
            checkWin();
        }


    }

    static void newline() {
        System.out.print("\n");
    }

    static void printArray(int[][] input) {
        if (input == playerBoard){
            System.out.println("Printing the player's board");
        }
        else if (input == compBoard){
            System.out.println("Printing the computer's board");
        }
        else if (input == compShips){
            System.out.println("Printing the computer's ships (DEBUG INFO)");
        }
        else if (input == playerShips){
            System.out.println("Printing the player's ships (DEBUG INFO)");
        }

        for (int[] row : input) {
            System.out.println(Arrays.toString(row));
        }
    }

    static void rules() {
        boolean rulesDone = false;
        System.out.println("Would you like to hear the rules? y/n");
        while(!rulesDone) {
            String printRules = rulesScanner.nextLine();
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

    static void setVars() {
        boolean varsDone = false;
        System.out.println("Would you like to use the default values? y/n");
        while(!varsDone){
            String useDefaultVars = varScanner.nextLine();
            if (useDefaultVars.equals("y")){
                numRows = 3;
                numCols = 3;
                numShips = 3;
                numShots = 1;
                varsDone = true;
            }
            else if (useDefaultVars.equals("n")){
                boolean rowChoiceDone = false;
                System.out.println("How many rows would you like the board to have? Please enter a number " +
                        "greater than or equal to 3.");
                while (!rowChoiceDone) {
                    while (!varScanner.hasNextInt()) {
                        System.out.println("Please enter a number.");
                        varScanner.next();
                    }
                    numRows = Math.abs(varScanner.nextInt());
                    if (numRows >= 3) {
                        System.out.println("Set the number of rows to " + numRows);
                        rowChoiceDone = true;
                    } else {
                        System.out.println("Please enter a number greater than or equal to 3.");
                    }
                }

                boolean columnChoiceDone = false;
                System.out.println("How many columns would you like the board to have? Please enter a number " +
                        "greater than or equal to 3.");
                while (!columnChoiceDone) {
                    while (!varScanner.hasNextInt()) {
                        System.out.println("Please enter a number.");
                        varScanner.next();
                    }
                    numCols = Math.abs(varScanner.nextInt());
                    if (numCols >= 3) {
                        System.out.println("Set the number of columns to " + numCols);
                        columnChoiceDone = true;
                    } else {
                        System.out.println("Please enter a number greater than or equal to 3.");
                    }
                }

                boolean shipChoiceDone = false;
                System.out.println("How many ships would you like the board to have? Choose 3, 5, or 7.");
                while (!shipChoiceDone) {
                    while (!varScanner.hasNextInt()) {
                        System.out.println("Please enter a number");
                        varScanner.next();
                    }
                    numShips = Math.abs(varScanner.nextInt());

                    // probably doesn't make sense to use a switch here, but I'm doing it for skill building
                    switch (numShips) {
                        case 3, 5, 7 -> {
                            System.out.println("Number of ships set to " + numShips);
                            shipChoiceDone = true;
                        }
                        default -> System.out.println("Please enter one of the options.");
                    }
                }

                boolean shotChoiceDone = false;
                System.out.println("How many shots would you like each player to have? Choose 1, 3, or 5.");
                while (!shotChoiceDone) {
                    while (!varScanner.hasNextInt()) {
                        System.out.println("Please enter a number");
                        varScanner.next();
                    }
                    numShots = Math.abs(varScanner.nextInt());

                    switch (numShots) {
                        case 1, 3, 5 -> {
                            System.out.println("Number of shots set to " + numShots);
                            shotChoiceDone = true;
                        }
                        default -> System.out.println("Please enter one of the options.");
                    }
                }

                varsDone = true;
            }
            else{
                System.out.println("Invalid input, please try again. Respond with y or n ONLY.");
            }
        }

        playerBoard = new int[numRows][numCols];
        playerShips = new int[numRows][numCols];
        compBoard = new int[numRows][numCols];
        compShips = new int[numRows][numCols];
    }

    static void placeCompShips() {
        for (int i = 0; i < numShips; i++) {
            int shipRow = ThreadLocalRandom.current().nextInt(0, numRows);
            int shipCol = ThreadLocalRandom.current().nextInt(0, numCols);
            if (compShips[shipRow][shipCol] == 0) {
                compShips[shipRow][shipCol] = 1;
                System.out.println("Placed a ship at (" + shipRow + ", " + shipCol + ")");
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

    static void checkWin(){
        for (int i = 0; i < 2; i ++){
            for (int j = 0; j < 2; j++){
                if (playerShips[i][j] == 1){
                    System.out.println("Player has ships remaining");
                    playerShipsLeft = true;
                    break;
                }
            }
        }

        for (int i = 0; i < 2; i ++){
            for (int j = 0; j < 2; j++){
                if (compShips[i][j] == 1){
                    System.out.println("Computer has ships remaining");
                    compShipsLeft = true;
                    break;
                }
            }
        }

        if(playerShipsLeft && !compShipsLeft){
            gameDone = true;
            winner = "player";
        }
        else if (!playerShipsLeft && compShipsLeft){
            gameDone = true;
            winner = "computer";
        }
        else if (playerShipsLeft && compShipsLeft){
            gameDone = false;
        }
    }

    static void autoWin(int[][] input){ // this is purely for testing purposes
        System.out.println("Cheating at the game.");
        for (int r = 0; r < input.length; r++) {
            for (int c = 0; c < input[r].length; c++) {
                if (input[r][c] == 1){
                    input[r][c] = 3;
                }
            }
        }
    }
}
