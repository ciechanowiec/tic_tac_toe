package tictactoe.logic;

import tictactoe.Controller;
import tictactoe.Definitions.Mark;
import tictactoe.Definitions.PlayerType;
import tictactoe.logic.disposable.Board;
import tictactoe.logic.disposable.Round;
import tictactoe.logic.disposable.Round.RoundResult;
import tictactoe.logic.persistant.Player;
import tictactoe.logic.persistant.PlayersManager;

public class MainEngine {

    private Controller controller;
    private PlayersManager playersManager;
    private int roundsPlayed;
    private Round round;
    private Board board;

    public MainEngine(Controller controller) {
        this.controller = controller;        
        this.playersManager = new PlayersManager(this);
    }
    
    public void processStartGameAction(PlayerType player1Type, String player1Name,
                                       PlayerType player2Type, String player2Name) {        
        setupPlayers(player1Type, player1Name, player2Type, player2Name);
        startRound();        
    }

    public void setupPlayers(PlayerType player1Type, String player1Name,
                            PlayerType player2Type, String player2Name) {
        this.playersManager.setupPlayers(player1Type, player1Name,
                                        player2Type, player2Name);
    }

    public void startRound() {
        roundsPlayed++;
        this.round = new Round(this);
        this.board = new Board(this);
        this.controller.prepareAllButtonsForRoundStart();
        this.controller.showPendingScreen();
        if (getCurrentPlayer().getPlayerType() != PlayerType.HUMAN) {
            makeBotMove();
        }
    }

    public void showMarkInputInLogic(int[] cellCoordinates) {
        Mark currentPlayerMarkType = getCurrentPlayer().getMarkType();
        this.board.showMarkInputInLogic(cellCoordinates, currentPlayerMarkType.getRepresentation());
        showMarkInputInGUI(cellCoordinates, currentPlayerMarkType);
        determineGameFlowAfterMarkInput();
    }

    private void showMarkInputInGUI(int[] cellCoordinates, Mark currentPlayerMarkType) {
        this.controller.showMarkInputInGUI(cellCoordinates, currentPlayerMarkType);
    }

    private void determineGameFlowAfterMarkInput() {
        if (this.board.checkIfWon()) {            
            processWinCombination();
        } else if (this.board.checkIfDraw()) {            
            processDrawCombination();
        } else {            
            proceedRound();
        }
    }
    
    private void processWinCombination() {        
        this.controller.grayOutAllButtons();
        addScoreForWinner();
        this.round.setRoundResult(RoundResult.WIN);
        this.round.setWinner(getCurrentPlayer());
        this.controller.showFinishScreen();        
    }

    private void addScoreForWinner() {
        if (getCurrentPlayer() == getPlayer1()) {
            getPlayer1().incrementScores();
        } else if (getCurrentPlayer() == getPlayer2()) {
            getPlayer2().incrementScores();
        }
    }
    
    private void processDrawCombination() {
        this.round.setRoundResult(RoundResult.DRAW);
        this.controller.showFinishScreen();        
    }

    private void proceedRound() {        
        this.round.invertPlayers();
        this.controller.showTurnCommandForCurrentPlayer();
        if (getCurrentPlayer().getPlayerType() != PlayerType.HUMAN) {
            makeBotMove();
        } 
    }

    public void makeBotMove() {            
        showMarkInputInLogic(this.board.getMoveFromBot());        
    }

    public int getRoundsPlayed() {
        return this.roundsPlayed;   
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

    public Player getWinner() {
        return this.round.getWinner();
    }

    public RoundResult getRoundResult() {
        return this.round.getRoundResult();
    }
    
}
