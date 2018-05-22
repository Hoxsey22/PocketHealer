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
     * @param player
     */
    public Lightwell(Player player, Assets assets)  {
        super(player,
                "Light Well",
                "Summons a magical Light Well that will send holy light at the most injured ally unit and will give the use a little bit of mana.",
                7,
                1,
                7,
                5f,
                70f,
                60f,
                1f,
                assets.getSound(assets.hotSFX),
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
                owner.raid.player.receiveMana(2);
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
