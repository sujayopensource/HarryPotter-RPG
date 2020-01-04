package com.game.rpg.game.hp;

import com.game.rpg.common.InputScanner;
import com.game.rpg.common.OutputDisplay;
import com.game.rpg.engine.GameStateManager;
import com.game.rpg.entities.menu.InGameMenuInterface;
import com.game.rpg.game.ExplorationManager;
import com.game.rpg.game.RPGGame;
import com.game.rpg.game.entities.Location;
import com.game.rpg.game.entities.MobilePlayer;
import com.game.rpg.game.entities.NonPlayer;

import java.io.IOException;

public class HpExplorationManager extends ExplorationManager<HpGameWorld>
{
    private static final long serialVersionUID = -8203570879042237L;
    private HpExplorationManager(HpGameWorld gameWorld, MobilePlayer player, InGameMenuInterface inGameMenu, RPGGame game, HpFightManager hpFightManager) {
        super(gameWorld, player, inGameMenu, game, hpFightManager);
    }

    //A factory method - NON GOF pattern
    public static HpExplorationManager create(final HpGameWorld gameWorld, final MobilePlayer player, final InGameMenuInterface inGameMenu, final RPGGame game, HpFightManager hpFightManager) {
        return new HpExplorationManager(gameWorld, player, inGameMenu, game, hpFightManager);
    }

    public void explore() throws IOException
    {
        m_inGameMenu.displayGameProgressInstructions();
        handleGamePlay();
        //If this is done it means exploration is done or halted
    }

    protected void handleGamePlay() throws IOException
    {
        //In case of game Load
        if(null == m_inputScanner)
        {
            m_inputScanner = new InputScanner();
        }

        String choice = m_inputScanner.getInput().trim();
        //TODO use String constants for cases.Enums dont work well with strings
        while (true) {
            //Current player location
            final Location playerLocation = m_player.getPosition();
            switch (choice) {
                case "A":
                {
                    if (canMoveLeft())
                    {
                        updatePreviousCell(playerLocation);
                        m_player.moveLeft();
                        processPostCellMovement(m_player.getPosition());
                        //Probably System.out is not the best way. Need an Wrapper class, similar to that done for System.in.
                        //this will be done later (TODO).
                        OutputDisplay.writeOutput("Player location on moving left : x =  " + playerLocation.getXCoordinate() + ", y = " + playerLocation.getYCoordinate());
                    }
                    else
                    {
                        //Can enternalize message.TODO
                        OutputDisplay.writeOutput("Cannot Move Left! Choose another direction");
                    }
                    break;
                }
                case "S": {
                    if (canMoveDown(m_gameWorld.getGameMap().getVerticalSize()))
                    {
                        updatePreviousCell(playerLocation);
                        m_player.moveDown();
                        processPostCellMovement(m_player.getPosition());
                        OutputDisplay.writeOutput("Player location on moving down : x =  " + playerLocation.getXCoordinate() + ", y = " + playerLocation.getYCoordinate());
                    }
                    else
                    {
                        //Can enternalize message.TODO
                        OutputDisplay.writeOutput("Cannot Move Down! Choose another direction");
                    }
                    break;
                }
                case "W": {
                    if (canMoveUp())
                    {
                        updatePreviousCell(playerLocation);
                        m_player.moveUp();
                        processPostCellMovement(m_player.getPosition());
                        //Debug
                        OutputDisplay.writeOutput("Player location on moving up : x =  " + playerLocation.getXCoordinate() + ", y = " + playerLocation.getYCoordinate());
                    }
                    else
                    {
                        //Can enternalize message.TODO
                        OutputDisplay.writeOutput("Cannot Move UP! Choose another direction");
                    }
                    break;
                }
                case "D": {
                    if (canMoveRight(m_gameWorld.getGameMap().getHorizontalSize()))
                    {
                        updatePreviousCell(playerLocation);
                        m_player.moveRight();
                        processPostCellMovement(m_player.getPosition());
                        //Debug
                        OutputDisplay.writeOutput("Player location on moving right : x =  " + playerLocation.getXCoordinate() + ", y = " + playerLocation.getYCoordinate());
                    }
                    else
                    {
                        //Can enternalize message.TODO
                        OutputDisplay.writeOutput("Cannot Move RIGHT! Choose another direction");
                    }
                    break;
                }

                case "1":
                {
                    //Display player status bar
                    OutputDisplay.writeOutput("Player status : ");
                    OutputDisplay.writeOutput(m_player.toString());
                    waitForUserInput("Hit enter(once and/or twice) to finish looking at Player Stats");
                    break;
                }

                //Enemy stats
                case "2":
                {
                    //Display number of enemies dead and remaining
                    final HpFightManager fightManager = (HpFightManager)m_interactionInterface;
                    OutputDisplay.writeOutput("Number of enemies DEAD: " + fightManager.getEnemiesDead());
                    int enemiesRem =  m_gameWorld.getEnemyCount() - fightManager.getEnemiesDead();
                    OutputDisplay.writeOutput("Number of enemies remaining: " + enemiesRem);
                    waitForUserInput("Hit enter(once and/or twice) to finish looking at Enemy Stats");
                    break;
                }
                case "3":
                {
                    //Debug
                    //Display Map
                    m_gameWorld.render();
                    OutputDisplay.writeOutput("Map is rendered, have a nice look");
                    OutputDisplay.writeOutput("Player is located at(ignore 0 index) = " + m_player.getPosition().toString());
                    waitForUserInput("Hit enter(once and/or twice) to finish looking at map");
                    break;

                }
                case "4":
                {
                    OutputDisplay.writeOutput("Going Back .......");
                    //Go back to previous
                    return;
                }
                case "5":
                {   //Save Game
                    OutputDisplay.writeOutput("Saving Game ............");
                    GameStateManager.saveGame(m_game);
                    OutputDisplay.writeOutput("Game Saved ............");
                    break;
                }
                case "6":
                 {
                    //Can Externalize
                    OutputDisplay.writeOutput("Exiting Game!! Come back next time");
                    System.exit(0);
                    break;
                 }

                default:
                {
                    //Bad choice
                    //Do nothing
                }
            }
            if(gameOver())
            {
                return;
            }

            //Continue menu display and choice intake - ie Continue exploring
            m_inGameMenu.displayGameProgressInstructions();
            choice = m_inputScanner.getInput().trim();
        }

    }

