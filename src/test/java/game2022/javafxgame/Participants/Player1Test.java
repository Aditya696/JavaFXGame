package game2022.javafxgame.Participants;

import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Player1Test extends ApplicationTest {

    @Test
    void valuesTest() {
        Player1 player1 = new Player1(1, 4, "Player1");
        assertEquals("Player1", player1.getName());
        assertEquals(4, player1.getRow());
        assertEquals(1, player1.getCol());
        assertTrue(player1.getImage() instanceof Image);
    }
}