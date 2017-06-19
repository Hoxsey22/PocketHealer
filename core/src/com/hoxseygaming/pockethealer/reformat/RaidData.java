package com.hoxseygaming.pockethealer.reformat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Hoxsey on 6/16/2017.
 */
public class RaidData {

    public static int postion[] = {
            660,20, 660,174, 660,328, 581,20,
            581,174, 581,328, 502,20, 502,174, 502,328,
            423,20, 423,174, 423,328, 344,20, 344,174,
            344,328, 265,20, 265,174, 265,328
    };

    public static Image dpsIconImage = new Image(new Texture("dps_role_icon.png"));
    public static Image healerIconImage = new Image(new Texture("healer_role_icon.png"));
    public static Image tankIconImage = new Image(new Texture("tank_role_icon.png"));

    public static Image raidFrameBackgroundBox = new Image(new Texture("raid_frame_background_box.png"));
    public static Image raidFrameIdle = new Image(new Texture("raid_frame_idle.png"));
    public static Image raidFrameSelected = new Image(new Texture("raid_frame_selected.png"));
    public static Image raidFrameHbFull = new Image(new Texture("raid_frame_health_bar_full.png"));
    public static Image raidFrameHbHalf = new Image(new Texture("raid_frame_health_bar_half.png"));
    public static Image raidFrameHbLow = new Image(new Texture("raid_frame_health_bar_low.png"));

    public static Image healIconImage = new Image(new Texture("spell_holy_greaterheal.jpg"));
    public static Image renewIconImage = new Image(new Texture("spell_holy_renew.jpg"));
    public static Image barrierIconImage = new Image(new Texture("spell_holy_powerwordshield.jpg"));
    public static Image flashIconImage = new Image(new Texture("spell_holy_flashheal.jpg"));

}
