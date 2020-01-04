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
public class WisdomFightStrategyTest
{
    private static HpFightStrategy fightStrategy;
    @BeforeClass
    public static void setUp()
    {
        fightStrategy = new WisdomFightStrategy();
    }


    @Before
    public void setUpTest()
    {
        PowerMockito.mockStatic(Utils.class);
    }


    @Test
    public void testSourceWisGreaterThanDestWis()
    {
        int sourceWis = 200;
        int destWis = 75;
        int expectedDamage = 1;
        when(Utils.getRandomNum(WisdomFightStrategy.getWisdomCoefficent())).thenReturn(10);
        when(Utils.getRandomNum(WisdomFightStrategy.getWisdomCoefficent() / 2)).thenReturn(10);
        when(Utils.getRandomNum(WisdomFightStrategy.getWisdomCoefficent() / 5)).thenReturn(10);
        fightStrategy.setUp(sourceWis, destWis);
        int damage = fightStrategy.inflict();
        assertEquals(expectedDamage, damage);

    }

    @Test
    public void testDestStrGreaterThanSourceStr()
    {
        int sourceKnowledge = 75;
        int destKnowledge = 200;
        int expectedDamage = 6;
        when(Utils.getRandomNum(WisdomFightStrategy.getWisdomCoefficent())).thenReturn(1);
        when(Utils.getRandomNum(WisdomFightStrategy.getWisdomCoefficent() / 2)).thenReturn(1);
        when(Utils.getRandomNum(WisdomFightStrategy.getWisdomCoefficent() / 5)).thenReturn(1);
        fightStrategy.setUp(sourceKnowledge, destKnowledge);
        int damage = fightStrategy.inflict();
        assertEquals(expectedDamage, damage);
    }
}
