package com.hoxseygaming.pockethealer.encounters.entities.raid;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.HealingTracker;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Hoxsey on 6/16/2017.
 */
public class Raid extends Group {

    public ArrayList<RaidMember> raidMembers;
    public ArrayList<RaidMember> healers;
    public Timer raidDamageTimer;
    private Assets assets;
    public boolean isRaidAlive;
    public HealingTracker healingTracker;
    public Player player;

    public Raid(int size, Assets assets)   {
        //super();
        this.assets = assets;
        setName("Raid");
        raidMembers = new ArrayList<>();
        healers = new ArrayList<>();
        preMade(size);
        raidDamageTimer = new Timer();
        isRaidAlive = true;
        healingTracker = new HealingTracker();
    }

    public void start(final Boss t)   {

        final Boss target = t;
        final boolean healerChannel = target.getPlayer().talentTree.getTalent("Healer Channel").isSelected();
        raidDamageTimer.scheduleTask(new Timer.Task() {

            @Override
            public void run() {

                for (int i = 0; i < raidMembers.size(); i++) {
                    if(!raidMembers.get(i).isDead()) {
                        if(raidMembers.get(i).getRole().equalsIgnoreCase("Healer"))    {
                            player.receiveMana(2);
                            if(healerChannel) {
                                getRaidMemberWithLowestHp().receiveHealing(raidMembers.get(i).damage,false);
                            }
                            else {
                                target.takeDamage(raidMembers.get(i).getDamage());
                            }
                        }
                        else    {
                            target.takeDamage(raidMembers.get(i).getDamage());
                        }
                    }
                }

            }
        },3f,1f);
    }

    public void stop()  {
        raidDamageTimer.stop();
        raidDamageTimer.clear();
        if(raidMembers != null) {
            for(int i = 0; i < raidMembers.size(); i++)   {
                raidMembers.get(i).stop();
            }
        }
    }

