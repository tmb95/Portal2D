package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 */
public abstract class Entity {

    protected Body body;
    protected World world;

    protected Entity(World world, Body body) {
        this.world = world;
        this.body = body;
    }

    public Body getBody() {
        return body;
    }
}
