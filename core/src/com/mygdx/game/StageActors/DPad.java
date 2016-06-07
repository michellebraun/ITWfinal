package com.mygdx.game.StageActors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class DPad extends Actor {

    Image imgOutline;
    ActChar actChar;
    Sprite sprDpad[] = new Sprite[4];
    Skin skDpad;
    Drawable drwDpad[] = new Drawable[4];
    ImageButtonStyle ibsDpad[] = new ImageButtonStyle[4];
    IbDir ibDir[] = new IbDir[4];

    public DPad(ActChar _actChar) {
        actChar = _actChar;
        //border around the dpad
        imgOutline = new Image(new Texture("dpad_outline.png"));
        imgOutline.setPosition(15, 15);

        //arrays for directions of dpad
        for (int i = 0; i < 4; i++) {
            // 0-3: up, right, down, left
            sprDpad[i] = new Sprite(new Texture("dpad_arrow" + i + ".png"));
            //no need for an array of skins
            skDpad = new Skin();
            skDpad.add("dpad" + i, sprDpad[i]);
            drwDpad[i] = skDpad.getDrawable("dpad" + i);
            ibsDpad[i] = new ImageButtonStyle();
            ibsDpad[i].imageUp = drwDpad[i];
            ibDir[i] = new IbDir(ibsDpad[i]);
            ibDir[i].setIndex(i, actChar);
        }
        //0 = up, 1 = right, 2 = down, 3 = left
        ibDir[0].setPosition(55, 100);
        ibDir[1].setPosition(105, 50);
        ibDir[2].setPosition(55, 0);
        ibDir[3].setPosition(0, 50);
    }
}
