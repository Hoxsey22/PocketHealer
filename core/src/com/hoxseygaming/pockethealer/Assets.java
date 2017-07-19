package com.hoxseygaming.pockethealer;

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
    public String yellowFill = "yellow_hp_fill.png";
    public String redFill = "red_hp_fill.png";
    public String greenFill = "green_hp_fill.png";

    public String raidFrameIdle = "raid_frame_idle.png";
    public String raidFrameSelected = "raid_frame_selected.png";

    public String hpManaBar = "hp_mana_bar.png";
    public String manaFill = "mana_fill.png";
    public String castBar = "casting_bar.png";
    public String cooldownBar = "cooldown_bar.png";
    public String spellBar = "spell_bar.png";

    public String battleMusic = "sfx/battle_music.ogg";
    public String barrierSFX ="sfx/barrier_sfx.mp3";
    public String castingSFX ="sfx/casting_sfx.mp3";
    public String healSFX ="sfx/heal_sfx.mp3";
    public String hotSFX ="sfx/hot_sfx.mp3";

    public String floatingFnt = "fonts/floating_font.fnt";
    public String manaFnt = "fonts/mana_font.fnt";
    public String cooldownFnt = "fonts/cooldown_font.fnt";
    public String talentTooltipFont = "fonts/talent_tooltip_font.fnt";

    public String continuousRenewalIcon = "talent_state/continuous_renewal.png";
    public String lifeboomIcon = "talent_state/lifeboom.png";
    public String perseveranceIcon = "talent_state/perseverance.png";
    public String burstHealerIcon = "talent_state/burst_healer.png";
    public String innerFocusIcon = "talent_state/inner_focus.png";
    public String workTogetherIcon = "talent_state/work_together.png";
    public String selectedTalent = "talent_state/selected_talent_frame.png";
    public String talentWindow = "talent_state/talent_window.png";
    public String talentBg = "talent_state/unselected_talent_frame.png";
    public String talentStateBg = "talent_state/bg.png";
    public String doneButton = "talent_state/done_button.png";
    public String toolTipFrame = "talent_state/tooltip_frame.png";

    public String miniBossIcon = "level_mini_boss.png";
    public String bossIcon = "level_boss.png";

    public String mmBG = "main_menu_state/bg.png";
    public String mmPlayButtonIdle = "main_menu_state/play_button_idle.png";
    public String mmPlayButtonHover = "main_menu_state/play_button_hover.png";
    public String mmContinueButtonIdle = "main_menu_state/play_button_idle.png";
    public String mmContinueButtonHover = "main_menu_state/play_button_hover.png";
    public String mmMusic = "mm_music.ogg";
    public String names[] = {"Hoxsey","Coco","Mop", "Zern","Ash","Captazn","Brian"};
    public String hoggerChat = "Hey, we need a healer this guys keeps giving us trouble...\n" +
            "he does a lot of cleave damage so watch out.";





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
        manager.load(redFill, Texture.class);
        manager.load(yellowFill, Texture.class);
        manager.load(greenFill, Texture.class);


        manager.load(hpManaBar, Texture.class);
        manager.load(manaFill, Texture.class);

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
        manager.load(talentWindow, Texture.class);
        manager.load(spellBar, Texture.class);

        manager.load(talentBg, Texture.class);
        manager.load(doneButton, Texture.class);
        manager.load(toolTipFrame, Texture.class);
        manager.load(talentStateBg, Texture.class);

        manager.load(miniBossIcon, Texture.class);
        manager.load(bossIcon, Texture.class);

        manager.load(mmBG, Texture.class);
        manager.load(mmPlayButtonIdle, Texture.class);
        manager.load(mmPlayButtonHover, Texture.class);
        manager.load(mmContinueButtonIdle, Texture.class);
        manager.load(mmContinueButtonHover, Texture.class);


    }

    public void loadSounds() {
        manager.load(battleMusic, Music.class);
        //manager.load(mmMusic, Music.class);
        manager.load(barrierSFX, Sound.class);
        manager.load(castingSFX, Sound.class);
        manager.load(healSFX, Sound.class);
        manager.load(hotSFX, Sound.class);

    }

    public void loadPositions() {
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 3; j++) {
                //raidPositions.add(new Vector2(20+(j*154), 660-(i*79)));
                raidPositions.add(new Vector2(20+(j*147), 660-(i*70)));
            }
        }


    }

    public void loadFonts() {
        manager.load(floatingFnt, BitmapFont.class);
        manager.load(manaFnt, BitmapFont.class);
        manager.load(cooldownFnt, BitmapFont.class);
        manager.load(talentTooltipFont, BitmapFont.class);
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
