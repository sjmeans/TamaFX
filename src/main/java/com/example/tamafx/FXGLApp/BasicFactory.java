package com.example.tamafx.FXGLApp;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BasicFactory implements EntityFactory {
    @Spawns("player")
    public Entity newPlayer(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(EntityType.PLAYER)
                .viewWithBBox("slime.png")
                .collidable()
                .build();
    }
    @Spawns("goodStuff")
    public Entity newGoodStuff(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(EntityType.GOODSTUFF)
                .viewWithBBox(new Rectangle(20,20, Color.GREEN))
                .collidable()
                .with(new ProjectileComponent(new Point2D(0,1), 200))
                .with(new OffscreenCleanComponent())
                .build();
    }

    @Spawns("badStuff")
    public Entity newBadStuff(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(EntityType.BADSTUFF)
                .viewWithBBox(new Rectangle(20,20,Color.BLUEVIOLET))
                .collidable()
                .with(new ProjectileComponent(new Point2D(0,1), 100))
                .with(new OffscreenCleanComponent())
                .build();
    }
}
