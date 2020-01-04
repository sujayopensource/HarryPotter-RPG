package com.game.rpg.game.hp;

import com.game.rpg.common.Utils;

import java.io.Serializable;

public class KnowledgeFightStrategy implements HpFightStrategy, Serializable
{
    private static final long serialVersionUID = -8203570823453117L;
    protected int m_sourceKnowledge;
    protected int m_destKnowledge;
    private static final int KNOWLEDGE_COEFFICIENT = 50;
    public KnowledgeFightStrategy()
    {

    }

    @Override
    public void setUp(int sourceMag, int destMag)
    {
        m_sourceKnowledge = sourceMag;
        m_destKnowledge = destMag;
    }

    @Override
    public int inflict()
    {
        if(Utils.getRandomNum(KNOWLEDGE_COEFFICIENT) > KNOWLEDGE_COEFFICIENT/2)
        {
            return ((((m_sourceKnowledge - m_destKnowledge) * Utils.getRandomNum(KNOWLEDGE_COEFFICIENT)) +(KNOWLEDGE_COEFFICIENT * Utils.getRandomNum(KNOWLEDGE_COEFFICIENT)) ) + 1) % KNOWLEDGE_COEFFICIENT;
        }
        else
        {
            return ((((m_destKnowledge - m_sourceKnowledge) * Utils.getRandomNum(KNOWLEDGE_COEFFICIENT)) +(KNOWLEDGE_COEFFICIENT * Utils.getRandomNum(KNOWLEDGE_COEFFICIENT))) + 1) % KNOWLEDGE_COEFFICIENT;
        }
    }

    public int getSourceKnowledge()
    {
        return m_sourceKnowledge;
    }

    public int getDestKnowledge() {
        return m_destKnowledge;
    }

    public void setSourceKnowledge(int sourceKnow)
    {
        m_sourceKnowledge = sourceKnow;
    }

    public void setDestKnowledge(int destKnow)
    {
        m_destKnowledge = destKnow;
    }

    public static int getKnowledgeCoefficient()
    {
        return KNOWLEDGE_COEFFICIENT;
    }
}

