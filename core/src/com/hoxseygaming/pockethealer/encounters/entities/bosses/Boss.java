package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.Entity;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Mechanic;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/16/2017.
 */
public class Boss extends Entity {

    public Raid enemies;
    public RaidMember target;
    public ArrayList<Mechanic> mechanics;
    public Texture namePlate;

    public Boss(String name, int maxHp, Assets assets) {
        super(name, maxHp, assets);
        setBounds(20, 740, 445, 40);
        target = getMainTank();
        mechanics = new ArrayList<>();
    }

    public Boss(String name, int maxHp, Raid enemies, Assets assets) {
        super(name, maxHp, assets);
        setBounds(20, 740, 445, 40);
        this.enemies = enemies;
        target = getMainTank();
        mechanics = new ArrayList<>();
    }


    public RaidMember getMainTank() {
       return enemies.getRaidMember(0);
    }

    public RaidMember getOffTank() {
        return enemies.getRaidMember(1);
    }

    public void start() {
        System.out.println("BOSS IS NOW ACTIVE!");
        enemies.startRaidDamageTimer(this);
    }

    public void stop()  {
        System.out.println("BOSS IS NOW INACTIVE!");
        for (int i = 0; i <  mechanics.size(); i++)
            mechanics.get(i).stop();
    }

    public void update()    {
        System.out.println("Updating Boss!");
    }

    public void nextThreat() {
        RaidMember temp = null;
        if (!enemies.isRaidDead()) {
            for (int i = 0; i < enemies.raidMembers.size(); i++) {
                if (enemies.getRaidMember(i).getRole() == "Tank" && !enemies.getRaidMember(i).isDead()) {
                    target =enemies.getRaidMember(i);
                    return;
                }
            }
            System.out.println("New threat is random!");
            target = enemies.getRandomRaidMember(1)[0];
        }

    }

    public void setEnemies(Raid enemies)    {
        this.enemies = enemies;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(assets.getTexture("black_bar.png"), getX(), getY(), getWidth(), getHeight());
        batch.draw(assets.getTexture("red_bar.png"), getX()+2, getY()+2, (getWidth()-4), getHeight()-4);
        batch.draw(assets.getTexture("green_bar.png"), getX()+2, getY()+2, (getWidth()-4)*getHpPercent(), getHeight()-4);
        if(target != null)
            batch.draw(assets.getTexture("red_outline_bar.png"), target.getX(), target.getY(), target.getWidth(), target.getHeight());
        batch.draw(namePlate, getX()+(getWidth()/2)-((getWidth()/3)/2) ,getY()+2,getWidth()/3,getHeight()-5);

    }
}
