package com.example.tamafx.FXMLControllers;

import com.almasb.fxgl.app.FXGLPane;
import com.almasb.fxgl.app.GameApplication;
import com.example.tamafx.FXGLApp.GameApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameScreenController implements Initializable {
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXGLPane embeddedGamePane = GameApplication.embeddedLaunch(new GameApp());
        anchorPane.getChildren().add(embeddedGamePane);
    }
}
