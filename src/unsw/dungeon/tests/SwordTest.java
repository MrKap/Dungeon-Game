package unsw.dungeon.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;



public class SwordTest {
	Dungeon dungeon;
	Player player;
	Enemy enemy;
	
	@Before
	public void setUp() throws Exception {
		// Create a 4x4 size dungeon
		dungeon = new Dungeon(4, 4);
		
		// Create a player at location (1, 1)
		player = new Player(dungeon, 1, 1);
		dungeon.setPlayer(player);
		
		// Equip player with sword and enemy at (2,1)
		newEnemy(2, 1);
		player.pickupSword(new Sword(1, 1));
	}
	
	private int getPlayerNumSwords() {
		return player.swordCount();
	}
	
	private int getPlayerSwordHitsRemaining() {
		return player.swordHitsRemaining();
	}
	
	/**
	 * Helper function to create an enemy at a given location.
	 * The enemy is linked to the player.
	 * @param x The x position of the enemy
	 * @param y The y position of the enemy
	 */
	public void newEnemy(int x, int y) {
		enemy = new Enemy(dungeon, x, y);
		dungeon.setEnemy(enemy);
		enemy.setPlayer(player);
	}
	


	/**
	 * Check sword is destroyed after 5 hits
	 */
	@Test
	public void SwordGetsDestroyedTest() {
		int initSwordHits = getPlayerSwordHitsRemaining();
		
		// Test sword hits ( player originally at (1, 1) )
		for(int i = 0; i < initSwordHits-1; i++) {
			assertEquals(false, dungeon.isPlayerDead());
			assertEquals(false, dungeon.isEnemyDead());
			assertEquals(true, player.moveRight());		// player at (2, 1)
			assertEquals(false, dungeon.isPlayerDead());
			assertEquals(true, dungeon.isEnemyDead());	// check enemy is dead
			
			// Check sword hits
			assertEquals(1, getPlayerNumSwords());
			
			
			assertEquals(initSwordHits-i-1, player.swordHitsRemaining());
			
			// Reset positions
			assertEquals(true, player.moveLeft());
			newEnemy(2, 1);
		}
		
		// On the last hit --> check if sword disappears
		assertEquals(false, dungeon.isPlayerDead());
		assertEquals(false, dungeon.isEnemyDead());
		assertEquals(true, player.moveRight());		// player at (2, 1)
		assertEquals(false, dungeon.isPlayerDead());
		assertEquals(true, dungeon.isEnemyDead());	// check enemy is dead
		assertEquals(0, getPlayerNumSwords());
	}
	
	
	/**
	 * Checks Sword is used to kill an enemy, if player wielding sword enters enemy square
	 */
	@Test
	public void PlayerAttackEnemy() {
		// Check who is dead and alive
		assertEquals(false, dungeon.isPlayerDead());
		assertEquals(false, dungeon.isEnemyDead());
		
		// Save initial sword hits
		int initSwordHits = getPlayerSwordHitsRemaining();
		assertEquals(false, dungeon.isEnemyDead());
		
		// Move player into enemy
		assertEquals(1, getPlayerNumSwords());
		assertEquals(initSwordHits, getPlayerSwordHitsRemaining());
		assertEquals(true, player.moveRight());
		assertEquals(1, getPlayerNumSwords());
		assertEquals(initSwordHits-1, getPlayerSwordHitsRemaining());
		assertEquals(false, dungeon.isPlayerDead());
		assertEquals(true, dungeon.isEnemyDead());
	}
	
	
	
	
}