package com.game.rpg.game.hp;

import com.game.rpg.common.Utils;

import java.io.Serializable;

public class WisdomFightStrategy implements HpFightStrategy, Serializable
{
    private static final long serialVersionUID = -81234570873333117L;

    protected int m_sourceWidom;
    protected int m_destWisdom;

    // The point is that wisdom redeces or increases slower :)
    private static final int WISDOM_COEFFICENT = 10;

    public WisdomFightStrategy()
    {

    }

    @Override
    public int inflict()
    {
        if (Utils.getRandomNum(WISDOM_COEFFICENT) + Utils.getRandomNum(WISDOM_COEFFICENT / 2) + Utils.getRandomNum(WISDOM_COEFFICENT / 5) > WISDOM_COEFFICENT) {
            return (((m_sourceWidom - m_destWisdom) * Utils.getRandomNum(WISDOM_COEFFICENT)) + 1) % WISDOM_COEFFICENT;
        } else {
            return (((m_destWisdom - m_sourceWidom) * Utils.getRandomNum(WISDOM_COEFFICENT)) + 1) % WISDOM_COEFFICENT;
        }

    }

    @Override
    public void setUp(int sourceMag, int destMag)
    {
        m_sourceWidom = sourceMag;
        m_destWisdom = destMag;
    }

    public int getSourceWisdom()
    {
        return m_sourceWidom;
    }

    public int getDestWisdom() {
        return m_destWisdom;
    }

    public void setSourceWisdom(int sourceWisdom)
    {
        m_sourceWidom = sourceWisdom;
    }

    public void setDestWisdom(int destWisdom)
    {
        m_destWisdom = destWisdom;
    }

    public static int getWisdomCoefficent()
    {
        return WISDOM_COEFFICENT;
    }
}
