package com.dtest.GameObject;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.dtest.GameConstant.GameConstant;

public class Pipe extends Scrollable {
	
	private Random random;
	
	private boolean isScored;
	
	private Rectangle hitboxPipe;
	private Rectangle hitboxPipeInverse;
	private Rectangle hitboxPipeEnd;
	private Rectangle hitboxPipeEndInverse;
	
	public Pipe(float initialX, float initialY, int newWidth, int newHeight, float scrollSpeed) {
		super(initialX, initialY, newWidth, newHeight, scrollSpeed);
		isScored = false;
		random = new Random();
		if (newHeight < 0 ) {
			super.height = random.nextInt(GameConstant.pipeRandomRange) + GameConstant.pipeMinHeight;
		}
		hitboxPipe = new Rectangle(
			position.x, position.y, 
			width, height
		);
		hitboxPipeInverse = new Rectangle(
			position.x, position.y + height + GameConstant.pipeThroughGap + GameConstant.pipeEndHeight,
			width, GameConstant.gameHeight - height - GameConstant.pipeThroughGap
		);
		hitboxPipeEnd = new Rectangle(
			position.x - 1, position.y + height,
			GameConstant.pipeEndWidth, GameConstant.pipeEndHeight
		);
		hitboxPipeEndInverse = new Rectangle(
			position.x - 1, position.y + height + GameConstant.pipeEndHeight + GameConstant.pipeThroughGap,
			GameConstant.pipeEndWidth, GameConstant.pipeEndHeight
		);
	}
	
	public boolean collide(Fish fish) {
		if (position.x < fish.getX() + fish.getWidth()) {
			return 
				Intersector.overlapCircleRectangle(fish.getHitboxBody(), hitboxPipe) ||
				Intersector.overlapCircleRectangle(fish.getHitboxBody(), hitboxPipeEnd) ||
				Intersector.overlapCircleRectangle(fish.getHitboxBody(), hitboxPipeInverse) ||
				Intersector.overlapCircleRectangle(fish.getHitboxBody(), hitboxPipeEndInverse);
		}
		return false;
	}
	
    @Override
	public void reset(float newPointX) {
		super.reset(newPointX);
		isScored = false;
		height = random.nextInt(GameConstant.pipeRandomRange) + GameConstant.pipeMinHeight;
		updateHitbox();
	}
    
    @Override
    public void update(float delta) {
    	super.update(delta);
    	updateHitbox();
    }
    
    public void updateHitbox() {
    	hitboxPipe.set(
			position.x, position.y, 
			width, height
		);
    	hitboxPipeEnd.set(
    		position.x - 1, position.y + height,
    		GameConstant.pipeEndWidth, GameConstant.pipeEndHeight
    	);
    	hitboxPipeInverse.set(			
    		position.x, position.y + height + GameConstant.pipeThroughGap + 2 * GameConstant.pipeEndHeight,
    		width, GameConstant.gameHeight - height - GameConstant.pipeThroughGap
    	);
    	hitboxPipeEndInverse.set(
    		position.x - 1, position.y + height + GameConstant.pipeEndHeight + GameConstant.pipeThroughGap,
    		GameConstant.pipeEndWidth, GameConstant.pipeEndHeight
    	);
    }
    
    public Rectangle getHitboxPipe() {
    	return hitboxPipe;
    }
    
    public Rectangle getHitboxPipeEnd() {
    	return hitboxPipeEnd;
    }
    
    public Rectangle getHitboxPipeInverse() {
    	return hitboxPipeInverse;
    }

    public Rectangle getHitboxPipeEndInverse() {
    	return hitboxPipeEndInverse;
    }

	public boolean getIsScored() {
		return isScored;
	}
	
	public void setIsScored(boolean isScored) {
		this.isScored = isScored; 
	}
}
