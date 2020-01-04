package com.game.rpg.entities.menu;

import com.game.rpg.common.OutputDisplay;
import com.game.rpg.common.constants.PropertyKeys;
import com.game.rpg.exceptions.InvalidConfigurationException;
import com.game.rpg.entities.instruction.Instruction;

import com.game.rpg.exceptions.InvalidFactoryUsageException;
import org.junit.Before;
import org.junit.Test;

import static com.game.rpg.common.constants.MenuTypes.HP_IN_GAME_MENU;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Properties;

import static com.game.rpg.common.constants.MenuTypes.GAME_MAIN_MENU;

public class MainMenuTest
{

    @Before
    public void setUpTest()
    {
        OutputDisplay.forceClearBuffer();
    }

    @Test
    public void testInvalidConfigurationOfMenuProps1()
    {
        try
        {
            final Properties config = new Properties();
            config.setProperty(PropertyKeys.MAIN_MENU_TITLE, "Choose Game:");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_COUNT, "3!");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_TEXT, "1 - Harry Potter | 2 - Star Wars | 3 - Exit game");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_COLOR, "black | black | black ");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_FONT, "bold | bold | bold");

            MenuInterface mainMenu  = MenuFactory.createMenu(GAME_MAIN_MENU, config);
        }
        catch (InvalidConfigurationException ive)
        {
            assertTrue("InvalidConfigurationException thrown", true);
        }
        catch(InvalidFactoryUsageException ife)
        {
            assertFalse("InvalidFactoryUsageException does not happen", false);
        }

    }

    @Test
    public void testInvalidConfigurationOfMenuProps2()
    {
        try
        {
            final Properties config = new Properties();
            config.setProperty(PropertyKeys.MAIN_MENU_TITLE, "Choose Game:");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_COUNT, "3");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_TEXT, "1 - Harry Potter | 2 - Star Wars | 3 - Exit game | 4 - Do nothing");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_COLOR, "black | black | black ");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_FONT, "bold | bold | bold");

            MenuInterface mainMenu  = MenuFactory.createMenu(GAME_MAIN_MENU, config);
        }
        catch (InvalidConfigurationException ive)
        {
            assertTrue("InvalidConfigurationException thrown", true);
        }
        catch(InvalidFactoryUsageException ife)
        {
            assertFalse("InvalidFactoryUsageException does not happen", false);
        }

    }

    @Test
    public void testInvalidConfigurationOfMenuProps3()
    {
        try
        {
            final Properties config = new Properties();
            config.setProperty(PropertyKeys.MAIN_MENU_TITLE, "Choose Game:");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_COUNT, "3");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_TEXT, "1 - Harry Potter | 2 - Star Wars | 3 - Exit game");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_COLOR, "black | black | black | red");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_FONT, "bold | bold | bold");

            MenuInterface mainMenu  = MenuFactory.createMenu(GAME_MAIN_MENU, config);
        }
        catch (InvalidConfigurationException ive)
        {
            assertTrue("InvalidConfigurationException thrown", true);
        }
        catch(InvalidFactoryUsageException ife)
        {
            assertFalse("InvalidFactoryUsageException does not happen", false);
        }
    }

    @Test
    public void testInvalidConfigurationOfMenuProps4()
    {
        try
        {
            final Properties config = new Properties();
            config.setProperty(PropertyKeys.MAIN_MENU_TITLE, "Choose Game:");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_COUNT, "3");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_TEXT, "1 - Harry Potter | 2 - Star Wars | 3 - Exit game");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_COLOR, "black | black | black");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_FONT, "bold | bold | bold | bold");

            MenuInterface mainMenu  = MenuFactory.createMenu(GAME_MAIN_MENU, config);
        }
        catch (InvalidConfigurationException ive)
        {
            assertTrue("InvalidConfigurationException thrown", true);
        }
        catch(InvalidFactoryUsageException ife)
        {
            assertFalse("InvalidFactoryUsageException does not happen", false);
        }

    }


    @Test
    public void testInvalidFactoryUsageException()
    {
        try
        {
            final Properties config = new Properties();
            config.setProperty(PropertyKeys.MAIN_MENU_TITLE, "Choose Game:");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_COUNT, "3");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_TEXT, "1 - Harry Potter | 2 - Star Wars | 3 - Exit game");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_COLOR, "black | black | black");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_FONT, "bold | bold | bold | bold");

            MenuInterface mainMenu  = MenuFactory.createMenu(HP_IN_GAME_MENU, config);
        }
        catch (InvalidConfigurationException ive)
        {
            assertTrue("InvalidConfigurationException thrown", true);
        }
        catch(InvalidFactoryUsageException ife)
        {
            assertTrue("InvalidFactoryUsageException does not happen", true);
        }

    }


    @Test
    public void testSuccessfulInstructionParsing()
    {
        MainMenu mainMenu = null;
        try
        {
            final Properties config = new Properties();
            config.setProperty(PropertyKeys.MAIN_MENU_TITLE, "Choose Game:");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_COUNT, "3");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_TEXT, "1 - Harry Potter | 2 - Star Wars | 3 - Exit game");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_COLOR, "black | black | black");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_FONT, "bold | bold | bold ");

            mainMenu  = (MainMenu)MenuFactory.createMenu(GAME_MAIN_MENU, config);
        }
        catch (InvalidConfigurationException ive)
        {
            assertFalse("NO InvalidConfigurationException thrown", true);
        }
        catch(InvalidFactoryUsageException ife)
        {
            assertFalse("InvalidFactoryUsageException does not happen", false);
        }
        assertEquals("Title is", "Choose Game:", mainMenu.getTitle());
        assertEquals("Instruction Count is", "3", String.valueOf(mainMenu.getInstructions().size()));
        assertInstructionsText(mainMenu.getInstructions(), "1 - Harry Potter", "2 - Star Wars", "3 - Exit game");
        assertInstructionsColor(mainMenu.getInstructions(), "black", "black", "black");
        assertInstructionsFont(mainMenu.getInstructions(), "bold", "bold", "bold");

    }

    @Test
    public void testInstructionDisplay()
    {

        MenuInterface mainMenu = null;
        try
        {
            final Properties config = new Properties();
            config.setProperty(PropertyKeys.MAIN_MENU_TITLE, "Choose Game:");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_COUNT, "3");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_TEXT, "1 - Harry Potter | 2 - Star Wars | 3 - Exit game");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_COLOR, "black | black | black");
            config.setProperty(PropertyKeys.MAIN_MENU_INSTRUCTIONS_FONT, "bold | bold | bold ");

            mainMenu  = MenuFactory.createMenu(GAME_MAIN_MENU, config);

        }

        catch(InvalidConfigurationException ice)
        {
            assertTrue("Test Failed", false);
        }

        catch(InvalidFactoryUsageException ife)
        {
            assertFalse("InvalidFactoryUsageException does not happen", false);
        }

        mainMenu.display();

        assertEquals("First Message", "Choose Game:",OutputDisplay.getOutputBuffer().get(0));
        assertEquals("Second Message", "1 - Harry Potter",OutputDisplay.getOutputBuffer().get(1));
        assertEquals("Third Message", "2 - Star Wars",OutputDisplay.getOutputBuffer().get(2));
        assertEquals("Fourth Message", "3 - Exit game",OutputDisplay.getOutputBuffer().get(3));
    }


    private void assertInstructionsText(List<Instruction> menuInstructions, String ... instructionsText)
    {
        assertEquals(instructionsText.length, menuInstructions.size());

        for(int ind = 0; ind < menuInstructions.size(); ind++)
        {
            assertEquals(instructionsText[ind].trim(), menuInstructions.get(ind).getText().trim());
        }
    }

    private void assertInstructionsColor(List<Instruction> menuInstructions, String ... instructionsColor)
    {
        assertEquals(instructionsColor.length, menuInstructions.size());

        for(int ind = 0; ind < menuInstructions.size(); ind++)
        {
            assertEquals(instructionsColor[ind].trim(), menuInstructions.get(ind).getColor().trim());
        }
    }

    private void assertInstructionsFont(List<Instruction> menuInstructions, String ... instructionsFont)
    {
        assertEquals(instructionsFont.length, menuInstructions.size());

        for(int ind = 0; ind < menuInstructions.size(); ind++)
        {
            assertEquals(instructionsFont[ind].trim(), menuInstructions.get(ind).getFont().trim());
        }
    }

}
