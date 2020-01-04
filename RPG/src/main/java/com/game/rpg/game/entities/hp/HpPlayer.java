package com.game.rpg.game.entities.hp;

import com.game.rpg.game.entities.*;
import com.game.rpg.game.hp.*;

import java.io.Serializable;
import java.util.*;

public class HpPlayer extends HpFightCharacter implements MobilePlayer
{
    private static final long serialVersionUID = -8303570879040117L;

    /*
        A Max heap for choosing best Strategy. Best Strategy corresponds to the
        attribute having most magnitude and a Max heap lends itself very
        well for this purpose.
     */
    private PriorityQueue<PlayerAttributeItem> m_attributeMaxHeap = null;

    private HpFightStrategy[] m_fightStrategies = null;

    private static class PlayerAttributeItem implements Comparable<PlayerAttributeItem>, Serializable
    {
        private FightStrategy m_strategy;
        private int m_magnitude;

        PlayerAttributeItem(int mag, FightStrategy strategy)
        {
            m_strategy = strategy;
            m_magnitude = mag;
        }

        //Comparision for max heap
        @Override
        public int compareTo(PlayerAttributeItem other)
        {
            if(this.m_magnitude > other.m_magnitude)
            {
                return -1;
            }
            else if (other.m_magnitude > this.m_magnitude)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }

    protected String m_playerName;// your name

    public HpPlayer(HpPlayerBuilder hpPlayerBuilder)
    {
        super(hpPlayerBuilder);
        m_playerName = hpPlayerBuilder.m_name;
        m_name = "Harry POTTER";//Right now only Harry Potter restricted to be player :)
        //Strength is not configurable Gme rule. Hence not part of builder :)
        m_strength = 100; //Can externalize
        m_attributeMaxHeap = hpPlayerBuilder.m_attributeMaxHeap;
        m_attributeMaxHeap.add(new PlayerAttributeItem(m_strength, FightStrategy.STRENGTH));
        m_fightStrategyToAttributeMap = hpPlayerBuilder.m_fightStrategyToAttributeMap;
        m_fightStrategyToAttributeMap.put(FightStrategy.STRENGTH, m_strength);
        m_fightStrategies = new HpFightStrategy[]{new KnowledgeFightStrategy(), new WisdomFightStrategy(), new MagicalFightStrategy(), new StrengthFightStrategy()};
    }


    @Override
    public void setPosition(Location location)
    {
        setLocation(location);
    }

    @Override
    public Location getPosition()
    {
        return getLocation();
    }

    public String displayStats()
    {
        return toString();
    }

    @Override
    public String toString()
    {
        return super.toString();
    }

    @Override
    public void moveUp()
    {
        m_location.up();
    }

    @Override
    public void moveDown()
    {
        m_location.down();
    }

    @Override
    public void moveLeft()
    {
        m_location.left();
    }

    @Override
    public void moveRight()
    {
        m_location.right();
    }

   /*
    Can only use child class Builders. Bit of code duplicated in form of parent class builder.
    But this way can ensure members in parent class initialized in parent class itself.
    Note also the builder is used not for immutablility, but to prevent
    telescope anti-pattern when number of attributes increase.
    */
    public static class HpPlayerBuilder extends HpCharacterBuilder
    {
        private String m_name;
        private PriorityQueue<PlayerAttributeItem> m_attributeMaxHeap = new PriorityQueue<>();
        private Map<FightStrategy, Integer> m_fightStrategyToAttributeMap = new HashMap<>();
        private HpPlayerBuilder()
        {

        }

        public HpPlayerBuilder name(String name)
        {
            m_name = name;
            return this;
        }
        public HpPlayerBuilder magic(int magic)
        {
            m_attributeMaxHeap.add(new PlayerAttributeItem(magic, FightStrategy.MAGIC));
            m_fightStrategyToAttributeMap.put(FightStrategy.MAGIC, magic);
            super.magic(magic);
            return this;
        }

        public HpPlayerBuilder knowledge(int knowledge)
        {
            m_attributeMaxHeap.add(new PlayerAttributeItem(knowledge, FightStrategy.KNOWLEDGE));
            m_fightStrategyToAttributeMap.put(FightStrategy.KNOWLEDGE, knowledge);
            super.knowledge(knowledge) ;
            return this;
        }

        public HpPlayerBuilder wisdom(int wisdom)
        {
            m_attributeMaxHeap.add(new PlayerAttributeItem(wisdom, FightStrategy.WISDOM));
            m_fightStrategyToAttributeMap.put(FightStrategy.WISDOM, wisdom);
            super.wisdom(wisdom);
            return this;
        }

