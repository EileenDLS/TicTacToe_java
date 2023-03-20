import java.util.Random;
import java.util.Scanner;

public class TicTacToe_PC_Improve {
    private Pieces[][] board;
    private Pieces firstPlayer;
    private Pieces secondPlayer;

    // constructor: initialize the board, firstPlayer and secondPlayer
    public TicTacToe_PC_Improve(){
        board = new Pieces[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Pieces.EMPTY;
            }
        }
        firstPlayer = Pieces.X;
        secondPlayer = Pieces.O;
    }

    // show the game board
    private void showBoard(){
        System.out.println("\n-------TicTacToe Board------\n");
        int i, j;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                System.out.print(board[i][j] + "  |  ");
            }
            System.out.println();
            if (i != 2 ){
                System.out.println("----------------------------");
            }
        }
    }

    // run this game
    private void playGame(){
        System.out.println("\nThere are 2 players.");
        System.out.println("Do you want to go first? (Y/N)");
        Scanner input = new Scanner(System.in);
        String answer = input.next();
        int round;
        if (answer.equals("Y")){
            for (round = 0; round < 9; round++) {
                // judge it's who's turn
                if (round % 2 == 0){
                    playerMove(firstPlayer);
                    if (gameOver()){
                        showBoard();
                        System.out.println("\nCongrats! You win!");
                        break;
                    }
                } else {
                    pcMove(secondPlayer);
                    if (gameOver()){
                        showBoard();
                        System.out.println("\nSorry! You lose!");
                        break;
                    }

                }
            }
        } else {
            for (round = 0; round < 9; round++) {
                // judge it's who's turn
                if (round % 2 == 0){
                    pcMove(firstPlayer);
                    if (gameOver()){
                        showBoard();
                        System.out.println("\nSorry! You lose!");
                        break;
                    }
                } else {
                    playerMove(secondPlayer);
                    if (gameOver()){
                        showBoard();
                        System.out.println("\nCongrats! You win!");
                        break;
                    }
                }
            }
        }
        if (round == 9){
            System.out.println("\n The game is draw!");
        }
    }

    // player chooses spot
    private void playerMove(Pieces player){
        showBoard();
        String curPlayer;
        if (player == Pieces.X){
            curPlayer = "Player 1(X)";
        } else {
            curPlayer = "Player 2(O)";
        }
        Scanner input = new Scanner(System.in);
        System.out.printf("\nNow, it's %s's turn.\n", curPlayer);
        System.out.print("Enter row number [1-3]: ");
        int row = input.nextInt() - 1;
        System.out.print("Enter column number [1-3]: ");
        int col = input.nextInt() - 1;
        while (board[row][col] != Pieces.EMPTY){
            System.out.println("This spot has been taken, plz choose empty one.");
            System.out.print("Enter row number [1-3]: ");
            row = input.nextInt() - 1;
            System.out.print("Enter column number [1-3]: ");
            col = input.nextInt() - 1;
        }
        board[row][col] = player;
    }

    // computer chooses spot: improved version
    private void pcMove(Pieces player){
        int row;
        int col;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j] == player) { // 1, 2
                    if (i != 0) {
                        row = i - 1;
                    } else {
                        row = i + 1;
                    }
                    if (j != 0) {
                        col = j - 1;
                    } else {
                        col = j + 1;
                    }
                }
            }
        }

        Random random = new Random();
        row = random.nextInt(3);
        col = random.nextInt(3);
        while (board[row][col] != Pieces.EMPTY){
            row = random.nextInt(3);
            col = random.nextInt(3);
        }
        System.out.println("\nNow, it's computer's turn...");
        board[row][col] = player;
    }

    // check game is over or not
    private boolean gameOver(){
        return (rowOver() || colOver() || diagonalOver());
    }

    // check rows
    private boolean rowOver(){
        for (int i = 0; i < 3; i++) {
            if (endRule(board[i][0], board[i][1], board[i][2])){
                return true;
            }
        }
        return false;
    }
    // check columns
    private boolean colOver(){
        for (int j = 0; j < 3; j++) {
            if (endRule(board[0][j], board[1][j], board[2][j])){
                return true;
            }
        }
        return false;
    }
    // check diagonals
    private boolean diagonalOver(){
        return (endRule(board[0][0], board[1][1], board[2][2]) || endRule(board[2][0], board[1][1], board[0][2]));
    }
    // the end condition of the game
    private boolean endRule(Pieces spot1, Pieces spot2, Pieces spot3){
        if (spot1 != Pieces.EMPTY && spot1 == spot2 && spot2 == spot3){
            return true;
        }
        return false;
    }

    private enum Pieces{
        X, O, EMPTY
    }



    public static void main(String[] args) {
        TicTacToe_PC_Improve game = new TicTacToe_PC_Improve();
        game.playGame();
    }
}



