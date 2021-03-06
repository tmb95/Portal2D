package com.portal2d.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Disposable;
import com.portal2d.game.model.level.LevelName;

import java.util.HashMap;
import java.util.Map;

import static com.portal2d.game.view.ViewConstants.FontName;
import static com.portal2d.game.view.ViewConstants.TextureName;

/**
 * Class that manages game resources (images, tmx files, etc)
 */
public class Assets implements Disposable {

    //tiled maps
    private Map<LevelName, TiledMap> tiledMaps;

    //textures
    private Map<TextureName, Texture> textures;

    //fonts
    private Map<FontName, BitmapFont> fonts;

    public Assets() {
        tiledMaps = new HashMap<LevelName, TiledMap>();
        textures = new HashMap<TextureName, Texture>();
        fonts = new HashMap<FontName, BitmapFont>();
    }

    public void loadTiledMaps() {
        TmxMapLoader tmxMapLoader = new TmxMapLoader();

        tiledMaps.put(LevelName.LEVEL_0, tmxMapLoader.load("core/assets/levels/level_0.tmx"));
        tiledMaps.put(LevelName.LEVEL_1, tmxMapLoader.load("core/assets/levels/level_1.tmx"));
        tiledMaps.put(LevelName.LEVEL_2, tmxMapLoader.load("core/assets/levels/level_2.tmx"));
        tiledMaps.put(LevelName.LEVEL_3, tmxMapLoader.load("core/assets/levels/level_3.tmx"));
        tiledMaps.put(LevelName.LEVEL_4, tmxMapLoader.load("core/assets/levels/level_4.tmx"));
        tiledMaps.put(LevelName.LEVEL_5, tmxMapLoader.load("core/assets/levels/level_5.tmx"));
        tiledMaps.put(LevelName.LEVEL_6, tmxMapLoader.load("core/assets/levels/level_6.tmx"));
    }

    public void loadTextures() {
        textures.put(TextureName.MENU_BG, new Texture(Gdx.files.internal("core/assets/backgrounds/menu.png")));
        textures.put(TextureName.SPRITE_BOX, new Texture(Gdx.files.internal("core/assets/sprites/box.png")));
        textures.put(TextureName.ANIM_GATE, new Texture(Gdx.files.internal("core/assets/sprites/gate.png")));
        textures.put(TextureName.SPRITE_BUTTON, new Texture(Gdx.files.internal("core/assets/sprites/button.png")));
        textures.put(TextureName.ANIM_PLAYER, new Texture(Gdx.files.internal("core/assets/sprites/player.png")));
        textures.put(TextureName.GAME_CURSOR, new Texture(Gdx.files.internal("core/assets/sprites/cursor.png")));
        textures.put(TextureName.BLUE_PORTAL, new Texture(Gdx.files.internal("core/assets/sprites/blue_portal.png")));
        textures.put(TextureName.ORANGE_PORTAL, new Texture(Gdx.files.internal("core/assets/sprites/orange_portal.png")));
        textures.put(TextureName.SPRITE_TURRET, new Texture(Gdx.files.internal("core/assets/sprites/turret.png")));
        textures.put(TextureName.SPRITE_BULLET, new Texture(Gdx.files.internal("core/assets/sprites/potato_bullet.png")));
    }

    public void createFonts() {
        fonts.put(FontName.PORTAL, createFont("core/assets/fonts/Portal.ttf", 60));
        fonts.put(FontName.PORTAL_33, createFont("core/assets/fonts/Portal.ttf", 33));

        fonts.put(FontName.DINM, createFont("core/assets/fonts/DINM.ttf", 60));
        fonts.put(FontName.DINM_33, createFont("core/assets/fonts/DINM.ttf", 33));

        fonts.put(FontName.DINB, createFont("core/assets/fonts/DINB.ttf", 60));
        fonts.put(FontName.DINB_33, createFont("core/assets/fonts/DINB.ttf", 33));

        fonts.put(FontName.MOLOT, createFont("core/assets/fonts/Molot.otf", 60));
        fonts.put(FontName.MOLOT_33, createFont("core/assets/fonts/Molot.otf", 33));
    }

    private BitmapFont createFont(String path, int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        return font;
    }

    public TiledMap getTiledMap(LevelName key) {
        return tiledMaps.get(key);
    }

    public Texture getTexture(TextureName key) {
        return textures.get(key);
    }

    public BitmapFont getFont(FontName key) {
        return fonts.get(key);
    }

    @Override
    public void dispose() {
        for(TiledMap tiledMap : tiledMaps.values()) {
            tiledMap.dispose();
        }

        for(Texture texture : textures.values()) {
            texture.dispose();
        }

        for(BitmapFont font : fonts.values()) {
            font.dispose();
        }
    }
}
