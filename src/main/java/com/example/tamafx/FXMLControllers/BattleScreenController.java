package com.example.tamafx.FXMLControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class BattleScreenController {
    @FXML
    Button attackButton1, attackButton2, attackButton3, attackButton4;
    @FXML
    Button menuButton1, menuButton2, menuButton3, menuButton4;
    @FXML
    ImageView slimeRed, slimeEnemy;


    // Attack Button handlers
    @FXML
    private void handleAttackButton1(){
        System.out.println("You attack with attack 1!");
    }
    @FXML
    private void handleAttackButton2(){
        System.out.println("You attack with attack 2!");
    }
    @FXML
    private void handleAttackButton3(){
        System.out.println("You attack with attack 3!");
    }
    @FXML
    private void handleAttackButton4(){
        System.out.println("You attack with attack 4!");
    }

    // Menu Button handlers
    @FXML
    private void handleMenuButton1(){
        System.out.println("You used menu button 1.");
    }
    @FXML
    private void handleMenuButton2(){
        System.out.println("You used menu button 2.");
    }
    @FXML
    private void handleMenuButton3(){
        System.out.println("You used menu button 3.");
    }
    @FXML
    private void handleMenuButton4(){
        System.out.println("You used menu button 4.");
    }
}
