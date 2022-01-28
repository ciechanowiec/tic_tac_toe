package tictactoe.gui.board.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import tictactoe.Definitions.Mark;

public class Button extends JButton {    
    
    private final int[] cellCoordinates;
    private Mark mark;

    public Button(int[] cellCoordinates) {
        this.cellCoordinates = cellCoordinates;
        this.mark = Mark.EMPTY;
        this.setFont(new Font("Comic Sans", Font.BOLD, 50));        
        this.setFocusPainted(false);
    }
    
    public void setMark(Mark mark) {
        this.mark = mark;
        this.setText(String.valueOf(mark.getRepresentation()));        
    }

    public int[] getCellCoordinates() {
        return this.cellCoordinates;
    }
    
    public void grayOut() {
        ActionListener[] buttonActionListeners = this.getActionListeners();
        for (ActionListener actionListener : buttonActionListeners) {
            this.removeActionListener(actionListener);
        }
        this.setBackground(new Color(236, 225, 221));        
    }    
    
}