    private boolean canMoveLeft()
    {
        return (m_player.getPosition().getXCoordinate() - 1) >= 1;
    }

    private boolean canMoveUp()
    {
        return (m_player.getPosition().getYCoordinate() - 1) >= 1;
    }

    private boolean canMoveRight(int mapSize)
    {
        return (m_player.getPosition().getXCoordinate() + 1) < mapSize;
    }

    private boolean canMoveDown(int mapSize)
    {
        return (m_player.getPosition().getYCoordinate() + 1) < mapSize;
    }


    private void updatePreviousCell(final Location playerPreviousLocation)
    {
        m_gameWorld.getGameMap().getGrid()[playerPreviousLocation.getYCoordinate()][playerPreviousLocation.getXCoordinate()].setOccupyingPlayer(null);
    }

    private boolean isInteractionRequiredOnNewCell(final Location playerNewLocation)
    {
        //Check if new position has an enemy or a friend
        final NonPlayer nonPlayer = m_gameWorld.getGameMap().getGrid()[playerNewLocation.getYCoordinate()][playerNewLocation.getXCoordinate()].getOccupyingNonPlayer();
        if (null != nonPlayer)
        {
            //Either Enemy or friend is here, intimate FightManager
            return true;
        }
        else
        {
            return false;
        }
    }

    private void processPostCellMovement(final Location playerNewLocation)
    {
        //Nobody here, happily occupy place.Also can safely cast here as this place only has HP characters!
        m_gameWorld.getGameMap().getGrid()[playerNewLocation.getYCoordinate()][playerNewLocation.getXCoordinate()].setOccupyingPlayer( m_player);
        if(isInteractionRequiredOnNewCell(playerNewLocation))
        {
            m_interactionInterface.interact(playerNewLocation);
        }
    }

    private boolean gameOver()
    {
        return ((HpFightManager) m_interactionInterface).isGameOver();
    }

    private void waitForUserInput(String promptText)
    {
        OutputDisplay.writeOutput(promptText);
        m_inputScanner.getInput();
    }
}
