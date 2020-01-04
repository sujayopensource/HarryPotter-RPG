package com.game.rpg.game.entities.hp;

import com.game.rpg.game.hp.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class HpPlayerTest
{


    @Test
    public void testPlayerDead1()
    {
        HpPlayer hpPlayer = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(0).wisdom(100).knowledge(100).build();
        hpPlayer.setStrength(0);
        assertTrue("Player is dead", hpPlayer.isDead());
    }

    @Test
    public void testPlayerDead2()
    {
        HpPlayer hpPlayer = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(10).wisdom(0).knowledge(100).build();
        hpPlayer.setStrength(0);
        assertTrue("Player is dead", hpPlayer.isDead());
    }

    @Test
    public void testPlayerDead3()
    {
        HpPlayer hpPlayer = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(10).wisdom(100).knowledge(0).build();
        hpPlayer.setStrength(0);
        assertTrue("Player is dead", hpPlayer.isDead());
    }

    @Test
    public void testPlayerNotDead1()
    {
        HpPlayer hpPlayer = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(0).wisdom(0).knowledge(0).build();
        hpPlayer.setStrength(10);
        assertTrue("Player is not dead", !hpPlayer.isDead());
    }

    @Test
    public void testPlayerNotDead2()
    {
        HpPlayer hpPlayer = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(10).wisdom(10).knowledge(10).build();
        hpPlayer.setStrength(0);
        assertTrue("Player is not dead", !hpPlayer.isDead());
    }

    @Test
    public void testBestFightStrategy1()
    {
        HpPlayer hpPlayer = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(150).wisdom(100).knowledge(50).build();
        HpFightStrategy fightStrategy = hpPlayer.getBestHpFightStrategy();
        assertTrue("Its MagicalFightStreategy", fightStrategy instanceof MagicalFightStrategy);
    }

    @Test
    public void testBestFightStrategy2()
    {
        HpPlayer hpPlayer = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(10).wisdom(150).knowledge(50).build();
        HpFightStrategy fightStrategy = hpPlayer.getBestHpFightStrategy();
        assertTrue("Its WisdomFightStrategy", fightStrategy instanceof WisdomFightStrategy);
    }

    @Test
    public void testBestFightStrategy3()
    {
        HpPlayer hpPlayer = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(10).wisdom(10).knowledge(150).build();
        HpFightStrategy fightStrategy = hpPlayer.getBestHpFightStrategy();
        assertTrue("Its KnowledgeFightStrategy", fightStrategy instanceof KnowledgeFightStrategy);
    }

    @Test
    public void testBestFightStrategy4()
    {
        HpPlayer hpPlayer = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(10).wisdom(10).knowledge(50).build();
        HpFightStrategy fightStrategy = hpPlayer.getBestHpFightStrategy();
        assertTrue("Its StrengthFightStrategy", fightStrategy instanceof StrengthFightStrategy);
    }

    @Test
    public void testBestFightStrategySwitch1()
    {
        HpPlayer hpPlayer = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(80).wisdom(85).knowledge(95).build();
        HpFightStrategy fightStrategy = hpPlayer.getBestHpFightStrategy();
        //Now Strength is the best Strategy
        assertTrue("Its StrengthFightStrategy", fightStrategy instanceof StrengthFightStrategy);

        hpPlayer.applyDamage(10);

        //New attribute values. Strength is initially 100(initial strength of any character)
        assertEquals("Strength now is", 90, hpPlayer.getStrength());

        //Remaining atrtributes 1/4 is deducted(game rule :) )
        assertEquals("Magic now is", 78, hpPlayer.getMagic());
        assertEquals("Wisdom now is", 83, hpPlayer.getWisdom());
        assertEquals("Knowledge now is", 93, hpPlayer.getKnowledge());

        //Now best fight Strategy is expected to be Knowledge
        fightStrategy = hpPlayer.getBestHpFightStrategy();
        assertTrue("Its KnowledgeFightStrategy", fightStrategy instanceof KnowledgeFightStrategy);

    }


    @Test
    public void testBestFightStrategySwitch2()
    {
        HpPlayer hpPlayer = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(106).wisdom(85).knowledge(95).build();
        HpFightStrategy fightStrategy = hpPlayer.getBestHpFightStrategy();
        //Now Magic is the best Strategy
        assertTrue("Its MagicalFightStrategy", fightStrategy instanceof MagicalFightStrategy);

        hpPlayer.applyDamage(10);

        //New attribute values. Strength is initially 100(initial strength of any character)
        assertEquals("Strength now is", 98, hpPlayer.getStrength());

        //Remaining atrtributes 1/4 is deducted(game rule :) )
        assertEquals("Magic now is", 96, hpPlayer.getMagic());
        assertEquals("Wisdom now is", 83, hpPlayer.getWisdom());
        assertEquals("Knowledge now is", 93, hpPlayer.getKnowledge());

        //Now best fight Strategy is expected to be Strength
        fightStrategy = hpPlayer.getBestHpFightStrategy();
        assertTrue("It StrengthFightStrategy", fightStrategy instanceof StrengthFightStrategy);

    }


    @Test
    public void testBestFightStrategySwitch3()
    {
        HpPlayer hpPlayer = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(88).wisdom(112).knowledge(105).build();
        HpFightStrategy fightStrategy = hpPlayer.getBestHpFightStrategy();
        //Now Wisdom is the best Strategy
        assertTrue("Its WisdomFightStrategy", fightStrategy instanceof WisdomFightStrategy);

        hpPlayer.applyDamage(10);

        //New attribute values. Strength is initially 100(initial strength of any character)
        assertEquals("Strength now is", 98, hpPlayer.getStrength());

        //Remaining attributes 1/4 is deducted(game rule :) )
        assertEquals("Magic now is", 86, hpPlayer.getMagic());
        assertEquals("Wisdom now is", 102, hpPlayer.getWisdom());
        assertEquals("Knowledge now is", 103, hpPlayer.getKnowledge());

        //Now best fight Strategy is expected to be Knowledge
        fightStrategy = hpPlayer.getBestHpFightStrategy();
        assertTrue("KnowledgeFightStrategy", fightStrategy instanceof KnowledgeFightStrategy);

    }


    @Test
    public void testBestFightStrategySwitch4()
    {
        HpPlayer hpPlayer = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(88).wisdom(105).knowledge(112).build();
        HpFightStrategy fightStrategy = hpPlayer.getBestHpFightStrategy();
        //Now Knowledge is the best Strategy
        assertTrue("Its KnowledgeFightStrategy", fightStrategy instanceof KnowledgeFightStrategy);

        hpPlayer.applyDamage(10);

        //New attribute values. Strength is initially 100(initial strength of any character)
        assertEquals("Strength now is", 98, hpPlayer.getStrength());

        //Remaining atrtributes 1/4 is deducted(game rule :) )
        assertEquals("Magic now is", 86, hpPlayer.getMagic());
        assertEquals("Wisdom now is", 103, hpPlayer.getWisdom());
        assertEquals("Knowledge now is", 102, hpPlayer.getKnowledge());

        //Now best fight Strategy is expected to be Wisdom
        fightStrategy = hpPlayer.getBestHpFightStrategy();
        assertTrue("KnowledgeFightStrategy", fightStrategy instanceof WisdomFightStrategy);

    }
}
