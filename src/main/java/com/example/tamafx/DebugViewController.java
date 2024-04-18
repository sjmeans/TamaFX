package com.example.tamafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class DebugViewController {
    @FXML
    Button debugButton1, debugButton2, debugButton3, debugButton4;

    // Debug Button handlers
    @FXML
    private void handleButton1(){
        System.out.println("1");
        try {
            MainGUI.changeScene("CareScreen.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleButton2(){
        System.out.println("2");
        try {
            MainGUI.changeScene("BattleScreen.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleButton3(){
        System.out.println("3");
        try {
            MainGUI.gameScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleButton4(){
        System.out.println("4");
        try {
            // If scene is currently on GameScreen it will have gamespeed bug
            MainGUI.changeScene("GameScreen.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
