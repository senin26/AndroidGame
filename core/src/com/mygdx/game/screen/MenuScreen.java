package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import com.mygdx.game.base.Base2DScreen;

public class MenuScreen extends Base2DScreen{


    private static final float V_LEN = 0.002f;

    SpriteBatch batch;
    Texture img;
    Texture background;

    Vector2 pos;
    Vector2 v;
    Vector2 posTemp;
    Vector2 touch;
    float boundX;
    float boundY;
    float boundX;
    float boundY;
    int imgWidth = 128;
    int imgHeight = 128;

    @Override
    public void show() {
        super.show();
        background = new Texture("background.jpg");
        img = new Texture("cat.jpg");
        pos = new Vector2(-0.5f, -0.5f);
        touch = new Vector2(-0.5f, -0.5f);
        posTemp = new Vector2();
        v = new Vector2(0f, 0f);
        batch = new SpriteBatch();
        background = new Texture("background.jpg");
        img = new Texture("cat.jpg");
        pos = new Vector2(0, 0);
        v = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.5f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, -0.5f, -0.5f, 1f, 1f);
        batch.draw(img, pos.x, pos.y, 0.25f, 0.25f);
        batch.end();
        posTemp.set(touch);
        if ( ((posTemp.x-pos.x)>V_LEN || (posTemp.x-pos.x)<-V_LEN) && ((posTemp.y-pos.y>V_LEN) || (posTemp.y-pos.y)<-V_LEN) ) {
            pos.add(v);
        }
        else pos.set(touch);
        batch.draw(background, 0, 0);
        batch.draw(img, pos.x, pos.y);
        batch.end();

        if ((Gdx.graphics.getWidth() - imgWidth) > pos.x && (Gdx.graphics.getHeight() - imgHeight) > pos.y) {
            if ( ((boundX-pos.x)>0.5 || (boundX-pos.x)<-0.5) && ((boundY-pos.y>0.5) || (boundY-pos.y)<-0.5) ) {
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
    public boolean touchDown(Vector2 touch, int pointer) {
        this.touch = touch;
        boundX = touch.x;
        boundY = touch.y;
        setV(pos.x, touch.x, pos.y, touch.y);
        return super.touchDown(touch, pointer);
    }

    void setV(float x0, float x, float y0, float y){

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchDown " + screenX + " " + (Gdx.graphics.getHeight() - screenY));
        boundX = screenX;
        boundY = (Gdx.graphics.getHeight() - screenY);
        setV(pos.x, screenX, pos.y, (Gdx.graphics.getHeight() - screenY));
        return super.touchDown(screenX, screenY, pointer, button);
    }

    void setV(float x0, float x, float y0, float y){
        float dx = x-x0;
        float dy = y-y0;
        float len = (float) Math.sqrt(dx*dx + dy*dy);
        float cos;
        float sin;
        if (dx<0){
            cos = -dx/len;
        } else cos = dx/len;
        if (dy<0){
            sin = -dy/len;
        } else sin = dy/len;
        v = v.set(Math.signum(dx)*cos*V_LEN, Math.signum(dy)*sin*V_LEN);

        v = v.set(Math.signum(dx)*cos*2, Math.signum(dy)*sin*2);
    }


}
