package com.game.rpg.entities.menu;

import com.game.rpg.exceptions.InvalidConfigurationException;
import com.game.rpg.exceptions.InvalidFactoryUsageException;

import java.util.Properties;

import static com.game.rpg.common.constants.MenuTypes.GAME_MAIN_MENU;

/*
    Right now only one main meu present. In future many will be added.
 */
public class MenuFactory
{
    public static MenuInterface createMenu(String type, Properties config) throws InvalidConfigurationException, InvalidFactoryUsageException
    {
        switch(type)
        {
            case GAME_MAIN_MENU:
            {
                return new MainMenu(config);
            }

            default:
            {
                throw new InvalidFactoryUsageException("This type of menu not supported.Cannot instantiate it.");
            }
        }
    }
}
