package src.main.java.com.sponzio.battleship;

import javax.swing.*;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class GraphicsBattleship {
    // initializing variables to be defined later
    public static int numRows;
    public static int numCols;
    public static int numShips;
    public static int numShots;

    // initializing various scanners for different places that require input
    public static Scanner varScanner = new Scanner(System.in);
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
        // create the frame for the game
        JFrame frame = new JFrame();
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // setup methods that don't get repeated
        rules.rulesDialogue(frame);
        playerShips = new int[5][5];
        PrintArray.printArray(frame, playerShips);
        //setVars();
        //placePlayerShips();
        //placeCompShips();

        // main gameplay loop
//        while (!gameDone) {
//            // print info for player
//            PrintArray.printArray(frame, playerBoard);
//            PrintArray.printArray(frame, playerShips);
//
//            playerShot();
//            // checks if game is done and breaks the while loop because the while loop only updates at the end
//            if (gameDone){
//                break;
//            }
//            compShot();
//            // no check here because the while loop does it
//        }
        System.out.println("GAME OVER!!!");
        System.out.println("The " + winner + " won!");
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
                // prints the location of the placed ship
                System.out.println("The computer placed ship #" + (i+1) +"at (" + shipRow + ", " + shipCol + ")");
            }
            // if there is already a ship there this decreases the iterator so that the coordinates get regenerated
            else {
                // prints an error
                System.out.println("The computer failed to place ship #" + (i+1) + ".");
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
                System.out.println("Shot failed.");
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
