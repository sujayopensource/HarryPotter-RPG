# HarryPotter-RPG
HarryPotter RPG in Java

RPG in Java :)

This is an RPG in Java. Its a map based RPG.
Right now there is only 1 RPG game in it and thats Harry Potter.
Star Wars will be supported soon :)

Game Play:
1) The game is quite intuitive to play and there are in game instructions
2) In short the Player is Harry Potter and there are 20 enemies and 6 friends distributed on a Tile map.
3) You can assign an attribute Magnitude  of 300 among 3 attributes of knowledge, magic and wisdom interactively. There are 2 more
   attributes ie Strength(=100) and Experience(not used as of now).
4)If Player kills all enemies then  player wins.IF Player dies then you lose.
5) It mainly supports:
     a) Interactively create a player
     b) Explore the gameworld(map)
     c) Fight with enemies and interact with friends.
     d) Save game and load(resume) it later.

6) Player can Interact with Enemies or Friends. Interaction with enemy can be Fight, Defend, Ignore, Stop Fight.
   Interaction with Friend is take reward or Do nothing :)

7)Enjoy Playing the game :)

NOTE:  1) Graphics support is minimal in this game as I am not great at graphics :). It will be added gradually :)



BUILDING THE GAME from code:

1) Build from command line.

    a) Goto the path haveing the pom.xml
    b) Run:
        mvn clean install

      This will compile the code and generate the RPG-1.0-SNAPSHOT.jar file in the target folder


2) From IDE :)
   Import the project into IntelliJ IDE and give build.




RUNNING THE RPG:

1) Copy the attached RPG-1.0-SNAPSHOT.jar to some directory
2) Run:
    java -cp RPG-1.0-SNAPSHOT.jar com.game.rpg.main.Main
3) Enjoy the game


NOTE: It can be run directly in IntelliJ IDE too  via Run Button.



DESIGN of the RPG:
1) As much as possible(in given timeframe :) ), the RPG has used Design patterns and Data Structures(look into code :) )
2) UTs have been written as much as possible.Some UTs might be missing.
3) Areas that need improvement have been mentioned in CODE comments.
4) SOLID principles and Testability have been taken care of as much as possible though are areas of improvement (some of them already identified).

NOTE:
    1) TO Save and load game state, Java's Serialization and Deserialization has been used.
    This is not the best way to do it, but has been done due to limited time.
    If non-compatible changes are made to classes the game might not load once saved(changes made post save and loaded using new game version).

    2) Some Strings have been externalized and some havent been. They neeed to be done (TODO).
    This was developed on my personal system and its slower than my office system :). It was taking lot of time to switch between amny tabs
    and hence the rest have been in TODO state.
