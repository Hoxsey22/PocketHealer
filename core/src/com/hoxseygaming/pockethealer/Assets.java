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
    public String dpsIcon = "dps_role_icon.png";
    public String healerIcon = "healer_role_icon.png";
    public String tankIcon = "tank_role_icon.png";

    public String healIcon = "heal_icon.png";
    public String renewIcon = "renew_icon.png";
    public String barrierIcon = "barrier_icon.png";
    public String flashIcon = "flash_heal_icon.png";
    public String deathIcon = "death_icon.png";

    public String hoggerName = "hogger_name.png";

    public String battleBg1 = "battle_bg1.png";
    public String battleBg2 = "battle_bg2.png";

    public String blackBar = "black_bar.png";
    public String whiteBar = "white_bar.png";
    public String redBar = "red_bar.png";
    public String redOutlineBar = "red_outline_bar.png";
    public String greenBar = "green_bar.png";

    public String raidFrameIdle = "raid_frame_idle.png";
    public String raidFrameSelected = "raid_frame_selected.png";

    public String manaBar = "mana_bar.png";
    public String castBar = "casting_bar.png";
    public String cooldownBar = "cooldown_bar.png";

    public String battleMusic = "sfx/battle_music.ogg";
    public String barrierSFX ="sfx/barrier_sfx.mp3";
    public String castingSFX ="sfx/casting_sfx.mp3";
    public String healSFX ="sfx/heal_sfx.mp3";
    public String hotSFX ="sfx/hot_sfx.mp3";

    public String floatingFnt = "floating_font.fnt";
    public String manaFnt = "mana_font.fnt";
    public String cooldownFnt = "cooldown_font.fnt";

    public String continuousRenewalIcon = "continuous_renewal.png";
    public String lifeboomIcon = "lifeboom.png";
    public String perseveranceIcon = "perseverance.png";
    public String burstHealerIcon = "burst_healer.png";
    public String innerFocusIcon = "inner_focus.png";
    public String workTogetherIcon = "work_together.png";
    public String selectedTalent = "selected_talent.png";
    public String talentStateTitle = "talent_screen_title.png";

    public String talentBg = "talent_bg.png";
    public String talentTooltip = "talent_tooltip_font.fnt";


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
        manager.load(dpsIcon, Texture.class);
        manager.load(healerIcon, Texture.class);
        manager.load(tankIcon, Texture.class);

        manager.load(healIcon, Texture.class);
        manager.load(deathIcon, Texture.class);
        manager.load(renewIcon, Texture.class);
        manager.load(barrierIcon, Texture.class);
        manager.load(flashIcon, Texture.class);

        manager.load(blackBar, Texture.class);
        manager.load(whiteBar, Texture.class);
        manager.load(raidFrameIdle, Texture.class);
        manager.load(raidFrameSelected, Texture.class);
        manager.load(redBar, Texture.class);
        manager.load(redOutlineBar, Texture.class);
        manager.load(greenBar, Texture.class);

        manager.load(manaBar, Texture.class);
        manager.load(castBar, Texture.class);
        manager.load(cooldownBar, Texture.class);

        manager.load(hoggerName, Texture.class);

        manager.load(battleBg1, Texture.class);
        manager.load(battleBg2, Texture.class);

        manager.load(continuousRenewalIcon, Texture.class);
        manager.load(lifeboomIcon, Texture.class);
        manager.load(perseveranceIcon, Texture.class);
        manager.load(burstHealerIcon, Texture.class);
        manager.load(innerFocusIcon, Texture.class);
        manager.load(workTogetherIcon, Texture.class);
        manager.load(selectedTalent, Texture.class);
        manager.load(talentStateTitle, Texture.class);

        manager.load(talentBg, Texture.class);


    }

    public void loadSounds() {
        manager.load(battleMusic, Music.class);
        manager.load(barrierSFX, Sound.class);
        manager.load(castingSFX, Sound.class);
        manager.load(healSFX, Sound.class);
        manager.load(hotSFX, Sound.class);

    }

    public void loadPositions() {
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 3; j++) {
                raidPositions.add(new Vector2(20+(j*154), 660-(i*79)));
            }
        }


    }

    public void loadFonts() {
        manager.load(floatingFnt, BitmapFont.class);
        manager.load(manaFnt, BitmapFont.class);
        manager.load(cooldownFnt, BitmapFont.class);
        manager.load(talentTooltip, BitmapFont.class);
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

    public float getProgress()  {
        return manager.getProgress();
    }

    public boolean update() {
        return manager.update();
    }
}
