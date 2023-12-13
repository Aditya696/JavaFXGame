package game2022.javafxgame.AlertBox;

import game2022.javafxgame.Participants.Player1;
import game2022.javafxgame.Participants.Player2;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
 * This class collects information about winner details and show it the alertbox
 */
public class WinnerAnnouncement {

    public static Button closeButton;
    public static Label winnerLabel;

    /**
     * This method will create an alert box containing labels with winner name and score details taken from the Field class
     * @param message contains name of the winner
     * @param GameWindow instance of the game stage
     * @param player1 instance of Player1
     * @param player2 instance of Player2
     */
    public static void displayWinnerName(String message, Stage GameWindow, Player1 player1, Player2 player2)
    {
        Stage announcementWindow =new Stage();
        announcementWindow.initModality(Modality.APPLICATION_MODAL);
        announcementWindow.setTitle("Winner of Simon's Race");
        announcementWindow.setWidth(600);
        Label label1=new Label("Winner Order");
        String winningSequence=winningOrder(player1,player2);
        Label label2=new Label(winningSequence);
        Label label = new Label();
        label.setText(message);
        winnerLabel=label;
        Button button=new Button();
        button.setText("Close Everything");
        button.setOnAction(actionEvent -> {
            GameWindow.close();
            announcementWindow.close();
        });
        closeButton=button;
        VBox vBox =new VBox(10);
        vBox.getChildren().addAll(label,label1,label2,button);
        vBox.setAlignment(Pos.CENTER);

        Scene scene =new Scene(vBox);
        announcementWindow.setScene(scene);
        announcementWindow.show();

    }

    /**
     * This method will collect the scores from Players and return a message containing player names and scores in descending order
     * @param player1 instance of Player1
     * @param player2 instance of Player2
     * @return Desired message of winning order
     */
    public static String winningOrder(Player1 player1,Player2 player2)
    {
        String order="";
        if (player1.getScore()>player2.getScore())
            order=player1.getName()+"- "+player1.getScore()+", "+player2.getName()+"- "+player2.getScore();
        else
            order=player2.getName()+"- "+player2.getScore()+", "+player1.getName()+"- "+player1.getScore();
        return order;

    }
}
