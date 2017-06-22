package com.hoxseygaming.pockethealer.reformat.entities.raid;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.reformat.entities.bosses.Boss;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Hoxsey on 6/16/2017.
 */
public class Raid extends Group {

    public ArrayList<RaidMember> raidMembers;
    public Timer raidDamageTimer;

    public Raid(int size)   {
        //super();
        setName("Raid");
        raidMembers = new ArrayList<RaidMember>();
        premade(size);
        raidDamageTimer = new Timer();

    }

    public void startRaidDamageTimer(final Boss t)   {
        final Boss target = t;

        raidDamageTimer.schedule(new Timer.Task() {
            @Override
            public void run() {
                for (int i = 0; i < raidMembers.size(); i++)
                    target.takeDamage(raidMembers.get(i).getDamage());
            }
        },3f,1f);
    }

    public void premade(int size)   {
        switch(size) {
            case 5:
                addTank(1);
                addHealer(1);
                addDps(3);
                break;
            case 10:
                addTank(2);
                addHealer(2);
                addDps(6);
                break;
            case 15:
                addTank(2);
                addHealer(3);
                addDps(10);
                break;
            case 20:
                addTank(2);
                addHealer(4);
                addDps(14);
                break;
        }


    }

    public void addTank(int amount)   {
        for(int i = 0; i < amount; i++) {
            raidMembers.add(new RaidMember(raidMembers.size(), "Tank"));
            addActor(raidMembers.get(raidMembers.size() - 1));
        }
    }

    public void addHealer(int amount)   {
        for(int i = 0; i < amount; i++) {
            raidMembers.add(new RaidMember(raidMembers.size(), "Healer"));
            addActor(raidMembers.get(raidMembers.size() - 1));
        }
    }

    public void addDps(int amount)   {
        for(int i = 0; i < amount; i++) {
            raidMembers.add(new RaidMember(raidMembers.size(), "Dps"));
            addActor(raidMembers.get(raidMembers.size() - 1));
        }
    }

    public RaidMember getRaidMember(int index)   {
        return raidMembers.get(index);
    }

    public RaidMember[] getRandomRaidMember(int amount) {
        RaidMember raidMembers [] = new RaidMember[amount];
        int counter = 0;
        ArrayList<RaidMember> temp = new ArrayList<RaidMember>();
        temp.addAll(this.raidMembers);
        Collections.shuffle(temp);
        for (int i = 0; i < temp.size(); i++) {
            if(counter != amount) {
                if (!temp.get(i).isDead()) {
                    raidMembers[counter] = temp.get(i);
                    counter++;
                }
            }
            else
                return raidMembers;
        }
        return  raidMembers;
    }

    public Actor getRandomRaidMember()  {
        ArrayList<RaidMember> temp = new ArrayList<RaidMember>();
        temp.addAll(raidMembers);
        Collections.shuffle(temp);
        for (int i = 0; i < temp.size(); i++) {
            if (!temp.get(i).isDead())
                return temp.get(i);
        }
        return temp.get(0);
    }

    public ArrayList<RaidMember> getRaidMembersWithLowestHp(int cap)    {
        ArrayList<RaidMember> lowest = new ArrayList<RaidMember>(cap);
        for (int i = 0; i < raidMembers.size(); i++)  {
            if(!raidMembers.get(i).isSelected()) {
                if (lowest.size() < cap) {
                    lowest.add(raidMembers.get(i));
                } else {
                    Collections.sort(lowest);
                    if (raidMembers.get(i).hp < lowest.get(0).getHp()) {
                        lowest.add(0, raidMembers.get(i));
                    }
                }
            }
        }
        return lowest;
    }
}
