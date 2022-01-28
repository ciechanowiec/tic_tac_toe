package tictactoe.gui.board;

import java.awt.GridLayout;

import javax.swing.JPanel;

import tictactoe.Definitions.Mark;
import tictactoe.gui.MainFrame;
import tictactoe.gui.board.buttons.Button;
import tictactoe.gui.board.buttons.ButtonsManager;

public class Board extends JPanel {

    private MainFrame mainFrame;
    private ButtonsManager buttonsManager;
    
    public Board(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.buttonsManager = new ButtonsManager(this);
        this.setLayout(new GridLayout(3, 3));
        addButtonsToBoard();
        disableAllButtons();
    }

    private void addButtonsToBoard() {
        for (Button button : this.buttonsManager.getButtons()) {
            this.add(button);
        }
    }

    public void disableAllButtons() {        
        this.buttonsManager.disableAllButtons();
    }

    public void prepareAllButtonsForRoundStart() {        
        this.buttonsManager.prepareAllButtonsForRoundStart();
    }

    public void showMarkInputInLogic(int[] cellCoordinates) {
        this.mainFrame.showMarkInputInLogic(cellCoordinates);       
    }

    public void showMarkInputInGUI(int[] cellCoordinates, Mark currentPlayerMarkType) {
        Button buttonToMark = this.buttonsManager.getButtonByCoordinates(cellCoordinates);        
        buttonToMark.setMark(currentPlayerMarkType);
        buttonToMark.grayOut();
    }
    
    public void grayOutAllButtons() {
        this.buttonsManager.grayOutAllButtons();
    }
    
}
