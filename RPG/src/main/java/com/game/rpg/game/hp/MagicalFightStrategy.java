package com.game.rpg.game.hp;

import com.game.rpg.common.Utils;

import java.io.Serializable;

public class MagicalFightStrategy implements HpFightStrategy, Serializable
{
    private static final long serialVersionUID = -8266660873333117L;
    //Magic level of attacker
    protected int m_sourceMagic;
    //Magic level of atackee
    protected int m_destMagic;
    //Can externalize. Its the remaining of damage that will be dedcuted from or added to Enemy
    private static final int MAGIC_COEFFICIENT = 100;
    public MagicalFightStrategy()
    {

    }
    public MagicalFightStrategy(int sourceMagic, int destMagic)
    {
        m_sourceMagic = sourceMagic;
        m_destMagic = destMagic;
    }
    @Override
    public int inflict()
    {
        if(Utils.getRandomNum(MAGIC_COEFFICIENT) > MAGIC_COEFFICIENT/2)
        {
            return (( (m_sourceMagic - m_destMagic) * Utils.getRandomNum(MAGIC_COEFFICIENT)) + 1) % MAGIC_COEFFICIENT;
        }
        else
        {
            return( ((m_destMagic - m_sourceMagic) * Utils.getRandomNum(MAGIC_COEFFICIENT))+ 1)  % MAGIC_COEFFICIENT;
        }

    }

    @Override
    public void setUp(int sourceMag, int destMag)
    {
        m_sourceMagic = sourceMag;
        m_destMagic = destMag;
    }

    public int getSourceMagic()
    {
        return m_sourceMagic;
    }

    public int getDestMagic() {
        return m_destMagic;
    }

    public void setSourceMagic(int sourceMagic)
    {
        m_sourceMagic = sourceMagic;
    }

    public void setDestMagic(int destMagic)
    {
        m_destMagic = destMagic;
    }

    public static int getMagicCoefficient()
    {
        return MAGIC_COEFFICIENT;
    }
}
