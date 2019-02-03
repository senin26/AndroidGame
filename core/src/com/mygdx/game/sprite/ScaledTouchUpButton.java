package com.mygdx.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.math.Rect;

public abstract class ScaledTouchUpButton extends Sprite {

    public static final float PRESS_SCALE = 0.75f;
    private int pointer;
    private boolean isPressed;
    private Vector2 touch = new Vector2();
    private Rect screenBounds;

    public ScaledTouchUpButton(TextureRegion region) {
        super(region);
        this.screenBounds = new Rect();
    }

    public abstract void action();

}
