package game2022.javafxgame.BoardComponents;

import javafx.scene.layout.StackPane;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class BoxTest extends ApplicationTest {
    @Test
    void valuesTest() {
        Box box = new Box(false, 2, 4);
        assertEquals(2, box.getxCoordinate());
        assertEquals(4, box.getyCoordinate());
        assertFalse(box.isFilled());
    }
}