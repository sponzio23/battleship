package src.main.java.com.sponzio.battleship;

public class SetVars {
    public static void setVars(){
        System.out.println("Would you like to use the default values? y/n"); // prompt for user input
        // establishes a boolean and loop so that only correct inputs will be accepted
        boolean varsDone = false;

        // the plan
        // dialog for using default vars
        // if true set default vars

        while(!varsDone){
            String useDefaultVars = varScanner.nextLine(); // sets useDefaultVars to user input
            // if useDefaultVars is y this sets the variables to the default values and breaks the loop
            if (useDefaultVars.equals("y")){
                GraphicsBattleship.numRows = 3;
                GraphicsBattleship.numCols = 3;
                GraphicsBattleship.numShips = 3;
                GraphicsBattleship.numShots = 3;
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
                    GraphicsBattleship.numRows = Math.abs(varScanner.nextInt()); // sets the number of rows to user input
                    // checks if the number of rows is greater than or equal to the minimum and breaks the loop if it is
                    if (battleship.numRows >= 3) {
                        System.out.println("Set the number of rows to " + GraphicsBattleship.numRows);
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
                    GraphicsBattleship.numCols = Math.abs(varScanner.nextInt());
                    if (battleship.numCols >= 3) {
                        System.out.println("Set the number of columns to " + GraphicsBattleship.numCols);
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
                    GraphicsBattleship.numShips = Math.abs(varScanner.nextInt()); // sets the number of ships to user input

                    // checks if the input is one of the options and breaks the loop if it is
                    // if the input is not one of the options this returns an error and DOESN'T break the loop
                    switch (battleship.numShips) {
                        case 3, 5, 7 -> {
                            System.out.println("Number of ships set to " + GraphicsBattleship.numShips);
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
                    GraphicsBattleship.numShots = Math.abs(battleship.varScanner.nextInt());

                    switch (battleship.numShots) {
                        case 1, 3, 5 -> {
                            System.out.println("Number of shots set to " + GraphicsBattleship.numShots);
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
        GraphicsBattleship.playerBoard = new int[battleship.numRows][battleship.numCols];
        GraphicsBattleship.playerShips = new int[GraphicsBattleship.numRows][GraphicsBattleship.numCols];
        GraphicsBattleship.compBoard = new int[GraphicsBattleship.numRows][GraphicsBattleship.numCols];
        GraphicsBattleship.compShips = new int[GraphicsBattleship.numRows][GraphicsBattleship.numCols];
    }
}
