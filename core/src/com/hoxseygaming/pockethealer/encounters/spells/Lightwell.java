package com.hoxseygaming.pockethealer.encounters.spells;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
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
        super(player,
                "Lightwell",
                "Summons a magical lightwell that will send holy light at the most injured ally unit.",
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

        checkCriticalHealer();
        checkHasteBuild();
    }

    @Override
    public void checkLifeboom() {
    }

}
