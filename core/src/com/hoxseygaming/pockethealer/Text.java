package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class Text extends Label{

    //private BitmapFont font;
    private Assets assets;

    public Text(String text, Assets assets)    {
        super(text, new LabelStyle(new BitmapFont(), Color.BLACK));
        this.assets = assets;
        /*
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/chela_one_regular.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 12;
        */

        //font = generator.generateFont(parameter);

        LabelStyle newLabelStyle = new LabelStyle();
        newLabelStyle.font = assets.gameFont16;
        newLabelStyle.fontColor = Color.WHITE;

        setStyle(newLabelStyle);
        setAlignment(Align.center);

    }

    public void setFont(int fontSize, boolean isBordered)   {
        LabelStyle temp = new LabelStyle();
        /*
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/chela_one_regular.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();

        parameter.size = size;
        //font.dispose();
        //font = generator.generateFont(parameter);
        */
        if(isBordered) {
            switch (fontSize) {
                case 16:
                    temp.font = assets.gameFontB16;
                    break;
                case 24:
                    temp.font = assets.gameFontB24;
                    break;
                case 32:
                    temp.font = assets.gameFontB32;
                    break;
                case 45:
                    temp.font = assets.gameFontB45;
                    break;
            }
        }
        else {
            switch (fontSize) {
                case 16:
                    temp.font = assets.gameFont16;
                    break;
                case 24:
                    temp.font = assets.gameFont24;
                    break;
                case 32:
                    temp.font = assets.gameFont32;
                    break;
                case 45:
                    temp.font = assets.gameFont45;
                    break;
            }
        }
        setStyle(temp);
    }

    /*
    public void setBorder(Color color, int width)   {
        LabelStyle temp = new LabelStyle();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/chela_one_regular.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();

        parameter.borderColor = color;
        parameter.borderWidth = width;

        //font = generator.generateFont(parameter);

        temp.font = generator.generateFont(parameter);
        setStyle(temp);
        generator.dispose();

    }
    */

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
