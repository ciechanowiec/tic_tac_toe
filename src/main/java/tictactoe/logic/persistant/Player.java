package tictactoe.logic.persistant;

import tictactoe.Definitions.Mark;
import tictactoe.Definitions.PlayerType;

public class Player {

    private PlayerType playerType;
    private String playerName;
    private Mark markType;
    private int scores;

    public Player(PlayerType playerType, String playerName) {
        this.playerType = playerType;
        this.playerName = playerName;
        this.scores = 0;
    }

    public void setMarkType(Mark markType) {
        this.markType = markType;
    }

    public Mark getMarkType() {
        return this.markType;
    }

    public PlayerType getPlayerType() {
        return this.playerType;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void incrementScores() {
        this.scores++;
    }

    public int getScores() {
        return this.scores;
    }
    
}
