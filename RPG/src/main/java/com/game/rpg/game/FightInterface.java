package com.game.rpg.game;

import com.game.rpg.game.entities.GameCharacter;
/*
    Not used now. Might be required if need to flee before visitng enemy cell or something
 */
public interface FightInterface extends InteractionInterface
{
    void fight(GameCharacter fighter, GameCharacter defender);
    void flee(GameCharacter escapist);
}
