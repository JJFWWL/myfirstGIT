package battleship;

/**
 * @author Jinjie Fan, discussed with Xiaoyong Liu(Sheryl), Tian Qiu
 * represents submarine and extends Ship
 */
public class Submarine extends Ship {
	
	static final int length=1;
	static final String name="submarine";

	/**
	 * sub constructor to set the length 
	 */
	public Submarine() {
		super(Submarine.length);
		
	}

	/**
	 * override method to return name to the getshiptype
	 */
	@Override
	public String getShipType() {
		
		return (Submarine.name);
	}
	

}
