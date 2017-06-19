package com.hoxseygaming.pockethealer.players;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.hoxseygaming.pockethealer.players.*;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 5/29/2017.
 */
public class RaidFrame  {

    public static final int WIDTH = 134;
    public static final int HEIGHT = 64;
    public static final int HealthBarWIDTH = 121;
    public static final int HealthBarHEIGHT = 13;
    public Color defaultColor = Color.SLATE;

    private float x;
    private float y;
    private Rectangle frame;
    private Rectangle healthFrame;
    private Rectangle healthBar;
    private Color healthColor;
    private Color frameColor;
    private String role;
    private Texture roleImage;
    private ArrayList<Image> effectStatuses;
    private ShapeRenderer shapeRenderer;
    private Member member;
    private boolean isSelected;

    public RaidFrame(Member member)  {
        this.member = member;
        x = (float) com.hoxseygaming.pockethealer.players.RaidFrameData.postion[(member.getId()-1)*2+1];
        y = (float) com.hoxseygaming.pockethealer.players.RaidFrameData.postion[(member.getId()-1)*2];
        role = member.getRole();

        frame = new Rectangle(x,y,WIDTH,HEIGHT);
        healthFrame = new Rectangle(x+5,y+5,HealthBarWIDTH, HealthBarHEIGHT);
        healthBar = new Rectangle(x+5,y+5,HealthBarWIDTH, HealthBarHEIGHT);

        healthColor = Color.GREEN;

        effectStatuses = new ArrayList<Image>();

        shapeRenderer = new ShapeRenderer();
        isSelected = false;
        frameColor = defaultColor;
        setRoleImage();

    }

    public void draw(SpriteBatch sb)  {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.rect(frame.getX()-2,frame.getY()-2,WIDTH+4,HEIGHT+4);
        shapeRenderer.end();
        // filled frame box
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(frameColor);
            shapeRenderer.rect(frame.getX(),frame.getY(),WIDTH,HEIGHT);
        shapeRenderer.end();

        // lined health bar box
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.rect(healthFrame.getX()-2,healthFrame.getY()-2,healthFrame.getWidth()+4,healthFrame.getHeight()+4);
        shapeRenderer.end();

        // filled health bar box
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(member.getHealthColor());
            shapeRenderer.rect(healthBar.getX(),healthBar.getY(),(float) (HealthBarWIDTH*((float)member.getHp()/(float)member.getMaxHp())),HealthBarHEIGHT);
        shapeRenderer.end();



        // role icon
        sb.begin();
            sb.draw(roleImage,healthBar.getX(),healthBar.getY()+healthBar.getHeight()+5, 34,34);
        sb.end();

        drawEffects(sb);


        // draw effect statuses

    }

    private void drawEffects(SpriteBatch sb)  {
        if(member.getEffects().size() > 0)    {
            for (int i = 0; i < member.getEffects().size(); i ++) {
                switch (member.getEffects().get(i)) {
                    case HEALOVERTIME:
                        sb.begin();
                            sb.draw(RaidFrameData.renewIconImage,healthBar.getX() + healthBar.width - 20 *(i) - 20,healthBar.getY()+healthBar.getHeight()+5,20,20);
                        sb.end();
                        break;
                    case SHIELD:
                        sb.begin();
                        sb.draw(RaidFrameData.barrierIconImage,healthBar.getX() + healthBar.width - 20 *(i)-20,healthBar.getY()+healthBar.getHeight()+5,20,20);
                        sb.end();
                        break;
                    case BLEED:
                        sb.begin();
                            sb.draw(RaidFrameData.renewIconImage,healthBar.getX() + healthBar.width - 20 *(i)-20,healthBar.getY(),10,10);
                        sb.end();
                        break;
                }
            }
        }
    }

    private void setupFrame()   {

    }

    public Rectangle getFrame() {
        return frame;
    }

    public void getPosition()   {
        System.out.println(x+","+y);
    }

    public void setRoleImage(Texture img)  {
        roleImage = img;
    }

    public void setRoleImage()  {
        switch (role)   {
            case Member.DPS:
                roleImage = com.hoxseygaming.pockethealer.players.RaidFrameData.dpsIconImage;
                break;
            case Member.HEALER:
                roleImage = com.hoxseygaming.pockethealer.players.RaidFrameData.healerIconImage;
                break;
            case Member.TANK:
                roleImage = com.hoxseygaming.pockethealer.players.RaidFrameData.tankIconImage;
                break;
        }
    }

    public Member getMember()   {
        return member;
    }

    public int getId()  {
       return member.getId();
    }

    public void selected()    {
        isSelected = true;
        frameColor = Color.TEAL;
    }

    public void unselected()    {
        isSelected = false;
        frameColor = defaultColor;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
