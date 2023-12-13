package game2022.javafxgame.AlertBox;

import game2022.javafxgame.Participants.Player1;
import game2022.javafxgame.Participants.Player2;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;


import static org.junit.jupiter.api.Assertions.*;

class WinnerAnnouncementTest extends ApplicationTest {

    private Label checkLabel;

    private Button button;
    @Override
    public void start(Stage stage) {
        String message = "Winner is Test";
        WinnerAnnouncement.displayWinnerName(message, new Stage(),new Player1(1,2,"Mark"),new Player2(1,2,"Henry"));
        checkLabel = WinnerAnnouncement.winnerLabel;
        button=WinnerAnnouncement.closeButton;
    }

    @Test
    void displayWinnerTest() {
        FxAssert.verifyThat(checkLabel, NodeMatchers.isVisible());
        FxAssert.verifyThat(checkLabel, LabeledMatchers.hasText("Winner is Test"));
        clickOn(button);
    }
}