        public HpPlayerBuilder experience(Experience experience)
        {
            super.experience(experience);
            return this;
        }

        public static HpPlayerBuilder create()
        {
            return new HpPlayerBuilder();
        }

        @Override
        public HpPlayer build() {
            return new HpPlayer(this);
        }
    }

    public boolean isDead()
    {
        if(m_strength <= 0 && ( m_wisdom <= 0 || m_magic <= 0 || m_knowledge <= 0))
        {
            return true;
        }
        return false;
    }

    @Override
    public int fight(final Enemy enemy)
    {
        //Choose a strategy
        final PlayerAttributeItem attributeItem = chooseBestAttributeItem();
        final HpFightStrategy hpFightStrategy = m_fightStrategies[attributeItem.m_strategy.getFightStrategy()];
        //Can safely cast to Hp type
        Integer enemyMagnitude = ((HpEnemy)enemy).getFightStrategyToAttributeMap().get(attributeItem.m_strategy);
        if(null == enemyMagnitude)
        {
            enemyMagnitude = 0;
        }
        hpFightStrategy.setUp(attributeItem.m_magnitude, enemyMagnitude);

        int damage = fight(hpFightStrategy);
        return damage;
    }


    //Root of heap is best attribute item
    private PlayerAttributeItem chooseBestAttributeItem()
    {
       return m_attributeMaxHeap.peek();
    }

    //Fight with this enemy using this strategy
    private int fight(final HpFightStrategy fightStrategy)
    {
        int damage = fightStrategy.inflict();
        return damage;
    }

    private void updateAttributeMap()
    {
        m_fightStrategyToAttributeMap.put(FightStrategy.MAGIC, m_magic);
        m_fightStrategyToAttributeMap.put(FightStrategy.STRENGTH, m_strength);
        m_fightStrategyToAttributeMap.put(FightStrategy.WISDOM, m_wisdom);
        m_fightStrategyToAttributeMap.put(FightStrategy.KNOWLEDGE, m_knowledge);
    }


    private void rebuildAttributeheap()
    {
        List<PlayerAttributeItem> tempList = new ArrayList<>();
        //reconstruct attribute heap;
        while (!m_attributeMaxHeap.isEmpty()) {
            HpPlayer.PlayerAttributeItem playerAttributeItem = m_attributeMaxHeap.poll();

            switch (playerAttributeItem.m_strategy)
            {
                case KNOWLEDGE:
                {
                    tempList.add(new PlayerAttributeItem(m_knowledge, FightStrategy.KNOWLEDGE));
                    break;
                }

                case WISDOM:
                {
                    tempList.add(new PlayerAttributeItem(m_wisdom, FightStrategy.WISDOM));
                    break;
                }

                case MAGIC:
                {
                    tempList.add(new PlayerAttributeItem(m_magic, FightStrategy.MAGIC));
                    break;
                }

                case STRENGTH:
                {
                    tempList.add(new PlayerAttributeItem(m_strength, FightStrategy.STRENGTH));
                    break;
                }
            }

        }

        for(PlayerAttributeItem item : tempList)
        {
            m_attributeMaxHeap.add(item);
        }
    }

    //Game rule, the damage is applied to best attribute  and one fourth to remaining attributes
    public void applyDamage(int damage)
    {
        final FightStrategy bestFightStrategy = getBestFightStrategyEnum();
       switch(bestFightStrategy)
       {
           case KNOWLEDGE:
           {
               m_knowledge -= damage;
               m_magic -= damage/4;
               m_strength -= damage/4;
               m_wisdom -= damage/4;
               break;
           }

           case MAGIC:
           {
               m_magic -= damage;
               m_strength -= damage/4;
               m_wisdom -= damage/4;
               m_knowledge -= damage/4;
               break;
           }
           case STRENGTH:
           {
               m_strength -= damage;
               m_magic -= damage/4;
               m_wisdom -= damage/4;
               m_knowledge -= damage/4;
               break;
           }
           case WISDOM:
           {
               m_wisdom -= damage;
               m_magic -= damage/4;
               m_knowledge -= damage/4;
               m_strength -= damage/4;
               break;
           }

       }

       updateAttributeMap();
       rebuildAttributeheap();
    }

    public HpFightStrategy getBestHpFightStrategy()
    {
        return m_fightStrategies[m_attributeMaxHeap.peek().m_strategy.getFightStrategy()];
    }

    private FightStrategy getBestFightStrategyEnum()
    {
        return m_attributeMaxHeap.peek().m_strategy;
    }
}
