/**
 * 
 */
package unsw.dungeon.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.ExitSquare;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;
import unsw.dungeon.Portal;

/**
 * @author Emanuel Kap
 *
 */
public class PortalTest {

	private Dungeon testDungeon;
	private Player testPlayer;
	private Boulder testBoulder;
	private Portal testPortal;
	private Portal testPortal2;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testDungeon = new Dungeon(5, 5);
		testPlayer = new Player(testDungeon, 0, 1);
		testDungeon.setPlayer(testPlayer);
		testPortal = new Portal(1,-1,1,1);
		testDungeon.addPortal(testPortal);
		testDungeon.addEntity(testPortal);
		testPortal2 = new Portal(-1,1,3,3);
		testDungeon.addEntity(testPortal2);
		testDungeon.addPortal(testPortal2);
	}

	@Test
	public void testTeleportsToExitPortal() {
		testPlayer.moveRight();
		// Player should move into portal and end up on exit portal square
		assertEquals(3, testPlayer.getX());
		assertEquals(3, testPlayer.getY());
	}
	

	
	@Test
	public void testTeleportsBackToStart() {
		testPlayer.moveRight();
		assertEquals(3, testPlayer.getX());
		assertEquals(3, testPlayer.getY());
		testPlayer.moveRight();
		assertEquals(4, testPlayer.getX());
		assertEquals(3, testPlayer.getY());
		testPlayer.moveLeft();
		assertEquals(1, testPlayer.getX());
		assertEquals(1, testPlayer.getY());
	}
}



