package com.game.rpg.entities.menu;

import com.game.rpg.common.OutputDisplay;
import com.game.rpg.common.constants.PropertyKeys;
import com.game.rpg.exceptions.InvalidConfigurationException;
import com.game.rpg.exceptions.InvalidFactoryUsageException;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static com.game.rpg.common.constants.MenuTypes.GAME_MAIN_MENU;
import static com.game.rpg.common.constants.MenuTypes.HP_IN_GAME_MENU;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HpInGameMenuTest
{
    @Before
    public void setUpTest()
    {
        OutputDisplay.forceClearBuffer();
    }

    @Test
    public void testInvalidConfigurationOfInGameMenuProps1()
    {
        try
        {
            final Properties config = new Properties();
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_TITLE, "Welcome to Harry Potter Game!");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_COUNT, "3!");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_TEXT, "Rule1 | Rule2 | Rule3");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_COLOR, "black | black | black ");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_FONT, "bold | bold | bold");

            InGameMenuInterface inGameMenuInterface  = InGameMenuFactory.createMenu(HP_IN_GAME_MENU, config);
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
    public void testInvalidConfigurationOfInGameMenuProps2()
    {
        try
        {
            final Properties config = new Properties();
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_TITLE, "Welcome to Harry Potter Game!");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_COUNT, "3");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_TEXT, "Rule1 | Rule2 | Rule3 | Rule4");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_COLOR, "black | black | black ");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_FONT, "bold | bold | bold");

            InGameMenuInterface inGameMenuInterface  = InGameMenuFactory.createMenu(HP_IN_GAME_MENU, config);
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
    public void testInvalidConfigurationOfInGameMenuProps3()
    {
        try
        {
            final Properties config = new Properties();
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_TITLE, "Welcome to Harry Potter Game!");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_COUNT, "3");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_TEXT, "Rule1 | Rule2 | Rule3 ");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_COLOR, "black | black | black | black");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_FONT, "bold | bold | bold");

            InGameMenuInterface inGameMenuInterface  = InGameMenuFactory.createMenu(HP_IN_GAME_MENU, config);
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
    public void testInvalidConfigurationOfInGameMenuProps4()
    {
        try
        {
            final Properties config = new Properties();
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_TITLE, "Welcome to Harry Potter Game!");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_COUNT, "3");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_TEXT, "Rule1 | Rule2 | Rule3 ");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_COLOR, "black | black | black ");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_FONT, "bold | bold | bold | bold");

            InGameMenuInterface inGameMenuInterface  = InGameMenuFactory.createMenu(HP_IN_GAME_MENU, config);
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
    public void testInvalidConfigurationOfInGameMenuProps5()
    {
        try
        {
            final Properties config = new Properties();
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_TITLE, "Welcome to Harry Potter Game!");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_COUNT, "3");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_TEXT, "Rule1 | Rule2 | Rule3 ");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_COLOR, "black | black | black ");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_FONT, "bold | bold | bold ");

            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_PROGRESS_INSTRUCTIONS_COUNT, "3");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_PROGRESS_INSTRUCTIONS_TEXT, "Rule1 | Rule2 | Rule3 ");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_PROGRESS_INSTRUCTIONS_COLOR, "black | black | black | black");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_PROGRESS_INSTRUCTIONS_FONT, "bold | bold | bold ");

            InGameMenuInterface inGameMenuInterface  = InGameMenuFactory.createMenu(HP_IN_GAME_MENU, config);
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
    public void testInvalidFactoryUsage()
    {
        try
        {
            final Properties config = new Properties();
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_TITLE, "Welcome to Harry Potter Game!");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_COUNT, "3");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_TEXT, "Rule1 | Rule2 | Rule3 ");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_COLOR, "black | black | black ");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_FONT, "bold | bold | bold | bold");

            InGameMenuInterface inGameMenuInterface  = InGameMenuFactory.createMenu(GAME_MAIN_MENU, config);
        }
        catch (InvalidConfigurationException ive)
        {
            assertTrue("InvalidConfigurationException thrown", true);
        }
        catch(InvalidFactoryUsageException ife)
        {
            assertTrue("InvalidFactoryUsageException Occured", true);
        }
    }

    @Test
    public void testInstructionDisplay()
    {
        InGameMenuInterface inGameMenuInterface  = null;
        try
        {
            final Properties config = new Properties();
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_TITLE, "Welcome to Harry Potter Game!");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_COUNT, "3");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_TEXT, "Rule1 | Rule2 | Rule3 ");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_COLOR, "black | black | black ");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_RULES_FONT, "bold | bold | bold ");

            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_PROGRESS_TITLE, "Start Exploring!");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_PROGRESS_INSTRUCTIONS_COUNT, "3");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_PROGRESS_INSTRUCTIONS_TEXT, "InGame-Rule1 | InGame-Rule2 | InGame-Rule3 ");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_PROGRESS_INSTRUCTIONS_COLOR, "black | black | black");
            config.setProperty(PropertyKeys.HP_IN_GAME_MENU_GAME_PROGRESS_INSTRUCTIONS_FONT, "bold | bold | bold ");

            inGameMenuInterface  = InGameMenuFactory.createMenu(HP_IN_GAME_MENU, config);

        }
        catch(InvalidConfigurationException ice)
        {
            assertTrue("Test Failed", false);
        }
        catch(InvalidFactoryUsageException ife)
        {
            assertFalse("InvalidFactoryUsageException does not happen", false);
        }

        inGameMenuInterface.displayStartInstructions();
        inGameMenuInterface.displayGameProgressInstructions();

        assertEquals("First Message", "Welcome to Harry Potter Game!",OutputDisplay.getOutputBuffer().get(0));
        assertEquals("Second Message", "Rule1",OutputDisplay.getOutputBuffer().get(1));
        assertEquals("Third Message", "Rule2",OutputDisplay.getOutputBuffer().get(2));
        assertEquals("Fourth Message", "Rule3",OutputDisplay.getOutputBuffer().get(3));

        assertEquals("Fifth Message", "Start Exploring!",OutputDisplay.getOutputBuffer().get(4));
        assertEquals("Sixth Message", "InGame-Rule1",OutputDisplay.getOutputBuffer().get(5));
        assertEquals("Seventh Message", "InGame-Rule2",OutputDisplay.getOutputBuffer().get(6));
        assertEquals("Eighth Message", "InGame-Rule3",OutputDisplay.getOutputBuffer().get(7));
    }

}
