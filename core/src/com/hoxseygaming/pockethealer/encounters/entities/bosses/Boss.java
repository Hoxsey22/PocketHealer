package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.Text;
import com.hoxseygaming.pockethealer.encounters.entities.Entity;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Mechanic;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.PhaseManager;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/16/2017.
 */
public abstract class Boss extends Entity {

    public Raid enemies;
    public RaidMember target;
    public Player player;
    public ArrayList<Mechanic> mechanics;
    public PhaseManager phaseManager;
    public Texture namePlate;
    public int level;
    public int raidSize;
    public Text nameText;
    public Text announcement;
    public String rewardDescription;
    public boolean isDefeated;
    public String description;
    public RewardPackage rewardPackage;

    public Boss(String name, String description, int maxHp, Assets assets) {
        super(name, maxHp, assets);
        this.description = description;
        setBounds(20, 740, 445, 40);
        target = getMainTank();
        mechanics = new ArrayList<>();
        rewardPackage = new RewardPackage(this);
    }

    public Boss(String name, String description, int bossLength, Raid enemies, Assets assets) {
        super(name, enemies.getRaidDamage()*bossLength, assets);
        setBounds(20, 740, 445, 40);
        this.description = description;
        this.enemies = enemies;
        raidSize = enemies.raidMembers.size();
        target = getMainTank();
        mechanics = new ArrayList<>();
        phaseManager = new PhaseManager();

        nameText = new Text(name, 45, Color.BLACK,false, assets);
        nameText.setPosition(getX()+(getWidth()/2) - nameText.getWidth()/2 ,getY() + getHeight()/2 - nameText.getHeight()/2);

        announcement = new Text("",16,Color.RED, false, assets);
        announcement.setPosition(getX(), enemies.getRaidMember(0).getY()+enemies.getRaidMember(0).getHeight()+10);
        announcement.setWrap(true);
        announcement.setAlignment(Align.left);
        rewardPackage = new RewardPackage(this);
        rewardDescription = "";
    }

    public void create()    {
        System.out.println("boss created!");
    }

    public void update()    {
        // checks if there are any additional phases
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

    public void displayAnnouncementTimer(final String text)  {
        final Timer announcementTimer = new Timer();

        announcementTimer.scheduleTask(new Timer.Task() {

            int counter = 0;

            @Override
            public void run() {

                counter++;
                if(counter == 1)    {
                    announcement.setText(text);
                }
                if(counter == 2)    {
                    announcement.setText("");
                    announcementTimer.clear();
                    announcementTimer.stop();
                }

            }
        },0.1f, 2f);
    }

    public void start() {
        System.out.println("BOSS IS NOW ACTIVE!");
        create();
        enemies.start(this);
        phaseManager.startPhase();

       /* for(int i = 0; i < mechanics.size(); i++)   {
            mechanics.get(i).start();
        }
        */
    }

    public void stop()  {
        System.out.println("BOSS IS NOW INACTIVE!");
        /*for (int i = 0; i <  mechanics.size(); i++)
            mechanics.get(i).stop();
            */
        phaseManager.cleanPhases();
    }

    public void nextThreat() {

        ArrayList<RaidMember> tanks = enemies.tanksAlive();

        if (!enemies.isRaidDead()) {

            switch (tanks.size())   {
                case 0:
                    target = enemies.getRandomRaidMember(1).get(0);
                    break;
                case 1:
                    target = tanks.get(0);
                    break;
                case 2:
                    if(target == tanks.get(0))  {
                        target = tanks.get(1);
                    }
                    else {
                        target = tanks.get(0);
                    }
                    break;
            }


            /*
            for (int i = 0; i < enemies.raidMembers.size(); i++) {
                if (enemies.getRaidMember(i).getRole() == "Tank" && !enemies.getRaidMember(i).isDead()) {
                    target = enemies.getRaidMember(i);
                    return;
                }
            }
            System.out.println("New threat is random!");
            target = enemies.getRandomRaidMember(1).get(0);
            */
        }

    }

    public RaidMember getNextThreat()   {
        ArrayList<RaidMember> tanks = enemies.tanksAlive();

        if (!enemies.isRaidDead()) {

            switch (tanks.size()) {
                case 0:
                    return enemies.getRandomRaidMember(1).get(0);
                case 1:
                    return tanks.get(0);
                case 2:
                    if (target == tanks.get(0)) {
                        return tanks.get(1);
                    } else {
                        return tanks.get(0);
                    }
            }
        }
        return null;
    }

    public abstract void reward();

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

    public boolean isDefeated() {
        return isDefeated;
    }

    public void setDefeated(boolean defeated) {
        isDefeated = defeated;
    }

    public Player getPlayer()   {
        return player;
    }

    public String getRewardDescription() {
        return rewardDescription;
    }

    public void setRewardDescription(String rewardDescription) {
        this.rewardDescription = rewardDescription;
    }

    public void rewardPoint()   {
        player.getTalentTree().addPoint();
    }

    public void rewardSpell(String spell)   {
        //give player spell
        rewardDescription = rewardDescription +"\n"+spell+" rewarded";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void reset() {
        super.reset();
        enemies = new Raid(raidSize, assets);
        target = getMainTank();
        phaseManager.cleanPhases();
    }

    public RewardPackage getRewardPackage() {
        return rewardPackage;
    }

    public void setRewardPackage(RewardPackage rewardPackage) {
        this.rewardPackage = rewardPackage;
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
