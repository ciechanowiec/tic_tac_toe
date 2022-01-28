package tictactoe.gui.controlpanel.screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import tictactoe.gui.controlpanel.ControlPanel;
import tictactoe.logic.persistant.Player;

public class PendingScreen extends JPanel {

    private ControlPanel controlPanel;
    private JLabel player1TurnCommand;
    private JLabel player2TurnCommand;
    
    public PendingScreen(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;

        this.setPreferredSize(new Dimension(400, 400));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        initTurnCommands();
        initComponents();
        showTurnCommandForCurrentPlayer();
    }

    private void initTurnCommands() {
        this.player1TurnCommand = new JLabel("your turn!");
        this.player2TurnCommand = new JLabel("your turn!");

        this.player1TurnCommand.setFont(this.player1TurnCommand.getFont().deriveFont(15f));
        this.player2TurnCommand.setFont(this.player2TurnCommand.getFont().deriveFont(15f));

        this.player1TurnCommand.setForeground(new Color(34, 177, 76));
        this.player2TurnCommand.setForeground(new Color(34, 177, 76));        
    }

    private void initComponents() {
        JLabel roundRow = initRoundRow();        
        JPanel playersRow = initPlayersRow();
        
        roundRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        playersRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(roundRow);
        this.add(Box.createRigidArea(new Dimension(0, 35)));
        this.add(playersRow);
    }

    private JLabel initRoundRow() {        
        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder border = BorderFactory.createTitledBorder(blackline, "ROUND");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitleFont(border.getTitleFont().deriveFont(16f));

        JLabel roundRow = new JLabel(String.valueOf(this.controlPanel.getRoundsPlayed()));
        roundRow.setFont(roundRow.getFont().deriveFont(19f));
        roundRow.setBorder(border);
        roundRow.setHorizontalAlignment(JLabel.CENTER);
        roundRow.setMinimumSize(new Dimension(100, 70));
        roundRow.setPreferredSize(new Dimension(100, 70));
        roundRow.setMaximumSize(new Dimension(100, 70));

        return roundRow;
    }

    private JPanel initPlayersRow() {
        JPanel playersRow = new JPanel();
        playersRow.setLayout(new BoxLayout(playersRow, BoxLayout.X_AXIS));

        JPanel player1Info = initPlayerInfo(this.controlPanel.getPlayer1(), this.player1TurnCommand);
        JPanel player2Info = initPlayerInfo(this.controlPanel.getPlayer2(), this.player2TurnCommand);
        
        playersRow.add(Box.createHorizontalGlue());
        playersRow.add(player1Info);             
        playersRow.add(Box.createHorizontalGlue());
        playersRow.add(player2Info);     
        playersRow.add(Box.createHorizontalGlue());
        return playersRow;
    }

    private JPanel initPlayerInfo(Player player, JLabel turnCommand) {
        JPanel playerInfo = new JPanel();
        playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.Y_AXIS));
        
        JLabel playerName = new JLabel(player.getPlayerName());
        JLabel textAboutRoundFirstLine = new JLabel("plays in this");        
        JLabel textAboutRoundSecondLine = new JLabel("round for:");
        JLabel markType = new JLabel(String.valueOf(player.getMarkType().getRepresentation()));
        
        playerName.setFont(playerName.getFont().deriveFont(16f));
        textAboutRoundFirstLine.setFont(new Font("Default", Font.PLAIN, 14));
        textAboutRoundSecondLine.setFont(new Font("Default", Font.PLAIN, 14));
        markType.setFont(markType.getFont().deriveFont(50f));

        playerName.setAlignmentX(Component.CENTER_ALIGNMENT);
        textAboutRoundFirstLine.setAlignmentX(Component.CENTER_ALIGNMENT);
        textAboutRoundSecondLine.setAlignmentX(Component.CENTER_ALIGNMENT);
        markType.setAlignmentX(Component.CENTER_ALIGNMENT);
        turnCommand.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        playerInfo.add(playerName);
        playerInfo.add(textAboutRoundFirstLine);
        playerInfo.add(textAboutRoundSecondLine);
        playerInfo.add(markType);        
        playerInfo.add(turnCommand);        
        
        return playerInfo;        
    }

    public void showTurnCommandForCurrentPlayer() {
        if (this.controlPanel.getPlayer1() == this.controlPanel.getCurrentPlayer()
            && this.controlPanel.getPlayer2() == this.controlPanel.getCurrentOpponent()) {
            this.player1TurnCommand.setText("your turn!");;
            this.player2TurnCommand.setText(" ");;            
        } else if (this.controlPanel.getPlayer2() == this.controlPanel.getCurrentPlayer()
                   && this.controlPanel.getPlayer1() == this.controlPanel.getCurrentOpponent()) {
            this.player1TurnCommand.setText(" ");;
            this.player2TurnCommand.setText("your turn!");;
        }
    }
        
}
