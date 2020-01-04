package com.game.rpg.game.hp;

import com.game.rpg.common.InputScanner;
import com.game.rpg.common.OutputDisplay;
import com.game.rpg.game.FightManager;
import com.game.rpg.game.entities.Location;
import com.game.rpg.game.entities.NonPlayer;
import com.game.rpg.game.entities.Player;
import com.game.rpg.game.entities.hp.*;

import java.util.Scanner;

//Could be renamed as interaction Manger too
public class HpFightManager extends FightManager<HpGameWorld>
{
    private static final long serialVersionUID = -82035113379040117L;
    //Game fight choices
    private static final String FIGHT = "1";
    private static final String DEFEND = "2";
    private static final String IGNORE = "3";
    private static final String STOP ="4";

    //Game friend interactions.
    private static final String TAKE_REWARD = "1";
    private static final String LEAVE = "2";

    private boolean m_gameOver = false;
    private int m_enemiesDead = 0;

    private HpFightManager(HpGameWorld gameWorld, Player player)
    {
        super(gameWorld, player);
    }

    //A factory method - NON GOF pattern
    public static HpFightManager create(final HpGameWorld gameWorld, final Player player)
    {
        return new HpFightManager(gameWorld, player);
    }

    //Game rule, the damage is applied to all attributes of player or enemy;
    protected void fight(HpPlayer fighter, HpEnemy defender)
    {
        int damage = fighter.fight(defender);
        defender.applyDamage(damage);
    }

    protected void defend(HpEnemy fighter, HpPlayer defender)
    {
        int damage = fighter.fight(defender);
        defender.applyDamage(damage);
    }


    //Equivalent to IGNORE. No use. Strength decreases
    protected void ignore(HpPlayer escapist)
    {
        escapist.applyDamage(10);
    }

    @Override
    public void interact(final Location playerlocation)
    {
        if(null == m_inputScanner)
        {
            m_inputScanner = new InputScanner();
        }

        int xCoordinate = playerlocation.getXCoordinate();
        int yCoordinate = playerlocation.getYCoordinate();

        NonPlayer cellOccupyingNonPlayer = m_gameWorld.getGameMap().getGrid()[yCoordinate][xCoordinate].getOccupyingNonPlayer();
        // instanceOf might not be the best way. Other alternative is to have function in HpGamecharacter whether its enemy or not(TODO)
        if(cellOccupyingNonPlayer instanceof HpEnemy)
        {
            //Not the best way but can safely cast as its all HP here
            //TODO - see if can avoid casts , try refining more ny applying 'D' in SOLID principles better.
            final HpPlayer hpPlayer = (HpPlayer)m_player;
            final HpEnemy hpEnemy = (HpEnemy)  cellOccupyingNonPlayer;
            //Display Fight menu(TODO add new menu)
            displayFightMenu();

            String choice = m_inputScanner.getInput().trim();
            while(true)
            {
                switch(choice)
                {
                    case FIGHT:
                    {
                        fight(hpPlayer, hpEnemy);
                        OutputDisplay.writeOutput("Post fight player status: ");
                        OutputDisplay.writeOutput(hpPlayer.toString());
                        OutputDisplay.writeOutput("Post fight Enemy status: ");
                        OutputDisplay.writeOutput(hpEnemy.toString());
                        if(hpPlayer.isDead())
                        {
                            //Player is dead,set a flag that game is over and return
                            m_gameOver = true;
                            return;
                        }

                        if(hpEnemy.isDead())
                        {
                            onEnemyDead(hpPlayer, hpEnemy);
                            return;
                        }

                            break;
                    }
                    case DEFEND:
                    {
                        defend(hpEnemy, hpPlayer);

                        OutputDisplay.writeOutput("Post defence player status: ");
                        OutputDisplay.writeOutput(hpPlayer.toString());
                        OutputDisplay.writeOutput("Post defence Enemy status: ");
                        OutputDisplay.writeOutput(hpEnemy.toString());

                        //Player can die during defence too
                        if(hpPlayer.isDead())
                        {
                            //Player is dead,set a flag that game is over and return
                            m_gameOver = true;
                            return;
                        }

                        if(hpEnemy.isDead())
                        {
                            onEnemyDead(hpPlayer, hpEnemy);
                            return;
                        }
                        break;
                    }

                    case IGNORE:
                    {
                        //if u ignore u keeping losing constant
                        ignore(hpPlayer);

                        OutputDisplay.writeOutput("Post Ignore player status: ");
                        OutputDisplay.writeOutput(hpPlayer.toString());

                        //On Ignoring also player can Die
                        if(hpPlayer.isDead())
                        {
                            //Player is dead,set a flag that game is over and return
                            m_gameOver = true;
                            return;
                        }

                        break;
                    }

                    case STOP:
                    {
                        //you go back
                        OutputDisplay.writeOutput("Stopping Fight! Upto player to move to another location");
                        return;
                    }

                }
                displayFightMenu();
                choice = m_inputScanner.getInput().trim();
            }

        }
        else if(cellOccupyingNonPlayer instanceof HpFriend)
        {
            final HpPlayer hpPlayer = (HpPlayer)m_player;
            final HpFriend hpFriend = (HpFriend)  cellOccupyingNonPlayer;
            //Display friendship menu (TODO add new menu)
            displayFriendMenu();
            String choice = m_inputScanner.getInput().trim();

            //Only one reward per visit;
            switch(choice)
            {
                case TAKE_REWARD:
                {
                    int reward = hpFriend.reward();

                    if(hpFriend.isDead())
                    {
                        //taking too much rewards can kill ur friends!!
                        onFriendDead(hpFriend);
                    }
                    OutputDisplay.writeOutput(" You have been rewarded " + reward + " by your friend! Use it well");
                    //allocate reward to player. Can have allocation strategies. But for now this is sufficient :)
                    allocateReward(reward, hpPlayer);

                    OutputDisplay.writeOutput("Post reward player status: ");
                    OutputDisplay.writeOutput(hpPlayer.toString());
                    OutputDisplay.writeOutput("Post reward friend status: ");
                    OutputDisplay.writeOutput(hpFriend.toString());
                    return;
                }

                case LEAVE:
                {
                    OutputDisplay.writeOutput("You are leaving your friend peacefully!");
                    return;
                }
            }
        }
    }

