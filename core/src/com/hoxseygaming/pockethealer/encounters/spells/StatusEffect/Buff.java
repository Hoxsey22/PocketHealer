package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect;

import com.badlogic.gdx.graphics.Texture;
import com.hoxseygaming.pockethealer.Player;

/**
 * Created by Hoxsey on 12/1/2017.
 */

public abstract class Buff extends StatusEffect {

    private Player owner;

    /**
     * A buff is a type of status effect that is helpful to raid member and the player.
     * All buffs should come from the player.
     *
     * @param id: ID of the status effect and should be unique.
     * @param name: Name of the status effect.
     * @param description: A brief description of the status effect.
     * @param texture: The texture that will create the icon.
     * @param duration: The time of which the status effect will last.
     * @param speed: The time of which the status effect will apply effect.
     * @param modValue: The mod value that will change a specific stat.
     * @param dispellable : Check if this status effect can be dispel.
     */
    public Buff(Player owner, int id, String name, String description, Texture texture, float duration, float speed, int modValue, boolean dispellable) {
        super(id, name, description, texture, duration, speed, modValue, dispellable, owner.getAssets());
        this.owner = owner;
    }

    public abstract void applyEffect();

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
