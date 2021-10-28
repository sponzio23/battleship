package src.main.java.com.sponzio.battleship;

import java.util.Arrays; // used to print arrays
import java.util.Scanner; // used to monitor for user input
import java.util.concurrent.ThreadLocalRandom; // used to generate random numbers

public class battleship {
    public static boolean debugMode = false; // if true, secret information about the location of ships will be printed

    // initializing variables to be defined later
    public static int numRows;
    public static int numCols;
    public static int numShips;
    public static int numShots;

    // initializing various scanners for different places that require input
    public static Scanner varScanner = new Scanner(System.in);
    public static Scanner rulesScanner = new Scanner(System.in);
    public static Scanner placeScanner = new Scanner(System.in);
    public static Scanner shotScanner = new Scanner(System.in);

    // initializing the 2d arrays for the boards and ships
    public static int[][] playerBoard;
    public static int[][] playerShips;
    public static int[][] compBoard;
    public static int[][] compShips;

    // initializing variables used to check and output the winner of the game
    public static int playerHits = 0;
    public static int compHits = 0;
    public static boolean gameDone = false;
    public static String winner = null;

    public static void main(String[] args) {
        // a disclaimer that debug mode is on
        if (debugMode){
            System.out.println("DEBUG MODE IS ENABLED");
            newline();
        }

        // setup methods that don't get repeated
        rules();
        setVars();
        placePlayerShips();
        placeCompShips();

        // main gameplay loop
        while (!gameDone) {
            // print info for player
            printArray(playerBoard);
            printArray(playerShips);
            playerShot();
            // checks if game is done and breaks the while loop because the while loop only updates at the end
            if (gameDone){
                break;
            }
            compShot();
            // no check here because the while loop does it
        }
        System.out.println("GAME OVER!!!");
        System.out.println("The " + winner + " won!");
    }

    // a very simple method that only existed because I got tired of typing System.out.print("\n"); over and over
    static void newline() {
        System.out.print("\n");
    }

    // a method that prints any 2d array of integers based into it
    static void printArray(int[][] input) {
        // print the name of the array if it's important for the user to know
        if (input == playerBoard){
            System.out.println("Player Board");
        }
        else if (input == playerShips){
            System.out.println("Player Ships");
        }

        // this loops through each row and uses the java.util.Arrays package that was imported at the start
        // to convert it to a string and print it
        for (int[] row : input) {
            System.out.println(Arrays.toString(row));
        }
    }

