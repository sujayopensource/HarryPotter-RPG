package com.game.rpg.game;

import com.game.rpg.game.entities.Cell;
import com.game.rpg.game.entities.GameMap;

import java.io.Serializable;

public abstract class GameWorld<T extends Cell> implements Serializable
{
    private static final long serialVersionUID = -44820357056790407L;
    protected GameMap<T> m_gameMap;
    //Will be used in future to border the map
    protected String m_mapBorder;

    //Can add more features to game world in the future

    public abstract void render();

    public GameMap<T> getGameMap()
    {
        return m_gameMap;
    }

}
