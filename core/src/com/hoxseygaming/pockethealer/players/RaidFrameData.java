package com.hoxseygaming.pockethealer.players;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Hoxsey on 5/30/2017.
 */
public class RaidFrameData{


    public static int postion[] = {
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

}
