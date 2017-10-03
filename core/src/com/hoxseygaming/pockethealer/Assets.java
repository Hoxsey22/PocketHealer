package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Mechanic;
import com.hoxseygaming.pockethealer.encounters.spells.Spell;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/26/2017.
 */
public class Assets {
    //
    public AssetManager manager;
    public ArrayList<Vector2> raidPositions;
    public ArrayList<Vector2> bossIconPosition;
    public ArrayList<Vector2> talentPositions;


    // fonts
    public String floatingFnt = "fonts/floating_font.fnt";
    public String manaFnt = "fonts/mana_font.fnt";
    public String cooldownFnt = "fonts/cooldown_font.fnt";
    public String talentTooltipFont = "fonts/talent_tooltip_font.fnt";
    public String mapTitle = "map_state/title.fnt";
    public String mapDescription = "map_state/description.fnt";
    public String gameFont = "fonts/chela_one_regular.ttf";

    public String gameFont16 = "fonts/game_font_small.fnt";
    public String gameFont24 = "fonts/game_font_medium.fnt";
    public String gameFont32 = "fonts/game_font_large.fnt";
    public String gameFont45 = "fonts/game_font_xlarge.fnt";
    public String gameFontB16 = "fonts/game_font_small_border.fnt";
    public String gameFontB24 = "fonts/game_font_medium_border.fnt";
    public String gameFontB32 = "fonts/game_font_large_border.fnt";
    public String gameFontB45 = "fonts/game_font_xlarge_border.fnt";


    // pngss
    public String continuousRenewalIcon = "talent_state/continuous_renewal.png";
    public String lifeboomIcon = "icons/lifeboom.png";
    public String perseveranceIcon = "talent_state/perseverance.png";
    public String burstHealerIcon = "talent_state/burst_healer.png";
    public String innerFocusIcon = "talent_state/inner_focus.png";
    public String diseaseIcon = "icons/disease_icon.png";
    public String workTogetherIcon = "icons/work_together.png";
    public String selectedTalent = "icons/selected.png";
    public String talentWindow = "talent_state/talent_window.png";
    public String talentBg = "talent_bg.png";
    public String talentStateBg = "talent_state/bg.png";
    public String doneButton = "talent_state/done_button.png";
    public String toolTipFrame = "talent_state/tooltip_frame.png";
    public String miniBossIcon = "icons/level_mini_boss.png";
    public String bossIcon = "icons/level_boss.png";
    public String mmBG = "main_menu_state/bg.png";
    public String mmBG2 = "main_menu_state/bg2.png";
    public String mmBG3 = "main_menu_state/bg3.png";
    public String mmBG4 = "main_menu_state/main.png";

    public String mmPlayButtonIdle = "main_menu_state/play_button_idle.png";
    public String mmPlayButtonHover = "main_menu_state/play_button_hover.png";
    public String mmContinueButtonIdle = "main_menu_state/play_button_idle.png";
    public String mmContinueButtonHover = "main_menu_state/play_button_hover.png";
    public String dpsIcon = "icons/dps_role_icon.png";
    public String healerIcon = "icons/healer_role_icon.png";
    public String tankIcon = "icons/tank_role_icon.png";
    public String healIcon = "icons/heal_icon.png";
    public String renewIcon = "icons/renew_icon.png";
    public String barrierIcon = "icons/barrier_icon.png";
    public String flashIcon = "icons/flash_heal_icon.png";
    public String deathIcon = "icons/death_icon.png";
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
    public String purpleFill = "purple_fill.png";
    public String raidFrameIdle = "raid_frame_idle.png";
    public String raidFrameSelected = "raid_frame_selected.png";
    public String hpManaBar = "hp_mana_bar.png";
    public String manaFill = "mana_fill.png";
    public String castBar = "casting_bar.png";
    public String cooldownBar = "cooldown_bar.png";
    public String spellBar = "spell_bar.png";
    public String exitButton = "map_state/exit_button.png";
    public String bossLocation = "map_state/boss_location.png";
    public String infoFrame = "map_state/info_frame.png";
    public String spellButton = "map_state/spells_button.png";
    public String talentButton = "map_state/talent_button.png";
    public String infoButton = "map_state/info_button.png";
    public String startButton = "map_state/start_button.png";
    public String selectedLevel = "map_state/selected_level.png";
    public String bleedIcon = "icons/bleed_icon.png";
    public String wampusCatName = "wampus_cat_name.png";
    public String disableBG = "disable_bg.png";
    public String endGameFrame = "end_game_frame.png";
    public String finishButton = "finish_button.png";
    public String resetButton = "reset_button.png";
    public String leaveButton = "leave_button.png";
    public String youWin = "you_win_text.png";
    public String youWiped = "you_wiped_text.png";
    public String mapOuterFrame = "map_state/map_outer_frame.png";
    public String mapInnerFrame = "map_state/map_inner_frame.png";
    public String button = "button.png";
    public String buttonHighlight = "button_select.png";
    public String title = "title.png";
    public String poisonIcon = "icons/poison_icon.png";

