package com.game.rpg.entities.menu;

import com.game.rpg.exceptions.InvalidConfigurationException;
import com.game.rpg.exceptions.InvalidFactoryUsageException;
import com.game.rpg.game.entities.hp.HpInGameMenu;

import java.util.Properties;

import static com.game.rpg.common.constants.MenuTypes.HP_IN_GAME_MENU;

/*
    Right now only one main In Game menu present. In future many will be added.
    Similar to Menufactory mostly, but ONLY returns instances of InGameMenuInterface
    as opposed to MenuInterface.
 */
public class InGameMenuFactory
{
    public static InGameMenuInterface createMenu(String type, Properties config) throws InvalidConfigurationException,InvalidFactoryUsageException
    {
        switch(type)
        {
            case HP_IN_GAME_MENU:
            {
                return new HpInGameMenu(config);
            }

            default:
            {
                throw new InvalidFactoryUsageException("This type of In-game menu not supported.Cannot instantiate it.");
            }
        }
    }
}
