package tictactoe.logic.disposable;

import java.util.ArrayList;
import java.util.Collections;

import tictactoe.Definitions.Mark;
import tictactoe.Definitions.PlayerType;
import tictactoe.logic.persistant.Player;

public class BoardCombinations {

    private Board board;
    private int[] emptyCellNearTwoInLine;

    public BoardCombinations(Board board) {
        this.board = board;
    }

    private boolean checkIfWon(Player checkedPlayer) {
        char checkedSign = checkedPlayer.getMarkType().getRepresentation();
        char[][] boardData = this.board.getBoardData();
        return
            /* Check rows */
          ((boardData[0][0] == checkedSign && boardData[0][0] == boardData[0][1] && boardData[0][1] == boardData[0][2])
        || (boardData[1][0] == checkedSign && boardData[1][0] == boardData[1][1] && boardData[1][1] == boardData[1][2])
        || (boardData[2][0] == checkedSign && boardData[2][0] == boardData[2][1] && boardData[2][1] == boardData[2][2])
            /* Check columns */
        || (boardData[0][0] == checkedSign && boardData[0][0] == boardData[1][0] && boardData[1][0] == boardData[2][0])
        || (boardData[0][1] == checkedSign && boardData[0][1] == boardData[1][1] && boardData[1][1] == boardData[2][1])
        || (boardData[0][2] == checkedSign && boardData[0][2] == boardData[1][2] && boardData[1][2] == boardData[2][2])
            /* Check diagonals */
        || (boardData[0][0] == checkedSign && boardData[0][0] == boardData[1][1] && boardData[1][1] == boardData[2][2])
        || (boardData[0][2] == checkedSign && boardData[0][2] == boardData[1][1] && boardData[1][1] == boardData[2][0]));
    }

    public boolean checkIfWon() {
        Player checkedPlayer = this.board.getCurrentPlayer();
        return checkIfWon(checkedPlayer);
    }

    public boolean checkIfDraw() {                
        Player currentPlayer = this.board.getCurrentPlayer();
        return !checkIfWon(currentPlayer) && this.board.getEmptyCells().size() == 0;
    }

    /* Bot move dispatcher */
    public int[] getMoveFromBot() {        
        PlayerType currentPlayer = this.board.getCurrentPlayer().getPlayerType();
        switch (currentPlayer) {
            case BOT_EASY:
                return getMoveFromBotEasy();
            case BOT_MEDIUM:
                return getMoveFromBotMedium();
            case BOT_HARD:
                return getMoveFromBotHard();
            default:
                throw new IllegalArgumentException("Exception occured: a bot type from" 
                                                    + "which you are trying to get a move doesn't exist");
        }

    }

    /* BOT EASY makes technically valid random moves */
    private int[] getMoveFromBotEasy() {
        ArrayList<Integer[]> emptyCells = this.board.getEmptyCells();
        Collections.shuffle(emptyCells);
        Integer[] chosenCell = emptyCells.get(0);        
        return new int[] {chosenCell[0], chosenCell[1]};
    }
    
    /* BOT MEDIUM makes moves using the following logic:
       1. If BOT has already two in a row and can win with one further move, BOT does so.
       2. If a BOT's opponent can win with one move, BOT plays the move necessary to block this.
       3. Otherwise, BOT makes a random move as BOT EASY. */
    private int[] getMoveFromBotMedium() {        
        Player currentPlayer = this.board.getCurrentPlayer();
        Player currentOpponent = this.board.getCurrentOpponent();
        if (checkIfTwoInRowPlusOneEmpty_Wrapper(currentPlayer)) {
            return this.emptyCellNearTwoInLine;
        } else if (checkIfTwoInRowPlusOneEmpty_Wrapper(currentOpponent)) {
            return this.emptyCellNearTwoInLine;
        } else {
            return getMoveFromBotEasy();
        }
    }

    private boolean checkIfTwoInRowPlusOneEmpty_Wrapper(Player checkedPlayer) {
        char[][] boardData = this.board.getBoardData();
        return (checkIfTwoInLinePlusOneEmpty_Rows(checkedPlayer, boardData)
                || checkIfTwoInLinePlusOneEmpty_Columns(checkedPlayer, boardData)
                || checkIfTwoInLinePlusOneEmpty_Diagonal_LRight(checkedPlayer, boardData)
                || checkIfTwoInLinePlusOneEmpty_Diagonal_RLeft(checkedPlayer, boardData));
    }

