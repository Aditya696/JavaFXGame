package game2022.javafxgame.Participants;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * This class represents the first player participating in the game
 * It extends the Imageview to represent Player1's image.It contains information Player's row, column ,score and name
 */
public class Player1 extends ImageView {

    private int row;
    private int col;

    private int score;
    private String name;

    public Player1(int col, int row, String name) {
        this.row = row;
        this.col = col;
        this.name = name;
        this.score=0;
        this.setImage(new Image("file:P1.png"));
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = this.score+score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (Objects.equals(name, ""))
            this.name = "Player1";
        else
            this.name = name;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
