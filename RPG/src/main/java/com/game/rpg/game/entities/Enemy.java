package com.game.rpg.game.entities;

public interface Enemy extends NonPlayer
{
    int fight(Player player);
}