    private boolean checkIfTwoInLinePlusOneEmpty_Rows(Player checkedPlayer, char[][] boardData) {        
        for (int rowIndex = 0; rowIndex < Board.BOARD_SIZE; rowIndex++) {
            StringBuilder marksInLine = new StringBuilder();
            int[] currentEmptyCellInLine = null;
            for (int columnIndex = 0; columnIndex < Board.BOARD_SIZE; columnIndex++) {
                String markInLine = String.valueOf(boardData[rowIndex][columnIndex]);                
                marksInLine.append(markInLine);
                if (boardData[rowIndex][columnIndex] == Mark.EMPTY.getRepresentation()) {
                    currentEmptyCellInLine = new int[] {rowIndex, columnIndex};
                }
            }
            if (checkIfTwoInLinePlusOneEmpty_CollectedLine(marksInLine.toString(), checkedPlayer)) {
                this.emptyCellNearTwoInLine = currentEmptyCellInLine;
                return true;
            }
        }
        return false;
    }

    private boolean checkIfTwoInLinePlusOneEmpty_Columns(Player checkedPlayer, char[][] boardData) {        
        for (int columnIndex = 0; columnIndex < Board.BOARD_SIZE; columnIndex++) {
            StringBuilder marksInLine = new StringBuilder();
            int[] currentEmptyCellInLine = null;
            for (int rowIndex = 0; rowIndex < Board.BOARD_SIZE; rowIndex++) {                
                String markInLine = String.valueOf(boardData[rowIndex][columnIndex]);                
                marksInLine.append(markInLine);
                if (boardData[rowIndex][columnIndex] == Mark.EMPTY.getRepresentation()) {
                    currentEmptyCellInLine = new int[] {rowIndex, columnIndex};
                }
            }
            if (checkIfTwoInLinePlusOneEmpty_CollectedLine(marksInLine.toString(), checkedPlayer)) {
                this.emptyCellNearTwoInLine = currentEmptyCellInLine;
                return true;
            }
        }
        return false;
    }

    private boolean checkIfTwoInLinePlusOneEmpty_Diagonal_LRight(Player checkedPlayer, char[][] boardData) {
        StringBuilder marksInLine = new StringBuilder();
        int[] currentEmptyCellInLine = null;
        for (int rowAndColumnIndex = 0; rowAndColumnIndex < Board.BOARD_SIZE; rowAndColumnIndex++) {
            String markInLine = String.valueOf(boardData[rowAndColumnIndex][rowAndColumnIndex]);                
            marksInLine.append(markInLine);
            if (boardData[rowAndColumnIndex][rowAndColumnIndex] == Mark.EMPTY.getRepresentation()) {
                currentEmptyCellInLine = new int[] {rowAndColumnIndex, rowAndColumnIndex};
            }
        }
        if (checkIfTwoInLinePlusOneEmpty_CollectedLine(marksInLine.toString(), checkedPlayer)) {
            this.emptyCellNearTwoInLine = currentEmptyCellInLine;
            return true;
        }
        return false;
    }

    private boolean checkIfTwoInLinePlusOneEmpty_Diagonal_RLeft(Player checkedPlayer, char[][] boardData) {
        StringBuilder marksInLine = new StringBuilder();
        int[] currentEmptyCellInLine = null;
        for (int rowIndex = 0, columnIndex = Board.BOARD_SIZE - 1; rowIndex < Board.BOARD_SIZE && columnIndex >= 0;
             rowIndex++, columnIndex--) {
                String markInLine = String.valueOf(boardData[rowIndex][columnIndex]);                
                marksInLine.append(markInLine);
                if (boardData[rowIndex][columnIndex] == Mark.EMPTY.getRepresentation()) {
                    currentEmptyCellInLine = new int[] {rowIndex, columnIndex};
                }
        }
        if (checkIfTwoInLinePlusOneEmpty_CollectedLine(marksInLine.toString(), checkedPlayer)) {
            this.emptyCellNearTwoInLine = currentEmptyCellInLine;
            return true;
        }
        return false;
    }

