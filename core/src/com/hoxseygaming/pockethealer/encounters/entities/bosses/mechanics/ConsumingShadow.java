package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.ConsumingShadowEffect;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class ConsumingShadow extends Mechanic{

    @SuppressWarnings("unused")
    public ConsumingShadow(Boss owner) {
        super("Consuming Shadow", 0, 8f, owner);
    }

    public ConsumingShadow(Boss owner, float speed) {
        super("Consuming Shadow", 0, speed, owner);
        setAnnounce();
    }

    @Override
    public void action() {
        for(int i = 0; i <  getOwner().getEnemies().getRaidMembers().size(); i++)   {
            getOwner().getEnemies().getRaidMembers().get(i).addStatusEffect(new ConsumingShadowEffect(getOwner()));
        }
    }
}
