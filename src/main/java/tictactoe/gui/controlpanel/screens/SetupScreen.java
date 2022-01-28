package tictactoe.gui.controlpanel.screens;

import java.util.Random;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tictactoe.Definitions.PlayerType;
import tictactoe.gui.controlpanel.ControlPanel;

public class SetupScreen extends JPanel {

    private ControlPanel controlPanel;    
    private JComboBox player1TypeChooser;
    private JComboBox player2TypeChooser;
    private JTextField player1NameField;
    private JTextField player2NameField;
    private JButton startButton;
    
    public SetupScreen(ControlPanel controlPanel) {    
        this.controlPanel = controlPanel;

        this.setPreferredSize(new Dimension(400, 400));
        this.setLayout(new GridLayout(3, 1));

        initPlayersTypes();
        initPlayersNames();
        initHeaderRow();        
        initPlayerChooserRow();
        initStartButtonRow();
    }

    private void initPlayersTypes() {
        this.player1TypeChooser = new JComboBox(PlayerType.getPlayersTypesRepresentations().toArray());
        this.player1TypeChooser.addActionListener(getSetPlayerTypeAction());
        this.player2TypeChooser = new JComboBox(PlayerType.getPlayersTypesRepresentations().toArray());
        this.player2TypeChooser.addActionListener(getSetPlayerTypeAction());
    }

    private void initPlayersNames() {
        this.player1NameField = new JTextField();        
        this.player2NameField = new JTextField();
    }

    private void initHeaderRow() {
        JLabel headerRow = new JLabel("SET UP PLAYERS:");
        headerRow.setFont(headerRow.getFont().deriveFont(22f));
        headerRow.setHorizontalAlignment(JLabel.CENTER);
        this.add(headerRow);
    }
    
    private void initPlayerChooserRow() {
        JPanel playerChooserRow = new JPanel();
        playerChooserRow.setLayout(new GridLayout(3, 1));        

        JPanel subRow1WithDescription = initSubRow1WithDescription();
        JPanel subRow2WIthType = initSubRow2WithTypeChooser();
        JPanel subRow3WithName = initSubRow3WithNameField();

        playerChooserRow.add(subRow1WithDescription);
        playerChooserRow.add(subRow2WIthType);
        playerChooserRow.add(subRow3WithName);

        this.add(playerChooserRow);
    }

    private JPanel initSubRow1WithDescription() {
        JPanel subRow1WithDescription = new JPanel();
        subRow1WithDescription.setLayout(null);

        JLabel player1Label = new JLabel("PLAYER  1");
        JLabel vsLabel = new JLabel("vs");
        JLabel player2Label = new JLabel("PLAYER  2");

        player1Label.setFont(player1Label.getFont().deriveFont(12f));
        player2Label.setFont(player2Label.getFont().deriveFont(12f));
        vsLabel.setFont(vsLabel.getFont().deriveFont(12f));

        player1Label.setBounds(80, 8, 65, 25);
        vsLabel.setBounds(190, 8, 20, 25);
        player2Label.setBounds(285, 8, 65, 25);

        subRow1WithDescription.add(player1Label);
        subRow1WithDescription.add(vsLabel);
        subRow1WithDescription.add(player2Label);

        return subRow1WithDescription;
    }

    private JPanel initSubRow2WithTypeChooser() {
        JPanel subRow2WithTypeChooser = new JPanel();
        subRow2WithTypeChooser.setLayout(null);

        JLabel player1TypeLabel = new JLabel("type:");
        JLabel player2TypeLabel = new JLabel("type:");

        player1TypeLabel.setBounds(22, 7, 60, 25);
        this.player1TypeChooser.setBounds(55, 7, 113, 25);
        player2TypeLabel.setBounds(227, 7, 60, 25);
        this.player2TypeChooser.setBounds(260, 7, 113, 25);

        subRow2WithTypeChooser.add(player1TypeLabel);
        subRow2WithTypeChooser.add(this.player1TypeChooser);
        subRow2WithTypeChooser.add(player2TypeLabel);
        subRow2WithTypeChooser.add(this.player2TypeChooser);
        
        return subRow2WithTypeChooser;        
    }

    private JPanel initSubRow3WithNameField() {
        JPanel subRow3WithNameField = new JPanel();
        subRow3WithNameField.setLayout(null);

        JLabel player1NameLabel = new JLabel("name:");
        JLabel player2NameLabel = new JLabel("name:");

        subRow3WithNameField.add(player1NameLabel);
        subRow3WithNameField.add(this.player1NameField);
        subRow3WithNameField.add(player2NameLabel);
        subRow3WithNameField.add(this.player2NameField);

        player1NameLabel.setBounds(14, 0, 60, 25);
        this.player1NameField.setBounds(55, 0, 113, 25);
        player2NameLabel.setBounds(219, 0, 60, 25);
        this.player2NameField.setBounds(260, 0, 113, 25);

        return subRow3WithNameField;                
    }
    
