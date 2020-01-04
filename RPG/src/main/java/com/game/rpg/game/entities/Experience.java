package com.game.rpg.game.entities;

import java.io.Serializable;

public class Experience implements Comparable<Experience> , Serializable
{
    private int m_magnitude;
    private int m_level = 1;

    //Can be externalized?? Lets see
    //The magnitude above which level increments and magnitude resets to excess
    public static final int START_LEVEL = 1;
    public static final int LEVEL_UP_MAGNITUDE = 100;

    public Experience(int level, int magnitude)
    {
        m_level = level;
        if(magnitude > LEVEL_UP_MAGNITUDE )
        {
            handleMagnitudeOverflow(magnitude);
        }
        else
        {
            m_magnitude = magnitude;
        }


    }

    //Required for comparing Experience in future;
    @Override
    public int compareTo(Experience other)
    {
        if(m_level > other.getLevel())
        {
            return 1;
        }
        else if(m_level < other.getLevel())
        {
            return -1;
        }
        else
        {
            //equal levels, compare magnitude at that level
            if(m_magnitude > other.getMagnitude())
            {
                return 1;
            }
            else if(m_magnitude < other.getMagnitude())
            {
                return -1;
            }

            else
            {
                //Both equal
                return 0;
            }
        }
    }

    public int getMagnitude()
    {
        return m_magnitude;
    }

    public void setMagnitude(int m_magnitude)
    {
        this.m_magnitude = m_magnitude;
    }

    public int getLevel()
    {
        return m_level;
    }

    public void setLevel(int m_level)
    {
        this.m_level = m_level;
    }

    public void add(final int total)
    {
        //Experience can never be negative!!
        if(total < 0)
        {
            return;
        }
        m_magnitude += total;
        if(m_magnitude > LEVEL_UP_MAGNITUDE)
        {
            handleMagnitudeOverflow(m_magnitude);
        }
    }

    private void handleMagnitudeOverflow(final int magnitude)
    {
        m_level = m_level + magnitude/LEVEL_UP_MAGNITUDE ;
        m_magnitude = magnitude % LEVEL_UP_MAGNITUDE;

    }

    public boolean isValid()
    {
        return (m_level > 0 && m_magnitude > 0 );
    }
    public int getTotal()
    {
        return (m_level  * LEVEL_UP_MAGNITUDE) + m_magnitude;
    }

    //Allows the other experience to be added to this
    public void add(final Experience other)
    {
        //Check validity of both experiences
        if(!other.isValid() || !this.isValid())
        {
            return;
        }

        add(other.getTotal());

    }

    @Override
    public String toString()
    {
        return "Level = " + m_level + "," +
                "Magnitude = " + m_magnitude + "\n";
    }
}
