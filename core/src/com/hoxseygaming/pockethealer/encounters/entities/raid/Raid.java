package com.hoxseygaming.pockethealer.encounters.entities.raid;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Hoxsey on 6/16/2017.
 */
public class Raid extends Group {

    public ArrayList<RaidMember> raidMembers;
    public Timer raidDamageTimer;
    private Assets assets;
    public boolean isRaidAlive;

    public Raid(int size, Assets assets)   {
        //super();
        this.assets = assets;
        setName("Raid");
        raidMembers = new ArrayList<RaidMember>();
        premade(size);
        raidDamageTimer = new Timer();
        isRaidAlive = true;
    }

    public void start(final Boss t)   {
        final Boss target = t;

        raidDamageTimer.schedule(new Timer.Task() {
            int deathCount = 0;
            @Override
            public void run() {

                for (int i = 0; i < raidMembers.size(); i++) {
                    if(raidMembers.get(i).isDead())
                        deathCount++;
                    target.takeDamage(raidMembers.get(i).getDamage());
                }

                if(deathCount == raidMembers.size())    {
                    raidDamageTimer.stop();
                    raidDamageTimer.clear();
                    System.out.println("Raid Damage Timer has stopped!");
                }

            }
        },3f,1f);
    }

    public void stop()  {
        raidDamageTimer.stop();
        raidDamageTimer.clear();
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

    public void customRaid(int tanks, int healers, int dps)    {
        addTank(tanks);
        addHealer(healers);
        addDps(dps);
    }

    public void addTank(int amount)   {
        for(int i = 0; i < amount; i++) {
            raidMembers.add(new RaidMember(raidMembers.size(), "Tank", assets));
            raidMembers.get(raidMembers.size() - 1).setAssets(assets);
            addActor(raidMembers.get(raidMembers.size() - 1));
        }
    }

    public void addHealer(int amount)   {
        for(int i = 0; i < amount; i++) {
            raidMembers.add(new RaidMember(raidMembers.size(), "Healer", assets));
            addActor(raidMembers.get(raidMembers.size() - 1));
        }
    }

    public void addDps(int amount)   {
        for(int i = 0; i < amount; i++) {
            raidMembers.add(new RaidMember(raidMembers.size(), "Dps", assets));
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
        ArrayList<RaidMember> lowest = new ArrayList<>(cap);
        ArrayList<RaidMember> temp = new ArrayList<>();
        temp.addAll(raidMembers);

        Collections.sort(temp);
        System.out.println(temp.toString());
        int counter = 0;
        for(int i = 0; i < temp.size(); i++) {
            if (!temp.get(i).isSelected()) {
                lowest.add(temp.get(i));
                counter++;
                if(counter == cap)    {
                    return lowest;
                }
            }
        }
        return null;
        /*
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
        }*/
    }

    public boolean tanksAlive() {

        for(int i = 0; i < raidMembers.size(); i++)   {
            if(raidMembers.get(i).getRole() == "tank" && !raidMembers.get(i).isDead());
            return true;
        }
        return false;
    }

    public int healersAlive() {
        int counter = 0;
        for(int i = 0; i < raidMembers.size(); i++)   {
            if(raidMembers.get(i).getRole() == "healer");
            counter++;
        }
        return counter;
    }

    public int dpsAlive() {
        int counter = 0;
        for(int i = 0; i < raidMembers.size(); i++)   {
            if(raidMembers.get(i).getRole() == "dps");
            counter++;
        }
        return counter;
    }

    public boolean isRaidDead() {
        for(int i = 0; i < raidMembers.size(); i++)   {
            if(!raidMembers.get(i).isDead())
                return false;
        }
        return true;
    }

    public void reset() {
        for(int i = 0; i < raidMembers.size(); i++)   {
            raidMembers.get(i).reset();
        }
    }


}
