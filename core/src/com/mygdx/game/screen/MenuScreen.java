package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import com.mygdx.game.base.Base2DScreen;

public class MenuScreen extends Base2DScreen{

    SpriteBatch batch;
    Texture img;
    Texture background;

    Vector2 pos;
    Vector2 v;
    float boundX; // Gdx.graphics.getWidth()
    float boundY; // Gdx.graphics.getHeight()

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        background = new Texture("background.jpg");
        img = new Texture("cat.jpg");
        //img = new Texture("don.jpg");
        pos = new Vector2(0, 0);
        v = new Vector2();
        //v = new Vector2(1,1);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.5f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(img, pos.x, pos.y);
        batch.end();

       /* if (Gdx.input.getInputProcessor().keyUp(19)) {
            pos.add(0,v.y);
        }
        if (Gdx.input.getInputProcessor().keyUp(20)) {
            pos.add(0,-v.y);
        }
        else if (Gdx.input.getInputProcessor().keyUp(21)){
            pos.add(-v.x,0);
        }
        else if (Gdx.input.getInputProcessor().keyUp(22)) {
            pos.add(v.x,0);
        }*/


        if ((Gdx.graphics.getWidth() - 128) > pos.x && (Gdx.graphics.getHeight() - 128) > pos.y) {
            if (Math.abs(boundX-(pos.x))>0.5 && Math.abs(boundY-(pos.y))>0.5){
                pos.add(v);
            }
        }

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchDown " + screenX + " " + (Gdx.graphics.getHeight() - screenY));
        boundX = screenX;
        boundY = (Gdx.graphics.getHeight() - screenY);
        setV(pos.x, screenX, pos.y, (Gdx.graphics.getHeight() - screenY));
        return super.touchDown(screenX, screenY, pointer, button);
    }

    void setV(float x0, float x, float y0, float y){
        double phi;
        float dx = x-x0-64;
        float dy = y-y0-64;
        float len = (float) Math.sqrt(dx*dx + dy*dy);
        float cos;
        float sin;
        if (dx<0){
            cos = -dx/len;
        } else cos = dx/len;
        if (dy<0){
            sin = -dy/len;
        } else sin = dy/len;

        v = v.set(Math.signum(dx)*cos, Math.signum(dy)*sin);
    }


}
