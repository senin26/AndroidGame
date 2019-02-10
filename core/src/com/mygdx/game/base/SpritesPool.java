package com.mygdx.game.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.math.Rect;

import java.util.ArrayList;
import java.util.List;

public abstract class SpritesPool<T extends Sprite> {
    protected List<T> activeObjects = new ArrayList<T>();
    protected List<T> freeObjects = new ArrayList<T>();

    protected abstract T newObject();

    public T obtain(){
        T object;
        if (freeObjects.isEmpty()){
            object = newObject();
        } else {
            object = freeObjects.remove(freeObjects.size()-1);
        }
        activeObjects.add(object);
        return object;
    }

    public void updateActiveSprites(float delta){
        for (int i = 0; i < activeObjects.size(); i++) {
            T sprite = activeObjects.get(i);
            sprite.update(delta);
        }
    }

    public void drawActiveSprites(SpriteBatch batch){
        for (int i = 0; i < activeObjects.size(); i++) {
            T sprite = activeObjects.get(i);
            sprite.draw(batch);
        }
    }

    public void resizeActiveSprites(Rect worldBounds) {
        for (int i = 0; i < activeObjects.size(); i++) {
            T sprite = activeObjects.get(i);
            sprite.resize(worldBounds);
        }
    }

    public void freeAllDestroyedActiveSprites(){
        for (int i = 0; i < activeObjects.size(); i++) {
            T sprite = activeObjects.get(i);
            if (sprite.isDestroyed()) {
                free(sprite);
                i--;
            }
        }
    }

    private void free(T object){
        activeObjects.remove(object);
        freeObjects.add(object);
        object.flushDestroy();
        System.out.println("active/free " + activeObjects.size() + "/" + freeObjects.size());
    }

    public List<T> getActiveObjects() {
        return activeObjects;
    }

    public void dispose(){
        activeObjects.clear();
        freeObjects.clear();
    }
}
