package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Phase;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.ZombieAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.ZombieBite;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.InfectedEffect;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class ZombieHorde extends Boss {

    private ZombieAttack zombieAttack;
    private ZombieBite zombieBite;

    public ZombieHorde(Assets assets) {
        super("Zombie Horde"," After the cave, a horde of zombies are drifting around. The only through them is through them. Luckily, " +
                "each zombie defeated is one less to deal with. Be careful for their bites, if not treated then that person becomes part of the horde.",
                240,
                new Raid(12,assets),
                assets);
        setId(13);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 0;

        zombieAttack = new ZombieAttack(this, 1.5f);
        zombieBite = new ZombieBite(this, 9f);
        zombieBite.setNumOfTargets(2);

        phaseManager.addPhase(new Phase(this, 0, zombieAttack, zombieBite));
        //loadMechanics(zombieAttack, zombieBite);
    }

    @Override
    public void start() {
        super.start();
        for(int i = 0; i < getEnemies().raidMembers.size(); i++)   {
            getEnemies().getRaidMember(i).addStatusEffect(new InfectedEffect(this));
        }
    }

    @Override
    public void reward() {
        if(player.getLevel() >= getId()) {
            rewardPackage.addNewLevelText();
        }
    }

    @Override
    public void receiveHealing(int output) {
        System.out.println("Before");
        System.out.println("Zombie Max Health: "+getMaxHp());
        System.out.println("Zombie Health: "+getHp());
        System.out.println("Zombie Health %: "+getHpPercent());

        super.receiveHealing(output);

        System.out.println("After");
        System.out.println("Zombie Max Health: "+getMaxHp());
        System.out.println("Zombie Health: "+getHp());
        System.out.println("Zombie Health %: "+getHpPercent());
    }
}
