package com.game.rpg.game;

import com.game.rpg.common.InputScanner;
import com.game.rpg.game.entities.MobilePlayer;
import com.game.rpg.game.entities.Player;

import java.io.Serializable;

/*
    A game manager for any Gameworld T that is of type GameWorld.
    Allows game specific gamemangers based on Gameworlds
 */
public abstract class FightManager<T extends GameWorld> implements InteractionInterface, Serializable
{
    private static final long serialVersionUID = -8203570445673117L;
    protected  T m_gameWorld;
    //Only mobile player can fight(From our point of view)
    protected Player m_player;

    protected transient InputScanner m_inputScanner;

    protected FightManager(T gameWorld, Player player)
    {
        m_gameWorld = gameWorld;
        m_player = player;
        m_inputScanner = new InputScanner();
    }

    public void setPlayer(final MobilePlayer player)
    {
        m_player = player;
    }
    public void setScanner(final InputScanner scanner)
    {
        m_inputScanner = scanner;
    }

}
