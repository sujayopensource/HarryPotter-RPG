package com.game.rpg.game.hp;

import com.game.rpg.common.InputScanner;
import com.game.rpg.common.OutputDisplay;
import com.game.rpg.game.entities.Location;
import com.game.rpg.game.entities.MobilePlayer;
import com.game.rpg.game.entities.hp.HpEnemy;
import com.game.rpg.game.entities.hp.HpFriend;
import com.game.rpg.game.entities.hp.HpPlayer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static org.junit.Assert.*;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

public class HpFightManagerTest
{

    @Before
    public void setUpTest()
    {
        OutputDisplay.forceClearBuffer();
    }

    @Test
    public void testEnemyFight()
    {
        InputScanner inputScannerMock = mock(InputScanner.class);
        mockInputScannerFirstChoice(inputScannerMock,"1", "4");

        final HpGameWorld gameWorld = new HpGameWorld(null);
        final MobilePlayer hpPlayer  = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        final Location playerLocation = new Location(2,2);
        hpPlayer.setPosition( playerLocation );

        //Set up enemy on this location
        final HpEnemy hpEnemy = HpEnemy.HpEnemyBuilder.create().magic(100).wisdom(100).knowledge(100).build();
        gameWorld.getGameMap().getGrid()[2][2].setOccupyingNonPlayer(hpEnemy);
        HpFightManager hpFightManager =  HpFightManager.create(gameWorld, hpPlayer);

        hpFightManager.setScanner(inputScannerMock);
        hpFightManager.interact(playerLocation);

        //Check console messages post fight. Ignore menu options
        assertEquals("First message", "Post fight player status: ", OutputDisplay.getOutputBuffer().get(5));
        assertEquals("Second message", hpPlayer.toString(), OutputDisplay.getOutputBuffer().get(6));
        assertEquals("Third message", "Post fight Enemy status: ", OutputDisplay.getOutputBuffer().get(7));
        assertEquals("Fourth message", hpEnemy.toString(), OutputDisplay.getOutputBuffer().get(8));

    }


    @Test
    public void testEnemyDefence()
    {
        InputScanner inputScannerMock = mock(InputScanner.class);
        mockInputScannerFirstChoice(inputScannerMock,"2", "4");

        final HpGameWorld gameWorld = new HpGameWorld(null);
        final MobilePlayer hpPlayer  = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        final Location playerLocation = new Location(2,2);
        hpPlayer.setPosition( playerLocation );

        //Set up enemy on this location
        final HpEnemy hpEnemy = HpEnemy.HpEnemyBuilder.create().magic(100).wisdom(100).knowledge(100).build();
        gameWorld.getGameMap().getGrid()[2][2].setOccupyingNonPlayer(hpEnemy);
        HpFightManager hpFightManager =  HpFightManager.create(gameWorld, hpPlayer);

        hpFightManager.setScanner(inputScannerMock);
        hpFightManager.interact(playerLocation);

        //Check console messages post fight. Ignore menu options
        assertEquals("First message", "Post defence player status: ", OutputDisplay.getOutputBuffer().get(5));
        assertEquals("Second message", hpPlayer.toString(), OutputDisplay.getOutputBuffer().get(6));
        assertEquals("Third message", "Post defence Enemy status: ", OutputDisplay.getOutputBuffer().get(7));
        assertEquals("Fourth message", hpEnemy.toString(), OutputDisplay.getOutputBuffer().get(8));

    }

    @Test
    public void testEnemyIgnore()
    {
        InputScanner inputScannerMock = mock(InputScanner.class);
        mockInputScannerFirstChoice(inputScannerMock,"3", "4");

        final HpGameWorld gameWorld = new HpGameWorld(null);
        final MobilePlayer hpPlayer  = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        final Location playerLocation = new Location(2,2);
        hpPlayer.setPosition( playerLocation );

        //Set up enemy on this location
        final HpEnemy hpEnemy = HpEnemy.HpEnemyBuilder.create().magic(100).wisdom(100).knowledge(100).build();
        gameWorld.getGameMap().getGrid()[2][2].setOccupyingNonPlayer(hpEnemy);
        HpFightManager hpFightManager =  HpFightManager.create(gameWorld, hpPlayer);

        hpFightManager.setScanner(inputScannerMock);
        hpFightManager.interact(playerLocation);

        //Check console messages post fight. Ignore menu options
        assertEquals("First message", "Post Ignore player status: ", OutputDisplay.getOutputBuffer().get(5));
        assertEquals("Second message", hpPlayer.toString(), OutputDisplay.getOutputBuffer().get(6));

    }

