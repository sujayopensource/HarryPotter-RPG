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
public class MagicalFightStrategyTest
{

    private static HpFightStrategy fightStrategy;
    @BeforeClass
    public static void setUp()
    {
        fightStrategy = new MagicalFightStrategy();
    }


    @Before
    public void setUpTest()
    {
        PowerMockito.mockStatic(Utils.class);
    }

    @Test
    public void testSourceMagGreaterThanDestMag()
    {
        int sourceMagic = 200;
        int destMagic = 75;
        int expectedDamage = 76;

        when(Utils.getRandomNum(MagicalFightStrategy.getMagicCoefficient())).thenReturn(51);
        fightStrategy.setUp(sourceMagic, destMagic);
        int damage = fightStrategy.inflict();
        assertEquals(expectedDamage, damage);

    }

    @Test
    public void testDestMagGreaterThanSourceMag()
    {
        int sourceKnowledge = 75;
        int destKnowledge = 200;
        int expectedDamage = 26;

        when(Utils.getRandomNum(MagicalFightStrategy.getMagicCoefficient())).thenReturn(41);
        fightStrategy.setUp(sourceKnowledge, destKnowledge);
        int damage = fightStrategy.inflict();
        assertEquals(expectedDamage, damage);

    }
}
