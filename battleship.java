import java.util.Arrays;

public class battleship {
    // initialize variables
    public static int boardHeight = 10;
    public static int boardLength = 10;
    public static int playerShips;
    public static int compShips;
    public static int[][] playerBoard = new int[boardLength][boardHeight];
    public static int[][] compBoard = new int[boardLength][boardHeight];


    public static void main(String[] args) {
        // print the rules
        System.out.println("RULES");
        System.out.print("\n");

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
        }


    }
