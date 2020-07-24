package unsw.dungeon.tests;

import static org.junit.Assert.*;

//import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Key;
//import unsw.dungeon.InventoryKeys;
import unsw.dungeon.Player;

public class DoorKeyTest {
    Dungeon testDungeon = new Dungeon(10, 10);
    Door testDoor = new Door(0,0,0);
    Player testPlayer;

    /**
     * Reinitialise player for every test.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        testPlayer = new Player(testDungeon, 0, 0);
    }

    
    /**
     * Tests if an incorrect key will not unlock the door.
     */
    @Test
    public void DoorWrongKey() {
        assertEquals(false, testDoor.canPassThrough());
        testPlayer.pickupKey(new Key(100, 0, 0));
        assertEquals(false,testDoor.validPlayerCollision(testPlayer));
        assertEquals(false, testDoor.canPassThrough());
    }
    
    
    /**
     * Tests if a correct key will unlock the door, and make it pass throughable.
     */
    @Test
    public void DoorKeyMatches() {
        assertEquals(false, testDoor.canPassThrough());
        testPlayer.pickupKey(new Key(0, 0, 0));
        assertEquals(true, testDoor.validPlayerCollision(testPlayer));
        assertEquals(true, testDoor.canPassThrough());
    }
    
 

}