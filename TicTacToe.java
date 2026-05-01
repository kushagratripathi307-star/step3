import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        
        // Initialize the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
        
        // Print the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        // UC2: Toss to Decide First Player and Symbol
        Random random = new Random();
        int toss = random.nextInt(2); // Generates 0 or 1
        
        int currentPlayer;
        char player1Symbol;
        char player2Symbol;
        
        System.out.println("\nFlipping a coin to decide who starts...");
        if (toss == 0) {
            currentPlayer = 1;
            player1Symbol = 'X';
            player2Symbol = 'O';
            System.out.println("Player 1 won the toss and gets 'X'. Player 2 gets 'O'.");
        } else {
            currentPlayer = 2;
            player1Symbol = 'O';
            player2Symbol = 'X';
            System.out.println("Player 2 won the toss and gets 'X'. Player 1 gets 'O'.");
        }
        
        System.out.println("Player " + currentPlayer + " will start the game.");
        
        // UC3: Accept User Slot Input
        Scanner scanner = new Scanner(System.in);
        int slot = getUserInput(scanner);
        System.out.println("You have selected slot: " + slot);
    }
    
    // Method to handle user input (UC3)
    public static int getUserInput(Scanner scanner) {
        int input = -1;
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter a slot number (1-9): ");
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= 1 && input <= 9) {
                    valid = true;
                } else {
                    System.out.println("Invalid input! Please enter a number between 1 and 9.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number between 1 and 9.");
                scanner.next(); // Clear invalid input
            }
        }
        return input;
    }
}
