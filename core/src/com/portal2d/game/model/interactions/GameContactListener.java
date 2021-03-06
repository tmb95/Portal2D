package com.portal2d.game.model.interactions;

import com.badlogic.gdx.physics.box2d.*;
import com.portal2d.game.model.entities.Entity;

/**
 * Helper class for informing interactions between two entities.
 * @see ContactListener
 */
public class GameContactListener implements ContactListener {

    private static GameContactListener instance;

    private GameContactListener() {

    }

    public static GameContactListener getInstance() {
        if(instance == null) {
            instance = new GameContactListener();
        }
        return instance;
    }

    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        if(bodyA.getUserData() == null || bodyB.getUserData() == null){
            return;
        }

        Entity e1 = (Entity)bodyA.getUserData();
        Entity e2 = (Entity)bodyB.getUserData();

        e1.beginInteraction(e2);
        e2.beginInteraction(e1);
    }

    @Override
    public void endContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        if(bodyA.getUserData()== null || bodyB.getUserData() == null){
            return;
        }

        Entity e1 = (Entity)bodyA.getUserData();
        Entity e2 = (Entity)bodyB.getUserData();

        e1.endInteraction(e2);
        e2.endInteraction(e1);
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
