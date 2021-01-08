package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit Tests for Ocean class
 * @author Tian Qiu, discussed with Xiaoyong Liu(Sheryl), Jinjie Fan
 */
class OceanTest {

	Ocean ocean;
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	@Test
	void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	@Test
	void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5));
		
		//TODO
		//More tests
		
		/* further the tests using the above test scenario */
		assertTrue(ocean.isOccupied(0, 0)); //check if the submarine is placed correctly
		assertFalse(ocean.isOccupied(1, 1)); //check if the diagonal is empty sea
		assertTrue(ocean.isOccupied(0, 5)); //check if the other side of the ship is also registered
		assertFalse(ocean.isOccupied(1, 6)); //check if the ship is placed facing East instead West
		assertFalse(ocean.isOccupied(1, 4)); //check if the ship is placed vertically instead of horizontally
		
		/* test scenario 2 */
		Ship ship = new Cruiser();
		row = 8;
		column = 8;
		horizontal = true;
		ship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.isOccupied(8, 8)); //check if the cruiser is placed correctly
		assertFalse(ocean.isOccupied(9, 9)); //check if the diagonal is empty sea
		assertTrue(ocean.isOccupied(8, 6)); //check if the other side of the ship is also registered
		assertFalse(ocean.isOccupied(8, 9)); //check if the ship is placed facing East instead West
		assertFalse(ocean.isOccupied(7, 8)); //check if the ship is placed vertically instead of horizontally
		
		/* test scenario 3 */
		ship = new Battleship();
		row = 3;
		column = 8;
		horizontal = true;
		ship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(ocean.isOccupied(3, 8)); //check if the cruiser is placed correctly
		assertFalse(ocean.isOccupied(2, 9)); //check if the diagonal is empty sea
		assertTrue(ocean.isOccupied(3, 5)); //check if the other side of the ship is also registered
		assertFalse(ocean.isOccupied(3, 9)); //check if the ship is placed facing East instead West
		assertFalse(ocean.isOccupied(2, 8)); //check if the ship is placed vertically instead of horizontally
	}

	@Test
	void testShootAt() {
	
		assertFalse(ocean.shootAt(0, 1));
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		
		//TODO
		//More tests

		/* further the tests using the above test scenario */
		assertTrue(destroyer.isSunk()); //check if the destroyer is sunk after the two shots
		assertFalse(ocean.shootAt(1, 5)); //check if shooting at the sunk ship will return false
		assertFalse(ocean.shootAt(0, 5)); //check if shooting at the sunk ship will return false

		/* test scenario 2 */
		Ship ship = new Submarine();
		row = 0;
		column = 0;
		horizontal = true;
		ship.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(0, 0)); //check if shooting at the submarine will return true
		assertTrue(ship.isSunk()); //check if the destroyer is sunk after the two shots
		assertFalse(ocean.shootAt(0, 0)); //check if shooting at the sunk ship will return false

		/* test scenario 3 */
		ship = new Battleship();
		row = 9;
		column = 9;
		horizontal = false;
		ship.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(9, 9)); //check if shooting at the battleship will return true
		assertFalse(ship.isSunk()); //check if the battleship is floating after the one shots
		assertTrue(ocean.shootAt(9, 9)); //check if shooting at the same position of the battleship will return true
		
		assertTrue(ocean.shootAt(8, 9)); //check if shooting at the battleship will return true
		assertFalse(ship.isSunk()); //check if the battleship is floating after the two shots
		assertTrue(ocean.shootAt(7, 9)); //check if shooting at the battleship will return true
		assertFalse(ship.isSunk()); //check if the battleship is floating after the three shots
		assertTrue(ocean.shootAt(6, 9)); //check if shooting at the battleship will return true
		assertTrue(ship.isSunk()); //check if the battleship is sunk after the four shots
		
		assertFalse(ocean.shootAt(9, 9)); //check if shooting at the sunk ship will return false
		assertFalse(ocean.shootAt(8, 9)); //check if shooting at the sunk ship will return false
		assertFalse(ocean.shootAt(7, 9)); //check if shooting at the sunk ship will return false
		assertFalse(ocean.shootAt(6, 9)); //check if shooting at the sunk ship will return false
	}

	@Test
	void testGetShotsFired() {
		
		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());
		
		//TODO
		//More tests
		
		/* further the tests using the above test scenario */
		assertFalse(ocean.shootAt(1, 5)); 
		assertEquals(7, ocean.getShotsFired()); //check if shooting at the sunk ship will still increment the shots fired count
		
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(8, ocean.getShotsFired()); //check if shooting at the same empty sea will still increment the shots fired count

		assertTrue(ocean.shootAt(0, 0));
		assertFalse(ocean.shootAt(0, 0));
		assertFalse(ocean.shootAt(0, 0));
		assertEquals(11, ocean.getShotsFired()); //check if shooting at the submarine three times will still increment 3x for the shots fired count
		
	}

	@Test
	void testGetHitCount() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		//TODO
		//More tests
		
		/* further the tests using the above test scenario */
		assertTrue(ocean.shootAt(1, 5));
		assertEquals(2, ocean.getHitCount()); //check if shooting at the same position of the destroyer will still increment the hit count
		
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(3, ocean.getHitCount()); //check if shooting at the other side of the destroyer will also increment the hit count

		assertFalse(ocean.shootAt(1, 5));
		assertEquals(3, ocean.getHitCount()); //check if shooting at the sunk destroyer will not increment the hit count

		assertFalse(ocean.shootAt(0, 0));
		assertEquals(3, ocean.getHitCount()); //check if shooting at the empty sea will not increment the hit count

		/* test scenario 2 */
		Ship ship = new Submarine();
		row = 9;
		column = 9;
		horizontal = false;
		ship.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(9, 9));
		assertTrue(ship.isSunk());
		assertEquals(4, ocean.getHitCount()); //check if shooting at another submarine will also increment the hit count
		
	}
	
	@Test
	void testGetShipsSunk() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		//TODO
		//More tests
		
		/* further the tests using the above test scenario */
		assertTrue(ocean.shootAt(1, 5));
		assertEquals(2, ocean.getHitCount()); //check if shooting at the same position of the destroyer will still increment the hit count
		assertEquals(0, ocean.getShipsSunk()); //check if shooting at the same position of the destroyer will still not increment the sunk count
		
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(3, ocean.getHitCount()); //check if shooting at the other side of the destroyer will also increment the hit count
		assertEquals(1, ocean.getShipsSunk()); //check if shooting at the other side of the destroyer will increment the sunk count

		assertFalse(ocean.shootAt(1, 5));
		assertEquals(3, ocean.getHitCount()); //check if shooting at the sunk destroyer will not increment the hit count
		assertEquals(1, ocean.getShipsSunk()); //check if shooting at the sunk destroyer will not increment the sunk count

		assertFalse(ocean.shootAt(0, 0));
		assertEquals(3, ocean.getHitCount()); //check if shooting at the empty sea will not increment the hit count
		assertEquals(1, ocean.getShipsSunk()); //check if shooting at the empty sea will not increment the sunk count

		/* test scenario 2 */
		Ship ship = new Submarine();
		row = 9;
		column = 9;
		horizontal = false;
		ship.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(9, 9));
		assertTrue(ship.isSunk());
		assertEquals(4, ocean.getHitCount()); //check if shooting at another submarine will also increment the hit count
		assertEquals(2, ocean.getShipsSunk()); //check if shooting at another submarine will increment the sunk count
		
	}

	@Test
	void testGetShipArray() {
		
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());
		
		//TODO
		//More tests

		/* further the tests using the above test scenario */
		for (int i = 0; i < OCEAN_SIZE; i++) {
			for (int j = 0; j < OCEAN_SIZE; j++) {
				assertEquals("empty", shipArray[i][j].getShipType()); //check if the whole ocean is initialized with empty sea
			}
		}
		
		/* test scenario 2 */
		Ship ship = new Submarine();
		int row = 9;
		int column = 9;
		boolean horizontal = false;
		ship.placeShipAt(row, column, horizontal, ocean); //implicitly check if getShipType() will work outside of the Ocean class when called in Ship class
		assertEquals("submarine", shipArray[9][9].getShipType()); //check if the location 9, 9 is placed with submarine now

		/* test scenario 3 */
		ship = new Cruiser();
		row = 0;
		column = 9;
		horizontal = true;
		ship.placeShipAt(row, column, horizontal, ocean); //implicitly check if getShipType() will work outside of the Ocean class when called in Ship class
		assertEquals("cruiser", shipArray[0][9].getShipType()); //check if the location 0, 9 is placed with cruiser now
		assertEquals("cruiser", shipArray[0][8].getShipType()); //check if the location 0, 8 is placed with cruiser now
		assertEquals("cruiser", shipArray[0][7].getShipType()); //check if the location 0, 7 is placed with cruiser now
		
	}

}