    public String mapBg1 = "map_state/map.png";
    public String biteIcon = "icons/bite_icon.png";
    public String boilIcon = "icons/boil_icon.png";
    public String smiteIcon = "icons/smite_icon.png";
    public String holyNovaIcon = "icons/holy_nova_icon.png";
    public String greaterHealerIcon = "icons/greater_heal_icon.png";
    public String lightWellIcon = "icons/light_well_icon.png";
    public String divineHymnIcon = "icons/divine_hymn_icon.png";
    public String renewingNovaIcon = "icons/renewing_nova_icon.png";
    public String disciplineIcon = "icons/discipline_icon.png";
    public String criticalHealer2Icon = "icons/critical_healerii_icon.png";
    public String resurgenceIcon = "icons/resurgence_icon.png";
    public String aodIcon = "icons/aod_icon.png";
    public String superNovaIcon = "icons/super_nova_icon.png";
    public String idleLine = "icons/idle_line.png";
    public String selectedLine = "icons/selected_line.png";
    public String shadowIcon = "icons/shadow_icon.png";
    public String talentCalculatorFrame = "talent_calculator_frame.png";
    public String blankIcon = "icons/blank_icon.png";
    public String arrowPointer = "arrow.png";
    public String defeatedBossIcon = "map_state/defeated_boss.png";
    public String spellBG = "spell_bg.png";



    public ArrayList<String> maps;

    // music
    public String mmMusic = "mm_music.ogg";
    public String battleMusic = "sfx/battle_music.ogg";

    // sounds
    public String barrierSFX ="sfx/barrier_sfx.mp3";
    public String castingSFX ="sfx/casting_sfx.mp3";
    public String healSFX ="sfx/heal_sfx.mp3";
    public String hotSFX ="sfx/hot_sfx.mp3";

