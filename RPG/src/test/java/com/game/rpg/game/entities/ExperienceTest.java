package com.game.rpg.game.entities;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExperienceTest
{
    @Test
    public void testBasicExperience()
    {
        Experience experience = new Experience(1, 75);
        assertEquals(1, experience.getLevel());
        assertEquals(75, experience.getMagnitude());
        assertEquals(175, experience.getTotal());
    }

    @Test
    public void testBasicExperience2()
    {
        Experience experience = new Experience(1, 175);
        assertEquals(2, experience.getLevel());
        assertEquals(75, experience.getMagnitude());
        assertEquals(275, experience.getTotal());
    }

    @Test
    public void addMagnitudeTest1()
    {
        Experience experience = new Experience(1, 75);
        experience.add(25);
        assertEquals(1, experience.getLevel());
        assertEquals(100, experience.getMagnitude());
        assertEquals(200, experience.getTotal());
    }

    @Test
    public void addMagnitudeTest2()
    {
        Experience experience = new Experience(1, 75);
        experience.add(26);
        assertEquals(2, experience.getLevel());
        assertEquals(1, experience.getMagnitude());
        assertEquals(201, experience.getTotal());
    }

    @Test
    public void addMagnitudeTest3()
    {
        Experience experience = new Experience(1, 75);
        experience.add(126);
        assertEquals(3, experience.getLevel());
        assertEquals(1, experience.getMagnitude());
        assertEquals(301, experience.getTotal());
    }


    @Test
    public void addMagnitudeNegativeTest()
    {
        Experience experience = new Experience(1, 75);
        experience.add(-26);
        //Adding nagative exp is denied
        assertEquals(1, experience.getLevel());
        assertEquals(75, experience.getMagnitude());
        assertEquals(175, experience.getTotal());
    }

    @Test
    public void valididtyTest()
    {
        Experience experience = new Experience(1, 75);
        assertTrue("Is Valid", experience.isValid());
    }

    @Test
    public void InvalididtyTest1()
    {
        Experience experience = new Experience(1, -25);
        assertTrue("Is Invalid", !experience.isValid());
    }


    @Test
    public void InvalididtyTest2()
    {
        Experience experience = new Experience(11, 0);
        assertTrue("Is Invalid", !experience.isValid());
    }


    @Test
    public void addOtherExpTest1()
    {
        Experience current = new Experience(1, 75);
        Experience other = new Experience(2, 80);
        current.add(other);

        assertEquals(455, current.getTotal());
        assertEquals(4, current.getLevel());
        assertEquals(55, current.getMagnitude());
    }

    @Test
    public void addOtherExpTest2()
    {
        Experience current = new Experience(1, 75);
        Experience other = new Experience(1, 100);
        current.add(other);

        assertEquals(375, current.getTotal());
        assertEquals(3, current.getLevel());
        assertEquals(75, current.getMagnitude());
    }

    @Test
    public void addOtherExpTest3()
    {
        Experience current = new Experience(1, 75);
        Experience other = new Experience(1, 0);
        current.add(other);

        //cannot add invalid exps
        assertEquals(175, current.getTotal());
        assertEquals(1, current.getLevel());
        assertEquals(75, current.getMagnitude());
    }

    @Test
    public void addOtherExpTest4()
    {
        Experience current = new Experience(1, 75);
        Experience other = new Experience(1, 101);
        current.add(other);

        //cannot add invalid exps
        assertEquals(376, current.getTotal());
        assertEquals(3, current.getLevel());
        assertEquals(76, current.getMagnitude());
    }
}
