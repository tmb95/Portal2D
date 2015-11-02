package com.portal2d.game.view;

/**
 *
 */
public interface ViewConstants {

    String TITLE = "Portal 2D";
    int VIEWPORT_WIDTH = 1200;
    int VIEWPORT_HEIGHT = 720;

    // scale for box2d world, pixels per meter
    float PPM = 100;

    enum TextureName {
        ANIM_GATE,
        MENU_BG,
        SPRITE_BOX,
        SPRITE_BUTTON,
        ANIM_PLAYER,
        INSTRUCTIONS_BG
    }

    enum FontName {
        FONT_80,
        FONT_40
    }

    //sizes in pixels
    int GATE_WIDTH = 40;
    int GATE_HEIGHT = 180;

    int BUTTON_WIDTH = 130;
    int BUTTON_HEIGHT = 20;

    int BOX_WIDTH = 60;
    int BOX_HEIGHT = 60;

    int PLAYER_WIDTH = 64;
    int PLAYER_HEIGHT= 64;

    //animations
    float ANIM_GATE_DELAY =  1 / 12f;
    float ANIM_PLAYER_DELAY = 1 / 12f;

    //font sizes
    int FORMAT_SIZE = 80;
    int INSTRUCTION_SIZE = 33;

    // textbutton sizes
    int TEXTBUTTON_WIDTH = 20;
    int TEXTBUTTON_HEIGHT = 20;

    int FIRST_BUTTONSTART_MENU = 600;
    int SPACE_BETWEEN_BUTTONS = 125;
    int FIRST_BUTTON_START_PAUSE = 500;

    int FIRST_BUTTON_INSTRUCTIONS_X = 100;
    int FIRST_BUTTON_INSTRUCTIONS_Y = 100;
    int INSTRUCTION_TEXT_POSITION_X = 325;
    int INSTRUCTION_TEXT_POSITION_Y = 650;

    String INSTRUCTIONS_TEXT = "Welcome to Portal2D!\n\nThis game is a puzzle style game\nwhere you will have " +
                                "to find your\nway through the map using portals.\nOnly two portals can be active\n" +
                                "simultaneously, you can exit and\nentry in both.\nBoxes can be used to activate gates\n" +
                                "(both in color RED).\nUse W,A,S,D to move, right and left \nclick to create portals, " +
                                "P to pause.\nPortals only can be created \non portable surfaces, distinguished\n" +
                                "by color WHITE.\nENJOY!";

}
