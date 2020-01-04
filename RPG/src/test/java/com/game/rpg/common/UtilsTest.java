package com.game.rpg.common;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class UtilsTest
{
    @Test
    public void testGetRandomNum1() throws IOException
    {
        int randomNum = Utils.getRandomNum(10);

        for(int count = 1; count <= 100 ;count++ )
        {
            assertTrue(randomNum < 10);
            assertTrue(randomNum >= 0);
        }

    }

    @Test
    public void testGetRandomNum2() throws IOException
    {
        int randomNum = Utils.getRandomNum(0);
        for(int count = 1; count <= 100; count++)
        {
            assertEquals(-1, randomNum);
        }

    }

    @Test
    public void testGetRandomNum3() throws IOException
    {
        int randomNum = Utils.getRandomNum(-10);
        for(int count = 1; count <= 100; count++)
        {
            assertEquals(-1, randomNum);
        }
    }

    @Test
    public void testGetRandomNum4() throws IOException
    {
        int randomNum = Utils.getRandomNum(100);
        for(int count = 1; count <= 100; count++)
        {
            assertTrue(randomNum < 100);
            assertTrue(randomNum >= 0);
        }

    }

    @Test
    public void testGetRandomNum5() throws IOException
    {
        int randomNum = Utils.getRandomNum(1);
        assertEquals(0 , randomNum);

        //Point is no matter how many times called it always returns 0
        for(int count = 1;count <= 100; count++)
        {
            randomNum = Utils.getRandomNum(1);
            assertEquals(0 , randomNum);
        }

    }

    @Test
    public void testRandomNumberInRange1() throws IOException
    {
        int randomNum = Utils.randomNumberInRange(10 , 20);
        for(int count = 1; count <= 100; count++)
        {
            assertTrue(randomNum >= 10);
            assertTrue(randomNum <= 20);
        }

    }

    @Test
    public void testRandomNumberInRange2() throws IOException
    {
        int randomNum = Utils.randomNumberInRange(-10 , 20);
        for(int count = 1; count <= 100; count++)
        {
            assertEquals(-1, randomNum);
        }
    }

    @Test
    public void testRandomNumberInRange3() throws IOException
    {
        int randomNum = Utils.randomNumberInRange(100 , 20);
        for(int count = 1; count <= 100; count++)
        {
            assertEquals(-1, randomNum);
        }
    }


    @Test
    public void testRandomNumberInRange4() throws IOException
    {
        int randomNum = Utils.randomNumberInRange(0 , 0);
        for(int count = 1; count <= 100; count++)
        {
            assertEquals(0, randomNum);
        }
    }

}
