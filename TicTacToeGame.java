package TicTacToe;

import TicTacToe.Model.*;
import java.util.Deque;
import java.util.LinkedList;

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
        return "test";
    }
}

