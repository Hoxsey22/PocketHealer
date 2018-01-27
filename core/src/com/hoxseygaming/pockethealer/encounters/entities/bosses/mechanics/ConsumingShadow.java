package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.ConsumingShadowEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class ConsumingShadow extends Mechanic{

    public ConsumingShadow(Boss owner) {
        super("Consuming Shadow", 0, 8f, owner);
    }

    public ConsumingShadow(Boss owner, float speed) {
        super("Consuming Shadow", 0, speed, owner);
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {

                ArrayList<RaidMember> targets = owner.getEnemies().getRandomRaidMember(4, owner.getEnemies().getDebuffLessRaidMembers("Consuming Shadow"));
                for(int i = 0; i < targets.size(); i++){
                    targets.get(i).addStatusEffect(new ConsumingShadowEffect(owner));
                }
            }
        },2f, speed);

    }
}
