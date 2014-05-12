package com.dtest.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.dtest.GameConstant.GameConstant;
import com.dtest.GameWorld.GameRenderer;
import com.dtest.GameWorld.GameWorld;
import com.dtest.PinkFishHelper.InputHandler;

public class GameScreen implements Screen {

	private GameWorld gameWorld;
	private GameRenderer gameRenderer;
	private float runTime;
	
	public GameScreen() {
		GameConstant.defineGameConstant(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		gameWorld = new GameWorld(GameConstant.gameMidPointY);
		gameRenderer = new GameRenderer(gameWorld);
		Gdx.input.setInputProcessor(new InputHandler(gameWorld));
	}
	
	@Override
	public void render(float delta) {
		runTime += delta;
		gameWorld.update(delta);
		gameRenderer.render(runTime);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
