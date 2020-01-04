package com.game.rpg.engine;

import com.game.rpg.common.OutputDisplay;
import com.game.rpg.game.RPGGame;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameStateManager
{
    private static Logger LOGGER = Logger.getLogger(GameStateManager.class.getName());
    private static final String SERIALIZATION_PATH = System.getProperty("user.dir") + File.separator + "gameState.ser";

    public static void saveGame(final RPGGame game) throws IOException
    {
        if(null == game)
        {
            LOGGER.log(Level.SEVERE,"Game doesnt exist. Cannot save!!");
            OutputDisplay.writeError("Game doesnt exist. Cannot save!!");
            return;
        }
        FileOutputStream fos = new FileOutputStream(SERIALIZATION_PATH);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        // write object to file
        oos.writeObject(game);
        oos.close();
    }

    public static RPGGame loadGame()throws IOException
    {
        final RPGGame game;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try
        {
            fis = new FileInputStream(SERIALIZATION_PATH);
        }
        catch(FileNotFoundException fe)
        {
            return null;
        }

        ois = new ObjectInputStream(fis);
        // write object to file
        try
        {
            game = (RPGGame)ois.readObject();
        }
        catch(InvalidClassException ice)
        {
            LOGGER.log(Level.SEVERE,"Incompatiblity between saved and loaded game.Cannot load game");
            OutputDisplay.writeError("Incompatiblity between saved and loaded game.Cannot load game.Please go back");

            return null;
        }
        catch(ClassNotFoundException ce )
        {
            LOGGER.log(Level.SEVERE, "this class does not exist.Cannot Load game");
            OutputDisplay.writeError("this class does not exist.Cannot Load game");
            throw new IOException(ce);
        }

        finally
        {
            ois.close();
            fis.close();

        }
        return game;
    }
}
