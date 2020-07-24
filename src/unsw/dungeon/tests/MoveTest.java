package unsw.dungeon.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;

/**
 * Unit tests for 'Move player' user story
 * 
 *
 */
public class MoveTest {
	Dungeon dungeon;
	Player player;
	
	@Before
	public void setUp() throws Exception {
		// Create a 3x3 size dungeon
		dungeon = new Dungeon(3, 3);
		
		// Create a player at location (1, 1)
		player = new Player(dungeon, 1, 1);
		dungeon.setPlayer(player);
	}
	

	
	/**
	 * 
	 * Checks move into empty floor tile.
	 */
	@Test
	public void MoveDown() {
		assertEquals(player.moveDown(), true);
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 2);
	}
	
	/**
	 * 
	 * Checks move into empty floor tile.
	 */
	@Test
	public void testMoveLeftUnoccupied() {
		assertEquals(player.moveLeft(), true);
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 1);
	}
	
	/**
	 * 
	 * Checks move into empty floor tile.
	 */
	@Test
	public void MoveUp() {
		assertEquals(player.moveUp(), true);
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 0);
	}
	
	
	/**
	 * 
	 * Checks move into empty floor tile.
	 */
	@Test
	public void testMoveRightUnoccupied() {
		assertEquals(player.moveRight(), true);
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 1);
	}
	
	/**
	 * 
	 * Checks move into wall tile.
	 */
	@Test
	public void testMoveLeftWall() {
		dungeon.addEntity( new Wall(0, 1) );
		assertEquals(player.moveLeft(), false);
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 1);
	}
	
	
	
	/**
	 * 
	 * Checks move into wall tile.
	 */
	@Test
	public void testMoveRightWall() {
		dungeon.addEntity( new Wall(2, 1) );
		assertEquals(player.moveRight(), false);
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 1);
	}
	
	/**
	 * 
	 * Checks move into wall tile.
	 */
	@Test
	public void testMoveUpWall() {
		dungeon.addEntity( new Wall(1, 0) );
		assertEquals(player.moveUp(), false);
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 1);
	}
	
	/**
	 * 
	 * Checks move into wall tile.
	 */
	@Test
	public void testMoveDownWall() {
		dungeon.addEntity( new Wall(1, 2) );
		assertEquals(player.moveDown(), false);
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 1);
	}
	


}