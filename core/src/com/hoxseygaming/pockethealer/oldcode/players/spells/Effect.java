package com.hoxseygaming.pockethealer.oldcode.players.spells;

import com.hoxseygaming.pockethealer.oldcode.players.Member;

/**
 * Created by Hoxsey on 5/27/2017.
 */
public class Effect {

    public enum Mechanic {
        DAMAGE, DAMAGE2, DAMAGE3, DAMAGEALL,

        BLEED, POSION, ABSORBHEALING,

        HEAL, HEAL2, HEAL3, HEALPARTY, HEALOVERTIME,

        DISPEL, SHIELD
    }

    public Effect(Mechanic effect, int output, int duration) {
        this.effect = effect;
        this.output = output;
        this.duration = duration;
    }

    private Mechanic effect;
    private int output;
    private float duration;

    public Mechanic getEffect() {
        return effect;
    }

    public void bleedEffect(int count, Member target)   {
        target.takeDamage(output*(count/10));
    }

    public void cleaveEffect(Member m [])  {
        if(m[0] !=  null)
            m[0].takeDamage(output);
        if(m[1] !=  null)
            m[1].takeDamage(output);

    }

    public void setEffect(Mechanic effect) {
        this.effect = effect;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
}
