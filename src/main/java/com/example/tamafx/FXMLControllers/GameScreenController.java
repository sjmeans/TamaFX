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
    public AnchorPane anchorPane;
    public FXGLPane bodyPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bodyPane = GameApplication.embeddedLaunch(new GameApp());
        System.out.println(bodyPane.isVisible());
        anchorPane.getChildren().add(bodyPane);

    }
}
