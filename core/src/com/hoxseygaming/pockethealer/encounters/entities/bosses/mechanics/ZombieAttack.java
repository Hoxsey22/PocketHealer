package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 7/26/2017.
 */

public class ZombieAttack extends Mechanic {

    public ZombieAttack(Boss owner) {
        super("Auto Attack", 0, 2.5f, owner);
        id = 1;
        bgMech = true;
    }

    public ZombieAttack(Boss owner, float speed) {
        super("Auto Attack", 0, speed, owner);
        id = 1;
        bgMech = true;
    }

    @Override
    public void action() {
        ArrayList<RaidMember> targets = owner.getEnemies().getRandomRaidMember(8);
        for (int i = 0; i < targets.size() ;i++)
            targets.get(i).takeDamage(getZombieDamage());
        //owner.getEnemies().takeDamage(getZombieDamage());
    }

    public int getZombieDamage()    {
        if(owner.getHpPercent() > 0.89f)    {
            return owner.damage+10;
        }
        if(owner.getHpPercent() > 0.79f)    {
            return owner.damage+9;
        }
        if(owner.getHpPercent() > 0.69f)    {
            return owner.damage+8;
        }
        if(owner.getHpPercent() > 0.59f)    {
            return owner.damage+7;
        }
        if(owner.getHpPercent() > 0.49f)    {
            return owner.damage+6;
        }
        if(owner.getHpPercent() > 0.39f)    {
            return owner.damage+5;
        }
        if(owner.getHpPercent() > 0.29f)    {
            return owner.damage+4;
        }
        if(owner.getHpPercent() > 0.19f)    {
            return owner.damage+3;
        }
        if(owner.getHpPercent() > 0.09f)    {
            return owner.damage+2;
        }
        if(owner.getHpPercent() > 0.0f)    {
            return owner.damage+1;
        }
        return 2;
    }

}
