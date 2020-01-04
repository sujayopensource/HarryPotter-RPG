package com.game.rpg.game.entities;

import java.io.Serializable;

public abstract class GameCharacter implements Serializable
{
    private static final long serialVersionUID = -8203570879040117L;
    protected String m_name; //Character name
    protected int m_strength;
    protected Experience m_experience;

    public void setExperience(final Experience exp)
    {
        m_experience = exp;
    }

    public Experience getExperience()
    {
        return m_experience;
    }

    public void setStrength(final int strength)
    {
        m_strength = strength;
    }

    public int getStrength()
    {
        return m_strength;
    }

}
