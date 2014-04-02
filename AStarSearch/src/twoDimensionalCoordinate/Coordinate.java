package twoDimensionalCoordinate;

/**
 * Coordinate    Representation a coordinate in the 2D space.
 * 
 * @remark       Coordinate(0,0) will be the top left starting coordinate,
 *               the y value will increase downwards and the x value will
 *               increase to the right.
 * @author       Mohammed Nabee
 */
public class Coordinate 
{

	private int x,y;

	/**
	 * Constructor initializing this Coordinate.
	 */ 
	public Coordinate (int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Accessor and mutator methods for this Coordinate's
	 * fields
	 */ 
	public int getX ()
	{
		return this.x;
	}
	
	public void setX(int x)
	{
	    this.x = x;	
	}
	
	public int getY ()
	{
		return this.y;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}

	/**
	 * @function toString Provides a string representation
	 * of this object
	 * 
 	 * @param None.
	 * @exception None.
	 * 
	 * @return String representation of this object.
	 ***********************************************/ 
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
}
