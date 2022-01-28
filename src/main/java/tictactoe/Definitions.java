package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;

public class Definitions {


    
    public static enum Mark {
        EMPTY(" "), CROSS("X"), NOUGHT("O");
        // ◯ //TODO: change representation
        private String representation;
        
        private Mark(String value) {
            this.representation = value;
        }

        public char getRepresentation() {
            return this.representation.charAt(0);
        }
    }

    public static enum PlayerType {
        HUMAN("Human"), BOT_EASY("BOT - easy"), BOT_MEDIUM("BOT - medium"), BOT_HARD ("BOT - hard");

        // private static ArrayList<PlayerType> allPlayersTypes = new ArrayList<>(Arrays
        //                                                        .asList(HUMAN, BOT_EASY, BOT_MEDIUM, BOT_HARD));
        
        private String representation;

        private PlayerType(String value) {
            this.representation = value;
        }

        // public static ArrayList<PlayerType> getPlayersTypes() {
        //     return allPlayersTypes;
        // }

        // public static PlayerType getPlayerTypeByRepresentation(String representation) {
        //     for (PlayerType playerType : getPlayersTypes()) {
        //         if (playerType.getRepresentation().equals(representation)) {
        //             return playerType;   
        //         }
        //     }
        //     throw new IllegalArgumentException(String
        //                 .format("Player type represented by '%s' doesn't exist", representation));
        // }

        // public static ArrayList<String> getPlayersTypesRepresentations() {
        //     ArrayList<String> representations = new ArrayList<>();
        //     allPlayersTypes.forEach(type -> representations.add(type.getRepresentation()));
        //     return representations;
        // }
        
        // public String getRepresentation() {
        //     return this.representation;
        // }
    }
}
