package com.hoxseygaming.pockethealer.oldcode.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.PocketHealer;
import com.hoxseygaming.pockethealer.oldcode.players.Member;
import com.hoxseygaming.pockethealer.oldcode.players.Party;
import com.hoxseygaming.pockethealer.oldcode.players.Player;
import com.hoxseygaming.pockethealer.oldcode.players.spells.Effect;
import com.hoxseygaming.pockethealer.oldcode.players.spells.Spell;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 5/28/2017.
 */
public class HealingEngine {
    private Timer hotTimer;
    private Timer castTimer;
    private Timer spellCDTimer;
    private boolean isCasting;
    private Player player;
    private Party party;
    private Member target;
    private Effect effect;
    private Rectangle spellButtons [] = {new Rectangle(20,110,80,95), new Rectangle(135,110,80,95),
                                        new Rectangle(250,110,80,95),new Rectangle(365,110,80,95)};
    private ShapeRenderer shapeRenderer;
    private ArrayList<Effect> spellsOnCD;
    private float castbarPercent;

    public HealingEngine(Player player, Party party)  {
        this.player = player;
        this.party = party;
        isCasting = false;
        shapeRenderer = new ShapeRenderer();
        castbarPercent = 0.0f;
        spellsOnCD = new ArrayList<Effect>();
    }

    public void handleInput() {

        Gdx.input.setInputProcessor(new InputProcessor() {

            @Override
            public boolean keyDown(int keycode) {
                switch (keycode)    {
                    case Input.Keys.NUM_1:
                        castSpell(Spell.HEAL);
                        break;
                    case Input.Keys.NUM_2:
                        if(party.target != null)
                            party.target.getMember().takeDamage(40);
                        break;
                }
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                party.setTarget(screenX, PocketHealer.HEIGHT-screenY);
                if(party.target != null) {
                    target = party.target.getMember();

                    for (int i = 0; i < player.getSpellButtons().size(); i++) {
                        if (player.getSpellButtons().get(i).contains(screenX, PocketHealer.HEIGHT - screenY)) {
                            if (isCasting == false)
                                castSpell(player.getSpell(i));
                            break;
                        }
                    }
                }
                System.out.println(screenX+","+(PocketHealer.HEIGHT-screenY));
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        });
    }

    public void castSpell(Spell spell)    {
        if(spellsOnCD.contains(spell.getEffect()))    {
            System.out.println(spell.getEffect().getEffect().name()+" IS NOT READY YET!");
            return;
        }
        if(party.target != null && !party.target.getMember().isDead() && !isCasting) {
            if (player.getMana() > spell.getCost()) {
                player.useMana(spell.getCost());
                effect = spell.getEffect();
                startCooldown(spell);

                if(spell.getCasttime() < 0)
                    applySpell(target);
                else {
                    startCastTimer(spell);
                }

            } else {
                System.out.println("OUT OF MANA!");
            }
        }
    }

    public void startCastTimer(Spell spell)    {

        final Member selectedTarget = target;
        final int duration = (int)(spell.getCasttime()/0.05f);
        isCasting = true;
        castTimer = new Timer();
        castTimer.scheduleTask(new Timer.Task() {
            int timerCounter = 0;

            @Override
            public void run() {
                timerCounter++;
                setCastbarPercent(((float) timerCounter / (float) duration));
                if (timerCounter >= duration) {
                    applySpell(selectedTarget);
                    setCastbarPercent(0f);
                    isCasting = false;
                    castTimer.clear();
                }
            }
        }, 0.05f, 0.05f, duration);
    }

    public void applySpell(Member tar) {
        switch (effect.getEffect())  {
            case HEAL:
                tar.receiveHealing(effect.getOutput());
                break;
            case HEAL2:
                ArrayList<Member> targets = party.getMemberWithLowestHp(1,tar);

                tar.receiveHealing(effect.getOutput());
                targets.get(0).receiveHealing(effect.getOutput());
                break;
            case HEALPARTY:
                // dont know what to do with this yet
                break;
            case HEALOVERTIME:
                applyHealOverTime();
                break;
            case SHIELD:
                if(!tar.containsEffects(effect.getEffect()))
                    tar.applyEffect(effect.getEffect());
                tar.applyShield(effect.getOutput());
                // dont know what to do with this yet
                break;
        }
    }

    public void applyHealOverTime() {
        if (!target.containsEffects(Effect.Mechanic.HEALOVERTIME)) {
            target.applyEffect(effect.getEffect());
            hotTimer = new Timer();
        }
        else
            hotTimer.clear();

        final Member t = target;
        final Effect e = effect;
        hotTimer.scheduleTask(new Timer.Task() {
            int counter = 0;
            int duration = (int)effect.getDuration()/2;
            @Override
            public void run() {
                t.receiveHealing(effect.getOutput());
                counter++;
                System.out.println("Renew is ticking! "+counter);
                if(counter == duration)    {
                    t.removeEffect(e.getEffect());
                    System.out.println("Renew expired");
                }
            }
        }, 2f, 2f, (int)e.getDuration()/2);

    }

    public void startCooldown(Spell s)  {
        spellCDTimer = new Timer();
        spellsOnCD.add(s.getEffect());

        final Spell spell = s;
        final int duration = (int)(spell.getCd()/0.1f);
        spell.setCdCounter(0);
        System.out.println(spell.getName()+" duration: "+duration);
        spellCDTimer.schedule(new Timer.Task() {
            int counter = 0;
            @Override
            public void run() {
                // reduce timers visual
                counter++;
                spell.setCdCounter(counter);
                /*
                System.out.println("CD: "+(spell.getCd()*10));
                System.out.println("counter: "+spell.getCdCounter());
                System.out.println("timer: "+(90*(1 - spell.getCdCounter() /(spell.getCd()*10))));
                */
                System.out.println(spell.getName()+" "+counter);
                if(counter >= duration) {
                    spellsOnCD.remove(spellsOnCD.indexOf(spell.getEffect()));
                    spellCDTimer.clear();
                    System.out.println(spell.name()+" is off cooldown.");
                }

            }
        },0.1f,0.1f, duration-1);

    }

    public void setCastbarPercent(float percent) {
        castbarPercent = percent;
    }

    public void draw(SpriteBatch sb)  {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.rect(10,230, 460,40);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.rect(15,235, 455*(castbarPercent),30);
        shapeRenderer.end();


    }




}
