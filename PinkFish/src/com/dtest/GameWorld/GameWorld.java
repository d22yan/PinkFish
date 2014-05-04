package com.dtest.GameWorld;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.dtest.GameConstant.GameConstant;
import com.dtest.GameConstant.GameConstant.GameState;
import com.dtest.GameObject.Fish;
import com.dtest.GameObject.Floor;
import com.dtest.GameObject.Pipe;
import com.dtest.OrangeFishHelper.AssetLoader;

public class GameWorld {
	
	private GameState currentState;
	
	private Fish fish;
	private Pipe pipe1, pipe2, pipe3;
	private Floor floor1, floor2;
	
	private int score = 0;
	
	public GameWorld(float gameMidPointY) {
		currentState = GameState.READY;
		fish = new Fish(
			GameConstant.fishInitialPosition.x, GameConstant.fishInitialPosition.y, 
			GameConstant.fishWidth, GameConstant.fishHeight
		);
		floor1 = new Floor(
			0, GameConstant.gameHeight - GameConstant.bedrockHeight - GameConstant.floorHeight,
			GameConstant.floorWidth, GameConstant.floorHeight, 
			GameConstant.floorScrollSpeed
		);
		floor2 = new Floor(
			GameConstant.floorWidth, GameConstant.gameHeight - GameConstant.bedrockHeight - GameConstant.floorHeight,
			GameConstant.floorWidth, GameConstant.floorHeight, 
			GameConstant.floorScrollSpeed
		);
		pipe1 = new Pipe(
			GameConstant.pipeInitialPosition.x, GameConstant.pipeInitialPosition.y, 
			GameConstant.pipeWidth, -1, 
			GameConstant.pipeScrollSpeed
		);
		pipe2 = new Pipe(
			pipe1.getX() + pipe1.getWidth() + GameConstant.pipeIntervalGap, GameConstant.pipeInitialPosition.y, 
			GameConstant.pipeWidth, -1,
			GameConstant.pipeScrollSpeed
		);
		pipe3 = new Pipe(
			pipe2.getX() + pipe2.getWidth() + GameConstant.pipeIntervalGap, GameConstant.pipeInitialPosition.y, 
			GameConstant.pipeWidth, -1,
			GameConstant.pipeScrollSpeed
		);
	}
	
	public void handleCollision() {
		if (pipe1.collide(fish) || pipe2.collide(fish) || pipe3.collide(fish) || floor1.collide(fish)) {
			currentState = GameState.GAMEOVER;
			if (score > AssetLoader.getHighScore()) {
				AssetLoader.setHighScore(score);
			}
			floor1.stop();
			floor2.stop();
			pipe1.stop();
			pipe2.stop();
			pipe3.stop();
			fish.die();
		}
	}
	
	public void handlePipeScored() {
		float fishHeadPositionX = fish.getX() + fish.getWidth();
		if (!pipe1.getIsScored() && pipe1.getX() + pipe1.getWidth() < fishHeadPositionX) {
			pipe1.setIsScored(true);
			score++;
		}
		if (!pipe2.getIsScored() && pipe2.getX() + pipe2.getWidth() < fishHeadPositionX) {
			pipe2.setIsScored(true);
			score++;
		}
		if (!pipe3.getIsScored() && pipe3.getX() + pipe3.getWidth() < fishHeadPositionX) {
			pipe3.setIsScored(true);
			score++;
		}
	}
	
	public void handleScrolledLeft() {
		if (floor1.isScrolledLeft()) {
			floor1.reset(floor2.getX() + floor2.getWidth());
		}
		if (floor2.isScrolledLeft()) {
			floor2.reset(floor1.getX() + floor1.getWidth());
		}
		if (pipe1.isScrolledLeft()) {
			pipe1.reset(pipe3.getX() + pipe3.getWidth() + GameConstant.pipeIntervalGap);
		}
		if (pipe2.isScrolledLeft()) {
			pipe2.reset(pipe1.getX() + pipe1.getWidth() + GameConstant.pipeIntervalGap);
		}
		if (pipe3.isScrolledLeft()) {
			pipe3.reset(pipe2.getX() + pipe2.getWidth() + GameConstant.pipeIntervalGap);
		}
	}

	public void start() {
		currentState = GameState.RUNNING;
	}
	
	public void restart() {
		currentState = GameState.READY;
		fish.reset(GameConstant.fishInitialPosition.x, GameConstant.fishInitialPosition.y);
		floor1.reset(0);
		floor2.reset(floor1.getX() + floor1.getWidth());
		pipe1.reset(GameConstant.pipeInitialPosition.x);
		pipe2.reset(pipe1.getX() + pipe1.getWidth() + GameConstant.pipeIntervalGap);
		pipe3.reset(pipe2.getX() + pipe2.getWidth() + GameConstant.pipeIntervalGap);
		score = 0;
	}
	
	public void update(float delta) {
		switch (currentState) {
			case READY:
				updateReady(delta);
				break;
			case RUNNING:
				updateRunning(delta);
				break;
			default:
				updateGameover(delta);
				break;
		}
	}
	
	public void updateReady(float delta) {
		floor1.update(delta);
		floor2.update(delta);
		handleScrolledLeft();
	}
	
	public void updateRunning(float delta) {
		fish.update(delta);
		floor1.update(delta);
		floor2.update(delta);
		pipe1.update(delta);
		pipe2.update(delta);
		pipe3.update(delta);
		
		handlePipeScored();
		handleScrolledLeft();
		handleCollision();
	}
	
	public void updateGameover(float delta) {
		fish.update(delta);
	}
	
	public Fish getFish() {
		return fish;
	}
	
	public Pipe getPipe1() {
		return pipe1;
	}
	
	public Pipe getPipe2() {
		return pipe2;
	}
	
	public Pipe getPipe3() {
		return pipe3;
	}
	
	public int getScore() {
		return score;
	}

	public Floor getFloor1() {
		return floor1;
	}
	
	public Floor getFloor2() {
		return floor2;
	}
	
	public boolean isReady() {
		return currentState == GameState.READY;
	}
	
	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

}
