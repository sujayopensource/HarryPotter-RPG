package com.game.rpg.game;

import com.game.rpg.common.InputScanner;
import com.game.rpg.entities.menu.InGameMenuInterface;
import com.game.rpg.game.entities.MobilePlayer;

import java.io.Serializable;

/*
Exploration Manger for GameWorld T
 */
public abstract class ExplorationManager<T extends GameWorld> implements ExplorationInterface,Serializable
{
    private static final long serialVersionUID = -8203570876543117L;
    protected  T m_gameWorld;
    //Only a mobile player can explore
    protected MobilePlayer m_player;
    protected InGameMenuInterface m_inGameMenu;
    // For which game is it exploring? Required for mainly saving the Game while exploring. Thats when game can be saved
    protected RPGGame m_game;
    protected transient InputScanner m_inputScanner;
    protected InteractionInterface m_interactionInterface;


    protected ExplorationManager(final T gameWorld, final MobilePlayer player, final InGameMenuInterface inGameMenu, final RPGGame game, InteractionInterface interactionInterface)
    {
        m_gameWorld = gameWorld;
        m_player = player;
        m_inGameMenu = inGameMenu;
        m_game = game;
        m_interactionInterface = interactionInterface;
        m_inputScanner = new InputScanner();
    }


    public void setGameWorld(final T gameWorld)
    {
        m_gameWorld = gameWorld;
    }

    public void setGame(final RPGGame game)
    {
        m_game = game;
    }

    public void setPlayer(final MobilePlayer player)
    {
        m_player = player;
    }

    public void setInGameMenu(final InteractionInterface inGameMenu)
    {
        m_interactionInterface = inGameMenu;
    }

    public void setInteractionInterface(final InteractionInterface interactionInterface)
    {
        m_interactionInterface = interactionInterface;
    }

    public void setScanner(final InputScanner scanner)
    {
        m_inputScanner = scanner;
    }

}
