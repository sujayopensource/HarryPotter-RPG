package com.game.rpg.game.hp;

import com.game.rpg.common.OutputDisplay;
import com.game.rpg.common.Utils;
import com.game.rpg.game.GameWorld;
import com.game.rpg.game.entities.Experience;
import com.game.rpg.game.entities.GameMap;
import com.game.rpg.game.entities.Location;
import com.game.rpg.game.entities.hp.HpCell;
import com.game.rpg.game.entities.hp.HpEnemy;
import com.game.rpg.game.entities.hp.HpFriend;
import com.game.rpg.game.entities.hp.HpPlayer;

import java.util.Properties;

public class HpGameWorld  extends GameWorld<HpCell>
{
    private static final long serialVersionUID = -8203570873333117L;
    //Will be used in future when cell,character etc properties will be fetched from props
    private Properties m_config;
    //can be externalized. TODO
    private static final int ENEMY_COUNT = 20;
    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;
    private static final int ENEMY_FRIEND_WISDOM = 100;
    private static final int ENEMY_FRIEND_KNOWLEDGE = 100;
    private static final int ENEMY_FRIEND_MAGIC = 100;
    private static final int FRIEND_EXP_LEVEL  = 2;
    private static final int FRIEND_EXP_MAG = 60;

    public HpGameWorld( final Properties config)
    {
        m_config = config;
        m_gameMap = new GameMap<>(HEIGHT + 1,WIDTH + 1);
        HpCell[][] grid = generateGrid(HEIGHT + 1, WIDTH + 1 );
        m_gameMap.setGrid(grid);
        m_mapBorder = buildBorder();
    }
    @Override
    public void render()
    {
        final HpCell[][] grid = m_gameMap.getGrid();

        for(int x = 0; x <= WIDTH; x++)
        {
            OutputDisplay.writeOutput(m_mapBorder);
            for(int y = 0; y <= HEIGHT; y++)
            {
                final HpCell cell = grid[x][y];
                if(x == 0)//First row cells used for numbering columns
                {
                    OutputDisplay.writeOutputWithoutNewLine(String.format(cell.getCellFormat(), y));
                }
                else if(y == 0)//First column cells used for numbering rows
                {
                    OutputDisplay.writeOutputWithoutNewLine(String.format(cell.getCellFormat(), x));
                }
                else
                {
                    OutputDisplay.writeOutputWithoutNewLine(cell.draw());

                }
                OutputDisplay.writeOutputWithoutNewLine(" ");

            }

            OutputDisplay.writeOutput("");
        }

        OutputDisplay.writeOutput(m_mapBorder);

    }

    private String buildBorder()
    {
        StringBuilder borderBuilder = new StringBuilder();

        for(int count = 0; count <= WIDTH ; count++)
        {
            borderBuilder.append(HpCell.getCellBorder());
            borderBuilder.append(" ");
        }

        return borderBuilder.toString();

    }

    public void allocatePlayer(final HpPlayer hpPlayer)
    {
        int xSize = m_gameMap.getHorizontalSize();
        int ySize = m_gameMap.getVerticalSize();

        int playerX = Utils.randomNumberInRange(1, xSize - 1 );
        int playerY = Utils.randomNumberInRange(1,  ySize - 1 );

        //Player allocated a cell
        m_gameMap.getGrid()[playerY][playerX].setOccupyingPlayer(hpPlayer);
        //update players location
        hpPlayer.setLocation(new Location(playerX, playerY));
        //Display player location once he gets a position
        OutputDisplay.writeOutput("Player allocated on : x = " + hpPlayer.getLocation().getXCoordinate() + ", y = " + hpPlayer.getLocation().getYCoordinate());

    }

    public void allocateEnemies()
    {
        int enemiesCount = 0;
        //Fetch below from hp.game properties file
        int requiredEnemies = ENEMY_COUNT;
        HpEnemy.HpEnemyBuilder enemyBuilder = HpEnemy.HpEnemyBuilder.create();

        int xSize = m_gameMap.getHorizontalSize();
        int ySize = m_gameMap.getVerticalSize();

        while (enemiesCount != requiredEnemies)
        {
            int playerX = Utils.randomNumberInRange(1, xSize - 1);
            int playerY = Utils.randomNumberInRange(1,  ySize - 1);

            if (!m_gameMap.getGrid()[playerY][playerX].isOccupied())
            {
                //Create an Enemy and make him occupy cell
                //randomized experience level and magnitude for enemy
                HpEnemy enemy = enemyBuilder.knowledge(ENEMY_FRIEND_KNOWLEDGE).wisdom(ENEMY_FRIEND_WISDOM).magic(ENEMY_FRIEND_MAGIC).experience(new Experience(Utils.randomNumberInRange(Experience.START_LEVEL, Experience.START_LEVEL + 5),Utils.randomNumberInRange(Experience.START_LEVEL, Experience.LEVEL_UP_MAGNITUDE - 1))).build();
                enemy.setLocation(new Location(playerX, playerY));
                //Player allocated a grid cell
                m_gameMap.getGrid()[playerY][playerX].setOccupyingNonPlayer(enemy);
                enemiesCount++;

            }
        }
    }

    //This function and allocateEnemies have similarity. Will Refactor later (TODO)
    public void allocateFriends()
    {
        int friendsCount = 0;
        //Fetch below from hp.game properties file
        int requiredFriends = 6;
        HpFriend.HpFriendBuilder friendBuilder = HpFriend.HpFriendBuilder.create();

        int xSize = m_gameMap.getHorizontalSize();
        int ySize = m_gameMap.getVerticalSize();

        while (friendsCount != requiredFriends) {

            int playerX = Utils.randomNumberInRange(1, xSize - 1 );
            int playerY = Utils.randomNumberInRange(1,  ySize - 1);

            if (!m_gameMap.getGrid()[playerY][playerX].isOccupied())
            {
                //Create an Enemy and make him occupy cell
                HpFriend friend = friendBuilder.knowledge(ENEMY_FRIEND_KNOWLEDGE).wisdom(ENEMY_FRIEND_WISDOM).magic(ENEMY_FRIEND_MAGIC).experience(new Experience(FRIEND_EXP_LEVEL, FRIEND_EXP_MAG)).build();
                friend.setLocation(new Location(playerX, playerY));
                //Player allocated a grid cell
                m_gameMap.getGrid()[playerY][playerX].setOccupyingNonPlayer(friend);
                friendsCount++;

            }
        }
    }

    private HpCell[][] generateGrid(int y, int x)
    {
        HpCell[][] grid = new HpCell[y + 1 ][x + 1 ];

        HpCell.HpCellBuilder hpCellBuilder = HpCell.HpCellBuilder.create();
        for(int indy = 0; indy < y; indy++)
        {
            for(int indx = 0; indx < x; indx++)
            {
                grid[indy][indx] = hpCellBuilder.location( new Location(indx, indy)).height(4).width(4).build();
            }
        }

        return grid;
    }

    public int getEnemyCount()
    {
        return ENEMY_COUNT;
    }
}
