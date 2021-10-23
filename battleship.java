import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class battleship {
    public static boolean debugMode = false;

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

    public static int playerHits = 0;
    public static int compHits = 0;
    public static boolean gameDone = false;
    public static String winner = null;

    public static void main(String[] args) {
        if (debugMode){
            System.out.println("DEBUG MODE IS ENABLED");
            newline();
        }
        rules();
        setVars();
        placePlayerShips();
        placeCompShips();
        while (!gameDone) {
            printArray(playerBoard);
            printArray(playerShips);
            playerShot();
            if (gameDone){
                break;
            }
            compShot();
        }
        System.out.println("The " + winner + " won!");


    }

    static void newline() {
        System.out.print("\n");
    }

    static void printArray(int[][] input) {
        if (input == playerBoard){
            System.out.println("Player Board");
        }
        else if (input == playerShips){
            System.out.println("Player Ships");
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
                System.out.println("""
                    
                    WELCOME TO BATTLESHIP!

                    OBJECT OF THE GAME
                    To sink your opponents ships before they can sink yours. You will be playing against a computer
                    that operates fully randomly.

                    PREPARE FOR BATTLE
                    You will be prompted if you would like to use the default values. If you choose yes, you will play
                    with a 3x3 board and 3 ships. You will have 3 shots each round. If you choose no, you can set these
                    values as you desire. Currently each ship only takes up a 1x1 space. This might be changed in
                    future versions. Once you have set all these values you will be prompted to place each of your
                    ships by entering coordinates. Values that are not integers or that don’t exist on the board will
                    not be accepted. Once you have placed your ships the computer will place its ships.

                    BATTLE
                    You and the computer will take turns shooting. You will go first. Before each time it becomes your
                    turn you will see two grids. One is labeled Player Board and the other is labeled Player Ships.
                    Player Ships shows your ships. A 0 means there is nothing there, a 1 means one of your ships is
                    there, a 2 means the computer has fired at that location and missed, and a 3 means that location
                    had a ship that the computer shot at and hit. Player board shows your previous shots. A 0 means
                    you have not yet shot there, a 2 means you missed, and a 3 means you hit something. When it is your
                    turn to shoot you will be prompted to enter coordinates similar to how you placed your ships.
                    Yet again, values that are not integers or that don’t exist on the board will not be accepted.
                    In addition, locations you have already shot at cannot be shot at again. Once you have taken your
                    shot you will be informed if it is a hit or miss. This process will repeat for as many shots as
                    you have. After you have shot, the computer will shoot. The computer randomly selects and announces
                    its shots and then informs you if they are a hit or a miss.

                    WINNING/LOSING THE GAME
                    If at any point you or the computer has no ships remaining, the game will end and the winner will
                    be announced. If you would like to play again, simply click the run button again.

                    ENJOY!
                    """);
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
                numShots = 3;
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
                if (debugMode){
                    System.out.println("The computer placed ship #" + (i+1) +"at (" + shipRow + ", " + shipCol + ")");
                }
            } else {
                if (debugMode){
                    System.out.println("The computer failed to place ship #" + (i+1) + ".");
                }
                i--;
            }
        }
        System.out.println("The computer has placed it's ships!");
    }

    static void placePlayerShips() {
        System.out.println("Placing " + numShips + " ships.");
        for (int i = 0; i < numShips; i++) {
            System.out.println("Place player ship #" + (i + 1) + " by entering a row. " +
                    "This value must be between 0 and " + (numRows - 1) + ".");
            while (!placeScanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                placeScanner.next();
            }
            int shipRow = Math.abs(placeScanner.nextInt());

            System.out.println("Place player ship #" + (i + 1) + " by entering a column. " +
                    "This value must be between 0 and " + (numCols - 1) + ".");
            while (!placeScanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                placeScanner.next();
            }
            int shipCol = Math.abs(placeScanner.nextInt());
            if (shipCol > (numCols - 1) || shipRow > (numRows - 1)) {
                System.out.println("Failed to place ship #"  + (i + 1) + ". " +
                        "One or both of your values is/are either too large or small. Please try again.");
                i--;
            } else if (playerShips[shipRow][shipCol] == 1) {
                System.out.println("Failed to place ship #" + (i + 1) + ". " +
                        "That location already has a ship. Please try again.");
                i--;
            } else {
                playerShips[shipRow][shipCol] = 1;
                System.out.println("Successfully placed player ship #"
                        + (i + 1) + " at (" + shipRow + ", " + shipCol + ")");
            }
        }
    }

    static void playerShot() {
        System.out.println("It is your turn to shoot! You have " + numShots + " shots.");
        for (int i = 0; i < numShots; i++) {
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
            if (shotCol > (numCols - 1) || shotRow > (numRows - 1)) {
                System.out.println("Failed to take shot. One or both of your values is/are either too large " +
                        "or small. Please try again.");
                i--;
            } else if (playerShips[shotRow][shotCol] == 2 || playerShips[shotRow][shotCol] == 3) {
                System.out.println("Failed to take shot. That location has already been shot at. Please try again.");
                i--;
            } else {
                System.out.println("Fired shot #" + (i + 1) + " at (" + shotRow + ", " + shotCol + ")");
                if (compShips[shotRow][shotCol] == 1) {
                    System.out.println("HIT!!!");
                    playerHits++;
                    compShips[shotRow][shotCol] = 3;
                    playerBoard[shotRow][shotCol] = 3;
                } else {
                    compShips[shotRow][shotCol] = 2;
                    playerBoard[shotRow][shotCol] = 2;
                    System.out.println("You missed, sorry.");
                }
            }
        checkWin();
        if (gameDone){
            break;
        }
        }
    }

    static void compShot() {
        System.out.println("It's the computer's turn to shoot!");
        for (int i = 0; i < numShots; i++) {
            int shotRow = ThreadLocalRandom.current().nextInt(0, numRows);
            int shotCol = ThreadLocalRandom.current().nextInt(0, numCols);
            if (playerShips[shotRow][shotCol] == 2 || playerShips[shotRow][shotCol] == 3) {
                if (debugMode){
                    System.out.println("Shot failed.");
                }
                i--;
            } else {
                System.out.println("The computer fired shot #" + (i + 1) + " at (" + shotRow + ", " + shotCol + ")");
                if (playerShips[shotRow][shotCol] == 1) {
                    System.out.println("HIT!!!");
                    compHits++;
                    playerShips[shotRow][shotCol] = 3;
                    compBoard[shotRow][shotCol] = 3;
                } else {
                    playerShips[shotRow][shotCol] = 2;
                    compBoard[shotRow][shotCol] = 2;
                    System.out.println("The computer missed!");
                }
            }
        checkWin();
        if (gameDone){
            break;
        }
        }
    }

    static void checkWin(){
        if(playerHits == numShips){
            gameDone = true;
            winner = "player";
        }
        else if (compHits == numShips){
            gameDone = true;
            winner = "computer";
        }
        else{
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
