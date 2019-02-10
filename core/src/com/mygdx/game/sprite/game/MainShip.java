package com.mygdx.game.sprite.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pool.BulletPool;

public class MainShip extends Sprite {

    private Rect worldBounds;
    private final Vector2 v0 = new Vector2(0.5f, 0);
    private Vector2 v = new Vector2();

    private boolean isPressedLeft;
    private boolean isPressedRight;
    private boolean isTouchDown;
    private boolean isRightClckMove;
    private boolean isLeftClckMove;

    private TextureRegion bulletRegion;
    private BulletPool bulletPool;

    private float reloadInterval;
    private float reloadTimer;

    private Sound shootSound;

    public MainShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        this.bulletPool = bulletPool;
        this.reloadInterval = 0.2f;
        this.shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        setHeightProportion(0.15f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + 0.02f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            //shoot(); //todo you can uncomment it and comment 93-96
        }
        if (pos.x > (worldBounds.getLeft()+getHalfWidth())) {
            pos.mulAdd(v, delta);
        } else {
            pos.set(worldBounds.getLeft()+getHalfWidth(), pos.y);
        }
        if (pos.x < (worldBounds.getRight()-getHalfWidth())) {
            pos.mulAdd(v, delta);
        } else {
            pos.set(worldBounds.getRight()-getHalfWidth(), pos.y);
        }
    }

    private void moveRight() {
        v.set(v0);
    }

    private void moveLeft() {
        v.set(v0).rotate(180);
    }

    private void stop() {
        v.setZero();
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                isPressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                isPressedRight = true;
                moveRight();
                break;
            case Input.Keys.W:
            case Input.Keys.UP:
                shoot();
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                isPressedLeft = false;
                if (!isPressedRight && !isTouchDown) stop();
                else if (isPressedRight) moveRight();
                else if (!isPressedRight && isTouchDown) {
                    if (isRightClckMove) moveRight();
                    else if (isLeftClckMove) moveLeft();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                isPressedRight = false;
                if (!isPressedLeft && !isTouchDown) stop();
                else if (isPressedLeft) moveLeft();
                else if (!isPressedLeft && isTouchDown) {
                    if (isRightClckMove) moveRight();
                    else if (isLeftClckMove) moveLeft();
                }
                break;
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        isTouchDown = true;
        if (touch.x>0) {
            isRightClckMove = true;
            moveRight();
        }
        else if (touch.x<0) {
            isLeftClckMove = true;
            moveLeft();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        isTouchDown = false;
        if (touch.x>0) {
            isRightClckMove = false;
        }
        else if (touch.x<0) {
            isLeftClckMove = false;
        }
        if (!isPressedLeft && !isPressedRight) {
            stop();
        }
        else if (isPressedLeft || isPressedRight) {
            if (isPressedRight) moveRight();
            else moveLeft();
        }
        return false;
    }

    public void dispose() {
        shootSound.dispose();
    }

    private void shoot(){
        shootSound.play();
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos.cpy().add(new Vector2(0f, -pos.cpy().y*0.175f)), new Vector2(0, 0.5f), 0.01f, worldBounds, 1);
    }


}