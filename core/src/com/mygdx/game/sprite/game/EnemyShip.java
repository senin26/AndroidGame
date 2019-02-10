package com.mygdx.game.sprite.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pool.BulletPool;

import java.util.Random;

public class EnemyShip extends Sprite {

    private Rect worldBounds;
    private final Vector2 v0 = new Vector2(0, -0.1f);
    private static Random random = new Random();
    private Random setRandPos = new Random();

    private TextureRegion bulletRegion;
    private BulletPool bulletPool;

    private float reloadInterval;
    private float reloadTimer;

    public EnemyShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion(setShipType()), 1, 2, 2);
        this.bulletRegion = atlas.findRegion("bulletEnemy");
        this.pos.rotate(180);
        this.bulletPool = bulletPool;
        this.reloadInterval = 1f;
        setHeightProportion(0.15f);
    }

    public static String setShipType() {
        if ((random.nextFloat() < 0.2f) || (random.nextFloat() > 0.8f)) {
            return "enemy2";
        }
        else if ((random.nextFloat() > 0.2f) & (random.nextFloat() < 0.5f)) {
            return "enemy0";
        }
        else return "enemy1";
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getTop() - 0.02f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v0, delta);
        reloadTimer += delta;
        if (isOutside(worldBounds)){
            destroy();
        }
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
    }

    public void set(Rect worldBounds) {
        this.worldBounds = worldBounds;
        pos.set(worldBounds.getLeft()+0.1f+setRandPos.nextFloat()*(worldBounds.getRight()-0.2f-worldBounds.getLeft()), worldBounds.getTop()-0.02f);
    }

    private void shoot(){
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos.cpy().add(new Vector2(0f, pos.cpy().y*0.175f)), new Vector2(0, -0.5f), 0.01f, worldBounds, 1);
    }

}
