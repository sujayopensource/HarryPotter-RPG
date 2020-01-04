package com.game.rpg.game.hp;

import com.game.rpg.common.Utils;

import java.io.Serializable;

public class StrengthFightStrategy implements HpFightStrategy, Serializable
{
    private static final long serialVersionUID = -7777570873333117L;
    protected int m_sourceStrength;
    protected int m_destStrength;

    private static final int STRENGTH_COEFFICIENT = 40;

    public StrengthFightStrategy()
    {

    }
    @Override
    public int inflict()
    {
        if(Utils.getRandomNum(STRENGTH_COEFFICIENT * 2) > STRENGTH_COEFFICIENT)
        {
            return ((m_sourceStrength - m_destStrength) * Utils.getRandomNum(STRENGTH_COEFFICIENT) + 1) % STRENGTH_COEFFICIENT;
        }
        else
        {
            return ((m_destStrength - m_sourceStrength) * Utils.getRandomNum(STRENGTH_COEFFICIENT) + 1) % STRENGTH_COEFFICIENT;
        }

    }

    @Override
    public void setUp(int sourceMag, int destMag)
    {
        m_sourceStrength = sourceMag;
        m_destStrength = destMag;
    }

    public int getSourceStrength()
    {
        return m_sourceStrength;
    }

    public int getDestStrength()
    {
        return m_destStrength;
    }

    public void setSourceStrength(int srcStrength)
    {
        m_sourceStrength = srcStrength;
    }

    public void setDestStrength(int destStrength)
    {
        m_destStrength = destStrength;
    }

    public static int getStrengthCoefficient()
    {
        return STRENGTH_COEFFICIENT;
    }
}
