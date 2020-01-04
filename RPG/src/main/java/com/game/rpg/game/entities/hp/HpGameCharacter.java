package com.game.rpg.game.entities.hp;

import com.game.rpg.game.entities.Experience;
import com.game.rpg.game.entities.GameCharacter;
import com.game.rpg.game.entities.Location;

public abstract class HpGameCharacter extends GameCharacter
{
    private static final long serialVersionUID = -81203570879040117L;

    protected int m_magic;
    protected int m_knowledge;
    protected int m_wisdom;
    protected Location m_location;

    public HpGameCharacter(HpCharacterBuilder hpCharacterBuilderBuilder)
    {
        super();
        this.m_magic = hpCharacterBuilderBuilder.m_magic;
        this.m_knowledge = hpCharacterBuilderBuilder.m_knowledge;
        this.m_wisdom = hpCharacterBuilderBuilder.m_wisdom;
        this.m_experience = hpCharacterBuilderBuilder.m_experience;
    }

    public void setMagic(final int magic)
    {
        m_magic = magic;
    }

    public int getMagic()
    {
        return m_magic;
    }

    public void setWisdom(final int wisdom)
    {
        m_wisdom = wisdom;
    }
    public int getWisdom()
    {
        return m_wisdom;
    }

    public void setKnowledge( final int knowledge)
    {
        m_knowledge = knowledge;

    }
    public int getKnowledge()
    {
        return m_knowledge;
    }

    /*
       This is an abstract builder for all Harry potter Characters.
        It might seem redundant as derived classes also have their own builders
        that delegate to this. Its more appropriate to do all settings of base class fields
        in base class. Without having corresponding builders in derived classes
        we would have to explicitly cast(Not favorable). this design provides
        more flexibility without need of explicit casts on client end.

        - A builder here is used  because,  the number of attributes for an Hp character might increase over
       time and it will become difficult to configure the player.

     - Note: HpGameCharacter is not immutable and builder is not used for purpose of imuutability here.
     - Note: The derived classes are using their own builders and delegate to this abstract builder
     */
    protected static abstract class HpCharacterBuilder
    {
        public Experience m_experience;
        protected int m_magic;
        protected int m_knowledge;
        protected int m_wisdom;

        protected HpCharacterBuilder()
        {

        }


        protected HpCharacterBuilder experience(Experience experience)
        {
            m_experience = experience;
            return this;
        }
        protected HpCharacterBuilder magic(int magic)
        {
            m_magic = magic;
            return this;
        }

        protected HpCharacterBuilder knowledge(int knowledge)
        {
            m_knowledge = knowledge;
            return this;
        }

        protected HpCharacterBuilder wisdom(int wisdom)
        {
            m_wisdom = wisdom;
            return this;
        }

        public abstract HpGameCharacter build();

    }

    //Well all characters have life and most will in the future too
    public abstract boolean isDead();

    public void setLocation(final Location location)
    {
        m_location = location;
    }
    public Location getLocation()
    {
        return m_location;
    }

    @Override
    public String toString()
    {
        String statusString = "Magic = " + m_magic + "\n" + "Wisdom = " + m_wisdom + "\n" + "Knowledge = " + m_knowledge + "\n" + "Strength = " + m_strength + "\n";

                if (null != m_experience)
                {
                    statusString = statusString + "Experience :" + m_experience.toString() + "\n";
                }

         return statusString;
    }

}
