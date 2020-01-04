package com.game.rpg.game.hp;

import com.game.rpg.common.InputScanner;
import com.game.rpg.common.OutputDisplay;
import com.game.rpg.entities.menu.InGameMenuInterface;
import com.game.rpg.game.RPGGame;
import com.game.rpg.game.entities.Location;
import com.game.rpg.game.entities.MobilePlayer;
import com.game.rpg.game.entities.hp.HpPlayer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;


import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;


public class HpExplorationMangerTest
{
    @Before
    public void setUpTest()
    {
        OutputDisplay.forceClearBuffer();
    }


    @Test
    public void testExploreMoveLeftSuccessfully() throws Exception
    {
        InGameMenuInterface mockInMenu = mock(InGameMenuInterface.class);
        PowerMockito.doNothing().when(mockInMenu, "displayStartInstructions");

        InputScanner inpuutScannerMock = mock(InputScanner.class);

        mockInputScannerFirstChoice(inpuutScannerMock, "A");

        final HpGameWorld gameWorld = new HpGameWorld(null);
        final MobilePlayer hpPlayer  = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        hpPlayer.setPosition(new Location(3,3));
        HpFightManager hpFightManager = mock(HpFightManager.class);
        RPGGame rpgGameMock = mock(RPGGame.class);

        HpExplorationManager explorationManager = HpExplorationManager.create(gameWorld, hpPlayer, mockInMenu, rpgGameMock, hpFightManager);
        explorationManager.setScanner(inpuutScannerMock);

        explorationManager.explore();

        //Verfiy new location
        final Location playerNewLocation = hpPlayer.getPosition();
        assertEquals("Xcoordinate", 2, playerNewLocation.getXCoordinate());
        assertEquals("Ycoordinate", 3, playerNewLocation.getYCoordinate());

    }

    @Test
    public void testExploreMoveRightSuccessfully() throws Exception
    {
        InGameMenuInterface mockInMenu = mock(InGameMenuInterface.class);
        PowerMockito.doNothing().when(mockInMenu, "displayStartInstructions");

        InputScanner inpuutScannerMock = mock(InputScanner.class);

        mockInputScannerFirstChoice(inpuutScannerMock, "D");

        final HpGameWorld gameWorld = new HpGameWorld(null);
        final MobilePlayer hpPlayer  = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        hpPlayer.setPosition(new Location(3,3));
        HpFightManager hpFightManager = mock(HpFightManager.class);
        RPGGame rpgGameMock = mock(RPGGame.class);

        HpExplorationManager explorationManager = HpExplorationManager.create(gameWorld, hpPlayer, mockInMenu, rpgGameMock, hpFightManager);
        explorationManager.setScanner(inpuutScannerMock);

        explorationManager.explore();

        //Verfiy new location
        final Location playerNewLocation = hpPlayer.getPosition();
        assertEquals("Xcoordinate", 4, playerNewLocation.getXCoordinate());
        assertEquals("Ycoordinate", 3, playerNewLocation.getYCoordinate());

    }

    @Test
    public void testExploreMoveUpSuccessfully() throws Exception
    {
        InGameMenuInterface mockInMenu = mock(InGameMenuInterface.class);
        PowerMockito.doNothing().when(mockInMenu, "displayStartInstructions");

        InputScanner inpuutScannerMock = mock(InputScanner.class);

        mockInputScannerFirstChoice(inpuutScannerMock, "W");

        final HpGameWorld gameWorld = new HpGameWorld(null);
        final MobilePlayer hpPlayer  = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        hpPlayer.setPosition(new Location(3,3));
        HpFightManager hpFightManager = mock(HpFightManager.class);
        RPGGame rpgGameMock = mock(RPGGame.class);

        HpExplorationManager explorationManager = HpExplorationManager.create(gameWorld, hpPlayer, mockInMenu, rpgGameMock, hpFightManager);
        explorationManager.setScanner(inpuutScannerMock);

        explorationManager.explore();

        //Verfiy new location
        final Location playerNewLocation = hpPlayer.getPosition();
        assertEquals("Xcoordinate", 3, playerNewLocation.getXCoordinate());
        assertEquals("Ycoordinate", 2, playerNewLocation.getYCoordinate());

    }

