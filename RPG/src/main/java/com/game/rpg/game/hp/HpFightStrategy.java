package com.game.rpg.game.hp;

public interface HpFightStrategy
{
    //Have a startegy setup method to reduce number of strategies required!!
    void setUp(int sourceMag, int destMag);
    //Inflict damage and get back result
    int inflict();
}
