package com.mygdx.game.StageActors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by Luke on 2016-04-07.
 */
public class ActChar extends Actor implements Json.Serializable {
    TextureAtlas taHero;
    TextureRegion trCurrentFrame, trLeft[], trRight[],trUp[],trDown[];
    Animation aniLeft, aniRight,aniUp,aniDown;
    Animation[] arAnimations;
    String sDirection="down";
    ActTiledMaps actTiledMaps;

//    static Sound Steps;
    float fStateTime;
    int nDir=4;
    Texture Background;
    float fHeroX=0,fHeroY=0,fHeroSpeed=50f;
    public ActChar(){
        //Background= new Texture(Gdx.files.internal("lostwoods2.jpg"));
        taHero = new TextureAtlas("PackedCinderellaSpriteSheet.pack");
        trCurrentFrame = new TextureRegion();
//        Steps= Gdx.audio.newSound(Gdx.files.internal("Steps.mp3"));
//        Music= Gdx.audio.newMusic(Gdx.files.internal("IntoTheWoods(Prologue).mp3"));
//        Music.isLooping();
//        Music.play();

        trLeft = new TextureRegion[3];
        trRight = new TextureRegion[3];
        trUp = new TextureRegion[3];
        trDown = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            trLeft[i] = taHero.findRegion("Left" + (i + 1));
            trRight[i] = taHero.findRegion("Right" + (i + 1));
            trUp[i] = taHero.findRegion("Up" + (i + 1));
            trDown[i] = taHero.findRegion("Down" + (i + 1));
        }
        aniRight= new Animation(1f/8,trRight);
        aniLeft= new Animation(1f/8,trLeft);
        aniUp= new Animation(1f/8,trUp);
        aniDown= new Animation(1f/8,trDown);
        // 0-3: up, right, down, left
//		arAnimations 0=up 1=right 2=down 3=left
        arAnimations= new Animation[]{aniUp,aniRight,aniDown,aniLeft};
    }
    public void setDir(int _nDir){
        nDir = _nDir;
        System.out.println(nDir);

    }
    public void act(float fDelta){
//        actTiledMaps.act();
        trCurrentFrame= aniDown.getKeyFrame(0);
        fStateTime += Gdx.graphics.getDeltaTime();
        if(nDir==3) {
            //Left
           fHeroX -= Gdx.graphics.getDeltaTime() * fHeroSpeed;
            trCurrentFrame = arAnimations[nDir].getKeyFrame(0+fStateTime,true);
            sDirection = "Left";
//            Steps.play(0.5f);
//            actTiledMaps.act();

        }
        else if(nDir==1) {
            //right
            fHeroX += Gdx.graphics.getDeltaTime() * fHeroSpeed;
            trCurrentFrame = arAnimations[nDir].getKeyFrame(0+fStateTime,true);
            sDirection = "Right";
//            actTiledMaps.act();
//            Steps.play(0.5f);
        }
        else if(nDir==0) {
            //up
            fHeroY += Gdx.graphics.getDeltaTime() * fHeroSpeed;
            trCurrentFrame = arAnimations[nDir].getKeyFrame(0+fStateTime,true);
            sDirection = "Up";
//            actTiledMaps.act();
//            Steps.play(0.5f);

        }
        else if(nDir==2) {
            //down
            fHeroY -= Gdx.graphics.getDeltaTime() * fHeroSpeed;
            trCurrentFrame = arAnimations[nDir].getKeyFrame(0+fStateTime,true);
            sDirection = "Down";
//            actTiledMaps.act();
//            Steps.play(0.5f);
        }
        else{
            trCurrentFrame = aniDown.getKeyFrame(0);
        }
    }
    public void draw(Batch batch, float parentAlpha){
        batch.draw(trCurrentFrame, (int)fHeroX, (int)fHeroY,50,100);
    }

    @Override
    public void write(Json json) {


    }

    @Override
    public void read(Json json, JsonValue jsonData) {

    }
}
