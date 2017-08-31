package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/28/2017.
 */

public class Consume extends Mechanic {

    public Consume(Boss owner) {
        super("Consume", 0, 2f, owner);
    }

    public Consume(Boss owner, float speed) {
        super("Consume", 0, speed, owner);
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            ArrayList<RaidMember> diseasedTargets;
            @Override
            public void run() {
                diseasedTargets = findDisease();
                for(int i = 0; i <  diseasedTargets.size(); i++)   {
                    diseasedTargets.get(i).takeDamage(200);
                    owner.receiveHealing(diseasedTargets.get(i).getMaxHp());
                    if(owner.getName().equalsIgnoreCase("Zombie Horde"))    {
                        owner.setDamage(owner.getDamage()+2);
                    }
                }
            }
        },speed,speed);
    }

    public ArrayList<RaidMember> findDisease()   {
        ArrayList<RaidMember> diseasedTargets = new ArrayList<>();
        for(int i = 0; i < owner.getEnemies().raidMembers.size(); i++)   {
            if(owner.getEnemies().raidMembers.get(i).containsEffects(Debuff.DISEASE))   {
                diseasedTargets.add(owner.getEnemies().raidMembers.get(i));
            }
        }
        return diseasedTargets;
    }
}
