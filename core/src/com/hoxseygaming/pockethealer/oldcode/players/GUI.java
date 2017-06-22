package com.hoxseygaming.pockethealer.oldcode.players;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.hoxseygaming.pockethealer.oldcode.players.bosses.Boss;
import com.hoxseygaming.pockethealer.oldcode.players.Party;
import com.hoxseygaming.pockethealer.oldcode.players.Player;

/**
 * Created by Hoxsey on 6/6/2017.
 */
public class GUI {

    /*
    Spell buttons
    mana bar
    casting bar
    raid frames
    boss bar
    annoucement
     */

    private Rectangle castingBar;
    private Party party;
    private Boss boss;
    private Player player;

    public GUI(Player player, Party party, Boss boss)    {
        castingBar = new Rectangle(10,170, 460,30);
        this.player = player;
        this.party = party;
        this.boss = boss;
    }

    public void draw(SpriteBatch sb)  {
        boss.draw(sb);
        party.draw(sb);
        player.draw(sb);

    }



}
