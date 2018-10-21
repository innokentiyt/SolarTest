package com.solar.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SolarSystemTest extends ApplicationAdapter {
	public static Stage stage;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "SolarSystemTest";
	
	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());
		Body Sun = new Body();
		Body Earth = new Body(
		        "earth.png",
                5,
                150,
                70,
                45,
                5,
                Sun
        );

        Body Moon = new Body(
                "moon.png",
                -3,
                90,
                35,
                120,
                3,
                Earth
        );

		Body Mars = new Body(
                "mars.png",
                -3,
                200,
                65,
                195,
                5,
                Sun
        );
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		/*
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		*/
	}
	
	@Override
	public void dispose () {
		stage.dispose();
	}
}
