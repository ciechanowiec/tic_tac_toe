package tictactoe.gui;

import tictactoe.Controller;

public class MainFrame {

    private Controller controller;

    public MainFrame(Controller controller) {
        this.controller = controller;
    }

    // public void showBoard() {
    //     char[][] boardData = this.controller.getBoardData();
    //     System.out.printf("""
    //     \n  %s  |  %s  |  %s  
    //     -----|-----|----
    //       %s  |  %s  |  %s  
    //     -----|-----|----
    //       %s  |  %s  |  %s  \n""",
    //     boardData[0][0], boardData[0][1], boardData[0][2],
    //     boardData[1][0], boardData[1][1], boardData[1][2],
    //     boardData[2][0], boardData[2][1], boardData[2][2]);
    // }    
    
}
