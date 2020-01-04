package com.game.rpg.game.entities.hp;

import com.game.rpg.game.entities.*;

public class HpCell extends Cell
{
    //Probably can use boolean for occupyingPlayer, but kept for simplicity. can refactor later TODO
    protected Player m_occupyingPlayer = null;
    protected NonPlayer m_occupyingNonPlayer = null;
    protected static String m_cellBorder;
    protected  String m_cellFormat = null;

    public HpCell(HpCellBuilder cellBuilder)
    {
        super(cellBuilder);
        m_occupyingPlayer = cellBuilder.m_occupyingPlayer;
        m_occupyingNonPlayer = cellBuilder.m_occupyingNonPlayer;
        m_cellBorder = generateCellBorder();
        //This only has empty cell Format which does not change
        m_cellFormat  = generateCellFormat();
    }

    protected String generateCellBorder()
    {
        StringBuilder borderBuilder = new StringBuilder();
        borderBuilder.append("+");
        for(int count = 1; count <= m_width + 1; count++)
        {
            borderBuilder.append("-");
        }
        borderBuilder.append("+");
        return borderBuilder.toString();
    }

    public static class HpCellBuilder extends CellBuilder
    {
        private Player m_occupyingPlayer = null;
        private NonPlayer m_occupyingNonPlayer = null;

        private HpCellBuilder()
        {

        }
        public static HpCellBuilder create()
        {
            return new HpCellBuilder();
        }

        public HpCellBuilder location(final Location location)
        {
            super.location(location);
            return this;
        }

        public HpCellBuilder width(final int width)
        {
            super.width(width);
            return this;
        }

        public HpCellBuilder height(final int height)
        {
            super.height(height);
            return this;
        }

        public HpCellBuilder color(final String color)
        {
            super.color(color);
            return this;
        }


        public HpCellBuilder player(final Player player)
        {
            m_occupyingPlayer = player;
            return this;
        }

        public HpCellBuilder nonplayer(final NonPlayer nonplayer)
        {
            m_occupyingNonPlayer = nonplayer;
            return this;
        }



        @Override
        public HpCell build() {
            return new HpCell(this);
        }
    }

    public boolean isOccupiedPlayer()
    {
        return null != m_occupyingPlayer;
    }
    public Player getOccupyingPlayer()
    {
        return m_occupyingPlayer;
    }
    public void setOccupyingPlayer(final Player player)
    {
        m_occupyingPlayer = player;
    }

    public boolean isOccupiedNonPlayer()
    {
        return null != m_occupyingNonPlayer;
    }
    public NonPlayer getOccupyingNonPlayer()
    {
        return m_occupyingNonPlayer;
    }
    public void setOccupyingNonPlayer(final NonPlayer nonPlayer)
    {
        m_occupyingNonPlayer = nonPlayer;
    }

    @Override
    public boolean isOccupied()
    {
        return (null != m_occupyingNonPlayer || null != m_occupyingPlayer);
    }

    //return String represntation of cell
    @Override
    public String draw()
    {
        return String.format(generateCellFormat(), generateContent()) ;
    }

    public static String getCellBorder()
    {
        return m_cellBorder;
    }
    public void setCellBorder(String cellborder)
    {
        m_cellBorder = cellborder;
    }

    private String generateCellFormat(final int leftPadding, final int rightPadding)
    {
        StringBuilder formatBuilder = new StringBuilder();
        formatBuilder.append("|");
        for(int ind = 1; ind <= leftPadding ; ind ++)
        {
            formatBuilder.append(" ");
        }
        formatBuilder.append("%s");
        for(int ind = 1; ind <= rightPadding ; ind ++)
        {
            formatBuilder.append(" ");
        }

        formatBuilder.append("|");

        return formatBuilder.toString();
    }

    private String generateCellFormat()
    {
        //Both player and non player occupy cell - use a slightly different formatting
        if(m_occupyingPlayer != null && m_occupyingNonPlayer != null)
        {
            return generateCellFormat((m_width/2) - 1 ,  m_width/2 );
        }
        else
        {
            //Avoid recomputation in case of static format cells
            if(null != m_cellFormat)
            {
                return m_cellFormat;
            }
            return generateCellFormat(m_width / 2, m_width / 2);
        }
    }


    //returns static format for empty cell.No need to recompute again
    public  String getCellFormat()
    {
        return m_cellFormat;
    }
    public  void setCellFormat(final String format)
    {
        m_cellFormat = format;
    }

    public String generateContent()
    {
        if(m_occupyingPlayer != null && m_occupyingNonPlayer instanceof  HpEnemy)
        {
            return "PE";
        }
        else if (m_occupyingPlayer != null && m_occupyingNonPlayer instanceof  HpFriend)
        {
            return "PF";
        }
        else if(m_occupyingPlayer instanceof HpPlayer)
        {
            return "P";
        }
        else if(m_occupyingNonPlayer instanceof HpEnemy)
        {
            return "E";
        }
        else if(m_occupyingNonPlayer instanceof  HpFriend)
        {
            return "F";
        }

        return " ";
    }
}
