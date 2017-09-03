package com.hoxseygaming.pockethealer.encounters.spells;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.Text;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 6/17/2017.
 */
public abstract class Spell extends Actor {


    public enum EffectType  {
        HEAL,HEALALL,HEALMULTIPLE,SHIELD,HEALOVERTIME,DAMAGEHEAL
    }

    public int index;
    public Player owner;
    public String name;
    public String description;
    public EffectType effectType;
    public int output;
    public int cost;
    public float cooldown;
    public float cdCounter;
    public boolean isReady;
    public boolean isCasting;
    public Texture image;
    public float cdPercentage;
    public Timer cdTimer;
    public Assets assets;
    private Text text;
    public RaidMember target;
    public CriticalChance criticalChance;
//
    /**
     * @param player
     * @param name
     * @param description
     * @param effectType
     * @param output
     * @param cost
     * @param cooldown
     * @param index
     * @param assets
     */
    public Spell(Player player, String name, String description, EffectType effectType, int output,
                 int cost, float cooldown, int index, Assets assets) {
        setBounds(SpellData.positions[index].x,SpellData.positions[index].y,80,80);
        owner = player;
        this.assets = assets;
        this.index = index;
        this.name = name;
        this.description = description;
        this.output = output;
        this.cost = cost;
        this.cooldown = cooldown;
        this.effectType = effectType;
        isReady = true;
        isCasting = false;
        cdCounter = 0f;
        cdPercentage = 1f;
        criticalChance = new CriticalChance(0);
        setupText();
    }
    public abstract void castSpell();

    public abstract void applySpell(RaidMember target);

    /**
     * This starts the cooldown timer for the spell
     */
    public void startCooldownTimer()    {
            cdTimer = new Timer();
            isReady = false;
            setCdCounter(cooldown);

            cdTimer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    cdCount();
                    System.out.println(name + " " + cdCounter);
                    if(getCdPercentage() <= 0f) {
                        cdTimer.clear();
                        isReady = true;
                        setCdCounter(0);
                    }

                }
            },0.1f,0.1f, (int)(cooldown/0.1));
    }

    public void setupText() {
        text = new Text("", 24, Color.WHITE, true,assets);
        text.setPosition(getX()+getWidth()/2 - text.getXCenter(), getY() + getHeight()/2 -
                text.getYCenter());
        text.setAlignment(Align.center);
    }

    /**
     * Gets the player that owns the the spell
     * @return Player
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Changes the owner of the spell
     * @param owner
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     *
     * @return boolean
     */
    public boolean isCasting() {
        return isCasting;
    }

    public void setCasting(boolean casting) {
        isCasting = casting;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public float getCdCounter() {
        return cdCounter;
    }

    public void setCdCounter(float cdCounter) {
        this.cdCounter = cdCounter;
    }

    public void cdCount()   {
        cdCounter = cdCounter - 0.1f;
        getCdPercentage();
    }

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    public EffectType getEffectType() {
        return effectType;
    }

    public void setEffectType(EffectType effectType) {
        this.effectType = effectType;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public float getCdPercentage() {
        return cdPercentage = cdCounter/cooldown;
    }

    public void setCdPercentage(float cdPercentage) {
        this.cdPercentage = cdPercentage;
    }

    public void useMana()   {
        owner.mana = owner.mana - cost;
    }

    public void setCriticalChance(int chance)  {
        criticalChance.setChanceThreshold(chance);
    }

    public boolean isCastable() {
        if(owner.target == null)    {
            System.out.println("NO TARGET FOUND!");
            return false;
        }
        if(owner.getMana() < cost) {
            System.out.println("OUT OF MANA!");
            return false;
        }
        if(!isReady) {
            System.out.println(name + " IS NOT READY!");
            return false;
        }
        if(isCasting) {
            System.out.println(name + " IS STILL CASTING!");
            return false;
        }
        if(owner.getTarget().isDead())    {
            System.out.println("ID:"+owner.getTarget().id+" Target is dead!");
            return false;
        }

        return true;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    public RaidMember getTarget() {
        return owner.getTarget();
    }

    public void setTarget(RaidMember newTarget) {
        owner.setTarget(newTarget);
    }

    public void resetCD()   {
        if(cdTimer != null) {
            cdTimer.stop();
            cdTimer.clear();
        }
        isReady = true;
        setCdCounter(0);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image, getX(),getY(),getWidth(), getHeight());
        batch.draw(assets.getTexture("cooldown_bar.png"), getX(),getY(),getWidth(), getHeight()*getCdPercentage());
        text.setText(String.format("%.1f",cdCounter));
        if(!isReady)
            text.draw(batch, parentAlpha);
    }
}
