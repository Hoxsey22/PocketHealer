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

    public Image getDisableBG() {
        return disableBG;
    }

    public void setDisableBG(Image disableBG) {
        this.disableBG = disableBG;
    }

    public Image getFrame() {
        return frame;
    }

    public void setFrame(Image frame) {
        this.frame = frame;
    }

    public Label getBoxLabel() {
        return boxLabel;
    }

    public void setBoxLabel(Label boxLabel) {
        this.boxLabel = boxLabel;
    }

    public ArrayList<Label> getChat() {
        return chat;
    }

    public void setChat(ArrayList<Label> chat) {
        this.chat = chat;
    }

    public TextButton getResetButton() {
        return resetButton;
    }

    public void setResetButton(TextButton resetButton) {
        this.resetButton = resetButton;
    }

    public TextButton getLeaveButton() {
        return leaveButton;
    }

    public void setLeaveButton(TextButton leaveButton) {
        this.leaveButton = leaveButton;
    }

    public TextButton getOkButton() {
        return okButton;
    }

    public void setOkButton(TextButton okButton) {
        this.okButton = okButton;
    }

    public Text getTitle() {
        return title;
    }

    public void setTitle(Text title) {
        this.title = title;
    }

    public Text getBody() {
        return body;
    }

    public void setBody(Text body) {
        this.body = body;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Image getResults() {
        return results;
    }

    public void setResults(Image results) {
        this.results = results;
    }

    public Music getEndingMusic() {
        return endingMusic;
    }

    public void setEndingMusic(Music endingMusic) {
        this.endingMusic = endingMusic;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
