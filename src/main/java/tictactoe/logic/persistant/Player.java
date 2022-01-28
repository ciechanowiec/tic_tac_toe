package tictactoe.logic.persistant;

import tictactoe.Definitions.Mark;
import tictactoe.Definitions.PlayerType;

public class Player {

    private PlayerType playerType;
    private String playerName;

    private Mark markType;

    public Player(PlayerType playerType, String playerName) {
        this.playerType = playerType;
        this.playerName = playerName;
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
    
}
