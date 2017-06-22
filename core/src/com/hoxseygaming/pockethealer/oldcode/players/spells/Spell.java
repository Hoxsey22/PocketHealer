package com.hoxseygaming.pockethealer.oldcode.players.spells;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Hoxsey on 5/27/2017.
 */
public enum Spell {
        HEAL("Heal",
                1,
                "A powerful single target heal.",
                10,
                1.5f,
                0.50f,
                new Effect(Effect.Mechanic.HEAL, 40,0),
                new Texture("spell_holy_greaterheal.jpg")),
        FLASH_HEAL("Flash Heal",
                2,"A powerful, but fast single target heal. Cost more!",
                25,
                0.7f,
                0.50f,
                new Effect(Effect.Mechanic.HEAL, 40,0),
                new Texture("spell_holy_flashheal.jpg")),
        BARRIER("Barrier",
                3,
                "Shield the target for half their health",
                35,
                0.0f,
                4f,
                new Effect(Effect.Mechanic.SHIELD, 60, 0),
                new Texture("spell_holy_powerwordshield.jpg")),
        RENEW("Renew",
                4,
                "Heal the target over time",
                15,
                0.0f,
                0.50f,
                new Effect(Effect.Mechanic.HEALOVERTIME, 7,10),
                new Texture("spell_holy_renew.jpg")),
        DIVINE_HYNM("Divine Hymn",
                5,
                "Heals the entire party with a powerful heal over time",
                100,
                0,
                new Effect(Effect.Mechanic.HEALPARTY, 35,2)),
        DISPEL("Dispel",
                6,
                "Removes magic effect",
                50,
                0.0f,
                new Effect(Effect.Mechanic.DISPEL, 0,0)),
        BURST_HEAL("Burst Heal",
                7,
                "Heals two targets if talents then 3",
                60,
                1.5f,
                new Effect(Effect.Mechanic.HEAL2, 40,0)),
        TANK_DAMAGE("Tank Damage",
                10,
                "Heavy damage toward the current target.",
                0,
                0f,
                new Effect(Effect.Mechanic.DAMAGE, 30,0)),
        STOMP("Stomp!",
                11,
                "Light damage toward the entire team.",
                0,
                0f,
                new Effect(Effect.Mechanic.DAMAGEALL, 15,0)),
        CLEAVE("Cleave",
            12,
            "Swings and cleaves 2 other targets",
            0,
            0f,
            new Effect(Effect.Mechanic.DAMAGE2, 20,0)),
        BLEED("Bleed",
            11,
            "Tank damage",
            0,
            0f,
            new Effect(Effect.Mechanic.BLEED, 10,10000));



    private String name;
    private int ID;
    private String description;
    private int cost;
    private float casttime;
    private float cd;
    private Effect effect;
    private Texture image;
    private float cdCounter;


    /**
     *
     * @param name
     * @param ID
     * @param description
     * @param cost
     * @param casttime
     * @param effect
     */
    Spell(String name, int ID, String description, int cost, float casttime, Effect effect, Texture image)  {
        this.name = name;
        this.ID = ID;
        this.description = description;
        this.cost = cost;
        this.casttime = casttime;
        this.effect = effect;
        this.image = image;
    }

    Spell(String name, int ID, String description, int cost, float casttime, float cd, Effect effect, Texture image)  {
        this.name = name;
        this.ID = ID;
        this.description = description;
        this.cost = cost;
        this.casttime = casttime;
        this.cd = cd;
        this.effect = effect;
        this.image = image;
        cdCounter = 0;
    }

    Spell(String name, int ID, String description, int cost, float casttime, Effect effect) {
        this.name = name;
        this.ID = ID;
        this.description = description;
        this.cost = cost;
        this.casttime = casttime;
        this.effect = effect;
    }


    public String getName() {
            return name;
        }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public float getCasttime() {
        return casttime;
    }

    public void setCasttime(float casttime) {
        this.casttime = casttime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCd() {
        return cd;
    }

    public void setCd(float cd) {
        this.cd = cd;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public float getCdCounter() {
        return cdCounter;
    }

    public void setCdCounter(int cdCounter) {
        this.cdCounter = (int)(cd*10 - cdCounter);
    }
}
