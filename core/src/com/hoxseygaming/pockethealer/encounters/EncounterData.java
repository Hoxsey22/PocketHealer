package com.hoxseygaming.pockethealer.encounters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.hoxseygaming.pockethealer.encounters.spells.Spell;

/**
 * Created by Hoxsey on 6/16/2017.
 */
public class EncounterData {

    public static int raidPositions[] = {
            660,20, 660,174, 660,328, 581,20,
            581,174, 581,328, 502,20, 502,174, 502,328,
            423,20, 423,174, 423,328, 344,20, 344,174,
            344,328, 265,20, 265,174, 265,328
    };

    public static Texture dpsIconImage = new Texture("dps_role_icon.png");
    public static Texture healerIconImage = new Texture("healer_role_icon.png");
    public static Texture tankIconImage = new Texture("tank_role_icon.png");

    public static Texture healIconImage = new Texture("spell_holy_greaterheal.jpg");
    public static Texture renewIconImage = new Texture("spell_holy_renew.jpg");
    public static Texture barrierIconImage = new Texture("spell_holy_powerwordshield.jpg");
    public static Texture flashIconImage = new Texture("spell_holy_flashheal.jpg");

    public static Texture blackBar = new Texture(Gdx.files.internal("raid_frame_background_box.png"));
    public static Texture whiteBar = new Texture(Gdx.files.internal("white_bar.png"));

    public static Texture raidFrameIdle = new Texture(Gdx.files.internal("raid_frame_idle.png"));
    public static Texture raidFrameSelected = new Texture(Gdx.files.internal("raid_frame_selected.png"));
    public static Texture raidFrameHbFull = new Texture(Gdx.files.internal("raid_frame_health_bar_full.png"));
    public static Texture raidFrameHbHalf = new Texture(Gdx.files.internal("raid_frame_health_bar_half.png"));
    public static Texture raidFrameHbLow = new Texture(Gdx.files.internal("raid_frame_health_bar_low.png"));
    public static Texture raidFrameBoss = new Texture(Gdx.files.internal("raid_frame_boss.png"));

    public static Texture manaBar = new Texture(Gdx.files.internal("mana_bar.png"));
    public static Texture castBar = new Texture(Gdx.files.internal("casting_bar.png"));
    public static Texture cooldownBar = new Texture(Gdx.files.internal("cooldown_bar.png"));

    public static Texture getEffectImage(Spell.EffectType effectType) {
        switch (effectType) {
            case SHIELD:
                return barrierIconImage;
            case HEALOVERTIME:
                return renewIconImage;
        }
        return null;
    }






}
