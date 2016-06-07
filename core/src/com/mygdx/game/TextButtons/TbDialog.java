package com.mygdx.game.TextButtons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by michelle on 5/10/2016.
 */
public class TbDialog extends TextButton {

    String sText;
    public TbDialog(String text, TextButtonStyle tbs) {
        super(text, tbs);
        sText = text;
        this.setSize(600, 200);
        this.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
            }
        });
    }}