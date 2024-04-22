package com.example.tamafx.Deprecated;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.app.FXGLPane;
import com.almasb.fxgl.app.GameApplication;
import com.example.tamafx.FXGLApp.GameApp;
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
/* Was used in MainGUI
 *     // TEMPORARY method to display game scene via debug screen handling
 *     // Maybe use a javaFX empty scene with a controller which extends initializable and handle all the current gamescreen logic there?
 *     public static void gameScene() throws IOException {
 *         GameScreen.display(window.getTitle());
 *     }
 *
 */