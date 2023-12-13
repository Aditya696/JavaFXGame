package game2022.javafxgame;

import game2022.javafxgame.BoardComponents.Field;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest extends ApplicationTest {
    private Controller controller;
    private Field field;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Game-UI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Simon's Race");
        stage.setScene(scene);
        controller= fxmlLoader.getController();
        controller.initialize();
        field=controller.getBoard();
    }

    @Test
    void setNameTest() {
        controller.setDirection(1);
        controller.setDistance();
        controller.setPlayer1Name();
        controller.setPlayer2Name();
        assertEquals("forward",Controller.direction);
        assertEquals("Player1",Field.Player1.getName());
        assertEquals("Player2",Field.Player2.getName());
    }

    @Test
    void directionTest() {
        controller.setDirection(3);
        assertEquals("backward",Controller.direction);
        controller.setDirection(4);
        assertEquals("skip",Controller.direction);
    }
}