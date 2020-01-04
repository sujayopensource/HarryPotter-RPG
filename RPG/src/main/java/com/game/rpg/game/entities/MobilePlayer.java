package com.game.rpg.game.entities;

public interface MobilePlayer extends Player
{
    void setPosition(final Location location);
    Location getPosition();
    void moveUp();
    void moveDown();
    void moveLeft();
    void moveRight();

}
