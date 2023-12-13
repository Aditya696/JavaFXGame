package game2022.javafxgame.Participants;

import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class Player2Test extends ApplicationTest {

    @Test
    void valuesTest() {
        Player2 player2 =new Player2(2,4,"Player2");
        assertEquals("Player2",player2.getName());
        assertEquals(4,player2.getRow());
        assertEquals(2,player2.getCol());
        assertTrue(player2.getImage() instanceof Image);
    }
}