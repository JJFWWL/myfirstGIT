package battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit Tests for Ship class and its child classes
 * @author Jinjie Fan, Xiaoyong Liu(Sheryl), discussed with Tian Qiu
 */
class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		//test scenario Cruiser
		ship = new Cruiser();
		assertEquals(3, ship.getLength());
		
		//test scenario Destroyer
		ship = new Destroyer();
		assertEquals(2, ship.getLength());
		
		//test scenario Submarine
		ship = new Submarine();
		assertEquals(1, ship.getLength());
		
		//test scenario EmptySea
		ship = new EmptySea();
		assertEquals(1, ship.getLength());
	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
				
		//test scenario Cruiser
		Ship Cruiser = new Cruiser();
		row = 9;
		column = 0;
		horizontal = false;
		Cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, Cruiser.getBowRow());	
				
		//test scenario Destroyer
		Ship Destroyer = new Destroyer();
		row = 2;
		column = 5;
		horizontal = false;
		Destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, Destroyer.getBowRow());
		
		//test scenario Submarine
		Ship Submarine = new Submarine();
		row = 3;
		column = 4;
		horizontal = false;
		Submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, Submarine.getBowRow());
		
		//test scenario EmptySea
		Ship EmptySea = new EmptySea();
		row = 6;
		column = 1;
		horizontal = false;
		EmptySea.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, EmptySea.getBowRow());
	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		//test scenario Cruiser
		Ship Cruiser = new Cruiser();
		row = 9;
		column = 0;
		horizontal = false;
		Cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, Cruiser.getBowColumn());	
				
		//test scenario Destroyer
		Ship Destroyer = new Destroyer();
		row = 2;
		column = 5;
		horizontal = false;
		Destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, Destroyer.getBowColumn());
		
		//test scenario Submarine
		Ship Submarine = new Submarine();
		row = 3;
		column = 4;
		horizontal = false;
		Submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, Submarine.getBowColumn());
		
		//test scenario EmptySea
		Ship EmptySea = new EmptySea();
		row = 6;
		column = 1;
		horizontal = false;
		EmptySea.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, EmptySea.getBowColumn());		
		
	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		//test scenario Destroyer
		ship = new Destroyer();
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		//try hit array after get hit
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		ship.placeShipAt(row, column, horizontal, ocean);
		ship.shootAt(row, column-1);
		assertTrue(ship.getHit()[1]);
		
		//test scenario Cruiser
		ship = new Cruiser();
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		//try hit array after get hit
		row = 0;
		column = 4;
		horizontal = true;
		ship.placeShipAt(row, column, horizontal, ocean);
		ship.shootAt(row, column);
		assertTrue(ship.getHit()[0]);
		
		//test scenario Submarine
		ship = new Submarine();
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		//try hit array after get hit
		row = 0;
		column = 4;
		horizontal = true;
		ship.placeShipAt(row, column, horizontal, ocean);
		ship.shootAt(row, column);
		assertTrue(ship.getHit()[0]);
		ship.shootAt(row, column-1);
		assertFalse(ship.getHit()[1]);
		
		//test scenario EmptySea
		ship = new EmptySea();
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		//try hit array after get hit
		row = 0;
		column = 4;
		horizontal = true;
		ship.placeShipAt(row, column, horizontal, ocean);
		ship.shootAt(row, column);
		assertTrue(ship.getHit()[0]);
	}

	@Test
	void testIsHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		//test scenario Destroyer
		Ship Destroyer = new Destroyer();
		row = 2;
		column = 4;
		horizontal = true;
		Destroyer.placeShipAt(row, column, horizontal, ocean);
		assertTrue(Destroyer.isHorizontal());
		
		//test scenario Cruiser
		Ship Cruiser = new Cruiser();
		row = 4;
		column = 0;
		horizontal = false;
		Cruiser.placeShipAt(row, column, horizontal, ocean);
		assertFalse(Cruiser.isHorizontal());
		
		//test scenario Submarine
		Ship Submarine = new Submarine();
		row = 3;
		column = 4;
		horizontal = true;
		Submarine.placeShipAt(row, column, horizontal, ocean);
		assertTrue(Submarine.isHorizontal());
		
		//test scenario Cruiser
		Ship EmptySea = new EmptySea();
		row = 6;
		column = 1;
		horizontal = false;
		EmptySea.placeShipAt(row, column, horizontal, ocean);
		assertFalse(EmptySea.isHorizontal());
		
	}

	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		//test scenario Destroyer
		ship = new Destroyer();
		assertEquals("destroyer", ship.getShipType());
		
		//test scenario Cruiser
		ship = new Cruiser();
		assertEquals("cruiser", ship.getShipType());
		
		//test scenario Submarine
		ship = new Submarine();
		assertEquals("submarine", ship.getShipType());
		
		//test scenario EmptySea
		ship = new EmptySea();
		assertEquals("empty", ship.getShipType());
	}

	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		//test scenario Destroyer
		Ship destroyer = new Destroyer();
		destroyer.setBowRow(row);
		assertEquals(row, destroyer.getBowRow());
		
		//test scenario Cruiser
		Ship cruiser = new Cruiser();
		cruiser.setBowRow(row);
		assertEquals(row, cruiser.getBowRow());
		
		//test scenario Submarine
		Ship submarine = new Submarine();
		submarine.setBowRow(row);
		assertEquals(row, submarine.getBowRow());
		
		//test scenario EmptySea
		Ship emptySea = new EmptySea();
		emptySea.setBowRow(row);
		assertEquals(row, emptySea.getBowRow());
	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		//test scenario Destroyer
		Ship destroyer = new Destroyer();
		destroyer.setBowColumn(column);
		assertEquals(column, destroyer.getBowColumn());
		
		//test scenario Cruiser
		Ship cruiser = new Cruiser();
		cruiser.setBowColumn(column);
		assertEquals(column, cruiser.getBowColumn());
		
		//test scenario Submarine
		Ship submarine = new Submarine();
		submarine.setBowRow(row);
		assertEquals(row, submarine.getBowColumn());
		
		//test scenario EmptySea
		Ship emptySea = new EmptySea();
		emptySea.setBowRow(row);
		assertEquals(row, emptySea.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		//test scenario Destroyer
		Ship destroyer = new Destroyer();
		destroyer.setHorizontal(horizontal);
		assertTrue(destroyer.isHorizontal());
		
		//test scenario Cruiser
		Ship cruiser = new Cruiser();
		cruiser.setHorizontal(false);
		assertFalse(cruiser.isHorizontal());
		
		//test scenario Submarine
		Ship submarine = new Submarine();
		submarine.setHorizontal(horizontal);
		assertTrue(submarine.isHorizontal());
		
		//test scenario EmptySea
		Ship emptySea = new EmptySea();
		emptySea.setHorizontal(false);
		assertFalse(emptySea.isHorizontal());

	}

	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		//test scenario Destroyer
		Ship destroyer = new Destroyer();
		row = 0;
		column = 4;
		horizontal = true;
		ok = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		row = 0;
		column = 0;
		horizontal = true;
		ok = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "not OK to place ship here.");
		
		row = 1;
		column = 20;
		horizontal = false;
		ok = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "not OK to place ship here.");
		
		row = -10;
		column = 9;
		horizontal = false;
		ok = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "not OK to place ship here.");
		
		//test scenario Cruiser
		Ship cruiser = new Cruiser();
		row = 0;
		column = 4;
		horizontal = true;
		ok = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");

		row = 0;
		column = 1;
		horizontal = true;
		ok = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "not OK to place ship here.");

		row = 0;
		column = -6;
		horizontal = false;
		ok = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "not OK to place ship here.");

		row = 18;
		column = 7;
		horizontal=false;
		ok = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "not OK to place ship here.");
		
		//test scenario Submarine
		Ship submarine = new Submarine();
		row = 7;
		column = 8;
		horizontal = true;
		ok = submarine.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");

		row = -2;
		column = 0;
		horizontal = true;
		ok = submarine.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "not OK to place ship here.");

		row = 9;
		column = 11;
		horizontal = false;
		ok = submarine.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "not OK to place ship here.");

		row = -4;
		column = -7;
		horizontal=false;
		ok = submarine.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "not OK to place ship here.");
		
		//test scenario EmptySea
		Ship emptySea = new EmptySea();
		row = 3;
		column = 4;
		horizontal = true;
		ok = emptySea.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");

		row = 0;
		column = 0;
		horizontal = true;
		ok = emptySea.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "not OK to place ship here.");

		row = 9;
		column = 11;
		horizontal = false;
		ok = emptySea.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "not OK to place ship here.");

		row = -4;
		column = 7;
		horizontal=false;
		ok = emptySea.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "not OK to place ship here.");
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		//test third ship on the diagonal case - top left corner
		Battleship battleship3 = new Battleship();
		row = 0;
		column = 0;
		horizontal = true;
		boolean ok3 = battleship3.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok3, "Not OK to place ship diagonally top left.");
		
		//test scenario Destroyer
		
		//place first ship
		Destroyer destroyer1 = new Destroyer();
		row = 4;
		column = 4;
		horizontal = true;
		ok1 = destroyer1.okToPlaceShipAt(row, column, horizontal, ocean);
		destroyer1.placeShipAt(row, column, horizontal, ocean);
		//test second ship case1, vertically adjacent below
		Destroyer destroyer2 = new Destroyer();
		row = 5;
		column = 4;
		horizontal = true;
		ok2 = destroyer2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		//test second ship case2, vertically adjacent above
		row = 3;
		column = 4;
		horizontal = true;
		ok2 = destroyer2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent above.");
		//test second ship case3, horizontally adjacent right
		row = 4;
		column = 5;
		horizontal = false;
		ok2 = destroyer2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship horizontally adjacent right.");	
		//test second ship case4, ship body interrupted
		row = 5;
		column = 3;
		horizontal = false;
		ok2 = destroyer2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship horizontally adjacent right.");
		//test second ship case5 - diagonal on the top right corner
		row = 0;
		column = 9;
		horizontal = false;
		ok2 = destroyer2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship diagonally top right.");		

		//test scenario Cruiser
		
		//place first ship
		Cruiser cruiser1 = new Cruiser();
		row = 7;
		column = 4;
		horizontal = true;
		ok1 = cruiser1.okToPlaceShipAt(row, column, horizontal, ocean);
		cruiser1.placeShipAt(row, column, horizontal, ocean);
		//test second ship case1, vertically adjacent below
		Destroyer cruiser2 = new Destroyer();
		row = 8;
		column = 4;
		horizontal = true;
		ok2 = cruiser2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		//test second ship case2, vertically adjacent above
		row = 6;
		column = 4;
		horizontal = true;
		ok2 = cruiser2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		//test second ship case3, horizontally adjacent right
		row = 7;
		column = 5;
		horizontal = false;
		ok2 = cruiser2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship horizontally adjacent right.");
		//test second ship case4, ship body interrupted
		row = 8;
		column = 3;
		horizontal = false;
		ok2 = cruiser2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		//test second ship case5 - diagonal on the left bottom corner
		row = 9;
		column = 0;
		horizontal = true;
		ok2 = cruiser2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship diagonally bottom left.");		
		
		
		//place first ship
		Submarine submarine1 = new Submarine();
		row = 8;
		column = 8;
		horizontal = true;
		ok1 = submarine1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		submarine1.placeShipAt(row, column, horizontal, ocean);
		//test second ship case1, vertically adjacent below
		Submarine submarine2 = new Submarine();
		row = 9;
		column = 8;
		horizontal = true;
		ok2 = submarine2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		//test second ship case2, vertically adjacent above
		row = 7;
		column = 8;
		horizontal = true;
		ok2 = submarine2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent above.");
		//test second ship case3, horizontally adjacent right
		row = 8;
		column = 9;
		horizontal = false;
		ok2 = submarine2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship horizontally adjacent right."); 
		//test second ship case4, ship body interrupted
		row = 7;
		column = 4;
		horizontal = false;
		ok2 = submarine2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship horizontally adjacent right.");
		//test second ship case5 - diagonal on the right, bottom corner
		row = 9;
		column = 9;
		horizontal = false;
		ok2 = submarine2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship diagonally at bottom right.");
	}

	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		
		//test scenario Destroyer
		
		//Destroyer case1
		Ship destroyer = new Destroyer();
		row = 2;
		column = 4;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, destroyer.getBowRow());
		assertEquals(column, destroyer.getBowColumn());
		assertTrue(destroyer.isHorizontal());
		assertEquals("empty", ocean.getShipArray()[2][0].getShipType());
		assertEquals(destroyer, ocean.getShipArray()[2][3]);
		
		//Destroyer case2
		row = 2;
		column = 5;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, destroyer.getBowRow());
		assertEquals(column, destroyer.getBowColumn());
		assertFalse(destroyer.isHorizontal());
		assertEquals(destroyer, ocean.getShipArray()[1][5]);
		
		//test scenario Cruiser
		
		//Cruiser case1
		Ship cruiser = new Cruiser();
		row = 4;
		column = 4;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		assertEquals(column, cruiser.getBowColumn());
		assertTrue(cruiser.isHorizontal());
		assertEquals("empty", ocean.getShipArray()[4][0].getShipType());
		assertEquals(cruiser, ocean.getShipArray()[4][2]);
		
		//Cruiser case2
		row = 5;
		column = 6;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		assertEquals(column, cruiser.getBowColumn());
		assertFalse(cruiser.isHorizontal());
		assertEquals(cruiser, ocean.getShipArray()[4][6]);
		
		//test scenario Submarine
		
		//Submarine case1
		Ship Submarine = new Submarine();
		row = 4;
		column = 4;
		horizontal = true;
		Submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, Submarine.getBowRow());
		assertEquals(column, Submarine.getBowColumn());
		assertTrue(Submarine.isHorizontal());
		assertEquals("empty", ocean.getShipArray()[4][0].getShipType());
		assertEquals(Submarine, ocean.getShipArray()[4][4]);
		
		//Submarine case2
		row = 6;
		column = 7;
		horizontal = false;
		Submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, Submarine.getBowRow());
		assertEquals(column, Submarine.getBowColumn());
		assertFalse(Submarine.isHorizontal());
		assertEquals(Submarine, ocean.getShipArray()[6][7]);
		
	}

	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		//test scenario Destroyer
		Ship destroyer = new Destroyer();
		row = 2;
		column = 9;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		//shot out of ship
		assertFalse(destroyer.shootAt(1, 9));
		boolean[] hitArray1 = {false, false, false, false};
		assertArrayEquals(hitArray0, destroyer.getHit());
		//shot on ship
		assertTrue(destroyer.shootAt(2, 8));
		hitArray1[1]=true;
		assertArrayEquals(hitArray1, destroyer.getHit());
		
		//test scenario Cruiser
		Ship cruiser = new Cruiser();
		row = 4;
		column = 9;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		//shot out of ship
		assertFalse(cruiser.shootAt(1, 9));
		boolean[] hitArray2 = {false, false, false, false};
		assertArrayEquals(hitArray2, cruiser.getHit());
		//shot on ship
		assertTrue(cruiser.shootAt(4, 9));
		hitArray2[0] = true;
		assertArrayEquals(hitArray2, cruiser.getHit());
		
		//test scenario Destroyer
		Ship submarine = new Submarine();
		row = 2;
		column = 9;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		//shot out of ship
		assertFalse(submarine.shootAt(1, 9));
		boolean[] hitArray3 = {false, false, false, false};
		assertArrayEquals(hitArray3, submarine.getHit());
		//shot on ship
		assertTrue(submarine.shootAt(2, 9));
		hitArray3[0]=true;
		assertArrayEquals(hitArray3, submarine.getHit());
		
	}
	
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		//continue the submarine test above
		assertTrue(submarine.shootAt(3, 3));
		assertTrue(submarine.isSunk());
		
		//test scenario Destroyer
		Ship destroyer = new Destroyer();
		row = 0;
		column = 9;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertFalse(destroyer.isSunk());
		assertFalse(destroyer.shootAt(5, 2));
		assertTrue(destroyer.shootAt(0, 9));
		assertFalse(destroyer.isSunk());
		assertTrue(destroyer.shootAt(0, 8));
		assertTrue(destroyer.isSunk());

		//test scenario Cruiser
		Ship cruiser = new Cruiser();
		row = 2;
		column = 9;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertFalse(cruiser.isSunk());
		assertFalse(cruiser.shootAt(5, 2));
		assertTrue(cruiser.shootAt(2, 9));
		assertFalse(cruiser.isSunk());
		assertTrue(cruiser.shootAt(2, 8));
		assertFalse(cruiser.isSunk());
		assertTrue(cruiser.shootAt(2, 7));
		assertTrue(cruiser.isSunk());
		
		//test scenario Battleship
		Ship battleship = new Battleship();
		row = 2;
		column = 9;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertFalse(battleship.isSunk());
		assertFalse(battleship.shootAt(5, 2));
		assertTrue(battleship.shootAt(2, 9));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(2, 8));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(2, 7));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(2, 6));
		assertTrue(battleship.isSunk());	
	}

	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
			
		//test scenario Destroyer
		Ship destroyer = new Destroyer();
		assertEquals("x", destroyer.toString());
		row = 0;
		column = 4;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		destroyer.shootAt(0, 4);
		assertEquals("x", destroyer.toString());
		destroyer.shootAt(0, 3);
		assertEquals("s", destroyer.toString());
		
		//test scenario Cruiser
		Ship cruiser = new Cruiser();
		assertEquals("x", cruiser.toString());
		row = 2;
		column = 4;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		cruiser.shootAt(2, 4);
		assertEquals("x", cruiser.toString());
		cruiser.shootAt(2, 3);
		assertEquals("x", cruiser.toString());
		cruiser.shootAt(2, 2);
		assertEquals("s", cruiser.toString());
		
		//test scenario Submarine
		Ship submarine = new Submarine();
		assertEquals("x", submarine.toString());
		row = 3;
		column = 4;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		submarine.shootAt(1, 4);
		assertEquals("x", submarine.toString());
		submarine.shootAt(3, 4);
		assertEquals("s", submarine.toString());
		
		
		//test scenario EmptySea
		Ship emptySea = new EmptySea();
		assertEquals("-", emptySea.toString());
		row = 3;
		column = 4;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		destroyer.shootAt(2, 8);
		assertEquals("-", emptySea.toString());
		destroyer.shootAt(3, 5);
		assertEquals("-", emptySea.toString());
	}

}
