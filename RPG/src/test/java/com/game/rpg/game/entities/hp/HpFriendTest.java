package com.game.rpg.game.entities.hp;

import org.junit.Test;

import static org.junit.Assert.*;

public class HpFriendTest
{
    @Test
    public void testFriendDead()
    {
        HpFriend hpFriend = HpFriend.HpFriendBuilder.create().magic(0).wisdom(0).knowledge(0).build();
        hpFriend.setStrength(0);
        assertTrue("Friend is Dead ", hpFriend.isDead());
    }

    @Test
    public void testFriendNotDead1()
    {
        HpFriend hpFriend = HpFriend.HpFriendBuilder.create().magic(100).wisdom(0).knowledge(0).build();
        hpFriend.setStrength(0);
        assertTrue("Friend is NOT  Dead ", !hpFriend.isDead());
    }

    @Test
    public void testFriendNotDead2()
    {
        HpFriend hpFriend = HpFriend.HpFriendBuilder.create().magic(0).wisdom(100).knowledge(0).build();
        hpFriend.setStrength(0);
        assertTrue("Friend is NOT  Dead ", !hpFriend.isDead());
    }

    @Test
    public void testFriendNotDead3()
    {
        HpFriend hpFriend = HpFriend.HpFriendBuilder.create().magic(0).wisdom(0).knowledge(100).build();
        hpFriend.setStrength(0);
        assertTrue("Friend is NOT  Dead ", !hpFriend.isDead());
    }

    @Test
    public void testFriendNotDead4()
    {
        HpFriend hpFriend = HpFriend.HpFriendBuilder.create().magic(0).wisdom(0).knowledge(0).build();
        hpFriend.setStrength(10);
        assertTrue("Friend is NOT  Dead ", !hpFriend.isDead());
    }


    @Test
    public void testFriendReward1()
    {
        HpFriend hpFriend = HpFriend.HpFriendBuilder.create().magic(100).wisdom(100).knowledge(100).build();
        hpFriend.setStrength(100);
        int actualReward = hpFriend.reward();
        int expectedReward = 100;
        assertEquals("Reward is", expectedReward, actualReward);

        //Check deductions on friend
        assertEquals("Knowledge ", 75, hpFriend.getKnowledge());
        assertEquals("Wisdom ", 75, hpFriend.getWisdom());
        assertEquals("Magic ", 75, hpFriend.getMagic());
        assertEquals("Strength", 75, hpFriend.getStrength());
    }


    @Test
    public void testFriendReward2()
    {
        HpFriend hpFriend = HpFriend.HpFriendBuilder.create().magic(100).wisdom(10).knowledge(10).build();
        hpFriend.setStrength(100);
        int actualReward = hpFriend.reward();
        int expectedReward = 55;
        assertEquals("Reward is", expectedReward, actualReward);

        //Check deductions on friend
        assertEquals("Knowledge ", 0, hpFriend.getKnowledge());
        assertEquals("Wisdom ", 0, hpFriend.getWisdom());
        assertEquals("Magic ", 87, hpFriend.getMagic());
        assertEquals("Strength", 87, hpFriend.getStrength());
    }


}
