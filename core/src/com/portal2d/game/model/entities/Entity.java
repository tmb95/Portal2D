package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.interactions.EntityType;

/**
 * Base class for all game entities.
 */
public abstract class Entity {

    protected Body body;
    protected World world;

    protected EntityType type;

    protected Entity(World world, Body body) {
        this.world = world;
        this.body = body;
    }

    public Body getBody() {
        return body;
    }
    public EntityType getType() {
        return type;
    }

    public void interact(Entity entity) {
        entity.type.interact(this, entity);
    }

    public void interact (Box box) {

    }

    public void interact (Player player) {

    }
    
    public void interact (Exit exit) {

    }

    public void interact (Button button) {

    }

    public void interact (Tile tile) {

    }

}
