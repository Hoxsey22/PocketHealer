package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 7/27/2017.
 */

public class TutorialFrame extends Group {

    public Image disableBG;
    public Image frame;
    public Text text;
    public Table table;
    public Assets assets;
    public BlinkingImage pointer;
    public int stage;
    public Player player;
    public Boss boss;
    public boolean isComplete;

    public TutorialFrame(Player player, Boss boss, Assets assets)  {
        this.assets = assets;
        this.player = player;
        this.boss = boss;
        pointer = new BlinkingImage(assets.getTexture(assets.arrowPointer));
        pointer.setBounds(-100, -100,50,50);
        stage = 1;
        isComplete = false;
        create();
    }

    public void create()    {
        disableBG = new Image(assets.getTexture(assets.disableBG));
        disableBG.setName("disable bg");
        disableBG.setBounds(0,0,PocketHealer.WIDTH, PocketHealer.HEIGHT);

        frame = new Image(assets.getTexture(assets.endGameFrame));
        frame.setName("frame");
        frame.setPosition(PocketHealer.WIDTH/2 - frame.getWidth()/2, PocketHealer.HEIGHT/2 - frame.getWidth()/2);

        addActor(disableBG);
        addActor(frame);
        addActor(pointer);
        createText();
    }

    public void createText()    {
        table = new Table();
        table.setBounds(frame.getX()+5,frame.getY(), frame.getWidth(), frame.getHeight());


        text = new Text("Welcome to \nPocket Healer!",24, Color.WHITE, false, assets);
        text.setWrap(true);
        text.setAlignment(Align.center);

        table.add(text.getLabel()).width(table.getWidth()-10);

        addActor(table);
    }

    public void nextStage() {
        stage++;
        switch(stage)    {
            case 2:
                text.setText("The object as a healer is to keep everyone alive until your team defeats the boss.");
                break;
            case 3:
                text.setText("This is the boss frame. \nWhen the boss frame is empty, you have defeated the boss.");

                pointer.setPosition(0,boss.getY());
                pointer.start();

                break;
            case 4:
                text.setText("These are the raid frames. These frames you need to pay close attention to." +
                        "When a raid member dies, the damage toward the boss is lowered.");

                pointer.setPosition(boss.getEnemies().getRaidMember(1).getX()-pointer.getWidth()-2,
                        boss.getEnemies().getRaidMember(1).getY());
                break;
            case 5:
                text.setText("Tanks have more health, but do moderate damage. Damage dealers have moderate health, but high damage." +
                        "Lastly, healers have moderate health and low damage.");
            case 6:
                text.setText("This is your spell bar. Each spell has it own benefits. To cast a spell, select a raid member and then press the spell.");
                pointer.setPosition(player.getSpellBar().getSpell(0).getX()-pointer.getWidth()-2,
                        player.getSpellBar().getSpell(0).getY());
                break;
            case 7:
                text.setText("This is your mana bar. Each spell cost mana and once your mana is depleted, you won't be able to use a spell.");
                pointer.setPosition(player.getManaBar().getX()-pointer.getWidth()-2,
                        player.getSpellBar().getSpell(0).getY());
            case 8:
                text.setText("To conclude, be mindful of your mana and keep your team alive!");
                break;
            case 9:
                text.setText("Boss is about to begin! Be ready healer!");
                break;
            case 10:
                isComplete = true;
                boss.start();
                break;
        }
    }

    public String hitButton(float x, float y)   {
        Actor hit = hit(x,y,false);

        if(hit !=  null)    {
            return hit.getName();
        }
        return "";
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(!isComplete)
            super.draw(batch, parentAlpha);
    }
}
