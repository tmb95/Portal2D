 package com.portal2d.game.model.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.interactions.Spring;

import static com.portal2d.game.model.ModelConstants.GRAVITY_GUN_RANGE;
import static com.portal2d.game.model.ModelConstants.GRAVITY_GUN_SPEED;

 /**
 * Weapon that allows the owner to manipulate an object by grabbing it.
 */
public class GravityGun implements Weapon {

    protected Entity owner;

    private Spring spring;
    private Entity grabbedEntity;
    private GravityGunQuery query;

    public GravityGun(World world, Entity owner) {
        this.owner = owner;

        query = new GravityGunQuery(world, this);
        spring = new Spring(world, GRAVITY_GUN_RANGE);
    }

    /** Shoots the grabbed entity. */
    @Override
    public void actionLeftClick(Vector2 position) {
        Vector2 direction = new Vector2(position);
        direction.sub(owner.getPosition());
        direction.nor();

        grabbedEntity.setLinearVelocity(direction.scl(GRAVITY_GUN_SPEED));

        dropEntity();
    }

    @Override
    public void actionRightClick(Vector2 position) {
        //do nothing for now...
    }

    @Override
    public void update(Vector2 position) {
        if(hasEntityGrabbed()) {
            spring.update(position);
        }
    }

    /**
     * Creates a joint between the {@link #owner} and the specified entity
     * @param entity the entity to grab
     */
     public void grabEntity(Entity entity) {
        spring.setBodies(owner.getBody(), entity.getBody());

        grabbedEntity = entity;
        grabbedEntity.getBody().setGravityScale(0); // this body is no longer affected by gravity
        grabbedEntity.setSensor(true);
    }

    /** Destroys the joint between the owner and the grabbed entity. */
    public void dropEntity() {
        spring.destroy();

        grabbedEntity.getBody().setGravityScale(1); // gravity back to normal
        grabbedEntity.setSensor(false);
        grabbedEntity = null;
    }

    protected Entity getOwner() {
         return owner;
     }

    public boolean canGrabEntity() {
        return grabbedEntity == null;
    }

    public boolean hasEntityGrabbed() {
        return grabbedEntity != null;
    }

    /** Returns true if the given entity is the same as the {@link #grabbedEntity} */
    public boolean hasEntityGrabbed(Entity entity) {
        return hasEntityGrabbed() && grabbedEntity == entity;
    }

    /** @see GravityGunQuery#queryAABB() */
    public void queryAABB() {
        query.queryAABB();
    }

    //TESTEO
    public GravityGunQuery getQuery() {
        return query;
    }

}
