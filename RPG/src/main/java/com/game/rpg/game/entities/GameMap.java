package com.game.rpg.game.entities;

import java.io.Serializable;

public class GameMap<T extends Cell> implements Serializable
{
    private static final long serialVersionUID = 299489040711L;
    private int m_horizontalSize;
    private int m_verticalSize;

    private T[][] m_grid;

    //Y is vertical size. In 2-d arry we traves in row order.hence first index is y coordinae and 2nd is x coordinate.Need to reverse it
    public GameMap(int y, int x)
    {
        m_horizontalSize = x;
        m_verticalSize = y;
    }
    public T[][] getGrid()
    {
        return m_grid;
    }
    public void setGrid(final T[][] grid)
    {
        m_grid = grid;
    }

    public int getHorizontalSize() {
        return m_horizontalSize;
    }

    public void setHorizontalSize(int horizontalSize) {
        m_horizontalSize = horizontalSize;
    }

    public int getVerticalSize() {
        return m_verticalSize;
    }

    public void setVerticalSize(int verticalSize) {
        m_verticalSize = verticalSize;
    }
}
