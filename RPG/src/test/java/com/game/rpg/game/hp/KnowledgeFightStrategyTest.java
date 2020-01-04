package com.game.rpg.game.hp;

import com.game.rpg.common.Utils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(Utils.class)
public class KnowledgeFightStrategyTest
{
     private static HpFightStrategy fightStrategy;
    @BeforeClass
    public static void setUp()
    {
        fightStrategy = new KnowledgeFightStrategy();
    }

    @Before
    public void setUpTest()
    {
        PowerMockito.mockStatic(Utils.class);
    }

    @Test
    public void testSourceKnowgGreaterThanDestKnow()
    {
            int sourceKnowledge = 100;
            int destKnowledge = 75;
            int expectedDamage = 1;

            when(Utils.getRandomNum(KnowledgeFightStrategy.getKnowledgeCoefficient())).thenReturn(26);
            fightStrategy.setUp(sourceKnowledge, destKnowledge);
            int damage = fightStrategy.inflict();
            assertEquals(expectedDamage, damage);

    }

    @Test
    public void testDestKnowGreaterThanSourceKnow()
    {
        int sourceKnowledge = 75;
        int destKnowledge = 100;
        int expectedDamage = 1;

        when(Utils.getRandomNum(KnowledgeFightStrategy.getKnowledgeCoefficient())).thenReturn(20);
        fightStrategy.setUp(sourceKnowledge, destKnowledge);
        int damage = fightStrategy.inflict();
        assertEquals(expectedDamage, damage);

    }
}
