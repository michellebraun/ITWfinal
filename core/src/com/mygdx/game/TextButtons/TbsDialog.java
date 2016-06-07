package com.mygdx.game.TextButtons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Fonts;

/**
 * Created by michelle on 5/10/2016.
 */
public class TbsDialog extends TextButton.TextButtonStyle{
    Skin skin = new Skin();
    TextureAtlas taAtlas;
    Fonts fonts;
    BitmapFont dialogFont;

    public TbsDialog(Fonts _fonts) {
        fonts = _fonts;
        dialogFont = fonts.makeFont(3);
        skin.add("default", dialogFont);
        taAtlas = new TextureAtlas(Gdx.files.internal("FancyBox.pack"));
        skin.addRegions(taAtlas);
        this.up = skin.getDrawable("FancyText");
        this.font = dialogFont;
    }
}
