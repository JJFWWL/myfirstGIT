package battleship;

/**
 * @author Jinjie Fan, discussed with Xiaoyong Liu(Sheryl), Tian Qiu
 * represents cruiser and extends Ship
 */
public class Cruiser extends Ship {
	
	static final int length=3;
	static final String name="cruiser";

	/**
	 * sub constructor to set the length
	 */
	public Cruiser() {
		super(Cruiser.length);
		
	}

	/**
	 * override method to return name to the getshiptype
	 */
	@Override
	public String getShipType() {
		
		return (Cruiser.name);
	}
	

}
