package com.example.tamafx.FXGLApp;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.example.tamafx.MainGUI;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.example.tamafx.FXGLApp.EntityType.*;

public class GameApp extends GameApplication {
    private Entity player;

    @Override
    protected void initInput() {
        onKey(KeyCode.A, () -> {
            if(player.getPosition().getX() <= 0) {
                player.translateX(0);
            }
            else player.translateX(-10);
        });
        onKey(KeyCode.D, () -> {
            if (player.getPosition().getX() >= getSettings().getWidth() - player.getWidth()) {
                player.translateX(0);
            }
            else player.translateX(10);
        });
        onKey(KeyCode.W, () -> {
            if (player.getPosition().getY() <= 0) {
                player.translateY(0);
            }
            else player.translateY(-10);
        });
        onKey(KeyCode.S, () -> {
            if(player.getPosition().getY() >= getSettings().getHeight() - player.getHeight()) {
                player.translateY(0);
            }
            else player.translateY(10);
        });

        //onKey(KeyCode.Q, () -> System.out.println(getSettings().getWidth() + "\n"+ getSettings().getHeight()));
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("TamaGamers");
        settings.setVersion("0.1");
        settings.setWidth(MainGUI.getWindowSize("x"));
        settings.setHeight(MainGUI.getWindowSize("y"));
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new BasicFactory());
        player = spawn("player", getAppWidth() / 2.0 - 32, getAppHeight() - 128);
        run(() -> {
            spawnFadeIn("goodStuff", new SpawnData(random(0, getSettings().getWidth())), Duration.seconds(0.3));
            spawnFadeIn("badStuff", new SpawnData(random(0,getSettings().getWidth())), Duration.seconds(0.6));
        }, Duration.seconds(0.5));

        getAudioPlayer().loopMusic(getAssetLoader().loadMusic("bgm.mp3"));
    }

    @Override
    protected void initPhysics() {
        onCollisionBegin(PLAYER, GOODSTUFF, (p, stuff) ->{
            inc("score", +1);
            stuff.removeFromWorld();
        });
        onCollisionBegin(PLAYER, BADSTUFF, (p, stuff) ->{
            inc("score", -2);
            stuff.removeFromWorld();
        });
    }

    @Override
    protected void initUI() {
        var text = getUIFactoryService().newText("", Color.BLUE, 24.0);
        text.setTranslateX(50);
        text.setTranslateY(50);
        text.textProperty().bind(getip("score").asString());
        addUINode(text);

        Text textPixels = new Text();
        textPixels.setTranslateX(50);
        textPixels.setTranslateY(100);

        FXGL.getGameScene().addUINode(textPixels); // add to the scene graph
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", 0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
