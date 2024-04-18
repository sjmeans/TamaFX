package com.example.tamafx;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.app.FXGLPane;
import com.almasb.fxgl.app.GameApplication;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

// DEPRECATED

public class GameScreen {
    public static void display(String title){
        //Initialize JavaFX components
        Stage window = new Stage();
        AnchorPane layout = new AnchorPane();
        FXGLPane pane = GameApplication.embeddedLaunch(new GameApp());

        layout.getChildren().addAll(pane);
        Scene scene = new Scene(layout);

        window.setTitle(title);
        window.setScene(scene);

        //Handle close request
        window.setOnCloseRequest(e -> {
            e.consume();
            try {
                FXGL.getAudioPlayer().stopAllSoundsAndMusic();
                GameApp.embeddedShutdown();
                window.close();
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        });

        //Display finalized window element
        window.showAndWait();
    }
}
