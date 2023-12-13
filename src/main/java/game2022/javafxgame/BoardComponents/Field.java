package game2022.javafxgame.BoardComponents;


import game2022.javafxgame.AlertBox.WinnerAnnouncement;
import game2022.javafxgame.Controller;
import game2022.javafxgame.Main;
import game2022.javafxgame.Participants.Player1;
import game2022.javafxgame.Participants.Player2;
import javafx.geometry.Insets;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Objects;

// As a help from the internet
//
// I have taken the idea of having gridpane as board ,stckpane as boxes and Imageview as Players/Obsatcles from already created
// JavaFX Chess Game as this project is a board based game.But the code is significantly different as per requirements of the Simon's Race.
// For verification of the statement above please check the codebase of that game:
// https://github.com/OmDharme/Chess---JavaFX
// For Learning JAVAFX I followed thenewboston channel on YouTube to create alertboxes
// Link to that channel : https://www.youtube.com/watch?v=FLkOX4Eez6o&list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG
// To write TestFx test cases I have used : https://github.com/TestFX/TestFX

/**
 * This class contains the core logic of game like creating board,adding participants,obstacles
 * Wait for input moves then perform validations,obstacle effects and scoring
 * Its constructor will take gridpane and Controller instance as parameters
 */
public class Field {
    private LinkedList<Box> boxes = new LinkedList<>();

    private Controller controller;
    private static LinkedList<Box> obstacleList = new LinkedList<>();
    public static game2022.javafxgame.Participants.Player1 Player1;
    public static String turn;
    public static game2022.javafxgame.Participants.Player2 Player2;
    public static GridPane field;
    public static LinkedList<Box> startingBoxes = new LinkedList<>();

    public Field(GridPane field, Controller controller) {
        Field.field = field;
        this.controller = controller;
        createField(Field.field);
        addParticipants(boxes);
        addObstacles();
        movement(Field.field);
        turn = "Player1";
    }

