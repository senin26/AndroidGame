package com.mygdx.game.pool;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.base.SpritesPool;
import com.mygdx.game.math.Rect;
import com.mygdx.game.sprite.game.EnemyShip;

public class EnemyShipPool extends SpritesPool<EnemyShip> {

    private TextureAtlas atlas;
    private BulletPool bulletPool;

    public EnemyShipPool(TextureAtlas atlas, BulletPool bulletPool) {
        this.atlas = atlas;
        this.bulletPool = bulletPool;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(atlas, bulletPool);
    }

}
