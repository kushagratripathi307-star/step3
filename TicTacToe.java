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
        
        // UC3: Accept User Slot Input (Replaced by loop below)
        Scanner scanner = new Scanner(System.in);
        boolean isWin = false;
        boolean isDraw = false;
        
        // UC8: Continuous Turn-Based Game Loop
        while (!isWin && !isDraw) {
            char currentSymbol = (currentPlayer == 1) ? player1Symbol : player2Symbol;
            System.out.println("\n--- Player " + currentPlayer + "'s Turn (" + currentSymbol + ") ---");
            
            int row, col;
            if (currentPlayer == 1) {
                // Human Turn
                while (true) {
                    int slot = getUserInput(scanner);
                    int[] index = getBoardIndex(slot);
                    row = index[0];
                    col = index[1];
                    if (isValidMove(board, row, col)) {
                        break;
                    } else {
                        System.out.println("Move is invalid! The cell might be occupied. Try again.");
                    }
                }
            } else {
                // Computer Turn
                int[] compMove = getComputerMove(board, random);
                row = compMove[0];
                col = compMove[1];
            }
            
            // Place the move
            placeMove(board, row, col, currentSymbol);
            
            // Print the updated board
            System.out.println("\nCurrent Board:");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(board[i][j]);
                    if (j < 2) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
            
            // Check win/draw
            isWin = checkWin(board, currentSymbol);
            if (isWin) {
                System.out.println("\nPlayer " + currentPlayer + " (" + currentSymbol + ") wins the game!");
                break;
            }
            
            isDraw = checkDraw(board);
            if (isDraw) {
                System.out.println("\nThe game is a draw!");
                break;
            }
            
            // Turn Switching
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }
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

    // Method to convert slot (1-9) to board index (row, col) (UC4)
    public static int[] getBoardIndex(int slot) {
        int row = (slot - 1) / 3;
        int col = (slot - 1) % 3;
        return new int[]{row, col};
    }

    // Method to validate user move (UC5)
    public static boolean isValidMove(char[][] board, int row, int col) {
        // Boundary Checking
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        // Emptiness Checking
        if (board[row][col] != '-') {
            return false;
        }
        return true;
    }

    // Method to place move on the board (UC6)
    public static void placeMove(char[][] board, int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    // Method for the computer to make a random valid move (UC7)
    public static int[] getComputerMove(char[][] board, Random random) {
        int slot;
        int row;
        int col;
        while (true) {
            slot = random.nextInt(9) + 1; // Generate random slot 1-9
            int[] index = getBoardIndex(slot);
            row = index[0];
            col = index[1];
            if (isValidMove(board, row, col)) {
                System.out.println("Computer selected slot: " + slot);
                return new int[]{row, col};
            }
        }
    }

    // Method to check for a win (UC8 helper)
    public static boolean checkWin(char[][] board, char symbol) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
            (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)) {
            return true;
        }
        return false;
    }

    // Method to check for a draw (UC8 helper)
    public static boolean checkDraw(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false; // Still empty spaces
                }
            }
        }
        return true; // Board is full
    }
}
