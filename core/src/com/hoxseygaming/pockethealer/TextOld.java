package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class TextOld extends Label{

    //private BitmapFont font;
    private Assets assets;
    private Color textColor;
    private int fontSize;

    public TextOld(String text, boolean isBorder, Assets assets)    {
        super(text, new LabelStyle(assets.getFont(isBorder), Color.BLACK));
        textColor = Color.BLACK;
        fontSize = 16;
        this.assets = assets;
        setAlignment(Align.center);

    }

    public TextOld(String text, int fontSize, Color color, boolean isBorder, Assets assets)    {
        super(text, new LabelStyle(assets.getFont(isBorder), Color.BLACK));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/chela_one_regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = fontSize;
        parameter.color = color;

        getStyle().font = generator.generateFont(parameter);
        getStyle().fontColor = color;
        generator.dispose();

        this.fontSize = fontSize;

        this.assets = assets;
        setAlignment(Align.center);

    }

    public void setFontColor(Color color)  {
        /*textColor = color;
        getStyle().fontColor = textColor;
        //setStyle(getStyle());*/

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/chela_one_regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = fontSize;
        parameter.color = color;

        textColor = color;

        getStyle().font = generator.generateFont(parameter);
        getStyle().fontColor = textColor;

        generator.dispose();
        setStyle(getStyle());
        setAlignment(Align.left);
    }

    /**
     * Be sure to change color before size
     * @param fontSize
     */
    public void setFontSize(int fontSize)   {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/chela_one_regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = fontSize;
        parameter.color = textColor;
        this.fontSize = fontSize;

        getStyle().font = generator.generateFont(parameter);
        getStyle().fontColor = textColor;

        generator.dispose();
        setStyle(getStyle());
    }

    public float getCenter()    {
        return getWidth()/2;
    }

    //@Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch,parentAlpha);
    }

    public void dispose()   {
    }
}
