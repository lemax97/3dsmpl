package P3D;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.ui.Table;


public abstract class BaseScreen implements Screen, InputProcessor {

    protected Stage3D mainStage3D;
    protected Stage uiStage;
    protected Table uiTable;

    public BaseScreen() {
        this.mainStage3D = new Stage3D();
        this.uiStage     = new Stage();

        this.uiTable     = new Table();
        uiTable.setFillParent(true);
        uiStage.addActor(uiTable);

        initialize();
    }

    public abstract void initialize();

    public abstract void update(float dt);

    // gameloop method
    public void render(float dt) {

        // limit abount of time that can pass while window is being dragged
        dt = Math.min(dt, 1/30f);

        // act methods
        uiStage.act(dt);
        mainStage3D.act(dt);

        // defined by game-specific classes
        update(dt);

        // render
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT + GL20.GL_DEPTH_BUFFER_BIT);

        // draw the graphics
        mainStage3D.draw();
        uiStage.draw();
    }

    // methods required by Screen interface
    public void resize(int width, int height){

        uiStage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {

        InputMultiplexer im = (InputMultiplexer)Gdx.input.getInputProcessor();
        im.addProcessor(this);
        im.addProcessor(uiStage);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

        InputMultiplexer im = (InputMultiplexer)Gdx.input.getInputProcessor();
        im.removeProcessor(this);
        im.removeProcessor(uiStage);
    }

    @Override
    public void dispose() {

    }

    // methods required by InputProcessor interface

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