    private boolean checkIfTwoInLinePlusOneEmpty_CollectedLine(String marksInLine, Player checkedPlayer) {
        int numOfEmptyMarks = marksInLine.replaceAll(String
                                            .format("[%s%s]*", Mark.CROSS.getRepresentation(),
                                                               Mark.NOUGHT.getRepresentation()),
                                                    "")
                                            .length();
        int numOfCheckedPlayerMarks = marksInLine.replaceAll(String
                                                   .format("[^%s]", checkedPlayer.getMarkType().getRepresentation()),
                                                           "")
                                                   .length();
        return (numOfEmptyMarks == 1 && numOfCheckedPlayerMarks == 2);
    }

    /* BOT HARD makes moves using a minimax algorithm:
       1. BOT HARD never loses.
       2. BOT HARD always wins if only it's opponent's moves make it possible.
       3. There are two possibly outcomes when playing with BOT HARD: BOT HARD win or draw. */    
    
    private int[] getMoveFromBotHard() {
        Player currentPlayer = this.board.getCurrentPlayer();
        Player currentOpponent = this.board.getCurrentOpponent();

        char[][] boardData = this.board.getBoardData();
        int bestScore = Integer.MIN_VALUE;
        int bestRowIndex = -1;
        int bestColumnIndex = -1;

        for (int rowIndex = 0; rowIndex < Board.BOARD_SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < Board.BOARD_SIZE; columnIndex++) {
                if (boardData[rowIndex][columnIndex] == Mark.EMPTY.getRepresentation()) {
                    char previousCellValue = boardData[rowIndex][columnIndex];
                    boardData[rowIndex][columnIndex] = currentPlayer.getMarkType().getRepresentation();
                    int currentMoveScore = minimax(currentPlayer, currentOpponent, false);
                    boardData[rowIndex][columnIndex] = previousCellValue;
                    if (currentMoveScore > bestScore) {
                        bestRowIndex = rowIndex;
                        bestColumnIndex = columnIndex;
                        bestScore = currentMoveScore;
                    }
                }
            }
        }
        return new int[] {bestRowIndex, bestColumnIndex};
    }

    
    private int minimax(Player currentPlayer, Player currentOpponent, boolean isMaximizing) {
        char[][] boardData = this.board.getBoardData();        
        
        int finalBoardStateScore = evaluateBoard(currentPlayer, currentOpponent);
        
        if (finalBoardStateScore == 10) {
            return finalBoardStateScore;
        } else if (finalBoardStateScore == -10) {
            return finalBoardStateScore;
        } else if (this.board.getEmptyCells().size() == 0) {
            return 0;
        }
        
        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int rowIndex = 0; rowIndex < Board.BOARD_SIZE; rowIndex++) {
                for (int columnIndex = 0; columnIndex < Board.BOARD_SIZE; columnIndex++) {
                    if (boardData[rowIndex][columnIndex] == Mark.EMPTY.getRepresentation()) {
                        char previousCellValue = boardData[rowIndex][columnIndex];
                        boardData[rowIndex][columnIndex] = currentPlayer.getMarkType().getRepresentation();
                        int currentScore = minimax(currentPlayer, currentOpponent, !isMaximizing);
                        bestScore = Math.max(bestScore, currentScore);
                        boardData[rowIndex][columnIndex] = previousCellValue;
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int rowIndex = 0; rowIndex < Board.BOARD_SIZE; rowIndex++) {
                for (int columnIndex = 0; columnIndex < Board.BOARD_SIZE; columnIndex++) {
                    if (boardData[rowIndex][columnIndex] == Mark.EMPTY.getRepresentation()) {
                        char previousCellValue = boardData[rowIndex][columnIndex];
                        boardData[rowIndex][columnIndex] = currentOpponent.getMarkType().getRepresentation();
                        int currentScore = minimax(currentPlayer, currentOpponent, !isMaximizing);
                        bestScore = Math.min(bestScore, currentScore);
                        boardData[rowIndex][columnIndex] = previousCellValue;
                    }
                }
            }
            return bestScore;
        }
    }
    
    private int evaluateBoard(Player currentPlayer, Player currentOpponent) {
        if (checkIfWon(currentPlayer)) {
            return 10;
        } else if (checkIfWon(currentOpponent)) {
            return -10;
        } else {
            return 0;
        }
    }
    
}
