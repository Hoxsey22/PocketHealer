package com.hoxseygaming.pockethealer.encounters.spells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.EncounterData;

/**
 * Created by Hoxsey on 6/17/2017.
 */
public class Spell extends Actor {


    public enum EffectType  {
        HEAL,HEALALL,HEALMULTIPLE,SHIELD,HEALOVERTIME
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
    private Label label;
    public CriticalChance criticalChance;


    public Spell(String name, String description, EffectType effectType, int output, int cost, float cooldown, int index) {
        setBounds(SpellData.positions[index].x,SpellData.positions[index].y,80,80);
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
        setupFont();
    }

    /**
     * Check to make sure the player can cast this spell
     */
    public void castSpell() {
        System.out.println("Casted a spell!");
    }

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
                        System.out.println(name+" is off cooldown.");
                        isReady = true;
                        setCdCounter(0);
                    }

                }
            },0.1f,0.1f, (int)(cooldown/0.1));
    }

    public void setupFont() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        BitmapFont font = assets.getFont("cooldown_font.fnt");
        labelStyle.font = font;
        labelStyle.fontColor = Color.WHITE;

        label = new Label(cdCounter+"",labelStyle);
        label.setSize(getWidth(), getHeight());
        label.setPosition(getX(), getY());
        label.setAlignment(Align.center);
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


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image, getX(),getY(),getWidth(), getHeight());
        batch.draw(assets.getTexture("cooldown_bar.png"), getX(),getY(),getWidth(), getHeight()*getCdPercentage());
        label.setText(String.format("%.1f",cdCounter));
        if(!isReady)
            label.draw(batch, parentAlpha);
    }
}
