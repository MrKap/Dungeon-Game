
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
public class BoulderTests {

	private Dungeon testDungeon;
	private Player testPlayer;
	private Boulder testBoulder;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testDungeon = new Dungeon(10, 10);
		testPlayer = new Player(testDungeon, 0, 1);
		testDungeon.setPlayer(testPlayer);
	}


	
	@Test
	public void BoulderPushedToPortal() {
		// Boulder to be pushed
		testBoulder = new Boulder(testDungeon, 1, 1);
		testDungeon.addEntity(testBoulder);
		
		//Setup Portals
		Portal testPortal = new Portal(1,-1,2,1);
		testDungeon.addPortal(testPortal);
		testDungeon.addEntity(testPortal);
		Portal testPortal2 = new Portal(-1,1,3,3);
		testDungeon.addPortal(testPortal2);
		testDungeon.addEntity(testPortal2);
		
		testPlayer.moveRight();
		
		// Player and boulder should not move
		assertEquals(1, testBoulder.getX());
		assertEquals(1, testBoulder.getY());
		assertEquals(0, testPlayer.getX());
		assertEquals(1, testPlayer.getY());
	}
	
	

	@Test
	public void BoulderPushedOffMap() {
		// Boulder to be pushed
		testBoulder = new Boulder(testDungeon, 0, 0);
		testDungeon.addEntity(testBoulder);
		testPlayer.moveUp();
		// Player and boulder should not move
		assertEquals(0, testBoulder.getX());
		assertEquals(0, testBoulder.getY());
		assertEquals(0, testPlayer.getX());
		assertEquals(1, testPlayer.getY());
	}
	
	
	
	@Test
	public void BoulderPushedToAnotherBoulder() {
		// Boulder to be pushed
		testBoulder = new Boulder(testDungeon, 1, 1);
		// Boulder to block the push
		Boulder boulderBlock = new Boulder(testDungeon, 2, 1);
		testDungeon.addEntity(testBoulder);
		testDungeon.addEntity(boulderBlock);
		testPlayer.moveRight();
		// Player and boulder should not move
		assertEquals(1, testBoulder.getX());
		assertEquals(1, testBoulder.getY());
		assertEquals(0, testPlayer.getX());
		assertEquals(1, testPlayer.getY());
	}
	
	@Test
	public void BoulderPushedToWall() {
		// Boulder to be pushed
		testBoulder = new Boulder(testDungeon, 1, 1);
		Wall testWall = new Wall(2, 1);
		testDungeon.addEntity(testBoulder);
		testDungeon.addEntity(testWall);
		testPlayer.moveRight();
		// Player and boulder should not move
		assertEquals(1, testBoulder.getX());
		assertEquals(1, testBoulder.getY());
		assertEquals(0, testPlayer.getX());
		assertEquals(1, testPlayer.getY());
	}
	
	@Test
	public void BoulderPushedToExitSquare() {
		// Boulder to be pushed
		testBoulder = new Boulder(testDungeon, 1, 1);
		ExitSquare testExit = new ExitSquare(testDungeon,2, 1);
		testDungeon.addEntity(testBoulder);
		testDungeon.addEntity(testExit);
		testPlayer.moveRight();
		// Player and boulder should not move
		assertEquals(1, testBoulder.getX());
		assertEquals(1, testBoulder.getY());
		assertEquals(0, testPlayer.getX());
		assertEquals(1, testPlayer.getY());
	}
	
	
	@Test
	public void BoulderPushedToEmptyFloorSquare() {
		testBoulder = new Boulder(testDungeon, 1, 1);
		testDungeon.addEntity(testBoulder);
		testPlayer.moveRight();
		// Player and boulder should move right by one
		assertEquals(2, testBoulder.getX());
		assertEquals(1, testBoulder.getY());
		assertEquals(1, testPlayer.getX());
		assertEquals(1, testPlayer.getY());
	}
	
	
	
	

	
	
}