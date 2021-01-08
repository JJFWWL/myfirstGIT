package battleship;

import java.util.Random;

/**
 * @author Jinjie Fan, discussed with Xiaoyong Liu(Sheryl), Tian Qiu
 *
 */
public class Ocean {

	/**
	 * used to quickly determine which ship is in any given location
	 */
	private Ship[][] ships=new Ship[10][10];
	
	/**
	 * the total number of shots fired by the user
	 */
	private int shotsFired;
	
	/**
	 * the number of times a shop hit a ship. even user shoots the same part more than once,
	 * every hit is counted
	 */
	private int hitCount;
	
	
	/**
	 * the number of ships sunk (10 ships in all)
	 */
	private int shipsSunk;
	
	//constructor

	/**
	 * creates an empty ocean,fills the ships array with EmptySea
	 * initialize game variables
	 */
	public Ocean() {
		this.shotsFired=0;
		this.hitCount=0;
		this.shipsSunk=0;
		//fill the ship array with emptySea
		for (int i=0;i<10;i++) {
			for (int j=0;j<10;j++) {

				this.ships[i][j]=new EmptySea();
				this.ships[i][j].setBowRow(i);
				this.ships[i][j].setBowColumn(j);
			}
		}
	}
	
	/**
	 * function to place all ten ships randomly on the ocean
	 * place larger ships before smaller ones
	 */
	void placeAllShipsRandomly() {
		//define the 10 ship objects in an gameship array

		Ship[] gameships= {new Battleship(),new Cruiser(),new Cruiser(),new Destroyer(),new Destroyer(),new Destroyer(),new Submarine(),new Submarine(),new Submarine(),new Submarine()};
		// start to place the ships, from big to small
		boolean check1=false;
		Random random = new Random();
		int rand_row=0;//0-9 random for row number
		int rand_column=0;//0-9 random for column number
		boolean direction=true; //generate random boolean for vertical or horizontal direction
		for(int i=0;i<10;i++) {
            check1=false;
			while(!check1) {
				direction= random.nextBoolean();
				rand_row=random.nextInt(10);
				rand_column=random.nextInt(10);
				
				check1=gameships[i].okToPlaceShipAt(rand_row, rand_column, direction, this);
			}
			//ok to place and get out of the loop
			gameships[i].placeShipAt(rand_row, rand_column, direction, this);
		}
	}
	
	

	/** function to check whether the input (row, column) is occupied by a ship
	 * @param row
	 * @param column
	 * @return true if the given location contains a ship, false if not
	 */
	public boolean isOccupied(int row, int column) {
		if (this.ships[row][column].getShipType().equals("empty")){
			return false;
		}
		else {
		return true;
		}
	}
	
	/**function to update informations after user shoot at the row,column point
	 * @param row
	 * @param column
	 * @return false if shoot at empty sea or the ship is sunk, return true if hit afloat ship 
	 */
	boolean shootAt(int row, int column) {
		if (this.ships[row][column].getShipType().equals("empty")){
			//condition shoot at the emptysea
			this.shotsFired+=1;
			this.ships[row][column].shootAt(row, column);//update the hit array
			return false;
		}
		else {
			//condition shoot at the ship
			this.shotsFired+=1;
			if(this.ships[row][column].isSunk()) {
				//condition when the ship is sunk
				return false;
			}
			else {
				//condition the ship is not sunk
				this.hitCount+=1; // count every hit on an un-sunk ship
				//update the ship hit information
				boolean beforeHit;//boolean check for sunk condition before hit
				boolean afterHit;//boolean check for sunk condition after hit
				beforeHit=this.ships[row][column].isSunk();
				this.ships[row][column].shootAt(row, column);//update the hit array if ship
				afterHit=this.ships[row][column].isSunk();
				
				if(beforeHit==false && afterHit==true) {
					this.shipsSunk+=1;
				}
				return true;
			}
		}
	}
	//getters and setters
	/**
	 * @return shotsFired
	 */
	int getShotsFired() {
		return this.shotsFired;
	}
	
	/**
	 * @return hitCount
	 */
	int getHitCount() {
		return this.hitCount;
	}
	
	/**
	 * @return shipSunk
	 */
	int getShipsSunk() {
		return this.shipsSunk;
	}
	
	
	/**function to check whether the game is over
	 * @return whether the game is over boolean
	 */
	boolean isGameOver() {
		if (this.shipsSunk==10) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**function to return the ship array in class ocean
	 * @return ship array
	 */
	public Ship[][] getShipArray() {
		
		return this.ships;
	}
	
	/**
	 * function to prints the ocean.
	 */
	void print() {
		System.out.print("  ");
		for (int i=0;i<10;i++) {
			System.out.print(i+" ");
			if(i==9) {
				System.out.print("\n");
			}
		}
		for (int i=0; i<10;i++) {//loop for row print
			for (int j=0;j<11;j++) { //loop for column print
				if(j==0) {
					System.out.print(i+" ");
				}
				else {
					int bowPrint=this.ships[i][j-1].getBowRow();
					int columnPrint=this.ships[i][j-1].getBowColumn();
					
						if(this.ships[i][j-1].getHit()[bowPrint-i+columnPrint-j+1]) {
							System.out.print(this.ships[i][j-1]+" ");//condition when the print point has hit array == true
						}
						else {
							System.out.print(". ");
						}
					
				}
				if (j==10) {
					System.out.print("\n");
				}
			}
		}
		System.out.println("Enter row, column:");
	}
}
