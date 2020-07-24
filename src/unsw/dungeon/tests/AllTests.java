package unsw.dungeon.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BoulderTests.class, DoorKeyTest.class, MoveTest.class, SwordTest.class, GoalTests.class, InventoryKeyTests.class, InvincibleTests.class, PickingUpTests.class, PortalTest.class})
public class AllTests {

}