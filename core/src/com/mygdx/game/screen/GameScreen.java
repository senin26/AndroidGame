package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Base2DScreen;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pool.BulletPool;
import com.mygdx.game.pool.EnemyShipPool;
import com.mygdx.game.pool.ExplosionPool;
import com.mygdx.game.sprite.Background;
import com.mygdx.game.sprite.Star;
import com.mygdx.game.sprite.game.EnemyShip;
import com.mygdx.game.sprite.game.Explosion;
import com.mygdx.game.sprite.game.MainShip;

public class GameScreen extends Base2DScreen {

    private Texture bg;
    private Background background;
    private TextureAtlas atlas;
    private TextureAtlas mainAtlas;
    private Star[] stars;
    private MainShip mainShip;

    private BulletPool bulletPool;
    private ExplosionPool explosionPool;

    private float newEnemyInterval = 3f;
    private float newEnemyTimer;
    private EnemyShipPool enemyShipPool;

    private Music music;

    @Override
    public void show() {
        super.show();
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setLooping(true);
        music.setVolume(0.8f);
        music.play();

        bg = new Texture("textures/background.jpg");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        mainAtlas = new TextureAtlas("textures/mainAtlas.tpack");

        bulletPool = new BulletPool();
        explosionPool = new ExplosionPool(mainAtlas);
        enemyShipPool = new EnemyShipPool(mainAtlas, bulletPool);

        mainShip = new MainShip(mainAtlas, bulletPool);

        stars = new Star[100];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        deleteAllDestroyed();
        draw();
    }

    public void update(float delta){
        for (int i = 0; i < stars.length; i++) {
            stars[i].update(delta);
        }
        mainShip.update(delta);
        newEnemyTimer += delta;
        if (newEnemyTimer >= newEnemyInterval) {
            newEnemyTimer = 0f;
            EnemyShip enemy = enemyShipPool.obtain();
            enemy.set(getWorldBounds());
        }
        enemyShipPool.updateActiveSprites(delta);
        bulletPool.updateActiveSprites(delta);
        explosionPool.updateActiveSprites(delta);
    }

    public void deleteAllDestroyed(){
        bulletPool.freeAllDestroyedActiveSprites();
        explosionPool.freeAllDestroyedActiveSprites();
        enemyShipPool.freeAllDestroyedActiveSprites();
    }

    public void draw(){
        Gdx.gl.glClearColor(0.2f, 0.5f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        mainShip.draw(batch);
        enemyShipPool.drawActiveSprites(batch);
        bulletPool.drawActiveSprites(batch);
        explosionPool.drawActiveSprites(batch);
        for (int i = 0; i < stars.length; i++) {
            stars[i].draw(batch);
        }
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        mainShip.resize(worldBounds);
        enemyShipPool.resizeActiveSprites(worldBounds);
        for (int i = 0; i < stars.length; i++) {
            stars[i].resize(worldBounds);
        }
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        explosionPool.dispose();
        enemyShipPool.dispose();
        mainShip.dispose();
        music.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        Explosion explosion = explosionPool.obtain();
        explosion.set(0.15f, touch);
        mainShip.touchDown(touch, pointer);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        mainShip.touchUp(touch, pointer);
        return super.touchUp(touch, pointer);
    }
}
