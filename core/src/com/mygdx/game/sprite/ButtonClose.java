package com.mygdx.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.math.Rect;

public class ButtonClose extends ScaledTouchUpButton {

    public ButtonClose(TextureAtlas atlas) {
        super(atlas.findRegion("close"));
        setHeightProportion(0.15f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom()+0.02f);
        setLeft(worldBounds.getLeft()+0.02f);
    }

    @Override
    public void action() {
    Gdx.app.exit();
    }
}
