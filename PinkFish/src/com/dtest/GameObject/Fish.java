package com.dtest.GameObject;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.dtest.GameConstant.GameConstant;

public class Fish {
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private float rotation;
	private int width;
	private int height;
	
	private boolean isAlive;
	private boolean isSurfaced;
	
	private Circle hitboxBody;
	
	public Fish(float initialX, float initialY, int width, int height) {
		this.width = width;
		this.height = height;
		position = new Vector2(initialX, initialY);
		velocity = new Vector2(0, 0);
		acceleration = GameConstant.gravity;
		isAlive = true;
		hitboxBody = new Circle();
		isSurfaced = position.y == 0;
	}
	
	public void die() {
		isAlive = false;
		acceleration = GameConstant.dieAcceleration;
		velocity.y = 0;
	}
	
	public void onClick() {
		if (isAlive) {
			velocity.y = GameConstant.onClickSwimVelocity.y;
		}
	}
	
	public void reset(float initialX, float initialY) {
		position.x = initialX;
		position.y = initialY;
		velocity.x = 0;
		velocity.y = 0;
		acceleration = GameConstant.gravity;
		rotation = 0;
		isAlive = true;
	}
	
	public void update(float delta) {
		velocity.add(acceleration.cpy().mul(delta));
        if (velocity.y > GameConstant.maxDivingVelocityY) {
            velocity.y = GameConstant.maxDivingVelocityY;
        }
        if (!isAlive() && velocity.y < -GameConstant.maxSwimmingVelocityY) {
        	velocity.y = -GameConstant.maxSwimmingVelocityY;
        }
        
        position.add(velocity.cpy().mul(delta));
        if (position.y < 0) {
        	position.y = 0;
        	isSurfaced = true;
        }
        
        if (position.y > GameConstant.gameHeight - height) {
        	position.y = GameConstant.gameHeight - height;
        }
        
        if (isSurfaced && position.y > 0) {
        	isSurfaced = false;
        }
        
        updateHitbox();

        if (isAlive()) {
            if (velocity.y < 0) {
            	rotation -= GameConstant.swimmingRotationSpeed * delta;
            	if (rotation < GameConstant.minRotationAngle) {
            		rotation = GameConstant.minRotationAngle;
            	}
            }
            if (isDiving()) {
            	rotation += GameConstant.divingRotationSpeed * delta;
            	if (rotation > GameConstant.maxRotationAngle) {
            		rotation = GameConstant.maxRotationAngle;
            	}
            }
        } else {
        	rotation -= GameConstant.dieRotationSpeed * delta;
        	if (rotation < GameConstant.dieRotationAngle) {
        		rotation = GameConstant.dieRotationAngle;
        	}
        }
	}
	
	public void updateHitbox() {
        hitboxBody.set(
        	position.x + GameConstant.fishCenterOffset.x, 
        	position.y + GameConstant.fishCenterOffset.y, 
        	GameConstant.fishHitboxRadius
        );
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public boolean isSurfaced() {
		return isSurfaced;
	}
	
	public boolean isDiving() {
		return velocity.y > GameConstant.isDivingVelocityY;
	}
	
	public boolean isSwimming() {
		return isAlive && velocity.y < GameConstant.isSwimmingVelocityY;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public Vector2 getAcceleration() {
		return acceleration;
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Circle getHitboxBody() {
		return hitboxBody;
	}
	
}
