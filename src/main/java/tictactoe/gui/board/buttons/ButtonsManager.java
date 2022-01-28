package tictactoe.gui.board.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Arrays;

import tictactoe.Definitions.Mark;
import tictactoe.gui.board.Board;

public class ButtonsManager {

    private ArrayList<Button> buttons;
    private Board board;

    public ButtonsManager(Board board) {
        this.board = board;
        initButtons();
    }

    private void initButtons() {
        this.buttons = new ArrayList<>();
        this.buttons.add(new Button(new int[] {0, 0}));
        this.buttons.add(new Button(new int[] {0, 1}));
        this.buttons.add(new Button(new int[] {0, 2}));
        this.buttons.add(new Button(new int[] {1, 0}));
        this.buttons.add(new Button(new int[] {1, 1}));
        this.buttons.add(new Button(new int[] {1, 2}));
        this.buttons.add(new Button(new int[] {2, 0}));
        this.buttons.add(new Button(new int[] {2, 1}));
        this.buttons.add(new Button(new int[] {2, 2}));
    }
    
    public void disableAllButtons() {        
        this.buttons.forEach(button -> button.setEnabled(false));
    }

    public ArrayList<Button> getButtons() {
        return this.buttons;
    }
    
    public void prepareAllButtonsForRoundStart() {                
        this.buttons.forEach(button -> button.addActionListener(getPressButtonAction()));
        this.buttons.forEach(button -> button.setEnabled(true));
        this.buttons.forEach(button -> button.setMark(Mark.EMPTY));
        this.buttons.forEach(button -> button.setBackground(null));
    }

    private ActionListener getPressButtonAction() {
        return new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Button pressedButton = (Button) actionEvent.getSource();
                board.showMarkInputInLogic(pressedButton.getCellCoordinates());
            }
        };
    }

    public Button getButtonByCoordinates(int[] cellCoordinates) {
        for (Button button : this.buttons) {
            if (Arrays.equals(button.getCellCoordinates(), cellCoordinates)) {
                return button;
            }
        }
        throw new IllegalArgumentException(String.format("A button with '%s' coordinates doesn't exist.",
                                                          Arrays.toString(cellCoordinates)));
    }

    public void grayOutAllButtons() {        
        this.buttons.forEach(button -> button.grayOut());
    }
    
}
