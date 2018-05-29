package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff;

import com.badlogic.gdx.graphics.Texture;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.StatusEffect;

/**
 * Created by Hoxsey on 12/1/2017.
 */

public abstract class Debuff extends StatusEffect {

    private Boss owner;

    /**
     * A debuff is a negative status effect that is commonly from a boss and is
     * put on a raid member.
     *
     * @param owner       : The owner of the buff.
     * @param id          : ID of the status effect and should be unique.
     * @param name        : Name of the status effect.
     * @param description : A brief description of the status effect.
     * @param texture     : The texture that will create the icon.
     * @param duration    : The time of which the status effect will last.
     * @param speed       : The time of which the status effect will apply effect.
     * @param modValue    : The mod value that will change a specific stat.
     * @param dispellable : Check if this status effect can be dispel.
     *
     * @
     */
    public Debuff(Boss owner, int id, String name, String description, Texture texture, float duration, float speed, int modValue, boolean dispellable) {
        super(id, name, description, texture, duration, speed, modValue, dispellable, owner.getAssets());
        this.owner = owner;
    }

    public abstract void applyEffect();

    public Boss getOwner() {
        return owner;
    }

    public void setOwner(Boss owner) {
        this.owner = owner;
    }


}
