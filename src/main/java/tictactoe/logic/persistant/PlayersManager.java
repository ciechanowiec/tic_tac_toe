package tictactoe.logic.persistant;

import tictactoe.Definitions.PlayerType;
import tictactoe.logic.MainEngine;

public class PlayersManager {

    private Player player1;
    private Player player2;
    private MainEngine mainEngine;

    public PlayersManager(MainEngine mainEngine) {
        this.mainEngine = mainEngine;
    }

    public void setupPlayers(PlayerType player1Type, String player1Name,
                             PlayerType player2Type, String player2Name) {
        this.player1 = new Player(player1Type, player1Name);
        this.player2 = new Player(player2Type, player2Name);
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public Player getPlayer2() {
        return this.player2;
    }
    
}
