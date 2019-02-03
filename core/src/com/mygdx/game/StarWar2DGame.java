package com.mygdx.game;

import com.badlogic.gdx.Game;

import com.mygdx.game.screen.MenuScreen;

public class StarWar2DGame extends Game{
    @Override
    public void create() {
        setScreen(new MenuScreen());
    }
}
