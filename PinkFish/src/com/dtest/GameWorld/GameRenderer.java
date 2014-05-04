package com.dtest.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.dtest.GameConstant.GameConstant;
import com.dtest.GameObject.Fish;
import com.dtest.GameObject.Floor;
import com.dtest.GameObject.Pipe;
import com.dtest.OrangeFishHelper.AssetLoader;

public class GameRenderer {
	
	private GameWorld gameWorld;
	private OrthographicCamera orthographicCamera;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteBatch;
	
    private Fish fish;
    private Animation fishAnimation;
    private TextureRegion background;
    private TextureRegion fishAnimateDown, fishAnimateUp;
    
    private Pipe pipe1, pipe2, pipe3;
    private TextureRegion pipe;
    private TextureRegion pipeEnd;
    
    private Floor floor1, floor2;
    private TextureRegion floorTexture, bedrockTexture;
    
    public GameRenderer(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
		orthographicCamera = new OrthographicCamera();
		orthographicCamera.setToOrtho(true, GameConstant.gameWidth, GameConstant.gameHeight);
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(orthographicCamera.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(orthographicCamera.combined);

		initializeGameObjects();
		initializeAssets();
	}
	
	public void initializeGameObjects() {
		fish = gameWorld.getFish();
		floor1 = gameWorld.getFloor1();
		floor2 = gameWorld.getFloor2();
		pipe1 = gameWorld.getPipe1();
		pipe2 = gameWorld.getPipe2();
		pipe3 = gameWorld.getPipe3();
	}
	
	public void initializeAssets() {
		background = AssetLoader.background;
		bedrockTexture = AssetLoader.bedrock;
		floorTexture = AssetLoader.floor;
		fishAnimation = AssetLoader.fishAnimation;
		fishAnimateDown = AssetLoader.fishAnimateDown;
		fishAnimateUp = AssetLoader.fishAnimateUp;
		pipe = AssetLoader.pipe;
		pipeEnd = AssetLoader.pipeEnd;
	}
	
	public void drawBackground() {
        shapeRenderer.begin(ShapeType.FilledRectangle);
        shapeRenderer.setColor(new Color(255 / 255.0f, 255 / 255.0f, 255 / 255.0f, 1));
//        shapeRenderer.setColor(new Color(4 / 255.0f, 32 / 255.0f, 54 / 255.0f, 1));
        shapeRenderer.filledRect(0, 0, 136, GameConstant.gameHeight);
        shapeRenderer.end();
	}
	
	public void drawBedrock() {
        spriteBatch.begin();
        spriteBatch.enableBlending();
        spriteBatch.draw(
        	bedrockTexture,
        	0, GameConstant.gameHeight - GameConstant.bedrockHeight,
        	GameConstant.gameWidth, GameConstant.bedrockHeight
        );
        spriteBatch.end();
	}
	
	public void drawFish(float runTime) {
        spriteBatch.begin();
        spriteBatch.enableBlending();
        if (fish.isSwimming()) {
	    	spriteBatch.draw(
	    		fishAnimation.getKeyFrame(runTime), 
	    		fish.getX(), fish.getY(), 
	    		fish.getWidth() / 2.0f, fish.getHeight() / 2.0f,
	    		fish.getWidth(), fish.getHeight(), 1, 1,
	    		fish.getRotation()
	    	);
        } else {
        	spriteBatch.draw(
	    		fishAnimateUp, 
	    		fish.getX(), fish.getY(), 
	    		fish.getWidth() / 2.0f, fish.getHeight() / 2.0f,
	    		fish.getWidth(), fish.getHeight(), 1, 1,
	    		fish.getRotation()
	    	);
        }
        spriteBatch.end();
    }
	
	public void drawFloor() {
		spriteBatch.begin();
		spriteBatch.enableBlending();
		spriteBatch.draw(
			floorTexture,
			floor1.getX(), floor1.getY(),
			floor1.getWidth(), floor1.getHeight()
		);
		spriteBatch.draw(
			floorTexture,
			floor2.getX(), floor2.getY(),
			floor2.getWidth(), floor2.getHeight()
		);
		spriteBatch.end();
	}
	
	public void drawIsReady() {
		spriteBatch.begin();
    	AssetLoader.shadow.draw(
    		spriteBatch, GameConstant.StringTouchMe, 
    		(GameConstant.gameWidth / 2) - 41 + GameConstant.shadowOffset, 75 + GameConstant.shadowOffset
    	);
    	AssetLoader.font.draw(
    		spriteBatch, GameConstant.StringTouchMe,
    		(GameConstant.gameWidth / 2) - 41, 75
    	);
    	spriteBatch.end();
	}
	
	public void drawIsGameOver() {
		spriteBatch.begin();
		AssetLoader.shadow.draw(
			spriteBatch,
			GameConstant.StringGameOver,
			24 + GameConstant.shadowOffset, 55 + GameConstant.shadowOffset
		);
		AssetLoader.font.draw(
			spriteBatch,
			GameConstant.StringGameOver,
			24, 55
		);
		AssetLoader.shadow.draw(
			spriteBatch, 
			GameConstant.StringTryAgain,
			24 + GameConstant.shadowOffset, 75 + GameConstant.shadowOffset
		);
		AssetLoader.font.draw(
			spriteBatch,
			GameConstant.StringTryAgain,
			24, 75
		);
		
		String highScore = "" + AssetLoader.getHighScore();
		
		AssetLoader.shadow.draw(
			spriteBatch, 
			GameConstant.StringHighScore + ":", 
			22 + GameConstant.shadowOffset, 105 + GameConstant.shadowOffset
		);
        AssetLoader.font.draw(
        	spriteBatch, 
        	GameConstant.StringHighScore + ":", 
        	22, 105
        );
		AssetLoader.shadow.draw(
			spriteBatch,
			highScore,
			(GameConstant.gameWidth / 2) - (3 * highScore.length() + GameConstant.shadowOffset), 127 + GameConstant.shadowOffset
		);
		AssetLoader.font.draw(
			spriteBatch, 
			highScore,
			(GameConstant.gameWidth / 2) - (3 * highScore.length()), 127
		);
		
		spriteBatch.end();
	}
	
	public void drawPipe() {
		spriteBatch.begin();
		spriteBatch.enableBlending();
		spriteBatch.draw(
			pipe, 
			pipe1.getX(), pipe1.getY(),
			pipe1.getWidth(), pipe1.getHeight()
		);
		spriteBatch.draw(
			pipeEnd,
			pipe1.getHitboxPipeEnd().x, pipe1.getHitboxPipeEnd().y, 
			pipe1.getHitboxPipeEnd().width, pipe1.getHitboxPipeEnd().height
		);
		spriteBatch.draw(
			pipe,
			pipe1.getX(), pipe1.getY() + pipe1.getHeight() + GameConstant.pipeThroughGap + 2 * GameConstant.pipeEndHeight,
			pipe1.getWidth(), GameConstant.gameHeight - pipe1.getHeight() - GameConstant.pipeThroughGap - 2 * GameConstant.pipeEndHeight
		);
		spriteBatch.draw(
			pipeEnd,
			pipe1.getHitboxPipeEndInverse().x, pipe1.getHitboxPipeEndInverse().y, 
			pipe1.getHitboxPipeEndInverse().width, pipe1.getHitboxPipeEndInverse().height
		);
		spriteBatch.draw(
			pipe, 
			pipe2.getX(), pipe2.getY(),
			pipe2.getWidth(), pipe2.getHeight()
		);
		spriteBatch.draw(
			pipeEnd,
			pipe2.getHitboxPipeEnd().x, pipe2.getHitboxPipeEnd().y, 
			pipe2.getHitboxPipeEnd().width, pipe2.getHitboxPipeEnd().height
		);
		spriteBatch.draw(
			pipe,
			pipe2.getX(), pipe2.getY() + pipe2.getHeight() + GameConstant.pipeThroughGap + 2 * GameConstant.pipeEndHeight,
			pipe2.getWidth(), GameConstant.gameHeight - pipe2.getHeight() - GameConstant.pipeThroughGap - 2 * GameConstant.pipeEndHeight
		);
		spriteBatch.draw(
			pipeEnd,
			pipe2.getHitboxPipeEndInverse().x, pipe2.getHitboxPipeEndInverse().y, 
			pipe2.getHitboxPipeEndInverse().width, pipe2.getHitboxPipeEndInverse().height
		);
		spriteBatch.draw(
			pipe, 
			pipe3.getX(), pipe3.getY(),
			pipe3.getWidth(), pipe3.getHeight()
		);
		spriteBatch.draw(
			pipeEnd,
			pipe3.getHitboxPipeEnd().x, pipe3.getHitboxPipeEnd().y, 
			pipe3.getHitboxPipeEnd().width, pipe3.getHitboxPipeEnd().height
		);
		spriteBatch.draw(
			pipe,
			pipe3.getX(), pipe3.getY() + pipe3.getHeight() + GameConstant.pipeThroughGap + 2 * GameConstant.pipeEndHeight,
			pipe3.getWidth(), GameConstant.gameHeight - pipe3.getHeight() - GameConstant.pipeThroughGap - 2 * GameConstant.pipeEndHeight
		);
		spriteBatch.draw(
			pipeEnd,
			pipe3.getHitboxPipeEndInverse().x, pipe3.getHitboxPipeEndInverse().y, 
			pipe3.getHitboxPipeEndInverse().width, pipe3.getHitboxPipeEndInverse().height
		);
        spriteBatch.end();
	}
	
	public void drawScore() {
		String score = "" + gameWorld.getScore();
		spriteBatch.begin();
		AssetLoader.shadow.draw(spriteBatch, score, (GameConstant.gameWidth / 2) - (3 * score.length()+ GameConstant.shadowOffset), GameConstant.scoreOffsetY + GameConstant.shadowOffset);
		AssetLoader.font.draw(spriteBatch, score, (GameConstant.gameWidth / 2) - (3 * score.length()), GameConstant.scoreOffsetY);
		spriteBatch.end();
	}
	
	public void render(float runTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        drawBackground();
        drawPipe();
        drawBedrock();
        drawFloor();
        drawFish(runTime);
        
        if (gameWorld.isReady()) {
        	drawIsReady();
        } else {
        	if (gameWorld.isGameOver()) {
        		drawIsGameOver();
        	}
        	drawScore();
        }
        
//        shapeRenderer.begin(ShapeType.FilledRectangle);
//        shapeRenderer.setColor(Color.RED);
//        shapeRenderer.filledRect(
//        	floor.getHitbox().x, floor.getHitbox().y, 
//        	floor.getHitbox().width, floor.getHitbox().height
//        );
//        shapeRenderer.end();
    }
}
