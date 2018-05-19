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

    public Image disableBG;
    public Image frame;
    public Label boxLabel;  //maybe for a later time
    public ArrayList<Label> chat;   // maybe for a later time
    //public Button resetButton;
    public TextButton resetButton;
    //public Button leaveButton;
    public TextButton leaveButton;
    public TextButton okButton;
    public Text title;
    public Text body;
    public Table table;
    public Image results;
    public Music endingMusic;   // maybe later
    public Boss boss;
    public Assets assets;
    public boolean won;
    public int page;

    public GameOverFrame(boolean won, Boss boss, Assets assets) {
        this.assets = assets;
        this.won = won;
        this.boss = boss;
        create();
    }

    public void create() {
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

    public void createText() {
        table = new Table();
        table.setName("table");
        table.setBounds(frame.getX(), frame.getY(), frame.getWidth() - 10, frame.getHeight() - 10);

        title = new Text("", 32, Color.WHITE, true, assets);
        title.setName("title");
        title.setWrap(true);
        title.setAlignment(Align.top);

        body = new Text("", 24, Color.WHITE, false, assets);
        body.setName("body");
        body.setWrap(true);
        body.setAlignment(Align.center);

        addActor(table);
    }

    public void showHealingStats() {
        title.setText("Healing");

        table.add(title.getLabel()).expandX();
        table.row();

        body.setText("Effective Healing\n" + boss.getEnemies().healingTracker.getEffectiveHealingPercent() + "%\n" +
                "Overhealing\n" + boss.getEnemies().healingTracker.getOverHealingPercent() + "%");

        table.add(body.getLabel()).expandX().expandY();
        table.row();
        table.add(okButton).bottom().padBottom(10);

    }

    public void showReward() {
        table.clear();

        title.setText("Reward");
        table.add(title.getLabel()).colspan(2);
        table.row();

        if (boss.getRewardPackage().spell) {
            for (int i = 0; i < boss.getRewardPackage().images.size(); i++) {
                table.add(boss.getRewardPackage().images.get(i));
            }
            table.row();
        }

        body.setText(boss.getRewardPackage().getReward());
        table.add(body.getLabel()).colspan(2).expandY();;
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

}
