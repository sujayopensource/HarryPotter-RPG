package com.game.rpg.game.entities.hp;

import java.util.Map;

public abstract class HpFightCharacter extends HpGameCharacter
{
    //A Map that maps Fight Strategy to corresponding magnitude.
    // Game rule is BY DEFAULT both Enemy and player have equal number of attributes.
    protected Map<FightStrategy, Integer> m_fightStrategyToAttributeMap = null;
    protected  enum FightStrategy
    {
        KNOWLEDGE(0),
        WISDOM(1),
        MAGIC(2),
        STRENGTH(3);

        private int m_fightStrategy;
        FightStrategy(int strategy)
        {
            m_fightStrategy = strategy;
        }

        public int getFightStrategy()
        {
            return m_fightStrategy;
        }

        public static FightStrategy fromInteger(int x)
        {
            switch(x) {
                case 0:
                    return KNOWLEDGE;
                case 1:
                    return WISDOM;
                case 2:
                    return MAGIC;
                case 3:
                    return STRENGTH;
            }
            return null;
        }
    }
    public HpFightCharacter(HpCharacterBuilder hpCharacterBuilderBuilder)
    {
        super(hpCharacterBuilderBuilder);
    }

    public Map<FightStrategy, Integer>  getFightStrategyToAttributeMap()
    {
        return m_fightStrategyToAttributeMap;
    }
}
