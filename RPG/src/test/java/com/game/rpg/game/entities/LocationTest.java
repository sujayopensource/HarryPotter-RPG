package com.game.rpg.game.entities;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class LocationTest
{
    @Test
    public void locationUp()
    {
        final Location location = new Location(10 ,10);
        location.up();
        assertEquals(9, location.getYCoordinate());
        assertEquals(10, location.getXCoordinate());
    }


    @Test
    public void locationDown()
    {
        final Location location = new Location(10 ,10);
        location.down();
        assertEquals(11, location.getYCoordinate());
        assertEquals(10, location.getXCoordinate());
    }

    @Test
    public void locationLeft()
    {
        final Location location = new Location(10 ,10);
        location.left();
        assertEquals(10, location.getYCoordinate());
        assertEquals(9, location.getXCoordinate());
    }

    @Test
    public void locationRight()
    {
        final Location location = new Location(10 ,10);
        location.right();
        assertEquals(10, location.getYCoordinate());
        assertEquals(11, location.getXCoordinate());
    }

    @Test
    public void locationMoveUpDown()
    {
        final Location location = new Location(10 ,10);
        location.up();
        location.down();
        assertEquals(10, location.getYCoordinate());
        assertEquals(10, location.getXCoordinate());
    }

    @Test
    public void locationMoveDownUp()
    {
        final Location location = new Location(10 ,10);
        location.down();
        location.up();
        assertEquals(10, location.getYCoordinate());
        assertEquals(10, location.getXCoordinate());
    }

    @Test
    public void locationMoveLeftRight()
    {
        final Location location = new Location(10 ,10);
        location.left();
        location.right();
        assertEquals(10, location.getYCoordinate());
        assertEquals(10, location.getXCoordinate());
    }

    @Test
    public void locationMoveRightLeft()
    {
        final Location location = new Location(10 ,10);
        location.right();
        location.left();

        assertEquals(10, location.getYCoordinate());
        assertEquals(10, location.getXCoordinate());
    }

    @Test
    public void locationMoveRightLeftUpDown()
    {
        final Location location = new Location(10 ,10);
        location.right();
        location.left();

        location.up();
        location.down();

        assertEquals(10, location.getYCoordinate());
        assertEquals(10, location.getXCoordinate());
    }

    @Test
    public void locationMoveRandomDifferntLocation()
    {
        final Location location = new Location(10 ,10);
        location.right();
        location.up();
        location.up();

        location.left();

        location.down();

        assertEquals(9, location.getYCoordinate());
        assertEquals(10, location.getXCoordinate());
    }
}
