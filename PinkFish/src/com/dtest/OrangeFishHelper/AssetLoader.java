package com.dtest.OrangeFishHelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dtest.GameConstant.GameConstant;

public class AssetLoader {
    public static Texture texture;
    
    public static Animation fishAnimation;
    public static TextureRegion fishAnimateDown, fishAnimateUp, background, bedrock, floor, pipe, pipeEnd;

    public static BitmapFont font, shadow;
    
    public static Preferences preference;
    
    public static void load() {
    	loadGraphics();
    	loadPreferences();
    }
    
    public static void loadGraphics() {
    	texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        background = new TextureRegion(texture, 0, 0, 136, 85);
        background.flip(false, true);

        fishAnimateUp = new TextureRegion(texture, 137, 0, 26, 12);
        fishAnimateUp.flip(false, true);

        fishAnimateDown = new TextureRegion(texture, 137, 12, 26, 12);
        fishAnimateDown.flip(false, true);

        TextureRegion[] birds = {fishAnimateUp, fishAnimateDown};
        fishAnimation = new Animation(0.15f, birds);
        fishAnimation.setPlayMode(Animation.LOOP_PINGPONG);
        
        pipe = new TextureRegion(texture, 165, 11 ,22 ,1);
        pipeEnd = new TextureRegion(texture, 164, 0, 24, 11);
        
        bedrock = new TextureRegion(texture, 0, 95, 143, 1);
        floor = new TextureRegion(texture, 0, 85, 143, 10);
        floor.flip(false, true);
        
        font = new BitmapFont(Gdx.files.internal("data/text.fnt"), false);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"), false);
        font.setScale(GameConstant.fontSizeScaleX, GameConstant.fontSizeScaleY);
        shadow.setScale(GameConstant.fontSizeScaleX, GameConstant.fontSizeScaleY);
    }
    
    public static void loadPreferences() {
    	preference = Gdx.app.getPreferences(GameConstant.PreferencesFileName);
        
        if (!preference.contains(GameConstant.PreferencesHighScore)) {
        	preference.putInteger(GameConstant.PreferencesHighScore, 0);
        	preference.flush();
        }
    }
    
    public static void setHighScore(int newHighScore) {
    	preference.putInteger(GameConstant.PreferencesHighScore, newHighScore);
    	preference.flush();
    }
    
    public static int getHighScore() {
    	return preference.getInteger(GameConstant.PreferencesHighScore);
    }
        
    public static void dispose() {
        texture.dispose();
    }
}
