package com.dtest.GameObject;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {
	
	protected Vector2 position;
	protected Vector2 velocity;
	protected int width;
	protected int height;
	protected boolean isScrolledLeft;
	protected boolean isStopped;
	
	public Scrollable(float intiialX, float initialY, int width,int height, float scrollSpeed) {
		position = new Vector2(intiialX, initialY);
		velocity = new Vector2(scrollSpeed, 0);
		this.width = width;
		this.height = height;
		isScrolledLeft = false;
		isStopped = false;
	}
	
	public void reset(float newInitialX) {
		isScrolledLeft = newInitialX + width < 0;
		isStopped = false;
		position.x = newInitialX;
	}
	
	public void stop() {
		isStopped = true;
	}
	
	public void update(float delta) {
		if (isStopped) return;
		position.add(velocity.cpy().mul(delta));
		if (position.x + width < 0) {
			isScrolledLeft = true;
		}
	}
	
	public boolean isScrolledLeft() {
		return isScrolledLeft;
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
}
