package com.game.rpg.main;

import com.game.rpg.engine.GameEngine;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        GameEngine gameEngine = GameEngine.create();
        gameEngine.init();
        gameEngine.start();
    }
}
