package com.hoxseygaming.pockethealer.encounters.spells;

import java.util.Random;

/**
 * Created by Hoxsey on 12/1/2017.
 */

public class CriticalDice {

    private static final Random diceRoll = new Random();

    public static boolean roll(int playerCritical){

        int roll = diceRoll.nextInt(100);

        if(roll < playerCritical)   {
            System.out.println("CRITICAL!");
            return true;
        }
        else {
            return false;
        }

    }

    public static boolean roll(int critical, int max, int min)    {
        int roll = diceRoll.nextInt(max)+min;

        if(roll < critical)   {
            System.out.println("CRITICAL!");
            return true;
        }
        else {
            return false;
        }
    }
}
