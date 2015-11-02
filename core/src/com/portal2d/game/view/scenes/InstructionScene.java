package com.portal2d.game.view.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.portal2d.game.Portal2D;
import com.portal2d.game.view.ui.Text;
import com.portal2d.game.view.ui.TextButton;

import static com.portal2d.game.view.ViewConstants.*;
import static com.portal2d.game.view.ViewConstants.TEXTBUTTON_HEIGHT;

/**
 * Created by matias on 01/11/15.
 */
public class InstructionScene extends Scene {

    private Texture background;
    private TextButton backButton;
    private Text description;

    public InstructionScene() {

        background = Portal2D.assets.getTexture(TextureName.INSTRUCTIONS_BG);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        // font setting
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("core/assets/font/font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = INSTRUCTION_SIZE; // setting font size
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        backButton = new TextButton(FIRST_BUTTON_INSTRUCTIONS_X,FIRST_BUTTON_INSTRUCTIONS_Y, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Back", font);
        description = new Text(INSTRUCTION_TEXT_POSITION_X, INSTRUCTION_TEXT_POSITION_Y, INSTRUCTIONS_TEXT, font);
    }

    @Override
    public void render(SpriteBatch batch, float mouseX, float mouseY) {

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();

        backButton.render(batch, mouseX, mouseY);
        description.render(batch, 0, 0);
    }

    public TextButton getBackButton() {
        return backButton;
    }

}
