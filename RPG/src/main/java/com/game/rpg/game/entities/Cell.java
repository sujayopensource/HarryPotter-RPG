package com.game.rpg.game.entities;

import java.io.Serializable;

public abstract class Cell implements Serializable
{
    private static final long serialVersionUID = 994820357087904L;

    protected Location m_location;
    protected int m_width;
    protected int m_height;
    protected String m_color;
    protected String m_content;


    public Cell(CellBuilder cellBuilder)
    {
        super();
        this.m_location = cellBuilder.m_location;
        this.m_width = cellBuilder.m_width;
        this.m_height = cellBuilder.m_height;
        this.m_color = cellBuilder.m_color;

    }

    public abstract boolean isOccupied();

    //A builder here is used  beacuse once a cell is built its MOSTLY immutable, and also the number of cell params might increase.
    //Note: Setters are provided, so its not really immutable.
    public static abstract class CellBuilder
    {
        private Location m_location;
        private int m_width;
        private int m_height;
        private String m_color;

        protected CellBuilder()
        {

        }

        public CellBuilder location(final Location location)
        {
            m_location = location;
            return this;
        }

        public CellBuilder width(final int width)
        {
            m_width = width;
            return this;
        }

        public CellBuilder height(final int height)
        {
            m_height = height;
            return this;
        }

        public CellBuilder color(final String color)
        {
            m_color = color;
            return this;
        }



        public abstract Cell build();


    }

    public Location getLocation()
    {
        return m_location;
    }
    public void setLocation(final Location location)
    {
        m_location = location;
    }

    public String getColor()
    {
        return m_color;
    }
    public void setColor(final String color)
    {
        m_color = color;
    }

    public int getWidth()
    {
        return m_width;
    }
    public void setWidth(final int width)
    {
        m_width = width;
    }

    public int getHeight()
    {
        return m_height;
    }
    public void setHeight(final int height)
    {
        m_height = height;
    }

    public void setContent(final String content)
    {
        m_content = content;
    }
    public String getContent()
    {
        return m_content;
    }
    public abstract String draw();
}
