package battleship;

/**
 * @author Jinjie Fan, discussed with Xiaoyong Liu(Sheryl), Tian Qiu
 * represents Empty and extends Ship
 */
public class EmptySea extends Ship {
	
	static final int length=1;
	static final String name="empty";

	/**
	 * sub constructor to set the length 
	 */
	public EmptySea() {
		super(EmptySea.length);
		
	}
	
	/**function to override the shootAt method, update the hit array and always return false.
	 * @param row
	 * @param column
	 * @return true if the ship is not sunk, otherwise false
	 */
	@Override
	boolean shootAt(int row, int column) {
		
			this.getHit()[0]=true;
			return false;
			
	}

	/**
	 * override method to return name to the getshiptype
	 */
	@Override
	public String getShipType() {
		
		return (EmptySea.name);
	}

	/**
	 *override method to always return false
	 */
	@Override
	boolean isSunk() {
		return false;
	}

	/**
	 *override method to return "-" for empty ocean
	 */
	@Override
	public String toString() {
		return "-";
		
	}
	
	
	

}
