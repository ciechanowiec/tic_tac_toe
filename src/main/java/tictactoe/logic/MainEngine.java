package tictactoe.logic;

import tictactoe.Controller;
import tictactoe.Definitions.PlayerType;
import tictactoe.logic.disposable.Board;
import tictactoe.logic.disposable.Round;
import tictactoe.logic.persistant.Player;
import tictactoe.logic.persistant.PlayersManager;

public class MainEngine {

    private Controller controller;
    private Board board;
    private PlayersManager playersManager;
    private Round round;

    public MainEngine(Controller controller) {
        this.controller = controller;        
        this.playersManager = new PlayersManager(this);
    }

    public void startRound() {
        this.round = new Round(this);
        this.board = new Board(this);
    }        

    public char[][] getBoardData() {
        return this.board.getBoardData();
    }    

    public void showMarkInputInLogic(int rowIndex, int columnIndex, char markTypeRepresentation) {
        this.board.showMarkInputInLogic(rowIndex, columnIndex, markTypeRepresentation);
    }

    public boolean checkIfWon() {
        return this.board.checkIfWon();
    }

    public boolean checkIfDraw() {
        return this.board.checkIfDraw();
    }

    public void setupPlayers(PlayerType player1Type, String player1Name,
                             PlayerType player2Type, String player2Name) {
        this.playersManager.setupPlayers(player1Type, player1Name,
                                         player2Type, player2Name);
    }

    public Player getPlayer1() {
        return this.playersManager.getPlayer1();
    }

    public Player getPlayer2() {
        return this.playersManager.getPlayer2();
    }

    public Player getCurrentPlayer() {
        return this.round.getCurrentPlayer();
    }

    public Player getCurrentOpponent() {
        return this.round.getCurrentOpponent();
    }

    public Round getRound() {
        return this.round;
    }

    public int[] getMoveFromBot() {
        return this.board.getMoveFromBot();
    }
    
}
