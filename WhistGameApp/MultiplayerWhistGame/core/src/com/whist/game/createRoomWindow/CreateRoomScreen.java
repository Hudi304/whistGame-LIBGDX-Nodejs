package com.whist.game.createRoomWindow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.ray3k.stripe.scenecomposer.SceneComposerStageBuilder;
import com.whist.game.StartClient;
import com.whist.game.utils.Constants;

public class CreateRoomScreen implements Screen {
    Stage stage;
    Skin skin;
    StartClient mainController;
    private final String TAG = CreateRoomScreen.class.getSimpleName();



    public CreateRoomScreen(StartClient mainController) {
        this.mainController = mainController;


    }

    @Override
    public void show() {
        stage = new Stage(new ExtendViewport(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT));
        skin = new Skin(Gdx.files.internal("skin.json"));

        SceneComposerStageBuilder builder = new SceneComposerStageBuilder();
        builder.build(stage,skin,Gdx.files.internal("createRoomScene.json"));

        Gdx.input.setInputProcessor(stage);

        VerticalGroup vBox = stage.getRoot().findActor("vBox");
        final TextField nicknameField = vBox.findActor("nickname");
        final TextField roomField = vBox.findActor("room");
        TextButton createRoomBtn = vBox.findActor("createRoom");
        TextButton backBtn = vBox.findActor("back");


        backBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainController.goToMainMenu();
            }
        });

        createRoomBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String nickname = nicknameField.getText();
                String room = roomField.getText();
                Gdx.app.log(TAG,"Nickname: " + nickname + "\t\t"  + "Room: " + room);
                mainController.createRoom(nickname,room);
                //mainController.goToLobby();
            }
        });




    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


}