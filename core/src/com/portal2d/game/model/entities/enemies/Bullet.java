package com.portal2d.game.model.entities.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.Projectile;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.level.Level;

/**
 *
 */
public class Bullet extends Projectile {

    public Bullet(Level level, Body body, Vector2 velocity) {
        super(level, body, velocity);
        type = EntityType.BULLET;
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
    }

    /**
     * Overridden so bullets don't destroy each other on collision.
     */
    @Override
    public void beginInteraction(Bullet projectile) {

    }

}