package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.ConsumingShadowEffect;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class ConsumingShadow extends Mechanic{

    public ConsumingShadow(Boss owner) {
        super("Consuming Shadow", 0, 8f, owner);
    }

    public ConsumingShadow(Boss owner, float speed) {
        super("Consuming Shadow", 0, speed, owner);
        announce = true;
    }

    @Override
    public void action() {
        for(int i = 0; i <  owner.getEnemies().raidMembers.size(); i++)   {
            owner.getEnemies().raidMembers.get(i).addStatusEffect(new ConsumingShadowEffect(owner));
        }
    }
}
