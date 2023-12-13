package game2022.javafxgame.BoardComponents;

import javafx.scene.layout.StackPane;

/**
 * This class is main fundamental structure of game as it contains the Players and obstacles on the top of it.
 * It is placed as square pieces on the top of gridpane, and it extends the stackpane
 * It contains information about position of box i.e x and y coordinates , and whether the box is filled (by Player or obstacle) or not
 */
public class Box extends StackPane {

    private boolean filled;
    private int xCoordinate;
    private int yCoordinate;

    public Box(boolean filled, int xCoordinate, int yCoordinate) {
        this.filled = filled;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public boolean isFilled() {
        return filled;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
