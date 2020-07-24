
package unsw.dungeon.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.*;

public class GoalTests {
	
	private Dungeon dungeon;
	private KillAllEnemiesGoal enemyGoal;
	private AllBouldersGoal switchGoal;
	private ExitSquareGoal exitGoal;
	private CollectTreasureGoal treasureGoal;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dungeon = new Dungeon(3, 3);
		enemyGoal = new KillAllEnemiesGoal(dungeon);
		switchGoal = new AllBouldersGoal(dungeon);
		exitGoal = new ExitSquareGoal(dungeon);
		treasureGoal = new CollectTreasureGoal(dungeon);
	}


	/**
	 * Tests a switch goal
	 */
	@Test
	public void BoulderGoall() {
		// Should be true if no switch exists yet
		assertEquals(true, switchGoal.isSolved());
				
		// Add some switches
		for (int i = 0; i < 3; i++) {
			dungeon.addSwitch(new FloorSwitch(dungeon, 0, i));
		}
		// No boulders on switches
		assertEquals(false, switchGoal.isSolved());
		
		// Boulder on some switches
		dungeon.addEntity(new Boulder(dungeon, 0, 0));
		dungeon.addEntity(new Boulder(dungeon, 1, 1));
		assertEquals(false, switchGoal.isSolved());
		
		// Boulders on all switches
		dungeon.addEntity(new Boulder(dungeon, 0, 1));
		dungeon.addEntity(new Boulder(dungeon, 0, 2));
		assertEquals(true, switchGoal.isSolved());
	}
	
	
	
	/**
	 * Tests a  enemy goal
	 */
	@Test
	public void KillAllEnemyGoal() {
		// Should be true if no enemy exists yet
		assertEquals(true, enemyGoal.isSolved());
		
		// Check when enemy exists and killed
		Enemy enemy = new Enemy(dungeon, 0, 0);
		dungeon.addEntity(enemy);
		assertEquals(false, enemyGoal.isSolved());
		dungeon.removeEntity(enemy);
		assertEquals(true, enemyGoal.isSolved());
	}
	
	
	
	/**
	 * Test composite OR goal
	 */
	@Test
	public void EitherEnemyORSwitch() {
		// Should be true if nothing exist yet
		SolveEitherGoal orGoal = new SolveEitherGoal(enemyGoal, switchGoal);
		assertEquals(true, orGoal.isSolved());
		
		// Add an enemy
		Enemy enemy = new Enemy(dungeon, 0, 0);
		dungeon.addEntity(enemy);
		
		// Add some switches
		for (int i = 0; i < 3; i++) {
			dungeon.addSwitch(new FloorSwitch(dungeon, 0, i));
		}
		// No boulders on switches
		assertEquals(false, orGoal.isSolved());
		
		// Boulder on some switches
		dungeon.addEntity(new Boulder(dungeon, 0, 0));
		dungeon.addEntity(new Boulder(dungeon, 1, 1));
		assertEquals(false, orGoal.isSolved());
		
		// Boulders on all switches
		dungeon.addEntity(new Boulder(dungeon, 0, 1));
		dungeon.addEntity(new Boulder(dungeon, 0, 2));
		assertEquals(true, orGoal.isSolved());
	}
	
	

	
	/**
	 * Tests treasure goal
	 */
	@Test
	public void CollectAllTreasureGoal() {
		// Should be true if no treasure exists yet
		assertEquals(true, treasureGoal.isSolved());
		
		// Put some treasure out
		for (int i = 0; i < 2; i++) {
			dungeon.addEntity(new Treasure(0, i));
		}
		assertEquals(false, treasureGoal.isSolved());
		
		// Add a player and pick up some treasure
		Player player = new Player(dungeon, 0, 2);
		dungeon.setPlayer(player);
		player.moveUp();
		assertEquals(false, treasureGoal.isSolved());
		
		// player collects all treasure
		player.moveUp();
		assertEquals(true, treasureGoal.isSolved());
	}
	
	/**
	 * Test composite AND goal
	 */
	@Test
	public void BOTHTreasureEnemyGoal() {
		// Should be true if nothing exists yet
		SolveBothGoals andGoal = new SolveBothGoals(treasureGoal, enemyGoal);
		assertEquals(true, andGoal.isSolved());
		
		// Put some treasure out
		for (int i = 0; i < 2; i++) {
			dungeon.addEntity(new Treasure(0, i));
		}
		assertEquals(false, andGoal.isSolved());
		
		// Put an enemy in dungeon
		// Check when enemy exists and killed
		Enemy enemy = new Enemy(dungeon, 1, 1);
		dungeon.addEntity(enemy);
		assertEquals(false, andGoal.isSolved());
		
		// Add a player and pick up some treasure
		Player player = new Player(dungeon, 0, 2);
		dungeon.setPlayer(player);
		player.moveUp();
		assertEquals(false, andGoal.isSolved());
		
		// player collects all treasure
		player.moveUp();
		assertEquals(false, andGoal.isSolved());
		
		// enemy is killed
		player.pickupSword(new Sword(0, 0));
		player.moveDown();
		player.moveRight();
		assertEquals(true, andGoal.isSolved());
	}
	
	/**
	 * Tests exit goal
	 */
	@Test
	public void ExitsquareGoal() {
		// Test exitSquare exists 
		ExitSquare exit = new ExitSquare(0, 1);
		dungeon.addEntity(exit);
		dungeon.setExitSquare(exit);
		Player player = new Player(dungeon, 0, 0);
		// Check when player not on exitSquare
		dungeon.setPlayer(player);
	//	dungeon.addEntity(player);

		assertEquals(false, exitGoal.isSolved());
		// Move player on exitSquare
		player.moveDown();
		assertEquals(true, exitGoal.isSolved());
	}
	
	
	
	

}