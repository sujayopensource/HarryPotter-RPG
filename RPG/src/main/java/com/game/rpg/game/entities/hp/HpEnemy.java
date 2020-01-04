package com.game.rpg.game.entities.hp;

import com.game.rpg.common.Utils;
import com.game.rpg.game.entities.Enemy;
import com.game.rpg.game.entities.Experience;
import com.game.rpg.game.entities.Player;
import com.game.rpg.game.hp.*;

import java.util.HashMap;
import java.util.Map;

public class HpEnemy extends HpFightCharacter implements Enemy
{
    private static final long serialVersionUID = -820670879040117L;

    private HpFightStrategy[] m_fightStrategies = null;

    public HpFightStrategy[] getFightStrategies()
    {
        return m_fightStrategies;
    }

    public HpEnemy(HpEnemyBuilder hpPlayerBuilder)
    {
        super(hpPlayerBuilder);
       //No name for enemies now
        //Strength is not configurable Gme rule. Hence not part of builder :)
        m_strength = 60; //Can externalize
        m_fightStrategyToAttributeMap = hpPlayerBuilder.m_fightStrategyToAttributeMap;
        m_fightStrategyToAttributeMap.put(FightStrategy.STRENGTH, m_strength);
        //Must be in ORDER of Enums defined.
        m_fightStrategies = new HpFightStrategy[]{new KnowledgeFightStrategy(), new WisdomFightStrategy(), new MagicalFightStrategy(), new StrengthFightStrategy()};
    }

    public static class HpEnemyBuilder extends HpCharacterBuilder {

        private Map<FightStrategy, Integer> m_fightStrategyToAttributeMap = new HashMap<>();
        private HpEnemyBuilder()
        {

        }

        public HpEnemyBuilder experience(Experience experience)
        {
            super.experience(experience);
            return this;
        }
        public HpEnemyBuilder magic(int magic) {
            m_fightStrategyToAttributeMap.put(FightStrategy.MAGIC, magic);
            super.magic(magic);
            return this;
        }

        public HpEnemyBuilder knowledge(int know) {
            m_fightStrategyToAttributeMap.put(FightStrategy.KNOWLEDGE, know);
            super.knowledge(know);
            return this;
        }

        public HpEnemyBuilder wisdom(int wisdom) {
            m_fightStrategyToAttributeMap.put(FightStrategy.WISDOM, wisdom);
            super.wisdom(wisdom);
            return this;
        }

        public static HpEnemyBuilder create() {
            return new HpEnemyBuilder();
        }

        @Override
        public HpEnemy build() {
            return new HpEnemy(this);
        }
    }

    public Map<HpEnemy.FightStrategy, Integer>  getFightStrategyToAttributeMap()
    {
        return m_fightStrategyToAttributeMap;
    }

    //Game rule, the damage is applied to all attributes of enemy equally;
    public void applyDamage(int damage)
    {
        m_knowledge -= damage;
        m_magic -= damage;
        m_strength -= damage;
        m_wisdom -= damage;

        m_fightStrategyToAttributeMap.put(FightStrategy.MAGIC, m_magic);
        m_fightStrategyToAttributeMap.put(FightStrategy.STRENGTH, m_strength);
        m_fightStrategyToAttributeMap.put(FightStrategy.WISDOM, m_wisdom);
        m_fightStrategyToAttributeMap.put(FightStrategy.KNOWLEDGE, m_knowledge);

    }

    public boolean isDead()
    {
        if(m_strength <= 0 || m_wisdom <= 0 || m_magic <= 0 || m_knowledge <= 0)
        {
            return true;
        }
        return false;
    }

    @Override
    public int fight(final Player player)
    {
        //Choose a strategy
        final int strategyIndex = chooseStrategyIndex();
        final HpFightStrategy hpFightStrategy = m_fightStrategies[strategyIndex];
        Integer playerMag = ((HpPlayer)player).getFightStrategyToAttributeMap().get(FightStrategy.fromInteger(strategyIndex));
        if(null == playerMag)
        {
            playerMag = 0;
        }
        hpFightStrategy.setUp(m_fightStrategyToAttributeMap.get(FightStrategy.fromInteger(strategyIndex)),playerMag);
        return fight(hpFightStrategy);
    }

    //Fight with this player  using this strategy
    private int fight(final HpFightStrategy fightStrategy)
    {
        int damage = fightStrategy.inflict();
        return damage;
    }

    public void reset()
    {
        //On being dead, exp is fully rewardsed.Hence it becomes 0
        m_experience.setLevel(0);
        m_experience.setMagnitude(0);
    }

    //Enemy's reward is complete experience
    @Override
    public int reward()
    {
        int totalExperience = m_experience.getTotal();
        return totalExperience;
    }

    //Enemy chooses strategy in a Random manner. Enemy not as intelligent as player :)
    public int chooseStrategyIndex()
    {
       int randomIndex = Utils.getRandomNum(m_fightStrategies.length);
       return randomIndex;
    }
}
