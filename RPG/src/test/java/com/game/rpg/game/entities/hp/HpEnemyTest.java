package com.game.rpg.game.entities.hp;

import com.game.rpg.game.entities.Experience;
import org.junit.Test;

import static org.junit.Assert.*;

public class HpEnemyTest
{
    @Test
    public void testEnemyDead1()
    {
        HpEnemy hpEnemy = HpEnemy.HpEnemyBuilder.create().magic(150).wisdom(100).knowledge(0).build();
        hpEnemy.setStrength(100);
        assertTrue("Enemy is Dead ", hpEnemy.isDead());
    }

    @Test
    public void testEnemyDead2()
    {
        HpEnemy hpEnemy = HpEnemy.HpEnemyBuilder.create().magic(150).wisdom(0).knowledge(10).build();
        hpEnemy.setStrength(100);
        assertTrue("Enemy is Dead ", hpEnemy.isDead());
    }

    @Test
    public void testEnemyDead3()
    {
        HpEnemy hpEnemy = HpEnemy.HpEnemyBuilder.create().magic(0).wisdom(100).knowledge(10).build();
        hpEnemy.setStrength(100);
        assertTrue("Enemy is Dead ", hpEnemy.isDead());
    }

    @Test
    public void testEnemyDead4()
    {
        HpEnemy hpEnemy = HpEnemy.HpEnemyBuilder.create().magic(150).wisdom(100).knowledge(10).build();
        hpEnemy.setStrength(0);
        assertTrue("Enemy is Dead ", hpEnemy.isDead());
    }

    @Test
    public void testEnemyReward1()
    {
        HpEnemy hpEnemy = HpEnemy.HpEnemyBuilder.create().magic(150).wisdom(100).knowledge(10).experience(new Experience(Experience.START_LEVEL, 50)).build();
        int actualReward = hpEnemy.reward();
        int expectedReward = 150;

        assertEquals("Reward from Enemy is", expectedReward, actualReward);
    }

    @Test
    public void testEnemyReward2()
    {
        HpEnemy hpEnemy = HpEnemy.HpEnemyBuilder.create().magic(150).wisdom(100).knowledge(10).experience(new Experience(Experience.START_LEVEL + 3, 50)).build();
        int actualReward = hpEnemy.reward();
        int expectedReward = 450;

        assertEquals("Reward from Enemy is", expectedReward, actualReward);
    }

    @Test
    public void testEnemyReset()
    {
        HpEnemy hpEnemy = HpEnemy.HpEnemyBuilder.create().magic(150).wisdom(100).knowledge(10).experience(new Experience(Experience.START_LEVEL + 3, 50)).build();
        hpEnemy.reset();

        assertEquals("On being reset expereicne becomes 0", 0, hpEnemy.getExperience().getLevel());
        assertEquals("On being reset expereicne becomes 0", 0, hpEnemy.getExperience().getMagnitude());
    }
}
