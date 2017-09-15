package com.mygdx.multitouch;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class Multitouch2 extends ApplicationAdapter
		implements GestureDetector.GestureListener {

	private SpriteBatch batch;
	private BitmapFont font;
	private String message = "No gesture performed yet";
	private int w,h;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.RED);
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		GestureDetector gd = new GestureDetector(this);
		Gdx.input.setInputProcessor(gd);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		GlyphLayout layout = new GlyphLayout();
		layout.setText(font, message);
		float x = w/2 - layout.width/2;
		float y = h/2 + layout.height/2;

		font.draw(batch, message, x, y);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
//		message = "Touch down!";
//		Gdx.app.log("INFO", message);
		return true;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		message = "Tap performed, finger " + Integer.toString(button);
		//Gdx.app.log("INFO", message);
		return true;
	}

	@Override
	public boolean longPress(float x, float y) {
		message = "Long press performed";
		//Gdx.app.log("INFO", message);
		return true;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		message = "Fling performed, velocity: " +
				Float.toString(velocityX) + ", " + Float.toString(velocityY);
		//Gdx.app.log("INFO", message);
		return true;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		message = "Pan performed, delta: " +
				Float.toString(deltaX) + ", " + Float.toString(deltaY);
		//Gdx.app.log("INFO", message);
		return true;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		message = "Pan stopped.";
		//Gdx.app.log("INFO", message);
		return true;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		message = "Zoom performed, initial distance: " +
				Float.toString(initialDistance) + ", " + Float.toString(distance);
		//Gdx.app.log("INFO", message);
		return true;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		message = "Pinch performed.";
		//Gdx.app.log("INFO", message);
		return true;
	}

	@Override
	public void pinchStop() {
		message = "Pinch stopped.";
		//Gdx.app.log("INFO", message);
		return;
	}

}
