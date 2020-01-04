package com.game.rpg.common;

import java.util.Random;

public class Utils
{

    public static int getRandomNum(int size)
    {
        if(size <= 0)
        {
            return -1;
        }
        Random random = new Random();
        return random.nextInt(size); //Random num between 0 - size(exclusive)
    }

    public static int randomNumberInRange(int min, int max)
    {
        if(min > max || min < 0 )
        {
            return -1;
        }
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
