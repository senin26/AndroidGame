package com.mygdx.game;

import com.badlogic.gdx.Game;

import com.mygdx.game.screen.MenuScreen;

public class StarWar2DGame extends Game{
    @Override
    public void create() {
        /*System.out.println("Math.atan(3,4)" + Math.atan(1.333)*180/(3.142));
        System.out.println("Math.asin(0.8)" + Math.asin(0.8)*180/(3.142));
        System.out.println("Math.sin(0.8)" + Math.sin(53.12*3.142/180));*/

        /*float dx = 2-3;
        float dy = 3-2;
        double phi = Math.atan((float) (dy/dx));
        System.out.println("Math.atan(phi)= " + phi*180/3.142);
        System.out.println("Math.cos(phi)= " + Math.cos(phi) + "Math.sin(phi)= " + Math.sin(phi));*/


        setScreen(new MenuScreen());
    }
}
