package tictactoe;

import tictactoe.Definitions.Mark;
import tictactoe.Definitions.PlayerType;
import tictactoe.gui.MainFrame;
import tictactoe.logic.MainEngine;
import tictactoe.logic.disposable.Round.RoundResult;
import tictactoe.logic.persistant.Player;

public class Controller {
    
    private MainEngine mainEngine;
    private MainFrame mainFrame;

    public void runGame() {
        this.mainEngine = new MainEngine(this);
        this.mainFrame = new MainFrame(this);    
    }
    
    public void processStartGameAction(PlayerType player1Type, String player1Name,
                                       PlayerType player2Type, String player2Name) {        
        this.mainEngine.processStartGameAction(player1Type, player1Name, player2Type, player2Name);
    }
    
    public void prepareAllButtonsForRoundStart() {
        this.mainFrame.prepareAllButtonsForRoundStart();
    }

    public void startRound() {     
        this.mainEngine.startRound();
    }
    
    public void showPendingScreen() {
        this.mainFrame.showPendingScreen();
    }

    public void showFinishScreen() {
        this.mainFrame.showFinishScreen();
    }

    public int getRoundsPlayed() {
        return this.mainEngine.getRoundsPlayed();   
    }

    public Player getPlayer1() {
        return this.mainEngine.getPlayer1();
    }

    public Player getPlayer2() {
        return this.mainEngine.getPlayer2();
    }

    public Player getCurrentPlayer() {
        return this.mainEngine.getCurrentPlayer();
    }

    public Player getCurrentOpponent() {
        return this.mainEngine.getCurrentOpponent();
    }
 
    public void showMarkInputInLogic(int[] cellCoordinates) {
        this.mainEngine.showMarkInputInLogic(cellCoordinates);
    }

    public void showMarkInputInGUI(int[] cellCoordinates, Mark currentPlayerMarkType) {
        this.mainFrame.showMarkInputInGUI(cellCoordinates, currentPlayerMarkType);
    }

    public void showTurnCommandForCurrentPlayer() {
        this.mainFrame.showTurnCommandForCurrentPlayer();
    }

    public void grayOutAllButtons() {
        this.mainFrame.grayOutAllButtons();
    }

    public RoundResult getRoundResult() {
        return this.mainEngine.getRoundResult();
    }

    public Player getWinner() {
        return this.mainEngine.getWinner();
    }

}
