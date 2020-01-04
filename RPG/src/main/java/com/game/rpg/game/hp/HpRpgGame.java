package com.game.rpg.game.hp;

import com.game.rpg.common.InputScanner;
import com.game.rpg.common.OutputDisplay;
import com.game.rpg.common.constants.PropertyFiles;
import com.game.rpg.engine.GameEngine;
import com.game.rpg.entities.menu.InGameMenuFactory;
import com.game.rpg.exceptions.InvalidConfigurationException;
import com.game.rpg.exceptions.InvalidFactoryUsageException;
import com.game.rpg.game.RPGGame;
import com.game.rpg.game.entities.Experience;
import com.game.rpg.game.entities.hp.HpPlayer;

import java.io.IOException;
import java.util.Scanner;

import static com.game.rpg.common.constants.MenuTypes.HP_IN_GAME_MENU;

public class HpRpgGame extends RPGGame
{
    private static final long serialVersionUID = 9482035708790407L;
    private HpGameWorld m_gameWorld;
    //Not Ideal for game to refer to gameEngine. But this is present just to get required info from it
    private transient GameEngine m_gameEngine;
    private HpPlayer m_player;

    private static int PLAYER_INITIAL_EXP_LEVEL = 1;
    private static int PLAYER_INITIAL_EXP_MAG = 60;
    private static final String PROCEED = "1";
    private static final String GO_BACK = "2";

    @Override
    public void init(final GameEngine gameEngine) throws IOException
    {
        m_gameEngine = gameEngine;
        try
        {
            m_inGameMenu = InGameMenuFactory.createMenu(HP_IN_GAME_MENU, m_gameEngine.getPropertyInfo().get(PropertyFiles.MENU_PROPERTIES));

        }
        catch(InvalidConfigurationException iec)
        {
            throw new IOException(iec);
        }
        catch(InvalidFactoryUsageException ife)
        {
            throw new IOException(ife);
        }

        m_gameWorld = new HpGameWorld(m_gameEngine.getPropertyInfo().get(PropertyFiles.HP_GAME_WORLD_PROPERTIES));
        m_inputScanner = new InputScanner();

    }

    @Override
    public void continueGame() throws IOException
    {
        OutputDisplay.writeOutput("Resuming Game. All the Best!");
        m_explorationManager.explore();
    }

    //Not used now. useful in future to update game state or world
    //Like reduce date in gameworld when game is inactive etc
    @Override
    protected void update()
    {

    }

    @Override
    protected void process() throws IOException
    {
        //Have choice to proceed or go back
        m_inGameMenu.displayStartInstructions();
        //Have choice to proceed or go back
        if (!chooseProgress())
        {
            return;
        }
        buildPlayer();
        buildGameWorld();
        //create Fight Manger
        m_interactionInterface = HpFightManager.create(m_gameWorld, m_player);
        m_explorationManager = HpExplorationManager.create(m_gameWorld, m_player, m_inGameMenu, this,(HpFightManager) m_interactionInterface);
        //Start exploring
        m_explorationManager.explore();
    }

    private boolean chooseProgress()
    {
        //TODO add in menu
        OutputDisplay.writeOutput("Choose how to proceed");
        OutputDisplay.writeOutput("1 - Proceed");
        OutputDisplay.writeOutput("2 - Go BACK");
        //When saved game is loaded
        if(null == m_inputScanner)
        {
            m_inputScanner = new InputScanner();
        }

        String choice =  m_inputScanner.getInput().trim();

        switch(choice)
        {
            case PROCEED:
            {
                return true;
            }
            case GO_BACK:
            {
                m_breakGame = true;
                return false;
            }
            default:
            {
                // bad choice, as of now its go back :)
                return false;
            }
        }
    }

    protected void buildGameWorld() {
        m_gameWorld.allocatePlayer(m_player);
        //Try putting this within game world
        m_gameWorld.allocateEnemies();
        m_gameWorld.allocateFriends();
    }

    protected void buildPlayer()
    {
        boolean properPlayer = false;
        //TODO: Externalize strings
        OutputDisplay.writeOutput("Enter your Name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        OutputDisplay.writeOutput("Good " + name + " is the player Harry Potter!");
        OutputDisplay.writeOutput("Allocate magic , intelligence and wisdom to harryPotter(player) in that order. Total magnitude of all 3 must be equal to 300");
        String magic = null;
        String knowledge = null;
        String wisdom = null;

        while (!properPlayer)
        {
            OutputDisplay.writeOutput("Magic: ?");
            magic = scanner.nextLine();
            OutputDisplay.writeOutput("Intelligence: ?");
            knowledge = scanner.nextLine();
            OutputDisplay.writeOutput("Wisdom: ?");
            wisdom = scanner.nextLine();

            //Externalize the sum too maybe
            properPlayer = validatePlayerAttributes(300, magic, knowledge, wisdom);
        }

        m_player = HpPlayer.HpPlayerBuilder.create().name(name.trim()).magic(Integer.parseInt(magic.trim())).wisdom(Integer.parseInt(wisdom.trim())).knowledge(Integer.parseInt(knowledge)).experience(new Experience(PLAYER_INITIAL_EXP_LEVEL, PLAYER_INITIAL_EXP_MAG)).build();

        //Player formed.
    }


       protected boolean validatePlayerAttributes(final int sum, final String magic, final String intelligence, final String wisdom)
       {
           int magicVal = 0;
           int intelligenceVal = 0;
           int wisdomVal = 0;

           try {
               magicVal = Integer.parseInt(magic.trim());
               intelligenceVal = Integer.parseInt(intelligence.trim());
               wisdomVal = Integer.parseInt(wisdom.trim());

           }
           catch (NumberFormatException ne)
           {
                OutputDisplay.writeOutput("Attribute value not in numeric format. Please add again");
               return false;
           }

           if(sum != magicVal + intelligenceVal + wisdomVal )
           {
               OutputDisplay.writeOutput("Attributes not allocated properly. Allocate again!");
               return false;
           }

           else
           {
               return true;
           }

       }

    }

