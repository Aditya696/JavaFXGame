package game2022.javafxgame;

import game2022.javafxgame.BoardComponents.Field;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Random;
// As a help from the internet
//
// I have taken the idea of having gridpane as board ,stckpane as boxes and Imageview as Players/Obsatcles from already created
// JavaFX Chess Game as this project is also a board based game.But the code is significantly different  as per requirements of the Simon's Race.
// For verification of the statement above please check the codebase of that game:
// https://github.com/OmDharme/Chess---JavaFX
// For Learning JAVAFX I followed thenewboston channel on YouTube to create alertboxes
// Link to that channel : https://www.youtube.com/watch?v=FLkOX4Eez6o&list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG
// To write TestFx test cases I have used : https://github.com/TestFX/TestFX  ,  https://stackoverflow.com/questions/58845282/can-i-test-stage-buttons-or-alert-dialog-with-testfx-and-junit

/**
 * This class is responsible for taking any actions from the UI , sending
 * the input to the Field and collecting response and sending back to the
 * UI for display.
 */
public class Controller {
    @FXML
    public GridPane field;

    public static int distance;

    public static String direction;

    private Random random = new Random();

    @FXML
    public TextField field1;

    @FXML
    public TextField field2;

    @FXML
    public TextField player1Name;

    @FXML
    public TextField player2Name;

    @FXML
    public TextField displayTurnValue;

    public Field board;

    /**
     * initializes the game as Game-UI.fxml is loaded
     */
    @FXML
    public void initialize() {
        Field field1 = new Field(field, this);
        board = field1;
    }

    /**
     * Generates a random number between 1 and 4
     * and calls setDirection to set Direction in textfield
     */
    @FXML
    public void getDirection() {
        int a = random.nextInt(1, 5);
        setDirection(a);
    }

    /**
     * Based upon the parameter this function will set the direction of Player's movements
     * in textfield
     *
     * @param a random value generated between 1 and 4 to determine the direction
     */
    public void setDirection(int a) {
        String playerName;
        switch (a) {
            case 1:
            case 2:
                direction = "forward";
                break;
            case 3:
                direction = "backward";
                break;
            case 4:
                direction = "skip";
                break;
        }
        field1.setText(direction);
        if (direction.equals("skip")) {
            endTurn();
            field1.setText("Skip Roll again");
        }
        playerName = getPlayerName(Field.turn);
        displayTurn(playerName);
    }

    /**
     * Return name of Player having turn
     *
     * @param turn determines which player's turn to roll the dice
     * @return name of the player
     */
    public String getPlayerName(String turn) {
        String returnName = turn.equals("Player1") ? Field.Player1.getName() : Field.Player2.getName();
        if (returnName == null)
            return turn;
        else
            return returnName;
    }

    /**
     * set the player name in the UI textfield
     *
     * @param playerName name which will be displayed
     */
    public void displayTurn(String playerName) {
        displayTurnValue.setText(playerName);
    }

    /**
     * This method will end turn for current player call the displayTurn to display the name of other player
     */
    public void endTurn() {
        if (Field.turn.equals("Player1"))
            Field.turn = "Player2";
        else
            Field.turn = "Player1";

        String name = getPlayerName(Field.turn);
        displayTurn(name);
    }

    /**
     * It sets the distance in th textfield based upon the random number between 1 and 5
     */
    @FXML
    public void setDistance() {
        distance = random.nextInt(1, 5);
        field2.setText(Integer.toString(distance));
    }

    /**
     * Sets the player1 name based upon value in player1 textfield
     */
    @FXML
    public void setPlayer1Name() {
        Field.Player1.setName(player1Name.getText());
    }

    /**
     * Sets the player2 name based upon value in player2 textfield
     */
    @FXML
    public void setPlayer2Name() {
        Field.Player2.setName(player2Name.getText());
    }

    /**
     * Returns the Field instance responsible for creating game
     *
     * @return Field instance
     */
    public Field getBoard() {
        return board;
    }

    /**
     * Made for testing purpose to set the distance value
     *
     * @param distance number of boxes a player can move
     */
    public static void setDistance(int distance) {
        Controller.distance = distance;
    }

    /**
     * Made for testing purpose to set the direction value
     *
     * @param direction Direction in which player is allowed to move
     */

    public static void setDirection(String direction) {
        Controller.direction = direction;
    }
}