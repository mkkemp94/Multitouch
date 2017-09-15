package com.mygdx.multitouch;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.Map;

public class Multitouch extends ApplicationAdapter
		implements InputProcessor {

	private SpriteBatch batch;
	private BitmapFont font;
	private String message = "Touch something already!";
	private int w,h;

	class TouchInfo {
		public float touchX = 0;
		public float touchY = 0;
		public boolean touched = false;
	}

	private Map<Integer, TouchInfo> touches = new HashMap<Integer, TouchInfo>();
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.RED);
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		Gdx.input.setInputProcessor(this);
		for (int i = 0; i < 5; i++) {
			touches.put(i, new TouchInfo());
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		message = "";
		for (int i = 0; i < 5; i++) {
			if (touches.get(i).touched) {
				message += "Finger: " + Integer.toString(i) + " touch at: " +
						Float.toString(touches.get(i).touchX) + ", " +
						Float.toString(touches.get(i).touchY) + "\n";
			}
		}

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
	public boolean keyDown(int keycode) {
		message = "Key Down: " + keycode;
		Gdx.app.log("INFO", message);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		message = "Key Up: " + keycode;
		Gdx.app.log("INFO", message);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		message = "Key Typed: " + character;
		Gdx.app.log("INFO", message);
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (pointer < 5) {
			touches.get(pointer).touchX = screenX;
			touches.get(pointer).touchY = screenY;
			touches.get(pointer).touched = true;
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (pointer < 5) {
			touches.get(pointer).touchX = 0;
			touches.get(pointer).touchY = 0;
			touches.get(pointer).touched = false;
		}
		return true;
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
