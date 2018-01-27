package com.hoxseygaming.pockethealer.encounters.spells;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.Periodical;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Lightwell extends Periodical {


    /**
     * @param index
     * @param player
     */
    public Lightwell(Player player, int index, Assets assets)  {
        super(player, "Lightwell", "This spell will heal the lowest health raid member over time.",
                6,
                EffectType.HEALOVERTIME,
                1,
                3,
                5f,
                70f,
                60f,
                1.5f,
                assets.getSound(assets.hotSFX),
                index,
                assets);
        image = this.assets.getTexture(assets.lightWellIcon);
    }

    @Override
    public void startDurationTimer()  {

        durationTimer = new Timer();

        durationTimer.scheduleTask(new Timer.Task() {
            float currentTime = 0;

            @Override
            public void run() {
                RaidMember lowest = owner.getRaid().getRaidMemberWithLowestHp();
                currentTime = currentTime + speed;

                if(currentTime >= duration )    {
                    durationTimer.stop();
                }
                lowest.receiveHealing(output,criticalChance.isCritical());
            }
        }, speed, speed);

    }

    @Override
    public void checkTalents() {

        resetDefault();

        if(owner.getTalentTree().getTalent(TalentTree.CRITICAL_HEALER).isSelected())    {
            setCriticalChance(30);
        }
        if(owner.getTalentTree().getTalent(TalentTree.HASTE_BUILD).isSelected())    {
            speed = speed - 0.25f;
        }


    }

    public void resetDefault()  {

        numOfTargets = MIN_NUM_OF_TARGETS;
        output = MIN_OUTPUT;
        cost = MIN_COST;
        cooldown = MIN_COOLDOWN;
        duration = MIN_DURATION;
        speed = MIN_SPEED;
    }

    @Override
    public void checkLifeboom() {

    }

}
