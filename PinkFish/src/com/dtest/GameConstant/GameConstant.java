package com.dtest.GameConstant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class GameConstant {
	
	public static final int gameWidth = 136;
	public static float gameHeight;
	public static float gameMidPointY;
	
	public enum GameState {
		READY,
		RUNNING,
		GAMEOVER
	}
	
	public static final Vector2 gravity = new Vector2(0, 460);
	
	public static final float fontSizeScaleX = 0.25f;
	public static final float fontSizeScaleY = -0.25f;
	
	public static final int bedrockHeight = 52;
	public static final int floorHeight = 11;
	public static final int floorWidth = 143;
	public static final int floorScrollSpeed = -59;
	
	public static final Vector2 fishInitialPosition = new Vector2(33, 0);
	public static final int fishWidth = 26;
	public static final int fishHeight = 12;
	public static final int fishHitboxRadius = 6;
	public static final Vector2 fishCenterOffset = new Vector2(15, 6);
		
	public static final int isDivingVelocityY = 50;
	public static final int isSwimmingVelocityY = 10;
	public static final int maxDivingVelocityY = 100;
	public static final int maxSwimmingVelocityY = 100;

	public static final Vector2 onClickSwimVelocity = new Vector2(0, -140);
	public static final int divingRotationSpeed = 100;
	public static final int swimmingRotationSpeed = 100;
	public static final int maxRotationAngle = 10;
	public static final int minRotationAngle = -10;
	public static final int dieRotationAngle = -180;
	public static final int dieRotationSpeed = 720;
	public static final Vector2 dieAcceleration = new Vector2(0, -300);
	
	public static final Vector2 pipeInitialPosition = new Vector2(210, 0);
	public static final int pipeWidth = 22;
	public static final int pipeHeight = 22;
	public static final int pipeEndWidth = 24;
	public static final int pipeEndHeight = 11;
	public static final int pipeScrollSpeed = -59;
	public static final int pipeIntervalGap = 75;
	public static final int pipeThroughGap = 35;
	public static final int pipeMinHeight = 15;
	public static final int pipeRandomRange = 90;
	
	public static final int scoreOffsetY = 12;
	public static final int shadowOffset = 1;
	
	public static final String StringHighScore = "high score";
	public static final String StringGameOver = "game over";
	public static final String StringTouchMe = "touch me";
	public static final String StringTryAgain = "try again";
	
	public static final String PreferencesFileName = "PinkFish";
	public static final String PreferencesHighScore = "highScore";
		
	public static void defineGameConstant(float gdxGraphicsWidth, float gdxGraphicsHeight) {
		gameHeight = gdxGraphicsHeight * gameWidth / gdxGraphicsWidth;
		gameMidPointY = gameHeight / 2;
		fishInitialPosition.y = gameMidPointY - 5;
	}
	
}
	
