package terrain;

/**
 * FlatlandsTerrain       This class encapsulates information about the
 *                        Flatland terrain.
 * @author                Mohammed Nabee
 */
public class FlatlandsTerrain implements ITerrain 
{
	
	private char representation;
	private double cost;
	private boolean isWalkable;
	private String description;

	/**
	 * Constructor initializing Flatlands terrain.
	 */ 
	public FlatlandsTerrain() 
	{
		this.representation = TerrainInfo.FlatLands.REPRESENTATION;
		this.cost = TerrainInfo.FlatLands.MOVECOST;
		this.isWalkable = TerrainInfo.FlatLands.WALKABLE;
		this.description = TerrainInfo.FlatLands.DESCRIPTION;
	}

	/**
	 * Accessor and mutator methods for Flatlands
	 * fields
	 */ 
	public double getCost() 
	{
		return this.cost;
	}
	
	public void setCost(double cost)
	{
		this.cost = cost;
	}

	public char getRepresentation() 
	{	
		return this.representation;
	}

	public void setRepresentation(char representation)
	{
		this.representation = representation;
	}
	
	public boolean getIsWalkable() 
	{
		return this.isWalkable;
	}
	
	public void setIsWalkable(boolean isWalkable)
	{
		this.isWalkable = isWalkable;
	}

	public String getDescription() 
	{
		return this.description;
	}

	public void setDescription(String description) 
	{
	    this.description = description;	
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
	public String toString() 
	{
		return "FlatlandsTerrain [representation=" + representation + ", cost="
				+ cost + ", isWalkable=" + isWalkable + ", description="
				+ description + "]";
	}

}
