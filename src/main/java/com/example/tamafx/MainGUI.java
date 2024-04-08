package com.example.tamafx;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainGUI extends Application {

    static Stage window, debugWindow;
    Scene scene, debugScene;
    Parent root, debugRoot;

    @Override
    public void start(Stage stage) throws IOException {
        try {
            // Initialize primary window
            window = stage;
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CareScreen.fxml")));
            scene = new Scene(root);
            window.setScene(scene);
            window.setTitle("Tamaslime!");
            window.show();

            // Initialize debug window
            debugWindow = new Stage();
            debugRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DebugView.fxml")));
            debugScene = new Scene(debugRoot);
            debugWindow.setScene(debugScene);
            debugWindow.setTitle("DEBUG WINDOW");
            debugWindow.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
    }

    /**
     * Changes scene in primary window to desired fxml resource.
     * @param sceneName input filename and extension of desired Scene in the resource folder.
     * @throws IOException
     */
    public static void changeScene(String sceneName) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainGUI.class.getResource(sceneName));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        window.setScene(newScene);
    }

    private void closeProgram() {
        System.out.println("Closing program...");
        Platform.exit();
    }

}
