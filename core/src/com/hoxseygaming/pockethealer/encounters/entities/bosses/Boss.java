package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Align;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.Text;
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
    public Player player;
    public ArrayList<Mechanic> mechanics;
    public Texture namePlate;
    public int level;
    public int raidSize;
    public Text nameText;
    public Text announcement;

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
        raidSize = enemies.raidMembers.size();
        target = getMainTank();
        mechanics = new ArrayList<>();

        nameText = new Text(name, 45, Color.BLACK,false, assets);
        nameText.setPosition(getX()+(getWidth()/2) - nameText.getWidth()/2 ,getY() + getHeight()/2 - nameText.getHeight()/2);

        announcement = new Text("",16,Color.RED, false, assets);
        announcement.setPosition(getX(), getY() - announcement.getHeight()-announcement.getHeight()/2);
        announcement.setWrap(true);
        announcement.setAlignment(Align.left);
    }

    public void create()    {
        System.out.println("boss created!");
    }

    public RaidMember getMainTank() {
       return enemies.getRaidMember(0);
    }

    public RaidMember getOffTank() {
        return enemies.getRaidMember(1);
    }

    public void loadMechanics(Mechanic... mechs) {
        for(Mechanic mech: mechs)   {
            mechanics.add(mech);
        }
    }

    public void start() {
        System.out.println("BOSS IS NOW ACTIVE!");
        enemies.start(this);
        for(int i = 0; i < mechanics.size(); i++)   {
            mechanics.get(i).start();
        }
    }

    public void stop()  {
        System.out.println("BOSS IS NOW INACTIVE!");
        for (int i = 0; i <  mechanics.size(); i++)
            mechanics.get(i).stop();
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
            target = enemies.getRandomRaidMember(1).get(0);
        }

    }

    public void setEnemies(Raid enemies)    {
        this.enemies = enemies;
    }

    public int getLevel() {
        return level;
    }

    public Raid getEnemies() {
        return enemies;
    }

    public RaidMember getTarget() {
        return target;
    }

    public void setTarget(RaidMember target) {
        this.target = target;
    }

    public ArrayList<Mechanic> getMechanics() {
        return mechanics;
    }

    public void setMechanics(ArrayList<Mechanic> mechanics) {
        this.mechanics = mechanics;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRaidSize() {
        return raidSize;
    }

    public void setRaidSize(int raidSize) {
        this.raidSize = raidSize;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer()   {
        return player;
    }

    @Override
    public void reset() {
        super.reset();
        enemies = new Raid(raidSize, assets);
        target = getMainTank();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(assets.getTexture("black_bar.png"), getX(), getY(), getWidth(), getHeight());
        batch.draw(assets.getTexture("red_bar.png"), getX()+2, getY()+2, (getWidth()-4), getHeight()-4);
        batch.draw(assets.getTexture("green_bar.png"), getX()+2, getY()+2, (getWidth()-4)*getHpPercent(), getHeight()-4);
        if(target != null)
            batch.draw(assets.getTexture("red_outline_bar.png"), target.getX(), target.getY(), target.getWidth(), target.getHeight());
        //batch.draw(namePlate, getX()+(getWidth()/2)-((getWidth()/3)/2) ,getY()+2,getWidth()/3,getHeight()-5);
        nameText.draw(batch, parentAlpha);
        announcement.draw(batch, parentAlpha);

    }
}
