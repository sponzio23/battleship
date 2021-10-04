import java.util.Arrays;
import java.util.Scanner;

public class battleship {
    // these three variables can all be customized to any value
    public static int numRows = 5;
    public static int numCols = 5;
    public static int numShips = 3;

    // create fields for visible boards and hidden boards with ships
    public static int[][] playerBoard = new int[numCols][numRows];
    public static int[][] playerShips = new int[numCols][numRows];
    public static int[][] compBoard = new int[numCols][numRows];
    public static int[][] compShips = new int[numCols][numRows];

    // create scanners to detect input for rules and ship placements
    public static Scanner rulesScanner = new Scanner(System.in);
    public static Scanner placeScanner = new Scanner(System.in);

    public static void main(String[] args) {
        // just doing literally everything to test it
        rules();
        newline();

        placePlayerShips();
        newline();

        placeCompShips();
        newline();

        printPlayerBoard();
        newline();

        printCompBoard();
        newline();

        printPlayerShips();
        newline();

        printCompShips();
        newline();
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

    static void placeCompShips() { // method to place ships
        for (int i = 0; i < numShips; i++) {
            int shipRow = (int) (Math.random() * 10 / 2);  // 0 to 4
            int shipCol = (int) (Math.random() * 10 / 2);  // 0 to 4
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
            System.out.print("\n");
            System.out.println("Place ship #" + (i + 1) + " by entering a row. This value must be between 0 and "
                    + (numRows - 1) + ".");
            int shipRow = placeScanner.nextInt();
            System.out.println("Place ship #" + (i + 1) + " by entering a column. This value must be between 0 and "
                    + (numCols - 1) + ".");
            int shipCol = placeScanner.nextInt();
            if (shipCol > (numCols - 1) || shipRow > (numRows - 1)) {
                System.out.println("Failed to place ship. Your value is either too large or small. Please try again.");
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

    // shot methods are a wip
    static void playerShot(){
        System.out.println("pew pew");
    }
    static void compShot(){
        System.out.println("pew pew");
    }
}
