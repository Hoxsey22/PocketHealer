package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 7/27/2017.
 * Merging Changes
 */

public class GameOverFrame extends Group {

    private Image disableBG;
    private Image frame;
    private Label boxLabel;  //maybe for a later time
    private ArrayList<Label> chat;   // maybe for a later time
    //public Button resetButton;
    private TextButton resetButton;
    //public Button leaveButton;
    private TextButton leaveButton;
    private TextButton okButton;
    private Text title;
    private Text body;
    private Table table;
    private Image results;
    private Music endingMusic;   // maybe later
    private Boss boss;
    private Assets assets;
    private boolean won;
    private int page;

    public GameOverFrame(boolean won, Boss boss, Assets assets) {
        this.assets = assets;
        this.won = won;
        this.boss = boss;
        create();
    }

    private void create() {
        disableBG = new Image(assets.getTexture(assets.disableBG));
        disableBG.setName("disable bg");
        disableBG.setBounds(0, 0, PocketHealer.WIDTH, PocketHealer.HEIGHT);

        frame = new Image(assets.getTexture(assets.endGameFrame));
        frame.setName("frame");
        frame.setPosition(PocketHealer.WIDTH / 2 - frame.getWidth() / 2, PocketHealer.HEIGHT / 2 - frame.getWidth() / 2);
        frame.setHeight(frame.getHeight()+30);

        if(won)    {
            okButton = new TextButton("Ok", assets.getSkin(), "small_button");
            okButton.setName("ok");
        }

        addActor(disableBG);
        addActor(frame);

        createText();
    }

    private void createText() {
        table = new Table();
        table.setName("table");
        table.setBounds(frame.getX(), frame.getY(), frame.getWidth() - 10, frame.getHeight() - 10);

        title = new Text("", 32, Color.WHITE, true, assets);
        title.setName("title");
        title.setWrap();
        title.setAlignment(Align.top);

        body = new Text("", 24, Color.WHITE, false, assets);
        body.setName("body");
        body.setWrap();
        body.setAlignment(Align.center);

        addActor(table);
    }

    public void showHealingStats() {
        title.setText("Healing");

        table.add(title.getLabel()).expandX();
        table.row();

        body.setText("Effective Healing\n" + boss.getEnemies().getHealingTracker().getEffectiveHealingPercent() + "%\n" +
                "Overhealing\n" + boss.getEnemies().getHealingTracker().getOverHealingPercent() + "%");

        table.add(body.getLabel()).expandX().expandY();
        table.row();
        table.add(okButton).bottom().padBottom(10);

    }

    public void showReward() {
        table.clear();

        title.setText("Reward");
        table.add(title.getLabel()).colspan(2);
        table.row();

        if (boss.getRewardPackage().isSpell()) {
            for (int i = 0; i < boss.getRewardPackage().getImages().size(); i++) {
                table.add(boss.getRewardPackage().getImages().get(i));
            }
            table.row();
        }

        body.setText(boss.getRewardPackage().getReward());
        table.add(body.getLabel()).colspan(2).expandY();
        table.row();
        table.add(okButton).bottom().padBottom(10);
    }

    public void showLose() {
        title.setText("You have wiped!");
        table.add(title.getLabel()).width(table.getWidth()).colspan(2).expand().top().center();
        table.row();

        //resetButton = new Button("RESET", false,assets);
        resetButton = new TextButton("RESET", assets.getSkin(),"small_button");
        resetButton.setName("reset");
        //resetButton.setPosition(frame.getX() + frame.getWidth()/2 - resetButton.getWidth(), frame.getY() - resetButton.getHeight()/2);
        table.add(resetButton).bottom().padBottom(10);
        //leaveButton = new Button("QUIT", false, assets);
        leaveButton = new TextButton("QUIT", assets.getSkin(), "small_button");
        leaveButton.setName("quit");
        //leaveButton.setPosition(resetButton.getX() + resetButton.getWidth(), frame.getY() - leaveButton.getHeight()/2);
        table.add(leaveButton).bottom().padBottom(10);

        //addActor(resetButton);
        //addActor(leaveButton);
    }

    public int hitButton(float x, float y) {
        /*TEMP FIX*/

        if (leaveButton != null && leaveButton.isPressed()) {
            return 0;
        }
        if (okButton != null && okButton.isPressed()) {
            return 1;
        }
        if (resetButton != null && resetButton.isPressed()) {
            return 2;
        }
        return -1;
    }

    public boolean isWon() {
        return won;
    }

}