    @Test
    public void testExploreMoveDownSuccessfully() throws Exception
    {
        InGameMenuInterface mockInMenu = mock(InGameMenuInterface.class);
        PowerMockito.doNothing().when(mockInMenu, "displayStartInstructions");

        InputScanner inpuutScannerMock = mock(InputScanner.class);

        mockInputScannerFirstChoice(inpuutScannerMock, "S");

        final HpGameWorld gameWorld = new HpGameWorld(null);
        final MobilePlayer hpPlayer  = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        hpPlayer.setPosition(new Location(3,3));
        HpFightManager hpFightManager = mock(HpFightManager.class);
        RPGGame rpgGameMock = mock(RPGGame.class);

        HpExplorationManager explorationManager = HpExplorationManager.create(gameWorld, hpPlayer, mockInMenu, rpgGameMock, hpFightManager);
        explorationManager.setScanner(inpuutScannerMock);

        explorationManager.explore();

        //Verfiy new location
        final Location playerNewLocation = hpPlayer.getPosition();
        assertEquals("Xcoordinate", 3, playerNewLocation.getXCoordinate());
        assertEquals("Ycoordinate", 4, playerNewLocation.getYCoordinate());

    }

    @Test
    public void testExploreMoveLeftDenied() throws Exception
    {
        InGameMenuInterface mockInMenu = mock(InGameMenuInterface.class);
        PowerMockito.doNothing().when(mockInMenu, "displayStartInstructions");

        InputScanner inpuutScannerMock = mock(InputScanner.class);

        mockInputScannerFirstChoice(inpuutScannerMock, "A");

        final HpGameWorld gameWorld = new HpGameWorld(null);
        final MobilePlayer hpPlayer  = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        //Its a grid tha befins from (1,1) that starts at top left
        hpPlayer.setPosition(new Location(1,3));
        HpFightManager hpFightManager = mock(HpFightManager.class);
        RPGGame rpgGameMock = mock(RPGGame.class);

        HpExplorationManager explorationManager = HpExplorationManager.create(gameWorld, hpPlayer, mockInMenu, rpgGameMock, hpFightManager);
        explorationManager.setScanner(inpuutScannerMock);

        explorationManager.explore();

        //Verfiy new location
        final Location playerNewLocation = hpPlayer.getPosition();
        assertEquals("Xcoordinate", 1, playerNewLocation.getXCoordinate());
        assertEquals("Ycoordinate", 3, playerNewLocation.getYCoordinate());

    }

    @Test
    public void testExploreMoveRightDenied() throws Exception
    {
        InGameMenuInterface mockInMenu = mock(InGameMenuInterface.class);
        PowerMockito.doNothing().when(mockInMenu, "displayStartInstructions");

        InputScanner inpuutScannerMock = mock(InputScanner.class);

        mockInputScannerFirstChoice(inpuutScannerMock, "D");

        final HpGameWorld gameWorld = new HpGameWorld(null);
        final MobilePlayer hpPlayer  = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        //Its a grid that begins from (1,1) that starts at top left
        hpPlayer.setPosition(new Location(8,4));
        HpFightManager hpFightManager = mock(HpFightManager.class);
        RPGGame rpgGameMock = mock(RPGGame.class);

        HpExplorationManager explorationManager = HpExplorationManager.create(gameWorld, hpPlayer, mockInMenu, rpgGameMock, hpFightManager);
        explorationManager.setScanner(inpuutScannerMock);

        explorationManager.explore();

        //Verfiy new location
        final Location playerNewLocation = hpPlayer.getPosition();
        assertEquals("Xcoordinate", 8, playerNewLocation.getXCoordinate());
        assertEquals("Ycoordinate", 4, playerNewLocation.getYCoordinate());

    }

    @Test
    public void testExploreMoveUpDenied() throws Exception
    {
        InGameMenuInterface mockInMenu = mock(InGameMenuInterface.class);
        PowerMockito.doNothing().when(mockInMenu, "displayStartInstructions");

        InputScanner inpuutScannerMock = mock(InputScanner.class);

        mockInputScannerFirstChoice(inpuutScannerMock, "W");

        final HpGameWorld gameWorld = new HpGameWorld(null);
        final MobilePlayer hpPlayer  = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        //Its a grid tha befins from (1,1) that starts at top left
        hpPlayer.setPosition(new Location(4,1));
        HpFightManager hpFightManager = mock(HpFightManager.class);
        RPGGame rpgGameMock = mock(RPGGame.class);

        HpExplorationManager explorationManager = HpExplorationManager.create(gameWorld, hpPlayer, mockInMenu, rpgGameMock, hpFightManager);
        explorationManager.setScanner(inpuutScannerMock);

        explorationManager.explore();

        //Verfiy new location
        final Location playerNewLocation = hpPlayer.getPosition();
        assertEquals("Xcoordinate", 4, playerNewLocation.getXCoordinate());
        assertEquals("Ycoordinate", 1, playerNewLocation.getYCoordinate());

    }

