package com.game.rpg.game;

import com.game.rpg.game.entities.Location;

/*
    An interface that allows interaction with other charcters.
    Fight is one type of intreaction
 */
public interface InteractionInterface
{
    //Whether player requires interaction at this location
    void interact(final Location playerLocation);
}
