package com.game.rpg.game.hp;

import com.game.rpg.common.Utils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.TestCase.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Utils.class)
public class StrengthFightStrategyTest
{
    private static HpFightStrategy fightStrategy;
    @BeforeClass
    public static void setUp()
    {
        fightStrategy = new StrengthFightStrategy();
    }


    @Before
    public void setUpTest()
    {
        PowerMockito.mockStatic(Utils.class);
    }


    @Test
    public void testSourceStrGreaterThanDestStr()
    {
            int sourceStr = 200;
            int destStr = 75;
            int expectedDamage = 1;
            when(Utils.getRandomNum(StrengthFightStrategy.getStrengthCoefficient() * 2)).thenReturn(41);
            fightStrategy.setUp(sourceStr, destStr);
            int damage = fightStrategy.inflict();
            assertEquals(expectedDamage, damage);

    }

        @Test
        public void testDestStrGreaterThanSourceStr()
        {
            int sourceStr = 75;
            int destStr = 200;
            int expectedDamage = 1;
            when(Utils.getRandomNum(StrengthFightStrategy.getStrengthCoefficient() * 2)).thenReturn(26);
            fightStrategy.setUp(sourceStr, destStr);
            int damage = fightStrategy.inflict();
            assertEquals(expectedDamage, damage);
    }
}


