import java.util.Arrays;
import java.util.Scanner;

public class battleship {
    // initialize variables
    public static int boardHeight = 5;
    public static int boardLength = 5;
    public static int numShips = 5;
    public static int[][] playerBoard = new int[boardLength][boardHeight];
    public static int[][] playerShips = new int[boardLength][boardHeight];
    public static int[][] compBoard = new int[boardLength][boardHeight];
    public static int[][] compShips = new int[boardLength][boardHeight];

    public static void main(String[] args) {
        System.out.println();

        // conditional for if rules should be printed
        System.out.println("Would you like to hear the rules? y/n"); // prompt for input
        Scanner rulesScanner = new Scanner(System.in); // create a new scanner
        String printRules = rulesScanner.nextLine(); // reads user input
        if (printRules.equals("y")){
            System.out.println("RULES PLACEHOLDER");
            System.out.print("\n");
        }

        // print the player board
        System.out.println("Player Board:");
        for (int[] row : playerBoard) {
            System.out.println(Arrays.toString(row));
            }
        System.out.print("\n");

        // print the computer board
        System.out.println("Computer Board:");
        for (int[] row : compBoard) {
            System.out.println(Arrays.toString(row));
            }
        System.out.println("\n");

        placeShips();

        // test to print player ships
        System.out.println("Player ships:");
        for (int[] row : playerShips) {
            System.out.println(Arrays.toString(row));
            }
        System.out.println("\n");

        }

        // this method needs to be made usable by any board object, either player or computer
        static void placeShips(){ // method to place ships
        for (int i = 0; i < numShips; i++){
            int shipRow = (int) (Math.random() * 10 / 2);  // 0 to 4
            int shipCol = (int) (Math.random() * 10 / 2);  // 0 to 4
            if (playerShips[shipRow][shipCol] == 0){
                playerShips[shipRow][shipCol] = 1;
                System.out.println("Placed a ship at (" + shipCol + ", " + shipRow + ")");
            }
            else{
                System.out.println("Failed to place ship.");
                i--;
            }
        }
    }
    }
