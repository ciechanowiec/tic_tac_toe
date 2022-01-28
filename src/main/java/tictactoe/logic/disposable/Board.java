package tictactoe.logic.disposable;

import java.util.ArrayList;

import tictactoe.Definitions;
import tictactoe.Definitions.Mark;
import tictactoe.logic.MainEngine;
import tictactoe.logic.persistant.Player;

public class Board {

    public static final int BOARD_SIZE = 3;

    private MainEngine mainEngine;
    private BoardCombinations boardCombinations;

    private char[][] boardData;

    public Board(MainEngine mainEngine) {
        this.mainEngine = mainEngine;
        this.boardCombinations = new BoardCombinations(this);
        initBoardData();                
    }

    private void initBoardData() {
        this.boardData = new char[BOARD_SIZE][BOARD_SIZE];
        for (int rowIndex = 0; rowIndex < BOARD_SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < BOARD_SIZE; columnIndex++) {
                this.boardData[rowIndex][columnIndex] = Definitions.Mark.EMPTY.getRepresentation();
            }
        }
    }    

    public char[][] getBoardData() {
        return this.boardData;
    }

    public void showMarkInputInLogic(int rowIndex, int columnIndex, char markTypeRepresentation) {
        this.boardData[rowIndex][columnIndex] = markTypeRepresentation;
    }

    public boolean checkIfWon() {
        return this.boardCombinations.checkIfWon();
    }

    public boolean checkIfDraw() {
        return this.boardCombinations.checkIfDraw();
    }

    public ArrayList<Integer[]> getEmptyCells() {
        ArrayList<Integer[]> emptyCells = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < Board.BOARD_SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < Board.BOARD_SIZE; columnIndex++) {
                if (this.boardData[rowIndex][columnIndex] == Mark.EMPTY.getRepresentation()) {
                    emptyCells.add(new Integer[] {rowIndex, columnIndex});
                }
            }
        }
        return emptyCells;
    }

    public int[] getMoveFromBot() {
        return this.boardCombinations.getMoveFromBot();
    }
    

    public Player getCurrentPlayer() {
        return this.mainEngine.getCurrentPlayer();
    }

    public Player getCurrentOpponent() {
        return this.mainEngine.getCurrentOpponent();
    }
    
}
