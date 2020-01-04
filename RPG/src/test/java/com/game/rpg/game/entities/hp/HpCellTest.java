package com.game.rpg.game.entities.hp;

import com.game.rpg.game.entities.NonPlayer;
import com.game.rpg.game.entities.Player;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HpCellTest
{
    private static  HpCell hpCell;
    @BeforeClass
    public static void setUp()
    {
        hpCell = HpCell.HpCellBuilder.create().width(8).height(2).build();
    }

    @Before
    public void setUpTest()
    {
        hpCell.setOccupyingPlayer(null);
        hpCell.setOccupyingNonPlayer(null);
    }

    @Test
    public void testCellContentOnlyPlayer()
    {
        Player hpPlayer = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        hpCell.setOccupyingPlayer(hpPlayer);
        final String expectedCellContent = "P";
        final String actualCellContent = hpCell.generateContent();
        assertEquals(expectedCellContent, actualCellContent);
    }

    @Test
    public void testCellContentOnlyEnemy()
    {
        NonPlayer hpEnemy = HpEnemy.HpEnemyBuilder.create().magic(100).wisdom(100).knowledge(100).build();
        hpCell.setOccupyingNonPlayer(hpEnemy);
        final String expectedCellContent = "E";
        final String actualCellContent = hpCell.generateContent();
        assertEquals(expectedCellContent, actualCellContent);
    }

    @Test
    public void testCellContentOnlyFriend()
    {
        NonPlayer hpFriend = HpFriend.HpFriendBuilder.create().magic(100).wisdom(100).knowledge(100).build();
        hpCell.setOccupyingNonPlayer(hpFriend);
        final String expectedCellContent = "F";
        final String actualCellContent = hpCell.generateContent();
        assertEquals(expectedCellContent, actualCellContent);
    }

    @Test
    public void testCellContentPlayerAndEnemy()
    {
        Player hpPlayer = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        hpCell.setOccupyingPlayer(hpPlayer);
        NonPlayer hpEnemy = HpEnemy.HpEnemyBuilder.create().magic(100).wisdom(100).knowledge(100).build();
        hpCell.setOccupyingNonPlayer(hpEnemy);

        final String expectedCellContent = "PE";
        final String actualCellContent = hpCell.generateContent();
        assertEquals(expectedCellContent, actualCellContent);
    }

    @Test
    public void testCellContentPlayerAndFriend()
    {
        Player hpPlayer = HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build();
        hpCell.setOccupyingPlayer(hpPlayer);
        NonPlayer hpFriend = HpFriend.HpFriendBuilder.create().magic(100).wisdom(100).knowledge(100).build();
        hpCell.setOccupyingNonPlayer(hpFriend);

        final String expectedCellContent = "PF";
        final String actualCellContent = hpCell.generateContent();
        assertEquals(expectedCellContent, actualCellContent);
    }

    @Test
    public void testCellBorder()
    {
        String expectedBorder = "+---------+";
        String actualBorder = HpCell.getCellBorder();
        assertEquals(expectedBorder, actualBorder);
    }

    @Test
    public void testCellDrawEmpty()
    {
        String expectedContent = "|         |";
        String actualContent = hpCell.draw();
        assertEquals(expectedContent, actualContent);
    }


    @Test
    public void testCellDrawPlayer()
    {
        String expectedContent = "|    P    |";
        hpCell.setOccupyingPlayer(HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build());
        String actualContent = hpCell.draw();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void testCellDrawEnemy()
    {
        String expectedContent = "|    E    |";
        hpCell.setOccupyingNonPlayer(HpEnemy.HpEnemyBuilder.create().magic(100).wisdom(100).knowledge(100).build());
        String actualContent = hpCell.draw();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void testCellDrawFriend()
    {
        String expectedContent = "|    F    |";
        hpCell.setOccupyingNonPlayer(HpFriend.HpFriendBuilder.create().magic(100).wisdom(100).knowledge(100).build());
        String actualContent = hpCell.draw();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void testCellDrawFriendAndPlayer()
    {
        String expectedContent = "|   PF    |";
        hpCell.setOccupyingPlayer(HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build());
        hpCell.setOccupyingNonPlayer(HpFriend.HpFriendBuilder.create().magic(100).wisdom(100).knowledge(100).build());
        String actualContent = hpCell.draw();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void testCellDrawEnemyAndPlayer()
    {
        String expectedContent = "|   PE    |";
        hpCell.setOccupyingPlayer(HpPlayer.HpPlayerBuilder.create().name("sujay").magic(100).wisdom(100).knowledge(100).build());
        hpCell.setOccupyingNonPlayer(HpEnemy.HpEnemyBuilder.create().magic(100).wisdom(100).knowledge(100).build());
        String actualContent = hpCell.draw();
        assertEquals(expectedContent, actualContent);
    }

    //Add more cases for cell format, whne PF and PE exist
}
