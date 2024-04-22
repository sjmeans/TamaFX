package com.example.tamafx;

import com.almasb.fxgl.dsl.FXGL;
import com.example.tamafx.FXGLApp.GameApp;
import com.example.tamafx.Deprecated.GameScreen;
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
    //CAUSING OBJECT BLOAT, may utilize singleton in some way
    public static void changeScene(String sceneName) throws IOException {
        if(gameFlag) closeGameApp(); // If game is running close gameApp to avoid reinstantiation bugs

        FXMLLoader loader = new FXMLLoader(MainGUI.class.getResource(sceneName));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        window.setScene(newScene);

        // Handle issues with terminating static game client
        if (sceneName.equals("GameScreen.fxml") && !gameFlag) {
            gameFlag = true;
        }
        if (!sceneName.equals("GameScreen.fxml") && gameFlag){
            closeGameApp();
            gameFlag = false;
        }

        System.out.printf("Loader: %s%nRoot: %s%nScene: %s%n", loader, root, newScene);
    }
    public static void closeGameApp(){
        FXGL.getAudioPlayer().stopAllSoundsAndMusic();
        GameApp.embeddedShutdown();
    }

    // Placeholder code to execute on close request handling
    private void closeProgram() {
        System.out.println("Closing program...");
        Platform.exit(); // Force close JavaFX program
    }
}
