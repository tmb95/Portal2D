package com.portal2d.game.model.level;

import java.util.NoSuchElementException;

/**
 *
 */
public enum LevelName {

    TEST_LEVEL,

    //names will be funnier
    LEVEL_1,
    LEVEL_2,
    LEVEL_3,
    LEVEL_4,
    LEVEL_5;

    /**
     * This method is used by the {@link com.portal2d.game.controller.LevelLoader} to create Exits.
     */
    public static LevelName getLevelName(int levelNumber) {
        for(LevelName levelName : values()) {
            if(levelName.ordinal() == levelNumber)
                return levelName;
        }

        throw new NoSuchElementException();
    }
}