    public Assets() {
        manager = new AssetManager();
        raidPositions = new ArrayList<>();
        bossIconPosition = new ArrayList<>();
        talentPositions = new ArrayList<>();
        maps = new ArrayList<>();
        maps.add(mapBg1);
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
        manager.load(purpleFill, Texture.class);


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
        manager.load(diseaseIcon, Texture.class);
        manager.load(spellBar, Texture.class);

        manager.load(talentBg, Texture.class);
        manager.load(doneButton, Texture.class);
        manager.load(toolTipFrame, Texture.class);
        manager.load(talentStateBg, Texture.class);

        manager.load(miniBossIcon, Texture.class);
        manager.load(bossIcon, Texture.class);

        manager.load(mmBG, Texture.class);
        manager.load(mmBG2, Texture.class);
        manager.load(mmBG3, Texture.class);
        manager.load(mmBG4, Texture.class);

        manager.load(mmPlayButtonIdle, Texture.class);
        manager.load(mmPlayButtonHover, Texture.class);
        manager.load(mmContinueButtonIdle, Texture.class);
        manager.load(mmContinueButtonHover, Texture.class);

        manager.load(bossLocation, Texture.class);
        manager.load(exitButton, Texture.class);
        manager.load(infoFrame, Texture.class);
        manager.load(mapBg1, Texture.class);
        manager.load(spellButton, Texture.class);
        manager.load(talentButton, Texture.class);
        manager.load(infoButton, Texture.class);
        manager.load(startButton, Texture.class);
        manager.load(selectedLevel, Texture.class);
        manager.load(bleedIcon, Texture.class);
        manager.load(wampusCatName, Texture.class);

        manager.load(disableBG, Texture.class);
        manager.load(endGameFrame, Texture.class);
        manager.load(finishButton, Texture.class);
        manager.load(resetButton, Texture.class);
        manager.load(leaveButton, Texture.class);
        manager.load(youWin, Texture.class);
        manager.load(youWiped, Texture.class);

        manager.load(mapInnerFrame, Texture.class);
        manager.load(mapOuterFrame, Texture.class);

        manager.load(button, Texture.class);

        manager.load(title, Texture.class);

        manager.load(poisonIcon, Texture.class);
        manager.load(diseaseIcon, Texture.class);

        manager.load(biteIcon, Texture.class);
        manager.load(boilIcon, Texture.class);

        manager.load(smiteIcon, Texture.class);
        manager.load(holyNovaIcon, Texture.class);
        manager.load(greaterHealerIcon, Texture.class);
        manager.load(lightWellIcon, Texture.class);
        manager.load(divineHymnIcon, Texture.class);

        manager.load(renewingNovaIcon, Texture.class);
        manager.load(disciplineIcon, Texture.class);
        manager.load(criticalHealer2Icon, Texture.class);
        manager.load(resurgenceIcon, Texture.class);
        manager.load(aodIcon, Texture.class);
        manager.load(superNovaIcon, Texture.class);
        manager.load(idleLine, Texture.class);
        manager.load(selectedLine, Texture.class);
        manager.load(shadowIcon, Texture.class);
        manager.load(talentCalculatorFrame, Texture.class);
        manager.load(buttonHighlight, Texture.class);
        manager.load(blankIcon, Texture.class);
        manager.load(arrowPointer, Texture.class);
        manager.load(defeatedBossIcon, Texture.class);
        manager.load(spellBG, Texture.class);


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
        // raid position
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 3; j++) {
                //raidPositions.add(new Vector2(20+(j*154), 660-(i*79)));
                raidPositions.add(new Vector2(20+(j*147), 650-(i*70)));
            }
        }

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 4; j++) {
                talentPositions.add(new Vector2(80+(i*120),600-(j*120)));
            }
        }

        // boss icon position
        bossIconPosition.add(new Vector2(30,-47)); // wild boar
        bossIconPosition.add(new Vector2(90,-47)); // tiger
        bossIconPosition.add(new Vector2(150,-47)); // giant bee
        bossIconPosition.add(new Vector2(210,-47)); // golem
        bossIconPosition.add(new Vector2(270,-47)); // bandit leader

        bossIconPosition.add(new Vector2(30,-200)); // hogger
        bossIconPosition.add(new Vector2(90,-200)); // wampus
        bossIconPosition.add(new Vector2(150,-200)); // proctor
        bossIconPosition.add(new Vector2(210,-200)); // apprentice
        bossIconPosition.add(new Vector2(270,-200)); // sorcerer

        bossIconPosition.add(new Vector2(30,-300)); // mother spider
        bossIconPosition.add(new Vector2(90,-300)); // zombie horde
        bossIconPosition.add(new Vector2(150,-300)); // crazy professor
        bossIconPosition.add(new Vector2(210,-300)); // blood queen
        bossIconPosition.add(new Vector2(270,-300)); // death dragon
        bossIconPosition.add(new Vector2(30,-400)); // test boss

    }

    public void loadFonts() {
        manager.load(floatingFnt, BitmapFont.class);
        manager.load(manaFnt, BitmapFont.class);
        manager.load(cooldownFnt, BitmapFont.class);
        manager.load(talentTooltipFont, BitmapFont.class);
        manager.load(mapTitle, BitmapFont.class);
        manager.load(mapDescription, BitmapFont.class);

        manager.load(gameFont16,BitmapFont.class);
        manager.load(gameFont24,BitmapFont.class);
        manager.load(gameFont32,BitmapFont.class);
        manager.load(gameFont45,BitmapFont.class);
        manager.load(gameFontB16,BitmapFont.class);
        manager.load(gameFontB24,BitmapFont.class);
        manager.load(gameFontB32,BitmapFont.class);
        manager.load(gameFontB45,BitmapFont.class);
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

    public BitmapFont getFont(boolean isBorder)   {
        if(isBorder)    {
            return manager.get(gameFontB16, BitmapFont.class);
        }
        else {
            return manager.get(gameFont16, BitmapFont.class);
        }
    }

    /*
    public BitmapFont getFont(int fontSize, Color fontColor, int borderSize, Color borderColor)   {

        FreeTypeFontLoaderParameter font = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        font.fontFileName = gameFont;
        font.fontParameters.size = fontSize;
        font.fontParameters.color = fontColor;
        font.fontParameters.borderWidth = borderSize;
        font.fontParameters.borderColor = borderColor;
        man






        return manager.get(filename, BitmapFont.class);
    }*/

    public Texture getEffectImage(Spell.EffectType effectType) {
        switch (effectType) {
            case SHIELD:
                return getTexture(barrierIcon);
            case HEALOVERTIME:
                return getTexture(renewIcon);
            case TEMPHEAL:
                return getTexture(lifeboomIcon);
        }
        return null;
    }

    public Texture getEffectImage(Mechanic.Debuff debuff) {
        switch (debuff) {
            case BLEED:
                return this.getTexture(bleedIcon);
            case POISON:
                return this.getTexture(poisonIcon);
            case DISEASE:
                return this.getTexture(diseaseIcon);
            case BITTEN:
                return this.getTexture(biteIcon);
            case BOIL:
                return this.getTexture(boilIcon);
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
