package game2022.javafxgame.BoardComponents;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.LinkedList;

/**
 * This class contains information about the obstacles, their row and column positions and their type(Portal, Cross etc)
 * It extends ImageView to show images and placed upon the box(stackpane)
 */
public class Obstacles extends ImageView {
    private int row;
    private int col;
    private String type;

    public Obstacles(int row, int col, String type) {
        this.row = row;
        this.col = col;
        try {
            this.type = type;
            LinkedList<String> types=new LinkedList<>();
            types.add("Portal");
            types.add("Cross");
            if (!types.contains(type))
                throw new Exception();
        }
        catch (Exception e)
        {

            System.out.println("Wrong Type");
        }
        setObstacleType(this);
    }

    private void setObstacleType(Obstacles obstacle)
    {
       if (obstacle.type.equals("Portal"))
       {
           obstacle.setImage(new Image("file:Portal.png"));
       }
       if (obstacle.type.equals("Cross"))
       {
           obstacle.setImage(new Image("file:cross.png"));
       }
    }
    public String getType() {
        return type;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
