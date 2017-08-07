package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class Text extends Label{

    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontParameter parameter;

    public Text(String text)    {
        super(text, new LabelStyle(new BitmapFont(), Color.BLACK));

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/chela_one_regular.ttf"));
        parameter = new FreeTypeFontParameter();
        parameter.size = 12;

        font = generator.generateFont(parameter);

        LabelStyle newLabelStyle = new LabelStyle();
        newLabelStyle.font = font;
        newLabelStyle.fontColor = Color.WHITE;

        setStyle(newLabelStyle);

        setAlignment(Align.center);

    }

    public void setFontSize(int size)   {
        LabelStyle temp = new LabelStyle();
        parameter.size = size;
        font = generator.generateFont(parameter);
        temp.font = font;
        temp.fontColor = getStyle().fontColor;
        setStyle(temp);
    }

    public void setBorder(Color color, int width)   {
        LabelStyle temp = new LabelStyle();

        parameter.borderColor = color;
        parameter.borderWidth = width;

        font = generator.generateFont(parameter);

        temp.font = font;
        setStyle(temp);

    }

    public float getCenter()    {
        return getWidth()/2;
    }

    //@Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch,parentAlpha);
    }

    public void dispose()   {
        generator.dispose();
    }
}
