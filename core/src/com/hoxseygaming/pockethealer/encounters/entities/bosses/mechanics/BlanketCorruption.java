package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.CorruptionEffect;

import java.util.Random;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class BlanketCorruption extends Mechanic{


    public Timer channel;
    private Random dice;

    public BlanketCorruption(Boss owner) {
        super("Blanket Corruption", 0, 65f, owner);
        dice = new Random();
        announce = true;
    }

    public BlanketCorruption(Boss owner, int damage, float speed) {
        super("Blanket Corruption", damage, speed, owner);
        dice = new Random();
    }

    @Override
    public void action() {
        for(int i = 0; i < owner.getEnemies().raidMembers.size(); i++)   {
            CorruptionEffect corruptionEffect = new CorruptionEffect(owner);
            corruptionEffect.setModValue(5);

            owner.getEnemies().raidMembers.get(i).addStatusEffect(corruptionEffect);
        }
    }
}
