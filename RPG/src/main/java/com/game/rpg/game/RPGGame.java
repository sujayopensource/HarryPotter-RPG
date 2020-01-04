package com.game.rpg.game;

import com.game.rpg.common.InputScanner;
import com.game.rpg.engine.GameEngine;
import com.game.rpg.entities.menu.InGameMenuInterface;

import java.io.IOException;
import java.io.Serializable;

public abstract class RPGGame implements Serializable
{
    private static final long serialVersionUID = -299482035708790407L;
   protected InGameMenuInterface m_inGameMenu;
   protected ExplorationInterface m_explorationManager;
   protected InteractionInterface m_interactionInterface;
   protected boolean m_breakGame = false;
   protected transient InputScanner m_inputScanner;
   public abstract void  init(final GameEngine gameEngine) throws IOException;

   //Required for loading game from a particular savePoint
   public abstract void continueGame() throws IOException;
   public void start() throws IOException
   {
       runGameLoop();
   }


   //Tempate Method pattern for the Game loop
   protected void runGameLoop() throws IOException
   {
       while(!m_breakGame)
       {
           process();
           update();
       }
   }

    protected abstract void update();

    protected abstract void process() throws IOException;

    public void setScanner(final InputScanner scanner)
    {
        m_inputScanner = scanner;
    }
}
