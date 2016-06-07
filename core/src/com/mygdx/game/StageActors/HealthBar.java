package com.mygdx.game.StageActors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by michelle on 5/5/2016.
 *
 * Idea came from (Rectangle that shrinks for the health bar)
 * http://stackoverflow.com/questions/24356672/how-to-create-a-healthbar-in-libgdx
 *
 */
public class HealthBar{

    public static Texture HealthColour(float fHealth) {
        Texture txRedHealth = new Texture(Gdx.files.internal("red.png"));
        Texture txGreenHealth = new Texture(Gdx.files.internal("green.jpg"));
        Texture txYellowHealth = new Texture(Gdx.files.internal("yellow.png"));
        if (fHealth >= 120) {
           return txGreenHealth;
        } else if (fHealth >= 60 && fHealth < 120) {
           return txYellowHealth;
        }
           return txRedHealth;
}
}