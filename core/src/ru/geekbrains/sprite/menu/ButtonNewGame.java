package ru.geekbrains.sprite.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.math.Rect;
import ru.geekbrains.screen.GameScreen;

public class ButtonNewGame extends ScaledTouchUpButton {
    private Game game;

    public ButtonNewGame(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("button_new_game"));
        this.game = game;
        setHeightProportion(0.05f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(-getHalfHeight()*2f);
        setLeft(-getHalfWidth());
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen(game));
    }

}
