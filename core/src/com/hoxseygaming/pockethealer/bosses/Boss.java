package com.hoxseygaming.pockethealer.bosses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.players.Member;
import com.hoxseygaming.pockethealer.players.Party;
import com.hoxseygaming.pockethealer.players.RaidFrame;
import com.hoxseygaming.pockethealer.spells.Effect;
import com.hoxseygaming.pockethealer.spells.Spell;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 5/28/2017.
 */
public class Boss {

    private int maxHp;
    private int hp;
    private Rectangle healthBar;
    private Timer tankSwapTimer;
    private Timer tankDamageTimer;
    private Timer abilityTimer;
    private Timer announcementTimer;
    private Timer takeDamageTimer;
    private Member target;
    private Party enemies;
    private ArrayList<Spell>spells;
    private String announcement;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private Texture name;
    private boolean isDead;


    public Boss(Party party, int maxHp) {
        this.maxHp = maxHp;
        hp = maxHp;
        enemies = party;
        healthBar = new Rectangle();
        abilityTimer = new Timer();
        tankDamageTimer = new Timer();
        tankSwapTimer = new Timer();
        announcementTimer = new Timer();
        shapeRenderer = new ShapeRenderer();
        name = new Texture("badlogic.jpg");
        isDead = false;
    }

    public void startTimers() {
        tankDamageTimer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                applySpell(Spell.TANK_DAMAGE);
            }
        },6.5f,1.5f);

        abilityTimer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                applySpell(Spell.CLEAVE);
            }
        }, 9.0f, 3.5f);

        tankSwapTimer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                if(target == enemies.getMember(0) && !enemies.getMember(1).isDead())
                    target = enemies.getMember(1);
                else if (!enemies.getMember(0).isDead())
                    target = enemies.getMember(0);
                else if(enemies.getMember(0).isDead() && enemies.getMember(1).isDead())
                    tankSwapTimer.clear();
            }
        }, 13f, 8f);

        announcementTimer.scheduleTask(new Timer.Task() {
            int counter = 1;
            @Override
            public void run() {
                if(counter % 2 == 0) {
                    displayAnnouncement();
                }
                else    {
                    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                }
                counter++;
                System.out.println(counter);
            }
        },8.0f, 4.0f);

        takeDamageTimer = new Timer();
        final Party p = enemies;
        takeDamageTimer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                for(int i =0; i < p.getSize(); i++)   {
                    if(!p.getMember(i).isDead())
                        takeDamage(p.getMember(i).getDamage());
                }
            }
        },5,1);
    }

    public Rectangle getHealthBar()   {
        return healthBar;
    }

    public void getTank() {
        for (int i = 0; i < enemies.getSize(); i++) {
            if(enemies.getMember(i).getRole() == Member.TANK)
                target = enemies.getMember(i);
        }
        target = enemies.getMember(0);
    }

    public void setTarget(Member newTarget) {
        target = newTarget;
    }

    public Member getTarget()   {
        return target;
    }

    public void applySpell(Spell spell)    {
        final Spell s = spell;

        switch (spell.getEffect().getEffect())  {
            case DAMAGE:
                target.takeDamage(spell.getEffect().getOutput());
                if(target.isDead()) {
                    target = enemies.swapTank(target);
                    System.out.println("HIT");
                }
                break;
            case BLEED:
                    final float duration = spell.getEffect().getDuration();
                    final Member t = target;
                    abilityTimer.scheduleTask(new Timer.Task() {
                        int counter = 0;

                        @Override
                        public void run() {
                            counter++;
                            if(t.getHp() > t.getMaxHp()*0.90) {
                                t.removeEffect(Effect.Mechanic.BLEED);
                                abilityTimer.clear();

                            }
                            if(counter % 10 == 0)
                                s.getEffect().bleedEffect(counter, t);


                        }
                    },0.1f,0.1f,(int)duration);
                    target.applyEffect(Effect.Mechanic.BLEED);
                break;
            case DAMAGE2:
                spell.getEffect().cleaveEffect(enemies.findTwo());
                break;
            case DAMAGEALL:
                for (int i = 0; i < enemies.getSize(); i++)
                    enemies.getMember(i).takeDamage(spell.getEffect().getOutput());
                break;
        }
    }

    public void displayAnnouncement()   {
        System.out.println(announcement);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Party getEnemies() {
        return enemies;
    }

    public RaidFrame getTargetFrame()   {
        return enemies.getMemberFrame(enemies.party.indexOf(target));
    }

    public void setEnemies(Party enemies) {
        this.enemies = enemies;
    }

    public Texture getName() {
        return name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void takeDamage(int damage)    {
        if(!isDead)
            hp = hp - damage;
        if(hp < 1)
            isDead = true;
        System.out.println("Boss: "+hp);
    }

    public void receiveHeal(int output)   {
        hp = hp + output;
    }

    public void draw(SpriteBatch sb)  {
        // background bar
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.rect(15, 740, 445, 40);
        shapeRenderer.end();

        // health bar
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(20, 745, 435*((float)hp /(float)maxHp), 30);
        shapeRenderer.end();

        // target outline
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect((int)getTargetFrame().getFrame().getX()-3,(int)getTargetFrame().getFrame().getY()-3,
                                                    (int)getTargetFrame().getFrame().width+9, (int)getTargetFrame().getFrame().height+9);
        shapeRenderer.end();

        sb.begin();
            sb.draw(getName(),20+(435/2)-getName().getWidth()/2,745);
        sb.end();
    }


}