    /**
     * This function will create field with 8 rows and 4 columns with stackpane as boxes on
     * cells making it a perfect foundation for participants and obstacles
     *
     * @param pane Contains stackpane(Boxes) and UI of the game
     */
    public void createField(GridPane pane) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 9; j++) {
                Box box = new Box(false, i, j);
                box.setPrefHeight(150);
                box.setPrefWidth(80);
                if (j == 0)
                    box.setBackground(new Background(new BackgroundFill(Color.web("FF0000"), CornerRadii.EMPTY, Insets.EMPTY)));
                else {
                    if (j == 8)
                        box.setBackground(new Background(new BackgroundFill(Color.web("008000"), CornerRadii.EMPTY, Insets.EMPTY)));
                    else {
                        if ((i + j) % 2 == 0)
                            box.setBackground(new Background(new BackgroundFill(Color.web("008080"), CornerRadii.EMPTY, Insets.EMPTY)));
                        else
                            box.setBackground(new Background(new BackgroundFill(Color.web("ffffff"), CornerRadii.EMPTY, Insets.EMPTY)));
                    }
                }
                if (i == 1 && j == 1 || i == 3 && j == 1) {
                    box.setEffect(new Glow(1));
                    box.setFilled(true);
                    obstacleList.add(box);
                }
                pane.add(box, i, j);
                boxes.add(box);
            }
        }

    }

    /**
     * addParticipants will add Players on the respective boxes
     *
     * @param boxes It will contain list of all boxes placed on pane
     */
    public void addParticipants(LinkedList<Box> boxes) {
        for (Box box : boxes) {
            if (box.getxCoordinate() == 3 && box.getyCoordinate() == 8) {
                box.getChildren().add(Player1 = new Player1(3, 8, "Player1"));
                box.setFilled(true);
                startingBoxes.add(box);
            }
            if (box.getxCoordinate() == 1 && box.getyCoordinate() == 8) {
                box.getChildren().add(Player2 = new Player2(1, 8, "Player2"));
                box.setFilled(true);
                startingBoxes.add(box);
            }
        }
    }

    /**
     * addObstacles will add obstacles on the respective boxes.It also updates them in the obstacleList
     */
    public void addObstacles() {
        for (Box box : boxes) {
            if (box.getxCoordinate() == 0 && box.getyCoordinate() == 5) {
                box.getChildren().add(new Obstacles(5, 0, "Portal"));
                box.setFilled(true);
                obstacleList.add(box);
            }
            if (box.getxCoordinate() == 2 && box.getyCoordinate() == 3) {
                box.getChildren().add(new Obstacles(3, 2, "Cross"));
                box.setFilled(true);
                obstacleList.add(box);
            }
            if (box.getxCoordinate() == 3 && box.getyCoordinate() == 3) {
                box.getChildren().add(new Obstacles(3, 3, "Portal"));
                box.setFilled(true);
                obstacleList.add(box);
            }

        }
    }

    /**
     * movement is core method for detecting any click on the boxes inside the field.
     * It will call the validating procedure , receive a list and if possible ask for the
     * necessary action
     *
     * @param pane Contains stackpane(Boxes) and UI of the game
     */
    public void movement(GridPane pane) {
        pane.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getTarget().toString().contains("Box")) {
                Box targetBox = (Box) mouseEvent.getTarget();
                Box startingBox = returnPlayerBox();
                LinkedList<Box> validBoxes = validateMove(startingBox);

                if (validBoxes != null) {
                    if (validBoxes.contains(targetBox)) {
                        if (!(targetBox == startingBox)) {
                            startingBox.getChildren().removeAll();
                            startingBox.setFilled(false);
                            setPlayer(targetBox);
                        }
                    }
                }
            }
        });
    }

    /**
     * @return Box of the current Player having turn.
     */
    public Box returnPlayerBox() {
        if (turn.equals("Player1"))
            return (Box) Player1.getParent();
        else
            return (Box) Player2.getParent();
    }

    /**
     * This function will place the player on the target box according to their turn
     *
     * @param box target box where Player will be placed
     */
    public void setPlayer(Box box) {
        box.getChildren().removeAll();
        addScore(box);
        if (turn.equals("Player1")) {
            box.getChildren().add(Player1);
            Player1.setCol(box.getxCoordinate());
            Player1.setRow(box.getyCoordinate());

        } else {
            box.getChildren().add(Player2);
            Player2.setCol(box.getxCoordinate());
            Player2.setRow(box.getyCoordinate());
        }
        box.setFilled(true);
        performObstacleEffect(box);
        checkWinningCondition(box);
        controller.endTurn();
        Controller.direction = null;
        Controller.distance = 0;
    }

    /**
     * Checks if player is placed on obstacles or not and if Yes then call the respective methods to
     * perform the respective effects
     *
     * @param box targetbox where player has been placed
     */
    public void performObstacleEffect(Box box) {
        if (obstacleList.contains(box)) {
            if (box.getxCoordinate() == 2 && box.getyCoordinate() == 3) {
                sendToStartingBox(box);
            }
            if (box.getxCoordinate() == 0 && box.getyCoordinate() == 5) {
                for (Box box1 : boxes) {
                    if (box1.getxCoordinate() == 3 && box1.getyCoordinate() == 4)
                        sendToPortalDoor(box1, box);

                }
            }
            if (box.getxCoordinate() == 3 && box.getyCoordinate() == 3) {
                for (Box box1 : boxes) {
                    if (box1.getxCoordinate() == 0 && box1.getyCoordinate() == 6)
                        sendToPortalDoor(box1, box);

                }
            }

            if (box.getxCoordinate() == 1 && box.getyCoordinate() == 1 || box.getxCoordinate() == 3 && box.getyCoordinate() == 1)
                swapPlayers(box);

        }
    }

    /**
     * This function performs the swapping of players positions if one of them steps on glowing boxes obstacle
     *
     * @param oldPosition is position of player who stepped on obstacle
     */
    public void swapPlayers(Box oldPosition) {
        oldPosition.getChildren().removeAll();
        Box newPosition = turn.equals("Player1") ? (Box) Player2.getParent() : (Box) Player1.getParent();
        newPosition.getChildren().removeAll();
        newPosition.getChildren().add(turn.equals("Player1") ? Player1 : Player2);
        oldPosition.getChildren().add(turn.equals("Player1") ? Player2 : Player1);
        if (turn.equals("Player1")) {
            Player1.setCol(newPosition.getxCoordinate());
            Player1.setRow(newPosition.getyCoordinate());
        } else {
            Player2.setCol(oldPosition.getxCoordinate());
            Player2.setRow(oldPosition.getyCoordinate());
        }
    }

    /**
     * This function will send the current player to his starting position
     *
     * @param playerPos Box on which current player stepped on
     */
    public void sendToStartingBox(Box playerPos) {
        Box newBox;
        playerPos.getChildren().removeAll();
        if (turn.equals("Player1")) {
            newBox = startingBoxes.get(1);
            newBox.getChildren().add(Player1);
            newBox.setFilled(true);
            Player1.setRow(newBox.getyCoordinate());
            Player1.setCol(newBox.getxCoordinate());
        } else {
            newBox = startingBoxes.get(0);
            newBox.getChildren().add(Player2);
            newBox.setFilled(true);
            Player2.setRow(newBox.getyCoordinate());
            Player2.setCol(newBox.getxCoordinate());
        }
    }

    /**
     * This function will send the current player to the portal's door on the other side
     *
     * @param newPlayerPos box of other portal's door
     * @param oldPlayerPos box of portal on which player in turn has stepped on
     */
    public void sendToPortalDoor(Box newPlayerPos, Box oldPlayerPos) {
        oldPlayerPos.getChildren().removeAll();
        newPlayerPos.setFilled(true);
        if (turn.equals("Player1")) {
            newPlayerPos.getChildren().add(Player1);
            Player1.setRow(newPlayerPos.getyCoordinate());
            Player1.setCol(newPlayerPos.getxCoordinate());
        } else {
            newPlayerPos.getChildren().add(Player2);
            Player2.setRow(newPlayerPos.getyCoordinate());
            Player2.setCol(newPlayerPos.getxCoordinate());
        }

    }

    /**
     * This function will call methods based upon the direction to get the valid list of boxes a player can travel
     * to .
     *
     * @param playerPos current position of player
     * @return List of valid boxes a player is allowed to visit
     */
    public LinkedList<Box> validateMove(Box playerPos) {
        try {
            if (Controller.direction.equals("forward")) {
                return validBoxesForward(playerPos.getxCoordinate(), playerPos.getyCoordinate(), Controller.distance);

            }
            if (Controller.direction.equals("backward")) {
                return validBoxesBackward(playerPos.getxCoordinate(), playerPos.getyCoordinate(), Controller.distance);
            }
        } catch (Exception e) {
            System.out.println("Please Roll the Dice first");
        }
        return null;
    }

    /**
     * This function will return the list of valid boxes in the forward direction. In case there
     * is obstruction in front then it will check for the side boxes for remaining distance.
     *
     * @param x        column number of current player
     * @param y        row number of current player
     * @param distance total number of boxes a player can travel
     * @return list of valid boxes in forward direction
     */
    public LinkedList<Box> validBoxesForward(int x, int y, int distance) {
        LinkedList<Box> permissibleBoxes = new LinkedList<>();
        int count = distance;
        for (int a = y - 1; a >= y - distance; a--) {
            for (Box temp : boxes) {
                if (temp.getxCoordinate() == x && temp.getyCoordinate() == a) {
                    if (!temp.isFilled()) {
                        permissibleBoxes.add(temp);
                        count--;
                    } else {
                        if (obstacleList.contains(temp))
                            permissibleBoxes.add(temp);
                        LinkedList<Box> rightSide = checkBoxesSide(x + 1, a + 1, count);
                        LinkedList<Box> leftSide = checkBoxesSide(x - 1, a + 1, count);
                        permissibleBoxes = updateList(permissibleBoxes, rightSide);
                        permissibleBoxes = updateList(permissibleBoxes, leftSide);
                        return permissibleBoxes;
                    }
                }
            }
        }
        return permissibleBoxes;
    }

    /**
     * This function will check for current coordinates if the box (left/right side of the given direction) is filled or not. If not filled then it will start exploring
     * to the forward/backward direction
     *
     * @param x        column number of side box
     * @param y        row number of the side box
     * @param distance boxes left to explore
     * @return list of valid boxes in the sides
     */
    public LinkedList<Box> checkBoxesSide(int x, int y, int distance) {
        LinkedList<Box> permissibleBoxes = new LinkedList<>();
        for (Box box : boxes) {
            if (box.getxCoordinate() == x && box.getyCoordinate() == y) {

                if (box.isFilled()) {
                    if (obstacleList.contains(box)) {
                        permissibleBoxes.add(box);
                        return permissibleBoxes;
                    } else
                        return null;
                } else {
                    permissibleBoxes.add(box);
                    distance = distance - 1;
                    if (distance == 0)
                        return permissibleBoxes;
                    else {
                        LinkedList<Box> temp = Objects.equals(Controller.direction, "forward") ? validBoxesForward(box.getxCoordinate(), box.getyCoordinate(), distance) : validBoxesBackward(box.getxCoordinate(), box.getyCoordinate(), distance);
                        for (Box box1 : temp) {
                            if (box1 != null)
                                permissibleBoxes.add(box1);
                        }

                    }
                }
            }
        }
        return permissibleBoxes;
    }

    /**
     * This function will return the list of valid boxes in the backward direction. In case there
     * is obstruction in front then it will check for the side boxes for remaining distance.
     *
     * @param x        column number of current Player
     * @param y        row number of current Player
     * @param distance number of boxes a player can move
     * @return list of boxes valid in backward direction
     */
    public LinkedList<Box> validBoxesBackward(int x, int y, int distance) {
        LinkedList<Box> permissibleBoxes = new LinkedList<>();
        int count = distance;
        for (int a = y + 1; a <= y + distance; a++) {
            for (Box temp : boxes) {
                if (temp.getxCoordinate() == x && temp.getyCoordinate() == a) {
                    if (!temp.isFilled()) {

                        permissibleBoxes.add(temp);
                        count--;
                    } else {
                        if (obstacleList.contains(temp))
                            permissibleBoxes.add(temp);
                        LinkedList<Box> rightSide = checkBoxesSide(x + 1, a - 1, count);
                        LinkedList<Box> leftSide = checkBoxesSide(x - 1, a - 1, count);
                        permissibleBoxes = updateList(permissibleBoxes, rightSide);
                        permissibleBoxes = updateList(permissibleBoxes, leftSide);
                        return permissibleBoxes;
                    }
                } else {
                    if (temp.getyCoordinate() == 8 && temp.getxCoordinate() == x && a == 9 && count != 0) {
                        LinkedList<Box> rightSide = checkBoxesSide(x + 1, 8, count);
                        LinkedList<Box> leftSide = checkBoxesSide(x - 1, 8, count);
                        permissibleBoxes = updateList(permissibleBoxes, rightSide);
                        permissibleBoxes = updateList(permissibleBoxes, leftSide);
                    }
                }
            }
        }

        return permissibleBoxes;
    }

    /**
     * This function will take the contents of newList and add it in the mainList
     *
     * @param mainList whose contents need to updated
     * @param newList  whose contents need to extracted
     * @return the updated mainlist
     */
    public LinkedList<Box> updateList(LinkedList<Box> mainList, LinkedList<Box> newList) {
        if (newList != null) {
            for (Box newBox : newList) {
                if (newBox != null)
                    mainList.add(newBox);
            }
        }
        return mainList;
    }

    /**
     * This function will check if the box player just moved to is a finishing line or not.
     * If Yes then it will end the game and show an alert box containing details of winner and
     * score of each player
     *
     * @param checkBox Box on which player in turn has been placed
     */
    public void checkWinningCondition(Box checkBox) {
        if (checkBox.getyCoordinate() == 0) {
            String player1Name = Player1.getName() == null ? "Player1" : Player1.getName();
            String player2Name = Player2.getName() == null ? "Player2" : Player2.getName();
            String message = turn.equals("Player1") ? player1Name + " is Winner" : player2Name + " is Winner";
            WinnerAnnouncement.displayWinnerName(message, Main.gameWindow, Player1, Player2);
            if (Main.gameWindow != null) {
                if (Main.gameWindow.isShowing())
                    Main.gameWindow.close();
            }
        }
    }

    /**
     * This function will add score to player having turn based upon the number of boxes he moved
     *
     * @param targetBox box on which player has been placed
     */
    public void addScore(Box targetBox) {
        Box box = returnPlayerBox();
        int score = absoluteScore(box.getxCoordinate(), targetBox.getxCoordinate()) + absoluteScore(box.getyCoordinate(), targetBox.getyCoordinate());
        if (turn.equals("Player1"))
            Player1.setScore(score);
        else
            Player2.setScore(score);
    }

    /**
     *  This function returns the absolute difference between current position coordinate and target position coordinate
     * @param x current position coordinate of player
     * @param y target position coordinate of player
     * @return Difference between the coordinates
     */
    public int absoluteScore(int x, int y) {
        int difference = x - y;
        if (difference < 0)
            return -(difference);
        else
            return difference;
    }

    public LinkedList<Box> getBoxes() {
        return boxes;
    }

}



