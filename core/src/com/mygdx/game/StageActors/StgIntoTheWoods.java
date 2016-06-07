package com.mygdx.game.StageActors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Fonts;
import com.mygdx.game.GamITW;
import com.mygdx.game.TextButtons.TbMenu;
import com.mygdx.game.TextButtons.TbsMenu;

/**
 * Created by Luke on 2016-04-07.
 */
public class StgIntoTheWoods extends Stage{
    ActChar actChar;
    ActTiledMaps actTiledMaps;
    DPad dPad;
    TbsMenu tbsMenu;
    GamITW gamITW;
    Fonts fonts;
//    OrthographicCamera ocMainCam;
//    public static final int StageWidth=Gdx.graphics.getWidth();
//    public static final int StageHeight=Gdx.graphics.getHeight();
    public StgIntoTheWoods(){
//        ocMainCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        ocMainCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        fonts= new Fonts();
        gamITW= new GamITW();
        actChar= new ActChar();
        dPad= new DPad(actChar);
        tbsMenu = new TbsMenu(fonts);
        actTiledMaps= new ActTiledMaps();
        this.addActor(actChar);
        this.addActor(actTiledMaps);
        this.addActor(dPad.imgOutline);
        for (int i = 0; i < 4; i++) {
            this.addActor(dPad.ibDir[i]);
        }
        Gdx.input.setInputProcessor(this);
//        ocMainCam.position.set(actChar.fHeroX, actChar.fHeroY, 0);
//        ocMainCam.position.x = MathUtils.clamp(ocMainCam.position.x, 0 + (Gdx.graphics.getWidth() / 2), actTiledMaps.nMapTileWidth - (Gdx.graphics.getWidth() / 2));
//        ocMainCam.position.y = MathUtils.clamp(ocMainCam.position.y, 0 + (Gdx.graphics.getHeight() / 2), actTiledMaps.nMapTileHeight - (Gdx.graphics.getHeight() / 2));
//        actTiledMaps.orthotmrRenderer.setView(ocMainCam);
//        ocMainCam.update();


    }

}
