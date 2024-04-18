package com.example.tamafx;

import com.almasb.fxgl.dsl.FXGL;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

//Primary JavaFX GUI initialization, must be launched via Starter class
public class MainGUI extends Application {

    private static Stage window, debugWindow;
    private static boolean gameFlag = false;
    private Scene scene, debugScene;
    private Parent root, debugRoot;

    public void start(Stage stage) throws IOException {
        try {
            // Initialize primary window
            window = stage;
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CareScreen.fxml")));
            scene = new Scene(root);
            window.setScene(scene);
            window.setTitle("Tamaslime!");
            //window.setResizable(false);
            //window.setMaximized(true);
            // FXGLPane pane = GameApplication.embeddedLaunch(new GameApp());
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

        //Handle close request
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
    //CAUSING OBJECT BLOAT, utilize singleton in some way
    public static void changeScene(String sceneName) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainGUI.class.getResource(sceneName));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        window.setScene(newScene);
        if (sceneName.equals("GameScreen.fxml"))
            gameFlag = true;
        if (!sceneName.equals("GameScreen.fxml") && gameFlag){
            FXGL.getAudioPlayer().stopAllSoundsAndMusic();
            GameApp.embeddedShutdown();
            gameFlag = false;
        }
        System.out.printf("Loader: %s%nRoot: %s%nScene: %s%n", loader, root, newScene);
    }
    // TEMPORARY method to display game scene via debug screen handling
    // Maybe use a javaFX empty scene with a controller which extends initializable and handle all the current gamescreen logic there?
    public static void gameScene() throws IOException {
        GameScreen.display(window.getTitle());
    }

    // Placeholder code to execute on close request handling
    private void closeProgram() {
        System.out.println("Closing program...");
        Platform.exit(); // Force closes all stages
    }
}
