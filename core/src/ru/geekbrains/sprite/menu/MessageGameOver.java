package ru.geekbrains.sprite.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class MessageGameOver extends Sprite {

    public MessageGameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
        //setHeight(0.02f);
        setHeightProportion(0.05f);
    }

    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        System.out.println("GAME OVER, resize");
        setBottom(getHalfHeight()*2f);
        setLeft(-getHalfWidth());
    }
}
