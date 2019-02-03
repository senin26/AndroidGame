package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.math.Rect;
import com.mygdx.game.math.Rnd;

public class Button extends ScaledTouchUpButton {

    private Rect worldBounds;
    private String name;

    public Button(TextureAtlas atlas, String name) {
        super(atlas.findRegion(name));
        setHeightProportion(0.1f);
        this.name = name;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float addition = 0f;
        if (this.name.equals("play")) {
            addition = -0.08f;
        }
        else if (this.name.equals("close")) {
            addition = 0.08f;
        }
        float center = (worldBounds.getLeft()+worldBounds.getRight())/2;
        float posX = center + addition;
        float posY = worldBounds.getBottom() + getHalfHeight()*1.5f;
        pos.set(posX, posY);
    }

    @Override
    public void action() {
        if (this.name.equals("play")){
            this.setScale(super.PRESS_SCALE);
        }
        else if (this.name.equals("close")){
            this.setScale(super.PRESS_SCALE);
            System.exit(0);
        }
    }
}
