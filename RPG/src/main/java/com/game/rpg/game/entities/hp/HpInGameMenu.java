package com.game.rpg.game.entities.hp;

import com.game.rpg.common.OutputDisplay;
import com.game.rpg.common.constants.PropertyKeys;
import com.game.rpg.entities.instruction.Instruction;
import com.game.rpg.entities.menu.InGameMenu;
import com.game.rpg.exceptions.InvalidConfigurationException;

import java.util.List;
import java.util.Properties;

public class HpInGameMenu extends InGameMenu
{
    protected String m_progressTitle;
    public HpInGameMenu(Properties config) throws InvalidConfigurationException
    {
        m_title = config.getProperty(PropertyKeys.HP_IN_GAME_MENU_TITLE);
        m_progressTitle = config.getProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_PROGRESS_TITLE);
        init(m_startInstructions, config, PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_COUNT, PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_TEXT, PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_COLOR, PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_FONT);
        init(m_gameProgressInstructions, config, PropertyKeys.HP_IN_GAME_MENU_GAME_PROGRESS_INSTRUCTIONS_COUNT, PropertyKeys.HP_IN_GAME_MENU_GAME_PROGRESS_INSTRUCTIONS_TEXT, PropertyKeys.HP_IN_GAME_MENU_GAME_PROGRESS_INSTRUCTIONS_COLOR, PropertyKeys.HP_IN_GAME_MENU_GAME_PROGRESS_INSTRUCTIONS_FONT);
        m_instructions.addAll(m_startInstructions);
        m_instructions.addAll(m_gameProgressInstructions);

    }

    private void init(List<Instruction> instructionList, Properties config, final String instCountProp, final String instTextProp, final String insColorProp, final String instFontProp) throws InvalidConfigurationException
    {
        int gameStartInstructionCount = 0;
        try
        {
            gameStartInstructionCount =  Integer.parseInt(config.getProperty(instCountProp));
        }
        catch(NumberFormatException nfe)
        {
            throw new InvalidConfigurationException("Invalid Configuration of Harry Potter In-game menu properties. Instruction Count not in Numeric format.Please check menu.properties file");
        }

        final String[] gameStartInstructionText = config.getProperty(instTextProp).split("\\|");
        final String[] gameStartInstructionColor = config.getProperty(insColorProp).split("\\|");
        final String[] gameStartInstructionFont = config.getProperty(instFontProp).split("\\|");

        //validate the input
        if(gameStartInstructionCount != gameStartInstructionText.length || gameStartInstructionCount != gameStartInstructionColor.length || gameStartInstructionCount != gameStartInstructionFont.length)
        {
            throw new InvalidConfigurationException("Invalid Configuration of hp In-game menu properties. Please check menu.properties");
        }

        //Number of instructions wont be very large, so no problem of large number of Builders created.
        //But the properties of each instruction could increase.
        for(int ind = 0; ind < gameStartInstructionCount; ind++)
        {
            instructionList.add(new Instruction.InstructionBuilder(gameStartInstructionText[ind]).color(gameStartInstructionColor[ind]).font(gameStartInstructionFont[ind]).build());
        }
    }

    @Override
    public void displayStartInstructions()
    {
        OutputDisplay.writeOutput(m_title);
        for(Instruction instruction : m_startInstructions)
        {
            instruction.display();
        }

    }

    @Override
    public void displayGameProgressInstructions()
    {
        OutputDisplay.writeOutput(m_progressTitle);
        for(Instruction instruction : m_gameProgressInstructions)
        {
            instruction.display();
        }
    }
}
