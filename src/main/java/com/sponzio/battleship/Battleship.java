package src.main.java.com.sponzio.battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class Battleship implements ActionListener {
    // initializing variables to be defined later
    public static int numRows;
    public static int numCols;
    public static int numShips;
    public static int numShots;

    // initializing the 2d arrays for the boards and ships
    public static int[][] playerBoard;
    public static int[][] playerShips;
    public static int[][] compBoard;
    public static int[][] compShips;

    // initializing variables used to check and output the winner of the game
    public static int playerHits = 0;
    public static int compHits = 0;
    public static boolean gameDone = false;
    public static String winner = "none";

    public static JFrame frame = new JFrame();

    public static JButton startButton = new JButton("Start the game");
    public static JButton varsButton = new JButton("Set the game's variables");
    public static JButton compShipsButton = new JButton("Place the computer's ships");
    public static JButton playerShipsButton = new JButton("Place your ships");
    public static JButton mainLoopButton = new JButton("Begin taking turns taking shots");
    public static JButton finishButton = new JButton("End the game");

    public static void main(String[] args) {
        JButton[] buttons = {startButton, varsButton, compShipsButton, playerShipsButton, mainLoopButton, finishButton};
        String[] buttonCommands = {"startGame", "setVars", "placeCompShips", "placePLayerShips", "mainLoop", "finish"};
        int i = 0;
        for(JButton buttonIndex : buttons){
            buttonIndex.setVerticalTextPosition(AbstractButton.CENTER);
            buttonIndex.setHorizontalTextPosition(AbstractButton.CENTER);
            buttonIndex.setActionCommand(buttonCommands[i]);
            buttonIndex.addActionListener(new Battleship());
            i++;
        }

        // TODO add padding to this
        frame.add(startButton, BorderLayout.PAGE_END);
        frame.add(new JLabel("<HTML> <H1> Welcome to Battleship! </H1> </HTML>", SwingConstants.CENTER), BorderLayout.PAGE_START);

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
                //System.out.println("The computer placed ship #" + (i+1) + " at (" + shipRow + ", " + shipCol + ")");
            }
            // if there is already a ship there this decreases the iterator so that the coordinates get regenerated
            else {
                // prints an error
                //System.out.println("The computer failed to place ship #" + (i+1) + ".");
                i--;
            }
        }
        //System.out.println("The computer has placed its ships!"); // lets the user know this method has finished
        JOptionPane.showMessageDialog(Battleship.frame, "The computer's ships have been placed",
                null, JOptionPane.PLAIN_MESSAGE);
    }

    static void compShot() {
        //System.out.println("It's the computer's turn to shoot!");
        // a loop that is run once for each ship
        String didCompShotHit;
        for (int i = 0; i < numShots; i++) {
            // sets the row and column of the shot to a random number between 0 and the number of rows/columns
            int shotRow = ThreadLocalRandom.current().nextInt(0, numRows);
            int shotCol = ThreadLocalRandom.current().nextInt(0, numCols);
            // decreases the iterator if the location has been shot at before and prints an error if debug mode is on
            if (playerShips[shotRow][shotCol] == 2 || playerShips[shotRow][shotCol] == 3) {
                //System.out.println("Shot failed.");
                i--;
            }
            else {
                // prints the shot
                //System.out.println("The computer fired shot #" + (i + 1) + " at (" + shotRow + ", " + shotCol + ")");
                // checks if there is a ship at the location
                if (playerShips[shotRow][shotCol] == 1) {
                    //System.out.println("HIT!!!"); // informs the user of the hit
                    didCompShotHit = "hit!";
                    compHits++; // increases the number of hits
                    // sets the locations on the arrays to indicate a hit
                    playerShips[shotRow][shotCol] = 3;
                    compBoard[shotRow][shotCol] = 3;
                } else {
                    // informs the user and sets the locations on the arrays to indicate a miss
                    playerShips[shotRow][shotCol] = 2;
                    compBoard[shotRow][shotCol] = 2;
                    //System.out.println("The computer missed!");
                    didCompShotHit = "missed.";
                }
                JOptionPane.showMessageDialog(Battleship.frame,
                        "The computer fired shot #" + (i + 1) + " at (" + (shotRow + 1) + ", " + (shotCol + 1) +
                                ") and " + didCompShotHit + "\nYou have " + (numShips - compHits) + " ships remaining.",
                        null, JOptionPane.PLAIN_MESSAGE);
            }

        // checks if the game has been won after each shot
        checkWin();
        if (gameDone) {break;}
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
        if (winner.equals("player")){
            JOptionPane.showMessageDialog(Battleship.frame, "Congrats! You won!",
                    null, JOptionPane.PLAIN_MESSAGE);
        }
        else if (winner.equals("computer")){
            JOptionPane.showMessageDialog(Battleship.frame, "Sorry, you lost.",
                    null, JOptionPane.PLAIN_MESSAGE);
        }
    }

    static void addToFeed(Component input){
        frame.add(input, BorderLayout.PAGE_END);
        frame.revalidate();
        frame.repaint();
    }

    static void removeFromFeed(Component input){
        frame.remove(input);
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("startGame".equals(e.getActionCommand())) {
            removeFromFeed(startButton);
            Rules.rulesDialogue();
            addToFeed(varsButton);
        }

        else if ("setVars".equals(e.getActionCommand())) {
            removeFromFeed(varsButton);
            SetVars.setVars();
            addToFeed(compShipsButton);
        }

        else if ("placeCompShips".equals(e.getActionCommand())) {
            removeFromFeed(compShipsButton);
            placeCompShips();
            addToFeed(playerShipsButton);
        }

        else if ("placePLayerShips".equals(e.getActionCommand())){
            removeFromFeed(playerShipsButton);
            PlacePlayerShips.placePlayerShips();
            addToFeed(mainLoopButton);
        }

        else if ("mainLoop".equals(e.getActionCommand())) {
            removeFromFeed(mainLoopButton);

            while (!gameDone) {
                PlayerShot.playerShot();
                if (gameDone){break;}
                compShot();
            }

            addToFeed(finishButton);
        }
        else if ("finish".equals(e.getActionCommand())) {
            removeFromFeed(finishButton);
            frame.dispose();
            System.exit(0);
        }
    }
}
