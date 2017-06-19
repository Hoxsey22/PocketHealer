package com.hoxseygaming.pockethealer.reformat.Spells;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class FlashHeal extends Heal {


    public FlashHeal(int position) {
        super(position);
        setName("Flash Heal");
        setCost(25);
        setCastTime(0.7f);
    }


}
