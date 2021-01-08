package battleship;

/**
 * @author Jinjie Fan,discussed with Xiaoyong Liu(Sheryl), Tian Qiu
 *
 */
public abstract class Ship {
	/*
	 * the row number that contain the bow coord.
	 */
	private int bowRow;
	/*
	 * the column number that contain the bow coord.
	 */
	private int bowColumn;
	/*
	 * the number of ship length
	 */
	private int length;
	/*
	 * boolean to indicate whether the ship is horizontal
	 */
	private boolean horizontal;
	/*
	 * array of 4 booleans that indicate whether the part of ship has been hit
	 */
	private boolean[] hit= new boolean[4];
	
	/**Ship constructor
	 * @param length: length for ship
	 */
	public Ship(int length) {
		this.length = length;
		
		//initialize hit array
		hit[0]=false;
		hit[1]=false;
		hit[2]=false;
		hit[3]=false;
	}
    
     // getters & setters
     
	/**
	 * @return bowRow
	 */
	public int getBowRow() {
		return bowRow;
	}
    /**set bowrow
     * @param bowRow: bow row coordinate
     */
	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}
	
	/**
	 * @return bowColumn
	 */

	public int getBowColumn() {
		return bowColumn;
	}
	
	/**set bowColumn
     * @param bowColumn: bow column coordinate
     */
	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}
	/**
	 * @return horizontal
	 */
	public boolean isHorizontal() {
		return horizontal;
	}
	/**set horizontal
     * @param horizontal, boolean value, if true ship will be horizontal
     */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	/**
	 * @return length
	 */
	public int getLength() {
		return length;
	}
	/**
	 * @return hit[]
	 */
	public boolean[] getHit() {
		return hit;
	}
	
	//abstract method
	/**
	 * force all ship to return their own shiptype
	 * @return shiptype string
	 */
	public abstract String getShipType();
	
	//other method, non-abstract
	
	/**based on the given information, determine whether it is ok to place the ship
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return boolean value true if ok to place. otherwise return false.
	 */
	boolean okToPlaceShipAt (int row, int column, boolean horizontal, Ocean ocean) {
		boolean check=false;
		//special case, in case input row column out of 0-9 range
		if(row>9||row<0||column>9||column<0) {
			return false;
		}
		
		if (horizontal==false) {
			//condition when the ship is placed vertically
			if (row+1-this.length<0) {
				//ship length exceed the ocean boundary
				check=false;
			}
			else {
				//ship in ocean boundary, check occupy
				for(int i=0;i<this.length;i++) {
					if(ocean.isOccupied(row-i,column)) {
						check=false;
	
					}
					else {
						//condition the location is not occupied, but adjacent to a ship
						try {
							if(ocean.isOccupied(row-i+1,column)) {
								check=false;
								break;
							}
						}
						catch (ArrayIndexOutOfBoundsException e) {}
						try {	
							if(ocean.isOccupied(row-i-1,column)) {
								check=false;
								break;
							}
						}
						catch (ArrayIndexOutOfBoundsException e) {}
						try {
							if(ocean.isOccupied(row-i,column+1)) {
								check=false;
								break;
							}
						}
						catch (ArrayIndexOutOfBoundsException e) {}
						try {
							if(ocean.isOccupied(row-i,column-1)) {
								check=false;
								break;
							}
						}
						catch (ArrayIndexOutOfBoundsException e) {}
						try {
							if(ocean.isOccupied(row-i+1,column+1)) {
								check=false;
								break;
							}
						}
						catch (ArrayIndexOutOfBoundsException e) {}
						try {
							if(ocean.isOccupied(row-i-1,column+1)) {
								check=false;
								break;
							}
						}
						catch (ArrayIndexOutOfBoundsException e) {}
						try {
							if(ocean.isOccupied(row-i+1,column-1)) {
								check=false;
								break;
							}
						}
						catch (ArrayIndexOutOfBoundsException e) {}
						try {
							if(ocean.isOccupied(row-i-1,column-1)) {
								check=false;
								break;
							}
						}
						catch (ArrayIndexOutOfBoundsException e) {}
							
							check=true;
						
						}
	
				  }
			}
			
		}
		
		else {
		//condition when the ship is placed horizontally
			if(column+1-this.length<0) {
				//ship length exceed the ocean boundary
				check=false;
			}
			
			else {
				//ship in ocean boundary, check occupy
				for(int i=0;i<this.length;i++) {
					if(ocean.isOccupied(row,column-i)) {
						check=false;
						break;
					}
					else {
						//condition the location is not occupied, but adjacent to a ship
						try {
							if(ocean.isOccupied(row+1,column-i)) {
								check=false;
								break;
							}
						}
						catch (ArrayIndexOutOfBoundsException e) {}
						try {	
							if(ocean.isOccupied(row-1,column-i)) {
								check=false;
								break;
							}
						}
						catch (ArrayIndexOutOfBoundsException e) {}
						try {
							if(ocean.isOccupied(row,column+1-i)) {
								check=false;
								break;
							}
						}
						catch (ArrayIndexOutOfBoundsException e) {}
						try {
							if(ocean.isOccupied(row,column-1-i)) {
								check=false;
								break;
							}
						}
						catch (ArrayIndexOutOfBoundsException e) {}
						try {
							if(ocean.isOccupied(row+1,column+1-i)) {
								check=false;
								break;
							}
						}
						catch (ArrayIndexOutOfBoundsException e) {}
						try {
							if(ocean.isOccupied(row-1,column+1-i)) {
								check=false;
								break;
							}
						}
						catch (ArrayIndexOutOfBoundsException e) {}
						try {
							if(ocean.isOccupied(row+1,column-1-i)) {
								check=false;
								break;
							}
						}
						catch (ArrayIndexOutOfBoundsException e) {}
						try {
							if(ocean.isOccupied(row-1,column-1-i)) {
								check=false;
								break;
							}
						}
						catch (ArrayIndexOutOfBoundsException e) {}
							
							check=true;
						
					}
					
				}
			}
		}
		
		return check;
	}
	
	
	/**function to set the ocean shiparray with the given information
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		//assign bow coordinate and direction boolean
		this.setBowRow(row);
		this.setBowColumn(column);
		this.setHorizontal(horizontal);
		//updated the ocean.ships array
		//condition when the ship is placed vertically
		if(horizontal==false) {
			for (int i=0;i<this.length;i++) {
				ocean.getShipArray()[row-i][column]=this;
			}
		}
		else {
			//condition when the ship is placed horizontally
			for (int i=0;i<this.length;i++) {
				ocean.getShipArray()[row][column-i]=this;
			}
		}
	}
	
	
	/**function to return true if the ship get hit, and update the hit array for the ship
	 * @param row
	 * @param column
	 * @return true if the ship is not sunk, otherwise false
	 */
	boolean shootAt(int row, int column) {
		//condition the input argument is not a point on this ship
		if(this.isHorizontal()) {
			if((row!=this.bowRow) ||((row==this.bowRow) && (this.bowColumn-column)>(this.length-1))) {
				return false;
			}
		}
		else {
			if((column!=this.bowColumn) ||((column==this.bowColumn) && (this.bowRow-row)>(this.length-1))) {
				return false;
			}
		}
		
		
		//condition the input argument is point on this ship
		if (this.isSunk()) {
			return false;
		}
		else {
			this.hit[this.bowRow-row+this.bowColumn-column]=true;
			return true;
			
		}
	}
	
	/**function to check whether the ship is sunk
	 * @return true if every part of the ship has been hit
	 */
	boolean isSunk() {
		boolean check=true;
		for(int i=0;i<this.length;i++) {
			if(this.hit[i]==false) {
				check=false;
				break;
			}
			else {
				check=true;
			}
		}
		
		return (check);
	}

	/**override function to return "s" or "x" once a ship object is called in print
	 *
	 */
	@Override
	public String toString() {
		if (this.isSunk()) {
			return "s";
		}
		else {
			
			return "x";
		}
	}

	
}
