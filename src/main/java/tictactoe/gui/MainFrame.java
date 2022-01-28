package tictactoe.gui;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import tictactoe.Controller;
import tictactoe.Definitions.Mark;
import tictactoe.Definitions.PlayerType;
import tictactoe.gui.board.Board;
import tictactoe.gui.controlpanel.ControlPanel;
import tictactoe.logic.disposable.Round.RoundResult;
import tictactoe.logic.persistant.Player;

public class MainFrame extends JFrame {

    private Controller controller;
    private Board board;
    private ControlPanel controlPanel;

    public MainFrame(Controller controller) {    
        this.controller = controller;
        setMainFrameParams();
        initComponents();
        this.setVisible(true);
    }

    private void setMainFrameParams() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Tic-Tac-Toe with Undefeatable Bot");
        this.setResizable(false);   
        this.setLayout(new GridLayout(1, 2));
        this.setSize(800, 400);
        this.setLocationRelativeTo(null);
        setMainFrameIcon();
    }

    private void setMainFrameIcon() {
        String iconURI = "src/main/resources/icon.png";
        ImageIcon icon = new ImageIcon(iconURI);
        this.setIconImage(icon.getImage());
    }

    private void initComponents() {
        this.board = new Board(this);
        this.controlPanel = new ControlPanel(this);
        this.add(this.board);
        this.add(this.controlPanel);
    }

    public void processStartGameAction(PlayerType player1Type, String player1Name,
                                    PlayerType player2Type, String player2Name) {        
        this.controller.processStartGameAction(player1Type, player1Name, player2Type, player2Name); 
    }    

    public void prepareAllButtonsForRoundStart() {
        this.board.prepareAllButtonsForRoundStart();
    }

    public void startRound() {     
        this.controller.startRound();
    }

    public void showPendingScreen() {
        this.controlPanel.showPendingScreen();
    }

    public void showFinishScreen() {
        this.controlPanel.showFinishScreen();
    }

    public void grayOutAllButtons() {
        this.board.grayOutAllButtons();
    }

    public int getRoundsPlayed() {
        return this.controller.getRoundsPlayed();   
    }

    public Player getPlayer1() {
        return this.controller.getPlayer1();
    }

    public Player getPlayer2() {
        return this.controller.getPlayer2();
    }

    public Player getCurrentPlayer() {
        return this.controller.getCurrentPlayer();
    }

    public Player getCurrentOpponent() {
        return this.controller.getCurrentOpponent();
    }

    public void showMarkInputInLogic(int[] cellCoordinates) {
        this.controller.showMarkInputInLogic(cellCoordinates);        
    }

    public void showMarkInputInGUI(int[] cellCoordinates, Mark currentPlayerMarkType) {
        this.board.showMarkInputInGUI(cellCoordinates, currentPlayerMarkType);
    }

    public void showTurnCommandForCurrentPlayer() {
        this.controlPanel.showTurnCommandForCurrentPlayer();
    }

    public RoundResult getRoundResult() {
        return this.controller.getRoundResult();
    }

    public Player getWinner() {
        return this.controller.getWinner();
    }
    
}