    public void preMade(int size)   {
        switch(size) {
            case 3:
                addTank(1);
                //addHealer(1);
                addDps(2);
                break;
            case 6:
                addTank(1);
                addHealer(1);
                addDps(4);
                break;
            case 9:
                addTank(2);
                addHealer(1);
                addDps(6);
                break;
            case 12:
                addTank(2);
                addHealer(2);
                addDps(8);
                break;
            case 15:
                addTank(2);
                addHealer(2);
                addDps(11);
                break;
            case 18:
                addTank(2);
                addHealer(3);
                addDps(13);
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
            healers.add(raidMembers.get(raidMembers.size()-1));
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

    public ArrayList<RaidMember> getRandomRaidMember(int amount) {
        ArrayList<RaidMember> raidMembers = new ArrayList<>();
        int counter = 0;
        ArrayList<RaidMember> temp = new ArrayList<>();
        temp.addAll(this.raidMembers);
        Collections.shuffle(temp);
        for (int i = 0; i < temp.size(); i++) {
            if(counter != amount) {
                if (!temp.get(i).isDead()) {
                    raidMembers.add(temp.get(i));
                    counter++;
                }
            }
            else
                return raidMembers;
        }
        return  raidMembers;
    }

    public ArrayList<RaidMember> getRandomRaidMember(int amount, ArrayList<RaidMember> group) {
        ArrayList<RaidMember> randomMembers = new ArrayList<>();
        int counter = 0;
        Collections.shuffle(group);
        for (int i = 0; i < group.size(); i++) {
            if(counter != amount) {
                if (!group.get(i).isDead()) {
                    randomMembers.add(group.get(i));
                    counter++;
                }
            }
            else
                return randomMembers;
        }
        return  randomMembers;
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
        ArrayList<RaidMember> lowest = new ArrayList<>();
        ArrayList<RaidMember> temp = new ArrayList<>();
        temp.addAll(raidMembers);

        Collections.sort(temp);
        int counter = 0;
        for(int i = 0; i < temp.size(); i++) {
            if (!temp.get(i).isSelected() && !temp.get(i).isDead) {
                lowest.add(temp.get(i));
                counter++;
                if(counter == cap)    {
                    return lowest;
                }
            }
        }
        return lowest;
    }

    public RaidMember getRaidMemberWithLowestHp()    {
        ArrayList<RaidMember> temp = new ArrayList<>();
        temp.addAll(raidMembers);
        Collections.sort(temp);

        for(int i = 0; i <  temp.size(); i++)   {
            if(!temp.get(i).isDead())    {
                return temp.get(i);
            }
        }
        return null;

    }

    public ArrayList<RaidMember> getRaidMembersWithLowestHp(int cap, RaidMember target)    {
        ArrayList<RaidMember> lowest = new ArrayList<>();
        ArrayList<RaidMember> temp = new ArrayList<>();

        temp.addAll(raidMembers);

        Collections.sort(temp);

        int counter = 0;
        for(int i = 0; i < temp.size(); i++) {
            if (!temp.get(i).isSelected()&& !temp.get(i).isDead) {
                lowest.add(temp.get(i));
                counter++;
                if(counter == cap)    {
                    return lowest;
                }
            }
        }
        return lowest;
    }

    public void receiveHealing(int output)    {
        for(int i= 0; i <  raidMembers.size(); i++)   {
            raidMembers.get(i).receiveHealing(output, false);
        }
    }

    public void takeDamage(int damage)    {
        for(int i= 0; i <  raidMembers.size(); i++)   {
            raidMembers.get(i).takeDamage(damage);
        }
    }

    public ArrayList<RaidMember> tanksAlive() {
        ArrayList<RaidMember> tanks = new ArrayList<>();
        for(int i = 0; i < raidMembers.size(); i++)   {
            if(raidMembers.get(i).getRole().equalsIgnoreCase("tank") && !raidMembers.get(i).isDead())  {
                tanks.add(raidMembers.get(i));
            }
        }
        return tanks;

    }

    public ArrayList<RaidMember> healersAlive() {
        ArrayList<RaidMember> healers = new ArrayList<>();
        for(int i = 0; i < raidMembers.size(); i++)   {
            if(raidMembers.get(i).getRole().equalsIgnoreCase("healer") && !raidMembers.get(i).isDead())
                healers.add(raidMembers.get(i));
        }
        return healers;
    }

    public ArrayList<RaidMember> dpsAlive() {
        ArrayList<RaidMember> dps = new ArrayList<>();
        for(int i = 0; i < raidMembers.size(); i++)   {
            if(raidMembers.get(i).getRole().equalsIgnoreCase("dps") && !raidMembers.get(i).isDead())
                dps.add(raidMembers.get(i));
        }
        return dps;
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

    public ArrayList<RaidMember> getDebuffLessRaidMembers(String name)    {
        ArrayList<RaidMember> debuffLess = new ArrayList<>();
        for(int i = 0; i <  raidMembers.size(); i++)   {
            if(!raidMembers.get(i).getStatusEffectList().contains(name) && !raidMembers.get(i).isDead)    {
                debuffLess.add(raidMembers.get(i));
            }
        }
        System.out.println("Debuffless List: ");
        for (int i = 0; i < debuffLess.size(); i++){System.out.print(debuffLess.get(i).getId()+",");}
        System.out.println("\n\n");
        return  debuffLess;
    }

    public ArrayList<RaidMember> getStatusEffectedRaidMembers(String name)    {
        ArrayList<RaidMember> statusEffectedMembers = new ArrayList<>();
        for(int i = 0; i <  raidMembers.size(); i++)   {
            if(raidMembers.get(i).getStatusEffectList().contains(name) && !raidMembers.get(i).isDead)    {
                statusEffectedMembers.add(raidMembers.get(i));
            }
        }
        return statusEffectedMembers;
    }

    public ArrayList<RaidMember> getBuffLessRaidMembers(String name)    {
        ArrayList<RaidMember> buffLess = new ArrayList<>();
        for(int i = 0; i <  raidMembers.size(); i++)   {
            if(!raidMembers.get(i).getStatusEffectList().contains(name))    {
                buffLess.add(raidMembers.get(i));
            }
        }
        return  buffLess;
    }

    public void loadHealingStats()   {
        for(int i = 0; i < raidMembers.size(); i++)   {
            healingTracker.addHealingTracker(raidMembers.get(i).getHealingTracker());
        }
    }

    public int getRaidDamage()  {
        int totalDamage = 0;
        for(int i = 0; i < raidMembers.size(); i++)   {
            totalDamage = totalDamage + raidMembers.get(i).getDamage();
        }
        return totalDamage;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public HealingTracker getHealingTracker()   {
        return healingTracker;
    }


}