    private void initStartButtonRow() {
        /* Wrap Start Button into JPanel to prevent it from 
           filling out the whole row by Grid Layout of Setup Screen */
        JPanel startButtonRow = new JPanel();
        startButtonRow.setLayout(null);

        this.startButton = new JButton("START");        
        this.startButton.setFont(new Font("Default", Font.BOLD, 14));        
        this.startButton.setBounds(145, 35, 110, 35);
        this.startButton.setFocusPainted(false);
        this.startButton.addActionListener(getStartAction());
        
        startButtonRow.add(this.startButton);
        this.add(startButtonRow);
    }
    
    private String generateBotName(PlayerType playerType) {
        /* Name format: BOT-[4-digit number]-[difficulty level]
           E.g.: "BOT-4582-HARD" */
        Random randomNumGenerator = new Random();
        StringBuilder botName = null;        
        int randomNum = 0;
        
        do { // Exclude duplicate numbers in bot names
            randomNum = randomNumGenerator.nextInt(9000) + 1000; // Number in range 1000-9999
            botName = new StringBuilder(String.format("BOT-%d-", randomNum));
        } while (this.player1NameField.getText().contains(String.valueOf(randomNum))
                || this.player2NameField.getText().contains(String.valueOf(randomNum)));

        switch (playerType) {
            case BOT_EASY:
                botName.append("EASY");
                break;
            case BOT_MEDIUM:
                botName.append("MEDIUM");
                break;
            case BOT_HARD:
                botName.append("HARD");
                break;
            case HUMAN:
                throw new IllegalArgumentException("Generating name for a human player is not allowed.");
            default:
                break;
        }
        return botName.toString();
    }    

    /* ACTION LISTENERS */

    private ActionListener getSetPlayerTypeAction() {                
        /* Deactivates Player Name Field and puts there automatically 
           generated name if BOT as a player has been selected.
           Clears and activates Player Name Field if HUMAN as
           a player has been selected. */
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {                
                JComboBox comboBoxWithSelection = (JComboBox) actionEvent.getSource();
                PlayerType selectedPlayerType = getPlayerTypeFromActionEvent(actionEvent);
                if (selectedPlayerType == PlayerType.BOT_EASY
                    || selectedPlayerType == PlayerType.BOT_MEDIUM
                    || selectedPlayerType == PlayerType.BOT_HARD) {
                        if (comboBoxWithSelection == player1TypeChooser) {
                            player1NameField.setEditable(false);
                            player1NameField.setText(generateBotName(selectedPlayerType));
                        } else if (comboBoxWithSelection == player2TypeChooser) {
                            player2NameField.setEditable(false);
                            player2NameField.setText(generateBotName(selectedPlayerType));
                        }
                } else if (selectedPlayerType == PlayerType.HUMAN) {
                        if (comboBoxWithSelection == player1TypeChooser) {
                            player1NameField.setEditable(true);
                            player1NameField.setText("");
                        } else if (comboBoxWithSelection == player2TypeChooser) {
                            player2NameField.setEditable(true);
                            player2NameField.setText("");
                        }
                }
            }

            private PlayerType getPlayerTypeFromActionEvent(ActionEvent actionEvent) {
                JComboBox comboBoxWithPlayerTypeSelection = (JComboBox) actionEvent.getSource();
                String playerTypeRepresentation = (String) comboBoxWithPlayerTypeSelection.getSelectedItem();                
                return PlayerType.getPlayerTypeByRepresentation(playerTypeRepresentation);
            }      
              
        };
    }

    private ActionListener getStartAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PlayerType player1TypeToSet = PlayerType.getPlayerTypeByRepresentation
                                                         ((String) player1TypeChooser.getSelectedItem());
                PlayerType player2TypeToSet = PlayerType.getPlayerTypeByRepresentation
                                                         ((String) player2TypeChooser.getSelectedItem());
                String player1NameToSet = getPlayer1NameFormatted();                
                String player2NameToSet = getPlayer2NameFormatted();
                controlPanel.processStartGameAction(player1TypeToSet, player1NameToSet, player2TypeToSet, player2NameToSet);
            }

            private String getPlayer1NameFormatted() {
                String player1NameFieldContent = player1NameField.getText();
                /* Set up default name if it hasn't been provided in Name Field */
                return player1NameFieldContent.length() == 0 ?
                                                     "PLAYER 1" 
                                                     : player1NameFieldContent
                                                       /* Cut name to fit GUI */
                                                       .substring(0, Math.min(player1NameFieldContent.length(), 15))
                                                       .toUpperCase();
            }

            private String getPlayer2NameFormatted() {
                String player2NameFieldContent = player2NameField.getText();
                /* Set up default name if it hasn't been provided in Name Field */
                return player2NameFieldContent.length() == 0 ?
                                                     "PLAYER 2" 
                                                     : player2NameFieldContent
                                                       /* Cut name to fit GUI */
                                                       .substring(0, Math.min(player2NameFieldContent.length(), 15))
                                                       .toUpperCase();
            }

        };
    }
    
}