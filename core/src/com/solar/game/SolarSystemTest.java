package com.solar.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SolarSystemTest extends ApplicationAdapter {
	public static Stage stage;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 640;
	public static final String TITLE = "SolarSystemTest";
    public static boolean pause = false;
	
	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Body Sun = new Body();
		Body Earth = new Body(
		        "earth.png",
                16,
                130,
                70,
                0,
                60,
                Sun
        );

        Body Moon = new Body(
                "moon.png",
                -15,
                60,
                35,
                50,
                100,
                Earth
        );

        /*Body MiniMoon = new Body(
                "moon.png",
                -15,
                25,
                5,
                50,
                100,
                Moon
        );*/

		Body Mars = new Body(
                "mars.png",
                -8,
                270,
                65,
                180,
                30,
                Sun
        );

		//кнопка паузы/возобновления
        Image Button = new Image(new Texture(Gdx.files.internal("button.png")));
        Button.setWidth(50);
        Button.setHeight(50);
        Button.setPosition(20,20);
        Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.printf("Нажатие! ");
                pause = !pause;
            }
        });
        stage.addActor(Button);
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(pause == false) stage.act();
		stage.draw();
	}
	
	@Override
	public void dispose () {
		stage.dispose();
	}
}
