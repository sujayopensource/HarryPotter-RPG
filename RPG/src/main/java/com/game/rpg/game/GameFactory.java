package com.game.rpg.game;

import com.game.rpg.game.hp.HpRpgGame;

import java.util.Properties;

public class GameFactory 
{
    public static final String CHOICE_HARRY_POTTER = "HP";
    public static final String CHOICE_STAR_WARS = "SW";

    public static RPGGame createGame(String gameChoice, Properties properties)
    {
        RPGGame rpgGame = null;
        switch(gameChoice)
        {
            case CHOICE_HARRY_POTTER:
            {
                rpgGame =  new HpRpgGame();
                break;
            }

            case CHOICE_STAR_WARS:
            {
                //Not supported yet
                break;
            }
        }

        return rpgGame;
    }
}
