package com.dtest.OrangeFishHelper;

import com.badlogic.gdx.InputProcessor;
import com.dtest.GameObject.Fish;
import com.dtest.GameWorld.GameWorld;

public class InputHandler implements InputProcessor {
	
	private Fish fish;
	private GameWorld gameWorld;
	
	public InputHandler(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
		this.fish = gameWorld.getFish();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (gameWorld.isReady()) {
			gameWorld.start();
		}
		
		fish.onClick();
		
		if (gameWorld.canRestart()) {
			gameWorld.restart();
		}
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
