package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import com.mygdx.game.base.Base2DScreen;
import com.mygdx.game.math.Rect;
import com.mygdx.game.sprite.Background;
import com.mygdx.game.sprite.Button;
import com.mygdx.game.sprite.Star;

public class MenuScreen extends Base2DScreen{

    private static final float V_LEN = 0.002f;

    private TextureAtlas atlas;
    private TextureAtlas atlasBtns;
    private Texture bg;
    private Texture exitTexture;
    private Texture playTexture;
    private Background background;
    private Button play;
    private Button close;
    private Star[] stars;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/background.jpg");
        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        atlasBtns = new TextureAtlas("textures/PlayEtExitBtn.pack");
        background = new Background(new TextureRegion(bg));
        play = new Button(atlasBtns, "play");
        close = new Button(atlasBtns, "close");
        stars = new Star[256];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
        if (close.isMe(getTouch())){
            close.action();
        }
        else if (play.isMe(getTouch())){
            play.action();
        }
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
        play.draw(batch);
        close.draw(batch);
        for (int i = 0; i < stars.length; i++) {
            stars[i].draw(batch);
        }
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        play.resize(worldBounds);
        close.resize(worldBounds);
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
        return super.touchDown(touch, pointer);
    }

}
