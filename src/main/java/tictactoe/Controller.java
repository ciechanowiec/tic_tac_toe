package tictactoe;

import java.util.Scanner;
import java.util.logging.Logger;

import tictactoe.Definitions.Mark;
import tictactoe.Definitions.PlayerType;
import tictactoe.gui.MainFrame;
import tictactoe.logic.MainEngine;
import tictactoe.logic.persistant.Player;

public class Controller {
    
    private MainEngine mainEngine;
    private MainFrame mainFrame;

    public Controller() {
        this.mainEngine = new MainEngine(this);
        this.mainFrame = new MainFrame(this);    
    }
        
    public void showMarkInputInLogic(int rowIndex, int columnIndex, char markTypeRepresentation) {
        this.mainEngine.showMarkInputInLogic(rowIndex, columnIndex, markTypeRepresentation);
    }
    
    public char[][] getBoardData() {
        return this.mainEngine.getBoardData();
    }
    
    // public void showBoard() {
    //     this.mainFrame.showBoard();
    // }

    // private void testMethod() {
    //     /* TESTING... */
    //     this.mainEngine.setupPlayers(PlayerType.HUMAN, "HUMAN", PlayerType.BOT_EASY, "BOT");        
    //     // this.mainEngine.setupPlayers(PlayerType.BOT_EASY, "BOT 1", PlayerType.BOT_HARD, "BOT 2");        

    //     Scanner scanner = new Scanner(System.in);
    //     while (true) {
    //         System.out.println("Round? (y/n)");
    //         String command = scanner.nextLine().toLowerCase();
    //         if (command.equals("y")) {
    //             testRound();
    //         } else {
    //             break;
    //         }
    //     }                        
    // }   
    
    // private void testRound() {
    //     this.mainEngine.startRound();

    //     while (!this.mainEngine.checkIfWon() && !this.mainEngine.checkIfDraw()) {            
    //         showBoard();
    //         Player currentPlayer = this.mainEngine.getCurrentPlayer();
    //         String startMessage = String.format("%s (%s - %s) move:", currentPlayer.getPlayerName(), currentPlayer.getMarkType(), currentPlayer.getPlayerType());
    //         System.out.println(startMessage);

    //         char currentPlayerMarkTypeRepresentation = currentPlayer.getMarkType().getRepresentation();
    //         int[] nextMove;
    //         if (currentPlayer.getPlayerType() == PlayerType.HUMAN) {
    //             Scanner scanner = new Scanner(System.in);
    //             int rowIndex = scanner.nextInt();
    //             int columnIndex = scanner.nextInt();
    //             nextMove = new int[] {rowIndex, columnIndex};
    //         } else {
    //             nextMove = this.mainEngine.getMoveFromBot();
    //         }
    //         showMarkInputInLogic(nextMove[0], nextMove[1], currentPlayerMarkTypeRepresentation);
    //         if (!this.mainEngine.checkIfWon() && !this.mainEngine.checkIfDraw()) {
    //             this.mainEngine.getRound().invertPlayers();
    //         }
    //     }

    //     if (this.mainEngine.checkIfWon()) {
    //         Player currentPlayer = this.mainEngine.getCurrentPlayer();
    //         String winMessage = String.format("\n%s (%s - %s) WON!\n", currentPlayer.getPlayerName(), currentPlayer.getMarkType(), currentPlayer.getPlayerType());
    //         System.out.println(winMessage);
    //     } else if (this.mainEngine.checkIfDraw()) {
    //         System.out.println("\nDRAW");
    //     }                

    // }
    
}
