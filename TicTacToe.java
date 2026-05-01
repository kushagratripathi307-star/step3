import java.util.Random;

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
    }
}
