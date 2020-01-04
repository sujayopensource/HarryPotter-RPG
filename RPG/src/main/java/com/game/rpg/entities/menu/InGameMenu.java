package com.game.rpg.entities.menu;

import com.game.rpg.entities.instruction.Instruction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public abstract class InGameMenu extends Menu implements InGameMenuInterface, Serializable
{
    protected List<Instruction> m_startInstructions = new ArrayList<>();
    protected List<Instruction> m_gameProgressInstructions = new ArrayList<>();
}