    @Test
    public void testStopFight()
    {
        InputScanner inputScannerMock = mock(InputScanner.class);
        mockInputScannerFirstChoice(inputScannerMock,"4", "4");

        final HpGameWorld gameWorld = new HpGameWorld(null);
        final MobilePlayer hpPlayer  = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        final Location playerLocation = new Location(2,2);
        hpPlayer.setPosition( playerLocation );

        //Set up enemy on this location
        final HpEnemy hpEnemy = HpEnemy.HpEnemyBuilder.create().magic(100).wisdom(100).knowledge(100).build();
        gameWorld.getGameMap().getGrid()[2][2].setOccupyingNonPlayer(hpEnemy);
        HpFightManager hpFightManager =  HpFightManager.create(gameWorld, hpPlayer);

        hpFightManager.setScanner(inputScannerMock);
        hpFightManager.interact(playerLocation);
        //
        assertEquals("First message", "Stopping Fight! Upto player to move to another location", OutputDisplay.getOutputBuffer().get(5));
    }

    @Test
    public void testFriendReward()
    {
        InputScanner inputScannerMock = mock(InputScanner.class);
        mockInputScannerFirstChoice(inputScannerMock,"1", "2");

        final HpGameWorld gameWorld = new HpGameWorld(null);
        final MobilePlayer hpPlayer  = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        final Location playerLocation = new Location(2,2);
        hpPlayer.setPosition( playerLocation );

        //Set up enemy on this location
        final HpFriend hpFriend = HpFriend.HpFriendBuilder.create().magic(100).wisdom(100).knowledge(100).build();
        gameWorld.getGameMap().getGrid()[2][2].setOccupyingNonPlayer(hpFriend);
        HpFightManager hpFightManager =  HpFightManager.create(gameWorld, hpPlayer);

        hpFightManager.setScanner(inputScannerMock);
        hpFightManager.interact(playerLocation);

        //Check console messages post fight. Ignore menu options
        assertEquals("First Message", " You have been rewarded " + "100" + " by your friend! Use it well", OutputDisplay.getOutputBuffer().get(3));
        assertEquals("Second message", "Post reward player status: ", OutputDisplay.getOutputBuffer().get(4));
        assertEquals("Third message", hpPlayer.toString(), OutputDisplay.getOutputBuffer().get(5));
        assertEquals("Fourth message", "Post reward friend status: ", OutputDisplay.getOutputBuffer().get(6));
        assertEquals("Fifth message", hpFriend.toString(), OutputDisplay.getOutputBuffer().get(7));
    }

    @Test
    public void testFriendIgnore()
    {
        InputScanner inputScannerMock = mock(InputScanner.class);
        mockInputScannerFirstChoice(inputScannerMock,"2", "2");

        final HpGameWorld gameWorld = new HpGameWorld(null);
        final MobilePlayer hpPlayer  = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        final Location playerLocation = new Location(2,2);
        hpPlayer.setPosition( playerLocation );

        //Set up enemy on this location
        final HpFriend hpFriend = HpFriend.HpFriendBuilder.create().magic(100).wisdom(100).knowledge(100).build();
        gameWorld.getGameMap().getGrid()[2][2].setOccupyingNonPlayer(hpFriend);
        HpFightManager hpFightManager =  HpFightManager.create(gameWorld, hpPlayer);

        hpFightManager.setScanner(inputScannerMock);
        hpFightManager.interact(playerLocation);

        //Check console messages post fight. Ignore menu options
        assertEquals("First Message", "You are leaving your friend peacefully!", OutputDisplay.getOutputBuffer().get(3));

    }

    private void mockInputScannerFirstChoice(InputScanner inputScannerMock , String firstChoice, String secondChoice)
    {
        when(inputScannerMock.getInput()).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                count++;
                if (count == 1)
                {
                    return firstChoice;
                }

                return secondChoice;
            }
        });
    }
}
