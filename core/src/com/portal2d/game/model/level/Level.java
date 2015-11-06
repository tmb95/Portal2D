package com.portal2d.game.model.level;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.controller.states.PlayState;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.Player;
import com.portal2d.game.model.entities.Projectile;
import com.portal2d.game.model.entities.portals.Portal;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.interactions.GameContactListener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Container for all game objects.
 */
public class Level {

    //Level properties
    private LevelName levelName;
    private LevelName nextLevel;
    private boolean finished;

    private World world;
    private TiledMap tiledMap;

    //Entities
    private Set<Entity> entities;

    // Projectiles (entities that are created at runtime)
    private Set<Projectile> projectiles;

    //Queues
    private Map<Entity, Portal> teleportQueue;
    private Set<Entity> removalQueue;
    private Set<Projectile> projectileRemovalQueue;

    //Player
    private Player player;

    // To add and remove entities at runtime
    private PlayState playState;

    public Level(World world, TiledMap tiledMap, LevelName levelName, PlayState playState) {
        this.world = world;
        this.tiledMap = tiledMap;
        this.levelName = levelName;
        this.playState = playState;

        entities = new HashSet<Entity>();
        projectiles = new HashSet<Projectile>();
        teleportQueue = new HashMap<Entity, Portal>();
        removalQueue = new HashSet<Entity>();
        projectileRemovalQueue = new HashSet<Projectile>();

        world.setContactListener(new GameContactListener());
    }

    public void update() {

        //Update player
        player.update();

        //Update all other entities
        for(Entity entity : entities) {
            entity.update();
        }

        for(Projectile projectile : projectiles) {
            projectile.update();
        }

        //Queue processing
        for(Map.Entry<Entity,Portal> entry : teleportQueue.entrySet()) {
            entry.getValue().receive(entry.getKey());
        }

        removeEntities();

        //Queue clearing
        teleportQueue.clear();
        removalQueue.clear();
        projectileRemovalQueue.clear();
    }

    public void addTeleportQueue(Entity e, Portal portal){
        teleportQueue.put(e, portal);
    }

    public void addToRemove(Entity entity) {
        removalQueue.add(entity);
    }

    public void addToRemove(Projectile projectile) {
        projectileRemovalQueue.add(projectile);
    }

    public Player getPlayer() {
        return player;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public World getWorld() {
        return world;
    }

    public void addPlayer(Player player) {
        this.player = player;
    }

    // Entity addition from level loader
    public void add(Entity entity) {
        entities.add(entity);
//        playState.add(entity);
    }

    // Entity addition at runtime
    public void add(Projectile projectile) {
        projectiles.add(projectile);
        playState.add(projectile);
    }

    public void add(Portal portal) {
        entities.add(portal);
        playState.add(portal);
    }

    private void removeEntities() {
        for(Entity entity : removalQueue) {
            Body body = entity.getBody();
            world.destroyBody(body);
            entities.remove(entity);
        }
        for(Projectile projectile : projectileRemovalQueue) {
            Body body = projectile.getBody();
            world.destroyBody(body);
            projectiles.remove(projectile);
        }
    }

    public int getWidth() {
        int tilewidth = (Integer) tiledMap.getProperties().get("tilewidth");
        int mapwidth = (Integer) tiledMap.getProperties().get("width");
        return tilewidth * mapwidth;
    }

    public int getHeight() {
        int tileheight = (Integer) tiledMap.getProperties().get("tileheight");
        int mapheight = (Integer) tiledMap.getProperties().get("height");
        return tileheight * mapheight;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public LevelName getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(LevelName nextLevel) {
        this.nextLevel = nextLevel;
    }

    public LevelName getLevelName() {
        return levelName;
    }

    public Set<Entity> getEntities() {
        return entities;
    }

}
