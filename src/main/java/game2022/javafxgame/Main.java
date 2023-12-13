package game2022.javafxgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
// As a help from the internet
//
// I have taken the idea of having gridpane as board ,stckpane as boxes and Imageview as Players/Obsatcles from already created
// JavaFX Chess Game as this project is a board based game.But the code is significantly different as per requirements of the Simon's Race.
// For verification of the statement above please check the codebase of that game:
// https://github.com/OmDharme/Chess---JavaFX
// For Learning JAVAFX I followed thenewboston channel on YouTube to create alertboxes
// Link to that channel : https://www.youtube.com/watch?v=FLkOX4Eez6o&list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG
// To write TestFx test cases I have used : https://github.com/TestFX/TestFX , https://stackoverflow.com/questions/58845282/can-i-test-stage-buttons-or-alert-dialog-with-testfx-and-junit

/**
 * This class contains the entrypoint to the whole Game
 */
public class Main extends Application {

    public static Stage gameWindow;

    /**
     * This method is responsible for creating whole game with Game-UI.fxml file  and displaying it on the
     * Stage
     * @param stage stage on which all components of game will be placed
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Game-UI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Simon's Race");
        stage.setScene(scene);
        gameWindow=stage;
        stage.show();
    }

    /**
     * Responsible for calling the start method
     */
    public static void main(String[] args) {
        launch();
    }
}