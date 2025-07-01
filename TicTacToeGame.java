package TicTacToe;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import TicTacToe.Model.*;

public class TicTacToeGame {
    Deque<Player> players;
    Board gameBoard;

    TicTacToeGame() {
        initializeGame();
    }

    public void initializeGame() {
        players = new LinkedList<>();
        PlayingPieceX crosspiece = new PlayingPieceX();
        Player player1 = new Player("Player1", crosspiece);

        PlayingPieceO noughtspiece = new PlayingPieceO();
        Player player2 = new Player("Player2", noughtspiece);

        players.add(player1);
        players.add(player2);

        gameBoard = new Board(3);
    }

    public String startGame() {
        boolean noWinner = true;
        Scanner sc = new Scanner(System.in);
        while(noWinner) {
            Player playerTurn = players.removeFirst();
            gameBoard.printBoard();
            List<Pair<Integer, Integer>> freespaces = gameBoard.getFreeCells();

            if(freespaces.isEmpty()) {
                noWinner = false;
                continue;
            }

            System.out.print("Player:" + playerTurn.name + "Enter row, column: ");
            
            String s = sc.nextLine();
            String values[] = s.split(",");
            int inputrow = Integer.valueOf(values[0]);
            int inputcol = Integer.valueOf(values[1]);


            boolean pieceAddedSuccess = gameBoard.addPiece(inputrow, inputcol, playerTurn.playingPiece);
            if(!pieceAddedSuccess) {
                System.out.println("Incorrect position chosen, try again.");
                players.addFirst(playerTurn);
                continue;
            }

            players.addLast(playerTurn);

            boolean winner = isThereWinner(inputrow, inputcol, playerTurn.playingPiece.piecetype);
            if(winner) {
                sc.close();
                return playerTurn.name;
            }
        }

        sc.close();

        return "tie";
    }

    public boolean isThereWinner(int row, int col, PieceType pieceType) {
        boolean rowMatch = true;
        boolean colMatch = true;
        boolean diagMatch = true;
        boolean antiDiagMatch = true;

        for(int i=0;i<gameBoard.size;i++){
            if(gameBoard.board[row][i] == null || gameBoard.board[row][i].piecetype != pieceType) 
                rowMatch = false;
            
            if(gameBoard.board[i][col] == null || gameBoard.board[i][col].piecetype != pieceType) 
                colMatch = false;  

        }

        for(int i=0, j=0; i<gameBoard.size; i++, j++){
            if(gameBoard.board[i][j] == null || gameBoard.board[i][j].piecetype != pieceType) 
                diagMatch = false;
        }

        for(int i=0, j=gameBoard.size-1; i<gameBoard.size; i++, j--){
            if(gameBoard.board[i][j] == null || gameBoard.board[i][j].piecetype != pieceType) 
                antiDiagMatch = false;
        }

        return rowMatch || colMatch || diagMatch || antiDiagMatch;
    }
}
