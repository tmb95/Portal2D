package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.level.LevelName;
import com.portal2d.game.model.interactions.EntityType;

/**
 * An entity the player has to get to to change the current level.
 */
public class Exit extends StaticEntity {

    private LevelName destinyLevel;
    private boolean reached;

    public Exit(Level level, Body body, LevelName destinyLevel) {
        super(level, body);
        this.destinyLevel = destinyLevel;
        type = EntityType.EXIT;
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
    }

    @Override
    public void beginInteraction(Player player) {
        level.setFinished(true);
        level.setNextLevel(destinyLevel);
        reached = true;
    }

    public boolean isReached() {
        return reached;
    }

}
