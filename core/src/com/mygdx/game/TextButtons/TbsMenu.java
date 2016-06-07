package com.mygdx.game.TextButtons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Fonts;

/**
 * Created by michelle on 4/20/2016.
 */
public class TbsMenu extends TextButton.TextButtonStyle{
    Skin skin;
    TextureAtlas taAtlas;
    Fonts fonts;
    BitmapFont buttonFont;

    public TbsMenu(Fonts _fonts) {
        fonts = _fonts;
        skin = new Skin();
        buttonFont = fonts.makeFont(0);
        skin.add("default", buttonFont);
        taAtlas = new TextureAtlas(Gdx.files.internal("Button.pack"));
        skin.addRegions(taAtlas);
        this.up = skin.getDrawable("buttonpressed01");
        this.down = skin.getDrawable("buttonpressed02");
        this.font = buttonFont;
    }
}


