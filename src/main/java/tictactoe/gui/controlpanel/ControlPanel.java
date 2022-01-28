package tictactoe.gui.controlpanel;

import java.awt.GridLayout;

import javax.swing.JPanel;

import tictactoe.Definitions.PlayerType;
import tictactoe.gui.MainFrame;
import tictactoe.gui.controlpanel.screens.FinishScreen;
import tictactoe.gui.controlpanel.screens.PendingScreen;
import tictactoe.gui.controlpanel.screens.SetupScreen;
import tictactoe.logic.disposable.Round.RoundResult;
import tictactoe.logic.persistant.Player;

public class ControlPanel extends JPanel {    

    private MainFrame mainFrame;
    private SetupScreen setupScreen;
    private PendingScreen pendingScreen;
    private FinishScreen finishScreen;

    public ControlPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(new GridLayout(1, 1));
        showSetupScreen();
    }
    
    private void showSetupScreen() {        
        clearControlPanel();
        this.setupScreen = new SetupScreen(this);
        this.add(this.setupScreen);        
    }    
    
    public void showPendingScreen() {        
        clearControlPanel();        
        this.pendingScreen = new PendingScreen(this);
        this.add(this.pendingScreen);        
    }
    
    public void showFinishScreen() {        
        clearControlPanel();                        
        this.finishScreen = new FinishScreen(this);
        this.add(this.finishScreen);        
    }
    
    private void clearControlPanel() {
        if (this.setupScreen != null) {
            this.setupScreen.setVisible(false);
            this.setupScreen = null;
        }
        if (this.pendingScreen != null) {
            this.pendingScreen.setVisible(false);
            this.pendingScreen = null;
        }
        if (this.finishScreen != null) {
            this.finishScreen.setVisible(false);
            this.finishScreen = null;
        }
        this.removeAll();
    }

    public void processStartGameAction(PlayerType player1Type, String player1Name,
                                       PlayerType player2Type, String player2Name) {
        this.mainFrame.processStartGameAction(player1Type, player1Name, player2Type, player2Name);        
    }

    public void startRound() {     
        this.mainFrame.startRound();
    }

    public void showTurnCommandForCurrentPlayer() {
        this.pendingScreen.showTurnCommandForCurrentPlayer();
    }

    public int getRoundsPlayed() {
        return this.mainFrame.getRoundsPlayed();   
    }

    public Player getPlayer1() {
        return this.mainFrame.getPlayer1();
    }

    public Player getPlayer2() {
        return this.mainFrame.getPlayer2();
    }

    public Player getCurrentPlayer() {
        return this.mainFrame.getCurrentPlayer();
    }

    public Player getCurrentOpponent() {
        return this.mainFrame.getCurrentOpponent();
    }
    
    public RoundResult getRoundResult() {
        return this.mainFrame.getRoundResult();
    }

    public Player getWinner() {
        return this.mainFrame.getWinner();
    }
    
}
