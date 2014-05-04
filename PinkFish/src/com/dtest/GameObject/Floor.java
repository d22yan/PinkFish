package com.dtest.GameObject;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Floor extends Scrollable {

	private Rectangle hitbox;
	
	public Floor(float initialX, float initialY, int width, int height, float scrollSpeed) {
		super(initialX, initialY, width, height, scrollSpeed);
		hitbox = new Rectangle(initialX, initialY, width, height);
	}
	
	public boolean collide(Fish fish) {
		return Intersector.overlapCircleRectangle(fish.getHitboxBody() , hitbox);
	}
	
	public void update(float delta) {
		super.update(delta);
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}

}
