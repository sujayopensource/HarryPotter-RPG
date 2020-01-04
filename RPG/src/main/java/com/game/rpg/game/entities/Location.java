package com.game.rpg.game.entities;

import java.io.Serializable;

public class Location implements Serializable
{
    private static final long serialVersionUID = 1112035708791111L;

    private int m_xCoordinate;
    private int m_yCoordinate;

    public Location()
    {
        m_xCoordinate = 0;
        m_yCoordinate = 0;
    }

    public Location(final int x, final int y)
    {
        m_xCoordinate = x;
        m_yCoordinate = y;
    }


    public int getXCoordinate()
    {
        return m_xCoordinate;
    }

    public void setXCoordinate(int xCoordinate)
    {
        m_xCoordinate = xCoordinate;
    }

    public int getYCoordinate()
    {
        return m_yCoordinate;
    }

    public void setYCoordinate(int yCoordinate)
    {
        m_yCoordinate = yCoordinate;
    }

    public void up()
    {
        m_yCoordinate--;
    }

    public void down()
    {
        m_yCoordinate++;
    }

    public void left()
    {
        m_xCoordinate--;
    }

    public void right()
    {
        m_xCoordinate++;
    }

    public String toString()
    {
        return "x = " + m_xCoordinate + ", y = " + m_yCoordinate;
    }
}
