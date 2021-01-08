package battleship;

/**
 * @author Jinjie Fan, discussed with Xiaoyong Liu(Sheryl), Tian Qiu
 * represents destroyer and extends Ship
 */
public class Destroyer extends Ship {
	
	static final int length=2;
	static final String name="destroyer";

	/**
	 * sub constructor to set the length
	 */
	public Destroyer() {
		super(Destroyer.length);
		
	}

	/**
	 * override method to return name to the getshiptype
	 */
	@Override
	public String getShipType() {
		
		return (Destroyer.name);
	}
	

}
