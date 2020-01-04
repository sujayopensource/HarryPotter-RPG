package com.game.rpg.entities.menu;

import com.game.rpg.entities.instruction.Instruction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Menu implements Serializable
{
    private static final long serialVersionUID = -299482035708797L;
    protected String m_title;
    protected List<Instruction> m_instructions = new ArrayList<>();

    public String getTitle()
    {
        return m_title;
    }

    public List<Instruction> getInstructions()
    {
        return m_instructions;
    }

    public void setInstructions(final List<Instruction> instructions)
    {
        m_instructions = instructions;
    }
}