    @Test
    public void testExploreMoveDownDenied() throws Exception
    {
        InGameMenuInterface mockInMenu = mock(InGameMenuInterface.class);
        PowerMockito.doNothing().when(mockInMenu, "displayStartInstructions");

        InputScanner inpuutScannerMock = mock(InputScanner.class);

        mockInputScannerFirstChoice(inpuutScannerMock, "S");

        final HpGameWorld gameWorld = new HpGameWorld(null);
        final MobilePlayer hpPlayer  = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        //Its a grid tha befins from (1,1) that starts at top left
        hpPlayer.setPosition(new Location(4,8));
        HpFightManager hpFightManager = mock(HpFightManager.class);
        RPGGame rpgGameMock = mock(RPGGame.class);

        HpExplorationManager explorationManager = HpExplorationManager.create(gameWorld, hpPlayer, mockInMenu, rpgGameMock, hpFightManager);
        explorationManager.setScanner(inpuutScannerMock);

        explorationManager.explore();

        //Verfiy new location
        final Location playerNewLocation = hpPlayer.getPosition();
        assertEquals("Xcoordinate", 4, playerNewLocation.getXCoordinate());
        assertEquals("Ycoordinate", 8, playerNewLocation.getYCoordinate());

    }


    @Test
    public void testDisplayPlayerStatus() throws Exception
    {
        InGameMenuInterface mockInMenu = mock(InGameMenuInterface.class);
        PowerMockito.doNothing().when(mockInMenu, "displayStartInstructions");

        InputScanner inpuutScannerMock = mock(InputScanner.class);

        mockInputScannerFirstChoiceGameSatus(inpuutScannerMock, "1");

        final HpGameWorld gameWorld = new HpGameWorld(null);
        final MobilePlayer hpPlayer  = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        //Its a grid tha befins from (1,1) that starts at top left
        hpPlayer.setPosition(new Location(4,8));
        HpFightManager hpFightManager = mock(HpFightManager.class);
        RPGGame rpgGameMock = mock(RPGGame.class);

        HpExplorationManager explorationManager = HpExplorationManager.create(gameWorld, hpPlayer, mockInMenu, rpgGameMock, hpFightManager);
        explorationManager.setScanner(inpuutScannerMock);

        explorationManager.explore();

        //Verfiy location
        final Location playerNewLocation = hpPlayer.getPosition();
        assertEquals("Xcoordinate", 4, playerNewLocation.getXCoordinate());
        assertEquals("Ycoordinate", 8, playerNewLocation.getYCoordinate());

        //Verfiy Console messages
        assertEquals("First Message", "Player status : ", OutputDisplay.getOutputBuffer().get(0));
        assertEquals("Second Message", hpPlayer.toString(), OutputDisplay.getOutputBuffer().get(1));
        assertEquals("Third Message", "Hit enter(once and/or twice) to finish looking at Player Stats", OutputDisplay.getOutputBuffer().get(2));
    }

    @Test
    public void testDisplayEnemyStats() throws Exception
    {
        InGameMenuInterface mockInMenu = mock(InGameMenuInterface.class);
        PowerMockito.doNothing().when(mockInMenu, "displayStartInstructions");

        InputScanner inpuutScannerMock = mock(InputScanner.class);

        mockInputScannerFirstChoiceGameSatus(inpuutScannerMock, "2");

        final HpGameWorld gameWorld = new HpGameWorld(null);
        final MobilePlayer hpPlayer  = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        //Its a grid tha befins from (1,1) that starts at top left
        hpPlayer.setPosition(new Location(4,8));
        HpFightManager hpFightManager = mock(HpFightManager.class);
        RPGGame rpgGameMock = mock(RPGGame.class);

        HpExplorationManager explorationManager = HpExplorationManager.create(gameWorld, hpPlayer, mockInMenu, rpgGameMock, hpFightManager);
        explorationManager.setScanner(inpuutScannerMock);

        explorationManager.explore();

        //Verfiy Console messages
        assertEquals("First Message", "Number of enemies DEAD: 0"  , OutputDisplay.getOutputBuffer().get(0));
        assertEquals("Second Message","Number of enemies remaining: 20" , OutputDisplay.getOutputBuffer().get(1));
        assertEquals("Third Message", "Hit enter(once and/or twice) to finish looking at Enemy Stats", OutputDisplay.getOutputBuffer().get(2));
    }

