package com.game.rpg.engine;

import com.game.rpg.common.OutputDisplay;
import com.game.rpg.common.constants.PropertyFiles;
import com.game.rpg.entities.menu.MenuFactory;
import com.game.rpg.entities.menu.MenuInterface;
import com.game.rpg.exceptions.InvalidConfigurationException;
import com.game.rpg.exceptions.InvalidFactoryUsageException;
import com.game.rpg.game.GameFactory;
import com.game.rpg.game.RPGGame;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.game.rpg.common.constants.MenuTypes.GAME_MAIN_MENU;

public class GameEngine
{
    private static final Logger LOGGER = Logger.getLogger(GameEngine.class.getName());
    private MenuInterface m_mainMenu = null;
    private static final  String[] PROPERTY_FILES = {PropertyFiles.MENU_PROPERTIES};
    private final Map<String, Properties> m_propertyInfo = new HashMap<>();

    public final String CHOICE_HARRY_POTTER = "1";
    public final String CHOICE_STAR_WARS = "2";
    public final String CHOICE_EXIT_GAME = "3";

    public final String NEW_GAME = "1";
    public final String LOAD_GAME = "2";

    private RPGGame m_game = null;
    private static class GameEngineInstanceHelper
    {
        private static GameEngine GAMEENGINE_INSTANCE = new GameEngine();
    }
    public static GameEngine create()
    {
        return GameEngineInstanceHelper.GAMEENGINE_INSTANCE;
    }

    public void init() throws IOException
    {
        loadProperties();
        try
        {
            createMainMenu();
        }
        catch (InvalidConfigurationException ice)
        {
            throw new IOException(ice);
        }
        catch(InvalidFactoryUsageException ife)
        {
            throw new IOException(ife);
        }
    }

    public void start() throws IOException
    {
        //Until Exit is pressed
        while(true)
        {
            m_mainMenu.display();
            chooseGame();

            if(null != m_game)
            {
                m_game.init(this); //Game knows its Engine!!
                m_game.start();
            }

        }

    }

    private void loadProperties() throws IOException
    {
        for(int ind = 0; ind < PROPERTY_FILES.length; ind++)
        {
            Properties prop = new Properties();
            InputStream input = null;
            input = GameEngine.class.getClassLoader().getResourceAsStream(PropertyFiles.MENU_PROPERTIES);
            prop.load(input);
            m_propertyInfo.put(PropertyFiles.MENU_PROPERTIES, prop);

        }

    }

    private void chooseGame() throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();

        switch(choice)
        {
            case CHOICE_HARRY_POTTER:
            {
                //Now new game or load game(TODO add a menu)
                OutputDisplay.writeOutput("1 - New Game");
                OutputDisplay.writeOutput("2 - Load game");

                String newOrLoad = scanner.next();

                switch(newOrLoad)
                {
                    case NEW_GAME:
                    {
                        m_game = GameFactory.createGame(GameFactory.CHOICE_HARRY_POTTER, m_propertyInfo.get(PropertyFiles.MENU_PROPERTIES));
                        LOGGER.log(Level.FINE, "game created");
                        return;
                    }

                    case LOAD_GAME:
                    {
                        //load from state manger
                        m_game = GameStateManager.loadGame();
                        if(null == m_game)
                        {
                            LOGGER.log(Level.WARNING, "No game saved before to load!!Start Again");
                            OutputDisplay.writeOutput("No game saved before to load!!Start Again");
                            return;
                        }
                        m_game.continueGame();
                        break;
                    }

                    default:
                    {
                        LOGGER.log(Level.INFO, "Sorry wrong choice");
                        OutputDisplay.writeOutput("Sorry wrong choice");
                    }
                }
                break;
            }

            case CHOICE_STAR_WARS:
            {
                OutputDisplay.writeOutput("game not supported yet.Sorry!!");
                break;
            }

            case CHOICE_EXIT_GAME:
            {
                OutputDisplay.writeOutput("Exiting game! Come back next time :)");
                System.exit(0);
            }
        }

    }
    private void createMainMenu() throws InvalidConfigurationException, InvalidFactoryUsageException
    {
        m_mainMenu = MenuFactory.createMenu(GAME_MAIN_MENU, m_propertyInfo.get(PropertyFiles.MENU_PROPERTIES));
    }

    public  Map<String, Properties> getPropertyInfo()
    {
        return m_propertyInfo;
    }

}
