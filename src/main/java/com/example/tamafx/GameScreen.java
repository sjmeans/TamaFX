package com.example.tamafx;

import com.almasb.fxgl.app.FXGLPane;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameScreen {
    public static void display(String title){
        Stage window = new Stage();

        window.setTitle(title);
        FXGLPane pane = GameApplication.embeddedLaunch(new GameApp());
        AnchorPane layout = new AnchorPane();
        layout.getChildren().addAll(pane);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.setOnCloseRequest(e -> {
            e.consume();
            //GameApplication.embeddedShutdown();
            FXGL.getGameController().exit();
            window.close();
        });
        window.showAndWait();
    }
}
