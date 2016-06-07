package com.mygdx.game.StageActors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

/**
 * Created by Ashleigh on 2016-05-30.
 */
public class ActTiledMaps extends Actor{
    int nMapWidth, nMapHeight, nTileWidth, nTileHeight, nMapTileWidth, nMapTileHeight;

    ActChar actChar;
    TiledMap tmGameMap;
    OrthogonalTiledMapRenderer orthotmrRenderer;
    OrthographicCamera ocMainCam;
    float fHeroX, fHeroY;
    SpriteBatch sbBatch;

    ArrayList<Rectangle> arlRectObjectBounds = new ArrayList<Rectangle>();
    Rectangle rectSprite;
    MapProperties mpBounds;

    RectangleMapObject rmoCollisionRect;
    MapObjects moCollisionDetection;
    Rectangle rectObjectBounds;

    public ActTiledMaps(){
        actChar= new ActChar();
        rectSprite = new Rectangle();
        mpBounds = new MapProperties();
        fHeroX= actChar.fHeroX;
        fHeroY= actChar.fHeroY;

        //Setting Up Orthographic Camera
        ocMainCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ocMainCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ocMainCam.update();

        //Setting Up TiledMap
        tmGameMap = new TmxMapLoader().load("Mission1Map1.tmx");
        orthotmrRenderer = new OrthogonalTiledMapRenderer(tmGameMap);

        mpBounds = tmGameMap.getProperties();

        nMapWidth = mpBounds.get("width", Integer.class);
        nMapHeight = mpBounds.get("height", Integer.class);
        nTileWidth = mpBounds.get("tilewidth", Integer.class);
        nTileHeight = mpBounds.get("tileheight", Integer.class);

        nMapTileWidth = nMapWidth * nTileWidth;
        nMapTileHeight = nMapHeight * nTileHeight;

        moCollisionDetection = tmGameMap.getLayers().get("Collision").getObjects();
        //Loop through the objects in the object layer, creating a rectangle from each object's information (height, width, etc)
        for (int i = 0; i < moCollisionDetection.getCount(); i++) {
            rmoCollisionRect = (RectangleMapObject) moCollisionDetection.get(i);
            rectObjectBounds = rmoCollisionRect.getRectangle();
            //Add this rectangle made from the Object into an arraylist
            arlRectObjectBounds.add(rectObjectBounds);
            System.out.println("Rectangle Added!");
        }

    }
    public void act(float fDelta){
        ocMainCam.position.set(fHeroX, fHeroY, 0);
//        sbBatch.setProjectionMatrix(ocMainCam.combined);
        ocMainCam.position.x = MathUtils.clamp(ocMainCam.position.x, 0 + (Gdx.graphics.getWidth() / 2), nMapTileWidth - (Gdx.graphics.getWidth() / 2));
        ocMainCam.position.y = MathUtils.clamp(ocMainCam.position.y, 0 + (Gdx.graphics.getHeight() / 2), nMapTileHeight - (Gdx.graphics.getHeight() / 2));
        ocMainCam.update();

//        fHeroX = MathUtils.clamp(fHeroX, 0 + 16, nMapTileWidth - 16);
//        fHeroY = MathUtils.clamp(fHeroY, 0 + 32, nMapTileHeight -32);

        //Rendering Tiled Map
        orthotmrRenderer.setView(ocMainCam);
        orthotmrRenderer.render();
        ocMainCam.update();



    }

}
