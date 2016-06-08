package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.StageActors.Dialog;
import com.mygdx.game.Fonts;
import com.mygdx.game.GamITW;
import com.mygdx.game.StageActors.HealthBar;
import com.mygdx.game.TextButtons.TbDialog;
import com.mygdx.game.TextButtons.TbMenu;
import com.mygdx.game.TextButtons.TbsDialog;
import com.mygdx.game.TextButtons.TbsMenu;

import java.util.Scanner;

/**
 * https://github.com/libgdx/libgdx/wiki/File-handling
 * http://badlogicgames.com/forum/viewtopic.php?f=11&t=2913
 * Created by michelle on 4/20/2016.
 */
public class ScrBattle implements Screen {

    GamITW gamITW;
    Texture txEnemy, txHealthBorder, txBackground, txHero,txHeroHealth, txEnemyHealth, txWeapon;
    TbsMenu tbsMenu;
    TbsDialog tbsDialog;
    TbDialog tbDialog;
    Fonts fonts;
    HealthBar healthBar;
    TbMenu tbAttack, tbWeapons;
    SpriteBatch spriteBatch;
    int nEnemyHealth = 200, nHeroHealth = 200, nDamage, i;
    Stage stage;
    BitmapFont font;
    String sEnemy, sHero, sFile;
    String arsDialog [] = new String[9];

    public ScrBattle(GamITW gamITW, HealthBar _healthBar, Fonts _fonts, Dialog dialog) {
        this.gamITW = gamITW;
        this.healthBar = _healthBar;
        this.fonts = _fonts;
    }
    public void damage (int fDamage){
        nDamage = fDamage;
    }
    public void weapon (Texture texture){
        txWeapon = texture;
    }
    public void dialog (String string){
        sFile = string;
    }

    @Override
    public void show() {
        stage = new Stage();
        tbsMenu = new TbsMenu(fonts);
        tbsDialog = new TbsDialog(fonts);
        tbAttack = new TbMenu("Attack", tbsMenu);
        tbWeapons = new TbMenu("Change Weapon", tbsMenu);
        tbWeapons.setBounds(270, 0, 260, 100);
        tbAttack.setBounds(0, 0 , 260, 100);
        stage.addActor(tbAttack);
        stage.addActor(tbWeapons);
        font = fonts.makeFont(2);
        txBackground = new Texture(Gdx.files.internal("woods.jpg"));
        txHero = new Texture(Gdx.files.internal("cinderella.png"));
        txHealthBorder = new Texture(Gdx.files.internal("healthborder.png"));
        txEnemy = new Texture(Gdx.files.internal("witch.png"));

        tbAttack.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                nEnemyHealth = nEnemyHealth - nDamage;
                txEnemyHealth = healthBar.HealthColour(nEnemyHealth);
                System.out.println("Enemy: "+nEnemyHealth);
                sEnemy = "Witch's Health: "+ nEnemyHealth;
                i = (int)(Math.random() * 9);

                Scanner sParse = new Scanner(sFile);
                for (int k= 0; sParse.hasNext(); k++) {
                    arsDialog[k] = sParse.nextLine();
                }
                tbDialog = new TbDialog(arsDialog[i], tbsDialog);
                tbDialog.setBounds(-10, -10, 660, 200);
                stage.addActor(tbDialog);

                if (nEnemyHealth <= 0) {
                    nEnemyHealth = 200;
                    nHeroHealth = 200;
                    gamITW.currentState = GamITW.GameState.WIN;
                    gamITW.updateState();

                } else {
                  //Timer:
                    //http://atsiitech.blogspot.ca/2013/09/adding-15-second-timer-to-your-games.html
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            tbDialog.remove();
                            int nDamage = (int )(Math.random() * 25 + 5);
                            nHeroHealth = nHeroHealth-nDamage;
                            txHeroHealth = healthBar.HealthColour(nHeroHealth);
                            System.out.println("Hero: "+nHeroHealth);
                            sHero = "Your Health: "+nHeroHealth;
                            if (nHeroHealth <= 0) {
                                nEnemyHealth = 200;
                                nHeroHealth = 200;
                                gamITW.currentState = GamITW.GameState.LOSE;
                                gamITW.updateState();
                            }
                        }
                    }, (float) 1.4);
                }
                return true;
            }
        });
        txEnemyHealth = healthBar.HealthColour(nEnemyHealth);
        txHeroHealth = healthBar.HealthColour(nHeroHealth);

        tbWeapons.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gamITW.currentState = GamITW.GameState.WEAPONS;
                gamITW.updateState();
                return true;
            }
        });
        sHero = "Your Health: "+nHeroHealth;
        sEnemy = "Witch's Health: "+ nEnemyHealth;
        spriteBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glClearColor(1,1,1,1);

        stage.act();
        spriteBatch.begin();
        spriteBatch.draw(txBackground, 0, 0);
        spriteBatch.draw(txHealthBorder, 390, 380, 220, 30);
        spriteBatch.draw(txHealthBorder, 0, 380, 220, 30);
        font.draw(spriteBatch, sHero, 0, 450);
        font.draw(spriteBatch, sEnemy, 370, 450);
        spriteBatch.draw(txEnemyHealth,400, 390, nEnemyHealth, 15);
        spriteBatch.draw(txHeroHealth, 10, 390, nHeroHealth, 15);
        spriteBatch.draw(txWeapon, 550, 30, 70, 50);
        spriteBatch.draw(txEnemy, 400, 180, 200, 200);
        spriteBatch.draw(txHero, 0, 170, 200, 200);
        spriteBatch.end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
