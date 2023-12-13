package game2022.javafxgame.BoardComponents;

import game2022.javafxgame.Controller;
import game2022.javafxgame.Main;
import game2022.javafxgame.Participants.Player2;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;



class FieldTest extends ApplicationTest{
    static LinkedList<Box> box;
    static Field field;

    static Controller controller;


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Game-UI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Simon's Race");
        stage.setScene(scene);
        stage.show();
        controller=fxmlLoader.getController();
        field=controller.getBoard();
        box=field.getBoxes();
    }

    @Test
    public void Player1WithoutDiceRoll() {
        Box player1pos=(Box) Field.Player1.getParent();
        Box targetPos=null;
        for (Box box1:box)
        {
            if (box1.getxCoordinate()==3&&box1.getyCoordinate()==5)
                targetPos=box1;
        }
        clickOn(targetPos);
        assertEquals(player1pos.getxCoordinate(),Field.Player1.getCol());
        assertEquals(player1pos.getyCoordinate(),Field.Player1.getRow());
    }

    @Test
    public void Player2WithoutDiceRoll() {
        Box player1pos=(Box) Field.Player2.getParent();
        Field.turn="Player2";
        Box targetPos=null;
        for (Box box1:box)
        {
            if (box1.getxCoordinate()==1&&box1.getyCoordinate()==5)
                targetPos=box1;
        }
        clickOn(targetPos);
        assertEquals(player1pos.getxCoordinate(),Field.Player2.getCol());
        assertEquals(player1pos.getyCoordinate(),Field.Player2.getRow());
    }
    @Test
    public void Player1MovementTest()  {
        Box targetPos = null;
        Controller.setDirection("forward");
        Controller.setDistance(3);
        for (Box box1:box)
        {
            if (box1.getxCoordinate()==3&&box1.getyCoordinate()==5)
                targetPos=box1;
        }
        clickOn(targetPos);

        assertEquals(targetPos.getxCoordinate(),Field.Player1.getCol());
        assertEquals(targetPos.getyCoordinate(),Field.Player1.getRow());

    }

    @Test
    public void Player2MovementTest()  {
        Box targetPos = null;
        Controller.setDirection("forward");
        Controller.setDistance(3);
        Field.turn="Player2";
        for (Box box1:box)
        {
            if (box1.getxCoordinate()==1&&box1.getyCoordinate()==5)
                targetPos=box1;
        }
        clickOn(targetPos);

        assertEquals(targetPos.getxCoordinate(),Field.Player2.getCol());
        assertEquals(targetPos.getyCoordinate(),Field.Player2.getRow());

    }

    @Test
    public void Player1MovementBackwardTest()  {
        Box playerPos=(Box)Field.Player1.getParent();
        Box targetPos = null;
        Controller.setDirection("forward");
        Controller.setDistance(3);
        for (Box box1:box)
        {
            if (box1.getxCoordinate()==3&&box1.getyCoordinate()==5)
                targetPos=box1;
        }
        clickOn(targetPos);
        Field.turn="Player1";
        Controller.setDirection("backward");
        Controller.setDistance(3);
        clickOn(playerPos);
        assertEquals(playerPos.getxCoordinate(),Field.Player1.getCol());
        assertEquals(playerPos.getyCoordinate(),Field.Player1.getRow());

    }

    @Test
    public void Player2MovementBackwardTest()  {
        Box playerPos=(Box)Field.Player2.getParent();
        Box targetPos = null;
        Field.turn="Player2";
        Controller.setDirection("forward");
        Controller.setDistance(3);
        for (Box box1:box)
        {
            if (box1.getxCoordinate()==1&&box1.getyCoordinate()==5)
                targetPos=box1;
        }
        clickOn(targetPos);
        Field.turn="Player2";
        Controller.setDirection("backward");
        Controller.setDistance(3);
        clickOn(playerPos);
        assertEquals(playerPos.getxCoordinate(),Field.Player2.getCol());
        assertEquals(playerPos.getyCoordinate(),Field.Player2.getRow());

    }

    @Test
    public void Player1MovementBackwardStartTest()  {
        Field.turn="Player1";
        Box targetPos = null;
        Controller.setDirection("backward");
        Controller.setDistance(3);
        for (Box box1:box)
        {
            if (box1.getxCoordinate()==2&&box1.getyCoordinate()==8)
                targetPos=box1;
        }
        clickOn(targetPos);
        assertEquals(targetPos.getxCoordinate(),Field.Player1.getCol());
        assertEquals(targetPos.getyCoordinate(),Field.Player1.getRow());
    }

    @Test
    public void Player2MovementBackwardStartTest()  {
        Box targetPos = null;
        Field.turn="Player2";
        Controller.setDirection("backward");
        Controller.setDistance(3);
        for (Box box1:box)
        {
            if (box1.getxCoordinate()==2&&box1.getyCoordinate()==8)
                targetPos=box1;
        }
        clickOn(targetPos);
        assertEquals(targetPos.getxCoordinate(),Field.Player2.getCol());
        assertEquals(targetPos.getyCoordinate(),Field.Player2.getRow());
    }

    @Test
    public void PlayerTurnTest()  {
        Box targetPos = null;
        Box targetPos2=null;

        Controller.setDirection("forward");
        Controller.setDistance(3);
        for (Box box1:box)
        {
            if (box1.getxCoordinate()==3&&box1.getyCoordinate()==5)
                targetPos=box1;
            if (box1.getxCoordinate()==1&&box1.getyCoordinate()==5)
                targetPos2=box1;

        }
        clickOn(targetPos);
        Controller.setDirection("forward");
        Controller.setDistance(3);
        clickOn(targetPos2);
        assertEquals(targetPos.getxCoordinate(),Field.Player1.getCol());
        assertEquals(targetPos.getyCoordinate(),Field.Player1.getRow());
        assertEquals(targetPos2.getyCoordinate(),Field.Player2.getRow());
        assertEquals(targetPos2.getxCoordinate(),Field.Player2.getCol());
    }

    @Test
    public void ObstacleAvoidTest()  {
        Box targetPos = null;
        Box targetPos2 = null;
        Box targetPos3 = null;
        LinkedList<Box> testingBoxes=new LinkedList<>();

        Controller.setDirection("forward");
        Controller.setDistance(3);
        for (Box box1:box)
        {
            if (box1.getxCoordinate()==3&&box1.getyCoordinate()==5)
                targetPos=box1;
            if (box1.getxCoordinate()==1&&box1.getyCoordinate()==5)
                targetPos2=box1;
            if (box1.getxCoordinate()==1&&box1.getyCoordinate()==4)
                targetPos3=box1;
            if (box1.getxCoordinate()==1&&box1.getyCoordinate()==3)
                testingBoxes.add(box1);
            if (box1.getxCoordinate()==3&&box1.getyCoordinate()==2)
                testingBoxes.add(box1);

        }
        clickOn(targetPos);
        Controller.setDirection("forward");
        Controller.setDistance(3);
        clickOn(targetPos2);
        Controller.setDirection("forward");
        Controller.setDistance(3);
        for (Box box1:testingBoxes)
            clickOn(box1);
        clickOn(targetPos3);
        assertEquals(1, Field.Player1.getCol());
        assertEquals(4, Field.Player1.getRow());
        assertEquals(targetPos2.getyCoordinate(),Field.Player2.getRow());
        assertEquals(targetPos2.getxCoordinate(),Field.Player2.getCol());
    }

    @Test
    public void ObstacleAvoidBackwardTest()  {
        Box targetPos = null;
        Box targetPos2 = null;
        Box targetPos3 = null;
        Box finalPos = null;


        Controller.setDirection("forward");
        Controller.setDistance(3);
        for (Box box1:box)
        {
            if (box1.getxCoordinate()==3&&box1.getyCoordinate()==5)
                targetPos=box1;
            if (box1.getxCoordinate()==1&&box1.getyCoordinate()==4)
                targetPos2=box1;
            if (box1.getxCoordinate()==2&&box1.getyCoordinate()==1)
                targetPos3=box1;
            if (box1.getxCoordinate()==3&&box1.getyCoordinate()==2)
                finalPos=box1;

        }
        clickOn(targetPos);
        Field.turn="Player1";
        Controller.setDirection("forward");
        Controller.setDistance(3);
        clickOn(targetPos2);
        Field.turn="Player1";
        Controller.setDirection("forward");
        Controller.setDistance(4);
        clickOn(targetPos3);
        Field.turn="Player1";
        Controller.setDirection("backward");
        Controller.setDistance(3);
        clickOn(finalPos);
        assertEquals(3, Field.Player1.getCol());
        assertEquals(2, Field.Player1.getRow());
    }

    @Test
    public void ObstaclePortal1Test()  {
        Box targetPos = null;
        Box finalPos = null;
        Box obstaclePos =null;
        Controller.setDirection("forward");
        Controller.setDistance(3);
        for (Box box1:box)
        {
            if (box1.getxCoordinate()==3&&box1.getyCoordinate()==5)
                targetPos=box1;
            if (box1.getxCoordinate()==3&&box1.getyCoordinate()==3)
                obstaclePos=box1;
            if (box1.getxCoordinate()==0&&box1.getyCoordinate()==6)
                finalPos=box1;
        }
        clickOn(targetPos);
        Field.turn="Player1";
        Controller.setDirection("forward");
        Controller.setDistance(3);
        clickOn(obstaclePos);
        assertEquals(finalPos.getxCoordinate(),Field.Player1.getCol());
        assertEquals(finalPos.getyCoordinate(),Field.Player1.getRow());
    }


    @Test
    public void ObstaclePortal2Test()  {
        Box targetPos = null;
        Box finalPos = null;
        Box obstaclePos =null;
        Field.turn="Player2";
        Controller.setDirection("backward");
        Controller.setDistance(3);
        for (Box box1:box)
        {
            if (box1.getxCoordinate()==0&&box1.getyCoordinate()==8)
                targetPos=box1;
            if (box1.getxCoordinate()==0&&box1.getyCoordinate()==5)
                obstaclePos=box1;
            if (box1.getxCoordinate()==3&&box1.getyCoordinate()==4)
                finalPos=box1;
        }
        clickOn(targetPos);
        Field.turn="Player2";
        Controller.setDirection("forward");
        Controller.setDistance(3);
        clickOn(obstaclePos);
        assertEquals(finalPos.getxCoordinate(),Field.Player2.getCol());
        assertEquals(finalPos.getyCoordinate(),Field.Player2.getRow());
    }

    @Test
    public void ObstacleCrossTest()  {
        Box targetPos = null;
        Box finalPos = (Box) Field.Player1.getParent();
        Box obstaclePos =null;
        Controller.setDirection("forward");
        Controller.setDistance(3);
        for (Box box1:box)
        {
            if (box1.getxCoordinate()==3&&box1.getyCoordinate()==5)
                targetPos=box1;
            if (box1.getxCoordinate()==2&&box1.getyCoordinate()==3)
                obstaclePos=box1;

        }
        clickOn(targetPos);
        Field.turn="Player1";
        Controller.setDirection("forward");
        Controller.setDistance(3);
        clickOn(obstaclePos);
        assertEquals(finalPos.getxCoordinate(),Field.Player1.getCol());
        assertEquals(finalPos.getyCoordinate(),Field.Player1.getRow());
    }

    @Test
    public void ObstacleSwitchPlacesTest()  {
        Box targetPos = null;
        Box finalPos = (Box) Field.Player2.getParent();
        Box targetPos2 =null;
        Box obstaclePos =null;
        Controller.setDirection("forward");
        Controller.setDistance(4);
        for (Box box1:box)
        {
            if (box1.getxCoordinate()==3&&box1.getyCoordinate()==5)
                targetPos=box1;
            if (box1.getxCoordinate()==1&&box1.getyCoordinate()==1)
                obstaclePos=box1;
            if (box1.getxCoordinate()==1&&box1.getyCoordinate()==4)
                targetPos2=box1;
        }
        clickOn(targetPos);
        Field.turn="Player1";
        Controller.setDirection("forward");
        Controller.setDistance(4);
        clickOn(targetPos2);
        Field.turn="Player1";
        Controller.setDirection("forward");
        Controller.setDistance(3);
        clickOn(obstaclePos);
        assertEquals(finalPos.getxCoordinate(),Field.Player1.getCol());
        assertEquals(finalPos.getyCoordinate(),Field.Player1.getRow());
    }

    @Test
    public void WinningConditionTest()  {
        Box targetPos = null;
        Box finalPos = null;
        Box targetPos2 =null;
        Controller.setDirection("forward");
        Controller.setDistance(4);
        for (Box box1:box)
        {
            if (box1.getxCoordinate()==3&&box1.getyCoordinate()==4)
                targetPos=box1;
            if (box1.getxCoordinate()==0&&box1.getyCoordinate()==0)
                finalPos=box1;
            if (box1.getxCoordinate()==1&&box1.getyCoordinate()==3)
                targetPos2=box1;
        }
        clickOn(targetPos);
        Field.turn="Player1";
        Controller.setDirection("forward");
        Controller.setDistance(4);
        clickOn(targetPos2);
        Field.turn="Player1";
        Controller.setDirection("forward");
        Controller.setDistance(4);
        clickOn(finalPos);
        assertEquals(finalPos.getxCoordinate(),Field.Player1.getCol());
        assertEquals(finalPos.getyCoordinate(),Field.Player1.getRow());
    }

}