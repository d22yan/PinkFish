package com.dtest.PinkFish;

import com.badlogic.gdx.Game;
import com.dtest.PinkFishHelper.AssetLoader;
import com.dtest.Screen.GameScreen;

public class PinkFishGame extends Game {

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new GameScreen());
	}
	
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
