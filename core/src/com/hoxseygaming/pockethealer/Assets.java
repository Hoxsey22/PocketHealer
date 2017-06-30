package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.hoxseygaming.pockethealer.encounters.spells.Spell;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/26/2017.
 */
public class Assets {

    public AssetManager manager;
    public ArrayList<Vector2> raidPositions;
    /*
    public Texture dpsIconImage = new Texture("dps_role_icon.png");
    public Texture healerIconImage = new Texture("healer_role_icon.png");
    public Texture tankIconImage = new Texture("tank_role_icon.png");

    public Texture healIconImage = new Texture("heal_icon.png");
    public Texture renewIconImage = new Texture("renew_icon.png");
    public Texture barrierIconImage = new Texture("barrier_icon.png");
    public Texture flashIconImage = new Texture("flash_heal_icon.png");

    public Texture blackBar = new Texture(Gdx.files.internal("raid_frame_background_box.png"));
    public Texture whiteBar = new Texture(Gdx.files.internal("white_bar.png"));

    public Texture raidFrameIdle = new Texture(Gdx.files.internal("raid_frame_idle.png"));
    public Texture raidFrameSelected = new Texture(Gdx.files.internal("raid_frame_selected.png"));
    public Texture raidFrameHbLow = new Texture(Gdx.files.internal("raid_frame_health_bar_low.png"));
    public Texture raidFrameBoss = new Texture(Gdx.files.internal("raid_frame_boss.png"));

    public Texture manaBar = new Texture(Gdx.files.internal("mana_bar.png"));
    public Texture castBar = new Texture(Gdx.files.internal("casting_bar.png"));
    public Texture cooldownBar = new Texture(Gdx.files.internal("cooldown_bar.png"));
    */

    public Assets() {
        manager = new AssetManager();
        raidPositions = new ArrayList<Vector2>();
    }

    public void load()  {
        loadImages();
        loadSounds();
        loadFonts();
        loadPositions();
    }

    public void loadImages() {
        manager.load("dps_role_icon.png", Texture.class);
        manager.load("healer_role_icon.png", Texture.class);
        manager.load("tank_role_icon.png", Texture.class);

        manager.load("heal_icon.png", Texture.class);
        manager.load("death_icon.png", Texture.class);
        manager.load("renew_icon.png", Texture.class);
        manager.load("barrier_icon.png", Texture.class);
        manager.load("flash_heal_icon.png", Texture.class);

        manager.load("black_bar.png", Texture.class);
        manager.load("white_bar.png", Texture.class);
        manager.load("raid_frame_idle.png", Texture.class);
        manager.load("raid_frame_selected.png", Texture.class);
        manager.load("red_bar.png", Texture.class);
        manager.load("red_outline_bar.png", Texture.class);
        manager.load("green_bar.png", Texture.class);

        manager.load("mana_bar.png", Texture.class);
        manager.load("casting_bar.png", Texture.class);
        manager.load("cooldown_bar.png", Texture.class);

        manager.load("hogger_name.png", Texture.class);

        manager.load("battle_bg1.png", Texture.class);
        manager.load("battle_bg2.png", Texture.class);
    }

    public void loadSounds() {
        manager.load("sfx/battle_music.ogg", Music.class);
        manager.load("sfx/barrier_sfx.mp3", Sound.class);
        manager.load("sfx/casting_sfx.mp3", Sound.class);
        manager.load("sfx/heal_sfx.mp3", Sound.class);
        manager.load("sfx/hot_sfx.mp3", Sound.class);

    }

    public void loadPositions() {
        /*
                660,20, 660,174, 660,328, 581,20,
                581,174, 581,328, 502,20, 502,174, 502,328,
                423,20, 423,174, 423,328, 344,20, 344,174,
                344,328, 265,20, 265,174, 265,328
                */
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 3; j++) {
                raidPositions.add(new Vector2(20+(j*154), 660-(i*79)));
            }
        }


    }

    public void loadFonts() {
        manager.load("floating_font", BitmapFont.class);
        manager.load("mana_font", BitmapFont.class);
        manager.load("cooldown_font", BitmapFont.class);
    }



    public Texture getTexture(String filename)   {
        return manager.get(filename, Texture.class);
    }

    public Sound getSound(String filename)   {
        return manager.get(filename, Sound.class);
    }

    public Music getMusic(String filename)   {
        return manager.get(filename, Music.class);
    }

    public BitmapFont getFont(String filename)   {
        return manager.get(filename, BitmapFont.class);
    }

    public Texture getEffectImage(Spell.EffectType effectType) {
        switch (effectType) {
            case SHIELD:
                return getTexture("barrier_icon.png");
            case HEALOVERTIME:
                return getTexture("renew_icon.png");
        }
        return null;
    }
}
