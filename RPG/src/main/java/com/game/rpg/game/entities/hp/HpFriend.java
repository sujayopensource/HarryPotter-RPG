package com.game.rpg.game.entities.hp;

import com.game.rpg.game.entities.Experience;
import com.game.rpg.game.entities.NonPlayer;

public class HpFriend extends  HpGameCharacter implements NonPlayer
{
    private static final long serialVersionUID = -8203570874040117L;

    public HpFriend(HpFriendBuilder hpPlayerBuilder)
    {
        super(hpPlayerBuilder);
        //No name for Friend now
        //Strength is not configurable Gme rule. Hence not part of builder :)
        m_strength = 100; //Can externalize
    }

    public static class HpFriendBuilder extends HpCharacterBuilder
    {
        private HpFriendBuilder()
        {

        }

        public HpFriendBuilder experience(Experience experience)
        {
            super.experience(experience);
            return this;
        }

        public HpFriendBuilder magic(int magic)
        {
            super.magic(magic);
            return this;
        }

        public HpFriendBuilder knowledge(int know)
        {
            super.knowledge(know) ;
            return this;
        }

        public HpFriendBuilder wisdom(int wisdom)
        {
            super.wisdom(wisdom);
            return this;
        }

        public static HpFriendBuilder create()
        {
            return new HpFriendBuilder();
        }

        @Override
        public HpFriend build()
        {
            return new HpFriend(this);
        }
    }

    public boolean isDead()
    {
        return ( m_knowledge <=0 && m_wisdom <=0 && m_magic<=0 && m_strength<=0);
    }
    @Override
    public int reward()
    {
        //Simple reward mechanism -average of attributes(no experience)
        int reward = (m_knowledge + m_magic + m_wisdom + m_strength)/ 4;
        recomputeAttributesOnReward(reward / 4);
        return reward;

    }

    private void recomputeAttributesOnReward(int avgReward)
    {
        m_knowledge -= avgReward;
        m_magic -= avgReward;
        m_wisdom -= avgReward;
        m_strength -= avgReward;


        if(m_strength < 0)
        {
            m_strength = 0;
        }

        if(m_magic < 0)
        {
            m_magic = 0;
        }

        if(m_knowledge < 0)
        {
            m_knowledge = 0;
        }

        if(m_wisdom < 0)
        {
            m_wisdom = 0;
        }

    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}
