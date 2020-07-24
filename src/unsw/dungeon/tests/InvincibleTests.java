package unsw.dungeon.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Invincibility;
import unsw.dungeon.Player;

public class InvincibleTests {
	Dungeon dungeon;
	Player player;
	Enemy enemy;
	
	@Before
	public void setUp() throws Exception {
		
		dungeon = new Dungeon(4, 4);
		player = new Player(dungeon, 1, 1);
		dungeon.setPlayer(player);
		newEnemy(2, 2);
		dungeon.addEntity( new Invincibility(2, 1));
	}
	
	/**
	 * Creates an enemy at a given location.
	 * @param x The enemy x coordinate
	 * @param y The enemy y coordinate
	 */
	public void newEnemy(int x, int y) {
		enemy = new Enemy(dungeon, x, y);
		dungeon.setEnemy(enemy);
		enemy.setPlayer(player);
	}

	
	/**
	 * Test enemy runs away from invincible player
	 */
	@Test
	public void EnemyRunAway() {
		// Pick up potion
		assertEquals(false, enemy.runningAway());
		assertEquals(true, player.moveRight());
		assertEquals(true, player.isPlayerInvincible());
		assertEquals(true, enemy.runningAway());
		
	}
	
	
	
	/**
	 * Becomes invincible after picking up
	 */
	@Test
	public void activatingInvincible() {
		// Test player becomes invincible
		assertNotEquals(true, dungeon.getEntities(2, 1).isEmpty());
		assertEquals(true, player.moveRight());
		assertEquals(true, player.isPlayerInvincible());
	}

	
	/**
	 * This tests checks that an invincible player kills an enemy without swords
	 */
	@Test
	public void EnemyAction() {
		// Pick up
		assertEquals(true, player.moveRight());
		assertEquals(true, player.isPlayerInvincible());
		
		// Move into enemy and see who dies
		assertEquals(false, dungeon.isPlayerDead());
		assertEquals(false, dungeon.isEnemyDead());
		assertEquals(true, player.moveDown());
		assertEquals(false, dungeon.isPlayerDead());
		assertEquals(true, dungeon.isEnemyDead());
	}
	

	
	/**
	 * Timer tests - unimplemented
	 */
	@Test
	public void testInvincibleTimerTest() {
	
		
	}
}