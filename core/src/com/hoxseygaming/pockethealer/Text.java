package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class Text extends Actor {

    private BitmapFont font;
    private Label label;
    private Label.LabelStyle labelStyle;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontParameter parameter;
    public String text;

    public Text(String text)   {

        this.text = text;

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/chela_one_regular.ttf"));
        parameter = new FreeTypeFontParameter();
        parameter.size = 12;

        font = generator.generateFont(parameter);

        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        label = new Label(this.text,labelStyle);

    }

    public void setWrap(boolean isWrap)   {
        label.setWrap(isWrap);
    }

    public void setWidth(float width)  {
        label.setWidth(width);
    }

    public void setColor(Color color)  {
        label.setColor(color);
    }

    public void setText(String string)   {
        text = string;
        label.setText(text);
    }

    public void setFontSize(int size)   {
        parameter.size = size;
        font = generator.generateFont(parameter);
        labelStyle.font = font;
        label.setStyle(labelStyle);
    }

    public float getCenter()    {
        return label.getWidth()/2;
    }

    public void setAlignment(int alignment)  {
        label.setAlignment(alignment);
    }

    @Override
    public void setPosition(float x, float y)   {
        label.setPosition(x,y);
        super.setPosition(x,y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        label.draw(batch, parentAlpha);
    }

    public void dispose()   {
        generator.dispose();
    }
}
