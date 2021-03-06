package com.portal2d.game.controller.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.controller.GameStateManager;
import com.portal2d.game.view.scenes.InstructionScene;

/**
 *
 */
public class InstructionState extends GameState {

    private InstructionScene scene;

    public InstructionState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void entered() {
        scene = new InstructionScene();
    }

    @Override
    public void handleInput() {
        unproject(scene.getCamera());

        if(scene.getBackButton().isClicked(mouse.x, mouse.y)) {
            gsm.pop();
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(scene.getCamera().combined);
        scene.render(batch, mouse.x, mouse.y);
    }

}
