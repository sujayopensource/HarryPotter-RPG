package com.game.rpg.entities.menu;

import com.game.rpg.common.OutputDisplay;
import com.game.rpg.common.constants.PropertyKeys;
import com.game.rpg.entities.instruction.Instruction;
import com.game.rpg.exceptions.InvalidConfigurationException;

import java.util.Properties;

public class MainMenu extends Menu implements MenuInterface
{
    @Override
    public void display()
    {
        OutputDisplay.writeOutput(m_title);
        //An instruction is an entity with content(text) and how to display that content(display attributes like color, font).
        //The display attributes are yet to be used(TODO)
        for(final Instruction instruction : m_instructions)
        {
            instruction.display();
        }

    }

   public MainMenu(final Properties config) throws InvalidConfigurationException
   {
       m_title = config.getProperty(PropertyKeys.MAIN_MENU_TITLE);

       int instructionCount =  0;
       try
       {
           instructionCount = Integer.parseInt(config.getProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_COUNT));
       }
       catch(NumberFormatException nfe)
       {
           throw new InvalidConfigurationException("Invalid Configuration of main menu properties. Instruction Count not in Numeric format.Please check menu.properties file");
       }

       final String[] instructionsText = config.getProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_TEXT).split("\\|");
       final String[] instructionsColor = config.getProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_COLOR).split("\\|");
       final String[] instructionsFont = config.getProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_FONT).split("\\|");

       //validate the input
       if(instructionCount != instructionsText.length || instructionCount != instructionsColor.length || instructionCount != instructionsFont.length)
       {
           throw new InvalidConfigurationException("Invalid Configuration of main menu properties. Please check menu.properties file");
       }

       //Number of instructions wont be very large, so no problem of large number of Builders created.
       //But the properties of each instruction could increase.
       for(int ind = 0; ind < instructionCount; ind++)
       {
           m_instructions.add(new Instruction.InstructionBuilder(instructionsText[ind]).color(instructionsColor[ind]).font(instructionsFont[ind]).build());
       }
   }
}