    // a method that checks if the player wants to hear the rules and prints them if they do
    static void rules() {
        System.out.println("Would you like to hear the rules? y/n"); // prompt for the user input
        // establishes a boolean and loop so that only correct inputs will be accepted
        boolean rulesDone = false;
        while(!rulesDone) {
            String printRules = rulesScanner.nextLine(); // sets printRules to user input
            // if printRules is y this prints the rules and then breaks the loop
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

                    These rules are based on Hasbro's official rules for Battleship.
                    (https://www.hasbro.com/common/instruct/Battleship.PDF)

                    ENJOY!
                    """);
                rulesDone = true;
            }
            // if printRules is n this breaks the loop without doing anything
            else if (printRules.equals("n")) {
                rulesDone = true;
            }
            // if printRules isn't y or n this gives an error and DOESN'T break the loop
            else {
                System.out.println("Invalid input, please try again. Respond with y or n ONLY.");
            }
        }
    }

    // a method that gives values to the variables initialized at the start
    static void setVars() {
        System.out.println("Would you like to use the default values? y/n"); // prompt for user input
        // establishes a boolean and loop so that only correct inputs will be accepted
        boolean varsDone = false;
        while(!varsDone){
            String useDefaultVars = varScanner.nextLine(); // sets useDefaultVars to user input
            // if useDefaultVars is y this sets the variables to the default values and breaks the loop
            if (useDefaultVars.equals("y")){
                numRows = 3;
                numCols = 3;
                numShips = 3;
                numShots = 3;
                varsDone = true;
            }
            // if useDefaultVars is n this lets the user define the variables and breaks the loop
            else if (useDefaultVars.equals("n")){
                // this section defines the number of rows the board should have
                System.out.println("How many rows would you like the board to have? Please enter a number " +
                        "greater than or equal to 3."); // prompt for user input
                // establishes a boolean and loop so that only correct inputs will be accepted
                boolean rowChoiceDone = false;
                while (!rowChoiceDone) {
                    // checks if input is an integer and returns an error if it's not
                    while (!varScanner.hasNextInt()) {
                        System.out.println("Please enter a number.");
                        varScanner.next();
                    }
                    numRows = Math.abs(varScanner.nextInt()); // sets the number of rows to user input
                    // checks if the number of rows is greater than or equal to the minimum and breaks the loop if it is
                    if (numRows >= 3) {
                        System.out.println("Set the number of rows to " + numRows);
                        rowChoiceDone = true;
                    }
                    // if the number of rows isn't greater than or equal to the minimum
                    // this returns an error and DOESN'T break the loop
                    else {
                        System.out.println("Please enter a number greater than or equal to 3.");
                    }
                }
                // this section defines the number of columns the board should have
                // this section is the same as the one above it just with different variables
                System.out.println("How many columns would you like the board to have? Please enter a number " +
                        "greater than or equal to 3.");
                boolean columnChoiceDone = false;
                while (!columnChoiceDone) {
                    while (!varScanner.hasNextInt()) {
                        System.out.println("Please enter a number.");
                        varScanner.next();
                    }
                    numCols = Math.abs(varScanner.nextInt());
                    if (numCols >= 3) {
                        System.out.println("Set the number of columns to " + numCols);
                        columnChoiceDone = true;
                    }
                    else {
                        System.out.println("Please enter a number greater than or equal to 3.");
                    }
                }

                // this section defines the number of ships each player should have
                System.out.println("How many ships would you like the board to have? " +
                        "Choose 3, 5, or 7."); // prompt for user input
                // establishes a boolean and loop so that only correct inputs will be accepted
                boolean shipChoiceDone = false;
                while (!shipChoiceDone) {
                    // checks if input is an integer and returns an error if it's not
                    while (!varScanner.hasNextInt()) {
                        System.out.println("Please enter a number");
                        varScanner.next();
                    }
                    numShips = Math.abs(varScanner.nextInt()); // sets the number of ships to user input

                    // checks if the input is one of the options and breaks the loop if it is
                    // if the input is not one of the options this returns an error and DOESN'T break the loop
                    switch (numShips) {
                        case 3, 5, 7 -> {
                            System.out.println("Number of ships set to " + numShips);
                            shipChoiceDone = true;
                        }
                        default -> System.out.println("Please enter one of the options.");
                    }
                }

                // this section defines the number of shots each player should have
                // this section is the same as the one above it just with different variables
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
            // if useDefaultVars isn't y or n this gives an error and DOESN'T break the loop
            else{
                System.out.println("Invalid input, please try again. Respond with y or n ONLY.");
            }
        }

        // defines the 2d arrays for the boards and ships using the variables set in this section
        playerBoard = new int[numRows][numCols];
        playerShips = new int[numRows][numCols];
        compBoard = new int[numRows][numCols];
        compShips = new int[numRows][numCols];
    }

    // a method that randomly places the computer's ships
    static void placeCompShips() {
        // a for loop that is run once for each ship
        for (int i = 0; i < numShips; i++) {
            // sets the ship's row and column to a random number between 0 and the number of rows/columns
            int shipRow = ThreadLocalRandom.current().nextInt(0, numRows);
            int shipCol = ThreadLocalRandom.current().nextInt(0, numCols);
            // places a ship at the randomly generated coordinates if there isn't already one there
            if (compShips[shipRow][shipCol] == 0) {
                compShips[shipRow][shipCol] = 1;
                // prints the location of the placed ship if debug mode is on
                if (debugMode){
                    System.out.println("The computer placed ship #" + (i+1) +"at (" + shipRow + ", " + shipCol + ")");
                }
            }
            // if there is already a ship there this decreases the iterator so that the coordinates get regenerated
            else {
                // prints an error if debug mode is on
                if (debugMode){
                    System.out.println("The computer failed to place ship #" + (i+1) + ".");
                }
                i--;
            }
        }
        System.out.println("The computer has placed it's ships!"); // lets the user know this method has finished
    }

    // a method that places the player's ships based on user input
    static void placePlayerShips() {
        System.out.println("Placing " + numShips + " ships.");
        // a loop that is run once for each ship
        for (int i = 0; i < numShips; i++) {
            System.out.println("Place player ship #" + (i + 1) + " by entering a row. " +
                    "This value must be between 0 and " + (numRows - 1) + "."); // prompt for user input
            // checks if input is an integer and returns an error if it's not
            while (!placeScanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                placeScanner.next();
            }
            int shipRow = Math.abs(placeScanner.nextInt()); // sets the ship's row to the user input

            // this section is the same as the one above it
            System.out.println("Place player ship #" + (i + 1) + " by entering a column. " +
                    "This value must be between 0 and " + (numCols - 1) + ".");
            while (!placeScanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                placeScanner.next();
            }
            int shipCol = Math.abs(placeScanner.nextInt());

            // decreases the iterator and prints an error if either user input is too large
            if (shipCol > (numCols - 1) || shipRow > (numRows - 1)) {
                System.out.println("Failed to place ship #"  + (i + 1) + ". " +
                        "One or both of your values is/are either too large or small. Please try again.");
                i--;
            }
            // decreases the iterator and prints an error if there is already a ship at the chosen location
            else if (playerShips[shipRow][shipCol] == 1) {
                System.out.println("Failed to place ship #" + (i + 1) + ". " +
                        "That location already has a ship. Please try again.");
                i--;
            }
            // if the user input isn't too large and there isn't already a ship at the location this places a ship
            // and informs the user that the ship has been placed
            else {
                playerShips[shipRow][shipCol] = 1;
                System.out.println("Successfully placed player ship #"
                        + (i + 1) + " at (" + shipRow + ", " + shipCol + ")");
            }
        }
    }

    static void playerShot() {
        System.out.println("It is your turn to shoot! You have " + numShots + " shots.");
        // a loop that is run once for each ship
        for (int i = 0; i < numShots; i++) {
            System.out.println("Take shot #" + (i + 1) + " by entering a row. This value must be between 0 and "
                    + (numRows - 1) + "."); // prompt for user input
            // checks if input is an integer and returns an error if it's not
            while (!shotScanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                shotScanner.next();
            }
            int shotRow = Math.abs(shotScanner.nextInt()); // sets the row of the shot to the user input

            // this section is the same as the one above but with different variables
            System.out.println("Take shot #" + (i + 1) + " by entering a column. This value must be between 0 and "
                    + (numCols - 1) + ".");
            while (!shotScanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                shotScanner.next();
            }
            int shotCol = Math.abs(shotScanner.nextInt());

            // decreases the iterator and prints an error if either user input is too large
            if (shotCol > (numCols - 1) || shotRow > (numRows - 1)) {
                System.out.println("Failed to take shot. One or both of your values is/are either too large " +
                        "or small. Please try again.");
                i--;
            }
            // decreases the iterator and prints an error if the chosen location has already been shot at
            else if (playerShips[shotRow][shotCol] == 2 || playerShips[shotRow][shotCol] == 3) {
                System.out.println("Failed to take shot. That location has already been shot at. Please try again.");
                i--;
            }
            // if player input is valid
            else {
                // prints the shot
                System.out.println("Fired shot #" + (i + 1) + " at (" + shotRow + ", " + shotCol + ")");
                // checks if there is a ship at the location
                if (compShips[shotRow][shotCol] == 1) {
                    System.out.println("HIT!!!"); // informs the user of the hit
                    playerHits++; // increases the number of hits
                    // sets the locations on the arrays to indicate a hit
                    compShips[shotRow][shotCol] = 3;
                    playerBoard[shotRow][shotCol] = 3;
                } else {
                    // informs the user and sets the locations on the arrays to indicate a miss
                    System.out.println("You missed, sorry.");
                    compShips[shotRow][shotCol] = 2;
                    playerBoard[shotRow][shotCol] = 2;
                }
            }
        // checks if the game has been won after each shot
        checkWin();
        if (gameDone){
            break;
        }
        }
    }

    static void compShot() {
        System.out.println("It's the computer's turn to shoot!");
        // a loop that is run once for each ship
        for (int i = 0; i < numShots; i++) {
            // sets the row and column of the shot to a random number between 0 and the number of rows/columns
            int shotRow = ThreadLocalRandom.current().nextInt(0, numRows);
            int shotCol = ThreadLocalRandom.current().nextInt(0, numCols);
            // decreases the iterator if the location has been shot at before and prints an error if debug mode is on
            if (playerShips[shotRow][shotCol] == 2 || playerShips[shotRow][shotCol] == 3) {
                if (debugMode){
                    System.out.println("Shot failed.");
                }
                i--;
            } else {
                // prints the shot
                System.out.println("The computer fired shot #" + (i + 1) + " at (" + shotRow + ", " + shotCol + ")");
                // checks if there is a ship at the location
                if (playerShips[shotRow][shotCol] == 1) {
                    System.out.println("HIT!!!"); // informs the user of the hit
                    compHits++; // increases the number of hits
                    // sets the locations on the arrays to indicate a hit
                    playerShips[shotRow][shotCol] = 3;
                    compBoard[shotRow][shotCol] = 3;
                } else {
                    // informs the user and sets the locations on the arrays to indicate a miss
                    playerShips[shotRow][shotCol] = 2;
                    compBoard[shotRow][shotCol] = 2;
                    System.out.println("The computer missed!");
                }
            }
        // checks if the game has been won after each shot
        checkWin();
        if (gameDone){
            break;
        }
        }
    }

    // a method to check if the game has been won
    static void checkWin(){
        // checks if the number of ships the player has hit is equal to the number of ships
        if(playerHits == numShips){
            // breaks the main game loop
            gameDone = true;
            // declares the player as the winner
            winner = "player";
        }
        // checks if the number of ships the computer has hit is equal to the number of ships
        else if (compHits == numShips){
            // breaks the main game loop
            gameDone = true;
            // declares the computer as the winner
            winner = "computer";
        }
        else{
            // makes sure the main game loop is not broken
            gameDone = false;
        }
    }
}
