package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import com.mygdx.game.base.Base2DScreen;
import com.mygdx.game.math.Rect;
import com.mygdx.game.sprite.Background;
import com.mygdx.game.sprite.ButtonClose;
import com.mygdx.game.sprite.ButtonPlay;
import com.mygdx.game.sprite.Star;

public class MenuScreen extends Base2DScreen{

    private Game game;

    private TextureAtlas atlas;
    private TextureAtlas atlasBtns;
    private Texture bg;
    private Background background;

    private ButtonPlay btnPlay;
    private ButtonClose btnClose;

    private Star[] stars;

    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/background.jpg");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        atlasBtns = new TextureAtlas("textures/PlayEtExitBtn.pack");
        btnClose = new ButtonClose(atlasBtns);
        btnPlay = new ButtonPlay(atlasBtns,game);
        stars = new Star[100];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    public void update(float delta){
        for (int i = 0; i < stars.length; i++) {
            stars[i].update(delta);
        }
    }

    public void draw(){
        Gdx.gl.glClearColor(0.2f, 0.5f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        btnPlay.draw(batch);
        btnClose.draw(batch);
        for (int i = 0; i < stars.length; i++) {
            stars[i].draw(batch);
        }
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        btnPlay.resize(worldBounds);
        btnClose.resize(worldBounds);
        for (int i = 0; i < stars.length; i++) {
            stars[i].resize(worldBounds);
        }
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        btnClose.touchDown(touch, pointer);
        btnPlay.touchDown(touch, pointer);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        btnClose.touchUp(touch, pointer);
        btnPlay.touchUp(touch, pointer);
        return super.touchUp(touch, pointer);
    }
}
