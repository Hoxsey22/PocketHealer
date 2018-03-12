package com.hoxseygaming.pockethealer.encounters.spells.Types;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;

/**
 * Created by Hoxsey on 9/7/2017.
 */

public abstract class Periodical extends InstantCast {

    public float duration;
    public float MIN_DURATION;
    public float speed;
    public float MIN_SPEED;
    public Timer durationTimer;

    /**
     * @param player
     * @param name
     * @param description
     * @param numOfTargets
     * @param output
     * @param costPercentage
     * @param cooldown
     * @param spellSFX
     * @param assets
     */
    public Periodical(Player player, String name, String description, int levelRequirement, int numOfTargets,
                      int output, float costPercentage, float cooldown, float duration, float speed, Sound spellSFX, Assets assets) {
        super(player, name, description, levelRequirement, numOfTargets, output, costPercentage, cooldown, spellSFX, assets);
        spellType = "Periodical";
        this.duration = duration;
        MIN_DURATION = duration;
        this.speed = speed;
        MIN_SPEED = speed;

    }

    @Override
    public void applySpell(RaidMember target) {
        startDurationTimer();
        System.out.println(name+" applied.");
    }

    public abstract void startDurationTimer();
    public abstract void checkLifeboom();

    public void checkHasteBuild()   {
        if(owner.getTalentTree().getTalent(TalentTree.HASTE_BUILD).isSelected())    {
            speed = MIN_SPEED - 0.25f;
        }
    }

    public void checkAoD()  {
        if(owner.getTalentTree().getTalent(TalentTree.AOD).isSelected())    {
            output = 10;
            duration = 12;
            speed = 1.5f;
        }
    }

    @Override
    public void resetDefault() {
        super.resetDefault();
        duration = MIN_DURATION;
        speed = MIN_SPEED;
    }

    public void stop() {
        if(durationTimer != null)    {
            durationTimer.stop();
            durationTimer.clear();
        }
    }
}