    @Test
    public void testSaveGame() throws Exception
    {
        InGameMenuInterface mockInMenu = mock(InGameMenuInterface.class);
        PowerMockito.doNothing().when(mockInMenu, "displayStartInstructions");

        InputScanner inpuutScannerMock = mock(InputScanner.class);

        mockInputScannerFirstChoice(inpuutScannerMock, "5");

        final HpGameWorld gameWorld = new HpGameWorld(null);
        final MobilePlayer hpPlayer  = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        hpPlayer.setPosition(new Location(3,3));
        HpFightManager hpFightManager = mock(HpFightManager.class);
        RPGGame rpgGameMock = mock(RPGGame.class);

        HpExplorationManager explorationManager = HpExplorationManager.create(gameWorld, hpPlayer, mockInMenu, rpgGameMock, hpFightManager);
        explorationManager.setScanner(inpuutScannerMock);

        explorationManager.explore();

        //Verfiy new location
        final Location playerNewLocation = hpPlayer.getPosition();
        assertEquals("Xcoordinate", 3, playerNewLocation.getXCoordinate());
        assertEquals("Ycoordinate", 3, playerNewLocation.getYCoordinate());

        //Verfiy Console messages
        assertEquals("First Message", "Saving Game ............", OutputDisplay.getOutputBuffer().get(0));
        assertEquals("Second Message", "Game Saved ............", OutputDisplay.getOutputBuffer().get(1));

        //After Saving you go back. test that here too.
        assertEquals("Second Message", "Going Back .......", OutputDisplay.getOutputBuffer().get(2));
    }

    @Test
    public void testDisplayMap() throws Exception
    {
        InGameMenuInterface mockInMenu = mock(InGameMenuInterface.class);
        PowerMockito.doNothing().when(mockInMenu, "displayStartInstructions");

        InputScanner inpuutScannerMock = mock(InputScanner.class);

        mockInputScannerFirstChoiceGameSatus(inpuutScannerMock, "3");

        final HpGameWorld gameWorld = mock(HpGameWorld.class);
        PowerMockito.doNothing().when(gameWorld, "render");
        final MobilePlayer hpPlayer  = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        //Its a grid tha befins from (1,1) that starts at top left
        hpPlayer.setPosition(new Location(4,8));
        HpFightManager hpFightManager = mock(HpFightManager.class);
        RPGGame rpgGameMock = mock(RPGGame.class);

        HpExplorationManager explorationManager = HpExplorationManager.create(gameWorld, hpPlayer, mockInMenu, rpgGameMock, hpFightManager);
        explorationManager.setScanner(inpuutScannerMock);

        explorationManager.explore();

        //Verfiy location
        final Location playerNewLocation = hpPlayer.getPosition();
        assertEquals("Xcoordinate", 4, playerNewLocation.getXCoordinate());
        assertEquals("Ycoordinate", 8, playerNewLocation.getYCoordinate());

        //Verfiy Console messages
        assertEquals("First Message", "Map is rendered, have a nice look", OutputDisplay.getOutputBuffer().get(0));
        assertEquals("Second Message","Player is located at(ignore 0 index) = "  + hpPlayer.getPosition().toString(), OutputDisplay.getOutputBuffer().get(1));
        assertEquals("Third Message", "Hit enter(once and/or twice) to finish looking at map", OutputDisplay.getOutputBuffer().get(2));
    }

    private void mockInputScannerFirstChoice(InputScanner inpuutScannerMock , String firstChoice)
    {
        when(inpuutScannerMock.getInput()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                count++;
                if (count == 1)
                {
                    return firstChoice;
                }

                return "4";
            }
        });
    }


    private void mockInputScannerFirstChoiceGameSatus(InputScanner inpuutScannerMock , String firstChoice)
    {
        when(inpuutScannerMock.getInput()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                count++;
                if (count == 1)
                {
                    return firstChoice;
                }

                if(count == 2)
                {
                    return "";
                }
                return "4";
            }
        });
    }
}


