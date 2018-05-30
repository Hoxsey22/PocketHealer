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

    private Image disableBG;
    private Image frame;
    private Text text;
    private Table table;
    private Assets assets;
    private BlinkingImage pointer;
    private int stageNumber;
    private Player player;
    private Boss boss;
    private boolean isComplete;

    public TutorialFrame(Player player, Boss boss, Assets assets)  {
        this.setAssets(assets);
        this.setPlayer(player);
        this.setBoss(boss);
        setPointer(new BlinkingImage(assets.getTexture(assets.arrowPointer)));
        getPointer().setBounds(-100, -100,50,50);
        setStageNumber(1);
        setComplete(false);
        create();
    }

    private void create()    {
        setDisableBG(new Image(getAssets().getTexture(getAssets().disableBG)));
        getDisableBG().setName("disable bg");
        getDisableBG().setBounds(0,0,PocketHealer.WIDTH, PocketHealer.HEIGHT);

        setFrame(new Image(getAssets().getTexture(getAssets().endGameFrame)));
        getFrame().setName("frame");
        getFrame().setPosition(PocketHealer.WIDTH/2 - getFrame().getWidth()/2, PocketHealer.HEIGHT/2 - getFrame().getWidth()/2);

        addActor(getDisableBG());
        addActor(getFrame());
        addActor(getPointer());
        createText();
    }

    private void createText()    {
        setTable(new Table());
        getTable().setBounds(getFrame().getX()+5, getFrame().getY(), getFrame().getWidth(), getFrame().getHeight());


        setText(new Text("Welcome to \nPocket Healer!",24, Color.WHITE, false, getAssets()));
        getText().setWrap();
        getText().setAlignment(Align.center);

        getTable().add(getText().getLabel()).width(getTable().getWidth()-10);

        addActor(getTable());
    }

    public void nextStage() {
        setStageNumber(getStageNumber() + 1);
        switch(getStageNumber())    {
            case 2:
                getText().setText("The objective as a healer is to keep everyone alive until your team defeats the boss.");
                break;
            case 3:
                getText().setText("This is the boss frame. \nWhen the boss frame is empty, you have defeated the boss.");

                getPointer().setPosition(0, getBoss().getY());
                getPointer().start();

                break;
            case 4:
                getText().setText("These are the raid frames. These frames you need to pay close attention to." +
                        "When a raid member dies, the damage toward the boss is lowered.");

                getPointer().setPosition(getBoss().getEnemies().getRaidMember(1).getX()- getPointer().getWidth()-2,
                        getBoss().getEnemies().getRaidMember(1).getY());
                break;
            case 5:
                getText().setText("Tanks have more health, but do moderate damage. Damage dealers have moderate health, but high damage." +
                        "Lastly, healers have moderate health and low damage.");
            case 6:
                getText().setText("This is your spell bar. Each spell has it own benefits. To cast a spell, select a raid member and then press the spell.");
                getPointer().setPosition(getPlayer().getSpellBar().getSpell(0).getX()- getPointer().getWidth()-2,
                        getPlayer().getSpellBar().getSpell(0).getY());
                break;
            case 7:
                getText().setText("This is your mana bar. Each spell cost mana and once your mana is depleted, you won't be able to use a spell.");
                getPointer().setPosition(getPlayer().getManaBar().getX()- getPointer().getWidth()-2,
                        getPlayer().getSpellBar().getSpell(0).getY());
            case 8:
                getText().setText("To conclude, be mindful of your mana and keep your team alive!");
                break;
            case 9:
                getText().setText("Boss is about to begin! Be ready healer!");
                break;
            case 10:
                setComplete(true);
                getBoss().start();
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
        if(!isComplete())
            super.draw(batch, parentAlpha);
    }

    private Image getDisableBG() {
        return disableBG;
    }

    private void setDisableBG(Image disableBG) {
        this.disableBG = disableBG;
    }

    private Image getFrame() {
        return frame;
    }

    private void setFrame(Image frame) {
        this.frame = frame;
    }

    private Text getText() {
        return text;
    }

    private void setText(Text text) {
        this.text = text;
    }

    private Table getTable() {
        return table;
    }

    private void setTable(Table table) {
        this.table = table;
    }

    private Assets getAssets() {
        return assets;
    }

    private void setAssets(Assets assets) {
        this.assets = assets;
    }

    private BlinkingImage getPointer() {
        return pointer;
    }

    private void setPointer(BlinkingImage pointer) {
        this.pointer = pointer;
    }

    private int getStageNumber() {
        return stageNumber;
    }

    private void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }

    private Player getPlayer() {
        return player;
    }

    private void setPlayer(Player player) {
        this.player = player;
    }

    private Boss getBoss() {
        return boss;
    }

    private void setBoss(Boss boss) {
        this.boss = boss;
    }

    public boolean isComplete() {
        return isComplete;
    }

    private void setComplete(boolean complete) {
        isComplete = complete;
    }
}
