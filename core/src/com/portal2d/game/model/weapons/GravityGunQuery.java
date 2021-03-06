package com.portal2d.game.model.weapons;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.EntityType;

import static com.portal2d.game.model.ModelConstants.GRAVITY_GUN_RANGE;

/**
 * Query callback for the GravityGun to find out which objects are around it's owner and can be grabbed.
 * @see QueryCallback
 */
public class GravityGunQuery implements QueryCallback {

    private GravityGun gravityGun;
    private World world;

    // AABB
    private float lowerX;
    private float lowerY;
    private float upperX;
    private float upperY;

    public GravityGunQuery(World world, GravityGun gravityGun) {
        this.world = world;
        this.gravityGun = gravityGun;
    }

    /**
     * Checks for every entity that overlaps the AABB. If it is a box, sends a message to the GravityGun to grab it.
     */
    @Override
    public boolean reportFixture(Fixture fixture) {

        Entity entity = (Entity) fixture.getBody().getUserData();

        // Only grab boxes
        if(entity.getType().equals(EntityType.BOX) && gravityGun.canGrabEntity()) {
            gravityGun.grabEntity(entity);
            return false; //terminates the query
        }

        return true;
    }

    /** Updates the area in which objects can be grabbed */
    public void updateAABB() {
        Entity owner = gravityGun.getOwner();
        Body ownerBody = owner.getBody();

        lowerX = ownerBody.getPosition().x - owner.getType().getWidth() / 2 - GRAVITY_GUN_RANGE;
        lowerY = ownerBody.getPosition().y - owner.getType().getHeight() / 2 - GRAVITY_GUN_RANGE;
        upperX = ownerBody.getPosition().x + owner.getType().getWidth() / 2 + GRAVITY_GUN_RANGE;
        upperY = ownerBody.getPosition().y + owner.getType().getHeight() / 2 + GRAVITY_GUN_RANGE;
    }

    /**
     * Attempt to grab an object if it is in range
     * @see World#QueryAABB(QueryCallback, float, float, float, float)
     */
    public void queryAABB() {
        updateAABB();
        world.QueryAABB(this, lowerX, lowerY, upperX, upperY);
    }

    //TESTEO
    public float getLowerX() {
        return lowerX;
    }

    public float getLowerY() {
        return lowerY;
    }

    public float getUpperX() {
        return upperX;
    }

    public float getUpperY() {
        return upperY;
    }
}
