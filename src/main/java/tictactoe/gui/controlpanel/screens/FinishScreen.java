package tictactoe.gui.controlpanel.screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import tictactoe.gui.controlpanel.ControlPanel;
import tictactoe.logic.disposable.Round.RoundResult;
import tictactoe.logic.persistant.Player;

public class FinishScreen extends JPanel {

    private ControlPanel controlPanel;

    public FinishScreen(ControlPanel controlPanel) {        
        this.controlPanel = controlPanel;

        this.setPreferredSize(new Dimension(400, 400));
        this.setLayout(new GridLayout(3, 1));

        initComponents();
    }

    private void initComponents() {
        JPanel scoresRow = initScoresRow();        
        JPanel roundResultRow = initRoundResultRow_Wrapper();
        JPanel questionRow = initQuestionRow();
        this.add(scoresRow);
        this.add(roundResultRow);
        this.add(questionRow);
    }
    
    private JPanel initScoresRow() {        
        JPanel scoresRow = new JPanel();        
        scoresRow.setLayout(new BoxLayout(scoresRow, BoxLayout.X_AXIS));
        scoresRow.setBorder(getTitledBorder(16f, "SCORES"));
            
        JLabel player1ScoresRepresentation = initPlayerScoresRepresentation(this.controlPanel.getPlayer1());
        JLabel player2ScoresRepresentation = initPlayerScoresRepresentation(this.controlPanel.getPlayer2());        

        scoresRow.add(Box.createHorizontalGlue());
        scoresRow.add(player1ScoresRepresentation);
        scoresRow.add(Box.createHorizontalGlue());
        scoresRow.add(player2ScoresRepresentation);
        scoresRow.add(Box.createHorizontalGlue());

        return scoresRow;
    }
    
    private JLabel initPlayerScoresRepresentation(Player player) {    
        JLabel playerScores = new JLabel(String.valueOf(player.getScores()));        
        playerScores.setHorizontalAlignment(JLabel.CENTER);        
        playerScores.setFont(playerScores.getFont().deriveFont(19f));
        playerScores.setBorder(getTitledBorder(12f, player.getPlayerName()));
        playerScores.setMinimumSize(new Dimension(140, 80));
        playerScores.setPreferredSize(new Dimension(140, 80));
        playerScores.setMaximumSize(new Dimension(140, 80));
        return playerScores;
    }

    private TitledBorder getTitledBorder(float titleSize, String title) {
        Border blackline = BorderFactory.createLineBorder(Color.black);        
        TitledBorder border = BorderFactory.createTitledBorder(blackline, title);
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitleFont(border.getTitleFont().deriveFont(titleSize));
        return border;
    }

    private JPanel initRoundResultRow_Wrapper() {
        JPanel roundResultRow = new JPanel();
        roundResultRow.setLayout(new BoxLayout(roundResultRow, BoxLayout.Y_AXIS));
        if (this.controlPanel.getRoundResult() == RoundResult.WIN) {
            return initRoundResultRow_Win(roundResultRow);
        } else if (this.controlPanel.getRoundResult() == RoundResult.DRAW) {
            return initRoundResultRow_Draw(roundResultRow);
        } else {
            throw new IllegalStateException("Exception occured: round result cannot be neither WIN nor DRAW.");
        }        
    }
    
    private JPanel initRoundResultRow_Win(JPanel roundResultRow) {
        Player winner = this.controlPanel.getWinner();
        String textForLabel = String.format("%s (%c)", winner.getPlayerName(),
                                                       winner.getMarkType().getRepresentation());
        JLabel playerName = new JLabel(textForLabel);        
        JLabel winText = new JLabel("won the round!");
    
        playerName.setFont(playerName.getFont().deriveFont(18f));
        winText.setFont(winText.getFont().deriveFont(16f));
    
        playerName.setAlignmentX(Component.CENTER_ALIGNMENT);
        winText.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        roundResultRow.add(Box.createVerticalGlue());
        roundResultRow.add(playerName);
        roundResultRow.add(winText);
        roundResultRow.add(Box.createVerticalGlue());
        
        return roundResultRow;
    }

    private JPanel initRoundResultRow_Draw(JPanel roundResultRow) {
        JLabel drawText = new JLabel("THAT'S A DRAW!");        
        drawText.setFont(drawText.getFont().deriveFont(18f));
        drawText.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        roundResultRow.add(Box.createVerticalGlue());        
        roundResultRow.add(drawText);
        roundResultRow.add(Box.createVerticalGlue());
        
        return roundResultRow;
    }

    private JPanel initQuestionRow() {
        JPanel questionRow = new JPanel();
        questionRow.setLayout(new BoxLayout(questionRow, BoxLayout.Y_AXIS));

        JLabel questionText = new JLabel("Play one more round?");
        JButton newRoundButton = new JButton("YES");
        newRoundButton.addActionListener(getNewRoundAction());

        questionText.setFont(questionText.getFont().deriveFont(15f));
        newRoundButton.setFont(newRoundButton.getFont().deriveFont(15f));
        newRoundButton.setMinimumSize(new Dimension(80, 35));
        newRoundButton.setPreferredSize(new Dimension(80, 35));
        newRoundButton.setMaximumSize(new Dimension(80, 35));
        newRoundButton.setFocusPainted(false);

        questionText.setAlignmentX(Component.CENTER_ALIGNMENT);
        newRoundButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        questionRow.add(Box.createVerticalGlue());
        questionRow.add(questionText);
        questionRow.add(Box.createRigidArea(new Dimension(0, 8)));
        questionRow.add(newRoundButton);
        questionRow.add(Box.createVerticalGlue());

        return questionRow;        
    }

    private ActionListener getNewRoundAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controlPanel.startRound();
            }
        };
    }
    
}
