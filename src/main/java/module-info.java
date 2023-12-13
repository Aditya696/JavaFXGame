module game2022.javafxgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens game2022.javafxgame to javafx.fxml;
    exports game2022.javafxgame;
    exports game2022.javafxgame.Participants;
    opens game2022.javafxgame.Participants to javafx.fxml;
    exports game2022.javafxgame.BoardComponents;
    opens game2022.javafxgame.BoardComponents to javafx.fxml;
    exports game2022.javafxgame.AlertBox;
    opens game2022.javafxgame.AlertBox to javafx.fxml;
}