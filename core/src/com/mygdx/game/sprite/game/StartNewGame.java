package com.mygdx.game.sprite.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.mygdx.game.pool.ScaledTouchUpButton;
import com.mygdx.game.screen.GameScreen;

public class StartNewGame extends ScaledTouchUpButton {

    private GameScreen gameScreen;

    public StartNewGame(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
        setHeightProportion(0.05f);
        setTop(-0.012f);
    }

    @Override
    public void action() {
        gameScreen.startNewGame();
    }
}