    private  void updateOccupyingNonPlayerOnDead(final Location location)
    {
        final HpCell[][] grid = m_gameWorld.getGameMap().getGrid();
        grid[location.getYCoordinate()][location.getXCoordinate()].setOccupyingNonPlayer(null);
    }
    private void onFriendDead(final HpFriend hpFriend)
    {
        OutputDisplay.writeOutput("Your friend passed away!! :( ");
        //Update Friend location
        updateOccupyingNonPlayerOnDead(hpFriend.getLocation());
    }

    private void onEnemyDead(final HpPlayer hpPlayer, final HpEnemy hpEnemy)
    {
        m_enemiesDead++;
        OutputDisplay.writeOutput("You have killed any enemy!!");
        if(m_enemiesDead == m_gameWorld.getEnemyCount())
        {
            //All enemies dead,set a flag that game is over and return
            m_gameOver = true;
            OutputDisplay.writeOutput("you have won!!");
            return;
        }
        //Take reward from enemy.
        int reward = hpEnemy.reward();
        hpPlayer.getExperience().add(reward);
        //reset enemy once dead
        hpEnemy.reset();
        //Update Enemy location
        updateOccupyingNonPlayerOnDead(hpEnemy.getLocation());
    }

    private void allocateReward(final int reward, final HpPlayer hpPlayer)
    {
        int avgReward = reward/4 ;
        //for each attribute allocate
       final int knowledge =  hpPlayer.getKnowledge() + avgReward;
       final int wisdom =  hpPlayer.getWisdom() + avgReward;
       final int magic =  hpPlayer.getMagic() + avgReward;
       final int strength =  hpPlayer.getStrength() + avgReward;

       hpPlayer.setKnowledge(knowledge);
       hpPlayer.setWisdom(wisdom);
       hpPlayer.setMagic(magic);
       hpPlayer.setStrength(strength);

    }


    private void displayFightMenu()
    {
        OutputDisplay.writeOutput(" Enemy Here! here are the options:");
        OutputDisplay.writeOutput("1 - Fight");
        OutputDisplay.writeOutput("2 - Defend");
        OutputDisplay.writeOutput("3 - Ignore");
        OutputDisplay.writeOutput("4 - Stop Fight(Go back)");
    }

    private void displayFriendMenu()
    {
        OutputDisplay.writeOutput("Friend here! here are the options:");
        OutputDisplay.writeOutput("1 - Take reward");
        OutputDisplay.writeOutput("2 - LEAVE");
    }
    public boolean isGameOver()
    {
        return m_gameOver;
    }

    public int getEnemiesDead()
    {
        return m_enemiesDead;
    }
}
