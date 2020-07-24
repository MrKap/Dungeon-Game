package unsw.dungeon.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;
import unsw.dungeon.Invincibility;
import unsw.dungeon.InventoryPotions;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;

public class EnemyTest {
    Dungeon testDungeon = new Dungeon(6, 6);
    Enemy testEnemy;
    Player testPlayer;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        testDungeon.addEntity(new Wall(5, 2));
        testDungeon.addEntity(new Wall(5, 1));
        testDungeon.addEntity(new Wall(5, 0));
        testDungeon.addEntity(new Wall(4, 4));
        testDungeon.addEntity(new Wall(4, 5));
        testDungeon.addEntity(new Wall(3, 1));
        testDungeon.addEntity(new Wall(3, 3));
        testDungeon.addEntity(new Wall(2, 0));
        testDungeon.addEntity(new Wall(2, 5));
        testDungeon.addEntity(new Wall(1, 0));
        testDungeon.addEntity(new Wall(1, 1));
        testDungeon.addEntity(new Wall(1, 2));
        testDungeon.addEntity(new Wall(1, 3));
        testDungeon.addEntity(new Wall(1, 5));
        testDungeon.addEntity(new Wall(0, 5));
    }

	
	
	
	   @Test
	    public void testStepAwayAdjacent() {
	        // Create player and enemy
	        testEnemy = new Enemy(testDungeon, 3, 2);
	        testDungeon.setEnemy(testEnemy);
	        testPlayer = new Player(testDungeon, 4, 2);
	        testDungeon.setPlayer(testPlayer);
	        
	        // Player invincible
	        Invincibility potion = new Invincibility(4, 2);
	        testPlayer.pickupInvPotion(potion);
	        
	        // Enemy should step away from player
	        testEnemy.nextCycle();
	        assertEquals(2, testEnemy.getX());
	        assertEquals(2, testEnemy.getY());
	    }
	
}
