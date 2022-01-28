package tictactoe.logic.disposable;

import java.util.Random;

import tictactoe.Definitions.Mark;
import tictactoe.logic.MainEngine;
import tictactoe.logic.persistant.Player;

public class Round {

    public enum RoundResult {
        WIN, DRAW
    }

    private MainEngine mainEngine;
    private Player currentPlayer;
    private Player currentOpponent;
    private RoundResult roundResult;
    private Player winner;

    public Round(MainEngine mainEngine) {
        this.mainEngine = mainEngine;
        assignMarks();
        setFirstPlayerAndOpponent();
    }

    private void assignMarks() {
        /* Randomly assign marks to players for this round */     
        int randomNum = new Random().nextInt(20);        
        if (randomNum % 2 == 0) {
            this.mainEngine.getPlayer1().setMarkType(Mark.CROSS);
            this.mainEngine.getPlayer2().setMarkType(Mark.NOUGHT);
        } else {
            this.mainEngine.getPlayer1().setMarkType(Mark.NOUGHT);
            this.mainEngine.getPlayer2().setMarkType(Mark.CROSS);
        }        
    }

    private void setFirstPlayerAndOpponent() {
        if (this.mainEngine.getPlayer1().getMarkType() == Mark.CROSS
            && this.mainEngine.getPlayer2().getMarkType() == Mark.NOUGHT) {
            this.currentPlayer = this.mainEngine.getPlayer1();
            this.currentOpponent = this.mainEngine.getPlayer2();
        } else if (this.mainEngine.getPlayer1().getMarkType() == Mark.NOUGHT
            && this.mainEngine.getPlayer2().getMarkType() == Mark.CROSS) {
            this.currentPlayer = this.mainEngine.getPlayer2();
            this.currentOpponent = this.mainEngine.getPlayer1();
        }
    }    

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public Player getCurrentOpponent() {
        return this.currentOpponent;
    }

    public void invertPlayers() {
        if (this.currentPlayer == this.mainEngine.getPlayer1()
            && this.currentOpponent == this.mainEngine.getPlayer2()) {
            this.currentPlayer = this.mainEngine.getPlayer2();
            this.currentOpponent = this.mainEngine.getPlayer1();
        } else if (this.currentPlayer == this.mainEngine.getPlayer2()
            && this.currentOpponent == this.mainEngine.getPlayer1()) {
            this.currentPlayer = this.mainEngine.getPlayer1();
            this.currentOpponent = this.mainEngine.getPlayer2();
        }
    }   

    public void setRoundResult(RoundResult roundResult) {
        this.roundResult = roundResult;
    }

    public RoundResult getRoundResult() {
        return this.roundResult;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getWinner() {
        return this.winner;
    }
    
}
