package com.hoxseygaming.pockethealer.encounters.spells;

import java.util.Random;

/**
 * Created by Hoxsey on 12/1/2017.
 */

public class CriticalDice {

    private static Random diceRoll = new Random();

    public static boolean roll(int playerCritical){

        int roll = diceRoll.nextInt(100)+0;

        if(roll < playerCritical)   {
            System.out.println("CRITICAL!");
            return true;
        }
        else {
            return false;
        }

    }
}
