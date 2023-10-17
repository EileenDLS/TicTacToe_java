import java.util.Scanner;

public class TicTacToe_PC_Improve {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';  // Human player is X

    public static void main(String[] args) {
        initializeBoard();
        while (true) {
            printBoard();
            if (currentPlayer == 'X') {
                playHumanTurn();
            } else {
                playComputerTurn();
            }

            if (checkWin(board, currentPlayer)) {
                printBoard();
                System.out.println(currentPlayer + " wins!");
                break;
            } else if (isBoardFull(board)) {
                printBoard();
                System.out.println("The game is a tie!");
                break;
            }
            switchPlayer();
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n---------");
        }
    }

    private static void playHumanTurn() {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        do {
            System.out.print("Enter row and column (1-3) separated by a space: ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
        } while (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ');
        board[row][col] = 'X';
    }

    private static void playComputerTurn() {
        int[] bestMove = minimax(board, 'O');
        board[bestMove[0]][bestMove[1]] = 'O';
    }

    private static int[] minimax(char[][] board, char currentPlayer) {
        char opponent = (currentPlayer == 'O') ? 'X' : 'O';
        int[] bestMove = new int[]{-1, -1, (currentPlayer == 'O') ? Integer.MIN_VALUE : Integer.MAX_VALUE};

        // Check if the game is over
        if (checkWin(board, 'O')) {
            return new int[]{-1, -1, 1};
        } else if (checkWin(board, 'X')) {
            return new int[]{-1, -1, -1};
        } else if (isBoardFull(board)) {
            return new int[]{-1, -1, 0};
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Try this move for the current "player"
                if (board[i][j] == ' ') {
                    board[i][j] = currentPlayer;

                    // Recursively find the value of this move
                    int moveValue = minimax(board, opponent)[2];

                    // Undo the move
                    board[i][j] = ' ';

                    // Update the bestMove if:
                    // - It's player O and found a move with higher value
                    // - It's player X and found a move with lower value
                    if ((currentPlayer == 'O' && moveValue > bestMove[2]) || (currentPlayer == 'X' && moveValue < bestMove[2])) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestMove[2] = moveValue;
                    }
                }
            }
        }
        return bestMove;
    }
    private static boolean checkWin(char[][] board, char currentPlayer) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                    (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }
        return false;
    }

    private static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}