package game2022.javafxgame.BoardComponents;

import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class ObstaclesTest extends ApplicationTest {
    @Test
    void valuesTest() {
        Obstacles obstacles=new Obstacles(4,2,"Portal");
        assertEquals(4,obstacles.getRow());
        assertEquals(2,obstacles.getCol());
        assertEquals("Portal",obstacles.getType());
        assertTrue(obstacles.getImage() instanceof Image);
    }

    @Test
    void valuesTest2() {
        Obstacles obstacles=new Obstacles(4,2,"Dummy");
        Obstacles obstacles1 =new Obstacles(0,0,"Cross");
        obstacles1.setCol(2);
        obstacles1.setRow(3);
        assertEquals(4,obstacles.getRow());
        assertEquals(2,obstacles.getCol());
        assertEquals("Dummy",obstacles.getType());
        assertTrue(!(obstacles.getImage() instanceof Image));

        assertEquals(3,obstacles1.getRow());
        assertEquals(2,obstacles1.getCol());
        assertEquals("Cross",obstacles1.getType());
        assertTrue((obstacles1.getImage() instanceof Image));


    }


}