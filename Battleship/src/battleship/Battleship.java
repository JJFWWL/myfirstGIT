package battleship;

/**
 * @author Jinjie Fan, discussed with Xiaoyong Liu(Sheryl), Tian Qiu
 * represents battleship and extends Ship
 */
public class Battleship extends Ship {
	
	static final int length=4;
	static final String name="battleship";

	/**
	 * sub constructor to set the length of 4
	 */
	public Battleship() {
		super(Battleship.length);
		
	}

	/**
	 * override method to return "battleship" to the getshiptype
	 */
	@Override
	public String getShipType() {
		
		return (Battleship.name);
	}
	

}
